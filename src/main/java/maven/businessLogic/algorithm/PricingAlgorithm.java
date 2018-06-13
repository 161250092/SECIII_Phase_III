package maven.businessLogic.algorithm;

import maven.model.massTask.AllocatedTask;
import maven.model.massTask.ImageNum;
import maven.model.massTask.WorkerBid;
import maven.model.primitiveType.Cash;

import java.util.*;

public class PricingAlgorithm {

    private final List<Double> quantiles = new ArrayList<>();
    private final int exponent = 2;

    private static int LAST_TIME_STEP_LOWER_BOUND_INDEX = 1;

    public PricingAlgorithm(){
        //初始化时间分位数数组 quantiles{q0, q1, ..., ql}，其中q0 = 1, ql = 0, qi一定是2的幂次
        double base = Math.pow(2, -exponent);
        double quantile = 1;
        while (quantile >= 0){
            quantiles.add(quantile);
            quantile -= base;
        }
    }

    /**
     * 根据任务的图片数，发布者给出的预算和发布者给出的图片[单价]来最小化支出
     * @param workerBidList 工人竞标的列表（从开始发布到截止日期的所有参与竞标该任务的工人）
     * @param totalImageNumOfThisTask 该任务的图片数
     * @param budget 发布者给出的预算
     * @param givenUnitPriceByRequester 发布者给出的图片[单价]
     * @return 任务分配列表
     *
     *
     * time step t∈{1,...,T}
     * input: number of tasks L, budget β, price δ, q1, T
     * 1. For all ai who arrive at time t≤q1, if bi≤δ do:
     *       allocate w_hat(i) = min{ wi, lower_bound(β/δ) - ∑r∈A w_hat(r) }
     *       set A = A ∪ {i}
     *
     * 2. At time step t = q1 do:
     *       set B = FindMinCost( S(t), 2L )
     *       set p = GetThreshold( S(t), B )
     *       w* = min{ max{a∈S(t)|ci≤p} wi, lower_bound(B/p) }
     *
     * 3. with probability 1/4 do:
     *       for all ai who arrive at time t>q1 s.t. bi≤p do:
     *           allocate w_hat(i) = min{ wi, lower_bound(B/p) - ∑r∈A w_hat(r) } at p
     *
     *    with probability 3/4 do:
     *        for first ai arriving by T s.t. wi≥w*:
     *            allocate w_hat(i) = min{ wi, lower_bound(B/p) } at p
     *
     */
    public List<AllocatedTask> minimizePayments(List<WorkerBid> workerBidList,ImageNum totalImageNumOfThisTask, Cash budget, Cash givenUnitPriceByRequester) {
        //按到达时间排序
        sortWorkerBidListByTimeOrder(workerBidList);

        //分配列表
        List<AllocatedTask> allocatedTaskList = new ArrayList<>();

        //A = ∅
        List<AllocatedTask> sampleAllocatedTaskList = new ArrayList<>();

        for (WorkerBid bid: workerBidList){
            //在 到达时间≤q1 且 bi≤δ 的所有工人中...
            if (bid.getRatioOfArrivedTime() <= quantiles.get(LAST_TIME_STEP_LOWER_BOUND_INDEX)
                    && bid.getWantedUnitPrice().value <= givenUnitPriceByRequester.value){

                //∑r∈A w_hat(r)
                int totalSampleAllocatedImageNum = 0;
                for (AllocatedTask sampleAllocatedTask: sampleAllocatedTaskList){
                    totalSampleAllocatedImageNum += sampleAllocatedTask.getImageNum().value;
                }

                //w_hat(i) = min{ wi, low_bound(β/δ)-∑r∈A w_hat(r) }
                int allocatedImageNum = Math.min(bid.getMaxWantedImageNum().value, (int)(budget.value / givenUnitPriceByRequester.value) - totalSampleAllocatedImageNum);
                //将 单价为δ，图片数为w_hat(i) 分配给该工人
                AllocatedTask allocatedTaskForThisWorker = new AllocatedTask(bid.getWorkerId(), givenUnitPriceByRequester, new ImageNum(allocatedImageNum));
                allocatedTaskList.add(allocatedTaskForThisWorker);
                //A = A ∪ {i}
                sampleAllocatedTaskList.add(allocatedTaskForThisWorker);
            }
        }

        //S(t) , t = q1
        List<WorkerBid> bidsArrivedAtAndBeforeThisTimeStep = new ArrayList<>();
        for (WorkerBid bid: workerBidList){
            if(bid.getRatioOfArrivedTime() <= quantiles.get(LAST_TIME_STEP_LOWER_BOUND_INDEX)){
                bidsArrivedAtAndBeforeThisTimeStep.add(bid);
            }
        }
        //B = FindMinCost( S(t), 2L ) , t = q1
        double allocatedBudget = this.findMinCost(bidsArrivedAtAndBeforeThisTimeStep, 2 * totalImageNumOfThisTask.value);
        //p = GetThreshold( S(t), B ) , t = q1
        double thresholdPrice = this.getThresholdPrice(bidsArrivedAtAndBeforeThisTimeStep, allocatedBudget);
        
        //找 max{a∈S(t)|ci≤p} wi , t = q1
        int maxOfWantedImageNum = 0;
        for(WorkerBid bid: bidsArrivedAtAndBeforeThisTimeStep){
            //a∈S(t)|ci≤p , t = q1
            if (bid.getWantedUnitPrice().value <= thresholdPrice && maxOfWantedImageNum < bid.getMaxWantedImageNum().value){
                maxOfWantedImageNum = bid.getMaxWantedImageNum().value;
            }
        }
        //w* = min{ max{a∈S(t)|ci≤p} wi, low_bound(B/p) }
        int estimateOfMaxImageNumAWorkerCanPerform = Math.min(maxOfWantedImageNum, (int)(allocatedBudget / thresholdPrice));

        if (Math.random() <= 1/4){
            //有1/4的概率...
            for (WorkerBid bid: workerBidList){
                //在最后一个时间段到达的所有工人中，找到满足 bi ≤ p 的
                if (bid.getRatioOfArrivedTime() > quantiles.get(LAST_TIME_STEP_LOWER_BOUND_INDEX)
                        && bid.getWantedUnitPrice().value <= thresholdPrice){

                    //∑r∈A w_hat(r)
                    int totalSampleAllocatedImageNum = 0;
                    for (AllocatedTask sampleAllocatedTask: sampleAllocatedTaskList){
                        totalSampleAllocatedImageNum += sampleAllocatedTask.getImageNum().value;
                    }

                    //w_hat(i) = min{ wi, low_bound(B/p)-∑r∈A w_hat(r) }
                    int allocatedImageNum = Math.min(bid.getMaxWantedImageNum().value, (int)(allocatedBudget / thresholdPrice) - totalSampleAllocatedImageNum);
                    //将 单价为p，图片数为w_hat(i) 分配给该工人
                    allocatedTaskList.add(new AllocatedTask(bid.getWorkerId(), new Cash(thresholdPrice), new ImageNum(allocatedImageNum)));
                }
            }
        }else {
            //有3/4的概率...
            WorkerBid selectedBid = null;
            //在最后一个时间段中，...
            for (WorkerBid bid: bidsArrivedAtAndBeforeThisTimeStep){
                //第一个满足 wi ≥ w* 的工人
                if (bid.getRatioOfArrivedTime() >= quantiles.get(LAST_TIME_STEP_LOWER_BOUND_INDEX)
                        && bid.getMaxWantedImageNum().value >= estimateOfMaxImageNumAWorkerCanPerform){
                    selectedBid = bid;
                    break;
                }
            }
            //按照 图片数为 w_hat(i), 金额为 p 分配给该工人
            if(selectedBid != null){
                //w_hat(i) = min{ w1, low_bound(B/p) }
                int allocatedImageNum = Math.min(selectedBid.getMaxWantedImageNum().value, (int)(allocatedBudget / thresholdPrice));
                //将 单价为p，图片数为w_hat(i) 分配给该工人
                allocatedTaskList.add(new AllocatedTask(selectedBid.getWorkerId(), new Cash(thresholdPrice), new ImageNum(allocatedImageNum)));
            }
        }

        return allocatedTaskList;
    }

