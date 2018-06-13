package maven.controller;

import maven.businessLogic.messageBL.MessageBLImpl;
import maven.businessLogic.messageBL.MessageBLService;
import maven.businessLogic.messageBL.MessageBLStub;
import maven.model.message.RequestorMessage;
import maven.model.message.WorkerMessage;
import maven.model.primitiveType.MessageId;
import maven.model.primitiveType.UserId;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class MessageController {

    private MessageBLService messageBLService;

    public MessageController(){
        messageBLService = new MessageBLImpl();
    }

    /**
     * 获取所有发布者未确认的消息
     * @param userId 发布者Id
     * @return 发布者消息
     */
    @RequestMapping(value = "/message/getAllRequestorMessage", method = RequestMethod.GET)
    public RequestorMessage getAllRequestorMessage(String userId){
        return messageBLService.getAllRequestorMessage(new UserId(userId));
    }


    /**
     * 获取所有工人未确认的消息
     * @param userId 工人Id
     * @return 工人消息
     */
    @RequestMapping(value = "/message/getAllWorkerMessage", method = RequestMethod.GET)
    public WorkerMessage getAllWorkerMessage(String userId){
        return messageBLService.getAllWorkerMessage(new UserId(userId));
    }

    /**
     * 发布者确认任务消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    @RequestMapping(value = "/message/checkRequestorTaskMessage", method = RequestMethod.GET)
    public boolean checkRequestorTaskMessage(String messageId){
        return messageBLService.checkRequestorTaskMessage(new MessageId(messageId));
    }

    /**
     * 工人确认任务消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    @RequestMapping(value = "/message/checkWorkerTaskMessage", method = RequestMethod.GET)
    public boolean checkWorkerTaskMessage(String messageId){
        return messageBLService.checkWorkerTaskMessage(new MessageId(messageId));
    }

    /**
     * 工人确认关注消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    @RequestMapping(value = "/message/checkGuyMessage", method = RequestMethod.GET)
    public boolean checkGuyMessage(String messageId){
        return messageBLService.checkGuyMessage(new MessageId(messageId));
    }

    /**
     * 用户确认账单消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    @RequestMapping(value = "/message/checkBillMessage", method = RequestMethod.GET)
    public boolean checkBillMessage(String messageId){
        return messageBLService.checkBillMessage(new MessageId(messageId));
    }

    /**
     * 用户确认成就消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    @RequestMapping(value = "/message/checkAchievementMessage", method = RequestMethod.GET)
    public boolean checkAchievementMessage(String messageId){
        return messageBLService.checkAchievementMessage(new MessageId(messageId));
    }
}
