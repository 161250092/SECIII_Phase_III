package maven.businessLogic.markLabelBL.MarkImageLableBL;

import maven.data.MarkLabelData.ImageLabelData.ImageLabelDataImpl;
import maven.data.MarkLabelData.ImageLabelData.ImageLabelDataService;
import maven.data.RequestorData.RequestorDataImpl;
import maven.data.RequestorData.RequestorDataService;
import maven.model.label.ImageLabel;
import maven.model.primitiveType.Filename;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.PublishedTask;
import maven.model.vo.ImageLabelSetVO;

import java.util.ArrayList;
import java.util.List;

public class MarkImageLabelBLImpl implements MarkImageLabelBLService{

    private ImageLabelDataService imageLabelDataService;
    private RequestorDataService requestorDataService;

    public MarkImageLabelBLImpl(){
        imageLabelDataService = new ImageLabelDataImpl();
        requestorDataService = new RequestorDataImpl();
    }

    @Override
    public ImageLabelSetVO getImageLabelSetVO(TaskId taskId, UserId userId) {
        List<ImageLabel> labelList = imageLabelDataService.getLabelList(userId, taskId);
        PublishedTask publishedTask = requestorDataService.getPublishedTask(taskId);
        List<Filename> imageFilenameList = publishedTask.getImageFilenameList();
        List<String> filenameList = new ArrayList<>();
        for(Filename filename : imageFilenameList){
            filenameList.add(filename.value);
        }
        return new ImageLabelSetVO(filenameList.size(), labelList, filenameList);
    }

    @Override
    public boolean saveImageLabelSet(TaskId taskId, UserId userId, ImageLabelSetVO imageLabelSetVO) {
        List<ImageLabel> labelList = imageLabelSetVO.getLabelList();
        return imageLabelDataService.saveLabelList(userId, taskId, labelList);
    }
}
