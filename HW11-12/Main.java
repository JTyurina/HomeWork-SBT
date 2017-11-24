public class Main {

    public static void main(String[] args) {

//        FixedThreadPool  fixThreadPool = new FixedThreadPool(4);
//
//        for (int i = 0; i < 7; i++ )
//            fixThreadPool.execute(new Task(i));
//        fixThreadPool.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        fixThreadPool.execute(new Task(5));
//        fixThreadPool.execute(new Task(3));

        ScalableThreadPool scalThreadPool = new ScalableThreadPool(2, 4);
        scalThreadPool.start();

        for (int i = 0; i < 5; i++)
            scalThreadPool.execute(new Task(i));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int j= 5; j<10; j++){
            scalThreadPool.execute(new Task(j));
        }





    }
}
