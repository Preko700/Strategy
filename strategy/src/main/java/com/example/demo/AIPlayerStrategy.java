package com.example.demo;

import java.util.*;

public class AIPlayerStrategy implements PlayerStrategy {
    @Override
    public List<Strike> selectAttacks(List<MartialArt> availableArts, MartialArt selectedArt) {
        List<Strike> attacks = new ArrayList<>();
        Random random = new Random();
        int numAttacks = random.nextInt(4) + 3; // 3-6 attacks
        
        for (int i = 0; i < numAttacks; i++) {
            // AI can use any available martial art
            MartialArt randomArt = availableArts.get(random.nextInt(availableArts.size()));
            attacks.add(randomArt.getRandomStrike());
        }
        
        return attacks;
    }
}