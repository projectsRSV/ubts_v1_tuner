package sample.ubts_v1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.main.Controller;

import java.util.Arrays;
import java.util.List;

public class UbtsV1Controller {
    private Controller controller;
    @FXML
    private Spinner<Integer> pa0ReadBandSpinner;
    @FXML
    private Spinner<Integer> pa1ReadBandSpinner;
    @FXML
    private Spinner<Integer> pa2ReadBandSpinner;
    //    @FXML
//    private Spinner<Integer> pa3ReadBandSpinner;
    @FXML
    private CheckBox pa0CheckBox;
    @FXML
    private CheckBox pa1CheckBox;
    @FXML
    private CheckBox pa2CheckBox;
    //    @FXML
//    private CheckBox pa3CheckBox;
    @FXML
    private Label pa0AddrI2CLabel;
    @FXML
    private Label pa1AddrI2CLabel;
    @FXML
    private Label pa2AddrI2CLabel;
    //    @FXML
//    private Label pa3AddrI2CLabel;
    @FXML
    private Label pa0BandLabel;
    @FXML
    private Label pa1BandLabel;
    @FXML
    private Label pa2BandLabel;
    //    @FXML
//    private Label pa3BandLabel;
    @FXML
    private Label pa0IsActiveLabel;
    @FXML
    private Label pa1IsActiveLabel;
    @FXML
    private Label pa2IsActiveLabel;
    //    @FXML
//    private Label pa3IsActiveLabel;
    @FXML
    private Label pa0FanPinLabel;
    @FXML
    private Label pa1FanPinLabel;
    @FXML
    private Label pa2FanPinLabel;
    //    @FXML
//    private Label pa3FanPinLabel;
    @FXML
    private ToggleButton nmTglBtn;
    @FXML
    private ToggleButton gpsTglBtn;
    @FXML
    private ToggleButton wifiTglBtn;
    @FXML
    private Spinner<Integer> pa0BandSpinner;
    @FXML
    private Spinner<Integer> pa1BandSpinner;
    @FXML
    private Spinner<Integer> pa2BandSpinner;
    @FXML
    private Spinner<Integer> pa3BandSpinner;
    @FXML
    private Spinner<Double> pa0AttSpinner;
    @FXML
    private Spinner<Double> pa1AttSpinner;
    @FXML
    private Spinner<Double> pa2AttSpinner;
    @FXML
    private Spinner<Double> pa3AttSpinner;
    @FXML
    private ToggleButton sendPa0;
    @FXML
    private ToggleButton sendPa1;
    @FXML
    private ToggleButton sendPa2;
    @FXML
    private ToggleButton sendPa3;
    @FXML
    private CheckBox setCommandE7;
    @FXML
    private ComboBox<String> comboBoxStandart0;
    @FXML
    private ComboBox<String> comboBoxStandart1;
    @FXML
    private ComboBox<String> comboBoxStandart2;
    @FXML
    private ComboBox<String> comboBoxStandart3;

    List<Label> addList;
    List<Label> bandList;
    List<Label> fanpinList;
    List<Label> activeList;
    List<Spinner<Double>> spinners = Arrays.asList(pa0AttSpinner, pa1AttSpinner, pa2AttSpinner, pa3AttSpinner);
    private List<AmplifModel> paModelList = Arrays.asList(new AmplifModel(), new AmplifModel(), new AmplifModel(), new AmplifModel());
    private Parser parser = new Parser(paModelList);

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Parser getParser() {
        return parser;
    }

    private void bindPaParams() {
        for (int i = 0; i < 3; i++) {
            addList.get(i).textProperty().bind(paModelList.get(i).addressI2CProperty());
            bandList.get(i).textProperty().bind(paModelList.get(i).bandProperty());
            fanpinList.get(i).textProperty().bind(paModelList.get(i).fanPinProperty());
            activeList.get(i).textProperty().bind(paModelList.get(i).validProperty());
        }
    }

    @FXML
    public void initialize() {

        SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 5);
        SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 5);
        SpinnerValueFactory<Integer> valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 5);
        SpinnerValueFactory<Integer> valueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 5);
        SpinnerValueFactory<Integer> valueFactory9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 5);
        SpinnerValueFactory<Integer> valueFactory10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 5);
        SpinnerValueFactory<Integer> valueFactory11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 5);
        SpinnerValueFactory<Integer> valueFactory12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 5);
        pa0ReadBandSpinner.setValueFactory(valueFactory1);
        pa1ReadBandSpinner.setValueFactory(valueFactory2);
        pa2ReadBandSpinner.setValueFactory(valueFactory3);
