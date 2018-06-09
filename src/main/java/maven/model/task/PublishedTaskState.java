package maven.model.task;

public enum PublishedTaskState {
    //未标注样本的草稿，已标注样本的草稿，任务进行中，终止，完成
    DRAFT_WITHOUT_SAMPLE, DRAFT_WITH_SAMPLE, INCOMPLETE, TERMINATED, ACCOMPLISHED
}
