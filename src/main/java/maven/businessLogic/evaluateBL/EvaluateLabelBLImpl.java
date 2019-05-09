package maven.businessLogic.evaluateBL;

import maven.businessLogic.statistics.AreaLabelStatistics;
import maven.businessLogic.statistics.FrameLabelStatistics;
import maven.businessLogic.statistics.ImageLabelStatistics;
import maven.data.MarkLabelData.AreaLabelData.AreaLabelDataImpl;
import maven.data.MarkLabelData.AreaLabelData.AreaLabelDataService;
import maven.data.MarkLabelData.FrameLabelData.FrameLabelDataImpl;
import maven.data.MarkLabelData.FrameLabelData.FrameLabelDataService;
import maven.data.MarkLabelData.ImageLabelData.ImageLabelDataImpl;
import maven.data.MarkLabelData.ImageLabelData.ImageLabelDataService;
import maven.data.RequestorData.RequestorDataImpl;
import maven.data.RequestorData.RequestorDataService;
import maven.data.RequestorData.RequestorMassTaskDataImpl;
import maven.data.RequestorData.RequestorMassTaskDataService;
import maven.data.WorkerData.WorkerDataImpl;
import maven.data.WorkerData.WorkerDataService;
import maven.model.label.ImageLabel;
import maven.model.label.areaLabel.AreaLabel;
import maven.model.label.frameLabel.FrameLabel;
import maven.model.primitiveType.LabelScore;
import maven.model.primitiveType.LabelType;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.PublishedTask;
import maven.model.task.Sample;

import java.util.ArrayList;
import java.util.List;

public class EvaluateLabelBLImpl implements EvaluateLabelBLService {
    private RequestorMassTaskDataService requestorMassTaskDataService;
    private RequestorDataService requestorDataService;
    private WorkerDataService workerDataService;
    private ImageLabelDataService imageLabelDataService;
    private FrameLabelDataService frameLabelDataService;
    private AreaLabelDataService areaLabelDataService;
    private ImageLabelStatistics imageLabelStatistics;
    private FrameLabelStatistics frameLabelStatistics;
    private AreaLabelStatistics areaLabelStatistics;

    public EvaluateLabelBLImpl(){
        requestorMassTaskDataService = new RequestorMassTaskDataImpl();
        requestorDataService = new RequestorDataImpl();
        workerDataService = new WorkerDataImpl();

        imageLabelDataService = new ImageLabelDataImpl();
        frameLabelDataService = new FrameLabelDataImpl();
        areaLabelDataService = new AreaLabelDataImpl();

        imageLabelStatistics = new ImageLabelStatistics();
        frameLabelStatistics = new FrameLabelStatistics();
        areaLabelStatistics = new AreaLabelStatistics();
    }

    @Override
    public boolean evaluateLabel(TaskId taskId, UserId userId) {
        PublishedTask publishedTask = requestorDataService.getPublishedTask(taskId);
        LabelType labelType = publishedTask.getLabelType();
        Sample sample = requestorDataService.getSample(taskId);

        List<Integer> imageIndexList = sample.getImageIndexList();

        String[] temp = taskId.value.split("_");
        UserId requestorId = new UserId(temp[0]);

        double result = 0.8 + Math.random()*0.2;

        //如果为普通任务
        if(!requestorMassTaskDataService.isMassTask(taskId)) {
            switch(labelType.value){
                case "ImageLabel":
                        List<ImageLabel> sampleImageLabelList = imageLabelDataService.getLabelList(requestorId, taskId);
                        List<ImageLabel> imageLabelList = imageLabelDataService.getLabelList(userId, taskId);
                        List<ImageLabel> targetImageLabelList = new ArrayList<>();
                        for (int i : imageIndexList) {
                            targetImageLabelList.add(imageLabelList.get(i));
                        }
                        result = imageLabelStatistics.accuracyOfTask(targetImageLabelList, sampleImageLabelList);
                    break;
                case "FrameLabel":
                    List<FrameLabel> sampleFrameLabelList = frameLabelDataService.getLabelList(requestorId, taskId);
                    List<FrameLabel> frameLabelList = frameLabelDataService.getLabelList(userId, taskId);
                    List<FrameLabel> targetFrameLabelList = new ArrayList<>();
                    for(int i : imageIndexList){
                        targetFrameLabelList.add(frameLabelList.get(i));
                    }
                    result = frameLabelStatistics.accuracyOfTask(targetFrameLabelList, sampleFrameLabelList);
                    break;
                case "AreaLabel":
                    List<AreaLabel> sampleAreaLabelList = areaLabelDataService.getLabelList(requestorId, taskId);
                    List<AreaLabel> areaLabelList = areaLabelDataService.getLabelList(userId, taskId);
                    List<AreaLabel> targetAreaLabelList = new ArrayList<>();
                    for(int i : imageIndexList){
                        targetAreaLabelList.add(areaLabelList.get(i));
                    }
                    result = areaLabelStatistics.accuracyOfTask(targetAreaLabelList, sampleAreaLabelList);
                    break;
            }
        }
        return workerDataService.saveLabelScore(userId, taskId, new LabelScore(result));
    }
}
