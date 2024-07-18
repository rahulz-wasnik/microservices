package com.microservices.feature.togglz.config;

import org.togglz.core.Feature;
import org.togglz.core.context.FeatureContext;

public enum FeatureFlags implements Feature {

    @Description("Acer product launch")
    PRODUCT_ACER_LAUNCH;

    @Override
    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
