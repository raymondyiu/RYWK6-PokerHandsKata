package org.example;

public enum WinEnum {
    Player1Win(2), Player2Win(2), Tie(0), NotMatch(-1);

    private final int value;

    WinEnum(int value) {
        this.value = value;
    }
}
