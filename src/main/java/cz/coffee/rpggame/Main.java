package cz.coffee.rpggame;

import cz.coffee.rpggame.services.GameEngine;

public class Main {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.run();
    }
}
