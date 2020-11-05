package com.drokita.service;

import com.drokita.model.Operation;

public interface RandomNumbersService {
    Integer doOperationOnRandomNumbers(int min, int max, int count, Operation operation);
}
