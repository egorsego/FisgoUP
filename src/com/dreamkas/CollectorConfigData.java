package com.dreamkas;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.dreamkas.enums.Agents.*;
import static com.dreamkas.enums.Stage.CHECKBOOK_MODE;
import static com.dreamkas.enums.Stage.KKT_IS_REGISTR;
import static com.dreamkas.enums.Stage.LEARNING_MODE;
import static com.dreamkas.enums.TaxSystem.*;

public class CollectorConfigData {

    private ConfigCreator configCreator;
    private Map<String, String> mapChangedConfigData;

    public CollectorConfigData(ConfigCreator configCreator) {
        this.configCreator = configCreator;
        mapChangedConfigData = new HashMap<>();
        fillMap();
    }

    public Map<String, String> getMapChangedConfigData() {
        return mapChangedConfigData;
    }

    private void fillMap() {
        putKktMode();
        putReplaceMode();
        putShiftTimer();
        putArticle();
        putFsNumberCount();
        putUUID();
        putFsNumberTable();
        putOrganizationName();
        putputCalculationAddress();
        putOrganizationInn();
        putCalculationPlace();
        putKktRegNum();
        putKktPlantNum();
        putTaxSystems();
        putCurrentTaxSystem();
        putOfd();
        putOfdContent();
        putAgents();
        putCurrentAgent();
        putSigns();
        putAddSigns();
        putStage();
        putCabinet();

        putFfdKktVer();
    }

    /**
     * FFD_KKT_VER
     */
    private void putFfdKktVer() {
        mapChangedConfigData.put("FFD_KKT_VER", "2");
    }

    /**
     * IS_CABINET_ENABLE
     */
    private void putCabinet() {
        if (configCreator.checkBoxCabinetIsEnable.isSelected()) {
            mapChangedConfigData.put("IS_CABINET_ENABLE", "1");
            mapChangedConfigData.put("CABINET_REG_EMAIL", configCreator.textFieldEmailCabinet.getText());
        }
    }

    /**
     * STAGE
     */
    private void putStage() {
        String selectedItem = (String) configCreator.comboBoxStage.getSelectedItem();
        String val = "";
        if (selectedItem.equals(CHECKBOOK_MODE.getDescription())) {
            val = String.valueOf(CHECKBOOK_MODE.getValue());
        } else if (selectedItem.equals(KKT_IS_REGISTR.getDescription())) {
            val = String.valueOf(KKT_IS_REGISTR.getValue());
        } else if (selectedItem.equals(LEARNING_MODE.getDescription())) {
            val = String.valueOf(LEARNING_MODE.getValue());
        }

        mapChangedConfigData.put("STAGE", val);
    }

    /**
     * ADD_KKT_SIGNS
     */
    private void putAddSigns() {
        int val = 0;
        if (configCreator.checkBoxAddSign4.isSelected()) {
            val = val + 4;
        }
        if (configCreator.checkBoxAddSign2.isSelected()) {
            val = val + 2;
        }
        if (configCreator.checkBoxAddSign1.isSelected()) {
            val = val + 1;
        }
        if (configCreator.checkBoxAddSign16.isSelected()) {
            val = val + 16;
        }
        if (configCreator.checkBoxAddSign8.isSelected()) {
            val = val + 8;
        }
        if (configCreator.checkBoxAddSign32.isSelected()) {
            val = val + 32;
        }

        mapChangedConfigData.put("ADD_KKT_SIGNS", String.valueOf(val));
    }

    /**
     * KKT_SIGNS
     */
    private void putSigns() {
        int val = 0;
        if (configCreator.checkBoxSign4.isSelected()) {
            val = val + 4;
        }
        if (configCreator.checkBoxSign2.isSelected()) {
            val = val + 2;
        }
        if (configCreator.checkBoxSign1.isSelected()) {
            val = val + 1;
        }
        if (configCreator.checkBoxSign8.isSelected()) {
            val = val + 8;
        }

        mapChangedConfigData.put("KKT_SIGNS", String.valueOf(val));
    }

    /**
     * CURRENT_AGENT
     */
    private void putCurrentAgent() {
        String value = "";
        if(configCreator.comboBoxCurentAgent.getItemCount() == 0){
           mapChangedConfigData.put("CURRENT_AGENT", "0");
           return;
        }
        String selectedItem = (String) configCreator.comboBoxCurentAgent.getSelectedItem();
        if (selectedItem.equals(NO_AGENTS.getName())) {
            value = String.valueOf(NO_AGENTS.getValue());
        }
        if (selectedItem.equals(BANK_PAYMENT_AGENT.getName())) {
            value = String.valueOf(BANK_PAYMENT_AGENT.getValue());
        }
        if (selectedItem.equals(BANK_PAYMENT_SUBAGENT.getName())) {
            value = String.valueOf(BANK_PAYMENT_SUBAGENT.getValue());
        }
        if (selectedItem.equals(PAYMENT_AGENT.getName())) {
            value = String.valueOf(PAYMENT_AGENT.getValue());
        }
        if (selectedItem.equals(PAYMENT_SUBAGENT.getName())) {
            value = String.valueOf(PAYMENT_SUBAGENT.getValue());
        }
        if (selectedItem.equals(ATTORNEY.getName())) {
            value = String.valueOf(ATTORNEY.getValue());
        }
        if (selectedItem.equals(KOMISSIONER.getName())) {
            value = String.valueOf(KOMISSIONER.getValue());
        }
        if (selectedItem.equals(AGENT.getName())) {
            value = String.valueOf(AGENT.getValue());
        }

        mapChangedConfigData.put("CURRENT_AGENT", value);
    }

