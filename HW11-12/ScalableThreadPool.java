import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ScalableThreadPool implements ThreadPool {
    private final int minThreadCount;
    private final int maxThreadCount;
    private final ArrayList<ThreadRunner> threads;
    private final Queue<Runnable> queue;

    private boolean isRunning = false;

    public ScalableThreadPool(int min, int max) {
        this.minThreadCount = min;
        this.maxThreadCount = max;

        threads = new ArrayList<>(max);
        queue = new LinkedList<>();

        for (int i = 0; i < minThreadCount; i++) {
            threads.add(new ThreadRunner());
            System.err.println("Creating " + threads.get(threads.size() - 1).getName());
        }
    }

    @Override
    public void start() {
        for (ThreadRunner thread : threads)
            thread.start();
        isRunning = true;
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (queue) {
            queue.add(runnable);
            queue.notify();
            checkThreadCount();
        }
    }

    private void checkThreadCount() {
        if (queue.size() > threads.size() && threads.size() < maxThreadCount) {
            threads.add(new ThreadRunner());
            System.err.println("Creating additional " + threads.get(threads.size() - 1).getName());

            if (isRunning)
                threads.get(threads.size() - 1).start();
        }
        if (queue.size() < threads.size() && threads.size() > minThreadCount) {
            System.err.println("Removing additional " + threads.get(threads.size() - 1).getName());
            threads.get(threads.size() - 1).interrupt();
            threads.remove(threads.size() - 1);
        }
    }

    class ThreadRunner extends Thread {
        @Override
        public void run() {
            Runnable r;

            while (! Thread.interrupted()) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            checkThreadCount();
                            queue.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    r = queue.remove();
                }
                r.run();
            }
        }
    }
}
