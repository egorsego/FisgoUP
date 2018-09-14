package com.dreamkas.enums;

import java.util.ArrayList;

public enum Agents {

    NO_AGENTS(0, "Нет признаков агента"),
    BANK_PAYMENT_AGENT(1, "Банковский платежный агент"),
    BANK_PAYMENT_SUBAGENT(2, "Банковский платежный субагент"),
    PAYMENT_AGENT(4, "Платежный агент"),
    PAYMENT_SUBAGENT(8, "Платежный субагент"),
    ATTORNEY(16, "Поверенный "),
    KOMISSIONER(32, "Комиссионер "),
    AGENT(64, "Агент ");

    private int value;
    private String name;

    Agents(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ArrayList<Agents> parseAgentsSum(int sum) {
        if(sum == 0){
            return new ArrayList<Agents>(){{add(NO_AGENTS);}};
        }
        Agents[] arrAllAgents = Agents.values();
        ArrayList<Agents> resultList = new ArrayList<>();
        for (Agents agent : arrAllAgents) {
            if ((sum & agent.getValue()) != 0) {
                resultList.add(agent);
            }
        }
        return resultList;
    }

    public static Agents parseAgents(String valueAgent){
        Agents[] arrAllAgent = Agents.values();
        for (Agents agent : arrAllAgent){
            if (agent.getValue() == Integer.parseInt(valueAgent)){
                return agent;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
