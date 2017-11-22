public class Student implements Person {

    private String name;
    private int age;
    private int iqLevel;
    public static final String University = "University";
    public static final String City = "City";

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public int getIqLevel() {
        return iqLevel;
    }

    public void setIqLevel(int iqLevel) {
        this.iqLevel = iqLevel;
    }

    public Student(String name, int age, int iqLevel) {
        this.name = name;
        this.age = age;
        this.iqLevel = iqLevel;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.iqLevel = 150;

    }

    private void increaseIQ()
    {
        this.iqLevel = (int) (this.iqLevel + (this.iqLevel*0.2));
    }

    @Override
    public String toString() {
        return "Student{"  +  "name='" + name + '\'' + ", age=" + getAge() +   ", iqLevel=" + iqLevel + '}';
    }
}
