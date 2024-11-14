package cz.coffee.components;

import cz.coffee.GameConfig;
import cz.coffee.controllers.EntityController;
import cz.coffee.controllers.GameController;
import cz.coffee.entities.Hero;
import cz.coffee.enums.GameState;
import cz.coffee.facades.GameEngine;
import cz.coffee.facades.Monster;
import cz.coffee.handlers.KeyEventHandler;
import cz.coffee.handlers.MovementHandler;
import cz.coffee.utils.Console;
import cz.coffee.utils.Location;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

import static cz.coffee.GameConfig.ITEMS;
import static cz.coffee.GameConfig.TILE_SIZE;
import static java.awt.Color.black;

public class Board extends JComponent implements Changeable, Runnable{
    private final FPSCounter fpsCounter;
    private final StartScreen startScreen;
    private final DialogScreen dialogScreen;
    private final FightUI fightUI;

    public GameState state;

    public static int LEVEL_MAP = 1;

    private final GameController controller;
    private final EntityController entitiesController;
    private final MovementHandler movementHandler;

    @Getter private Thread gameLoop;

    public Board(FPSCounter fpsCounter, Hero hero) {
        this.fpsCounter = fpsCounter;
        this.controller = new GameController(hero);
        this.entitiesController = new EntityController();
        this.movementHandler = new MovementHandler(this);

        this.startScreen = new StartScreen();
        this.dialogScreen = new DialogScreen();
        this.fightUI = new FightUI();
        setPreferredSize(new Dimension(GameConfig.GAME_WINDOW_WIDTH, GameConfig.GAME_WINDOW_HEIGHT));
        setVisible(true);
    }

    private void startGameLoop() {
        gameLoop = new Thread(this);
        gameLoop.start();
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D) graphics;

        switch (state) {
            case NEW_GAME -> startScreen.paintComponent(g2);
            case INSTR -> //noinspection TextBlockMigration
                    dialogScreen.drawWithText(g2, "INSTRUKCE:\n" +
                    "Pohybujte hrdinou pomocí šipek." +
                    "Když narazíte na příšeru, začne bitva. Uvidíte červený obdélník kolem vašeho hrdiny." +
                    "Útočte pomocí mezerníku. Nepřátelé vrátí úder, ale nikdy nezaútočí jako první." +
                    "Pro vstup do další úrovně musíte najít klíč tím, že zabijete příšeru, která ho má, a porazit bosse." +
                    "\n" +
                    "HP = Body zdraví\n" +
                    "DP = Body obrany\n" +
                    "SP = Body útoku");
            case INITIALIZED -> {
                this.startGameLoop();
                controller.startGame(g2);
                state = GameState.PLAYING;
            }
            case PLAYING -> {
                controller.play(g2);
                ITEMS.forEach(item -> item.update(g2));
                entitiesController.update(g2);

                var hero = this.controller.hero();
                var loc = hero.getLocation();

                if (GameConfig.SMOOTH_MOVING && KeyEventHandler.leftPressed || KeyEventHandler.rightPressed || KeyEventHandler.upPressed || KeyEventHandler.downPressed) {
                    if (KeyEventHandler.leftPressed) {
                        this.movementHandler.move(hero, new Location(loc.getX() - 2, loc.getY()));
                    } else if (KeyEventHandler.rightPressed) {
                        this.movementHandler.move(hero, new Location(loc.getX() + 2, loc.getY()));
                    } else if (KeyEventHandler.upPressed) {
                        this.movementHandler.move(hero, new Location(loc.getX(), loc.getY() - 2));
                    } else {
                        this.movementHandler.move(hero, new Location(loc.getX(), loc.getY() + 2));
                    }
                    hero.update(g2);
                }
                drawHeroStats(hero, g2);
            }
            case BOSS_FIGHT -> fightUI.paintComponent(g2);
        }

        fpsCounter.paintComponent(graphics);
        fpsCounter.incrementFrames();
    }

    @Override
    public void changeState(GameState state) {
        this.state = state;
        repaint();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / GameConfig.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        int timer = 0;
        int fpsCounter = 0;

        // Game loop
        while (gameLoop != null && gameLoop.isAlive()) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (int) (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                repaint();
                delta--;
                fpsCounter++;
            }

            if (timer >= 1_000_000_000) { // If one second has passed
                Console.printlnVia("game-loop", String.format("FPS: %d, draw: %f", fpsCounter, delta));
                Console.printlnVia("game-loop", String.format("STATE: %s", state.toString()));
                fpsCounter = 0;
                timer = 0;
            }
        }
    }

    public void drawHeroStats(Hero hero, Graphics2D g2) {
        int frameX = 2 * TILE_SIZE;
        int frameY = 690;
        int frameWidth = 6 * TILE_SIZE;
        int frameHeight = 50;

        Color c = new Color(255, 255, 255, 180); // frame
        g2.setColor(c);
        g2.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        g2.setColor(black); // stats
        g2.setFont(g2.getFont().deriveFont(16F));
        int textX = frameX + 53;
        int textY = frameY + 20;
        g2.drawString(
                "Hero"
                        + " (Level "
                        + hero.getLevel()
                        + ") HP: "
                        + hero.getCurrentHP()
                        + "/"
                        + hero.getMaxHP()
                        + " | DP: "
                        + hero.getDp()
                        + " | SP: "
                        + hero.getSp(),
                textX,
                textY);
    }

    public Graphics2D fight(Monster monster, Hero hero) {
        Graphics2D g2 = (Graphics2D) getGraphics();

        monster.setCurrentHP(0);
        hero.setCurrentHP(hero.getCurrentHP() - 4);

        return g2;
    }
}
