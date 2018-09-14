package com.dreamkas.enums;

import java.util.ArrayList;

public enum AddKktSigns {

    NO_SIGNS(0, "Нет режимов"),
    ENCRYPTION(1, "Шифрование"),
    AUTONOMOUS(2, "Автономный режим"),
    AUTOMATIC(4, "Автоматический режим"),
    USE_IN_SERVICE(8, "Применение в сфере услуг"),
    BSO_OR_CHEQUE(16, "Режим БСО(1) иначе Режим чеков (0)"),
    USE_IN_INTERNET(32, " Применение в Интернет");

    private int value;
    private String description;

    AddKktSigns(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static ArrayList<AddKktSigns> parseAddKktSignsSum(int sum){
        if(sum == 0){
            return new ArrayList<AddKktSigns>(){{add(NO_SIGNS);}};
        }
        ArrayList<AddKktSigns> resultList = new ArrayList<>();
        AddKktSigns[] arrAllSigns = AddKktSigns.values();
        for (AddKktSigns sign : arrAllSigns){
            if ((sum & sign.getValue()) != 0){
                resultList.add(sign);
            }
        }
        return resultList;
    }
}
