package maven.businessLogic.allocateMassTaskBL;

public interface AllocateMassTaskBLService {
    /**
     * 将已过截止日期的大任务分配给工人
     * @param allocateTaskDate 分配时间点
     * @return 是否分配成功
     */
    Exception allocateMassTask(long allocateTaskDate);
}
