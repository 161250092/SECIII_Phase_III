package com.example.maven.data.fileHelper;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {

    public  boolean createDirectory(String filePath) {
        boolean flag = false;

        File newF = new File(filePath);

        if (!newF.exists()) {
            try {

                Files.createDirectory(Paths.get(filePath));
                flag = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return flag;
    }


    public boolean createFile(String filePath){
        boolean result = false;

        File newF = new File(filePath);

        if(!newF.exists()){
            try{
                newF.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        return result;
    }

    public boolean writeFile(String filePath,String content){
        boolean flag = false;

        FileWriter fw = null;
        BufferedWriter bw = null;

        try{

            File file = new File(filePath);

            if(file.isFile())
                file.delete();

            file.createNewFile();

            fw = new FileWriter(filePath);
            bw = new BufferedWriter(fw);

            bw.write(content);

            bw.close();
            fw.close();

            flag = true;
        }catch(IOException e){
            e.printStackTrace();
        }

        return flag;
    }

    public String readFile(String filePath){
        String result = "";

        FileReader fr = null;
        BufferedReader br = null;

        try{

            fr = new FileReader(filePath);
            br = new BufferedReader(fr);

            result = br.readLine();

            br.close();
            fr.close();

        }catch(IOException e){
            e.printStackTrace();
        }

        return result;
    }



}
