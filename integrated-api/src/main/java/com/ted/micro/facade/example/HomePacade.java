package com.ted.micro.facade.example;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HomePacade {

    private final Computer computer;
    private final Radio radio;
    private final Tv tv;

    public void homeIn() {
        if (!computer.isTurnOn()) computer.turnOn();
        if (!radio.isTurnOn()) radio.turnOn();
        if (!tv.isTurnOn()) tv.turnOn();
    }

    public void homeOut() {
        if (computer.isTurnOn()) computer.turnOff();
        if (radio.isTurnOn()) radio.turnOff();
        if (tv.isTurnOn()) tv.turnOff();
    }
}
