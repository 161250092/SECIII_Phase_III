package maven.businessLogic.statistics;

import maven.model.label.frameLabel.Frame;
import maven.model.label.frameLabel.FrameLabel;
import sun.nio.cs.ext.MacArabic;

import java.util.ArrayList;
import java.util.List;

public class FrameLabelStatistics {
    /**
     * 获得方框标注的准确性
     * @param frameLabelList 工人的标注中对于养不的部分
     * @param sampleFrameLabelList 样本标注
     * @return 标注的准确性
     */
    public double accuracyOfTask(List<FrameLabel> frameLabelList, List<FrameLabel> sampleFrameLabelList){
        if(frameLabelList.size() != sampleFrameLabelList.size()){
            System.out.println("传入的两个标注列表长度不同，无法获得准确度");
            return -1;
        }

        double totalAccuracy = 0;

        for (int i = 0; i < sampleFrameLabelList.size(); i++){
            totalAccuracy += accuracyOfLabel(frameLabelList.get(i), sampleFrameLabelList.get(i));
        }

        return totalAccuracy / (double)sampleFrameLabelList.size();
    }

    public double accuracyOfLabel(FrameLabel label, FrameLabel sampleLabel){
        List<Frame> frameList = label.getFrameList();
        List<Frame> sampleFrameList = sampleLabel.getFrameList();

        int frameNum = Math.max(frameList.size(), sampleFrameList.size());
        double totalAccuracy = 0.0;

        for (Frame frame : frameList) {
            double frameAccuracy = 0;
            for (Frame sampleFrame : sampleFrameList) {

                double frameArea = frame.getWidth() * frame.getHeight();
                double sampleFrameArea = sampleFrame.getWidth() * sampleFrame.getHeight();
                double overlappingArea = sizeOfOverlappingArea(frame, sampleFrame);

                double ratioOfOverlappingAreaToFrame = overlappingArea / frameArea;
                double ratioOfOverlappingAreaToSampleFrame = overlappingArea / sampleFrameArea;

                double estimator = Math.pow(Math.min(ratioOfOverlappingAreaToFrame, ratioOfOverlappingAreaToSampleFrame), 0.5);

                frameAccuracy = (frameAccuracy < estimator) ? estimator : frameAccuracy;
            }
            totalAccuracy += frameAccuracy;
        }

        return totalAccuracy / (double)frameNum;
    }


    public double sizeOfOverlappingArea(Frame frame, Frame sampleFrame){
        int startX = Math.max(frame.getStartX(), sampleFrame.getStartX());
        int endX = Math.min(frame.getStartX() + frame.getWidth(), sampleFrame.getStartX() + sampleFrame.getWidth());
        int overlappingWidth = endX - startX;

        int startY = Math.max(frame.getStartY(), sampleFrame.getStartY());
        int endY = Math.min(frame.getStartY() + frame.getHeight(), sampleFrame.getStartY() + sampleFrame.getHeight());
        int overlappingHeight = endY - startY;

        if (overlappingWidth < 0 || overlappingHeight < 0){
            return 0.0;
        }else {
            return overlappingWidth * overlappingHeight;
        }
    }

    //public double getAverageDistanceOfFrameLabel(FrameLabel label, FrameLabel sampleLabel){
    //    double sumOfAverageDistance = 0.0;
    //
    //    for(Frame sampleFrame: sampleLabel.getFrameList()){
    //        double averageDistanceOfThisFrame = 0.0;
    //        for (Frame frame: label.getFrameList()){
    //            if(averageDistanceOfThisFrame < getAverageDistanceOfFrame(frame, sampleFrame)){
    //                averageDistanceOfThisFrame = getAverageDistanceOfFrame(frame, sampleFrame);
    //            }
    //        }
    //        sumOfAverageDistance += averageDistanceOfThisFrame;
    //    }
    //
    //    return sumOfAverageDistance;
    //}
    //
    //public double getAverageDistanceOfFrame(Frame frame, Frame sampleFrame){
    //    double offset_upperLeft =
    //            squareOfEuclideanMetric(frame.getStartX(), frame.getStartY(), sampleFrame.getStartX(), sampleFrame.getStartY());
    //    double offset_upperRight =
    //            squareOfEuclideanMetric((frame.getStartX()+frame.getWidth()), frame.getStartY(), (sampleFrame.getStartX()+sampleFrame.getWidth()), sampleFrame.getStartY());
    //    double offset_lowerLeft =
    //            squareOfEuclideanMetric(frame.getStartX(), (frame.getStartY()+frame.getHeight()), sampleFrame.getStartX(), (sampleFrame.getStartY()+sampleFrame.getHeight()));
    //    double offset_lowerRight =
    //            squareOfEuclideanMetric((frame.getStartX()+frame.getWidth()), (frame.getStartY()+frame.getHeight()), (sampleFrame.getStartX()+sampleFrame.getWidth()), (sampleFrame.getStartY()+sampleFrame.getHeight()));
    //
    //    return (offset_upperLeft + offset_upperRight + offset_lowerLeft + offset_lowerRight)/4.0;
    //}
    //
    //public double squareOfEuclideanMetric(double x1, double y1, double x2, double y2){
    //    return Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2);
    //}
}
