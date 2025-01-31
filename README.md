
# Wanderer Game 

>[!NOTE]
>Images will be added!


# Wanderer Game Project Enhancements - Developer Guide

*Prepared by: Developer of Wanderer Game Project*

---

## Introduction

> [!IMPORTANT]
> This document serves as a comprehensive guide to recent enhancements, optimizations, and new mechanics introduced in the Wanderer Game project. It covers performance improvements, dependency injection (DI) to reduce static variables, new gameplay features like a battle function and random map generator, visual enhancements with colored effects for entities, and the addition of a final door mechanic.

---

## 1. Dependency Injection and Reduction of Static Variables

> [!NOTE]
> **Objective:** To make the project more modular and testable, we’ve refactored several classes to reduce or remove static variables. This also introduces dependency injection (DI), allowing easier management of class dependencies.

### a. Refactoring `GameEngine`

> [!WARNING]
> **Previous Implementation:** `GameEngine` contained static references to `hero`, `board`, and `tiles`, making them globally accessible but inflexible.

> [!TIP]
> **Revised Code with DI:** Now, we’ve refactored these to be instance variables passed via the constructor, which makes `GameEngine` more modular and testable.

```java
public class GameEngine {
    private final Hero hero;
    private final Board board;
    private final WeakHashMap<String, PositionedImage> tiles;

    public GameEngine(Hero hero, Board board, WeakHashMap<String, PositionedImage> tiles) {
        this.hero = hero;
        this.board = board;
        this.tiles = tiles;
    }

    public void start() { /* ... */ }
}
```

In `Main`, we now instantiate these dependencies before passing them to `GameEngine`:

```java
public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero();
        Board board = new Board(new FPSCounter(), hero);
        WeakHashMap<String, PositionedImage> tiles = new WeakHashMap<>();
        GameEngine engine = new GameEngine(hero, board, tiles);
        engine.start();
    }
}
```

### b. Updating `MovementHandler` with DI

> [!NOTE]
> **Previous Implementation:** `MovementHandler` internally instantiated dependencies like `EntityHandler` and `ItemHandler`, limiting flexibility.

> [!TIP]
> **Revised Code with DI:** These dependencies are now injected via the constructor, making the `MovementHandler` more flexible and modular.

```java
public class MovementHandler {
    private final Board board;
    private final EntityHandler entityHandler;
    private final ItemHandler itemHandler;

    public MovementHandler(Board board, EntityHandler entityHandler, ItemHandler itemHandler) {
        this.board = board;
        this.entityHandler = entityHandler;
        this.itemHandler = itemHandler;
    }
}
```

> [!TIP]
> **Benefits of DI & Reduced Static Variables:**
> - **Improved Testability**: Classes like `GameEngine` and `MovementHandler` are easier to test in isolation.
> - **Modular Design**: DI facilitates dynamic object creation and allows flexible management of object dependencies.
> - **Maintainability**: Easier to make changes in one part of the code without affecting global state.

---

## 2. Performance and Code Optimization

### a. Optimize Collision Checking with Wall Matrix Using Set

> [!NOTE]
> The `wallMatrix` method is currently called for every movement check, which can slow down the game due to frequent lookups.

```java
private static final Set<Point> wallPositions = new HashSet<>();

private void initializeWallPositions() {
    boolean[][] walls = Floor.wallMatrix(Board.LEVEL_MAP);
    for (int i = 0; i < walls.length; i++) {
        for (int j = 0; j < walls[i].length; j++) {
            if (walls[i][j]) {
                wallPositions.add(new Point(i * TILE_SIZE, j * TILE_SIZE));
            }
        }
    }
}
```

> [!TIP]
> **Benefits:**
> - **Faster Lookups**: Reduces the time complexity of collision checks.
> - **Improved CPU Usage**: Reduces computation, especially beneficial for larger maps.

---

### b. Use Vector for Position Management and Tile Handling

> [!IMPORTANT]
> Using `Vector` for position management standardizes how positions are represented and enables better integration of mathematical operations.

