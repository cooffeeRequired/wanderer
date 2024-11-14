package cz.coffee.enums;

import lombok.Getter;

@Getter
public enum GameState {
    RUNNING("running", (byte) 0x0),
    STOPPED("stopped", (byte) 0x1),
    WIN("win", (byte) 0x2),
    GAME_OVER( "lose", (byte) 0x3),

    START("start", (byte) 0xf1),
    NEW_GAME("new_game", (byte) 0xf2),
    PLAYING("playing", (byte) 0xf03),
    INSTR("instructions", (byte) 0xf3),
    INITIALIZED("initialized", (byte) 0xf9),


    FIGHT("fight", (byte) 0xf88),
    BOSS_FIGHT("boss_fight", (byte) 0xf99),


    UNKNOWN("unknown", (byte) 0x00);

    final String stateText;
    final byte stateByte;

    GameState(String stateText, byte StateByte) {
        this.stateText = stateText;
        this.stateByte = StateByte;
    }
}
