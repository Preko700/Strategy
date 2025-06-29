package com.example.demo;

public class KungFu extends MartialArt {
    public KungFu() {
        super("Kung Fu");
        addStrike(new Strike("Tiger Claw", 22, 0, 8));   // High bonus damage
        addStrike(new Strike("Dragon Punch", 35, 0, 0)); // Strong attack
        addStrike(new Strike("Crane Stance", 18, 7, 0)); // Defensive with healing
    }
}