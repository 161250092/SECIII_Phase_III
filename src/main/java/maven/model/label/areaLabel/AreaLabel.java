package maven.model.label.areaLabel;

import maven.model.label.Label;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 区域标注类
 */
public class AreaLabel extends Label implements Serializable{

    private List<Area> areaList;

    public AreaLabel(List<Area> areaList) {
        setType("AreaLabel");
        this.areaList = areaList;
    }

    public AreaLabel(){
        setType("AreaLabel");
        this.areaList = new ArrayList<>();
    }

    public List<Area> getAreaList() {
        return areaList;
    }

}
