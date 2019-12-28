package com.dreamkas.enums;

public enum ConfigFieldsEnum {

    KKT_MODE("dasdas"),
    FS_REPLACE_MODE("dasdas"),
    SHIFT_TIMER("dasdas"),

    KKT_REG_VERSION("dasdas"),
    ARTICLE("dasdas"),
    UUID("dasdas"),

    FS_NUMBER("dasdas"),
    FS_NUMBER_COUNT("dasdas"),
    FS_NUMBERS_TABLE("dasdas"),

    ORGANIZATION_NAME("dasdas"),
    CALCULATION_ADDRESS("dasdas"),
    CALCULATION_PLACE("dasdas"),
    ORGANIZATION_INN("dasdas"),
    ORGANIZATION_KPP("dasdas"),
    KKT_REG_NUM("dasdas"),
    KKT_PLANT_NUM("dasdas"),
    TAX_SYSTEMS("dasdas"),
    CUR_TAX_SYSTEM("dasdas"),
    ENCRYPTION_SIGN("dasdas"),
    EXCISABLE_SIGN("dasdas"),
    CLC_SERVICE_SIGN("dasdas"),
    GAMBLING_SIGN("dasdas"),
    LOTTERY_SIGN("dasdas"),
    PAYING_AGENT_SIGN("dasdas"),
    OFD_CHOOSE("dasdas"),
    OFD_INN("dasdas"),
    OFD_SERVER_ADDRESS("dasdas"),
    OFD_NAME("dasdas"),
    OFD_SERVER_PORT("dasdas"),
    CHECK_RECEIPT_ADDRESS("dasdas"),
    OFD_SERVER_IP("dasdas"),
    OPEN_SHIFT_DATE("dasdas"),
    AGENT_MASK("dasdas"),
    CURRENT_AGENT("dasdas"),

    IS_CABINET_ENABLE("dasdas"),

    FFD_KKT_VER("dasdas"),

    KKT_SIGNS("dasdas"),
    ADD_KKT_SIGNS("dasdas"),

    STAGE("dasdas"),
    INTERNET_RECIEPT("dasdas"),
    CONNECT_TO("dasdas"),
    CLOUD_REG_STATUS("dasdas"),
    CLOUD_TEL("dasdas"),
    CLOUD_PIN("dasdas"),
    CLOUD_ORG_FNS_NUM("dasdas"),
    CLOUD_KKT_FNS_NUM("dasdas"),
    CABINET_REG_EMAIL("dasdas"),
    LAST_FD_NUM("dasdas"),
    SHIFT_NUM("dasdas"),
    RECEIPT_NUM("dasdas"),
    IS_SHIFT_OPEN("dasdas"),
    IS_READY_TO_FISCAL("dasdas");


    private String discription;

    ConfigFieldsEnum(String discription) {
        this.discription = discription;
    }

    public String getDiscription() {
        return discription;
    }
}
