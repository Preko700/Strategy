package com.example.demo;

import java.util.*;

public abstract class MartialArt {
    protected String name;
    protected List<Strike> strikes;
    
    public MartialArt(String name) {
        this.name = name;
        this.strikes = new ArrayList<>();
    }
    
    protected void addStrike(Strike strike) {
        strikes.add(strike);
    }
    
    public List<Strike> getStrikes() {
        return new ArrayList<>(strikes);
    }
    
    public String getName() {
        return name;
    }
    
    public Strike getRandomStrike() {
        Random random = new Random();
        return strikes.get(random.nextInt(strikes.size()));
    }
}