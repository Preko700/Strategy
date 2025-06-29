package com.example.demo;

import java.util.*;

public class Player {
    private String name;
    private int health;
    private List<MartialArt> assignedArts;
    private PlayerStrategy strategy;
    
    public Player(String name, PlayerStrategy strategy) {
        this.name = name;
        this.health = 200;
        this.assignedArts = new ArrayList<>();
        this.strategy = strategy;
    }
    
    public List<AttackResult> attack(Player opponent, MartialArt selectedArt) {
        List<Strike> strikes = strategy.selectAttacks(assignedArts, selectedArt);
        List<AttackResult> results = new ArrayList<>();
        
        for (Strike strike : strikes) {
            if (opponent.isAlive()) {
                AttackResult result = strike.execute(this, opponent);
                result.setMartialArt(selectedArt.getName());
                results.add(result);
            }
        }
        
        return results;
    }
    
    public void takeDamage(int damage) {
        this.health = Math.max(0, this.health - damage);
    }
    
    public void heal(int amount) {
        this.health = Math.min(200, this.health + amount);
    }
    
    public boolean isAlive() {
        return health > 0;
    }
    
    public void assignMartialArts(List<MartialArt> arts) {
        this.assignedArts = new ArrayList<>(arts);
    }
    
    // Getters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public List<MartialArt> getAssignedArts() { return new ArrayList<>(assignedArts); }
}