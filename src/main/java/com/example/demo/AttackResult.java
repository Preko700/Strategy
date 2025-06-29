package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AttackResult {
    private String attackerName;
    private String strikeName;
    private String martialArt;
    private int damageDealt;
    private int healingReceived;
    private String timestamp;
    
    public AttackResult(String attackerName, String strikeName, String martialArt, 
                       int damageDealt, int healingReceived) {
        this.attackerName = attackerName;
        this.strikeName = strikeName;
        this.martialArt = martialArt;
        this.damageDealt = damageDealt;
        this.healingReceived = healingReceived;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
    
    public void setMartialArt(String martialArt) {
        this.martialArt = martialArt;
    }
    
    @Override
    public String toString() {
        String result = String.format("[%s] %s used %s (%s) - Damage: %d", 
            timestamp, attackerName, strikeName, martialArt, damageDealt);
        
        if (healingReceived > 0) {
            result += ", Healed: " + healingReceived;
        }
        
        return result;
    }
    
    // Getters
    public String getAttackerName() { return attackerName; }
    public String getStrikeName() { return strikeName; }
    public String getMartialArt() { return martialArt; }
    public int getDamageDealt() { return damageDealt; }
    public int getHealingReceived() { return healingReceived; }
    public String getTimestamp() { return timestamp; }
}