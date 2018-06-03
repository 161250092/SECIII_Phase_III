package maven.businessLogic.markLabelBL.MarkAreaLableBL;

import maven.data.MarkLabelData.AreaLabelData.AreaLabelDataImpl;
import maven.data.MarkLabelData.AreaLabelData.AreaLabelDataService;
import maven.data.RequestorData.RequestorDataImpl;
import maven.data.RequestorData.RequestorDataService;
import maven.model.label.areaLabel.AreaLabel;
import maven.model.primitiveType.Filename;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.PublishedTask;
import maven.model.vo.AreaLabelSetVO;

import java.util.ArrayList;
import java.util.List;

public class MarkAreaLabelBLImpl implements MarkAreaLabelBLService {
    private AreaLabelDataService areaLabelDataService;
    private RequestorDataService requestorDataService;

    public MarkAreaLabelBLImpl(){
        areaLabelDataService = new AreaLabelDataImpl();
        requestorDataService = new RequestorDataImpl();
    }

    @Override
    public AreaLabelSetVO getAreaLabelSetVO(TaskId taskId, UserId userId) {
        List<AreaLabel> labelList = areaLabelDataService.getLabelList(userId, taskId);
        PublishedTask publishedTask = requestorDataService.getPublishedTask(taskId);
        List<Filename> imageFilenameList = publishedTask.getImageFilenameList();
        List<String> filenameList = new ArrayList<>();
        for(Filename filename : imageFilenameList){
            filenameList.add(filename.value);
        }
        AreaLabelSetVO vo = new AreaLabelSetVO(filenameList.size(), labelList, filenameList);
        return vo;
    }

    @Override
    public boolean saveAreaLabelSet(TaskId taskId, UserId userId, AreaLabelSetVO areaLabelSetVO, boolean isWorker) {
        List<AreaLabel> labelList = areaLabelSetVO.getLabelList();
        if(areaLabelDataService.saveLabelList(userId, taskId, labelList))
            return true;
        return false;
    }
}
