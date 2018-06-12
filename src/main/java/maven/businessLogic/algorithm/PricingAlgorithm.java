package maven.businessLogic.algorithm;

import maven.model.massTask.AllocatedTask;
import maven.model.massTask.ImageNum;
import maven.model.massTask.WorkerBid;
import maven.model.primitiveType.Cash;
import maven.model.user.Worker;

import java.util.*;

public class PricingAlgorithm {

    private final List<Double> quantiles = new ArrayList<>();
    private final int exponent = 2;

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
     * 根据发布者给出的预算最大化地分配任务
     * @param workerBidList 工人竞标的列表（从开始发布到截止日期的所有参与竞标该任务的工人）
     * @param budget 发布者给出的预算
     * @return 任务分配列表
     *
     *
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
        workerBidList.sort(new Comparator<WorkerBid>() {
            @Override
            public int compare(WorkerBid o1, WorkerBid o2) {
                double diff = o1.getRatioOfArrivedTime() - o2.getRatioOfArrivedTime();
                if (diff > 0)return 1;
                else return -1;
            }
        });

        //A = ∅
        List<AllocatedTask> allocatedTaskList = new ArrayList<>();
        //l
        int l = quantiles.size() - 1;
        //B' = B * 2^(-(l+1))
        double allocatedBudget = budget.value * Math.pow(2, -(l + 1));

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
            int thresholdImageNum = Math.min(maxOfWantedImageNum, (int)((2 * allocatedBudget) / thresholdPrice));

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
                        int totalAllocatedImageNum = 0;
                        for (AllocatedTask allocatedTask: allocatedTaskList){
                            totalAllocatedImageNum += allocatedTask.getImageNum().value;
                        }

                        //w_hat(i) = min{ wi, low_bound(B'/p)-∑r∈A w_hat(r) }
                        int allocatedImageNum = Math.min(bid.getMaxWantedImageNum().value, (int)(allocatedBudget / thresholdPrice) - totalAllocatedImageNum);
                        //A = A ∪ {i}
                        allocatedTaskList.add(new AllocatedTask(bid.getWorkerId(), new Cash(thresholdPrice), new ImageNum(allocatedImageNum)));
                    }
                }

            }else{
                //有2/3的概率...
                WorkerBid selectedBid = null;
                //在qj+1到qj这一个时间段中，...
                for (WorkerBid bid: bidsArrivedAtAndBeforeThisTimeStep){
                    //第一个满足 wi ≥ w*
                    if (bid.getRatioOfArrivedTime() <= quantiles.get(j-1)
                            && bid.getRatioOfArrivedTime() >= quantiles.get(j)
                            && bid.getMaxWantedImageNum().value >= thresholdImageNum){
                        selectedBid = bid;
                        break;
                    }
                }
                //按照 图片数为 w_hat(i), 金额为 p 分配给该工人
                if(selectedBid != null){
                    //w_hat(i) = min{ w1, low_bound(B'/p) }
                    int allocatedImageNum = Math.min(selectedBid.getMaxWantedImageNum().value, (int)(allocatedBudget / thresholdPrice));
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
    public double getThresholdPrice(List<WorkerBid> workerBidList, double budget){
        double thresholdPrice = 0.0;

        //排序，使 bids服从 b0 ≤ b1 ≤ ... ≤ bm-1
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

    private int getSumOfAllocatedImageNum(List<Integer> allocatedImageNumList, int index){
        int sum = 0;
        for (int j = 0; j < index; j++){
            sum += allocatedImageNumList.get(j);
        }
        return sum;
    }
}
