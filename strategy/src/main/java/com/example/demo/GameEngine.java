package com.example.demo;

import java.util.*;

public class GameEngine {
    private Player player1;
    private Player player2;
    private CombatLog combatLog;
    private MartialArtFactory factory;
    
    public GameEngine() {
        this.factory = MartialArtFactory.getInstance();
        this.combatLog = new CombatLog();
        initializePlayers();
    }
    
    public void initializePlayers() {
        player1 = new Player("Player 1", new HumanPlayerStrategy());
        player2 = new Player("Player 2", new AIPlayerStrategy());
        
        // Assign 3 random martial arts to each player
        player1.assignMartialArts(factory.getRandomMartialArts(3));
        player2.assignMartialArts(factory.getRandomMartialArts(3));
    }
    
    public void startNewGame() {
        player1 = new Player("Player 1", new HumanPlayerStrategy());
        player2 = new Player("Player 2", new AIPlayerStrategy());
        player1.assignMartialArts(factory.getRandomMartialArts(3));
        player2.assignMartialArts(factory.getRandomMartialArts(3));
        combatLog.clear();
    }
    
    public List<AttackResult> executePlayerTurn(Player attacker, Player defender, MartialArt selectedArt) {
        List<AttackResult> results = attacker.attack(defender, selectedArt);
        combatLog.addAttacks(results);
        return results;
    }
    
    public boolean isGameOver() {
        return !player1.isAlive() || !player2.isAlive();
    }
    
    public Player getWinner() {
        if (!isGameOver()) return null;
        return player1.isAlive() ? player1 : player2;
    }
    
    public void reassignMartialArts(Player player) {
        player.assignMartialArts(factory.getRandomMartialArts(3));
    }
    
    // Getters
    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }
    public CombatLog getCombatLog() { return combatLog; }
    public MartialArtFactory getFactory() { return factory; }
}