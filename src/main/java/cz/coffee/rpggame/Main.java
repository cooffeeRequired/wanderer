package cz.coffee.rpggame;

import cz.coffee.rpggame.services.GameEngine;
import cz.coffee.rpggame.utils.Console;
import cz.coffee.rpggame.utils.ConsoleColors;

public class Main {

    static {
        Console.addFormatter("game-loop", "[GameLoop]", ConsoleColors.CYAN);
    }

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.start();
    }
}
