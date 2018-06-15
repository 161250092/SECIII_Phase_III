package maven.model.vo;

import maven.model.massTask.MassTaskDetail;

public class PublishedMassTaskVO {
    private PublishedTaskVO publishedTaskVO;
    private MassTaskDetail massTaskDetail;

    public PublishedTaskVO getPublishedTaskVO() {
        return publishedTaskVO;
    }

    public MassTaskDetail getMassTaskDetail() {
        return massTaskDetail;
    }

    public PublishedMassTaskVO(PublishedTaskVO publishedTaskVO, MassTaskDetail massTaskDetail) {
        this.publishedTaskVO = publishedTaskVO;
        this.massTaskDetail = massTaskDetail;
    }
}
