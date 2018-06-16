package maven.model.vo;

import maven.model.massTask.MassTaskDetail;

public class PublishedMassTaskVO {
    private PublishedTaskVO publishedTaskVO;
    private MassTaskDetailVO massTaskDetailVO;

    public PublishedTaskVO getPublishedTaskVO() {
        return publishedTaskVO;
    }

    public MassTaskDetailVO getMassTaskDetailVO() {
        return massTaskDetailVO;
    }

    public PublishedMassTaskVO(PublishedTaskVO publishedTaskVO, MassTaskDetailVO massTaskDetailVO) {
        this.publishedTaskVO = publishedTaskVO;
        this.massTaskDetailVO = massTaskDetailVO;
    }
}
