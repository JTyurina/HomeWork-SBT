package test.java;

import main.java.simpleIterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;



@RunWith(Parameterized.class)
public class simpleIteratorTest<Number> {
    @Parameterized.Parameter
    public Number[] a;

    @Parameterized.Parameter(1)
    public Number expected;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public main.java.simpleIterator simpleIterator = new main.java.simpleIterator();

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{{new Integer[]{1,2},1},{new Double[]{3.0,4.0},3.0}});
    }



    @Test
    public void main() throws Exception {
        assertEquals(expected, main.java.simpleIterator.testIter(a).next());
    }

}