```java
public class Tile {
    private final String type;
    private final Image image;
    private final Vector<Point> position;

    public Tile(String type, Image image, Vector<Point> position) {
        this.type = type;
        this.image = image;
        this.position = position;
    }
}
```

> [!TIP]
> **Benefits:**
> - **Consistency**: Positions are managed consistently across entities and objects.
> - **Future-Proofing**: Vector-based calculations make future modifications more straightforward.

---

### c. Reduce Console Output in Production

> [!NOTE]
> Writing to the console for every movement action is performance-intensive. Limiting this logging to debug mode reduces the I/O overhead.

```java
private static final boolean DEBUG_MODE = false;

if (DEBUG_MODE) {
    Console.printlnVia("mv-handler", String.format("entity{%s} at X: %d, Y: %d", entity.getUuid(), entity.getLocation().getX(), entity.getLocation().getY()));
}
```

> [!TIP]
> **Benefits:**
> - **Reduced I/O Load**: Reduces unnecessary console operations, optimizing production performance.

---

### d. Cache Location Checks Within Each Game Tick

> [!NOTE]
> Caching location checks within each game tick prevents redundant computations, especially beneficial in movement-heavy gameplay.

```java
private final Map<Point, Boolean> movementCache = new HashMap<>();

// In canMoveThere method
if (movementCache.containsKey(target)) {
    return movementCache.get(target);
}
boolean canMove = handleInteraction(entity);
movementCache.put(target, canMove);
return canMove;
```

> [!TIP]
> **Benefits:**
> - **Improved Efficiency**: Reduces redundant checks, speeding up movement calculations.

---

### e. Simplify Collision Handling by Combining Handlers

> [!IMPORTANT]
> Combining `EntityHandler` and `ItemHandler` reduces method calls, streamlining collision handling.

```java
private boolean handleInteraction(GameEntity entity) {
    return entityHandler.handle(entity) && itemHandler.handle(entity);
}
```

> [!TIP]
> **Benefits:**
> - **Reduced Method Calls**: Improves performance.
> - **Simplified Logic**: Centralized collision logic.

---

### f. Add Colored Effects for Entities

> [!NOTE]
> Colored effects enhance visual engagement, making entities more dynamic and visually distinct.

```java
private Color effectColor;

public void applyColorEffect(Color startColor, Color endColor, int duration) {
    Timer timer = new Timer(duration, e -> {
        // Logic to interpolate colors
    });
    timer.start();
}
```

> [!TIP]
> **Benefits:**
> - **Enhanced Visuals**: Entities become visually engaging.
> - **Dynamic Interactions**: Visual feedback indicates different entity states.

---

## 3. New Gameplay Mechanics

### a. Battle Function

```java
public class Hero extends GameEntity {
    public boolean battle(GameEntity monster) {
        while (this.health > 0 && monster.getHealth() > 0) {
            monster.takeDamage(this.attackPower);
            if (monster.getHealth() <= 0) return true;
            this.takeDamage(monster.getAttackPower());
        }
        return this.health > 0;
    }
}
```

### b. Random Map Generator

```java
public class MapGenerator {
    public void generateRandomMap(Board board) {
        for (int i = 0; i < 10; i++) {
            Monster monster = new Monster(randomLocation());
            board.addEntity(monster);
        }
        board.addEntity(new Monster(bossLocation(), "Boss"));
    }
}
```

### c. Final Door Mechanic

> [!IMPORTANT]
> Spawns a final door after defeating all monsters and the boss, signaling the map's completion.

```java
public class Board {
    public void checkAllMonstersDefeated(MapGenerator mapGen) {
        if (mapGen.getMonsters().stream().allMatch(monster -> monster.getHealth() <= 0) && mapGen.getBoss().getHealth() <= 0) {
            spawnFinalDoor();
        }
    }
}
```

---

### Closing Notes
> [!NOTE]
> With these enhancements, the Wanderer Game Project is now more robust, modular, and ready for future development. DI, in particular, has improved maintainability and flexibility, making the codebase cleaner and more resilient to change.
=======
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

<img src="https://ai-tomatica.cz/wp-content/uploads/2023/11/4373217_java_logo_logos_icon.png" style="width: 64px; heigh: 64px">

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

