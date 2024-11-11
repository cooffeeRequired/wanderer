package cz.coffee.rpggame;

import cz.coffee.rpggame.facades.GameEngine;
import cz.coffee.rpggame.utils.Console;
import cz.coffee.rpggame.utils.ConsoleColors;

public class Main {

    static {
        Console.addFormatter("game-loop", "[GameLoop]", ConsoleColors.CYAN);
        Console.addFormatter("mv-handler", "[MovementHandler]", ConsoleColors.YELLOW);
        Console.addFormatter("items-c-spawn", "[ItemsController]", ConsoleColors.RED);
        Console.addFormatter("items-c", "[ItemsController]", ConsoleColors.GREEN);
    }

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.start();
    }
}
