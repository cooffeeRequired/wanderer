package cz.coffee;

import cz.coffee.facades.GameEngine;
import cz.coffee.utils.Console;
import cz.coffee.utils.ConsoleColors;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;


/**
 * Main class that initializes the console formatters and starts the game engine.
 */
public class Main {

   public static Font applicationFont;

    static {
        // Adds custom formatters for console logging with specific colors.
        Console.addFormatter("game-loop", "[GameLoop]", ConsoleColors.CYAN);
        Console.addFormatter("mv-handler", "[MovementHandler]", ConsoleColors.YELLOW);
        Console.addFormatter("items-c-spawn", "[ItemsController]", ConsoleColors.RED);
        Console.addFormatter("items-c", "[ItemsController]", ConsoleColors.GREEN);
        Console.addFormatter("item-handler", "[ItemHandler]", ConsoleColors.PURPLE_BOLD);

        InputStream is = Main.class.getResourceAsStream("/fonts/HighJakarta.ttf");
        try {
            assert is != null;
            Main.applicationFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The main method that serves as the entry point of the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.start();
    }
}