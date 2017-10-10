package sbtPack;

public class Main {

    public static void main(String[] args) {
        Person m1 = new Person(true,"Alex");
        Person m2 = new Person(true,"Matt");
        Person w1 = new Person(false,"Jane");
        Person w2 = new Person(false,"Alice");

        m1.marry(m2);
        w1.marry(w1);
        m1.marry(w1);
        m2.marry(w2);
        w1.marry(m2);



    }

}
