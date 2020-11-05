package com.drokita.generators.impl;

import com.drokita.generators.RandomNumberGenerator;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RestApiNumberGenerator implements RandomNumberGenerator {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;
    private static final String RANDOM_API_URL = "https://www.random.org/integers/";
    private static final String AMOUNT_OF_NUMBERS = "num";
    private static final String MINIMUM_NUMBER = "min";
    private static final String MAXIMUM_NUMBER = "max";
    private static final String NUMBER_OF_COLUMNS = "col";
    private static final String NUMBERS_FORMAT = "base";
    private static final String RESPONSE_FORMAT = "format";
    private static final String RANDOMIZATION_FACTOR = "rnd";


    @Autowired
    public RestApiNumberGenerator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Generates random numbers using random.org api
     *
     * @param min   minimum number of range
     * @param max   maximum number of range
     * @param count count of numbers which will be given back
     * @return List of random Integers from range
     */
    @Override
    public List<Integer> generateRandomNumbers(int min, int max, int count) {
        log.info("Generating numbers using {} with params min {}, max {}, count {}", this.getClass().getName(), min, max, count);
        return getRandomNumberFromApi(min, max, count);
    }

    private List<Integer> getRandomNumberFromApi(int min, int max, int count) {
        URI uri = UriComponentsBuilder.fromHttpUrl(RANDOM_API_URL)
                .queryParam(AMOUNT_OF_NUMBERS, count)
                .queryParam(MINIMUM_NUMBER, min)
                .queryParam(MAXIMUM_NUMBER, max)
                .queryParam(NUMBER_OF_COLUMNS, 1)
                .queryParam(NUMBERS_FORMAT, 10)
                .queryParam(RESPONSE_FORMAT, "plain")
                .queryParam(RANDOMIZATION_FACTOR, "new")
                .build().toUri();

        try {
            String response = restTemplate.getForObject(uri, String.class);
            return Lists.newArrayList(Objects.requireNonNull(response).split("\\n"))
                    .stream()
                    .map(NumberUtils::toInt)
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            log.error(e.getMessage());
            return Lists.newArrayList();
        }
    }
}
