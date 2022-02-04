import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LoaderMultithreaded {
    public static void main(String[] args){
        int maxRegion = 100;
        long start = System.currentTimeMillis();
        int threadsCount = Runtime.getRuntime().availableProcessors();
//        threadsCount = 1;
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
        for (int region = 0; region < maxRegion; region++) {
            Thread thread = new Thread(new RegionGenerator(region));
            executor.execute(thread);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long totalTime = System.currentTimeMillis() - start;
        System.out.println("Threads count: " + threadsCount);
        System.out.println("Total time: " + totalTime + " ms");
    }
}
