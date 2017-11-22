import java.io.*;
import java.lang.*;
class PluginManager extends ClassLoader {
    private final String pluginRootDirectory;
    private java.util.Map classesHash= new java.util.HashMap();

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }





    public Class loadClass(String pluginName) throws ClassNotFoundException {

        Class result= (Class)classesHash.get(pluginName);
        if (result!=null) {
            System.out.println("Name "+pluginName+" is already taken!");
            return null;
        }

        File f= findFile(pluginName.replace('.','/'),".class");

        try {
            byte[] classBytes= loadFileAsBytes(f);
            result = defineClass(pluginName,classBytes,0,classBytes.length);
        } catch (IOException e) {
                throw new ClassNotFoundException("Cannot load class "+pluginName+": "+e);
            } catch (ClassFormatError e) {
                throw new ClassNotFoundException("Format of class file incorrect for class "+pluginName+": "+e);
            }
        classesHash.put(pluginName,result);
        return result;
    }



    private File findFile(String name, String extension)
    {

        File f= new File((new File(pluginRootDirectory).getPath() +  File.separatorChar + name.replace('/',File.separatorChar) + extension));
        if (f.exists())
            return f;
        else
            return null;
    }


    public static byte[] loadFileAsBytes(File file) throws IOException
    {
        byte[] result = new byte[(int)file.length()];
        FileInputStream f = new FileInputStream(file);
        try {
            f.read(result,0,result.length);
        }
        finally {
            try {
                f.close();
            }
            catch (Exception e) {
            };
        }
        return result;
    }



}


