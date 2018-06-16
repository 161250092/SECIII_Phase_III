package maven.model.vo;

import maven.model.massTask.MassTaskDetail;
import maven.model.massTask.MassTaskPricingMechanism;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.TaskId;

public class MassTaskDetailVO {
    //任务Id
    private String  taskId;
    //发布者给出的单价
    private double givenUnitPrice;
    //总的预算
    private double budget;
    //大任务分配的选择机制
    private String massTaskPricingMechanism;
    //任务发布时间
    private long startTime;
    //任务截止时间
    private long endTime;

    public MassTaskDetailVO(MassTaskDetail massTaskDetail) {
        this.taskId = massTaskDetail.getTaskId().value;
        this.givenUnitPrice = massTaskDetail.getGivenUnitPrice().value;
        this.budget = massTaskDetail.getBudget().value;
        this.massTaskPricingMechanism = massTaskDetail.getMassTaskPricingMechanism().name();
        this.startTime = massTaskDetail.getStartTime();
        this.endTime = massTaskDetail.getEndTime();
    }

    public String getTaskId() {
        return taskId;
    }

    public double getGivenUnitPrice() {
        return givenUnitPrice;
    }

    public double getBudget() {
        return budget;
    }

    public String getMassTaskPricingMechanism() {
        return massTaskPricingMechanism;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
