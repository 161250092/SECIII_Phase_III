package model.po.areaLabel;

import java.util.List;

/**
 * 区域标注中单个区域
 */
public class Area {
    private String tag;
    private List<Pixel> areaBorder;

    public Area(String tag, List<Pixel> areaBorder) {
        this.tag = tag;
        this.areaBorder = areaBorder;
    }

    public String getTag() {
        return tag;
    }

    public List<Pixel> getAreaBorder() {
        return areaBorder;
    }
}
