package cz.coffee.utils;

import lombok.Data;

@Data
public class ConsoleFormatter {
    private String color;
    private String prefix;

    public ConsoleFormatter(String color, String prefix) {
        this.color = color;
        this.prefix = prefix;
    }
}
