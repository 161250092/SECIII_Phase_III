package maven.businessLogic.markLabelBL.MarkFrameLableBL;

import maven.businessLogic.markLabelBL.FilenameGetter;
import maven.data.MarkLabelData.FrameLabelData.FrameLabelDataImpl;
import maven.data.MarkLabelData.FrameLabelData.FrameLabelDataService;
import maven.model.label.frameLabel.FrameLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.FrameLabelSetVO;

import java.util.List;

public class MarkFrameLabelBLImpl implements MarkFrameLabelBLService {
    private FrameLabelDataService frameLabelDataService;
    private FilenameGetter filenameGetter;

    public MarkFrameLabelBLImpl(){
        frameLabelDataService = new FrameLabelDataImpl();
        filenameGetter = new FilenameGetter();
    }

    @Override
    public FrameLabelSetVO getFrameLabelSetVO(TaskId taskId, UserId userId) {
        //欲返回的图片名称数组
        List<String> filenameList = filenameGetter.getFilenameList(taskId, userId);
        //欲返回的标注信息数组
        List<FrameLabel> labelList = frameLabelDataService.getLabelList(userId, taskId);

        return new FrameLabelSetVO(filenameList.size(), labelList, filenameList);
    }

    @Override
    public boolean saveFrameLabelSet(TaskId taskId, UserId userId, FrameLabelSetVO frameLabelSetVO) {
        //清空工人曾经的标注
        frameLabelDataService.deleteLabel(userId, taskId);
        //保存标注
        List<FrameLabel> labelList = frameLabelSetVO.getLabelList();
        return frameLabelDataService.saveLabelList(userId, taskId, labelList);
    }
}
