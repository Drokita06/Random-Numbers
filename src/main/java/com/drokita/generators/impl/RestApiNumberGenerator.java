package com.drokita.generators.impl;

import com.google.common.collect.Lists;

import com.drokita.generators.RandomNumberGenerator;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiNumberGenerator.class);

    private final RestTemplate restTemplate;
    private static final String RANDOM_API_URL = "https://www.random.org/integers/";

    @Autowired
    public RestApiNumberGenerator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Integer> generateRandomNumbers(int min, int max, int count) {
        LOGGER.info("Generating numbers using {} with params min {}, max {}, count {}", this.getClass().getName(), min, max, count);
        return getRandomNumberFromApi(min, max, count);
    }

    private List<Integer> getRandomNumberFromApi(int min, int max, int count) {
        URI uri = UriComponentsBuilder.fromHttpUrl(RANDOM_API_URL)
                .queryParam("num", count)
                .queryParam("min", min)
                .queryParam("max", max)
                .queryParam("col", 1)
                .queryParam("base", 10)
                .queryParam("format", "plain")
                .queryParam("rnd", "new")
                .build().toUri();

        try {
            String response = restTemplate.getForObject(uri, String.class);
            return Lists.newArrayList(Objects.requireNonNull(response).split("\\n"))
                    .stream()
                    .map(NumberUtils::toInt)
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return Lists.newArrayList();
        }
    }
}
