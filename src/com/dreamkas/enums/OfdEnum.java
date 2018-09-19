package com.dreamkas.enums;

public enum OfdEnum {

    AUTONOMIC(0, "", "", "", "", "", ""),
    FIRST_OFD(1, "7709364346", "Первый ОФД", "k-server.1-ofd.ru", "7777", "consumer.1-ofd.ru", "91.107.114.10"),
    TAXCOM(2, "7704211201", "Такском", "f1.taxcom.ru", "7777", "receipt.taxcom.ru", "193.0.214.11"),
    OFD_YA(3, "7728699517", "ОФД-Я", "connect.ofd-ya.ru", "7779", "ofd-ya.ru/open_service", "91.107.67.212"),
    SBIS_OFD(4, "7605016030", "\"Компания \"ТЕНЗОР\", ООО", "kkt.sbis.ru", "7777", "ofd.sbis.ru", "91.213.144.29"),
    KALUGA_ASTRAL(5, "4029017981", "Астрал ОФД", "ofd.astralnalog.ru", "7777", "https://ofd.astralnalog.ru/#find-ticket", "91.239.5.68"),
    KORUS_OFD(6, "7801392271", "КОРУС ОФД", "", "7777", "https://ofd.esphere.ru/CheckWebApp/fiscaldocsearch.zul", "92.38.2.200"),
    ELECTRO_EXPRESS(7, "7729633131", "Гарант ОФД", "ofd.garantexpress.ru", "30801", "", "141.101.203.186"),
    EVOTOR(8, "9715260691", "Платформа ОФД", "ofdp.platformaofd.ru", "21101", "lk.platformaofd.ru/web/noauth/cheque/search", "185.170.204.085"),
    KONTUR(9, "6663003127", "КОНТУР ОФД", "ofd.kontur.ru", "7777", "https://cash.kontur.ru/CashReceipt/Search", "46.17.204.250"),
    OFD_RU(16, "7841465198", "ООО ПЕТЕР-СЕРВИС Спецтехнологии", "gate.ofd.ru", "4000", "", "185.15.172.18"),
    OUTHER(32, "", "", "", "", "", ""),
    YANDEX(64, "7704358518", "ООО \"Яндекс ОФД\"", "kkt.ofd.yandex.net", "12345", "", "185.32.186.252"),
    DREAMKAS(96, "7802870820", "Дримкас ОФД", "ofd.dreamkas.ru", "21101", "https://ofd.dreamkas.ru/check", "185.241.176.4");

    private int value;
    private String inn;
    private String name;
    private String address;
    private String port;
    private String addressCheckCheque;
    private String ipServer;

    OfdEnum(int value, String inn, String name, String address, String port, String addressCheckCheque, String ipServer) {
        this.value = value;
        this.inn = inn;
        this.name = name;
        this.address = address;
        this.port = port;
        this.addressCheckCheque = addressCheckCheque;
        this.ipServer = ipServer;
    }

    public int getValue() {
        return value;
    }

    public String getInn() {
        return inn;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPort() {
        return port;
    }

    public String getAddressCheckCheque() {
        return addressCheckCheque;
    }

    public String getIpServer() {
        return ipServer;
    }
}
