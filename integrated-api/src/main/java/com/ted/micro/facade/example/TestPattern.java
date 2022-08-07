package com.ted.micro.facade.example;

// https://break-over.tistory.com/47

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestPattern {

    public static void main(String[] args) {
        Computer computer = new Computer();
        Radio radio = new Radio();
        Tv tv = new Tv();
        HomePacade homePacade = new HomePacade(computer, radio, tv);
        homePacade.homeIn();
        homePacade.homeOut();
    }
}
