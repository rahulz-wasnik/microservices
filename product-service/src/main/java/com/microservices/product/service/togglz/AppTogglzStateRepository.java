package com.microservices.product.service.togglz;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.togglz.core.Feature;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.repository.StateRepository;

import java.net.URI;

@RequiredArgsConstructor
@Slf4j
public class AppTogglzStateRepository implements StateRepository {

    private final RestTemplate restTemplate;
    private final String featureTogglzServiceUri;
    private final boolean enableCustomActivationStrategy;
    public final String AUTHORIZATION = "AUTHORIZATION";

    @Override
    public FeatureState getFeatureState(Feature feature) {
        URI uri = UriComponentsBuilder.fromUriString(featureTogglzServiceUri).pathSegment(feature.name()).build().toUri();

        HttpEntity<String> httpEntity = null;
        if (enableCustomActivationStrategy) {
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
            String authorization = request.getHeader(AUTHORIZATION);
            HttpHeaders headers = new HttpHeaders();
            headers.set(AUTHORIZATION, authorization);
            httpEntity = new HttpEntity<>(headers);
        }

        Boolean state = null;

        try {
            ResponseEntity<Boolean> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, Boolean.class);
            state = response.getBody();
        } catch (Exception exception) {

        }

        return state == null ? null : new FeatureState(feature, state);
    }

    @Override
    public void setFeatureState(FeatureState featureState) {
        /*NOOP*/
    }
}
