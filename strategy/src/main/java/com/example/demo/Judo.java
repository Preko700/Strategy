package com.example.demo;

public class Judo extends MartialArt {
    public Judo() {
        super("Judo");
        addStrike(new Strike("Ippon Seoi", 28, 0, 0));   // Shoulder throw
        addStrike(new Strike("O-Goshi", 24, 4, 0));      // Hip throw with healing
        addStrike(new Strike("Uchi Mata", 32, 0, 2));    // Inner thigh throw
    }
}