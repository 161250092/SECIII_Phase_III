package maven.data.LabelQualityData;

import maven.model.task.AcceptedTaskQuality;

import java.util.List;

public interface LabelQualityDataService {
    /**
     * 获取所有的任务历史评价
     * 此处评价仅三种可能---通过，驳回，被发布者废弃
     * @return 历史评价列表
     */
    List<AcceptedTaskQuality> getAllAcceptedTaskQualityList();
}
