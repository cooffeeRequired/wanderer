package cz.coffee.rpggame.utils;

import java.io.PrintStream;
import java.util.Scanner;

public class Console {
    private final Scanner in;
    private final PrintStream out;

    public Console() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void println(String str) {
        out.println(str);
    }
}
