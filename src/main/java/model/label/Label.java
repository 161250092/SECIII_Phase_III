package model.label;

/**
 * 标注类型
 */
abstract public class Label {
    //标注类型
    protected String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
