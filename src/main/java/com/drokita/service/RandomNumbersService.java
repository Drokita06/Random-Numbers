package com.drokita.service;

import com.drokita.exception.IllegalNumbersInputException;
import com.drokita.model.OPERATION;

import java.math.BigDecimal;

public interface RandomNumbersService {
    BigDecimal doOperationOnRandomNumbers(int min, int max, int count, OPERATION operation) throws IllegalNumbersInputException;
}
