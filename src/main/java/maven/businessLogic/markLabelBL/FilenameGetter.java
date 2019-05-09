package maven.businessLogic.markLabelBL;

import maven.data.RequestorData.RequestorDataImpl;
import maven.data.RequestorData.RequestorDataService;
import maven.data.RequestorData.RequestorMassTaskDataImpl;
import maven.data.RequestorData.RequestorMassTaskDataService;
import maven.data.WorkerData.WorkerMassTaskDataImpl;
import maven.data.WorkerData.WorkerMassTaskDataService;
import maven.model.massTask.WorkerBid;
import maven.model.primitiveType.Filename;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.PublishedTask;
import maven.model.task.Sample;

import java.util.ArrayList;
import java.util.List;

public class FilenameGetter {
    private RequestorDataService requestorDataService;
    private RequestorMassTaskDataService requestorMassTaskDataService;
    private WorkerMassTaskDataService workerMassTaskDataService;

    public FilenameGetter(){
        requestorDataService = new RequestorDataImpl();
        requestorMassTaskDataService = new RequestorMassTaskDataImpl();
        workerMassTaskDataService = new WorkerMassTaskDataImpl();
    }

    //获取欲标注的任务中图片名称数组
    public List<String> getFilenameList(TaskId taskId, UserId userId){
        //欲返回的图片名称数组
        List<String> filenameList = new ArrayList<>();

        //获取发布者Id
        UserId requestorId = new UserId(taskId.value.split("_")[0]);

        //获取发布任务
        PublishedTask publishedTask = requestorDataService.getPublishedTask(taskId);
        //获取该发布任务的图片名称数组
        List<Filename> imageFilenameList = publishedTask.getImageFilenameList();

        //若是工人做标注
        if(!userId.value.equals(requestorId.value)){
            //假如该任务是大任务
            if(requestorMassTaskDataService.isMassTask(taskId)){

                //获取该工人的竞标
                WorkerBid workerBid = workerMassTaskDataService.getWorkerBidOfThisTask(taskId, userId);

                int index;
                int publishTaskFileListLength = imageFilenameList.size();
                for(int i = workerBid.getFileListStartIndex(); i < workerBid.getFileListStartIndex()+ workerBid.getFileListLength(); i++){
                    index = (i % publishTaskFileListLength);
                    filenameList.add(imageFilenameList.get(index).value);
                }

            }else{
                for(Filename filename : imageFilenameList){
                    filenameList.add(filename.value);
                }
            }

        }
        //若是发布者标注样本
        else{
            //获取样本
            Sample sample = requestorDataService.getSample(taskId);
            List<Integer> imageIndexList = sample.getImageIndexList();
            for(int i : imageIndexList){
                filenameList.add(imageFilenameList.get(i).value);
            }
        }

        return filenameList;
    }
}
