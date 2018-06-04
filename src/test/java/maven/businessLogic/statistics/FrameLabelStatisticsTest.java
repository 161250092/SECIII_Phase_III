package maven.businessLogic.statistics;

import maven.model.label.frameLabel.Frame;
import maven.model.label.frameLabel.FrameLabel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class FrameLabelStatisticsTest {

    FrameLabelStatistics frameLabelStatistics = new FrameLabelStatistics();


    @Test
    public void accuracyOfLabel1(){
        ArrayList<Frame> fl = new ArrayList<>();
        fl.add(new Frame(10, 10, 50 , 50, ""));
        fl.add(new Frame(70,70,10,10,""));
        fl.add(new Frame(270,270,20,20,""));
        FrameLabel frameLabel = new FrameLabel(fl);

        ArrayList<Frame> fl2 = new ArrayList<>();
        fl2.add(new Frame(0,0,40,40,""));
        fl2.add(new Frame(50,50,200,200,""));
        fl2.add(new Frame(300,300,100,100,""));
        FrameLabel sampleLabel = new FrameLabel(fl2);

        double d = ((30.0*30.0)/(50.0*50.0) + (10.0*10.0)/(200.0*200.0))/3.0;
        Assert.assertEquals(d, frameLabelStatistics.accuracyOfLabel(frameLabel,sampleLabel), 0.0001);
    }
    @Test
    public void accuracyOfLabel2(){
        ArrayList<Frame> fl = new ArrayList<>();
        fl.add(new Frame(10, 10, 50 , 50, ""));
        fl.add(new Frame(70,70,180,180,""));
        fl.add(new Frame(270,270,20,20,""));
        FrameLabel frameLabel = new FrameLabel(fl);

        ArrayList<Frame> fl2 = new ArrayList<>();
        fl2.add(new Frame(0,0,40,40,""));
        fl2.add(new Frame(50,50,200,200,""));
        fl2.add(new Frame(300,300,100,100,""));
        FrameLabel sampleLabel = new FrameLabel(fl2);

        double d = ((30.0*30.0)/(50.0*50.0) + (180.0*180.0)/(200.0*200.0))/3.0;
        Assert.assertEquals(d, frameLabelStatistics.accuracyOfLabel(frameLabel,sampleLabel), 0.0001);
    }
    @Test
    public void accuracyOfLabel3(){
        ArrayList<Frame> fl = new ArrayList<>();
        fl.add(new Frame(300,300,90,90,""));
        fl.add(new Frame(10, 10, 50 , 50, ""));
        fl.add(new Frame(70,70,180,180,""));
        FrameLabel frameLabel = new FrameLabel(fl);

        ArrayList<Frame> fl2 = new ArrayList<>();
        fl2.add(new Frame(0,0,40,40,""));
        fl2.add(new Frame(50,50,200,200,""));
        fl2.add(new Frame(300,300,100,100,""));
        FrameLabel sampleLabel = new FrameLabel(fl2);

        double d = ((30.0*30.0)/(50.0*50.0) + (180.0*180.0)/(200.0*200.0) + (90.0*90.0)/(100.0*100.0))/3.0;
        System.out.println(d);
        Assert.assertEquals(d, frameLabelStatistics.accuracyOfLabel(frameLabel,sampleLabel), 0.0001);
    }

    @Test
    public void sizeOfOverlappingArea1() {
        Frame sampleFrame = new Frame(10, 20, 100, 200, "tag");
        Frame frame1 = new Frame(15, 25, 100, 200, "tag");

        double st1 = 95*195;

        Assert.assertEquals(st1, frameLabelStatistics.sizeOfOverlappingArea(frame1,sampleFrame), 0.0001);
    }
    @Test
    public void sizeOfOverlappingArea2() {
        Frame sampleFrame = new Frame(10, 20, 100, 200, "tag");
        Frame frame2 = new Frame(20, 30, 100, 50, "tag");

        double st2 = 90*50;

        Assert.assertEquals(st2, frameLabelStatistics.sizeOfOverlappingArea(frame2,sampleFrame), 0.0001);
    }
    @Test
    public void sizeOfOverlappingArea3() {
        Frame sampleFrame = new Frame(10, 20, 10, 20, "tag");
        Frame frame = new Frame(30, 50, 10, 20, "tag");

        double st = 0;

        Assert.assertEquals(st, frameLabelStatistics.sizeOfOverlappingArea(frame,sampleFrame), 0.0001);
    }
}