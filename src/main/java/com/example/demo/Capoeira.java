package com.example.demo;

public class Capoeira extends MartialArt {
    public Capoeira() {
        super("Capoeira");
        addStrike(new Strike("Ginga", 20, 3, 0));        // Basic movement with healing
        addStrike(new Strike("Martelo", 28, 0, 0));      // Hammer kick
        addStrike(new Strike("Armada", 32, 0, 3));       // Spinning kick with bonus
    }
}