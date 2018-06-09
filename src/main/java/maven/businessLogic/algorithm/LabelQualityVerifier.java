package maven.businessLogic.algorithm;

import maven.data.LabelQualityData.LabelQualityDataService;
import maven.model.label.Label;
import maven.model.primitiveType.LabelQuality;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskQuality;
import maven.model.task.AcceptedTaskState;
import maven.model.user.User;

import java.util.*;

public class LabelQualityVerifier {

    private LabelQualityDataService labelQualityData;

    private double z_dot01;
    private Map<Integer, Double> t_dot01_map;

    private List<AcceptedTaskQuality> taskQualityList;

    public LabelQualityVerifier(){
        //labelQualityData
        initNormMap();
        initTMap();
    }

    public boolean isLabelQualityValid(UserId requesterId, UserId workerId, LabelQuality labelQuality){
        if (labelQuality == LabelQuality.TRUSTFUL){
            return true;
        }else {
            taskQualityList = labelQualityData.getAllAcceptedTaskQualityList();
            return !(this.isThisRequesterMoreNegativeThanOtherRequester(requesterId) &&
                    this.doesThisRequesterHavePrejudiceAgainstThisWorker(requesterId, workerId));
        }
    }

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

    ////////////////////////
    //未完成
    private boolean doesThisRequesterHavePrejudiceAgainstThisWorker(UserId requesterId, UserId workerId){
        double workerDistrustedRateFromThisRequester = 0.0;

        Map<UserId, Integer> thisWorkerDistrustedCountMap = new HashMap<>();
        Map<UserId, Integer> thisWorkerTotalCountMap = new HashMap<>();
        List<Double> thisWorkerDistrustedRateList = new ArrayList<>();

        for(AcceptedTaskQuality taskQuality: taskQualityList){
            UserId currentWorkerId = taskQuality.getWorkerId();
            if (currentWorkerId.value.equals(workerId.value)) continue;

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
    ////////////////////////

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

    private double mean(List<Double> data){
        int n = data.size();
        if (n == 0) return 0.0;

        double sum = 0.0;
        for (Double num: data){
            sum += num;
        }

        return sum / (double)n;
    }

    private double sampleVariance(List<Double> data){
        int n = data.size();
        if (n == 0) return 0.0;
        if (n == 1) return data.get(0);

        double mean = this.mean(data);

        double squareSum = 0.0;
        for (Double num: data){
            squareSum += Math.pow(num - mean, 2);
        }

        return squareSum / (double)(n-1);
    }

    private double variance(List<Double> data){
        int n = data.size();
        if (n == 0) return 0.0;

        double mean = this.mean(data);

        double squareSum = 0.0;
        for (Double num: data){
            squareSum += Math.pow(num - mean, 2);
        }

        return squareSum / (double)n;
    }

    private void solveCountMap(Map<UserId, Integer> distrustfulCountMap, Map<UserId, Integer> totalCountMap, AcceptedTaskQuality taskQuality, UserId currentRequesterId) {
        if (totalCountMap.containsKey(currentRequesterId)){
            int requesterTotalCount = totalCountMap.get(currentRequesterId);
            totalCountMap.put(currentRequesterId, requesterTotalCount+1);

            if (taskQuality.getAcceptedTaskState() == AcceptedTaskState.ABANDONED_BY_REQUESTOR){
                int requesterDistrustfulCount = totalCountMap.get(currentRequesterId);
                distrustfulCountMap.put(currentRequesterId, requesterDistrustfulCount+1);
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

    private double getRequiredDistrustfulRateAndDistrustfulRateList(UserId requesterId, Map<UserId, Integer> requesterDistrustfulCountMap, Map<UserId, Integer> requesterTotalCountMap, List<Double> requesterDistrustfulRateList) {
        double requiredDistrustfulRate = 0.0;
        for (UserId currentRequesterId: requesterTotalCountMap.keySet()){
            int requesterDistrustfulCount = requesterDistrustfulCountMap.get(currentRequesterId);
            int requesterTotalCount = requesterTotalCountMap.get(currentRequesterId);
            double requesterDistrustfulRate = (double)requesterDistrustfulCount / (double)requesterTotalCount;

            requesterDistrustfulRateList.add(requesterDistrustfulRate);

            if (currentRequesterId.value.equals(requesterId.value)){
                requiredDistrustfulRate = requesterDistrustfulRate;
            }
        }
        return requiredDistrustfulRate;
    }

    private double getEstimate(List<Double> distrustfulRateList, double requiredDistrustfulRateList){
        //假设发布者的消极评价服从正态分布，X ～ N（μ, σ^2）
        //μ的最大似然估计值为平均值
        double mu_hat = this.mean(distrustfulRateList);
        //σ^2的最大似然估计值为方差
        double sigma_hat = Math.pow(this.variance(distrustfulRateList), 0.5);

        //估计量：(X_bar - μ_hat) / σ_hat
        return (requiredDistrustfulRateList - mu_hat) / sigma_hat;
    }
}
