package cz.coffee.rpggame.services;

import cz.coffee.rpggame.GameConfig;
import cz.coffee.rpggame.components.DialogScreen;
import cz.coffee.rpggame.components.FPSCounter;
import cz.coffee.rpggame.components.StartScreen;
import cz.coffee.rpggame.controllers.GameController;
import cz.coffee.rpggame.enums.GameState;
import cz.coffee.rpggame.models.Hero;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class Board extends JComponent implements Changeable {
    private final FPSCounter fpsCounter;
    private final StartScreen startScreen;
    private final DialogScreen dialogScreen;
    public GameState state;

    public static int heroX = 0;
    public static int heroY = 0;
    public static String heroDir = "img/hero-down.png";

    @Override
    public String toString() {
        return "Board{state=" + state + '}';
    }

    public static int LEVEL_MAP = 1;

    @Getter private Hero hero;

    private final GameController controller;

    public Board(FPSCounter fpsCounter, Hero hero) {
        this.fpsCounter = fpsCounter;
        this.hero = hero;
        this.controller = new GameController(hero); // Inicializace controlleru
        this.startScreen = new StartScreen();
        this.dialogScreen = new DialogScreen();
        setPreferredSize(new Dimension(GameConfig.GAME_WINDOW_WIDTH, GameConfig.GAME_WINDOW_HEIGHT));
        setVisible(true);
    }



    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (state == GameState.START || state == GameState.NEW_GAME) {
            startScreen.paintComponent(graphics);
        } else if (state == GameState.INSTR) {
            dialogScreen.drawWithText(graphics, "INSTRUKCE:\n" +
                    "Pohybujte hrdinou pomocí šipek." +
                    "Když narazíte na příšeru, začne bitva. Uvidíte červený obdélník kolem vašeho hrdiny." +
                    "Útočte pomocí mezerníku. Nepřátelé vrátí úder, ale nikdy nezaútočí jako první." +
                    "Pro vstup do další úrovně musíte najít klíč tím, že zabijete příšeru, která ho má, a porazit bosse." +
                    "\n" +
                    "HP = Body zdraví\n" +
                    "DP = Body obrany\n" +
                    "SP = Body útoku");
        } else if (state == GameState.PLAYING) {
            controller.startGame(graphics, GameConfig.TILES, GameConfig.TILE_SIZE);

            /*
                TODO HighestScore...
                TODO You Win...
                TODO You Lose...
            */



        }
        fpsCounter.paintComponent(graphics);
        fpsCounter.incrementFrames();

        if (hero != null) {
            System.out.println("[Board] Repainting board with hero: " + hero.getUuid()  + " at: " + heroX+ ", " + heroY + ", dir: " + heroDir);
        }
    }

    @Override
    public void changeState(GameState state) {
        this.state = state;
        repaint();
    }

}
