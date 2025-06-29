package com.example.demo;

public class Karate extends MartialArt {
    public Karate() {
        super("Karate");
        addStrike(new Strike("Mae Geri", 25, 0, 0));     // Front kick
        addStrike(new Strike("Yoko Geri", 30, 0, 0));    // Side kick
        addStrike(new Strike("Mawashi Geri", 35, 0, 0)); // Round kick
    }
}