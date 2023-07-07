package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReduceExampleTest {

    ReduceExample reduceExample = new ReduceExample();

    @Test
    void reduceSumInParallel() {
        List<Integer> integerList = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        int result = reduceExample.reduceSumInParallel(integerList);
        assertEquals(36, result);
    }

    @Test
    void reduceMultiplyInParallel() {
        List<Integer> integerList = List.of(1, 2, 3, 4);
        int result = reduceExample.reduceMultiplyInParallel(integerList);
        assertEquals(24, result);
    }
}