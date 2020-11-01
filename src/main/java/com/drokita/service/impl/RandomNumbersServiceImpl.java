package com.drokita.service.impl;

import com.drokita.generators.RandomNumberGenerator;
import com.drokita.service.RandomNumbersService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.PostConstruct;

@Service
public class RandomNumbersServiceImpl implements RandomNumbersService {

    List<RandomNumberGenerator> generators;

    public void setGenerators(List<RandomNumberGenerator> generators) {
        this.generators = generators;
    }

    @PostConstruct
    void init() {

    }

    @Override
    public Integer getSumOfRandomNumbers(int min, int max, int count) {
        validateInputsAndDefaultEmpty(min, max, count);
        int sum = 0;
        for (RandomNumberGenerator generator : generators) {
            sum += generator.generateRandomNumbers(min, max, count).stream().mapToInt(Integer::intValue).sum();
        }
        return sum;
    }

    private void validateInputsAndDefaultEmpty(int min, int max, int count) {

    }
}
