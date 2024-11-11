package cz.coffee.rpggame.utils;

import lombok.Getter;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.WeakHashMap;

public class Console {
    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;

    @Getter private static WeakHashMap<String, ConsoleFormatter> formatters = new WeakHashMap<>();

    public static void println(String prefix, ConsoleColors color, String str) {
        out.printf("%s [%s] %s%n%s", color, prefix, str, ConsoleColors.RESET);
    }

    public static void printlnVia(String id, String str) {
        ConsoleFormatter formatter = formatters.get(id);
        out.printf("%s %s %s%n%s", formatter.getColor(), formatter.getPrefix(), str, ConsoleColors.RESET);
    }

    public static void addFormatter(String id, String prefix, String color){
        formatters.put(id, new ConsoleFormatter(color, prefix));
    }
}
