package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.learnjava.util.CommonUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class ParallelStreamsExampleTest {

    ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();

    @Test
    void stringTransform() {
        List<String> namesList = DataSet.namesList();
        startTimer();
        List<String> resultList = parallelStreamsExample.stringTransform(namesList);
        timeTaken();
        assertEquals(4, resultList.size());
        resultList.forEach(name -> assertTrue(name.contains("-")));
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void stringTransformNew(boolean isParallel) {
        startTimer();
        List<String> inputList = DataSet.namesList();

        //when
        List<String> stringList = parallelStreamsExample.stringTransformNew(inputList, isParallel);
        timeTaken();
        stopWatchReset();

        //then
        assertEquals(4, stringList.size());
        stringList.forEach((name) -> {
            assertTrue(name.contains("-"));
        });
    }
}