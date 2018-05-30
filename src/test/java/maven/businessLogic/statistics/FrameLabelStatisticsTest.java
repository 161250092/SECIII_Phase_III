package maven.businessLogic.statistics;

import maven.model.label.frameLabel.Frame;
import org.junit.Assert;
import org.junit.Test;

public class FrameLabelStatisticsTest {

    FrameLabelStatistics frameLabelStatistics = new FrameLabelStatistics();

    @Test
    public void getAccuracyOfFrameLabelSet() {
    }

    @Test
    public void getAccuracyOfFrame() {
        Frame sampleFrame = new Frame(10, 20, 100, 200, "tag");
        Frame frame1 = new Frame(15, 25, 100, 200, "tag");
        Frame frame2 = new Frame(20, 30, 100, 50, "tag");

        double st1 = (frameLabelStatistics.squareOfEuclideanMetric(10,20,15,25) +
                frameLabelStatistics.squareOfEuclideanMetric(10+100,20,15+100,25)+
                frameLabelStatistics.squareOfEuclideanMetric(10,20+200,15,25+200)+
                frameLabelStatistics.squareOfEuclideanMetric(10+100,20+200,15+100,25+200))/4.0;

        double st2 = (frameLabelStatistics.squareOfEuclideanMetric(10,20,20,30) +
                frameLabelStatistics.squareOfEuclideanMetric(10+100,20,20+100,30) +
                frameLabelStatistics.squareOfEuclideanMetric(10,20+200,20,30+50) +
                frameLabelStatistics.squareOfEuclideanMetric(10+100,20+200,20+100,30+50))/4.0;

        Assert.assertEquals(st1, frameLabelStatistics.getAccuracyOfFrame(sampleFrame, frame1), 0.0001);
        Assert.assertEquals(st2, frameLabelStatistics.getAccuracyOfFrame(sampleFrame, frame2), 0.0001);
    }

    @Test
    public void squareOfEuclideanMetric(){
        Assert.assertEquals(Math.pow((10-20), 2) + Math.pow((30-40),2), frameLabelStatistics.squareOfEuclideanMetric(10,30, 20,40), 0.0001);
        Assert.assertEquals(Math.pow((30-10), 2) + Math.pow((50-20),2), frameLabelStatistics.squareOfEuclideanMetric(30,50, 10,20), 0.0001);
    }
}