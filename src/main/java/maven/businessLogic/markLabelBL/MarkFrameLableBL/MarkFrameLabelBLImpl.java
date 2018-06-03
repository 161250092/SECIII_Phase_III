package maven.businessLogic.markLabelBL.MarkFrameLableBL;

import maven.data.MarkLabelData.FrameLabelData.FrameLabelDataImpl;
import maven.data.MarkLabelData.FrameLabelData.FrameLabelDataService;
import maven.data.RequestorData.RequestorDataImpl;
import maven.data.RequestorData.RequestorDataService;
import maven.model.label.frameLabel.FrameLabel;
import maven.model.primitiveType.Filename;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.PublishedTask;
import maven.model.vo.FrameLabelSetVO;

import java.util.ArrayList;
import java.util.List;

public class MarkFrameLabelBLImpl implements MarkFrameLabelBLService {
    private FrameLabelDataService frameLabelDataService;
    private RequestorDataService requestorDataService;

    public MarkFrameLabelBLImpl(){
        frameLabelDataService = new FrameLabelDataImpl();
        requestorDataService = new RequestorDataImpl();
    }

    @Override
    public FrameLabelSetVO getFrameLabelSetVO(TaskId taskId, UserId userId) {
        List<FrameLabel> labelList = frameLabelDataService.getLabelList(userId, taskId);
        PublishedTask publishedTask = requestorDataService.getPublishedTask(taskId);
        List<Filename> imageFilenameList = publishedTask.getImageFilenameList();
        List<String> filenameList = new ArrayList<>();
        for(Filename filename : imageFilenameList){
            filenameList.add(filename.value);
        }
        FrameLabelSetVO vo = new FrameLabelSetVO(filenameList.size(), labelList, filenameList);
        return vo;
    }

    @Override
    public boolean saveFrameLabelSet(TaskId taskId, UserId userId, FrameLabelSetVO frameLabelSetVO, boolean isWorker) {
        List<FrameLabel> labelList = frameLabelSetVO.getLabelList();
        if(frameLabelDataService.saveLabelList(userId, taskId, labelList))
            return true;
        return false;
    }
}