    /**
     * AGENT_MASK
     */
    private void putAgents() {
        int value = 0;
        if (configCreator.checkBoxAgents1.isSelected()) {
            value = value + 1;
        }
        if (configCreator.checkBoxAgents2.isSelected()) {
            value = value + 2;
        }
        if (configCreator.checkBoxAgents4.isSelected()) {
            value = value + 4;
        }
        if (configCreator.checkBoxAgents8.isSelected()) {
            value = value + 8;
        }
        if (configCreator.checkBoxAgents16.isSelected()) {
            value = value + 16;
        }
        if (configCreator.checkBoxAgents32.isSelected()) {
            value = value + 32;
        }
        if (configCreator.checkBoxAgents64.isSelected()) {
            value = value + 64;
        }

        mapChangedConfigData.put("AGENT_MASK", String.valueOf(value));
    }


    /**
     * OFD_INN *** OFD_SERVER_ADDRESS *** OFD_NAME  ** OFD_SERVER_PORT  ** CHECK_RECEIPT_ADDRESS  *** OFD_SERVER_IP
     */
    private void putOfdContent() {
        mapChangedConfigData.put("OFD_INN", configCreator.textFieldOFDinn.getText());
        mapChangedConfigData.put("OFD_SERVER_ADDRESS", configCreator.textFieldOFDserverAddress.getText());
        mapChangedConfigData.put("OFD_NAME", configCreator.textFieldOFDname.getText());
        mapChangedConfigData.put("OFD_SERVER_PORT", configCreator.textFieldlOFDport.getText());
        mapChangedConfigData.put("CHECK_RECEIPT_ADDRESS", configCreator.textFieldOFDcheckReceiptAddress.getText());
        mapChangedConfigData.put("OFD_SERVER_IP", configCreator.textFieldOFDipServer.getText());
    }

    /**
     * OFD_CHOOSE
     */
    private void putOfd() {
        String value = "";
        if (configCreator.radioButtonOFDauto.isSelected()) {
            value = "0";
        }
        if (configCreator.radioButtonOFDfirst.isSelected()) {
            value = "1";
        }
        if (configCreator.radioButtonOFDTaxcom.isSelected()) {
            value = "2";
        }
        if (configCreator.radioButtonOFDya.isSelected()) {
            value = "3";
        }
        if (configCreator.radioButtonOFDsbis.isSelected()) {
            value = "4";
        }
        if (configCreator.radioButtonOFDastral.isSelected()) {
            value = "5";
        }
        if (configCreator.radioButtonOFDkorus.isSelected()) {
            value = "6";
        }
        if (configCreator.radioButtonOFDexpress.isSelected()) {
            value = "7";
        }
        if (configCreator.radioButtonOFDevotor.isSelected()) {
            value = "8";
        }
        if (configCreator.radioButtonOFDcontur.isSelected()) {
            value = "9";
        }
        if (configCreator.radioButtonOFDru.isSelected()) {
            value = "16";
        }
        if (configCreator.radioButtonOFDouther.isSelected()) {
            value = "32";
        }
        if (configCreator.radioButtonOFDyandex.isSelected()) {
            value = "64";
        }
        if (configCreator.radioButtonDreamkas.isSelected()) {
            value = "96";
        }

        mapChangedConfigData.put("OFD_CHOOSE", String.valueOf(value));
    }

    /**
     * CUR_TAX_SYSTEM
     */
    private void putCurrentTaxSystem() {
        String selectedItem = (String) configCreator.comboBoxCurentTax.getSelectedItem();
        String val = "";

        if (selectedItem.equals(TOTAL.getNameTaxSystem())) {
            val = String.valueOf(TOTAL.getValueTaxSystem());
        }
        if (selectedItem.equals(SIMPLIFIED.getNameTaxSystem())) {
            val = String.valueOf(SIMPLIFIED.getValueTaxSystem());
        }
        if (selectedItem.equals(SIMPLIFIED_REV_MIN_CON.getNameTaxSystem())) {
            val = String.valueOf(SIMPLIFIED_REV_MIN_CON.getValueTaxSystem());
        }
        if (selectedItem.equals(ENVD.getNameTaxSystem())) {
            val = String.valueOf(ENVD.getValueTaxSystem());
        }
        if (selectedItem.equals(ESHN.getNameTaxSystem())) {
            val = String.valueOf(ESHN.getValueTaxSystem());
        }
        if (selectedItem.equals(PATENT.getNameTaxSystem())) {
            val = String.valueOf(PATENT.getValueTaxSystem());
        }
        mapChangedConfigData.put("CUR_TAX_SYSTEM", val);
    }

