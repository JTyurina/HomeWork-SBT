public class Main {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {


   ClassLoader loader= new PluginManager("C:\\Users\\TurUli\\Desktop\\PluginTest");
    Object object = loader.loadClass("TestPlugin").newInstance();
   //     Class clazz= Class.forName("Calculator",true,loader);
   //     Object object= clazz.newInstance();
      // System.out.println(object);


    }
}
