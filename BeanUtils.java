import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.lang.reflect.*;
import java.util.*;


public class BeanUtils {

    public static boolean isGetter(Method method)
    {
        if (!method.getName().startsWith("get"))
            return false;
        else
            return true;

    }

    public static boolean isSetter(Method method){
        if (!method.getName().startsWith("set")) {
            return false;
        }
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        return true;
    }


    // вывести на консоль все методы класса, включая все родительские методы  (включая приватные)

    public static void printAllObjectMethods(Object from)
    {
        System.out.print("Methods of class " + from.getClass().getName() + ": { ");
        Method[] methodsFrom = from.getClass().getDeclaredMethods();
        for (Method method: methodsFrom)
            System.out.print(method.getName() + "; ");
        System.out.print(" }");
        System.out.println();
    }

    public static boolean checkFinalFieldsBeNameValue(Object from) throws IllegalAccessException {
        Field[] fieldsFrom = from.getClass().getDeclaredFields();
        Map<Field,Boolean> finalFields = new HashMap<>();
        for (Field field: fieldsFrom) {
            if  ((field.getType() == String.class) &&  Modifier.isFinal(field.getModifiers())) // получить все строковые поля с модификатором final
            {
                String value = (String) field.get(from);
                finalFields.put(field, field.getName().equals(value));
            }
        }
        return (!finalFields.containsValue(false))?true:false;


    }

    public static void printAllGetters(Object from)
    {
        Method[] methods = from.getClass().getMethods();
        System.out.print("Getters of class " + from.getClass().getName() + ": { ");
        for (Method method: methods)
            if (isGetter(method))
                System.out.print(method.getName() + "; ");
        System.out.print(" }");
        System.out.println();

    }


    public static void assign(Object to, Object from){

        // получить все публичные методы классы from
        Method[] methodsFrom = from.getClass().getMethods();
        Method[] methodsTo = to.getClass().getMethods();
        // получить только геттеры
        ArrayList<Method> getters = new ArrayList<>();
        ArrayList<Method> setters = new ArrayList<>();
        for (Method method: methodsFrom)
            if (isGetter(method))
                getters.add(method);

        // / получить только сеттеры
        for (Method method: methodsTo)
            if (isSetter(method))
                setters.add(method);

        for (Method method:getters)
        {
            String searchedMethName = "set" + (method.getName().toString().replaceAll("get",""));
            try {
                Method m = to.getClass().getMethod(searchedMethName, method.getReturnType());
                m.invoke(to, method.invoke(from));
            }
            catch (NoSuchMethodException e) {

            }
            catch (IllegalAccessException e) {

            }
            catch (InvocationTargetException e) {

            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("Source data: ");
        Worker w = new Worker("Ivan", 23, 4);
        Student s = new Student("Darya", 18, 130);
        System.out.println(w.toString());
        System.out.println(s.toString());
        System.out.println();

        // вывести на консоль все методы класса, включая все родительские методы  (включая приватные)
        printAllObjectMethods(w);

        // Вывести все геттеры класса
        printAllGetters(s);

        // Проверить что все String константы имеют значение = их имени
        System.out.println("All final fields name are equal to their values: " + checkFinalFieldsBeNameValue(s));
        System.out.println();

        // Реализовать кэширующий прокси
        Person p1 = (Person) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),Worker.class.getInterfaces(),new LogHandler(w));
        Person p2 = (Person) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),Student.class.getInterfaces(),new LogHandler(s));


        /*Scans object "from" for all getters. If object "to" contains correspondent setter, it will invoke it to set property value for "to" which equals to the propertyof "from". */
        System.out.println("Setting new values:");
        assign(p1, p2);
        System.out.println();
        System.out.println("Result data: ");
        System.out.println(w.toString());
        System.out.print(s.toString());
    }
}
