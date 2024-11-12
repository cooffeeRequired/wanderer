
# Wanderer Game 

>[!NOTE]
>Images will be added!

>[!IMPORTANT]
>**Projct state**: *WIP*

---

**Wanderer** is an engaging, tile-based RPG game developed in Java. Players explore procedurally generated maps, encounter and battle monsters, and aim to defeat a final boss to unlock the exit door. This project emphasizes modular design, dependency injection, and optimized performance for a smooth gameplay experience.

---

## Table of Contents

- [Game Features](#game-features)
- [Gameplay Mechanics](#gameplay-mechanics)
- [Technology Stack](#technology-stack)
- [Screenshots](#screenshots)
- [Getting Started](#getting-started)

---

## Game Features

- **Procedurally Generated Maps**: Every game map is unique, thanks to a random map generation system that includes walls, monsters, and a final boss.
- **Turn-Based Combat**: Engage monsters in a turn-based battle system that tests strategic thinking.
- **Dynamic Visual Effects**: Enjoy colored effects on entities, adding visual appeal and aiding gameplay clarity.
- **Victory Condition**: Defeat all monsters, including the boss, to unlock the final door and complete the level.
- **Optimized Performance**: With improvements like caching and optimized collision checks, the game runs smoothly even in complex scenarios.

---

## Gameplay Mechanics

### 1. Map Generation

Each game map is procedurally generated with random placements for walls, monsters, and the boss. The layout changes with each playthrough, creating unique challenges.

> [!NOTE]
> **Objective:** Defeat all monsters on the map, including the boss, to unlock the exit door.

### 2. Combat System

The turn-based combat system allows players to battle monsters in an engaging and strategic way.

1. **Attack**: The player can initiate an attack on a monster.
2. **Defend**: Monsters retaliate in each turn until one of the participants is defeated.

### 3. Final Door

Once all monsters and the boss are defeated, a final door appears on the map, signaling the level's completion.

---

## Technology Stack

![Java Logo](images/java_logo.png)

- **Language**: Java
- **Libraries**: Java Swing for the UI, custom classes for game logic and rendering.
- **Design Patterns**: Dependency Injection for better modularity and testability, singleton pattern in relevant sections.

---

## Screenshots

Here are some screenshots showcasing different aspects of the game:

### Gameplay
![Gameplay Screenshot](images/gameplay_screenshot.png)

### Combat
![Combat Screenshot](images/combat_screenshot.png)

### Victory
![Victory Screenshot](images/victory_screenshot.png)

---

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.

### Installation

1. **Clone the repository**:

    ```bash
    git clone https://github.com/coffeerequired/wanderer-game.git
    ```

2. **Navigate to the project directory**:

    ```bash
    cd wanderer-game
    ```

3. **Compile and Run the Game**:

    ```bash
    javac -d bin src/**/*.java
    java -cp bin Main
    ```

---

## Contributing

We welcome contributions! Feel free to open issues and submit pull requests.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

