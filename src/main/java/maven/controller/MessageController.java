package maven.controller;

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
        messageBLService = new MessageBLStub();
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
    @RequestMapping(value = "/message/confirmRequestorTaskMessage", method = RequestMethod.GET)
    public boolean confirmRequestorTaskMessage(String messageId){
        return messageBLService.confirmRequestorTaskMessage(new MessageId(messageId));
    }

    /**
     * 工人确认任务消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    @RequestMapping(value = "/message/confirmWorkerTaskMessage", method = RequestMethod.GET)
    public boolean confirmWorkerTaskMessage(String messageId){
        return messageBLService.confirmWorkerTaskMessage(new MessageId(messageId));
    }

    /**
     * 工人确认关注消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    @RequestMapping(value = "/message/confirmGuyMessage", method = RequestMethod.GET)
    public boolean confirmGuyMessage(String messageId){
        return messageBLService.confirmGuyMessage(new MessageId(messageId));
    }

    /**
     * 用户确认账单消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    @RequestMapping(value = "/message/confirmBillMessage", method = RequestMethod.GET)
    public boolean confirmBillMessage(String messageId){
        return messageBLService.confirmBillMessage(new MessageId(messageId));
    }

    /**
     * 用户确认成就消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    @RequestMapping(value = "/message/confirmAchievementMessage", method = RequestMethod.GET)
    public boolean confirmAchievementMessage(String messageId){
        return messageBLService.confirmAchievementMessage(new MessageId(messageId));
    }
}
