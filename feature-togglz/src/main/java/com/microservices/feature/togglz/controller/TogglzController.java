package com.microservices.feature.togglz.controller;

import com.microservices.feature.togglz.config.FeatureFlags;
import com.microservices.feature.togglz.config.ValueOfEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/togglz")
@Validated
public class TogglzController {

    private final FeatureManager featureManager;

    @GetMapping("/{toggle}")
    public ResponseEntity<Boolean> getToggleState(@PathVariable @ValueOfEnum(enumClass = FeatureFlags.class) String toggle) {
        return ResponseEntity.ok(featureManager.isActive(FeatureFlags.valueOf(toggle)));
    }
}
