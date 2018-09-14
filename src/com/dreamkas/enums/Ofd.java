package com.dreamkas.enums;

public enum Ofd {

    AUTONOMIC(0),
    FIRST_OFD(1),
    TAXCOM(2),
    OFD_YA(3),
    SBIS_OFD(4),
    KALUGA_ASTRAL(5),
    KORUS_OFD(6),
    ELECTRO_EXPRESS(7),
    EVOTOR(8),
    KONTUR(9),
    OFD_RU(16),
    OUTHER(32),
    YANDEX(64),
    DREAMKAS(96);

    private int value;

    Ofd(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
