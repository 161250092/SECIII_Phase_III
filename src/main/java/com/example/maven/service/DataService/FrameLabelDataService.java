package com.example.maven.service.DataService;

import com.example.maven.model.frameLabel.FrameLabel;

import java.util.ArrayList;

/**
 * 标框标注模块数据层接口
 */
public interface FrameLabelDataService {
    /**
     * 保存标框的数据
     * @param frameLabel 标框的数据
     * @return 是否成功
     */
    public boolean saveFrameLabel(FrameLabel frameLabel);

    /**
     * 通过用户Id获得标框的数据
     * @param userId 用户Id
     * @return 标框的数据
     */
    public FrameLabel getFrameLabelByUserId(String userId);

    /**
     * 通过用户Id获得标框的数据
     * @param imageId 图片Id
     * @return 标框的数据
     */
    public FrameLabel getFrameLabelByImageId(String imageId);
}
