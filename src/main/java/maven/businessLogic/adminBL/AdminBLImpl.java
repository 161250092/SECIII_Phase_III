package maven.businessLogic.adminBL;

import maven.data.AdminData.AdminDataImpl;
import maven.data.AdminData.AdminDataService;
import maven.data.MessageData.MessageDataImpl;
import maven.data.MessageData.MessageDataService;
import maven.data.UserData.UserDataImpl;
import maven.data.UserData.UserDataService;
import maven.data.WorkerData.WorkerDataImpl;
import maven.data.WorkerData.WorkerDataService;
import maven.model.message.BillMessage;
import maven.model.message.BillReason;
import maven.model.primitiveType.UserId;
import maven.model.statistics.WebsiteStatistics;
import maven.model.statistics.WebsiteTrafficStatics;
import maven.model.task.AcceptedTask;
import maven.model.task.AcceptedTaskState;
import maven.model.task.PublishedTask;
import maven.model.task.PublishedTaskState;
import maven.model.user.Requestor;
import maven.model.user.Worker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdminBLImpl implements AdminBLService {

    private AdminDataService adminDataService;
    private UserDataService userDataService;
    private WorkerDataService workerDataService;
    private MessageDataService messageDataService;

    public AdminBLImpl(){
        adminDataService = new AdminDataImpl();
        userDataService = new UserDataImpl();
        workerDataService = new WorkerDataImpl();
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
//        numOfTasks = publishedTaskList.size();

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

    @Override
    public WebsiteTrafficStatics getWebsiteTrafficStatics(Date startDate, Date endDate) {

        //发布者发布的任务数量 列表
        List<Integer> numOfPublishedTask = new ArrayList<>();
        //工人提交的完成任务数量 列表
        List<Integer> numOfSubmittedAcceptedTask = new ArrayList<>();
        //用户充值的总金额
        double chargedCash = 0;
        //用户兑换的总金额
        double exchangedCash = 0;

        int numOfDay = getDistanceFromDates(startDate, endDate);

        for(int i = 0; i < numOfDay; i++){
            numOfPublishedTask.add(0);
            numOfSubmittedAcceptedTask.add(0);
        }

        //日期错误！
        if(numOfDay <= 0)
            return null;

        List<Requestor> requestorList = userDataService.getAllRequestor();
        List<Worker> workerList = userDataService.getAllWorker();
        List<PublishedTask> publishedTaskList = adminDataService.getAllPublishedTask();
        List<BillMessage> billMessageList = messageDataService.getAllBillMessage();

        UserId userId;
        List<AcceptedTask> acceptedTaskList;
        Date tempDate;
        int tempNum;
        int interval;

        for(PublishedTask publishedTask : publishedTaskList){
            //排除为草稿状态的任务
            if(publishedTask.getPublishedTaskState() != PublishedTaskState.DRAFT_WITHOUT_SAMPLE
                    && publishedTask.getPublishedTaskState() != PublishedTaskState.DRAFT_WITH_SAMPLE){
                tempDate = publishedTask.getPublishedTaskDetailList().get(0).getStartTime();
                interval = getDistanceFromDates(startDate, tempDate);
                //判断任务发布日期是否 介于查询的时间区间
                if(interval>=0 && interval<numOfDay){
                    tempNum = numOfPublishedTask.get(interval);
                    numOfPublishedTask.set(interval, tempNum+1);
                }
            }
        }

        for(Worker worker : workerList){
            userId = worker.getUserId();
            acceptedTaskList = workerDataService.getAcceptedTaskListByUserId(userId);
            for(AcceptedTask acceptedTask : acceptedTaskList){
                //查询状态为提交的任务
                if(acceptedTask.getAcceptedTaskState() == AcceptedTaskState.SUBMITTED){
                    tempDate = acceptedTask.getStartTime();
                    interval = getDistanceFromDates(startDate, tempDate);
                    //判断任务修改日期是否 介于查询的时间区间
                    if(interval>=0 && interval<numOfDay){
                        tempNum = numOfSubmittedAcceptedTask.get(interval);
                        numOfSubmittedAcceptedTask.set(interval, tempNum+1);
                    }
                }
            }
        }

        for(BillMessage billMessage : billMessageList){
            tempDate = billMessage.getDate();
            interval = getDistanceFromDates(startDate, tempDate);
            //判断消息日期是否 介于查询的时间区间
            if(interval>=0 && interval<numOfDay){
                if(billMessage.getBillReason() == BillReason.CHARGE)
                    chargedCash += billMessage.getCash().value;
                else if(billMessage.getBillReason() == BillReason.EXCHANGE)
                    exchangedCash += billMessage.getCash().value;
            }
        }

        return new WebsiteTrafficStatics(numOfPublishedTask, numOfSubmittedAcceptedTask, chargedCash, exchangedCash);
    }

    //date2比date1多的天数
    private int getDistanceFromDates(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        //若不是同一年的
        if(year1 != year2){
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i++){
                //若为闰年
                if(i%4==0 && i%100!=0 || i%400==0)
                    timeDistance += 366;
                else
                    timeDistance += 365;

            }

            return timeDistance + (day2-day1) ;
        }
        //同一年
        else
            return day2-day1;
    }


}
