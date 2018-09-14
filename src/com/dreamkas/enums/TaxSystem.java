package com.dreamkas.enums;

import java.util.ArrayList;

public enum TaxSystem {

    TOTAL(1, "Общая"),
    SIMPLIFIED(2, "Упрощенная"),
    SIMPLIFIED_REV_MIN_CON(4, "Упрощенная доход минус расход"),
    ENVD(8, "ЕНВД"),
    ESHN(16, "ЕСХН"),
    PATENT(32, "Патент");

    private int valueTaxSystem;
    private String nameTaxSystem;

    TaxSystem(int valueTaxSystem, String nameTaxSystem) {
        this.valueTaxSystem = valueTaxSystem;
        this.nameTaxSystem = nameTaxSystem;
    }

    public int getValueTaxSystem() {
        return valueTaxSystem;
    }

    public String getNameTaxSystem() {
        return nameTaxSystem;
    }

    /**
     * Метод принимает алгебраическую сумму значений СНО и парсит их в лист с СНО
     * @param sum - сумма
     * @return лист с закодированными СНО
     */
    public static ArrayList<TaxSystem> parseTaxSum(int sum){
        TaxSystem[] arrAllTax = TaxSystem.values();
        ArrayList<TaxSystem> resultList = new ArrayList<>();
        for (TaxSystem taxSystem : arrAllTax){
            if ((sum & taxSystem.getValueTaxSystem()) != 0){
                resultList.add(taxSystem);
            }
        }
        return resultList;
    }

    /**
     * Метод по числовому значаению определяет что это за СНО
     */
    public static TaxSystem parseTaxSystem(String valueTaxSystem){
        TaxSystem[] arrAllTax = TaxSystem.values();
        for (TaxSystem tax : arrAllTax){
            if (tax.getValueTaxSystem() == Integer.parseInt(valueTaxSystem)){
                return tax;
            }
        }
        return null;
    }
}
