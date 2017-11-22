package main.java;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Iterator;


public class simpleIterator{



    public static <Number> Iterator<Number> testIter(final Number[] array) {
        return new Iterator<Number>() {
            private int size = array.length;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Number next() {
                if (index < size) {
                    return (Number) array[index++];
                } else {
                    throw new NoSuchElementException("Index out of bounds of array");
                }
            }
                    };}



    public static void main(String[] args) {
        Integer[] integers = {3, 6, 5, 7, 4, 5};
        Iterator<Integer> integerIterator = testIter(integers);

        while (integerIterator.hasNext()) {
            System.out.println(integerIterator.next());
        }

        Double[] doubles = {3.0, 6.0, 5.0, 7.0, 4.0, 5.0};
        Iterator<Double> doubleIterator = testIter(doubles);

        while (doubleIterator.hasNext()) {
            System.out.println(doubleIterator.next());
        }


    }
}