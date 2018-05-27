package maven.data.fileHelper;

import java.io.File;

public class FileSystemInitializer {
    private String directoryPath = null;
    private FileHelper fileHelper = null;

    public FileSystemInitializer(){
        fileHelper = new FileHelper();
        directoryPath = fileHelper.getDirectoryPath();
    }

    public void initFileSystem(){
        System.out.println("Hello "+directoryPath);
        boolean isExist = false;
        //用户根文件夹
        String userDirectoryPath = directoryPath + "/user";
        //任务图片根文件夹
        String taskImageDirectoryPath = directoryPath + "/taskImage";

        File userDirFile = new File(userDirectoryPath);
        File taskImageDirFile = new File(taskImageDirectoryPath);

        //判断用户文件夹是否存在
        isExist = userDirFile.exists();
        if(!isExist){
            System.out.println("The user folder does not exist, now we are trying to create a one...");

            if(!userDirFile.mkdir()){
                System.out.println("Create folder Failed!");
            } else{
                System.out.println("Create Success!");
                initUserInfo();
            }
        }
        else{
            System.out.println("The user folder exists.");
        }

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

    public void initUserInfo(){
        String userId = "test0000";
        String userPath = directoryPath + "/user" + "/" + userId;

        //创建用户文件夹
        fileHelper.createDirectory(userPath);

        //创建用户数据txt文件
        String userInfoPath = userPath + "/" + userId + ".txt";

        //创建任务文件夹
        String acceptedTask = userPath + "/AcceptedTask";
        String AcceptedAccomplishedTaskPath = acceptedTask + "/AccomplishedTask";
        String AcceptedIncompleteTaskPath = acceptedTask + "/IncompleteTask";

        String publishedTask = userPath + "/PublishedTask";
        String publishedAccomplishedTaskPath = publishedTask + "/AccomplishedTask";
        String publishedIncompleteTask = publishedTask + "/IncompleteTask";


        fileHelper.createDirectory(acceptedTask);
        fileHelper.createDirectory(AcceptedAccomplishedTaskPath);
        fileHelper.createDirectory(AcceptedIncompleteTaskPath);

        fileHelper.createDirectory(publishedTask);
        fileHelper.createDirectory(publishedAccomplishedTaskPath);
        fileHelper.createDirectory(publishedIncompleteTask);

        //保证文件夹正常创建
        fileHelper.createFile(AcceptedAccomplishedTaskPath + "/null.txt");
        fileHelper.createFile(AcceptedIncompleteTaskPath + "/null.txt");

        fileHelper.createFile(publishedAccomplishedTaskPath + "/null.txt");
        fileHelper.createFile(publishedIncompleteTask + "/null.txt");

        //写用户数据
        //文件内容：用户ID，用户名，密码，积分，是否为管理员
        String userInformation = userId + "," +  "admin" + "," + "admin" + ",0,true\r\n";

        fileHelper.writeFile(userInfoPath ,userInformation);
    }
}
