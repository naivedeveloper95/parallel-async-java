package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class ParallelStreamsExample {

    public List<String> stringTransform(List<String> namesList) {
        return namesList
                .stream()
//                .parallelStream()
                .map(this::addNameLengthTransform)
//                .sequential()
                .parallel()
                .collect(Collectors.toList());
    }

    public List<String> stringTransformNew(List<String> namesList, boolean isParallel) {
        Stream<String> nameListStream = namesList.stream();

        if (isParallel)
            nameListStream.parallel();

        return nameListStream
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public List<String> string_toLowerCase(List<String> namesList, boolean isParallel) {
        Stream<String> nameListStream = namesList.stream();

        if (isParallel)
            nameListStream.parallel();

        return nameListStream
                .map(this::nameTransform)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> namesList = DataSet.namesList();
        ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();
        startTimer();
        List<String> resultList = parallelStreamsExample.stringTransform(namesList);
        log("resultList " + resultList);
        timeTaken();
    }

    private String addNameLengthTransform(String name) {
        delay(500);
        return name.length() + " - " + name;
    }

    private String nameTransform(String name) {
        delay(500);
        return name.toLowerCase();
    }
}
