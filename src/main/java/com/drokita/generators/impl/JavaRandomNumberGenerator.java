package com.drokita.generators.impl;

import com.drokita.generators.RandomNumberGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class JavaRandomNumberGenerator implements RandomNumberGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiNumberGenerator.class);

    @Override
    public List<Integer> generateRandomNumbers(int min, int max, int count) {
        LOGGER.info("Generating numbers using {} with params min {}, max {}, count {}", this.getClass().getName(), min, max, count);
        return IntStream.range(0, count)
                .mapToObj(i -> (int) (Math.random() * ((max - min) + 1)) + min)
                .collect(Collectors.toList());
    }
}
