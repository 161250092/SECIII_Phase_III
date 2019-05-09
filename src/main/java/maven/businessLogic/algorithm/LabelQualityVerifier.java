package maven.businessLogic.algorithm;

import maven.data.LabelQualityData.LabelQualityDataImpl;
import maven.data.LabelQualityData.LabelQualityDataService;
import maven.model.primitiveType.LabelQuality;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskQuality;
import maven.model.task.AcceptedTaskState;

import java.util.*;

public class LabelQualityVerifier {

    private LabelQualityDataService labelQualityData;

    private double z_dot01;
    private Map<Integer, Double> t_dot01_map;

    private List<AcceptedTaskQuality> taskQualityList;

    public LabelQualityVerifier(){
        labelQualityData = new LabelQualityDataImpl();
        initNormMap();
        initTMap();
    }

    /**
     * 判断该任务评价是否有效
     * @param requesterId 该任务的发布者
     * @param workerId 接受该任务的工人
     * @param labelQuality 任务评价
     * @return 该任务评价是否有效
     */
    public boolean isLabelQualityValid(UserId requesterId, UserId workerId, LabelQuality labelQuality){
        if (labelQuality == LabelQuality.TRUSTFUL){
            //若为积极评价，则有效
            return true;
        }else {
            //若为消极评价，则 该发布者比其他发布者更偏激 且 该发布者对该工人有偏见 时无效
            taskQualityList = labelQualityData.getAllAcceptedTaskQualityList();
            return !(this.isThisRequesterMoreNegativeThanOtherRequester(requesterId) &&
                    this.doesThisRequesterHavePrejudiceAgainstThisWorker(requesterId, workerId));
        }
    }

    /**
     * 判断该发布者是否比其他发布者更偏激
     * @param requesterId 该发布者
     * @return 该发布者是否比其他发布者更偏激
     */
    private boolean isThisRequesterMoreNegativeThanOtherRequester(UserId requesterId){
        //该发布者的消极评价率
        double thisRequesterDistrustfulRate = 0.0;

        //发布者的所有消极评价次数
        Map<UserId, Integer> requesterDistrustfulCountMap = new HashMap<>();
        //发布者的所有评价次数
        Map<UserId, Integer> requesterTotalCountMap = new HashMap<>();
        //发布者的消极评价率
        List<Double> requesterDistrustfulRateList = new ArrayList<>();

        //获得所有发布者所做过的所有评价以及所有消极评价
        for(AcceptedTaskQuality taskQuality: taskQualityList){
            UserId currentRequesterId = taskQuality.getRequestorId();
            solveCountMap(requesterDistrustfulCountMap, requesterTotalCountMap, taskQuality, currentRequesterId);
        }

        //获得发布者的消极评价率
        thisRequesterDistrustfulRate =
                getRequiredDistrustfulRateAndDistrustfulRateList(requesterId, requesterDistrustfulCountMap, requesterTotalCountMap, requesterDistrustfulRateList);


        double estimate = getEstimate(requesterDistrustfulRateList, thisRequesterDistrustfulRate);

        //若估计量大于Z_0.01，则说明该发布者比其他发布者更容易给出消极评价
        return estimate > z_dot01;
    }

    /**
     * 判断该发布者是否对该工人有偏见
     * @param requesterId 该发布者
     * @param workerId 该工人
     * @return 该发布者是否对该工人有偏见
     */
    private boolean doesThisRequesterHavePrejudiceAgainstThisWorker(UserId requesterId, UserId workerId){
        //该工人从该发布者获得的消极评价率
        double workerDistrustedRateFromThisRequester = 0.0;

        //所有发布者对该工人的消极评价数
        Map<UserId, Integer> thisWorkerDistrustedCountMap = new HashMap<>();
        //所有发布者对该工人的总评价数
        Map<UserId, Integer> thisWorkerTotalCountMap = new HashMap<>();
        //所有发布者对该工人的消极评价率
        List<Double> thisWorkerDistrustedRateList = new ArrayList<>();

        for(AcceptedTaskQuality taskQuality: taskQualityList){
            //只有当前该任务是由该工人接受的才会记录
            UserId currentWorkerId = taskQuality.getWorkerId();
            if (!currentWorkerId.value.equals(workerId.value)) continue;

            //该任务是由该工人接受的
            UserId currentRequesterId = taskQuality.getRequestorId();
            solveCountMap(thisWorkerDistrustedCountMap, thisWorkerTotalCountMap, taskQuality, currentRequesterId);
        }

        //获得发布者的消极评价率
        workerDistrustedRateFromThisRequester
                = getRequiredDistrustfulRateAndDistrustfulRateList(requesterId, thisWorkerDistrustedCountMap, thisWorkerTotalCountMap, thisWorkerDistrustedRateList);


        double estimate = getEstimate(thisWorkerDistrustedRateList, workerDistrustedRateFromThisRequester);

        //若估计量大于Z_0.01，则说明该发布者比其他发布者更容易给出消极评价
        return estimate > z_dot01;
    }

    private void initNormMap(){
        z_dot01 = 2.3263478740408408;
    }

