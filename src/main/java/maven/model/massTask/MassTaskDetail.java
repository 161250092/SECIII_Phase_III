package maven.model.massTask;

import maven.model.primitiveType.Cash;
import maven.model.primitiveType.TaskId;
import maven.model.vo.MassTaskDetailVO;

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
    private long startTime;
    //任务截止时间
    private long endTime;

    public TaskId getTaskId() {
        return taskId;
    }

    public Cash getGivenUnitPrice() {
        return givenUnitPrice;
    }

    public Cash getBudget() {
        return budget;
    }

    public MassTaskPricingMechanism getMassTaskPricingMechanism() {
        return massTaskPricingMechanism;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    //最大化 -- 无单价
    public MassTaskDetail(TaskId taskId, Cash budget, long startTime, long endTime) {
        this.taskId = taskId;
        this.givenUnitPrice = new Cash(0);
        this.budget = budget;
        this.massTaskPricingMechanism = MassTaskPricingMechanism.MAXIMIZE_TASKS;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //最小支出 -- 有单价
    public MassTaskDetail(TaskId taskId, Cash givenUnitPrice, Cash budget, long startTime, long endTime){
        this.taskId = taskId;
        this.givenUnitPrice = givenUnitPrice;
        this.budget = budget;
        this.massTaskPricingMechanism = MassTaskPricingMechanism.MINIMIZE_PAYMENTS;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public MassTaskDetail(MassTaskDetailVO massTaskDetailVO){
        this.taskId = new TaskId(massTaskDetailVO.getTaskId());
        this.budget = new Cash(massTaskDetailVO.getBudget());
        this.massTaskPricingMechanism = MassTaskPricingMechanism.valueOf(massTaskDetailVO.getMassTaskPricingMechanism());
        this.startTime = massTaskDetailVO.getStartTime();
        this.endTime = massTaskDetailVO.getEndTime();
        if (massTaskDetailVO.getMassTaskPricingMechanism().equals(MassTaskPricingMechanism.MAXIMIZE_TASKS.name())){
            this.givenUnitPrice = new Cash(0);
        }else if (massTaskDetailVO.getMassTaskPricingMechanism().equals(MassTaskPricingMechanism.MINIMIZE_PAYMENTS.name())){
            this.givenUnitPrice = new Cash(massTaskDetailVO.getGivenUnitPrice());
        }
    }

    public void setGivenUnitPrice(Cash givenUnitPrice) {
        this.givenUnitPrice = givenUnitPrice;
    }
}
