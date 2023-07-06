package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListSpliteratorExampleTest {

    LinkedListSpliteratorExample linkedListSpliteratorExample = new LinkedListSpliteratorExample();

    @RepeatedTest(5)
    void multipleEachValue() {
        int size = 1000000;
        LinkedList<Integer> inputList = DataSet.generateIntegerLinkedList(size);

        List<Integer> integerList = linkedListSpliteratorExample.multipleEachValue(inputList, 2, false);

        assertEquals(integerList.size(), size);
    }

    @RepeatedTest(5)
    void multipleEachValueInParallel() {
        int size = 1000000;
        LinkedList<Integer> inputList = DataSet.generateIntegerLinkedList(size);

        List<Integer> integerList = linkedListSpliteratorExample.multipleEachValue(inputList, 2, true);

        assertEquals(integerList.size(), size);
    }
}