package com.ted.micro.facade.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Tv {
    private boolean turnOn = false;

    public void turnOn() {
        turnOn = true;
        log.info("{}", true);
    }

    public void turnOff() {
        turnOn = false;
        log.info("{}", false);
    }

    public boolean isTurnOn() {
        log.info("{}", turnOn);
        return turnOn;
    }
}
