package com.example.demo;

public class Krav extends MartialArt {
    public Krav() {
        super("Krav Maga");
        addStrike(new Strike("Palm Strike", 26, 0, 4)); // Palm with bonus
        addStrike(new Strike("Knee Groin", 38, 0, 0));  // Devastating knee
        addStrike(new Strike("Eye Poke", 15, 0, 10));   // Low damage, high bonus
    }
}