    /**
     * TAX_SYSTEMS
     */
    private void putTaxSystems() {
        int value = 0;
        if (configCreator.checkBoxTaxENVD.isSelected()) {
            value = value + ENVD.getValueTaxSystem();
        }
        if (configCreator.checkBoxTaxTotal.isSelected()) {
            value = value + TOTAL.getValueTaxSystem();
        }
        if (configCreator.checkBoxTaxESHN.isSelected()) {
            value = value + ESHN.getValueTaxSystem();
        }
        if (configCreator.checkBoxTaxPatent.isSelected()) {
            value = value + PATENT.getValueTaxSystem();
        }
        if (configCreator.checkBoxTaxSimplified.isSelected()) {
            value = value + SIMPLIFIED.getValueTaxSystem();
        }
        if (configCreator.checkBoxTaxSimplifiedRevMinCon.isSelected()) {
            value = value + SIMPLIFIED_REV_MIN_CON.getValueTaxSystem();
        }

        mapChangedConfigData.put("TAX_SYSTEMS", String.valueOf(value));
    }

    /**
     * KKT_PLANT_NUM
     */
    private void putKktPlantNum() {
        mapChangedConfigData.put("KKT_PLANT_NUM", configCreator.textFieldKktPluntNum.getText());
    }

    /**
     * KKT_REG_NUM
     */
    private void putKktRegNum() {
        mapChangedConfigData.put("KKT_REG_NUM", configCreator.textFieldKktRegNum.getText());
    }

    /**
     * CALCULATION_ADDRESS
     */
    private void putputCalculationAddress() {
        mapChangedConfigData.put("CALCULATION_ADDRESS", configCreator.textFieldCalculationAddress.getText());
    }

    /**
     * CALCULATION_PLACE
     */
    private void putCalculationPlace() {
        mapChangedConfigData.put("CALCULATION_PLACE", configCreator.textFieldCalculationPlace.getText());
    }

    /**
     * ORGANIZATION_INN
     */
    private void putOrganizationInn() {
        mapChangedConfigData.put("ORGANIZATION_INN", configCreator.textFieldOrganizationINN.getText());
    }

    /**
     * ORGANIZATION_NAME
     */
    private void putOrganizationName() {
        mapChangedConfigData.put("ORGANIZATION_NAME", configCreator.textFieldOrganizationName.getText());
    }

    /**
     * FS_NUMBERS_TABLE
     */
    private void putFsNumberTable() {
        int size = configCreator.modelListTableFn.getSize();
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < size; i++) {
            int count = i + 1;
            String value = configCreator.modelListTableFn.getElementAt(i).toString();
            jsonObject.put("FS NUMBER #" + count, value);
        }
        String value = jsonObject.toString();

        mapChangedConfigData.put("FS_NUMBERS_TABLE", value);
    }

    /**
     * UUID
     */
    private void putUUID() {
        mapChangedConfigData.put("UUID", configCreator.textFieldUUID.getText());
    }

    /**
     * FS_NUMBER_COUNT
     */
    private void putFsNumberCount() {
        mapChangedConfigData.put("FS_NUMBER_COUNT", String.valueOf(configCreator.spinnerFsNumberCount.getValue()));
    }

    /**
     * ARTICLE
     */
    private void putArticle() {
        mapChangedConfigData.put("ARTICLE", configCreator.textFieldArticle.getText());
    }

    /**
     * SHIFT_TIMER
     */
    private void putShiftTimer() {
        String value = "";
        if (configCreator.checkBoxShiftTimer.isSelected()) {
            value = "1";
        } else {
            value = "0";
        }
        mapChangedConfigData.put("SHIFT_TIMER", value);
    }

    /**
     * FS_REPLACE_MODE
     */
    private void putReplaceMode() {
        String value = "";
        if (configCreator.checkBoxFsReplaceMode.isSelected()) {
            value = "1";
        } else {
            value = "0";
        }
        mapChangedConfigData.put("FS_REPLACE_MODE", value);
    }

    /**
     * KKT_MODE
     */
    private void putKktMode() {
        String value = "";
        String comboBoxValue = (String) configCreator.comBoxKktMode.getSelectedItem();
        if (comboBoxValue.equals("Автономный режим")) {
            value = "1";
        } else {
            value = "0";
        }
        mapChangedConfigData.put("KKT_MODE", value);
    }


}
