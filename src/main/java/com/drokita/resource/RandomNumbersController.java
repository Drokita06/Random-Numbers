package com.drokita.resource;

import com.drokita.model.Operation;
import com.drokita.service.RandomNumbersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/random-numbers")
public class RandomNumbersController {

    private final RandomNumbersService randomNumbersService;

    @Autowired
    public RandomNumbersController(RandomNumbersService randomNumbersService) {
        this.randomNumbersService = randomNumbersService;
    }

    @GetMapping
    public Integer doOperationOnRandomNumbers(
            @RequestParam("min") Integer min,
            @RequestParam("max") Integer max,
            @RequestParam("count") Integer count,
            @RequestParam("operation") Operation operation) {
        return randomNumbersService.doOperationOnRandomNumbers(min, max, count, operation);
    }
}
