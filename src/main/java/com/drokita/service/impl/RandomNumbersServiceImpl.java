package com.drokita.service.impl;

import com.drokita.generators.RandomNumberGenerator;
import com.drokita.model.Operation;
import com.drokita.service.RandomNumbersService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomNumbersServiceImpl implements RandomNumbersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomNumbersServiceImpl.class);

    List<RandomNumberGenerator> generators;

    public void setGenerators(List<RandomNumberGenerator> generators) {
        this.generators = generators;
    }

    @Override
    public Integer doOperationOnRandomNumbers(int min, int max, int count, Operation operation) {
        Integer result = 0;
        for (RandomNumberGenerator generator : generators) {
            List<Integer> numbers = generator.generateRandomNumbers(min, max, count);
            if (numbers.contains(0) && operation.equals(Operation.DIVISION)) {
                LOGGER.warn("Generated numbers contains zero which is illegal to use for {} operation ", operation);
                break;
            }
            switch (operation) {
                case ADDITION:
                    result += numbers.stream().reduce(0, Integer::sum);
                    break;
                case SUBTRACTION:
                    result += numbers.stream().reduce(0, Math::subtractExact);
                    break;
                case MULTIPLICATION:
                    result += numbers.stream().reduce(1, Math::multiplyExact);
                    break;
                case DIVISION:
                    result += numbers.stream().reduce(1, Math::floorDiv);
                    break;
            }
        }
        return result;

        Expression
    }
}
