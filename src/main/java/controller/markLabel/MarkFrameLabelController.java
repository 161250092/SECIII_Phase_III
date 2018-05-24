//package controller.markLabel;
//
////import businessLogic.markLabel.markFrameLabelBL.MarkFrameLabelBLImpl;
//import businessLogic.markLabel.markFrameLabelBL.MarkFrameLabelBLService;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class MarkFrameLabelController implements MarkFrameLabelBLService {
//
//    private MarkFrameLabelBLService markFrameLabelBL;
//
//    public MarkFrameLabelController(){
//        //STUB
//        //markFrameLabelBL = new MarkFrameLabelBLStub();
//    }
//
//    @Override
//    @RequestMapping(value = "/markFrameLabel/getTaskImageNumber", method = RequestMethod.GET)
//    public int getTaskImageNumber(String taskId) {
//        return markFrameLabelBL.getTaskImageNumber(taskId);
//    }
//
//    @Override
//    @RequestMapping(value = "/markFrameLabel/getFrameLabel", method = RequestMethod.GET)
//    public String getFrameLabel(int imageIndex, String taskId, String userId){
//       return markFrameLabelBL.getFrameLabel(imageIndex, taskId, userId);
//    }
//
//    @Override
//    @RequestMapping(value = "/markFrameLabel/saveFrameLabel", method = RequestMethod.GET)
//    public String saveFrameLabel(String frameLabelVOJson){
//        return markFrameLabelBL.saveFrameLabel(frameLabelVOJson);
//    }
//}
