public class Worker implements Person{
    private String name;
    private int age;
    private int efficiency;



    public Worker(String name, int age, int senseOfHumor) {
        this.name = name;
        this.age = age;
        this.efficiency = senseOfHumor;

    }

    public Worker(String name, int age) {
        this.name = name;
        this.age = age;

    }

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

    public int getSenseOfHumor() {
        return efficiency;
    }

    public void setSenseOfHumor(int senseOfHumor) {
        this.efficiency = senseOfHumor;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", efficiency=" + efficiency +
                '}';
    }
}
