package com.robertkoziej.api.github.util;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Requester<T> {

    private final RestTemplate restTemplate;
    private final String url;
    private final Class<T> responseClazz;
    private final List<String> pathVariables = new ArrayList<>();

    public Requester(RestTemplate restTemplate, String url, Class<T> responseClazz) {
        this.restTemplate = restTemplate;
        this.url = url;
        this.responseClazz = responseClazz;
    }

    public Requester<T> addPathVariable(String pathVariable) {
        pathVariables.add(pathVariable);
        return this;
    }

    public T get() {
        return restTemplate.getForEntity(url, responseClazz, pathVariables.toArray())
                .getBody();
    }
}

