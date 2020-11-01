package com.drokita.generators;

import java.math.BigDecimal;
import java.util.List;

public interface RandomNumberGenerator {
    List<Integer> generateRandomNumbers(int min, int max, int count);
}
