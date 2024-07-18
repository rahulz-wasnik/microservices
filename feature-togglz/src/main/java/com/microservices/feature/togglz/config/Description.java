package com.microservices.feature.togglz.config;

import org.togglz.core.annotation.FeatureAttribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@FeatureAttribute(value = "Description")
public @interface Description {

    String value() default "";
}
