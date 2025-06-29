package com.example.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameUI extends JFrame {
    private GameEngine gameEngine;
    private JLabel player1Health, player2Health;
    private JTextArea combatLogArea;
    private JComboBox<MartialArt> player1ArtsCombo;
    private JButton attackButton, reassignP1Button, reassignP2Button, newGameButton;
    private JPanel player1ArtsPanel, player2ArtsPanel;
    
    public GameUI() {
        gameEngine = new GameEngine();
        setupUI();
        updateDisplay();
    }
    
    private void setupUI() {
        setTitle("Martial Arts Fighting Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Main panel
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Player 1 Panel
        JPanel player1Panel = createPlayerPanel(gameEngine.getPlayer1(), true);
        mainPanel.add(player1Panel);
        
        // Player 2 Panel
        JPanel player2Panel = createPlayerPanel(gameEngine.getPlayer2(), false);
        mainPanel.add(player2Panel);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Combat log
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(BorderFactory.createTitledBorder("Combat Log"));
        
        combatLogArea = new JTextArea(8, 50);
        combatLogArea.setEditable(false);
        combatLogArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(combatLogArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        logPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Control buttons
        JPanel controlPanel = new JPanel(new FlowLayout());
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> startNewGame());
        controlPanel.add(newGameButton);
        
        logPanel.add(controlPanel, BorderLayout.SOUTH);
        add(logPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private JPanel createPlayerPanel(Player player, boolean isPlayer1) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(player.getName()));
        
        // Health display
        JLabel healthLabel = new JLabel("Health: 200/200", JLabel.CENTER);
        healthLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        if (isPlayer1) {
            player1Health = healthLabel;
        } else {
            player2Health = healthLabel;
        }
        panel.add(healthLabel, BorderLayout.NORTH);
        
        // Martial arts display
        JPanel artsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        artsPanel.setBorder(BorderFactory.createTitledBorder("Martial Arts"));
        
        if (isPlayer1) {
            player1ArtsPanel = artsPanel;
        } else {
            player2ArtsPanel = artsPanel;
        }
        
        panel.add(artsPanel, BorderLayout.CENTER);
        
        // Controls
        JPanel controlsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        
        if (isPlayer1) {
            // Martial art selection
            player1ArtsCombo = new JComboBox<>();
            controlsPanel.add(player1ArtsCombo);
            
            // Attack button
            attackButton = new JButton("Attack!");
            attackButton.setBackground(new Color(200, 100, 100));
            attackButton.setForeground(Color.WHITE);
            attackButton.addActionListener(new AttackActionListener());
            controlsPanel.add(attackButton);
            
            // Reassign button
            reassignP1Button = new JButton("Re Asignar");
            reassignP1Button.setBackground(new Color(150, 150, 100));
            reassignP1Button.addActionListener(e -> reassignPlayer1Arts());
            controlsPanel.add(reassignP1Button);
        } else {
            // Player 2 info
            JLabel aiLabel = new JLabel("AI Player", JLabel.CENTER);
            controlsPanel.add(aiLabel);
            
            controlsPanel.add(new JLabel("", JLabel.CENTER)); // Spacer
            
            // Reassign button for Player 2
            reassignP2Button = new JButton("Re Asignar");
            reassignP2Button.setBackground(new Color(150, 150, 100));
            reassignP2Button.addActionListener(e -> reassignPlayer2Arts());
            controlsPanel.add(reassignP2Button);
        }
        
        panel.add(controlsPanel, BorderLayout.SOUTH);
        return panel;
    }
    
    private void updateDisplay() {
        updatePlayerInfo();
        updateCombatLog();
    }
    
    private void updatePlayerInfo() {
        // Update health
        Player p1 = gameEngine.getPlayer1();
        Player p2 = gameEngine.getPlayer2();
        
        player1Health.setText(String.format("Health: %d/200", p1.getHealth()));
        player2Health.setText(String.format("Health: %d/200", p2.getHealth()));
        
        // Update health color
        if (p1.getHealth() < 50) {
            player1Health.setForeground(Color.RED);
        } else if (p1.getHealth() < 100) {
            player1Health.setForeground(Color.ORANGE);
        } else {
            player1Health.setForeground(Color.BLACK);
        }
        
        if (p2.getHealth() < 50) {
            player2Health.setForeground(Color.RED);
        } else if (p2.getHealth() < 100) {
            player2Health.setForeground(Color.ORANGE);
        } else {
            player2Health.setForeground(Color.BLACK);
        }
        
        // Update martial arts displays
        updateMartialArtsDisplay(p1, player1ArtsPanel);
        updateMartialArtsDisplay(p2, player2ArtsPanel);
        
        // Update combo box
        player1ArtsCombo.removeAllItems();
        for (MartialArt art : p1.getAssignedArts()) {
            player1ArtsCombo.addItem(art);
        }
        
        // Check game over
        if (gameEngine.isGameOver()) {
            Player winner = gameEngine.getWinner();
            showWinner(winner);
            attackButton.setEnabled(false);
        } else {
            attackButton.setEnabled(true);
        }
    }
    
    private void updateMartialArtsDisplay(Player player, JPanel artsPanel) {
        artsPanel.removeAll();
        
        for (MartialArt art : player.getAssignedArts()) {
            JPanel artPanel = new JPanel(new BorderLayout());
            artPanel.setBorder(BorderFactory.createEtchedBorder());
            
            JLabel nameLabel = new JLabel(art.getName(), JLabel.CENTER);
            nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            artPanel.add(nameLabel, BorderLayout.NORTH);
            
            JPanel strikesPanel = new JPanel(new GridLayout(3, 1, 2, 2));
            for (Strike strike : art.getStrikes()) {
                JLabel strikeLabel = new JLabel(String.format("%s (%d)", 
                    strike.getName(), strike.getDamage()), JLabel.CENTER);
                strikeLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
                strikesPanel.add(strikeLabel);
            }
            artPanel.add(strikesPanel, BorderLayout.CENTER);
            
            artsPanel.add(artPanel);
        }
        
        artsPanel.revalidate();
        artsPanel.repaint();
    }
    
    private void updateCombatLog() {
        StringBuilder logText = new StringBuilder();
        List<AttackResult> history = gameEngine.getCombatLog().getCombatHistory();
        
        // Show last 20 entries
        int start = Math.max(0, history.size() - 20);
        for (int i = start; i < history.size(); i++) {
            logText.append(history.get(i).toString()).append("");
        }
        
        combatLogArea.setText(logText.toString());
        combatLogArea.setCaretPosition(combatLogArea.getDocument().getLength());
    }
    
    private void showWinner(Player winner) {
        String message = winner != null ? 
            winner.getName() + " Wins!" : "It's a tie!";
        
        JOptionPane.showMessageDialog(this, message, "Game Over", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void startNewGame() {
        gameEngine.startNewGame();
        updateDisplay();
    }
    
    private void reassignPlayer1Arts() {
        gameEngine.reassignMartialArts(gameEngine.getPlayer1());
        updateDisplay();
    }
    
    private void reassignPlayer2Arts() {
        gameEngine.reassignMartialArts(gameEngine.getPlayer2());
        updateDisplay();
    }
    
    private class AttackActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameEngine.isGameOver()) return;
            
            // Player 1 attacks
            MartialArt selectedArt = (MartialArt) player1ArtsCombo.getSelectedItem();
            if (selectedArt != null) {
                gameEngine.executePlayerTurn(gameEngine.getPlayer1(), 
                    gameEngine.getPlayer2(), selectedArt);
                
                updateDisplay();
                
                if (!gameEngine.isGameOver()) {
                    // Player 2 counter-attacks after a short delay
                    Timer timer = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            List<MartialArt> p2Arts = gameEngine.getPlayer2().getAssignedArts();
                            MartialArt randomArt = p2Arts.get((int)(Math.random() * p2Arts.size()));
                            
                            gameEngine.executePlayerTurn(gameEngine.getPlayer2(), 
                                gameEngine.getPlayer1(), randomArt);
                            
                            updateDisplay();
                            ((Timer)e.getSource()).stop();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new GameUI().setVisible(true);
        });
    }
}