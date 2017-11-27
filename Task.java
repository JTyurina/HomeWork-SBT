import java.util.concurrent.Callable;

public class Task<T> {

    private final Callable<? extends T> callable;

    private volatile T calculatedValue;
    private volatile CallableException exception;


     public Task(Callable<? extends T> callable) {
         this.callable = callable;
     }

     public T get() {
         // todo implement me
         try {
             run();
             return calculatedValue;
         } catch (CallableException e) {
            throw e;
         }
     }


    public synchronized void run() throws CallableException {
        try {
            calculatedValue = callable.call();
        } catch (Exception e) {
           throw new CallableException(e);

        }
    }
 }