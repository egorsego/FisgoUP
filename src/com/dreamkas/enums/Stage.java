package com.dreamkas.enums;

public enum Stage {

    LEARNING_MODE (0, "Учебный режим"),
    CHECKBOOK_MODE (1, "Режим чекопечатающей машины"),
    KKT_IS_REGISTR (3, "ККТ зарегистрирована");

    private int value;
    private String descriptions;

    Stage(int value, String descriptions) {
        this.value = value;
        this.descriptions = descriptions;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return descriptions;
    }
}
