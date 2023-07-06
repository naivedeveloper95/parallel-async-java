package com.learnjava.parallelstreams;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

public class LinkedListSpliteratorExample {
    public List<Integer> multipleEachValue(
            LinkedList<Integer> inputList,
            int multiplyValue,
            boolean isParallel
    ) {
        startTimer();

        Stream<Integer> inputStream = inputList.stream();
        if (isParallel) inputStream.parallel();
        List<Integer> integerList = inputStream
                .map(integer -> integer * multiplyValue)
                .collect(Collectors.toList());

        timeTaken();
        return integerList;
    }
}
