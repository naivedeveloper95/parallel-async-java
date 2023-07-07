package com.learnjava.parallelstreams;

import java.util.ArrayList;
import java.util.List;

public class ReduceExample {
    public int reduceSumInParallel(List<Integer> integerList) {
        return integerList
                .parallelStream()
                .reduce(0, Integer::sum);
    }

    public int reduceMultiplyInParallel(List<Integer> integerList) {
        return integerList
                .parallelStream()
                .reduce(1, (x, y) -> x * y);
    }
}
