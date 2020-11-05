package com.drokita.service.impl;

import com.drokita.exception.IllegalNumbersInputException;
import com.drokita.generators.RandomNumberGenerator;
import com.drokita.model.OPERATION;
import com.drokita.service.RandomNumbersService;
import com.udojava.evalex.Expression;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class RandomNumbersServiceImpl implements RandomNumbersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomNumbersServiceImpl.class);

    List<RandomNumberGenerator> generators;

    public void setGenerators(List<RandomNumberGenerator> generators) {
        this.generators = generators;
    }

    RandomNumbersServiceImpl(List<RandomNumberGenerator> generators) {
        this.generators = generators;
    }

    /**
     * Performs operation on random numbers from range
     *
     * @param min       minimum number of range
     * @param max       maximum number of range
     * @param count     count of numbers which will be given back
     * @param operation see
     * @return Sum of random numbers from possible sources
     * @throws IllegalNumbersInputException
     */
    @Override
    public BigDecimal doOperationOnRandomNumbers(int min, int max, int count, OPERATION operation) throws IllegalNumbersInputException {
        validateInputs(min, max, count);
        List<Integer> resultNumbers = new ArrayList<>();
        for (RandomNumberGenerator generator : generators) {
            List<Integer> numbers = generator.generateRandomNumbers(min, max, count);
            if (numbers.contains(0) && operation.equals(OPERATION.DIVISION)) {
                LOGGER.warn("Generated numbers contains zero which is illegal to use for {} operation ", operation);
                break;
            }
            resultNumbers.addAll(numbers);
        }
        LOGGER.info("Generated random numbers {}", resultNumbers);
        String resultExpression = StringUtils.join(resultNumbers, operation.getSign());
        Expression expression = new Expression(resultExpression);
        return expression.eval();
    }

    private void validateInputs(int min, int max, int count) throws IllegalNumbersInputException {
        if (min < 0 || max < 0 || count < 1) {
            throw new IllegalNumbersInputException();
        }
    }
}
