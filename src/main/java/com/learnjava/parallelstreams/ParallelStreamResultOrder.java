package com.learnjava.parallelstreams;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.learnjava.util.LoggerUtil.log;

public class ParallelStreamResultOrder {

    public static List<Integer> listOrder(List<Integer> integerList) {
        return integerList.parallelStream()
                .map(integer -> integer * 2)
                .collect(Collectors.toList());
    }

    public static Set<Integer> setOfOrder(Set<Integer> integerSet) {
        return integerSet.parallelStream()
                .map(integer -> integer * 2)
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        log("Integer List " + integerList);
        List<Integer> orderList = listOrder(integerList);
        log("Result " + orderList);


        Set<Integer> setOfList = Set.of(1, 2, 3, 4, 5, 6, 7, 8);
        log("Set List " + setOfList);
        Set<Integer> integerSet = setOfOrder(setOfList);
        log("Result " + integerSet);
    }
}
