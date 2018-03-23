package com.example.maven.service;

public interface DataService {
    //存IMG
    public  boolean  storeImg();

    //取所有的IMG
    public  boolean  getAllImg();

    //整体标记
    public boolean  MarkImg();

    //框标记
    public boolean MarkImgByRectangle();


    //区域标记
    public boolean MarkImgByLine();


}
