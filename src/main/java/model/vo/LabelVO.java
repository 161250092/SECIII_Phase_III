package model.vo;

abstract public class LabelVO {
    String image;

    public String getImage() {
        return image;
    }

    public LabelVO(String image){
        this.image = image;
    }
}
