package com.example.demo;

import java.util.*;

public class CombatLog {
    private List<AttackResult> combatHistory;
    
    public CombatLog() {
        this.combatHistory = new ArrayList<>();
    }
    
    public void addAttack(AttackResult result) {
        combatHistory.add(result);
    }
    
    public void addAttacks(List<AttackResult> results) {
        combatHistory.addAll(results);
    }
    
    public List<AttackResult> getCombatHistory() {
        return new ArrayList<>(combatHistory);
    }
    
    public void clear() {
        combatHistory.clear();
    }
}