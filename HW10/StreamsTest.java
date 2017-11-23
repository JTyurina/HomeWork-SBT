package test.java;


import main.java.Streams;
import org.junit.Before;
import org.junit.Test;
import main.java.Person;

import java.util.*;

import static org.junit.Assert.*;

public class StreamsTest {

    private List<Person> personList;

    @Before
    public void setUp() throws Exception {

        personList = new ArrayList<>();
        personList.add(new Person("Ann", 30));
        personList.add(new Person("Pavel", 20));
        personList.add(new Person("Kate", 25));

    }

    @Test
    public void ofAndToMapList() throws Exception {

        Map<String, Integer> m = Streams.of(personList)
                .toMap(Person::getName, Person::getAge);

        assertEquals(3, m.size());

        assertEquals(30, (long) m.get("Ann"));
        assertEquals(20, (long) m.get("Pavel"));
        assertEquals(25, (long) m.get("Kate"));
    }



    @Test
    public void filterList() throws Exception {

        Map<String, Integer> m = Streams.of(personList)
                .filter(p -> p.getName().length() > 3)
                .toMap(Person::getName, Person::getAge);

        assertEquals(2, m.size());
        assertEquals(20, (long) m.get("Pavel"));
        assertEquals(25, (long) m.get("Kate"));
    }


    @Test
    public void transformList() throws Exception {

        Map<String, Integer> m = Streams.of(personList)
                .transform(p -> new Person("Person " + p.getName(), p.getAge() + 1))
                .toMap(Person::getName, Person::getAge);

        assertEquals(3, m.size());

        assertEquals(31, (long) m.get("Person Ann"));
        assertEquals(21, (long) m.get("Person Pavel"));
        assertEquals(26, (long) m.get("Person Kate"));
    }



}