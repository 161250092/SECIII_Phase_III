package maven.runner;

import java.io.File;

public class FileSystemInitializer {

    public void initFileSystem(){
        boolean isExist = false;
        //任务图片根文件夹路径
        String taskImageDirectoryPath = "./taskImage";

        File taskImageDirFile = new File(taskImageDirectoryPath);

        //判断任务图片文件夹是否存在
        isExist = taskImageDirFile.exists();
        if(!isExist){
            System.out.println("The taskImage folder does not exist, now we are trying to create a one...");

            if(!taskImageDirFile.mkdir()){
                System.out.println("Create folder Failed!");
            } else{
                System.out.println("Create Success!");
            }
        }
        else{
            System.out.println("The taskImage folder exists.");
        }

    }
}
