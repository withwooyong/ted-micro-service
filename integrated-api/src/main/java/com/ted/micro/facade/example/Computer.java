package com.ted.micro.facade.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Computer {
    private boolean turnOn = false;

    public void turnOn() {
        turnOn = true;
        log.info("{}, turnOn {}", Thread.currentThread().getStackTrace()[1].getMethodName(), true);
    }

    public void turnOff() {
        turnOn = false;
        log.info("turnOff {}", false);
    }

    public boolean isTurnOn() {
        log.info("isTurnOn {}", turnOn);
        return turnOn;
    }
}
