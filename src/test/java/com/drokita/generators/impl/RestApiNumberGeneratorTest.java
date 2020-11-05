package com.drokita.generators.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class RestApiNumberGeneratorTest {

    RestApiNumberGenerator restApiNumberGenerator;
    private RestTemplate restTemplate = new RestTemplate();

    @BeforeEach
    void setUp(){
        restApiNumberGenerator = new RestApiNumberGenerator(restTemplate);
    }

//    @Test
//    void test1() {
//        restApiNumberGenerator.generateRandomNumbers(1, 10, 10);
//    }

}