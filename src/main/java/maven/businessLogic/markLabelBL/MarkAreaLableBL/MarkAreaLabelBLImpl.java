package maven.businessLogic.markLabelBL.MarkAreaLableBL;

import maven.businessLogic.markLabelBL.FilenameGetter;
import maven.data.MarkLabelData.AreaLabelData.AreaLabelDataImpl;
import maven.data.MarkLabelData.AreaLabelData.AreaLabelDataService;
import maven.model.label.areaLabel.AreaLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.AreaLabelSetVO;

import java.util.List;

public class MarkAreaLabelBLImpl implements MarkAreaLabelBLService {
    private AreaLabelDataService areaLabelDataService;
    private FilenameGetter filenameGetter;

    public MarkAreaLabelBLImpl(){
        areaLabelDataService = new AreaLabelDataImpl();
        filenameGetter = new FilenameGetter();
    }

    @Override
    public AreaLabelSetVO getAreaLabelSetVO(TaskId taskId, UserId userId) {
        //欲返回的图片名称数组
        List<String> filenameList = filenameGetter.getFilenameList(taskId, userId);
        //欲返回的标注信息数组
        List<AreaLabel> labelList = areaLabelDataService.getLabelList(userId, taskId);

        return new AreaLabelSetVO(filenameList.size(), labelList, filenameList);
    }

    @Override
    public boolean saveAreaLabelSet(TaskId taskId, UserId userId, AreaLabelSetVO areaLabelSetVO) {
        //清空用户曾经的标注
        areaLabelDataService.deleteLabel(userId, taskId);
        //保存标注
        List<AreaLabel> labelList = areaLabelSetVO.getLabelList();
        return areaLabelDataService.saveLabelList(userId, taskId, labelList);
    }
}