//        pa3ReadBandSpinner.setValueFactory(valueFactory4);

        SpinnerValueFactory<Double> valueFactory5 = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 27.25, 0, 0.25);
        SpinnerValueFactory<Double> valueFactory6 = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 27.25, 0, 0.25);
        SpinnerValueFactory<Double> valueFactory7 = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 27.25, 0, 0.25);
        SpinnerValueFactory<Double> valueFactory8 = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 27.25, 0, 0.25);
        pa0AttSpinner.setValueFactory(valueFactory5);
        pa1AttSpinner.setValueFactory(valueFactory6);
        pa2AttSpinner.setValueFactory(valueFactory7);
        pa3AttSpinner.setValueFactory(valueFactory8);

        pa0BandSpinner.setValueFactory(valueFactory9);
        pa1BandSpinner.setValueFactory(valueFactory10);
        pa2BandSpinner.setValueFactory(valueFactory11);
        pa3BandSpinner.setValueFactory(valueFactory12);
//        List<CheckBox> checkBoxList = Arrays.asList(pa0CheckBox, pa1CheckBox, pa2CheckBox, pa3CheckBox);
//        List<Spinner<Integer>> spinnerList = Arrays.asList(pa0ReadBandSpinner, pa1ReadBandSpinner, pa2ReadBandSpinner, pa3ReadBandSpinner);
        addList = Arrays.asList(pa0AddrI2CLabel, pa1AddrI2CLabel, pa2AddrI2CLabel);
        bandList = Arrays.asList(pa0BandLabel, pa1BandLabel, pa2BandLabel);
        fanpinList = Arrays.asList(pa0FanPinLabel, pa1FanPinLabel, pa2FanPinLabel);
        activeList = Arrays.asList(pa0IsActiveLabel, pa1IsActiveLabel, pa2IsActiveLabel);

        /*EventHandler setButtonHandler = event -> {
            Button button = (Button) event.getSource();
            int index = button.getId().charAt(3);
            controller.sendCommand("76,0," + ((spinnerList.get(index).getValue() < 10) ? "0" : "") + spinnerList.get(index).getValue() + "," +
                    ((checkBoxList.get(index).isSelected() == true) ? "1" : "0"));
        };*/
        ObservableList<String> stringObservableList = FXCollections.observableArrayList("GSM", "UMTS", "LTE", "CDMA");
        comboBoxStandart0.setItems(stringObservableList);
        comboBoxStandart1.setItems(stringObservableList);
        comboBoxStandart2.setItems(stringObservableList);
        comboBoxStandart3.setItems(stringObservableList);
        comboBoxStandart0.setValue("UMTS");
        comboBoxStandart1.setValue("UMTS");
        comboBoxStandart2.setValue("UMTS");
        comboBoxStandart3.setValue("UMTS");

        spinners = Arrays.asList(pa0AttSpinner, pa1AttSpinner, pa2AttSpinner, pa3AttSpinner);
        parser.setSpinners(spinners);
        bindPaParams();
    }

    @FXML
    public void setPA0Handler() {
        controller.sendCommand(Commands.TUNE_PA + ",0," + ((pa0ReadBandSpinner.getValue() < 10) ? "0" : "") + pa0ReadBandSpinner.getValue() + "," +
                (pa0CheckBox.isSelected() ? "1" : "0"));
    }

    @FXML
    public void setPA1Handler() {
        controller.sendCommand(Commands.TUNE_PA + ",1," + ((pa1ReadBandSpinner.getValue() < 10) ? "0" : "") + pa1ReadBandSpinner.getValue() + "," +
                (pa1CheckBox.isSelected() ? "1" : "0"));
    }

    @FXML
    public void setPA2Handler() {
        controller.sendCommand(Commands.TUNE_PA + ",2," + ((pa2ReadBandSpinner.getValue() < 10) ? "0" : "") + pa2ReadBandSpinner.getValue() + "," +
                (pa2CheckBox.isSelected() ? "1" : "0"));
    }