    /**
     * 根据简单的贪婪算法获得 处理双倍该任务图片数的最低金额
     * @param bidsArrivedAtAndBeforeThisTimeStep 在这一时间点以及之前到达的工人竞标
     * @param doubleImageNum 双倍该任务的图片数
     * @return 处理双倍图片数的最低金额
     */
    private double findMinCost(List<WorkerBid> bidsArrivedAtAndBeforeThisTimeStep, int doubleImageNum){
        if (bidsArrivedAtAndBeforeThisTimeStep.size() == 0) return 0.0;

        //按工人竞标的图片单价进行排序，从小到大
        bidsArrivedAtAndBeforeThisTimeStep.sort(new Comparator<WorkerBid>() {
            @Override
            public int compare(WorkerBid o1, WorkerBid o2) {
                double diff = o1.getWantedUnitPrice().value - o2.getWantedUnitPrice().value;
                if (diff > 0)return 1;
                else if(diff == 0) return 0;
                else return -1;
            }
        });

        int allocatedImageNum = 0;
        double minCost = Double.POSITIVE_INFINITY;
        //只要让 分配的图片数 达到 双倍该任务的图片数 即可
        for(WorkerBid bid: bidsArrivedAtAndBeforeThisTimeStep ){
            if(allocatedImageNum > doubleImageNum) break;

            allocatedImageNum += bid.getMaxWantedImageNum().value;
            minCost = (minCost > bid.getWantedUnitPrice().value)? bid.getWantedUnitPrice().value: minCost;
        }

        return minCost;
    }

