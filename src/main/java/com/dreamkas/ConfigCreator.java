package com.dreamkas;

import com.dreamkas.enums.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.sqlite.util.StringUtils;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.dreamkas.enums.Agents.*;
import static com.dreamkas.enums.Stage.CHECKBOOK_MODE;
import static com.dreamkas.enums.Stage.KKT_IS_REGISTR;
import static com.dreamkas.enums.Stage.LEARNING_MODE;
import static com.dreamkas.enums.TaxSystem.*;

public class ConfigCreator extends JFrame {

    public JCheckBox checkBoxFsReplaceMode;
    public JComboBox comBoxKktMode;
    public JCheckBox checkBoxShiftTimer;
    public JTextField textFieldArticle;
    public JTextField textFieldUUID;
    public JTextField textFieldFsNumber;
    public JLabel labelKktMode;
    public JPanel mainPanel;
    public JLabel labelFsReplaceMode;
    public JLabel labelShiftTimer;
    public JLabel labelArticle;
    public JLabel labelUUID;
    public JLabel labelFsNumber;
    public JSpinner spinnerFsNumberCount;
    public JTextField textFieldOrganizationName;
    public JTextField textFieldCalculationAddress;
    public JTextField textFieldCalculationPlace;
    public JTextField textFieldOrganizationINN;
    public JTextField textFieldKktRegNum;
    public JTextField textFieldKktPluntNum;
    public JList listFsNumberTable;
    public JTextField textFieldFsNumberTable;
    public JButton buttonFsNumberTable;
    public JCheckBox checkBoxTaxTotal;
    public JCheckBox checkBoxTaxSimplified;
    public JCheckBox checkBoxTaxSimplifiedRevMinCon;
    public JCheckBox checkBoxTaxENVD;
    public JCheckBox checkBoxTaxESHN;
    public JCheckBox checkBoxTaxPatent;
    public JRadioButton radioButtonOFDauto;
    public JRadioButton radioButtonOFDexpress;
    public JRadioButton radioButtonDreamkas;
    public JRadioButton radioButtonOFDcontur;
    public JRadioButton radioButtonOFDevotor;
    public JRadioButton radioButtonOFDTaxcom;
    public JRadioButton radioButtonOFDya;
    public JRadioButton radioButtonOFDsbis;
    public JRadioButton radioButtonOFDastral;
    public JRadioButton radioButtonOFDkorus;
    public JRadioButton radioButtonOFDru;
    public JRadioButton radioButtonOFDyandex;
    public JRadioButton radioButtonOFDfirst;
    public JRadioButton radioButtonOFDouther;
    public JTextField textFieldOFDinn;
    public JTextField textFieldOFDserverAddress;
    public JTextField textFieldOFDname;
    public JTextField textFieldlOFDport;
    public JTextField textFieldOFDcheckReceiptAddress;
    public JTextField textFieldOFDipServer;
    public JCheckBox checkBoxAgents0;
    public JCheckBox checkBoxAgents8;
    public JCheckBox checkBoxAgents1;
    public JCheckBox checkBoxAgents2;
    public JCheckBox checkBoxAgents4;
    public JCheckBox checkBoxAgents32;
    public JCheckBox checkBoxAgents64;
    public JCheckBox checkBoxAgents16;
    public JComboBox comboBoxCurentAgent;
    public JCheckBox checkBoxSign0;
    public JCheckBox checkBoxSign4;
    public JCheckBox checkBoxSign8;
    public JCheckBox checkBoxSign1;
    public JCheckBox checkBoxSign2;
    public JCheckBox checkBoxAddSign1;
    public JCheckBox checkBoxAddSign2;
    public JCheckBox checkBoxAddSign8;
    public JCheckBox checkBoxAddSign4;
    public JCheckBox checkBoxAddSign32;
    public JComboBox comboBoxStage;
    public JLabel labelFsNumberCount;
    public JLabel labelFsNumberTable;
    public JLabel labelOrganizationName;
    public JLabel labelCalculationAddress;
    public JLabel labelCalculationPlace;
    public JLabel labelOrganizationINN;
    public JLabel labelKktRegNum;
    public JLabel labelKktPlantNum;
    public JLabel labelTaxSystem;
    public JComboBox comboBoxCurentTax;
    public JLabel labelCurentTax;
    public JLabel labelOfd;
    public JLabel labelOFDinn;
    public JLabel labelOFDserverAddress;
    public JLabel labelOFDname;
    public JLabel labelOFDport;
    public JLabel labelOFDcheckReceiptAddress;
    public JLabel labelOFDipServer;
    public JLabel labelAgents;
    public JLabel labelCurentAgent;
    public JLabel labelKKTsign;
    public JLabel labelAddSign;
    public JCheckBox checkBoxAddSign0;
    public JLabel labelIsCabEnable;
    public JCheckBox checkBoxCabinetIsEnable;
    public JLabel labelArticleValidate;
    public JLabel labelValidateFsNumber;
    public JLabel labelMessageTableFn;
    public JButton buttonDeleteNumberFsTable;
    public JLabel labelValidateButtonTableFnNum;
    public JComboBox comboBoxCurrentFnNum;
    public JLabel messageValidateOrgName;
    public JLabel messageValidateAddressCalc;
    public JLabel messageValidatePlaceCalc;
    public JLabel messageValidateInnOrg;
    public JLabel messageValidateRegNum;
    public JLabel messageValidateKktPluntNum;
    public JLabel labelMessageTaxSystem;
    public JLabel labelMessageStage;
    public JButton closeButton;
    public JButton saveButton;
    public JCheckBox checkBoxAddSign16;
    public JLabel messageValidateUUID;
    public JLabel labelMessageOFD;
    public JLabel labelMessageOfdInn;
    public JLabel labelMessageOfdAddressSer;
    public JLabel labelMessageOfdName;
    public JLabel labelMessageOfdPort;
    public JLabel labelMessageOfdReceiptCheque;
    public JLabel labelMessageIpServer;
    public JTextField textFieldEmailCabinet;
    public JLabel labelMessageEmailCabinet;

    private JLabel labelMessageCountFN;
    private JLabel labelStage;
    private JButton generateUUIDButton;
    public boolean checkButtonSave;


    public Map<String, String> config;
    public int countFn;
    public DefaultListModel modelListTableFn;
    public final String HEAD_PLANT_NUM_DREAMKAS_F = "0496";

    public final String REG_EXP_UUID = "^[A-Fa-f0-9]{8}\\-[A-Fa-f0-9]{4}\\-4[A-Fa-f0-9]{3}\\-[A-Fa-f0-9]{4}\\-[A-Fa-f0-9]{2}" + HEAD_PLANT_NUM_DREAMKAS_F + "[A-Fa-f0-9]{6}$";
    public static final String REG_EXP_IP = "^(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[0-9]{2}|[0-9])(\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[0-9]{2}|[0-9])){3}$";

    Map<TaxSystem, JCheckBox> mapTaxAndCheckBox;
    Map<Agents, JCheckBox> mapAgentsAndCheckBox;

    private FrontEnd m_fe;
    private String ipCashbox;

