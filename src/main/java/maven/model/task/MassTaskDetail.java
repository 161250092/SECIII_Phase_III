package maven.model.task;

import maven.model.massTask.MassTaskPricingMechanism;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.TaskId;

public class MassTaskDetail {
    //任务Id
    private TaskId taskId;
    //发布者给出的单价
    private Cash givenUnitPrice;
    //总的预算
    private Cash budget;
    //大任务分配的选择机制
    private MassTaskPricingMechanism massTaskPricingMechanism;

    //最大化 -- 无单价
    public MassTaskDetail(TaskId taskId, Cash budget) {
        this.taskId = taskId;
        this.givenUnitPrice = new Cash(0);
        this.budget = budget;
        this.massTaskPricingMechanism = MassTaskPricingMechanism.MAXIMIZE_TASKS;
    }

    //最小支出 -- 有单价
    public MassTaskDetail(TaskId taskId, Cash givenUnitPrice, Cash budget){
        this.taskId = taskId;
        this.givenUnitPrice = givenUnitPrice;
        this.budget = budget;
        this.massTaskPricingMechanism = MassTaskPricingMechanism.MINIMIZE_PAYMENTS;
    }
}
