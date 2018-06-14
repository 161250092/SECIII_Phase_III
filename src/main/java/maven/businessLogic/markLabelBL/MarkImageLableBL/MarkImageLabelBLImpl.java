package maven.businessLogic.markLabelBL.MarkImageLableBL;

import maven.businessLogic.markLabelBL.FilenameGetter;
import maven.data.MarkLabelData.ImageLabelData.ImageLabelDataImpl;
import maven.data.MarkLabelData.ImageLabelData.ImageLabelDataService;
import maven.model.label.ImageLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.ImageLabelSetVO;

import java.util.List;

public class MarkImageLabelBLImpl implements MarkImageLabelBLService{

    private ImageLabelDataService imageLabelDataService;
    private FilenameGetter filenameGetter;

    public MarkImageLabelBLImpl(){
        imageLabelDataService = new ImageLabelDataImpl();
        filenameGetter = new FilenameGetter();
    }

    @Override
    public ImageLabelSetVO getImageLabelSetVO(TaskId taskId, UserId userId) {
        //欲返回的图片名称数组
        List<String> filenameList = filenameGetter.getFilenameList(taskId, userId);
        //欲返回的标注信息数组
        List<ImageLabel> labelList = imageLabelDataService.getLabelList(userId, taskId);
        return new ImageLabelSetVO(filenameList.size(), labelList, filenameList);
    }

    @Override
    public boolean saveImageLabelSet(TaskId taskId, UserId userId, ImageLabelSetVO imageLabelSetVO) {
        //清空工人曾经的标注
        imageLabelDataService.deleteLabel(userId, taskId);
        //保存标注
        List<ImageLabel> labelList = imageLabelSetVO.getLabelList();
        return imageLabelDataService.saveLabelList(userId, taskId, labelList);
    }
}