//    @FXML
//    public void setPA3Handler() {
//        controller.sendCommand(Commands.TUNE_PA + ",3," + ((pa3ReadBandSpinner.getValue() < 10) ? "0" : "") + pa3ReadBandSpinner.getValue() + "," +
//                (pa3CheckBox.isSelected() ? "1" : "0"));
//    }

    @FXML
    public void readPA0Handler() {
        controller.sendCommand(Commands.READ_PA0);
    }

    @FXML
    public void readPA1Handler() {
        controller.sendCommand(Commands.READ_PA1);
    }

    @FXML
    public void readPA2Handler() {
        controller.sendCommand(Commands.READ_PA2);
    }

    @FXML
    public void readPA3Handler() {
        controller.sendCommand(Commands.READ_PA3);
    }

    @FXML
    public void rebootDevice() {
        controller.sendCommand(Commands.REBOOT_MCU);
    }

    @FXML
    public void jumpToBootloader() {
        controller.sendCommand(Commands.JUMP_BOOTLOADER);
    }

    @FXML
    public void readTempBoard() {
        controller.sendCommand(Commands.TEMP_BOARD);
    }

    @FXML
    public void readTempPA0() {
        controller.sendCommand(Commands.TEMP_PA0);
    }

    @FXML
    public void readTempPA1() {
        controller.sendCommand(Commands.TEMP_PA1);
    }

    @FXML
    public void readTempPA2() {
        controller.sendCommand(Commands.TEMP_PA2);
    }

    @FXML
    public void readTempPA3() {
        controller.sendCommand(Commands.TEMP_PA3);
    }

    @FXML
    public void readAllTemp() {
        controller.sendCommand(Commands.TEMP_ALL);
    }

    @FXML
    public void readVersion() {
        controller.sendCommand(Commands.READ_VERSION);
    }

    @FXML
    public void readInaBts() {
        controller.sendCommand(Commands.INA);
    }

    @FXML
    public void nmHandler() {
        controller.sendCommand(nmTglBtn.isSelected() ? Commands.NM_ON : Commands.NM_OFF);
    }

    @FXML
    public void readSerialNumber() {
        controller.sendCommand(Commands.SERIAL_NUM);
    }

    @FXML
    public void gpsHandler() {
        controller.sendCommand(gpsTglBtn.isSelected() ? Commands.GPS_ON : Commands.GPS_OFF);
    }

    @FXML
    public void wifiHandler() {
        controller.sendCommand(Commands.WIFI_ON + (wifiTglBtn.isSelected() ? "01" : "00"));
    }

    @FXML
    public void scanNmHadler() {
        controller.sendCommand(Commands.SCAN_NM);
    }

    @FXML
    public void askBandNmHadler() {
        controller.sendCommand(Commands.ASK_BAND_NM);
    }

    @FXML
    public void sendPa0Handler() {
        controller.sendCommand(Commands.SET_CHANNEL + "," + "1" + "," + comboBoxStandart0.getValue().substring(0, 1) +
                ((pa0BandSpinner.getValue() < 10) ? "0" : "") + pa0BandSpinner.getValue() + "," + (sendPa0.isSelected() ? "1" : "0"));
    }

    @FXML
    public void sendPa1Handler() {
        controller.sendCommand(Commands.SET_CHANNEL + "," + "2" + "," + comboBoxStandart1.getValue().substring(0, 1) +
                ((pa1BandSpinner.getValue() < 10) ? "0" : "") + pa1BandSpinner.getValue() + "," + (sendPa1.isSelected() ? "1" : "0"));
    }

    @FXML
    public void sendPa2Handler() {
        controller.sendCommand(Commands.SET_CHANNEL + "," + "3" + "," + comboBoxStandart2.getValue().substring(0, 1) +
                ((pa2BandSpinner.getValue() < 10) ? "0" : "") + pa2BandSpinner.getValue() + "," + (sendPa2.isSelected() ? "1" : "0"));
    }

    @FXML
    public void sendPa3Handler() {
        controller.sendCommand(Commands.SET_CHANNEL + "," + "4" + "," + comboBoxStandart3.getValue().substring(0, 1) +
                ((pa3BandSpinner.getValue() < 10) ? "0" : "") + pa3BandSpinner.getValue() + "," + (sendPa3.isSelected() ? "1" : "0"));
    }

    @FXML
    public void setAttHandler0() {
        String result = getStringSpinnerValue(pa0AttSpinner.getValue());
        controller.sendCommand(Commands.SET_ATT_PA0 + "," + ((result.length() == 1) ? "0" : "") + result);
    }

    @FXML
    public void setAttHandler1() {
        String result = getStringSpinnerValue(pa1AttSpinner.getValue());
        controller.sendCommand(Commands.SET_ATT_PA1 + "," + ((result.length() == 1) ? "0" : "") + result);
    }

    @FXML
    public void setAttHandler2() {
        String result = getStringSpinnerValue(pa2AttSpinner.getValue());
        controller.sendCommand(Commands.SET_ATT_PA2 + "," + ((result.length() == 1) ? "0" : "") + result);
    }

    @FXML
    public void setAttHandler3() {
        String result = getStringSpinnerValue(pa3AttSpinner.getValue());
        controller.sendCommand(Commands.SET_ATT_PA3 + "," + ((result.length() == 1) ? "0" : "") + result);
    }

    @FXML
    public void setSetCommandE7() {
        if (setCommandE7.isSelected()) controller.sendCommand(Commands.E7 + controller.getInputTextField().getText());
        else controller.sendCommand(Commands.E8);
    }

    @FXML
    public void readAttHandler() {
        controller.sendCommand(Commands.READ_ATT);
    }

    private String getStringSpinnerValue(double spinnerValue) {
        int whole = (int) spinnerValue << 2;
        int fraction = (int) (spinnerValue % 1 * 100);
        whole = whole + fraction / 25;
        return Integer.toHexString(whole);
    }
}
