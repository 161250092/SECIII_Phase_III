package com.example.maven.data.fileHelper;

import java.io.*;

public class FileHelper {

    public  boolean createdFile(String filePath) {
        boolean flag = false;
        File newF = new File(filePath);

        if (!newF.exists()) {
            try {
                newF.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
            flag = true;
        }

        return flag;
    }

    public  String readTxtFile(String filepath) {
        String result = "";
        String thisLine = null;
        File file = new File(filepath);
        if (file.exists() && file.isFile()) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                while ((thisLine = br.readLine()) != null) {
                    result += thisLine + "\n";
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public boolean writeTxtFile(String content,String filePath,boolean append){
        boolean flag = false;
        File thisFile = new File(filePath);
        try{
            if(!thisFile.exists()){
                thisFile.createNewFile();
            }
            FileWriter fw = new FileWriter(filePath,append);
            fw.write(content+"\n");
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        flag= true;

        return flag;
    }


    public String[] readFileList(String filePath) {

        String result[];
        String r[] ={"error"};
        File file=new File(filePath);
        File[] list=file.listFiles();
        result = new String[list.length];

        try{
            for(int i=0;i<list.length;i++){
                result[i]=list[i].getName();
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return r;
        }
    }



}
