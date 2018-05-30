package maven.businessLogic.statistics;

import maven.model.label.frameLabel.Frame;
import maven.model.label.frameLabel.FrameLabel;

import java.util.List;

public class FrameLabelStatistics {
    public double getAccuracyOfFrameLabelSet(List<FrameLabel> frameLabelList, List<FrameLabel> sampleFrameLabelList){
    //    double totalAccuracy = 0, tempAccuracy = 0;
    //    for(int i = 0; i< sampleFrameLabelList.size(); i++){
    //        tempAccuracy = 0;
    //        for(int j = 0; j < sampleFrameLabelList.get(i).getFrameList().size(); j++){
    //            tempAccuracy += this.getAccuracyOfFrame(sampleFrameLabelList.get(i).getFrameList().get(j), sampleFrameLabelList.get(i).getFrameList().get(j));
    //        }
    //        tempAccuracy /= (sampleFrameLabelList.get(i).getFrameList().size());
    //        accuracy += this.getAccuracyOfFrame(frameLabelList.get(i), sampleFrameLabelList.get(i));
    //    }
        return 0;
    }

    public double getAccuracyOfFrame(Frame frame, Frame sampleFrame){
        double offset_upperLeft =
                squareOfEuclideanMetric(frame.getStartX(), frame.getStartY(), sampleFrame.getStartX(), sampleFrame.getStartY());
        double offset_upperRight =
                squareOfEuclideanMetric((frame.getStartX()+frame.getWidth()), frame.getStartY(), (sampleFrame.getStartX()+sampleFrame.getWidth()), sampleFrame.getStartY());
        double offset_lowerLeft =
                squareOfEuclideanMetric(frame.getStartX(), (frame.getStartY()+frame.getHeight()), sampleFrame.getStartX(), (sampleFrame.getStartY()+sampleFrame.getHeight()));
        double offset_lowerRight =
                squareOfEuclideanMetric((frame.getStartX()+frame.getWidth()), (frame.getStartY()+frame.getHeight()), (sampleFrame.getStartX()+sampleFrame.getWidth()), (sampleFrame.getStartY()+sampleFrame.getHeight()));

        return (offset_upperLeft + offset_upperRight + offset_lowerLeft + offset_lowerRight)/4.0;
    }

    public double squareOfEuclideanMetric(double x1, double y1, double x2, double y2){
        return Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2);
    }
}
