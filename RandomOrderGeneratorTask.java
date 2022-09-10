import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;


    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        try {
            int random = (int) (Math.random() * tablets.size());
            Tablet excepted = tablets.get(random);
            excepted.createTestOrder();
            Thread.sleep(interval);
        } catch (InterruptedException e) {

        }
    }
}
