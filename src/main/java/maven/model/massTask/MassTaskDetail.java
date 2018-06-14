package maven.model.massTask;

import maven.model.massTask.MassTaskPricingMechanism;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.TaskId;

import java.util.Date;

public class MassTaskDetail {
    //任务Id
    private TaskId taskId;
    //发布者给出的单价
    private Cash givenUnitPrice;
    //总的预算
    private Cash budget;
    //大任务分配的选择机制
    private MassTaskPricingMechanism massTaskPricingMechanism;
    //任务发布时间
    private Date startTime;
    //任务截止时间
    private Date endTime;


    //最大化 -- 无单价
    public MassTaskDetail(TaskId taskId, Cash budget, Date startTime, Date endTime) {
        this.taskId = taskId;
        this.givenUnitPrice = new Cash(0);
        this.budget = budget;
        this.massTaskPricingMechanism = MassTaskPricingMechanism.MAXIMIZE_TASKS;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //最小支出 -- 有单价
    public MassTaskDetail(TaskId taskId, Cash givenUnitPrice, Cash budget, Date startTime, Date endTime){
        this.taskId = taskId;
        this.givenUnitPrice = givenUnitPrice;
        this.budget = budget;
        this.massTaskPricingMechanism = MassTaskPricingMechanism.MINIMIZE_PAYMENTS;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
