# Martial Arts Fighting Game - Class Diagram

```mermaid
classDiagram
    class Strike {
        -String name
        -int damage
        -int healAmount
        -int bonusDamage
        +Strike(name, damage, healAmount, bonusDamage)
        +execute(Player attacker, Player defender) AttackResult
        +getName() String
        +getDamage() int
    }

    class MartialArt {
        <<abstract>>
        -String name
        -List~Strike~ strikes
        +MartialArt(name)
        +addStrike(Strike strike)
        +getStrikes() List~Strike~
        +getName() String
        +getRandomStrike() Strike
    }

    class Karate {
        +Karate()
    }

    class Boxing {
        +Boxing()
    }

    class Capoeira {
        +Capoeira()
    }

    class KungFu {
        +KungFu()
    }

    class Judo {
        +Judo()
    }

    class Taekwondo {
        +Taekwondo()
    }

    class Muaythai {
        +Muaythai()
    }

    class Jujitsu {
        +Jujitsu()
    }

    class Aikido {
        +Aikido()
    }

    class Krav {
        +Krav()
    }

    class Player {
        -String name
        -int health
        -List~MartialArt~ assignedArts
        -PlayerStrategy strategy
        +Player(name, strategy)
        +attack(Player opponent, MartialArt selectedArt) List~AttackResult~
        +takeDamage(int damage)
        +heal(int amount)
        +isAlive() boolean
        +assignMartialArts(List~MartialArt~ arts)
        +getHealth() int
        +getName() String
        +getAssignedArts() List~MartialArt~
    }

    class PlayerStrategy {
        <<interface>>
        +selectAttacks(List~MartialArt~ availableArts, MartialArt selectedArt) List~Strike~
    }

    class HumanPlayerStrategy {
        +selectAttacks(List~MartialArt~ availableArts, MartialArt selectedArt) List~Strike~
    }

    class AIPlayerStrategy {
        +selectAttacks(List~MartialArt~ availableArts, MartialArt selectedArt) List~Strike~
    }

    class AttackResult {
        -String attackerName
        -String strikeName
        -String martialArt
        -int damageDealt
        -int healingReceived
        -String timestamp
        +AttackResult(attackerName, strikeName, martialArt, damageDealt, healingReceived)
        +setMartialArt(String martialArt)
        +toString() String
        +getAttackerName() String
        +getStrikeName() String
        +getMartialArt() String
        +getDamageDealt() int
        +getHealingReceived() int
        +getTimestamp() String
    }

    class MartialArtFactory {
        <<Singleton>>
        -MartialArtFactory instance
        -List~MartialArt~ allMartialArts
        +getInstance() MartialArtFactory
        +getAllMartialArts() List~MartialArt~
        +getRandomMartialArts(int count) List~MartialArt~
        -createMartialArts()
    }

    class CombatLog {
        -List~AttackResult~ combatHistory
        +addAttack(AttackResult result)
        +addAttacks(List~AttackResult~ results)
        +getCombatHistory() List~AttackResult~
        +clear()
    }

    class GameEngine {
        -Player player1
        -Player player2
        -CombatLog combatLog
        -MartialArtFactory factory
        +GameEngine()
        +initializePlayers()
        +startNewGame()
        +executePlayerTurn(Player attacker, Player defender, MartialArt selectedArt) List~AttackResult~
        +isGameOver() boolean
        +getWinner() Player
        +reassignMartialArts(Player player)
        +getPlayer1() Player
        +getPlayer2() Player
        +getCombatLog() CombatLog
        +getFactory() MartialArtFactory
    }

    class GameUI {
        -GameEngine gameEngine
        -JLabel player1Health
        -JLabel player2Health
        -JTextArea combatLogArea
        -JComboBox~MartialArt~ player1ArtsCombo
        -JButton attackButton
        -JButton reassignP1Button
        -JButton reassignP2Button
        -JButton newGameButton
        -JPanel player1ArtsPanel
        -JPanel player2ArtsPanel
        +GameUI()
        -setupUI()
        -createPlayerPanel(Player player, boolean isPlayer1) JPanel
        -updateDisplay()
        -updatePlayerInfo()
        -updateMartialArtsDisplay(Player player, JPanel artsPanel)
        -updateCombatLog()
        -showWinner(Player winner)
        -startNewGame()
        -reassignPlayer1Arts()
        -reassignPlayer2Arts()
        -AttackActionListener()
        +main(String[] args)
    }

    MartialArt <|-- Karate
    MartialArt <|-- Boxing
    MartialArt <|-- Capoeira
    MartialArt <|-- KungFu
    MartialArt <|-- Judo
    MartialArt <|-- Taekwondo
    MartialArt <|-- Muaythai
    MartialArt <|-- Jujitsu
    MartialArt <|-- Aikido
    MartialArt <|-- Krav

    PlayerStrategy <|.. HumanPlayerStrategy
    PlayerStrategy <|.. AIPlayerStrategy

    Player --> PlayerStrategy
    Player --> MartialArt
    Strike --> AttackResult
    MartialArt --> Strike
    GameEngine --> Player
    GameEngine --> CombatLog
    GameEngine --> MartialArtFactory
    GameUI --> GameEngine
    CombatLog --> AttackResult
    MartialArtFactory --> MartialArt
```