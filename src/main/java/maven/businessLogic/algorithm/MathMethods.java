package maven.businessLogic.algorithm;

import java.util.List;

class MathMethods {
    static double mean(List<Double> data){
        int n = data.size();
        if (n == 0) return 0.0;

        double sum = 0.0;
        for (Double num: data){
            sum += num;
        }

        return sum / (double)n;
    }
    static double sum(List<Double> data){
        double sum = 0.0;
        for (Double num: data){
            sum += num;
        }
        return sum;
    }
}
