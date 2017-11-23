
import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServiceIncreaseMotivation implements Service, Serializable{
    private HashMap<Integer, Integer> hm;

    public int doHardWork(int i){
        hm.put(i, 10*i);
        return 10*i;
    }

    public ServiceIncreaseMotivation() {
        hm = new HashMap();
    }

    public int returnValue(int i)
    {
        System.out.println("Получение данных из кэша..");
        for (Map.Entry entry : hm.entrySet()) {
            if ((Integer)entry.getKey() == i)
                return (Integer)entry.getValue();
        }
        return -1;
    }


    public boolean ValueExists (int i)
    {
        return hm.containsKey(i);
    }

    public void printAllCachedOperations()
    {
        System.out.println("Истории успешных операций по повышению мотивации:");

        for (Map.Entry entry : hm.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void cachedInFile() throws IOException, ClassNotFoundException {
        ServiceIncreaseMotivation ts;
        FileInputStream fis = new FileInputStream("C:\\Users\\TurUli\\Downloads\\2.txt");

        try {
            ObjectInputStream oin = new ObjectInputStream(fis);
            ts = (ServiceIncreaseMotivation) oin.readObject();
        }
        catch (EOFException e)
        {
            ts = new ServiceIncreaseMotivation();
        }
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Введите показатель мотивации: ");
            int intMotive = scanner.nextInt();
            if (ts.ValueExists(intMotive))
                System.out.println(ts.returnValue(intMotive));
            else {
                System.out.println(ts.doHardWork(intMotive));
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\TurUli\\Downloads\\2.txt"));
                //  out.reset();
                out.writeObject(ts);
                out.close();
            }
        }
    }

    public int cachedInMemory(Object obj, Method method, int[] args) throws Throwable
    {
        int result;
        if (!hm.containsKey(args[0])) {
            result = (int) method.invoke(obj, (int)args[0]);
            hm.put((int)args[0], (Integer)result);
        } else {
            System.out.println("Результат получаем из памяти...");
            result = (int)hm.get((int)args[0]);
        }

        return (int) result;
    }




    public static void main(String[] args) throws Throwable {
        ServiceIncreaseMotivation ts = new ServiceIncreaseMotivation();
        int[][] iArgs = {{5},{3},{5}};
        Class[] paramTypes = new Class[] {int.class};
        Method method = ServiceIncreaseMotivation.class.getMethod("doHardWork", paramTypes);
        for (int i=0; i<iArgs.length; i++)
            System.out.println(ts.cachedInMemory(ts, method, iArgs[i]));

    }

}

