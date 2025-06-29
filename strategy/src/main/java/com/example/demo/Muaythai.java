package com.example.demo;

public class Muaythai extends MartialArt {
    public Muaythai() {
        super("Muay Thai");
        addStrike(new Strike("Teep", 20, 0, 0));         // Push kick
        addStrike(new Strike("Elbow Strike", 40, 0, 0)); // Powerful elbow
        addStrike(new Strike("Knee Strike", 35, 3, 0));  // Knee with healing
    }
}