    ConfigCreator(Map<String, String> config, FrontEnd m_fe, String ipCashbox) {
        this.m_fe = m_fe;
        this.ipCashbox = ipCashbox;
        this.config = config;
        mapTaxAndCheckBox = new HashMap<>();
        mapAgentsAndCheckBox = new HashMap<>();
        setTitle("Изменение конфига");
        $$$setupUI$$$();
        modelListTableFn = new DefaultListModel();
        listFsNumberTable.setModel(modelListTableFn);
        fillConfigCreator();
        JScrollPane scroll = new JScrollPane(mainPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        setContentPane(scroll);
        setBounds(30, 50, 850, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        addListenerCheckBoxes();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                m_fe.resetGui();
                dispose();
            }
        });
    }

    /**
     * Метод заполняет формы ConfigCreator значениями из конфига, который находится на кассе
     */
    private void fillConfigCreator() {
        tuneComBoxKktMode(config.get("KKT_MODE"));
        tuneCheckBoxFsReplaceMode(config.get("FS_REPLACE_MODE"));
        tuneShiftTimer(config.get("SHIFT_TIMER"));
        tuneArticle(config.get("ARTICLE"));
        tuneFsNumberCount(config.get("FS_NUMBER_COUNT"));
        tuneUUID(config.get("UUID"));
        tuneListFsNumberTable(config.get("FS_NUMBERS_TABLE"));
        tuneOrganizationName(config.get("ORGANIZATION_NAME"));
        tuneCalculationAddress(config.get("CALCULATION_ADDRESS"));
        tuneCalculationPlace(config.get("CALCULATION_PLACE"));
        tuneOrganizationInn(config.get("ORGANIZATION_INN"));
        tuneKktRegNum(config.get("KKT_REG_NUM"));
        tuneKktPlantNum(config.get("KKT_PLANT_NUM"));
        tuneTaxSystems(config.get("TAX_SYSTEMS"), config.get("CUR_TAX_SYSTEM"));
        tuneOfdRadioButton(config.get("OFD_CHOOSE"));
        tuneOfdTextFields(config.get("OFD_INN"),
                config.get("OFD_NAME"),
                config.get("OFD_SERVER_ADDRESS"),
                config.get("OFD_SERVER_PORT"),
                config.get("CHECK_RECEIPT_ADDRESS"),
                config.get("OFD_SERVER_IP"));
        tuneAgents(config.get("AGENT_MASK"), config.get("CURRENT_AGENT"));
        tuneKktSigns(config.get("KKT_SIGNS"));
        tuneStage(config.get("STAGE"));
        tuneAddSign(config.get("ADD_KKT_SIGNS"));
        tuneIsCabinetEnable(config.get("IS_CABINET_ENABLE"));

        generateUUIDButtonInit();
        saveButtonInit();
        closeButtonInit();
    }

    private void generateUUIDButtonInit() {
        generateUUIDButton.addActionListener(e -> {
            if (textFieldKktPluntNum.getText().isEmpty()) {
                return;
            } else if (!messageValidateKktPluntNum.getText().isEmpty()) {
                return;
            } else {
                StringBuffer uuid = new StringBuffer(UUID.randomUUID().toString());
                uuid.replace(26, 36, textFieldKktPluntNum.getText());
                textFieldUUID.setText(uuid.toString());
                messageValidateUUID.setText("");
            }
        });

        System.out.println();
    }

    private void closeButtonInit() {
        closeButton.addActionListener(e -> dispose());
    }

    private void tuneOfdTextFields(String inn, String name, String address, String port, String addressCheckCheque, String ipServer) {
        setOfdTextFieldEnable(false);
        if (radioButtonOFDouther.isSelected()) {
            setOfdTextFieldEnable(true);
        }
        addListenersOfdTextFields();
    }

    private void validateOfdValuesFromCashbox() {
        validateNumber(textFieldOFDinn, labelMessageOfdInn, 10);

        if (!regExpCheck(textFieldOFDipServer.getText(), REG_EXP_IP)) {
            labelMessageIpServer.setForeground(Color.RED);
            labelMessageIpServer.setText("<html>Неверный IP</html>");
        } else {
            labelMessageIpServer.setText("");
        }
        validateForEmpty(textFieldOFDinn, labelMessageOfdInn);
        validateForEmpty(textFieldOFDserverAddress, labelMessageOfdAddressSer);
        validateForEmpty(textFieldlOFDport, labelMessageOfdPort);
        validateForEmpty(textFieldOFDname, labelMessageOfdName);
        validateNumber(textFieldlOFDport, labelMessageOfdPort, 0);
        validateForEmpty(textFieldOFDcheckReceiptAddress, labelMessageOfdReceiptCheque);
    }

    private void addListenersOfdTextFields() {
        //валидация ИНН
        textFieldOFDinn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateNumber(textFieldOFDinn, labelMessageOfdInn, 10);
            }
        });

        //валидация IP
        textFieldOFDipServer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!regExpCheck(textFieldOFDipServer.getText(), REG_EXP_IP)) {
                    labelMessageIpServer.setForeground(Color.RED);
                    labelMessageIpServer.setText("<html>Неверный IP</html>");
                } else {
                    labelMessageIpServer.setText("");
                }
            }
        });

        //валидация сервера ip
        textFieldOFDserverAddress.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateForEmpty(textFieldOFDserverAddress, labelMessageOfdAddressSer);
            }
        });

        //валидация имени офд
        textFieldOFDname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateForEmpty(textFieldOFDname, labelMessageOfdName);
            }
        });

        //валидация порта
        textFieldlOFDport.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateNumber(textFieldlOFDport, labelMessageOfdPort, 0);
            }
        });

        textFieldOFDcheckReceiptAddress.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateForEmpty(textFieldOFDcheckReceiptAddress, labelMessageOfdReceiptCheque);
            }
        });
    }

    private void validateForEmpty(JTextField textField, JLabel labelMessage) {
        validateForEmptyField(textField, labelMessage);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateForEmptyField(textField, labelMessage);
            }
        });
    }

    private void validateForEmptyField(JTextField textField, JLabel labelMessage) {
        if (textField.getText().isEmpty()) {
            labelMessage.setForeground(Color.RED);
            labelMessage.setText("<html>Введите значение</html>");
        } else {
            labelMessage.setText("");
        }
    }

    private void addListenerCheckBoxes() {
        checkBoxSign0.addItemListener(itemEvent -> actionChangeKktSigns());
        checkBoxAddSign0.addItemListener(itemEvent -> actionChangeAddKktSigns());
        checkBoxAddSign2.addItemListener(itemEvent -> {
            checkBoxAddSign1.setSelected(false);

            if (checkBoxAddSign2.isSelected()) {
                checkBoxAddSign1.setEnabled(false);
            } else {
                checkBoxAddSign1.setEnabled(true);
            }
        });
        checkBoxAgents0.addItemListener(itemEvent -> {

            checkBoxAgents1.setSelected(false);
            checkBoxAgents2.setSelected(false);
            checkBoxAgents4.setSelected(false);
            checkBoxAgents8.setSelected(false);
            checkBoxAgents16.setSelected(false);
            checkBoxAgents32.setSelected(false);
            checkBoxAgents64.setSelected(false);

            if (checkBoxAgents0.isSelected()) {
                checkBoxAgents1.setEnabled(false);
                checkBoxAgents2.setEnabled(false);
                checkBoxAgents4.setEnabled(false);
                checkBoxAgents8.setEnabled(false);
                checkBoxAgents16.setEnabled(false);
                checkBoxAgents32.setEnabled(false);
                checkBoxAgents64.setEnabled(false);

                comboBoxCurentAgent.removeAllItems();
                comboBoxCurentAgent.setEnabled(false);
            } else {
                checkBoxAgents1.setEnabled(true);
                checkBoxAgents2.setEnabled(true);
                checkBoxAgents4.setEnabled(true);
                checkBoxAgents8.setEnabled(true);
                checkBoxAgents16.setEnabled(true);
                checkBoxAgents32.setEnabled(true);
                checkBoxAgents64.setEnabled(true);

                comboBoxCurentAgent.removeAllItems();
                comboBoxCurentAgent.setEnabled(true);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    static boolean regExpCheck(String value, String regExp) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    private void tuneUUID(String uuid) {
        textFieldUUID.setText(uuid);
        checkUUID(textFieldUUID, messageValidateUUID);
        validateUUID(textFieldUUID, messageValidateUUID);
    }

    private void validateUUID(JTextField textField, JLabel label) {
        textFieldUUID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                checkUUID(textField, label);
            }
        });
    }

    private void checkUUID(JTextField textField, JLabel label) {
        /* FIXME later for Kassa-F */
        if (!regExpCheck(textField.getText(), REG_EXP_UUID)) {
            label.setForeground(Color.RED);
            label.setText("Неверный формат UUID!");
        } else {
            label.setText("");
        }
    }

    private void actionChangeAddKktSigns() {

        checkBoxAddSign4.setSelected(false);
        checkBoxAddSign2.setSelected(false);
        checkBoxAddSign1.setSelected(false);
        checkBoxAddSign16.setSelected(false);
        checkBoxAddSign8.setSelected(false);
        checkBoxAddSign32.setSelected(false);

        if (checkBoxAddSign0.isSelected()) {

            checkBoxAddSign4.setEnabled(false);
            checkBoxAddSign2.setEnabled(false);
            checkBoxAddSign1.setEnabled(false);
            checkBoxAddSign16.setEnabled(false);
            checkBoxAddSign8.setEnabled(false);
            checkBoxAddSign32.setEnabled(false);
        } else {
            checkBoxAddSign4.setEnabled(true);
            checkBoxAddSign2.setEnabled(true);
            checkBoxAddSign16.setEnabled(true);
            checkBoxAddSign8.setEnabled(true);
            checkBoxAddSign32.setEnabled(true);
            checkBoxAddSign1.setEnabled(true);
        }
    }

    private void tuneAddSign(String addKktSigns) {
        fillWithCurrentConfigAddSigns(addKktSigns);
    }

    private void fillWithCurrentConfigAddSigns(String addKktSigns) {
        ArrayList<AddKktSigns> arrayAgents = AddKktSigns.parseAddKktSignsSum(Integer.parseInt(addKktSigns));
        for (AddKktSigns sign : arrayAgents) {
            switch (sign) {
                case NO_ADD_SIGNS: {
                    checkBoxAddSign0.setSelected(true);

                    checkBoxAddSign4.setSelected(false);
                    checkBoxAddSign2.setSelected(false);
                    checkBoxAddSign1.setSelected(false);
                    checkBoxAddSign16.setSelected(false);
                    checkBoxAddSign8.setSelected(false);
                    checkBoxAddSign32.setSelected(false);

                    checkBoxAddSign4.setEnabled(false);
                    checkBoxAddSign2.setEnabled(false);
                    checkBoxAddSign1.setEnabled(false);
                    checkBoxAddSign16.setEnabled(false);
                    checkBoxAddSign8.setEnabled(false);
                    checkBoxAddSign32.setEnabled(false);
                    break;
                }
                case AUTOMATIC: {
                    checkBoxAddSign4.setSelected(true);
                    break;
                }
                case AUTONOMOUS: {
                    checkBoxAddSign1.setSelected(false);
                    checkBoxAddSign1.setEnabled(false);

                    checkBoxAddSign2.setSelected(true);
                    break;
                }
                case ENCRYPTION: {
                    checkBoxAddSign1.setSelected(true);
                    break;
                }
                case BSO_OR_CHEQUE: {
                    checkBoxAddSign16.setSelected(true);
                    break;
                }
                case USE_IN_SERVICE: {
                    checkBoxAddSign8.setSelected(true);
                    break;
                }
                case USE_IN_INTERNET: {
                    checkBoxAddSign32.setSelected(true);
                    break;
                }
            }
        }
    }

    /**
     * Метод устанавливает значение подключения к кабинету и активирует поле для ввода email.
     *
     * @param isCabinetEnable - значение из конфига на кассе
     */
    private void tuneIsCabinetEnable(String isCabinetEnable) {
        textFieldEmailCabinet.setEnabled(false);

        //листенер для чекбокса
        checkBoxCabinetIsEnable.addActionListener(e -> {
            if (checkBoxCabinetIsEnable.isSelected()) {
                textFieldEmailCabinet.setEnabled(true);
                validateForEmpty(textFieldEmailCabinet, labelMessageEmailCabinet);
            } else {
                textFieldEmailCabinet.setEnabled(false);
                labelMessageEmailCabinet.setText("");
            }
        });

        if (isCabinetEnable.equals("1")) {
            checkBoxCabinetIsEnable.setSelected(true);
            textFieldEmailCabinet.setEnabled(true);

            //валидация значения из конфига
            validateForEmpty(textFieldEmailCabinet, labelMessageEmailCabinet);

            //валидация значения при изменении
            textFieldEmailCabinet.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    validateForEmpty(textFieldEmailCabinet, labelMessageEmailCabinet);
                }
            });

        }
    }

    private void checkStage(String selectedStage) {
        switch (selectedStage) {
            case "0":
            case "Учебный режим":
                setEnabledFnFields(false);
                comboBoxStage.setSelectedItem(LEARNING_MODE.getDescription());
                messageValidateRegNum.setText("");
                break;
            case "1":
            case "Режим чекопечатающей машины":
                setEnabledFnFields(false);
                comboBoxStage.setSelectedItem(CHECKBOOK_MODE.getDescription());
                messageValidateRegNum.setText("");
                break;
            case "2":
            case "ККТ зарегистрирована":
                validateFnTable();
                validateCountFn();
                setEnabledFnFields(true);

                comboBoxStage.setSelectedItem(KKT_IS_REGISTR.getDescription());
                validateNumber(textFieldKktRegNum, messageValidateRegNum, 16);
                break;
            default:
                JLabel error = new JLabel("Выберите режим...");
                comboBoxStage.addItem(error.getText());
                comboBoxStage.setSelectedItem(error.getText());
                labelMessageStage.setText("Выберете режим KKT");
                labelMessageStage.setForeground(Color.RED);
                break;
        }
    }

    private void setEnabledFnFields(boolean isEnable) {
        spinnerFsNumberCount.setEnabled(isEnable);
        textFieldFsNumberTable.setEnabled(isEnable);
        buttonFsNumberTable.setEnabled(isEnable);
        buttonDeleteNumberFsTable.setEnabled(isEnable);
        comboBoxCurrentFnNum.setEnabled(isEnable);

        if (!isEnable) {
            labelMessageCountFN.setText("");
            labelMessageTableFn.setText("");
            labelValidateButtonTableFnNum.setText("");
        }
    }


    private void tuneStage(String stage) {
        comboBoxStage.addItem(CHECKBOOK_MODE.getDescription());
        comboBoxStage.addItem(KKT_IS_REGISTR.getDescription());
        comboBoxStage.addItem(LEARNING_MODE.getDescription());

        checkStage(stage);

        comboBoxStage.addActionListener(e -> {
            JComboBox comboBox = (JComboBox) e.getSource();
            String selectedItem = (String) comboBox.getSelectedItem();
            if (!selectedItem.equals("Выберите режим...")) {
                labelMessageStage.setText("");
                comboBoxStage.removeItem("Выберите режим...");
            }
            checkStage(selectedItem);
        });
    }

    private void tuneAgents(String agentMask, String currentAgent) {
        getMapAgentsAndCheckBox();
        fillWithCurrentConfigAgentsValues(agentMask, currentAgent);

        mapAgentsAndCheckBox.forEach(((enumAgent, checkBoxAgents) -> checkBoxAgents.addActionListener(event -> {
            if (checkBoxAgents.isSelected()) {
                comboBoxCurentAgent.addItem(enumAgent.getName());
            } else {
                comboBoxCurentAgent.removeItem(enumAgent.getName());
            }
        })));

    }

    private void fillWithCurrentConfigAgentsValues(String agentMask, String currentAgent) {
        ArrayList<Agents> arrayAgents = Agents.parseAgentsSum(Integer.parseInt(agentMask));
        for (Agents agent : arrayAgents) {
            switch (agent) {
                case NO_AGENTS:
                    checkBoxAgents0.setSelected(true);

                    checkBoxAgents1.setSelected(false);
                    checkBoxAgents2.setSelected(false);
                    checkBoxAgents4.setSelected(false);
                    checkBoxAgents8.setSelected(false);
                    checkBoxAgents16.setSelected(false);
                    checkBoxAgents32.setSelected(false);
                    checkBoxAgents64.setSelected(false);

                    checkBoxAgents1.setEnabled(false);
                    checkBoxAgents2.setEnabled(false);
                    checkBoxAgents4.setEnabled(false);
                    checkBoxAgents8.setEnabled(false);
                    checkBoxAgents16.setEnabled(false);
                    checkBoxAgents32.setEnabled(false);
                    checkBoxAgents64.setEnabled(false);

                    comboBoxCurentAgent.removeAllItems();
                    break;
                case AGENT:
                    checkBoxAgents64.setSelected(true);
                    comboBoxCurentAgent.addItem(agent.getName());
                    break;
                case BANK_PAYMENT_AGENT:
                    checkBoxAgents1.setSelected(true);
                    comboBoxCurentAgent.addItem(agent.getName());
                    break;
                case BANK_PAYMENT_SUBAGENT:
                    checkBoxAgents2.setSelected(true);
                    comboBoxCurentAgent.addItem(agent.getName());
                    break;
                case PAYMENT_AGENT:
                    checkBoxAgents4.setSelected(true);
                    comboBoxCurentAgent.addItem(agent.getName());
                    break;
                case PAYMENT_SUBAGENT:
                    checkBoxAgents8.setSelected(true);
                    comboBoxCurentAgent.addItem(agent.getName());
                    break;
                case ATTORNEY:
                    checkBoxAgents16.setSelected(true);
                    comboBoxCurentAgent.addItem(agent.getName());
                    break;
                case KOMISSIONER:
                    checkBoxAgents32.setSelected(true);
                    comboBoxCurentAgent.addItem(agent.getName());
                    break;
            }

            Agents currentAgents = Agents.parseAgents(currentAgent);
            if (arrayAgents.contains(currentAgents)) {
                comboBoxCurentAgent.setSelectedItem(currentAgents.getName());
            }
        }
    }

    private Map<Agents, JCheckBox> getMapAgentsAndCheckBox() {
        mapAgentsAndCheckBox.put(NO_AGENTS, checkBoxAgents0);
        mapAgentsAndCheckBox.put(BANK_PAYMENT_AGENT, checkBoxAgents1);
        mapAgentsAndCheckBox.put(BANK_PAYMENT_SUBAGENT, checkBoxAgents2);
        mapAgentsAndCheckBox.put(PAYMENT_AGENT, checkBoxAgents4);
        mapAgentsAndCheckBox.put(PAYMENT_SUBAGENT, checkBoxAgents8);
        mapAgentsAndCheckBox.put(ATTORNEY, checkBoxAgents16);
        mapAgentsAndCheckBox.put(KOMISSIONER, checkBoxAgents32);
        mapAgentsAndCheckBox.put(AGENT, checkBoxAgents64);
        return mapAgentsAndCheckBox;
    }

    private void actionChangeKktSigns() {

        checkBoxSign4.setSelected(false);
        checkBoxSign2.setSelected(false);
        checkBoxSign1.setSelected(false);
        checkBoxSign8.setSelected(false);

        if (checkBoxSign0.isSelected()) {
            checkBoxSign4.setEnabled(false);
            checkBoxSign2.setEnabled(false);
            checkBoxSign1.setEnabled(false);
            checkBoxSign8.setEnabled(false);
        } else {
            checkBoxSign4.setEnabled(true);
            checkBoxSign2.setEnabled(true);
            checkBoxSign1.setEnabled(true);
            checkBoxSign8.setEnabled(true);
        }
    }

    private void tuneKktSigns(String kktSigns) {
        fillWithCurrentConfigSignValues(kktSigns);
    }

    private void fillWithCurrentConfigSignValues(String kktSigns) {
        ArrayList<KktSigns> arrayKktSigns = KktSigns.parseKktSignsSum(Integer.parseInt(kktSigns));
        for (KktSigns sign : arrayKktSigns) {
            switch (sign) {
                case NO_SIGNS: {
                    checkBoxSign0.setSelected(true);

                    checkBoxSign4.setSelected(false);
                    checkBoxSign2.setSelected(false);
                    checkBoxSign1.setSelected(false);
                    checkBoxSign8.setSelected(false);

                    checkBoxSign4.setEnabled(false);
                    checkBoxSign2.setEnabled(false);
                    checkBoxSign1.setEnabled(false);
                    checkBoxSign8.setEnabled(false);
                    break;
                }
                case LOTERY: {
                    checkBoxSign4.setSelected(true);
                    break;
                }
                case GAMBLING: {
                    checkBoxSign2.setSelected(true);
                    break;
                }
                case EXCISABLE: {
                    checkBoxSign1.setSelected(true);
                    break;
                }
                case PRINTER_IN_AUTO: {
                    checkBoxSign8.setSelected(true);
                    break;
                }
            }
        }
    }

    private void tuneOfdRadioButton(String ofd) {
        ButtonGroup ofdGroup = new ButtonGroup();
        ofdGroup.add(radioButtonOFDauto);
        ofdGroup.add(radioButtonOFDexpress);
        ofdGroup.add(radioButtonDreamkas);
        ofdGroup.add(radioButtonOFDcontur);
        ofdGroup.add(radioButtonOFDevotor);
        ofdGroup.add(radioButtonOFDTaxcom);
        ofdGroup.add(radioButtonOFDya);
        ofdGroup.add(radioButtonOFDsbis);
        ofdGroup.add(radioButtonOFDastral);
        ofdGroup.add(radioButtonOFDkorus);
        ofdGroup.add(radioButtonOFDru);
        ofdGroup.add(radioButtonOFDyandex);
        ofdGroup.add(radioButtonOFDfirst);
        ofdGroup.add(radioButtonOFDouther);


        setOfdFromConfig(ofd);
        addListenerRadioButton();

        setOfdFromConfig(ofd);

    }

    /**
     * Метод добавляет листенер на каждый радио-баттон
     */
    private void addListenerRadioButton() {
        //положить в массив
        ArrayList<JRadioButton> arrRadioButton = new ArrayList<>();
        arrRadioButton.add(radioButtonOFDauto);
        arrRadioButton.add(radioButtonOFDexpress);
        arrRadioButton.add(radioButtonDreamkas);
        arrRadioButton.add(radioButtonOFDcontur);
        arrRadioButton.add(radioButtonOFDevotor);
        arrRadioButton.add(radioButtonOFDTaxcom);
        arrRadioButton.add(radioButtonOFDya);
        arrRadioButton.add(radioButtonOFDsbis);
        arrRadioButton.add(radioButtonOFDastral);
        arrRadioButton.add(radioButtonOFDkorus);
        arrRadioButton.add(radioButtonOFDru);
        arrRadioButton.add(radioButtonOFDyandex);
        arrRadioButton.add(radioButtonOFDfirst);
        arrRadioButton.add(radioButtonOFDouther);

        //каждому элементу массива добавить листенер
        arrRadioButton.forEach(jRadioButton -> jRadioButton.addActionListener(e -> {
            if (radioButtonOFDouther.isSelected()) {
                clearTextFieldsOfd();
            }
            labelMessageOFD.setText("");
            fillOfdTextFields();
        }));
    }

    private void clearTextFieldsOfd() {
        textFieldOFDinn.setText("");
        textFieldOFDserverAddress.setText("");
        textFieldOFDname.setText("");
        textFieldlOFDport.setText("");
        textFieldOFDcheckReceiptAddress.setText("");
        textFieldOFDipServer.setText("");
    }

    /**
     * Метод заполняет текстовые поля ОФД в зависимости от выбранного ОФД в радио-баттонах и дизейблит их если выбран
     * автономный режим
     */
    private void fillOfdTextFields() {
        if (radioButtonOFDauto.isSelected()) {
            setContentOfdTextField(OfdEnum.AUTONOMIC);
            setOfdTextFieldEnable(false);
            clearMessageOfdLabels();
        } else if (radioButtonOFDexpress.isSelected()) {
            setOfdTextFieldEnable(false);
            setContentOfdTextField(OfdEnum.ELECTRO_EXPRESS);
            clearMessageOfdLabels();
        } else if (radioButtonDreamkas.isSelected()) {
            setOfdTextFieldEnable(false);
            setContentOfdTextField(OfdEnum.DREAMKAS);
            clearMessageOfdLabels();
        } else if (radioButtonOFDcontur.isSelected()) {
            setOfdTextFieldEnable(false);
            setContentOfdTextField(OfdEnum.KONTUR);
            clearMessageOfdLabels();
        } else if (radioButtonOFDevotor.isSelected()) {
            setOfdTextFieldEnable(false);
            setContentOfdTextField(OfdEnum.EVOTOR);
            clearMessageOfdLabels();
        } else if (radioButtonOFDTaxcom.isSelected()) {
            setOfdTextFieldEnable(false);
            setContentOfdTextField(OfdEnum.TAXCOM);
            clearMessageOfdLabels();
        } else if (radioButtonOFDya.isSelected()) {
            setOfdTextFieldEnable(false);
            setContentOfdTextField(OfdEnum.OFD_YA);
            clearMessageOfdLabels();
        } else if (radioButtonOFDsbis.isSelected()) {
            setOfdTextFieldEnable(false);
            setContentOfdTextField(OfdEnum.SBIS_OFD);
            clearMessageOfdLabels();
        } else if (radioButtonOFDastral.isSelected()) {
            setOfdTextFieldEnable(false);
            setContentOfdTextField(OfdEnum.KALUGA_ASTRAL);
            clearMessageOfdLabels();
        } else if (radioButtonOFDkorus.isSelected()) {
            setOfdTextFieldEnable(false);
            setContentOfdTextField(OfdEnum.KORUS_OFD);
            clearMessageOfdLabels();
        } else if (radioButtonOFDru.isSelected()) {
            setOfdTextFieldEnable(false);
            setContentOfdTextField(OfdEnum.OFD_RU);
            clearMessageOfdLabels();
        } else if (radioButtonOFDyandex.isSelected()) {
            setOfdTextFieldEnable(false);
            setContentOfdTextField(OfdEnum.YANDEX);
            clearMessageOfdLabels();
        } else if (radioButtonOFDfirst.isSelected()) {
            setOfdTextFieldEnable(false);
            setContentOfdTextField(OfdEnum.FIRST_OFD);
            clearMessageOfdLabels();
        } else if (radioButtonOFDouther.isSelected()) {
            setOfdTextFieldEnable(true);
            // setContentOfdTextField(OfdEnum.OUTHER);
            validateOfdValuesFromCashbox();
        }
    }

    private void clearMessageOfdLabels() {
        labelMessageOFD.setText("");
        labelMessageOfdInn.setText("");
        labelMessageOfdAddressSer.setText("");
        labelMessageOfdName.setText("");
        labelMessageOfdPort.setText("");
        labelMessageOfdReceiptCheque.setText("");
        labelMessageIpServer.setText("");
    }

    /**
     * Метод устанавливает enable/disable для текстовых полей офд
     */
    private void setOfdTextFieldEnable(boolean isEnable) {
        textFieldOFDinn.setEnabled(isEnable);
        textFieldOFDserverAddress.setEnabled(isEnable);
        textFieldOFDname.setEnabled(isEnable);
        textFieldlOFDport.setEnabled(isEnable);
        textFieldOFDcheckReceiptAddress.setEnabled(isEnable);
        textFieldOFDipServer.setEnabled(isEnable);
    }

    /**
     * Метод заполняет текстовые поля ОФД
     *
     * @param ofd - enum описывающий офд
     */
    private void setContentOfdTextField(OfdEnum ofd) {
        textFieldOFDinn.setText(ofd.getInn());
        textFieldOFDserverAddress.setText(ofd.getAddress());
        textFieldOFDname.setText(ofd.getName());
        textFieldlOFDport.setText(ofd.getPort());
        textFieldOFDcheckReceiptAddress.setText(ofd.getAddressCheckCheque());
        textFieldOFDipServer.setText(ofd.getIpServer());
    }

    private void setOfdFromConfig(String ofdFromConfig) {
        switch (ofdFromConfig) {
            case "0":
                radioButtonOFDauto.setSelected(true);
                break;
            case "1":
                radioButtonOFDfirst.setSelected(true);
                setContentOfdTextField(OfdEnum.FIRST_OFD);
                break;
            case "2":
                radioButtonOFDTaxcom.setSelected(true);
                setContentOfdTextField(OfdEnum.TAXCOM);
                break;
            case "3":
                radioButtonOFDya.setSelected(true);
                setContentOfdTextField(OfdEnum.OFD_YA);
                break;
            case "4":
                radioButtonOFDsbis.setSelected(true);
                setContentOfdTextField(OfdEnum.SBIS_OFD);
                break;
            case "5":
                radioButtonOFDastral.setSelected(true);
                setContentOfdTextField(OfdEnum.KALUGA_ASTRAL);
                break;
            case "6":
                radioButtonOFDkorus.setSelected(true);
                setContentOfdTextField(OfdEnum.KORUS_OFD);
                break;
            case "7":
                radioButtonOFDexpress.setSelected(true);
                setContentOfdTextField(OfdEnum.ELECTRO_EXPRESS);
                break;
            case "8":
                radioButtonOFDevotor.setSelected(true);
                setContentOfdTextField(OfdEnum.EVOTOR);
                break;
            case "16":
                radioButtonOFDru.setSelected(true);
                setContentOfdTextField(OfdEnum.OFD_RU);
                break;
            case "32":
                radioButtonOFDouther.setSelected(true);
                break;
            case "64":
                radioButtonOFDyandex.setSelected(true);
                setContentOfdTextField(OfdEnum.YANDEX);
                break;
            case "96":
                radioButtonDreamkas.setSelected(true);
                setContentOfdTextField(OfdEnum.DREAMKAS);
                break;
            default:
                labelMessageOFD.setForeground(Color.RED);
                labelMessageOFD.setText("<html>В конфиге на кассе<br>" +
                        "установлено неверное <br> значение ОФД.<br>" +
                        "Выберите необходимый ОФД <br> из списка</html>");
                return;
        }
        //checkKktModeAuto();
    }

