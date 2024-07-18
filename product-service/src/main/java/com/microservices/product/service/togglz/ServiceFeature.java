package com.microservices.product.service.togglz;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;

public enum ServiceFeature implements Feature {

    @Label("Acer")
    PRODUCT_ACER_LAUNCH;
}
