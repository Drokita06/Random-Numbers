package com.drokita.service.impl;

import com.drokita.model.Operation;
import com.drokita.service.RandomNumbersService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = "classpath:random-generators-test.xml")
class RandomNumbersServiceImplTest {

    private RandomNumbersService randomNumbersService;

    @BeforeEach
    void setUp() {
        randomNumbersService = new RandomNumbersServiceImpl();
    }

//    @ParameterizedTest
//    @CsvSource({"1,10,2,ADDITION",
//            "1,10,2,SUBSTRACTION",
//            "1,10,2,MULTIPLICATION",
//            "1,10,2,DIVISION"})
//    void test(int min, int max, int count, Operation operation) {
//        randomNumbersService.doOperationOnRandomNumbers(min, max, count, operation);
//    }

}