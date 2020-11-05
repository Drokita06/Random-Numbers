package com.drokita.resource;

import com.drokita.exception.IllegalNumbersInputException;
import com.drokita.model.OPERATION;
import com.drokita.service.RandomNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@RestController
@RequestMapping("/random-numbers")
public class RandomNumbersController {

    private final RandomNumbersService randomNumbersService;

    @Autowired
    public RandomNumbersController(RandomNumbersService randomNumbersService) {
        this.randomNumbersService = randomNumbersService;
    }

    @GetMapping
    public BigDecimal doOperationOnRandomNumbers(
            @RequestParam("min") @NotNull Integer min,
            @RequestParam("max") @NotNull Integer max,
            @RequestParam("count") @Min(1) Integer count,
            @RequestParam("operation") @NotNull OPERATION operation) {
        try {
            return randomNumbersService.doOperationOnRandomNumbers(min, max, count, operation);
        } catch (IllegalNumbersInputException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Numbers are not valid", e);
        }
    }
}
