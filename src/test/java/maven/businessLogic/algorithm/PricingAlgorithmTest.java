//package maven.businessLogic.algorithm;
//
//import maven.model.massTask.ImageNum;
//import maven.model.massTask.WorkerBid;
//import maven.model.primitiveType.Cash;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class PricingAlgorithmTest {
//
//    PricingAlgorithm pa = new PricingAlgorithm();
//
//    @Test
//    public void getThresholdPrice() {
//        List<WorkerBid> workerBidList = new ArrayList<>();
//
//        workerBidList.add(new WorkerBid(null,  null,0, new Cash(10), new ImageNum(10)));
//        workerBidList.add(new WorkerBid(null, null,0, new Cash(5), new ImageNum(10)));
//        workerBidList.add(new WorkerBid(null, null,0, new Cash(20), new ImageNum(5)));
//        workerBidList.add(new WorkerBid(null, null,0, new Cash(30), new ImageNum(20)));
//
//        double result = pa.getThresholdPrice(workerBidList, new Cash(1000)).value;
//
//        assertEquals(30, result, 0.001);
//    }
//    @Test
//    public void getThresholdPrice2() {
//        List<WorkerBid> workerBidList = new ArrayList<>();
//
//        workerBidList.add(new WorkerBid(null, null,0, new Cash(10), new ImageNum(10)));
//        workerBidList.add(new WorkerBid(null, null,0, new Cash(5), new ImageNum(10)));
//        workerBidList.add(new WorkerBid(null, null,0, new Cash(20), new ImageNum(5)));
//        workerBidList.add(new WorkerBid(null, null,0, new Cash(30), new ImageNum(20)));
//
//        double result = pa.getThresholdPrice(workerBidList, new Cash(500)).value;
//
//        assertEquals(20, result, 0.001);
//    }
//    @Test
//    public void getThresholdPrice3() {
//        List<WorkerBid> workerBidList = new ArrayList<>();
//
//        workerBidList.add(new WorkerBid(null, null,0, new Cash(15), new ImageNum(30)));
//        workerBidList.add(new WorkerBid(null, null,0, new Cash(20), new ImageNum(10)));
//        workerBidList.add(new WorkerBid(null, null,0, new Cash(20), new ImageNum(30)));
//        workerBidList.add(new WorkerBid(null, null,0, new Cash(50), new ImageNum(50)));
//        workerBidList.add(new WorkerBid(null, null,0, new Cash(60), new ImageNum(50)));
//
//        double result = pa.getThresholdPrice(workerBidList, new Cash(1000)).value;
//
//        assertEquals(20, result, 0.001);
//    }
//}