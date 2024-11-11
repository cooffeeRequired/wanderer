package cz.coffee.rpggame.components;

import cz.coffee.rpggame.GameConfig;
import cz.coffee.rpggame.controllers.EntityController;
import cz.coffee.rpggame.controllers.GameController;
import cz.coffee.rpggame.enums.GameState;
import cz.coffee.rpggame.models.Hero;
import cz.coffee.rpggame.utils.Console;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

import static cz.coffee.rpggame.GameConfig.ITEMS;

public class Board extends JComponent implements Changeable, Runnable{
    private final FPSCounter fpsCounter;
    private final StartScreen startScreen;
    private final DialogScreen dialogScreen;

    public GameState state;

    public static int LEVEL_MAP = 1;

    private final GameController controller;
    private final EntityController entitiesController;

    @Getter private Thread gameLoop;

    public Board(FPSCounter fpsCounter, Hero hero) {
        this.fpsCounter = fpsCounter;
        this.controller = new GameController(hero);
        this.entitiesController = new EntityController();

        this.startScreen = new StartScreen();
        this.dialogScreen = new DialogScreen();
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
            }
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
}
