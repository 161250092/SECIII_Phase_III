package maven.model.label;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 整体标注类
 */
public class ImageLabel extends Label implements Serializable{

    private List<String> tagList;

    public ImageLabel(List<String> tagList) {
        setType("ImageLabel");
        this.tagList = tagList;
    }

    public ImageLabel(){
        setType("ImageLabel");
        this.tagList = new ArrayList<>();
    }

    public List<String> getTagList() {
        return tagList;
    }

}
