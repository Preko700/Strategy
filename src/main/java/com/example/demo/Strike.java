package com.example.demo;

public class Strike {
    private String name;
    private int damage;
    private int healAmount;
    private int bonusDamage;
    
    public Strike(String name, int damage, int healAmount, int bonusDamage) {
        this.name = name;
        this.damage = damage;
        this.healAmount = healAmount;
        this.bonusDamage = bonusDamage;
    }
    
    public AttackResult execute(Player attacker, Player defender) {
        int totalDamage = damage + bonusDamage;
        defender.takeDamage(totalDamage);
        
        if (healAmount > 0) {
            attacker.heal(healAmount);
        }
        
        return new AttackResult(
            attacker.getName(),
            this.name,
            "", // Will be set by martial art
            totalDamage,
            healAmount
        );
    }
    
    public String getName() { return name; }
    public int getDamage() { return damage; }
    public int getHealAmount() { return healAmount; }
    public int getBonusDamage() { return bonusDamage; }
}