    /**
     * 根据发布者给出的预算最大化地分配任务
     * @param workerBidList 工人竞标的列表（从开始发布到截止日期的所有参与竞标该任务的工人）
     * @param budget 发布者给出的预算
     * @return 任务分配列表
     *
     *
     * time step t∈{1,...,T}
     * input: Budget B, quantiles {q1,...,ql}
     * 1. initialize: B' = B * 2^(l+1), t = 1
     *
     * 2. For every quantile qj, j = l,l-1,...,1 do:
     *      a. at time step t= qj set:
     *           p = GetThreshold( S(t), 2B' ), S(t) is the set of all bids that arrived at and before time step 1
     *           w* = min{ max{a∈S(t)|ci≤p} wi, lower_bound(2B' / p) }
     *           B' = 2B',  A = ∅
     *
     *      b. with probability 1/3 do:
     *           while qj < t ≤ qj+1:
     *                for all ai who arrive at time t s.t. bi ≤ p do:
     *                    allocate w_hat(i) = min{ wi, lower_bound(B'/p} - ∑r∈A w_hat(r) } at p, set A = A ∪ {i}
     *
     *         with probability 2/3 do:
     *             for first ai arriving by qj+1 s.t. wi ≥ w*:
     *                  allocate w_hat(i) = min{ wi, lower_bound(B'/p} at p
     *
     *
     */
    public List<AllocatedTask> maximizeTasks(List<WorkerBid> workerBidList, Cash budget){
        //按到达时间排序
        sortWorkerBidListByTimeOrder(workerBidList);

        //分配列表
        List<AllocatedTask> allocatedTaskList = new ArrayList<>();

        //l
        int l = quantiles.size() - 1;
        //B' = B * 2^(-(l+1))
        double allocatedBudget = budget.value * Math.pow(2, -(l + 1));
        //A = ∅
        List<AllocatedTask> sampleAllocatedTaskList = new ArrayList<>();

        for (int j = l; j >= 1; j--){
            //S(t)
            List<WorkerBid> bidsArrivedAtAndBeforeThisTimeStep = new ArrayList<>();
            for (WorkerBid bid: workerBidList){
                if(bid.getRatioOfArrivedTime() <= quantiles.get(j-1)){
                    bidsArrivedAtAndBeforeThisTimeStep.add(bid);
                }
            }
            //p = GetThreshold( S(t), 2B' )
            double thresholdPrice = this.getThresholdPrice(bidsArrivedAtAndBeforeThisTimeStep, 2 * allocatedBudget);

            //找 max{a∈S(t)|ci≤p} wi
            int maxOfWantedImageNum = 0;
            for(WorkerBid bid: bidsArrivedAtAndBeforeThisTimeStep){
                //a∈S(t)|ci≤p
                if (bid.getWantedUnitPrice().value <= thresholdPrice && maxOfWantedImageNum < bid.getMaxWantedImageNum().value){
                    maxOfWantedImageNum = bid.getMaxWantedImageNum().value;
                }
            }
            //w* = min{ max{a∈S(t)|ci≤p} wi, low_bound(2B'/p) }
            int estimateOfMaxImageNumAWorkerCanPerform = Math.min(maxOfWantedImageNum, (int)((2 * allocatedBudget) / thresholdPrice));

            //B' = 2B'
            allocatedBudget = 2 * allocatedBudget;

            if (Math.random() <= 1/3){
                //有1/3的概率...
                for (WorkerBid bid: bidsArrivedAtAndBeforeThisTimeStep){
                    //在时间段t到达的所有工人中，找到满足 bi ≤ p 的
                    if (bid.getRatioOfArrivedTime()< quantiles.get(j-1)
                            && bid.getRatioOfArrivedTime() >= quantiles.get(j)
                            && bid.getWantedUnitPrice().value <= thresholdPrice){

                        //∑r∈A w_hat(r)
                        int totalSampleAllocatedImageNum = 0;
                        for (AllocatedTask sampleAllocatedTask: sampleAllocatedTaskList){
                            totalSampleAllocatedImageNum += sampleAllocatedTask.getImageNum().value;
                        }

                        //w_hat(i) = min{ wi, low_bound(B'/p)-∑r∈A w_hat(r) }
                        int allocatedImageNum = Math.min(bid.getMaxWantedImageNum().value, (int)(allocatedBudget / thresholdPrice) - totalSampleAllocatedImageNum);
                        //将 单价为p，图片数为w_hat(i) 分配给该工人
                        AllocatedTask allocatedTaskForThisWorker = new AllocatedTask(bid.getWorkerId(), new Cash(thresholdPrice), new ImageNum(allocatedImageNum));
                        allocatedTaskList.add(allocatedTaskForThisWorker);
                        //A = A ∪ {i}
                        sampleAllocatedTaskList.add(allocatedTaskForThisWorker);
                    }
                }

            }else{
                //有2/3的概率...
                WorkerBid selectedBid = null;
                //在qj+1到qj这一个时间段中，...
                for (WorkerBid bid: bidsArrivedAtAndBeforeThisTimeStep){
                    //第一个满足 wi ≥ w* 的工人
                    if (bid.getRatioOfArrivedTime() <= quantiles.get(j-1)
                            && bid.getRatioOfArrivedTime() >= quantiles.get(j)
                            && bid.getMaxWantedImageNum().value >= estimateOfMaxImageNumAWorkerCanPerform){
                        selectedBid = bid;
                        break;
                    }
                }
                //按照 图片数为 w_hat(i), 金额为 p 分配给该工人
                if(selectedBid != null){
                    //w_hat(i) = min{ w1, low_bound(B'/p) }
                    int allocatedImageNum = Math.min(selectedBid.getMaxWantedImageNum().value, (int)(allocatedBudget / thresholdPrice));
                    //将 单价为p，图片数为w_hat(i) 分配给该工人
                    allocatedTaskList.add(new AllocatedTask(selectedBid.getWorkerId(), new Cash(thresholdPrice), new ImageNum(allocatedImageNum)));
                }
            }
        }

        return allocatedTaskList;
    }

