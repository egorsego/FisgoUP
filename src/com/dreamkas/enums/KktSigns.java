package com.dreamkas.enums;

import java.util.ArrayList;

public enum KktSigns {

    EXCISABLE(1, "Продажа подакцизного товара"),
    GAMBLING (2, "Признак проведения азартных игр"),
    LOTERY(4, "Признак проведения лотерии"),
    PRINTER_IN_AUTO(8, "Признак установки принтера в автомате");

    private int value;
    private String name;

    KktSigns(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    /**
     * Метод принимает алгебраическую сумму значений признаков и возвращает лист с этими признаками
     */
    public static ArrayList<KktSigns> parseKktSignsSum(int sum){
        KktSigns[] arrAllSigns = KktSigns.values();
        ArrayList<KktSigns> resultList = new ArrayList<>();
        for (KktSigns sign : arrAllSigns){
            if ((sum & sign.getValue()) != 0){
                resultList.add(sign);
            }
        }
        return resultList;
    }
}
