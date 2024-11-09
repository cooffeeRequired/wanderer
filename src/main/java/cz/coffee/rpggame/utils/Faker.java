package cz.coffee.rpggame.utils;

public abstract class Faker {
    public static void fakeLoad()
    {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
