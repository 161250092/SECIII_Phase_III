package maven.businessLogic.adminBL;

import maven.data.AdminData.AdminDataImpl;
import maven.data.AdminData.AdminDataService;
import maven.data.MessageData.MessageDataImpl;
import maven.data.MessageData.MessageDataService;
import maven.data.UserData.UserDataImpl;
import maven.data.UserData.UserDataService;
import maven.model.message.BillMessage;
import maven.model.message.BillReason;
import maven.model.statistics.WebsiteStatistics;
import maven.model.task.PublishedTask;
import maven.model.task.PublishedTaskState;
import maven.model.user.Requestor;
import maven.model.user.Worker;

import java.util.List;

public class AdminBLImpl implements AdminBLService {

    private AdminDataService adminDataService;
    private UserDataService userDataService;
    private MessageDataService messageDataService;

    public AdminBLImpl(){
        adminDataService = new AdminDataImpl();
        userDataService = new UserDataImpl();
        messageDataService = new MessageDataImpl();
    }

    @Override
    public WebsiteStatistics getWebsiteStatistics(){
        int numOfRequestors = 0;
        int numOfWorkers = 0;
        int numOfTasks = 0;
        int numOfIncompleteTasks = 0;
        int numOfAccomplishedTasks = 0;
        double chargedCash = 0;
        double exchangedCash = 0;

        List<Requestor> requestorList = userDataService.getAllRequestor();
        List<Worker> workerList = userDataService.getAllWorker();
        List<PublishedTask> publishedTaskList = adminDataService.getAllPublishedTask();
        List<BillMessage> billMessageList = messageDataService.getAllBillMessage();

        numOfRequestors = requestorList.size();
        numOfWorkers = workerList.size();
        numOfTasks = publishedTaskList.size();

        PublishedTaskState publishedTaskState;
        for(PublishedTask publishedTask : publishedTaskList){
            publishedTaskState = publishedTask.getPublishedTaskState();
            if(publishedTaskState == PublishedTaskState.INCOMPLETE)
                numOfIncompleteTasks++;
            else if(publishedTaskState == PublishedTaskState.ACCOMPLISHED)
                numOfAccomplishedTasks++;
        }
        numOfTasks = numOfIncompleteTasks + numOfAccomplishedTasks;

        BillReason billReason;
        for(BillMessage billMessage : billMessageList){
            billReason = billMessage.getBillReason();
            if(billReason == BillReason.CHARGE)
                chargedCash += billMessage.getCash().value;
            else if(billReason == BillReason.EXCHANGE)
                exchangedCash += billMessage.getCash().value;
        }

        return new WebsiteStatistics(numOfRequestors, numOfWorkers, numOfTasks, numOfIncompleteTasks, numOfAccomplishedTasks,
                chargedCash, exchangedCash);
    }
}
