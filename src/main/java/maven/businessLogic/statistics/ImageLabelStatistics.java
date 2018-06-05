package maven.businessLogic.statistics;

import maven.model.label.ImageLabel;

import java.util.List;

public class ImageLabelStatistics {
    public double accuracyOfTask(List<ImageLabel> imageLabelList, List<ImageLabel> sampleImageLabelList){
        return 0.8 + Math.random()*0.2;
    }
}