    private void initTMap(){
        t_dot01_map = new HashMap<Integer, Double>();

        t_dot01_map.put(2, 6.964556718755855);
        t_dot01_map.put(3, 4.540702858698419);
        t_dot01_map.put(4, 3.7469473879811366);
        t_dot01_map.put(5, 3.3649299989072743);
        t_dot01_map.put(6, 3.142668403290985);
        t_dot01_map.put(7, 2.9979515668685277);
        t_dot01_map.put(8, 2.896459442760522);
        t_dot01_map.put(9, 2.8214379233005493);
        t_dot01_map.put(10, 2.763769457447889);
        t_dot01_map.put(11, 2.7180791835355564);
        t_dot01_map.put(12, 2.680997992996005);
        t_dot01_map.put(13, 2.6503088378527013);
        t_dot01_map.put(14, 2.624494067560231);
        t_dot01_map.put(15, 2.602480294995493);
        t_dot01_map.put(16, 2.583487185267472);
        t_dot01_map.put(17, 2.5669339837199097);
        t_dot01_map.put(18, 2.552379630179453);
        t_dot01_map.put(19, 2.5394831906222883);
        t_dot01_map.put(20, 2.527977002740546);
        t_dot01_map.put(21, 2.517648016044097);
        t_dot01_map.put(22, 2.508324552898667);
        t_dot01_map.put(23, 2.4998667394943976);
        t_dot01_map.put(24, 2.4921594731575762);
        t_dot01_map.put(25, 2.4851071754106413);
        t_dot01_map.put(26, 2.478629823591159);
        t_dot01_map.put(27, 2.4726599119559487);
        t_dot01_map.put(28, 2.4671400979674316);
        t_dot01_map.put(29, 2.4620213601503833);
    }

    private double sampleVariance(List<Double> data){
        int n = data.size();
        if (n == 0) return 0.0;
        if (n == 1) return data.get(0);

        double mean = MathMethods.mean(data);

        double squareSum = 0.0;
        for (Double num: data){
            squareSum += Math.pow(num - mean, 2);
        }

        return squareSum / (double)(n-1);
    }

    private double variance(List<Double> data){
        int n = data.size();
        if (n == 0) return 0.0;

        double mean = MathMethods.mean(data);

        double squareSum = 0.0;
        for (Double num: data){
            squareSum += Math.pow(num - mean, 2);
        }

        return squareSum / (double)n;
    }

    /**
     * 获得消极评价数映射表以及总评价数映射表
     * @param distrustfulCountMap 消极评价数映射表
     * @param totalCountMap 总评价数映射表
     * @param taskQuality 当前任务的任务质量，是积极还是消极
     * @param currentRequesterId 当前任务的发布者
     */
    private void solveCountMap(Map<UserId, Integer> distrustfulCountMap, Map<UserId, Integer> totalCountMap, AcceptedTaskQuality taskQuality, UserId currentRequesterId) {
        if (totalCountMap.containsKey(currentRequesterId)){
            int totalCount = totalCountMap.get(currentRequesterId);
            totalCountMap.put(currentRequesterId, totalCount+1);

            if (taskQuality.getAcceptedTaskState() == AcceptedTaskState.ABANDONED_BY_REQUESTOR){
                int distrustfulCount = totalCountMap.get(currentRequesterId);
                distrustfulCountMap.put(currentRequesterId, distrustfulCount+1);
            }
        }else {
            totalCountMap.put(currentRequesterId, 1);

            if (taskQuality.getAcceptedTaskState() == AcceptedTaskState.ABANDONED_BY_REQUESTOR){
                distrustfulCountMap.put(currentRequesterId, 1);
            }else {
                distrustfulCountMap.put(currentRequesterId, 0);
            }
        }
    }

    /**
     * 获得该用户的消极评价率以及所有用户的消极评价率
     * @param requesterId 目标发布者
     * @param distrustfulCountMap 消极评价数映射表
     * @param totalCountMap 总评价数映射表
     * @param distrustfulRateList 消极评价率列表
     * @return 该用户的消极评价率
     */
    private double getRequiredDistrustfulRateAndDistrustfulRateList(
            UserId requesterId, Map<UserId, Integer> distrustfulCountMap, Map<UserId, Integer> totalCountMap, List<Double> distrustfulRateList) {

        double requiredDistrustfulRate = 0.0;
        for (UserId currentRequesterId: totalCountMap.keySet()){
            int distrustfulCount = distrustfulCountMap.get(currentRequesterId);
            int totalCount = totalCountMap.get(currentRequesterId);
            double requesterDistrustfulRate = (double)distrustfulCount / (double)totalCount;

            distrustfulRateList.add(requesterDistrustfulRate);

            if (currentRequesterId.value.equals(requesterId.value)){
                requiredDistrustfulRate = requesterDistrustfulRate;
            }
        }
        return requiredDistrustfulRate;
    }

    /**
     * 求估计值
     * @param distrustfulRateList 消极评价率列表
     * @param requiredDistrustfulRateList 该用户的消极评价率
     * @return 估计值
     */
    private double getEstimate(List<Double> distrustfulRateList, double requiredDistrustfulRateList){
        //假设发布者的消极评价服从正态分布，X ～ N（μ, σ^2）
        //μ的最大似然估计值为平均值
        double mu_hat = MathMethods.mean(distrustfulRateList);
        //σ^2的最大似然估计值为方差
        double sigma_hat = Math.pow(this.variance(distrustfulRateList), 0.5);

        //估计量：(X_bar - μ_hat) / σ_hat
        return (requiredDistrustfulRateList - mu_hat) / sigma_hat;
    }
}