//    private boolean checkKktModeAuto() {
//        String kktMode = (String) comBoxKktMode.getSelectedItem();
//        if (kktMode.equals("Автономный режим") && !radioButtonOFDauto.isSelected()) {
//            labelMessageOFD.setForeground(Color.RED);
//            labelMessageOFD.setText("Выбран автономный режим работы ККТ");
//            return false;
//        }
//        if (!kktMode.equals("Автономный режим") && radioButtonOFDauto.isSelected()) {
//            labelMessageOFD.setForeground(Color.RED);
//            labelMessageOFD.setText("Выбран неавтономный режим работы ККТ");
//            return false;
//        }
//        return true;
//    }


    /**
     * Заполнение данных СНО
     *
     * @param allTaxSystems - значение всех СНО на кассе
     * @param currentTaxStr - значение текущего
     */
    private void tuneTaxSystems(String allTaxSystems, String currentTaxStr) {
        getMapTaxAndCheckBox();
        fillWithCurrentConfigTaxValues(allTaxSystems, currentTaxStr);
        mapTaxAndCheckBox.forEach(((enumTax, checkBoxTax) -> checkBoxTax.addActionListener(event -> {
            if (checkBoxTax.isSelected()) {
                comboBoxCurentTax.addItem(enumTax.getNameTaxSystem());
            } else {
                comboBoxCurentTax.removeItem(enumTax.getNameTaxSystem());
            }
        })));
    }

    /**
     * Метод заполняет формы СНО значениями из текущего конфига, который был загружен
     *
     * @param allTaxSystems - Значение о всех СНО, полученное из конфига
     * @param currentTaxStr - значение текущей СНО
     */
    private void fillWithCurrentConfigTaxValues(String allTaxSystems, String currentTaxStr) {
        ArrayList<TaxSystem> arrayTaxSystems = TaxSystem.parseTaxSum(Integer.parseInt(allTaxSystems));
        for (TaxSystem tax : arrayTaxSystems) {
            switch (tax) {
                case ENVD:
                    checkBoxTaxENVD.setSelected(true);
                    comboBoxCurentTax.addItem(tax.getNameTaxSystem());
                    break;
                case TOTAL:
                    checkBoxTaxTotal.setSelected(true);
                    comboBoxCurentTax.addItem(tax.getNameTaxSystem());
                    break;
                case ESHN:
                    checkBoxTaxESHN.setSelected(true);
                    comboBoxCurentTax.addItem(tax.getNameTaxSystem());
                    break;
                case PATENT:
                    checkBoxTaxPatent.setSelected(true);
                    comboBoxCurentTax.addItem(tax.getNameTaxSystem());
                    break;
                case SIMPLIFIED:
                    checkBoxTaxSimplified.setSelected(true);
                    comboBoxCurentTax.addItem(tax.getNameTaxSystem());
                    break;
                case SIMPLIFIED_REV_MIN_CON:
                    checkBoxTaxSimplifiedRevMinCon.setSelected(true);
                    comboBoxCurentTax.addItem(tax.getNameTaxSystem());
                    break;
            }
        }
        TaxSystem currentTaxs = TaxSystem.parseTaxSystem(currentTaxStr);
        if (arrayTaxSystems.contains(currentTaxs)) {
            comboBoxCurentTax.setSelectedItem(currentTaxs.getNameTaxSystem());
        }
    }

    /**
     * Метод заполняет мапу СНО, для связывания чекбокса и енума характеризующий этот чекбокс.
     */
    private Map<TaxSystem, JCheckBox> getMapTaxAndCheckBox() {
        mapTaxAndCheckBox.put(ENVD, checkBoxTaxENVD);
        mapTaxAndCheckBox.put(TOTAL, checkBoxTaxTotal);
        mapTaxAndCheckBox.put(ESHN, checkBoxTaxESHN);
        mapTaxAndCheckBox.put(PATENT, checkBoxTaxPatent);
        mapTaxAndCheckBox.put(SIMPLIFIED, checkBoxTaxSimplified);
        mapTaxAndCheckBox.put(SIMPLIFIED_REV_MIN_CON, checkBoxTaxSimplifiedRevMinCon);
        return mapTaxAndCheckBox;
    }


    private void tuneKktPlantNum(String value) {
        textFieldKktPluntNum.setText(value);
        validateNumber(textFieldKktPluntNum, messageValidateKktPluntNum, 10);
        validatePlantNum(textFieldKktPluntNum, messageValidateKktPluntNum);
    }

    private void tuneKktRegNum(String value) {
        textFieldKktRegNum.setText(value);
        validateNumber(textFieldKktRegNum, messageValidateRegNum, 16);
    }

    private void tuneOrganizationInn(String value) {
        textFieldOrganizationINN.setText(value);
        validateNumber(textFieldOrganizationINN, messageValidateInnOrg, -1);
    }

    private void tuneCalculationPlace(String value) {
        textFieldCalculationPlace.setText(value);
        validateForEmpty(textFieldCalculationPlace, messageValidatePlaceCalc);
    }

    private void tuneCalculationAddress(String value) {
        textFieldCalculationAddress.setText(value);
        validateForEmpty(textFieldCalculationAddress, messageValidateAddressCalc);
    }

    private void tuneOrganizationName(String value) {
        textFieldOrganizationName.setText(value);
        validateForEmpty(textFieldOrganizationName, messageValidateOrgName);
    }

    /**
     * Установка значений таблицы с номерами фискальных накопителей
     *
     * @param value - значение из конфига
     */
    private void tuneListFsNumberTable(String value) {
        ArrayList<String> fnNumbersList = parserFnTable(value);


        //слушатель кнопки "добавить" в таблицу номеров ФН
        buttonFsNumberTable.addActionListener(e -> {
            //если нет сообщения об ошибке и поле ввода пустое
            if (labelValidateButtonTableFnNum.getText().equals("") & !textFieldFsNumberTable.getText().equals("")) {
                modelListTableFn.addElement(textFieldFsNumberTable.getText());
                comboBoxCurrentFnNum.addItem(textFieldFsNumberTable.getText());
                //если размер листа больше чем количество зарегистрированных фн-ов
                validateFnTable();
            }
        });
        //слушатель поля ввода для добавления нового номера в таблицу
        textFieldFsNumberTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateNumber(textFieldFsNumberTable, labelValidateButtonTableFnNum, 16);
            }
        });
