package com.dreamkas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

import static com.dreamkas.ConfigCreator.REG_EXP_IP;

public class MainGui extends JFrame {

    private static final int mWidth = 350;
    private static final int mHeighth = 400;
    private FrontEnd m_fe;
    private JPanel MainPanel;
    private JTextField ipTextField;
    private JButton getConfigButton;
    private JButton buttonGetClone;
    private JButton updateDrawer;
    private JProgressBar progressBar;
    private JTextArea logField;
    private JLabel labelInputIp;
    private JLabel labelMessageIp;

    private static final String COMMAND_RM_BACKUP = "rm -r /updateBackup/";
    private static final String TITLE = "FisGoUP-1.3 (for Dreamkas-F)";

    MainGui() {
        setTitle(TITLE);
        setContentPane(MainPanel);
        setSize(mWidth, mHeighth);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        LeftButtonListener buttonLeft = new LeftButtonListener();
        updateDrawer.addActionListener(buttonLeft);

        DownloadConfigListener buttonDownloadConfig = new DownloadConfigListener();
        getConfigButton.addActionListener(buttonDownloadConfig);

        CloneDrawerListener buttonCloneDrawerListener = new CloneDrawerListener();
        buttonGetClone.addActionListener(buttonCloneDrawerListener);

        progressBar.setVisible(false);
        logField.setEditable(false);

        validateIp();
        ipTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateIp();
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 3, new Insets(0, 0, 0, 0), -1, -1));
        ipTextField = new JTextField();
        MainPanel.add(ipTextField, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        buttonGetClone = new JButton();
        buttonGetClone.setText("Копировать каталог FisGo на ПК");
        MainPanel.add(buttonGetClone, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateDrawer = new JButton();
        updateDrawer.setText("Обновить кассу");
        MainPanel.add(updateDrawer, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        getConfigButton = new JButton();
        getConfigButton.setText("Загрузить конфиг");
        MainPanel.add(getConfigButton, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logField = new JTextArea();
        MainPanel.add(logField, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 50), null, 0, false));
        labelInputIp = new JLabel();
        labelInputIp.setText("Введите IP ");
        MainPanel.add(labelInputIp, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        MainPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        MainPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        labelMessageIp = new JLabel();
        labelMessageIp.setText("");
        MainPanel.add(labelMessageIp, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        progressBar = new JProgressBar();
        MainPanel.add(progressBar, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }

    /**
     * Обработчик нажатия кнопки "Обновить кассу"
     */
    public class LeftButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            logField.setText("");
            System.out.println(ipTextField.getText());
            m_fe.updateDrawer(ipTextField.getText());
            setEnabledButtons(false);
        }
    }

    /**
     * Обработчик кнопки загрузки конфига с кассы
     */
    public class DownloadConfigListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            logField.setText("");
            System.out.println(ipTextField.getText());
            m_fe.downloadConfig(ipTextField.getText());

            progressBar.setIndeterminate(true);
            progressBar.setVisible(true);

            setEnabledButtons(false);
        }
    }

    /**
     * Обработчик нажатия кнопки клонирования кассы
     */
    public class CloneDrawerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            logField.setText("");
            System.out.println(ipTextField.getText());
            m_fe.cloneDrawer(ipTextField.getText());
            setEnabledButtons(false);
        }
    }

    /**
     * Метод изображения фрейма с конфигом
     * @param config - лист конфига
     */
    public void drawConfigPanel(Map<String, String> config) {
        JFrame configCreator = new ConfigCreator(config, m_fe, ipTextField.getText());
    }

    /**
     * Сброс всех элементов в дефолтное состояние
     */
    public void resetGui(String msg) {
        if (msg.equals("Success")) {
            System.out.println("GUI: ОПЕРАЦИЯ УСПЕШНО ЗАВЕРШЕНА!!!");
            printLogString("GUI: ОПЕРАЦИЯ УСПЕШНО ЗАВЕРШЕНА!!!\n");
            progressBar.setIndeterminate(false);
            progressBar.setVisible(false);
            setEnabledButtons(true);
        } else if (msg.equals("Failed")) {
            System.out.println("GUI: ОПЕРАЦИЯ ПРОВАЛЕНА!!!");
            printLogString("GUI: ОПЕРАЦИЯ ПРОВАЛЕНА!!!\n");
            progressBar.setIndeterminate(false);
            setEnabledButtons(true);
        }
    }

    /**
     * Валидатор IP-адресса
     */
    private void validateIp() {
        setEnabledButtons(ConfigCreator.regExpCheck(ipTextField.getText(), REG_EXP_IP));
    }

    /**
     * Установить активность кнопок
     */
    private void setEnabledButtons(boolean isEnabled) {
        getConfigButton.setEnabled(isEnabled);
        updateDrawer.setEnabled(isEnabled);
        buttonGetClone.setEnabled(isEnabled);
    }

    //установить ссылку на frontEnd
    public void setFrontEnd(FrontEnd fe) {
        this.m_fe = fe;
    }

    //напечатать строку лога
    public void printLogString(String logString) {
        logField.append(logString);
    }
}