    /**
     * 获得发布任务的最低图片单价
     * @param workerBidList 工人投标列表 bids = {(b0, w0),...,(bm-1, wm-1}
     * @param budget 该任务的预算 B
     * @return 该任务在当前工人列表中的图片单价 p
     */
    public double getThresholdPrice(List<WorkerBid> workerBidList, Cash budget){
        return this.getThresholdPrice(workerBidList, budget.value);
    }

    /**
     * 获得发布任务的最低图片单价
     * @param workerBidList 工人投标列表 bids = {(b0, w0),...,(bm-1, wm-1}
     * @param budget 该任务的预算 B
     * @return 该任务在当前工人列表中的图片单价 p
     *
     *
     * input: bids {(b1,w1),...,(bm,wm)}, Budget B
     *
     * 1. initialize: sort bids s.t. b1 ≤ b2 ≤ ...≤ bm; seti =1
     * 2. while bi ≤ B / (∑j<i w_bar(i) + 1)
     *      set: p = bi, ¯
     *           wi = min {wi,lower_bound(B / p) − ∑j<i w_bar(i)},
     *           i = i + 1;
     *
     * output: p
     *
     *
     */
    private double getThresholdPrice(List<WorkerBid> workerBidList, double budget){
        double thresholdPrice = 0.0;

        //按竞标的图片单价排序，使 bids服从 b0 ≤ b1 ≤ ... ≤ bm-1
        workerBidList.sort(new Comparator<WorkerBid>() {
            @Override
            public int compare(WorkerBid o1, WorkerBid o2) {
                return (int)(o1.getWantedUnitPrice().value - o2.getWantedUnitPrice().value);
            }
        });

        //{w0,...,wm-1}
        List<Integer> allocatedImageNumList = new ArrayList<>();

        //将i设为0
        int index = 0;
        //∑j<i w_bar(i)
        int sumOfAllocatedImageNum = 0;
        //当bi ≤ B / (∑j<i w_bar(i) + 1)时，执行循环   循环可能遍历整个{w0,...,wm-1}
        while(index < workerBidList.size()
                && workerBidList.get(index).getWantedUnitPrice().value <= (budget / (sumOfAllocatedImageNum + 1))){
            double currentWantedPrice = workerBidList.get(index).getWantedUnitPrice().value;
            int currentMaxWantedImageNum = workerBidList.get(index).getMaxWantedImageNum().value;

            //p = bi
            thresholdPrice = currentWantedPrice;
            //w_bar(i) = min{wi, (B / p)取下界 -  ∑j<i w_bar(i)}
            allocatedImageNumList.add(Math.min(currentMaxWantedImageNum, (int)(budget / thresholdPrice) - sumOfAllocatedImageNum));

            //i = i +1
            index++;
            sumOfAllocatedImageNum = this.getSumOfAllocatedImageNum(allocatedImageNumList, index);
        }

        //输出p
        return thresholdPrice;
    }

    /**
     * 指定获得已分配图片的总数 ∑j<i w_bar(i)
     * @param allocatedImageNumList 已分配图片数的列表
     * @param index 统计到哪，即 j<i 中的 i
     * @return 所需的已分配图片的总数 ∑j<i w_bar(i)
     */
    private int getSumOfAllocatedImageNum(List<Integer> allocatedImageNumList, int index){
        int sum = 0;
        for (int j = 0; j < index; j++){
            sum += allocatedImageNumList.get(j);
        }
        return sum;
    }

    /**
     * 将工人竞价的列表按时间先后顺序排序 从小到大
     * @param workerBidList 工人竞价的列表
     */
    private void sortWorkerBidListByTimeOrder(List<WorkerBid> workerBidList) {
        workerBidList.sort(new Comparator<WorkerBid>() {
            @Override
            public int compare(WorkerBid o1, WorkerBid o2) {
                double diff = o1.getRatioOfArrivedTime() - o2.getRatioOfArrivedTime();
                if (diff > 0)return 1;
                else if(diff == 0) return 0;
                else return -1;
            }
        });
    }
}
