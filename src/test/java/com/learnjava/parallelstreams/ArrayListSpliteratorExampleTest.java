package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListSpliteratorExampleTest {

    ArrayListSpliteratorExample arrayListSpliteratorExample = new ArrayListSpliteratorExample();

    @RepeatedTest(5)
    void multipleEachValue() {
        int size = 1000000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);

        List<Integer> integerList = arrayListSpliteratorExample.multipleEachValue(inputList, 2, false);

        assertEquals(integerList.size(), size);
    }

    @RepeatedTest(5)
    void multipleEachValueInParallel() {
        int size = 1000000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);

        List<Integer> integerList = arrayListSpliteratorExample.multipleEachValue(inputList, 2, true);

        assertEquals(integerList.size(), size);
    }
}