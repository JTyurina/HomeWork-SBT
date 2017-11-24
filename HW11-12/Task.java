
public class Task implements Runnable {
    private final int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.err.println("Task № " +  id + " is running in " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {}

         System.err.println("Task № " +  id + " is completed by " + Thread.currentThread().getName());
    }
}
