package maven.model.label.frameLabel;

import maven.model.label.Label;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 标框标注类
 */
public class FrameLabel extends Label implements Serializable {

    private List<Frame> frameList;

    public FrameLabel(List<Frame> frameList) {
        setType("FrameLabel");
        this.frameList = frameList;
    }

    public FrameLabel(){
        setType("FrameLabel");
        this.frameList = new ArrayList<Frame>();
    }

    public List<Frame> getFrameList() { return frameList; }
}

