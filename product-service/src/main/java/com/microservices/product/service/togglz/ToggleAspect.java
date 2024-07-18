package com.microservices.product.service.togglz;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ToggleAspect {

    @Around(
            "@within(toggleFeature) || @annotation(toggleFeature)"
    )
    public Object checkAspect(ProceedingJoinPoint joinPoint, ToggleFeature toggleFeature) throws Throwable {

        if(toggleFeature.value().isActive()) {
            return joinPoint.proceed();
        } else {
            log.info("Feature " + toggleFeature.value().name() + " is not enabled");
            throw new RuntimeException("Togglz not registered");
        }
    }
}
