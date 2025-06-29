package com.example.demo;

public class Taekwondo extends MartialArt {
    public Taekwondo() {
        super("Taekwondo");
        addStrike(new Strike("Dollyo Chagi", 30, 0, 0)); // Roundhouse kick
        addStrike(new Strike("Yeop Chagi", 26, 2, 0));   // Side kick with healing
        addStrike(new Strike("Naeryeo Chagi", 35, 0, 5)); // Axe kick with bonus
    }
}