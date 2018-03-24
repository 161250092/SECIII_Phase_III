package com.example.maven.service.DataService;

import com.example.maven.model.ImageLabel;
import java.util.ArrayList;

public interface ImageLabelDataService {

    //保存一个标签
    public boolean saveImageLabel(ImageLabel label);

    //读取所有的标签
    public ArrayList<ImageLabel> getAllImageLabel();

    //获取一张图片的标签
    public ImageLabel getAreaLabelByImageId(String ImageId);
}
