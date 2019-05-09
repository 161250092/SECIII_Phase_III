package maven.businessLogic.algorithm;

import maven.data.Map.MapDataImpl;
import maven.data.Map.MapDataService;
import maven.model.primitiveType.LabelQuality;
import maven.model.primitiveType.Prestige;
import maven.model.task.TaskType;

import java.util.Map;

public class PrestigeAlgorithm {

    private MapDataService mapData;

    public PrestigeAlgorithm(){
        mapData = new MapDataImpl();
    }

    /**
     * 更新威望
     * @param workerOldPrestige 用户当前的威望值 Rt-1,j ≥ θi
     * @param labelQuality 标注质量 Qt,j ∈ {TRUSTFUL, DISTRUSTFUL}
     * @param taskType 该任务的类别 φi ∈ {ORDINARY_LEVEL_LABEL_REQUIRED, HIGH_LEVEL_LABEL_REQUIRED, VERY_HIGH_LEVEL_LABEL_REQUIRED}
     * @return 用户新的威望值 Rt,j
     */
    public Prestige renewWorkerPrestige(Prestige workerOldPrestige, LabelQuality labelQuality, TaskType taskType){
        Map<TaskType, Prestige> taskTypePrestigeMap = mapData.getPrestigeTaskType();

        //Rmax
        Prestige maxPrestige = mapData.getMaxPrestige();
        //θ0
        Prestige minPrestigeThresholdOfAllType = taskTypePrestigeMap.get(TaskType.ORDINARY_LEVEL_LABEL_REQUIRED);
        //θi
        Prestige minPrestigeThresholdOfArgumentType = taskTypePrestigeMap.get(taskType);

        //Rt,j
        double newPrestige;

        //Qt,j = Trustful 且 Rt-1,j ≥ θi
        if(labelQuality == LabelQuality.TRUSTFUL){
            newPrestige = Math.min(workerOldPrestige.value + 1, maxPrestige.value);

        //Qt,j = Distrustful 且 Rt-1,j ≥ θi
        }else {
            //Qt,j = Distrustful 且 Rt－1,j ＞ θi
            if (workerOldPrestige.value > minPrestigeThresholdOfArgumentType.value){
                // Qt,j = Distrustful, Rt－1,j ＞ θi 且 θi ＞ θ0
                if (minPrestigeThresholdOfArgumentType.value > minPrestigeThresholdOfAllType.value){
                    newPrestige = minPrestigeThresholdOfArgumentType.value - 1;

                // Qt,j = Distrustful, Rt－1,j ＞ θi 且 θi = θ0（θi 不可能小于 θ0）
                }else {
                    newPrestige = minPrestigeThresholdOfAllType.value;
                }

            //Qt,j = Distrustful 且 Rt－1,j = θi
            }else {
                // Qt,j = Distrustful, Rt－1,j = θi 且 θi ＞ θ0
                if (minPrestigeThresholdOfArgumentType.value > minPrestigeThresholdOfAllType.value){
                    newPrestige = minPrestigeThresholdOfAllType.value;

                // Qt,j = Distrustful, Rt－1,j = θi 且 θi = θ0（θi 不可能小于 θ0）
                }else {
                    newPrestige = minPrestigeThresholdOfAllType.value - 1;
                }
            }
        }

        return new Prestige(newPrestige);
    }

    /**
     * 判断这个工人能否接受这个任务
     * @param workerCurrentPrestige 这个工人当前的威望值 Rt-1,j
     * @param taskType 这个任务的类型 φi ∈ {ORDINARY_LEVEL_LABEL_REQUIRED, HIGH_LEVEL_LABEL_REQUIRED, VERY_HIGH_LEVEL_LABEL_REQUIRED}
     * @return 这个工人能否接受这个任务
     */
    public boolean canWorkerGetThisTask(Prestige workerCurrentPrestige, TaskType taskType){
        Map<TaskType, Prestige> taskTypePrestigeMap = mapData.getPrestigeTaskType();

        //θi
        Prestige minPrestigeThresholdOfArgumentType = taskTypePrestigeMap.get(taskType);

        //Rt-1,j ≥ θi，则这个工人可以接这个任务
        return workerCurrentPrestige.value >= minPrestigeThresholdOfArgumentType.value;
    }
}
