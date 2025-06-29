package com.example.demo;

public class Jujitsu extends MartialArt {
    public Jujitsu() {
        super("Jiu-Jitsu");
        addStrike(new Strike("Armbar", 25, 0, 0));       // Joint lock
        addStrike(new Strike("Choke", 30, 0, 0));        // Submission
        addStrike(new Strike("Sweep", 20, 5, 5));        // Sweep with heal and bonus
    }
}