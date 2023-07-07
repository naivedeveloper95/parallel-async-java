package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParallelStreamPerformanceTest {

    ParallelStreamPerformance intStreamExample = new ParallelStreamPerformance();

    @Test
    void sumUsingIntegerStream() {
        int sum = intStreamExample.sumUsingIntegerStream(1000000, false);
        System.out.println("sum : " + sum);
        assertEquals(1784293664, sum);
    }

    @Test
    void sumUsingIntegerStreamParallel() {
        int sum = intStreamExample.sumUsingIntegerStream(1000000, true);
        System.out.println("sum : " + sum);
        assertEquals(1784293664, sum);
    }

    @Test
    void sumUsingIterate() {
        int sum = intStreamExample.sumUsingIterate(1000000, false);
        System.out.println("sum : " + sum);
        assertEquals(1784293664, sum);
    }

    @Test
    void sumUsingIterateParallel() {
        int sum = intStreamExample.sumUsingIterate(1000000, true);
        System.out.println("sum : " + sum);
        assertEquals(1784293664, sum);
    }

    @Test
    void sumUsingIntegerList() {
        int size = 1000000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);

        int sum = intStreamExample.sumUsingIntegerList(inputList, false);
        System.out.println("sum : " + sum);

        assertEquals(1784293664, sum);
    }

    @Test
    void sumUsingIntegerListParallel() {
        int size = 1000000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);

        int sum = intStreamExample.sumUsingIntegerList(inputList, true);
        System.out.println("sum : " + sum);

        assertEquals(1784293664, sum);
    }

}