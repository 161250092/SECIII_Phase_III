package com.example.maven.data.areaLabelData;

import com.example.maven.model.AreaLabel;

import java.util.ArrayList;

public interface AreaLabelDataService {

    //保存一个标签
    public boolean saveAreaLabel(AreaLabel label);

    //读取所有的标签
    public ArrayList<AreaLabel> getAllAreaLabel();

    //获取一张图片的标签
    public AreaLabel getAreaLabelByImageId(String ImageId);

}
