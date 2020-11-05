package com.drokita.service.impl;

import com.drokita.exception.IllegalNumbersInputException;
import com.drokita.generators.RandomNumberGenerator;
import com.drokita.generators.impl.JavaRandomNumberGenerator;
import com.drokita.model.OPERATION;
import com.drokita.service.RandomNumbersService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RandomNumbersServiceImplTest {

    private RandomNumbersService randomNumbersService;

    private JavaRandomNumberGenerator randomNumberGenerator;

    private final List<RandomNumberGenerator> generators = new ArrayList<>();

    private int min;
    private int max;
    private int count;

    @BeforeEach
    void setUp() {
        randomNumberGenerator = mock(JavaRandomNumberGenerator.class);
        generators.add(randomNumberGenerator);
        randomNumbersService = new RandomNumbersServiceImpl(generators);
        min = 1;
        max = 10;
        count = 2;
    }

    @ParameterizedTest
    @CsvSource(value = {"ADDITION:1:5:6",
            "SUBTRACTION:2:4:-2",
            "MULTIPLICATION:3:3:9",
            "DIVISION:10:5:2"},
            delimiter = ':')
    void shouldAddRandomNumbers(OPERATION operation, int first, int second, int result) throws IllegalNumbersInputException {
        //when
        when(randomNumberGenerator.generateRandomNumbers(anyInt(), anyInt(), anyInt())).thenReturn(Lists.newArrayList(first, second));
        BigDecimal bigDecimal = randomNumbersService.doOperationOnRandomNumbers(min, max, count, operation);

        //then
        assertEquals(BigDecimal.valueOf(result), bigDecimal);
    }

    @ParameterizedTest
    @CsvSource(value = {"ADDITION:-2:2:2",
            "SUBTRACTION:1:-2:2",
            "MULTIPLICATION:1:5:0"},
            delimiter = ':')
    void shouldThrowIllegalNumbersInputException(OPERATION operation, int min, int max, int count) {
        assertThrows(IllegalNumbersInputException.class, () -> {
            randomNumbersService.doOperationOnRandomNumbers(min, max, count, operation);
        });
    }

}