package maven.businessLogic.algorithm;

import maven.model.massTask.WorkerBid;
import maven.model.user.Worker;

import java.util.ArrayList;
import java.util.List;

public class LinearRegression {

    public double getEstimateUnitPrice(List<WorkerBid> bidList, int taskImageNum){
        int remainAllocatedTaskImageNum = 2 * taskImageNum;

        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        for (WorkerBid bid: bidList){
            remainAllocatedTaskImageNum -= bid.getMaxWantedImageNum().value;
            xList.add((double)bid.getMaxWantedImageNum().value);
            yList.add(bid.getWantedUnitPrice().value);
        }

        Tuple tuple = this.findLinearRelationship(xList, yList);

        double last_worker_time = bidList.get(bidList.size()-1).getRatioOfArrivedTime();
        double estimate_of_worker_num_in_remain_time = (bidList.size() / last_worker_time) * (1 - last_worker_time);
        double estimate_of_image_num_per_worker = remainAllocatedTaskImageNum / estimate_of_worker_num_in_remain_time;

        return tuple.slope * estimate_of_image_num_per_worker + tuple.intercept;
    }
    private Tuple findLinearRelationship(List<Double> xList, List<Double> yList){
        double x_mean = MathMethods.mean(xList);
        double y_mean = MathMethods.mean(yList);

        double slope = this.getSlope(xList, yList, x_mean, y_mean);
        double intercept = this.getIntercept(x_mean, y_mean, slope);

        Tuple tuple = new Tuple();
        tuple.slope = slope;
        tuple.intercept = intercept;
        return tuple;
    }
    private double getSlope(List<Double> xList, List<Double> yList, double x_mean, double y_mean){
        List<Double> x_minus_xMean_multiply_y_minus_yMean_list = new ArrayList<>();
        for (int i = 0; i < xList.size(); i++){
            x_minus_xMean_multiply_y_minus_yMean_list.add(
                    (xList.get(i) - x_mean) * (yList.get(i) - y_mean)
            );
        }
        double numeratorOfSlope = MathMethods.sum(x_minus_xMean_multiply_y_minus_yMean_list);

        List<Double> square_of_x_minus_x_mean_list = new ArrayList<>();
        for (Double xi: xList){
            square_of_x_minus_x_mean_list.add(
                    (xi - x_mean) * (xi - x_mean)
            );
        }
        double denominatorOfSlope = MathMethods.sum(square_of_x_minus_x_mean_list);

        return numeratorOfSlope / denominatorOfSlope;
    }
    private double getIntercept(double x_mean, double y_mean, double slope){
        return y_mean - slope * x_mean;
    }

    class Tuple{
        double slope;
        double intercept;
    }
}
