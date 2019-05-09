package maven;

import maven.businessLogic.allocateMassTaskBL.AllocateMassTaskBLImpl;
import maven.businessLogic.allocateMassTaskBL.AllocateMassTaskBLService;

public class MassTaskAllocator implements Runnable {
    private Thread t;
    private AllocateMassTaskBLService allocateMassTaskBL;

    MassTaskAllocator() {
        allocateMassTaskBL = new AllocateMassTaskBLImpl();
    }

    @Override
    public void run() {
        long MILLIS_OF_ONE_DAY = 1000 * 60 * 60 * 24;
        try{
            while (true){
                System.out.println("Start allocating mass tasks.");
                allocateMassTaskBL.allocateMassTask(System.currentTimeMillis());
                System.out.println("Finish allocating mass tasks");
                Thread.sleep(MILLIS_OF_ONE_DAY);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void start(){
        System.out.println("Mass Task Allocator Initialize");
        if (t == null){
            t = new Thread(this);
            t.start();
        }
    }
}