//        if (fnNumbersList.isEmpty()) {
//            return;
//        }

        if (fnNumbersList.size() != Integer.parseInt(config.get("FS_NUMBER_COUNT"))) {
            labelMessageTableFn.setForeground(Color.RED);
            labelMessageTableFn.setText("<html>Количество номеров <br>в табл. не соответствует<br>" +
                    "значению поля <br>\"Количество зарегистр-ых ФН\".</html>");
        }

        for (String fnNumber : fnNumbersList) {
            modelListTableFn.addElement(fnNumber);
            comboBoxCurrentFnNum.addItem(fnNumber);
        }


        //слушатель кнопки "удалить" в таблицу номеров ФН
        buttonDeleteNumberFsTable.addActionListener(e -> {
            int selectedIndex = listFsNumberTable.getSelectedIndex();
            if (selectedIndex != -1) {
                modelListTableFn.remove(selectedIndex);
                comboBoxCurrentFnNum.removeItemAt(selectedIndex);
                if (modelListTableFn.size() != (Integer) spinnerFsNumberCount.getValue()) {
                    labelMessageTableFn.setForeground(Color.RED);
                    labelMessageTableFn.setText("<html>Количество номеров <br>в табл. не соответствует<br>" +
                            "значению поля <br>\"Количество зарегистр-ых ФН\".</html>");
                }
            }
        });

    }

    private void validateFnTable() {
        if (modelListTableFn.size() != (Integer) spinnerFsNumberCount.getValue()) {
            labelMessageTableFn.setForeground(Color.RED);
            labelMessageTableFn.setText("<html>Количество номеров <br>в табл. не соответствует<br>" +
                    "значению поля <br>\"Количество зарегистр-ых ФН\".</html>");
            textFieldFsNumberTable.setText("");
        } else {
            labelMessageTableFn.setText("");
            textFieldFsNumberTable.setText("");
        }
    }

    /**
     * Установка количества FN
     *
     * @param value - значение из конфига
     */
    private void tuneFsNumberCount(String value) {
        if (value.isEmpty() | value.equals("0")) {
            value = "0";
            labelMessageCountFN.setForeground(Color.RED);
            labelMessageCountFN.setText("Выберете количество ФН");
        }

        SpinnerModel sm = new SpinnerNumberModel(Integer.parseInt(value), 0, 100, 1);
        spinnerFsNumberCount.setModel(sm);
        spinnerFsNumberCount.addChangeListener(e -> {
            validateCountFn();

        });
    }

    private void validateCountFn() {
        countFn = (Integer) spinnerFsNumberCount.getValue();
        if (countFn == 0) {
            labelMessageCountFN.setForeground(Color.RED);
            labelMessageCountFN.setText("Выберете количество ФН");
        } else {
            labelMessageCountFN.setText("");
            if ((Integer) spinnerFsNumberCount.getValue() == modelListTableFn.size()) {
                labelMessageTableFn.setText("");
            } else {
                labelMessageTableFn.setForeground(Color.RED);
                labelMessageTableFn.setText("<html>Количество номеров <br>в табл. не соответствует<br>" +
                        "значению поля <br>\"Количество зарегистр-ых ФН\".</html>");
            }
        }
    }

    /**
     * Установка артикула кассы
     *
     * @param value - значение из конфига
     */
    private void tuneArticle(String value) {
        textFieldArticle.setText(value);
        validateNumber(textFieldArticle, labelArticleValidate, 13);
    }

    private void validateNumber(JTextField validatedTextField, JLabel messageLabel, int limitChars) {
        validateNumberField(validatedTextField, messageLabel, limitChars);
        validatedTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateNumberField(validatedTextField, messageLabel, limitChars);
            }
        });
    }

    private void validateRegNumField() {
        String stage = (String) comboBoxStage.getSelectedItem();
        boolean enable;
        try {
            enable = stage.equals(KKT_IS_REGISTR.getDescription());
        } catch (NullPointerException e) {
            return;
        }
        if (!enable) {
            System.out.println("NOT FISCAL STAGE");
            return;
        }
        System.out.println("FISCAL STAGE");
        // Подсчёт контрольной суммы
        String kktPlantNum = leftComplite(textFieldKktPluntNum.getText(), 20);
        String inn = leftComplite(textFieldOrganizationINN.getText(), 12);
        String fnsNum;
        try {
            fnsNum = textFieldKktRegNum.getText().substring(0, 10);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        int crcInit;
        try {
            crcInit = Integer.parseInt(textFieldKktRegNum.getText().substring(10));
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        if (crcInit != crc(fnsNum + inn + kktPlantNum)) {
            messageValidateRegNum.setText("Неверный регистрационный номер");
        } else {
            messageValidateRegNum.setText("");
        }
    }

    /**
     * @param num
     * @param digits
     * @return
     */
    public static String leftComplite(String text, int digits) {
        while (text.length() < digits) text = "0" + text;
        return text;
    }

    /**
     * @param text
     * @return
     */
    private int crc(String str) {
        int crc = 0xFFFF; // initial value
        int polynomial = 0x1021; // 0001 0000 0010 0001 (0, 5, 12)
        byte[] bytes = str.getBytes();
        for (byte b : bytes) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i) & 1) == 1);
                boolean c15 = ((crc >> 15 & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= polynomial;
            }
        }
        crc &= 0xffff;
        return crc;
    }

    private void validateNumberField(JTextField validatedTextField, JLabel messageLabel, int limitChars) {
        try {
            String[] arr = validatedTextField.getText().split("");
            for (String str : arr) {
                int i = Integer.parseInt(str);
                messageLabel.setText("");
            }
        } catch (NumberFormatException ex) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Недопустимое значение");
            return;
        }
        if (limitChars == 0) {
            return;
        }
        if (validatedTextField.equals(textFieldOrganizationINN)) {
            if (validatedTextField.getText().length() != 10 && validatedTextField.getText().length() != 12) {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Количество символов ИНН должно быть 10 или 12");
                return;
            }
        } else if (validatedTextField.getText().length() != limitChars) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Количество символов должно быть - " + limitChars);
            return;
        }
        if (validatedTextField.equals(textFieldOrganizationINN)
                || validatedTextField.equals(textFieldKktPluntNum)
                || validatedTextField.equals(textFieldKktRegNum)) {
            validateRegNumField();
        }
    }

    /**
     * Проверка заводского номера
     *
     * @param validatedTextField - форма ввода ЗН ККТ
     * @param messageLabel       - label отображения ошибки
     */
    private void validatePlantNum(JTextField validatedTextField, JLabel messageLabel) {
        validateKktPlantNum(validatedTextField, messageLabel);
        validatedTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {

                validateKktPlantNum(validatedTextField, messageLabel);
            }
        });
    }

    private void validateKktPlantNum(JTextField validatedTextField, JLabel messageLabel) {
        String headPlantNum = "";

        try {
            headPlantNum = validatedTextField.getText().substring(0, 4);
        } catch (StringIndexOutOfBoundsException ignored) {

        }

        if (!headPlantNum.equals(HEAD_PLANT_NUM_DREAMKAS_F)) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Номер должен начинаться с " + HEAD_PLANT_NUM_DREAMKAS_F);
        }
    }

    /**
     * Установка значения "Таймер смены"
     *
     * @param value - значение из конфига
     */
    private void tuneShiftTimer(String value) {
        if (value.equals("1")) {
            checkBoxShiftTimer.setSelected(true);
        }
    }

    /**
     * Установка значения "Режим замены ФН
     *
     * @param value - значение из конфига
     */
    private void tuneCheckBoxFsReplaceMode(String value) {
        if (value.equals("1")) {
            checkBoxFsReplaceMode.setSelected(true);
        }
    }

    /**
     * Установка значения "Режим работы кассы"
     *
     * @param value - значение из конфига
     */
    private void tuneComBoxKktMode(String value) {
        comBoxKktMode.addItem("Автономный режим");
        comBoxKktMode.addItem("Неавтономный режим");

        if (value.equals("1")) {
            comBoxKktMode.setSelectedItem("Автономный режим");
        } else {
            if (value.equals("0")) {
                comBoxKktMode.setSelectedItem("Неавтономный режим");
            } else {
                comBoxKktMode.addItem("Выберите режим...");
                comBoxKktMode.setSelectedItem("Выберите режим...");
            }
        }
    }

    /**
     * Метод парсинга JSON. Используется для получения из поля конфига FS_NUMBERS_TABLE номеров фискальных накопителей
     *
     * @param value - значение поля из конфига
     * @return ArrayList<String> - номера ФН-ов
     */
    private ArrayList<String> parserFnTable(String value) {
        try {
            JSONObject fnTable = new JSONObject(value);
            ArrayList<String> fnNumbersList = new ArrayList<>();
            Iterator<String> keys = fnTable.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                fnNumbersList.add(fnTable.getString(key));
            }
            return fnNumbersList;
        } catch (JSONException e) {
            return new ArrayList<String>();
        }

    }

    private void saveButtonInit() {
        saveButton.addActionListener(e -> {
            checkButtonSave = false;
            saveButton.setEnabled(false);
            saveConfig();
        });
        checkButtonSave = true;
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> saveButtonInitEx());
        return;
    }

    private void saveButtonInitEx() {
        while (checkButtonSave) {
            saveButtonSetEnable();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveButtonSetEnable() {
        boolean saveButtonEnable =
                labelMessageTableFn.getText().isEmpty()
                        && messageValidateUUID.getText().isEmpty()
                        && labelArticleValidate.getText().isEmpty()
                        && labelMessageStage.getText().isEmpty()
                        && labelValidateButtonTableFnNum.getText().isEmpty()
                        && labelMessageCountFN.getText().isEmpty()
                        && labelValidateFsNumber.getText().isEmpty()
                        && messageValidateOrgName.getText().isEmpty()
                        && messageValidateAddressCalc.getText().isEmpty()
                        && messageValidatePlaceCalc.getText().isEmpty()
                        && messageValidateInnOrg.getText().isEmpty()
                        && messageValidateRegNum.getText().isEmpty()
                        && messageValidateKktPluntNum.getText().isEmpty()
                        && labelMessageTaxSystem.getText().isEmpty()
                        && labelMessageOFD.getText().isEmpty()
                        && labelMessageEmailCabinet.getText().isEmpty()
                        && labelMessageOfdInn.getText().isEmpty()
                        && labelMessageOfdAddressSer.getText().isEmpty()
                        && labelMessageOfdName.getText().isEmpty()
                        && labelMessageOfdPort.getText().isEmpty()
                        && labelMessageOfdReceiptCheque.getText().isEmpty()
                        && labelMessageIpServer.getText().isEmpty();

        if (saveButtonEnable) {
            saveButton.setEnabled(true);
        } else {
            saveButton.setEnabled(false);
        }
    }

    private void saveConfig() {
        CollectorConfigData collectorConfigData = new CollectorConfigData(this);
        Map<String, String> map = collectorConfigData.getMapChangedConfigData();
        m_fe.uploadConfig(ipCashbox, map);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(34, 7, new Insets(0, 0, 0, 0), -1, -1));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(33, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        labelFsReplaceMode = new JLabel();
        labelFsReplaceMode.setText("Режим замены ФН");
        mainPanel.add(labelFsReplaceMode, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxFsReplaceMode = new JCheckBox();
        checkBoxFsReplaceMode.setText("");
        mainPanel.add(checkBoxFsReplaceMode, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comBoxKktMode = new JComboBox();
        mainPanel.add(comBoxKktMode, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelShiftTimer = new JLabel();
        labelShiftTimer.setText("Смена в ФН открыта");
        mainPanel.add(labelShiftTimer, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxShiftTimer = new JCheckBox();
        checkBoxShiftTimer.setText("");
        mainPanel.add(checkBoxShiftTimer, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelArticle = new JLabel();
        labelArticle.setText("Артикул");
        mainPanel.add(labelArticle, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldArticle = new JTextField();
        textFieldArticle.setText("");
        mainPanel.add(textFieldArticle, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelArticleValidate = new JLabel();
        labelArticleValidate.setBackground(new Color(-14606047));
        labelArticleValidate.setForeground(new Color(-14606047));
        labelArticleValidate.setText("");
        mainPanel.add(labelArticleValidate, new com.intellij.uiDesigner.core.GridConstraints(4, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelFsNumberCount = new JLabel();
        labelFsNumberCount.setText("Количество зарегистрированных ФН");
        mainPanel.add(labelFsNumberCount, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        spinnerFsNumberCount = new JSpinner();
        mainPanel.add(spinnerFsNumberCount, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelFsNumberTable = new JLabel();
        labelFsNumberTable.setText("Таблица номеров ФН");
        mainPanel.add(labelFsNumberTable, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelOrganizationName = new JLabel();
        labelOrganizationName.setText("Название организации");
        mainPanel.add(labelOrganizationName, new com.intellij.uiDesigner.core.GridConstraints(10, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldOrganizationName = new JTextField();
        mainPanel.add(textFieldOrganizationName, new com.intellij.uiDesigner.core.GridConstraints(10, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelCalculationAddress = new JLabel();
        labelCalculationAddress.setText("Адрес расчетов");
        mainPanel.add(labelCalculationAddress, new com.intellij.uiDesigner.core.GridConstraints(11, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelCalculationPlace = new JLabel();
        labelCalculationPlace.setText("Место расчетов");
        mainPanel.add(labelCalculationPlace, new com.intellij.uiDesigner.core.GridConstraints(12, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelOrganizationINN = new JLabel();
        labelOrganizationINN.setText("ИНН организации");
        mainPanel.add(labelOrganizationINN, new com.intellij.uiDesigner.core.GridConstraints(13, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelKktRegNum = new JLabel();
        labelKktRegNum.setText("Регистрационный номер ККТ");
        mainPanel.add(labelKktRegNum, new com.intellij.uiDesigner.core.GridConstraints(14, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelKktPlantNum = new JLabel();
        labelKktPlantNum.setText("Заводской номер ККТ");
        mainPanel.add(labelKktPlantNum, new com.intellij.uiDesigner.core.GridConstraints(15, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelTaxSystem = new JLabel();
        labelTaxSystem.setText("Налоговые системы");
        mainPanel.add(labelTaxSystem, new com.intellij.uiDesigner.core.GridConstraints(17, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelCurentTax = new JLabel();
        labelCurentTax.setText("Текущая налоговая система");
        mainPanel.add(labelCurentTax, new com.intellij.uiDesigner.core.GridConstraints(18, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelOFDport = new JLabel();
        labelOFDport.setText("ОФД Порт");
        mainPanel.add(labelOFDport, new com.intellij.uiDesigner.core.GridConstraints(23, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelOFDname = new JLabel();
        labelOFDname.setText("ОФД Наименование");
        mainPanel.add(labelOFDname, new com.intellij.uiDesigner.core.GridConstraints(22, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelOFDserverAddress = new JLabel();
        labelOFDserverAddress.setText("ОФД Адрес сервера");
        mainPanel.add(labelOFDserverAddress, new com.intellij.uiDesigner.core.GridConstraints(21, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelOFDinn = new JLabel();
        labelOFDinn.setText("ОФД ИНН");
        mainPanel.add(labelOFDinn, new com.intellij.uiDesigner.core.GridConstraints(20, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelOfd = new JLabel();
        labelOfd.setText("ОФД");
        mainPanel.add(labelOfd, new com.intellij.uiDesigner.core.GridConstraints(19, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelAddSign = new JLabel();
        labelAddSign.setText("Дополнительные признаки ККТ");
        mainPanel.add(labelAddSign, new com.intellij.uiDesigner.core.GridConstraints(29, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelAgents = new JLabel();
        labelAgents.setText("Агенты");
        mainPanel.add(labelAgents, new com.intellij.uiDesigner.core.GridConstraints(26, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelOFDcheckReceiptAddress = new JLabel();
        labelOFDcheckReceiptAddress.setText("ОФД Адрес проверки чека");
        mainPanel.add(labelOFDcheckReceiptAddress, new com.intellij.uiDesigner.core.GridConstraints(24, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelOFDipServer = new JLabel();
        labelOFDipServer.setText("ОФД IP сервера");
        mainPanel.add(labelOFDipServer, new com.intellij.uiDesigner.core.GridConstraints(25, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelCurentAgent = new JLabel();
        labelCurentAgent.setText("Текущий агент");
        mainPanel.add(labelCurentAgent, new com.intellij.uiDesigner.core.GridConstraints(27, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelKKTsign = new JLabel();
        labelKKTsign.setText("Признаки ККТ");
        mainPanel.add(labelKKTsign, new com.intellij.uiDesigner.core.GridConstraints(28, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelIsCabEnable = new JLabel();
        labelIsCabEnable.setText("Подключение к Кабинету");
        mainPanel.add(labelIsCabEnable, new com.intellij.uiDesigner.core.GridConstraints(30, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldCalculationAddress = new JTextField();
        mainPanel.add(textFieldCalculationAddress, new com.intellij.uiDesigner.core.GridConstraints(11, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldCalculationPlace = new JTextField();
        mainPanel.add(textFieldCalculationPlace, new com.intellij.uiDesigner.core.GridConstraints(12, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldOrganizationINN = new JTextField();
        mainPanel.add(textFieldOrganizationINN, new com.intellij.uiDesigner.core.GridConstraints(13, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldKktRegNum = new JTextField();
        mainPanel.add(textFieldKktRegNum, new com.intellij.uiDesigner.core.GridConstraints(14, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldKktPluntNum = new JTextField();
        mainPanel.add(textFieldKktPluntNum, new com.intellij.uiDesigner.core.GridConstraints(15, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 2, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        listFsNumberTable = new JList();
        panel1.add(listFsNumberTable, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        textFieldFsNumberTable = new JTextField();
        panel1.add(textFieldFsNumberTable, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        buttonFsNumberTable = new JButton();
        buttonFsNumberTable.setText("Добавить");
        panel1.add(buttonFsNumberTable, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonDeleteNumberFsTable = new JButton();
        buttonDeleteNumberFsTable.setText("Удалить");
        panel1.add(buttonDeleteNumberFsTable, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelValidateButtonTableFnNum = new JLabel();
        labelValidateButtonTableFnNum.setText("");
        panel1.add(labelValidateButtonTableFnNum, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(17, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        checkBoxTaxTotal = new JCheckBox();
        checkBoxTaxTotal.setText("Общая");
        panel2.add(checkBoxTaxTotal, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTaxSimplified = new JCheckBox();
        checkBoxTaxSimplified.setText("Упрощенная");
        panel2.add(checkBoxTaxSimplified, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTaxSimplifiedRevMinCon = new JCheckBox();
        checkBoxTaxSimplifiedRevMinCon.setText("Упрощенная доход минус расход");
        panel2.add(checkBoxTaxSimplifiedRevMinCon, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTaxENVD = new JCheckBox();
        checkBoxTaxENVD.setText("ЕНВД");
        panel2.add(checkBoxTaxENVD, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTaxESHN = new JCheckBox();
        checkBoxTaxESHN.setText("ЕСХН");
        panel2.add(checkBoxTaxESHN, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTaxPatent = new JCheckBox();
        checkBoxTaxPatent.setText("Патент");
        panel2.add(checkBoxTaxPatent, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(19, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        radioButtonOFDauto = new JRadioButton();
        radioButtonOFDauto.setText("Автономный режим");
        panel3.add(radioButtonOFDauto, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonOFDexpress = new JRadioButton();
        radioButtonOFDexpress.setText("Электронный экспресс");
        panel3.add(radioButtonOFDexpress, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonDreamkas = new JRadioButton();
        radioButtonDreamkas.setText("Дримкас");
        panel3.add(radioButtonDreamkas, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonOFDcontur = new JRadioButton();
        radioButtonOFDcontur.setText("Контур");
        panel3.add(radioButtonOFDcontur, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonOFDevotor = new JRadioButton();
        radioButtonOFDevotor.setText("Эвотор");
        panel3.add(radioButtonOFDevotor, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonOFDTaxcom = new JRadioButton();
        radioButtonOFDTaxcom.setText("Такском");
        panel3.add(radioButtonOFDTaxcom, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonOFDya = new JRadioButton();
        radioButtonOFDya.setText("ОФД-Я");
        panel3.add(radioButtonOFDya, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonOFDsbis = new JRadioButton();
        radioButtonOFDsbis.setText("СБИС ОФД");
        panel3.add(radioButtonOFDsbis, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonOFDastral = new JRadioButton();
        radioButtonOFDastral.setText("КАЛУГА АСТРАЛ");
        panel3.add(radioButtonOFDastral, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonOFDkorus = new JRadioButton();
        radioButtonOFDkorus.setText("Корус ОФД");
        panel3.add(radioButtonOFDkorus, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonOFDru = new JRadioButton();
        radioButtonOFDru.setText("ofd.ru");
        panel3.add(radioButtonOFDru, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonOFDyandex = new JRadioButton();
        radioButtonOFDyandex.setText("Яндекс");
        panel3.add(radioButtonOFDyandex, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonOFDfirst = new JRadioButton();
        radioButtonOFDfirst.setText("Первый ОФД");
        panel3.add(radioButtonOFDfirst, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonOFDouther = new JRadioButton();
        radioButtonOFDouther.setText("Другой");
        panel3.add(radioButtonOFDouther, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldOFDinn = new JTextField();
        mainPanel.add(textFieldOFDinn, new com.intellij.uiDesigner.core.GridConstraints(20, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldOFDserverAddress = new JTextField();
        mainPanel.add(textFieldOFDserverAddress, new com.intellij.uiDesigner.core.GridConstraints(21, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldOFDname = new JTextField();
        mainPanel.add(textFieldOFDname, new com.intellij.uiDesigner.core.GridConstraints(22, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldlOFDport = new JTextField();
        mainPanel.add(textFieldlOFDport, new com.intellij.uiDesigner.core.GridConstraints(23, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldOFDcheckReceiptAddress = new JTextField();
        mainPanel.add(textFieldOFDcheckReceiptAddress, new com.intellij.uiDesigner.core.GridConstraints(24, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldOFDipServer = new JTextField();
        mainPanel.add(textFieldOFDipServer, new com.intellij.uiDesigner.core.GridConstraints(25, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(26, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        checkBoxAgents0 = new JCheckBox();
        checkBoxAgents0.setText("Нет признаков агента");
        panel4.add(checkBoxAgents0, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxAgents8 = new JCheckBox();
        checkBoxAgents8.setText("Платёжный субагент");
        panel4.add(checkBoxAgents8, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxAgents1 = new JCheckBox();
        checkBoxAgents1.setText("Банковский платёжный агент");
        panel4.add(checkBoxAgents1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxAgents2 = new JCheckBox();
        checkBoxAgents2.setText("Банковский платёжный субагент");
        panel4.add(checkBoxAgents2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxAgents4 = new JCheckBox();
        checkBoxAgents4.setText("Платёжный агент");
        panel4.add(checkBoxAgents4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxAgents32 = new JCheckBox();
        checkBoxAgents32.setText("Комиссионер");
        panel4.add(checkBoxAgents32, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxAgents64 = new JCheckBox();
        checkBoxAgents64.setText("Агент");
        panel4.add(checkBoxAgents64, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxAgents16 = new JCheckBox();
        checkBoxAgents16.setText("Поверенный");
        panel4.add(checkBoxAgents16, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(27, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        comboBoxCurentAgent = new JComboBox();
        panel5.add(comboBoxCurentAgent, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelKktMode = new JLabel();
        labelKktMode.setHorizontalAlignment(4);
        labelKktMode.setHorizontalTextPosition(4);
        labelKktMode.setText("Режим работы кассы");
        mainPanel.add(labelKktMode, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel6, new com.intellij.uiDesigner.core.GridConstraints(28, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel6.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        checkBoxSign0 = new JCheckBox();
        checkBoxSign0.setText("Нет признаков");
        panel6.add(checkBoxSign0, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxSign4 = new JCheckBox();
        checkBoxSign4.setText("Признак проведения лотереи");
        panel6.add(checkBoxSign4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxSign8 = new JCheckBox();
        checkBoxSign8.setText("Признак установки принтера в автомате");
        panel6.add(checkBoxSign8, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxSign1 = new JCheckBox();
        checkBoxSign1.setText("Продажа подакцизного товара");
        panel6.add(checkBoxSign1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxSign2 = new JCheckBox();
        checkBoxSign2.setText("Признак проведения азартных игр");
        panel6.add(checkBoxSign2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel7, new com.intellij.uiDesigner.core.GridConstraints(29, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel7.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        checkBoxAddSign1 = new JCheckBox();
        checkBoxAddSign1.setText("Шифрование");
        panel7.add(checkBoxAddSign1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxAddSign2 = new JCheckBox();
        checkBoxAddSign2.setText("Автономный режим");
        panel7.add(checkBoxAddSign2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxAddSign8 = new JCheckBox();
        checkBoxAddSign8.setText("Применение в сфере услуг");
        panel7.add(checkBoxAddSign8, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxAddSign4 = new JCheckBox();
        checkBoxAddSign4.setText("Автоматический режим");
        panel7.add(checkBoxAddSign4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxAddSign32 = new JCheckBox();
        checkBoxAddSign32.setText("Применение в интернет");
        panel7.add(checkBoxAddSign32, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxAddSign0 = new JCheckBox();
        checkBoxAddSign0.setEnabled(true);
        checkBoxAddSign0.setSelected(false);
        checkBoxAddSign0.setText("Нет режимов");
        panel7.add(checkBoxAddSign0, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxAddSign16 = new JCheckBox();
        checkBoxAddSign16.setText("Режим БСО");
        panel7.add(checkBoxAddSign16, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxCurentTax = new JComboBox();
        mainPanel.add(comboBoxCurentTax, new com.intellij.uiDesigner.core.GridConstraints(18, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxCabinetIsEnable = new JCheckBox();
        checkBoxCabinetIsEnable.setText("");
        mainPanel.add(checkBoxCabinetIsEnable, new com.intellij.uiDesigner.core.GridConstraints(30, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMessageTableFn = new JLabel();
        labelMessageTableFn.setText("");
        mainPanel.add(labelMessageTableFn, new com.intellij.uiDesigner.core.GridConstraints(7, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelFsNumber = new JLabel();
        labelFsNumber.setText("Номер текущего ФН");
        mainPanel.add(labelFsNumber, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelValidateFsNumber = new JLabel();
        labelValidateFsNumber.setText("");
        mainPanel.add(labelValidateFsNumber, new com.intellij.uiDesigner.core.GridConstraints(9, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxCurrentFnNum = new JComboBox();
        mainPanel.add(comboBoxCurrentFnNum, new com.intellij.uiDesigner.core.GridConstraints(9, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        messageValidateOrgName = new JLabel();
        messageValidateOrgName.setText("");
        mainPanel.add(messageValidateOrgName, new com.intellij.uiDesigner.core.GridConstraints(10, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        messageValidateAddressCalc = new JLabel();
        messageValidateAddressCalc.setText("");
        mainPanel.add(messageValidateAddressCalc, new com.intellij.uiDesigner.core.GridConstraints(11, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        messageValidatePlaceCalc = new JLabel();
        messageValidatePlaceCalc.setText("");
        mainPanel.add(messageValidatePlaceCalc, new com.intellij.uiDesigner.core.GridConstraints(12, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        messageValidateInnOrg = new JLabel();
        messageValidateInnOrg.setText("");
        mainPanel.add(messageValidateInnOrg, new com.intellij.uiDesigner.core.GridConstraints(13, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        messageValidateRegNum = new JLabel();
        messageValidateRegNum.setText("");
        mainPanel.add(messageValidateRegNum, new com.intellij.uiDesigner.core.GridConstraints(14, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        messageValidateKktPluntNum = new JLabel();
        messageValidateKktPluntNum.setText("");
        mainPanel.add(messageValidateKktPluntNum, new com.intellij.uiDesigner.core.GridConstraints(15, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMessageTaxSystem = new JLabel();
        labelMessageTaxSystem.setText("");
        mainPanel.add(labelMessageTaxSystem, new com.intellij.uiDesigner.core.GridConstraints(18, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        closeButton = new JButton();
        closeButton.setText("Закрыть");
        mainPanel.add(closeButton, new com.intellij.uiDesigner.core.GridConstraints(32, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Сохранить");
        mainPanel.add(saveButton, new com.intellij.uiDesigner.core.GridConstraints(32, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 13, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(11, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        mainPanel.add(separator1, new com.intellij.uiDesigner.core.GridConstraints(31, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        labelMessageOFD = new JLabel();
        labelMessageOFD.setText("");
        mainPanel.add(labelMessageOFD, new com.intellij.uiDesigner.core.GridConstraints(19, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMessageOfdInn = new JLabel();
        labelMessageOfdInn.setText("");
        mainPanel.add(labelMessageOfdInn, new com.intellij.uiDesigner.core.GridConstraints(20, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMessageOfdAddressSer = new JLabel();
        labelMessageOfdAddressSer.setText("");
        mainPanel.add(labelMessageOfdAddressSer, new com.intellij.uiDesigner.core.GridConstraints(21, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMessageOfdName = new JLabel();
        labelMessageOfdName.setText("");
        mainPanel.add(labelMessageOfdName, new com.intellij.uiDesigner.core.GridConstraints(22, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMessageOfdPort = new JLabel();
        labelMessageOfdPort.setText("");
        mainPanel.add(labelMessageOfdPort, new com.intellij.uiDesigner.core.GridConstraints(23, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMessageOfdReceiptCheque = new JLabel();
        labelMessageOfdReceiptCheque.setText("");
        mainPanel.add(labelMessageOfdReceiptCheque, new com.intellij.uiDesigner.core.GridConstraints(24, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMessageIpServer = new JLabel();
        labelMessageIpServer.setText("");
        mainPanel.add(labelMessageIpServer, new com.intellij.uiDesigner.core.GridConstraints(25, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldEmailCabinet = new JTextField();
        mainPanel.add(textFieldEmailCabinet, new com.intellij.uiDesigner.core.GridConstraints(30, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelMessageEmailCabinet = new JLabel();
        labelMessageEmailCabinet.setText("");
        mainPanel.add(labelMessageEmailCabinet, new com.intellij.uiDesigner.core.GridConstraints(30, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMessageCountFN = new JLabel();
        labelMessageCountFN.setText("");
        mainPanel.add(labelMessageCountFN, new com.intellij.uiDesigner.core.GridConstraints(6, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelStage = new JLabel();
        labelStage.setText("Стадия жизни кассы");
        mainPanel.add(labelStage, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxStage = new JComboBox();
        mainPanel.add(comboBoxStage, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMessageStage = new JLabel();
        labelMessageStage.setText("");
        mainPanel.add(labelMessageStage, new com.intellij.uiDesigner.core.GridConstraints(1, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Email");
        mainPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(30, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(127, 16), null, 0, false));
        labelUUID = new JLabel();
        labelUUID.setText("UUID");
        mainPanel.add(labelUUID, new com.intellij.uiDesigner.core.GridConstraints(16, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        generateUUIDButton = new JButton();
        generateUUIDButton.setText("Сгенерировать новый");
        mainPanel.add(generateUUIDButton, new com.intellij.uiDesigner.core.GridConstraints(16, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldUUID = new JTextField();
        mainPanel.add(textFieldUUID, new com.intellij.uiDesigner.core.GridConstraints(16, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        messageValidateUUID = new JLabel();
        messageValidateUUID.setBackground(new Color(-14606047));
        messageValidateUUID.setForeground(new Color(-14606047));
        messageValidateUUID.setText("");
        mainPanel.add(messageValidateUUID, new com.intellij.uiDesigner.core.GridConstraints(16, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
