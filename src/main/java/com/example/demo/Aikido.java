package com.example.demo;

public class Aikido extends MartialArt {
    public Aikido() {
        super("Aikido");
        addStrike(new Strike("Irimi", 22, 6, 0));        // Entering movement
        addStrike(new Strike("Tenkan", 18, 8, 0));       // Turning movement
        addStrike(new Strike("Shiho Nage", 28, 0, 0));   // Four-direction throw
    }
}