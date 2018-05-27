//package businessLogic.markLabelBL.markLabelBL;
//
//import model.vo.ImageLabelVO;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MarkImageLabelBLStub implements MarkLabelBLService {
//
//    @Override
//    public int getTaskImageNumber(String taskId) {
//        return 3;
//    }
//
//    @Override
//    public String getLabel(String taskId, String userId, String labelType, int imageIndex) {
//        System.out.println("taskId:" + taskId);
//        System.out.println("userId:" + userId);
//        System.out.println("labelType:" + labelType);
//        System.out.println("imageIndex:" + imageIndex);
//
//        List<String> labelList  = new ArrayList<>();
//        ImageLabelVO vo = null;
//
//        if(imageIndex == 0){
//            vo = new ImageLabelVO("test5.jpg", labelList);
//            labelList.add("tag0-1");
//            labelList.add("tag0-2");
//            labelList.add("tag0-3");
//        }else if (imageIndex == 1){
//            vo = new ImageLabelVO("test6.jpg", labelList);
//            labelList.add("tag1-1");
//            labelList.add("tag2-2");
//        }
//
//        Gson gson = new GsonBuilder().create();
//        String objectToJson = gson.toJson(vo);
//
//        return objectToJson;
//    }
//
//    @Override
//    public boolean saveLabel(String taskId, String userId, String labelType, int imageIndex, String labelVOJson) {
//        System.out.println("taskId:" + taskId);
//        System.out.println("userId:" + userId);
//        System.out.println("labelType:" + labelType);
//        System.out.println("imageIndex:" + imageIndex);
//        System.out.println("frameLabelJson:" + labelVOJson);
//        return true;
//    }
//
//    @Override
//    public boolean setTaskAccomplished(String taskId, String userId) {
//        System.out.println("taskId:" + taskId);
//        System.out.println("userId:" + userId);
//        return true;
//    }
//}
