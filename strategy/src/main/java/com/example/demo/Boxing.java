package com.example.demo;

public class Boxing extends MartialArt {
    public Boxing() {
        super("Box");
        addStrike(new Strike("Jab", 15, 5, 0));          // Light punch with healing
        addStrike(new Strike("Cross", 25, 0, 0));        // Straight punch
        addStrike(new Strike("Hook", 30, 0, 5));         // Hook with bonus damage
    }
}