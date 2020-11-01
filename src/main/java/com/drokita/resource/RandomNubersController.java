package com.drokita.resource;

import com.drokita.service.RandomNumbersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/random-numbers")
public class RandomNubersController {

    private RandomNumbersService randomNumbersService;

    @Autowired
    public RandomNubersController(RandomNumbersService randomNumbersService) {
        this.randomNumbersService = randomNumbersService;
    }

    @GetMapping
    public Integer getRandomNumbers(@RequestParam("min") Integer min, @RequestParam("max") Integer max, @RequestParam("count") Integer count) {
        return randomNumbersService.getSumOfRandomNumbers(min, max, count);
    }
}
