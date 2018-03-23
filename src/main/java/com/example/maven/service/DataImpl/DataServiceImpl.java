package com.example.maven.service.Impl;

import java.io.*;

public class DataServiceImpl implements com.example.maven.service.DataService {
    //存IMG
    public  boolean  storeImg(){

        return true;
    }


    //取所有的IMG
    public  boolean  getAllImg(){

        return true;
    }


    //整体标记
    public boolean  MarkImg(){

        return true;
    }

    //框标记
    public boolean MarkImgByRectangle(){

        return true;
    }


    //区域标记
    public boolean MarkImgByLine(){

        return true;
    }




    public boolean writeFile(String str){
        /**

        **/
        File file = new File("");
        System.out.println("test");
        BufferedWriter writer=null;
        try {
            writer=new BufferedWriter(new FileWriter(file));

            //writer.write();
            //writer.flush();
            //writer.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }



    public String readFile(String name,String numbering) {
        try{
            File myFile=new File("客户信息"+File.separator+name+"_"+numbering+"_"+"ClientInfo.txt");

            FileReader fileReader=new FileReader(myFile);
            BufferedReader reader=new BufferedReader(fileReader);
            String line=reader.readLine();

            reader.close();

            return line;
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return "ERROR";
    }

}
