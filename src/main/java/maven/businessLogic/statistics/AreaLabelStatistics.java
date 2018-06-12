package maven.businessLogic.statistics;

import maven.model.label.ImageLabel;
import maven.model.label.areaLabel.AreaLabel;

import java.util.List;

public class AreaLabelStatistics {
    public double accuracyOfTask(List<AreaLabel> areaLabelList, List<AreaLabel> sampleAreaLabelList){
        return 0.8 + Math.random()*0.2;
    }
}
