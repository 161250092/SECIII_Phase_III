package com.example.maven.businessLogic.markLabel.imageBL;

public interface ImageBLService {
    /**
     * 通过图片ID获得图片
     * @param imageId 图片ID
     * @return 图片Json
     */
    String getImageById(String imageId);

    /**
     * 通过当前图片的ID获得前一张图片
     * @param currentImageId 当前图片的ID
     * @return 前一张图片Json
     */
    String getPreviousImageId(String currentImageId);

    /**
     * 通过当前图片的ID获得后一张图片
     * @param currentImageId 当前图片的ID
     * @return 后一张图片Json
     */
    String getNextImageId(String currentImageId);
}
