package sample.main;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.controlsfx.control.textfield.TextFields;
import sample.socketfx.SocketClient;
import sample.socketfx.SocketListener;
import sample.ubts2100.Ubts2100Controller;
import sample.ubts_v1.AmplifModel;
import sample.ubts_v1.Parser;
import sample.ubts_v1.UbtsV1Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Controller {
    @FXML
    private TextFlow mainTextFlow;
    @FXML
    private ScrollPane mainScrollPane;
    @FXML
    private TextField inputTextField;
    @FXML
    private TextField ipInputField;
    @FXML
    private TextField socketInputField;
    @FXML
    private ToggleButton connectButton;
    @FXML
    private UbtsV1Controller ubtsV1Controller;
    @FXML
    private Ubts2100Controller ubts2100Controller;

    private SocketClient socketClient;
    private List<AmplifModel> paModelList = Arrays.asList(new AmplifModel(), new AmplifModel(), new AmplifModel(), new AmplifModel());
    private Parser parser = new Parser(paModelList);

    @FXML
    public void initialize() {
        String[] ipWords = {"192.168.3.3", "192.168.6.5", "192.168.6.10", "192.168.6.15", "192.168.6.20", "192.168.6.40"};
        String[] socketWords = {"4000", "5000", "5001", "6000"};
        TextFields.bindAutoCompletion(ipInputField, ipWords);
        TextFields.bindAutoCompletion(socketInputField, socketWords);

//        ipInputField.setText("192.168.3.3");
        socketInputField.setText("5001");

        ubtsV1Controller.setController(this);
        ubtsV1Controller.setPaModel(paModelList);

        mainTextFlow.getChildren().addListener((ListChangeListener<? super Node>) c -> {
            mainTextFlow.layout();
            mainScrollPane.layout();
            mainScrollPane.setVvalue(1.0f);
        });
        mainScrollPane.setContent(mainTextFlow);
    }

    @FXML
    public void clearTextArea() {
        mainTextFlow.getChildren().clear();
    }

    @FXML
    public void pressConnectButton() {
        try {
            if (connectButton.isSelected()) {
                socketClient = new SocketClient(new SocketObject(), ipInputField.getText(), Integer.parseInt(socketInputField.getText()));
                doConnectActions();
                socketClient.connect();
            } else {
                socketClient.close();
                doDisonnectActions();
            }
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getClass().getName());
            doDisonnectActions();
        }
    }

    private class SocketObject implements SocketListener {

        @Override
        public void onMessage(String message) {
            printReceivedMessage(message);
            parser.parse(message);
        }

        @Override
        public void onClosedStatus(boolean isClosed) {
            if (isClosed) doDisonnectActions();
            else doConnectActions();
        }
    }

    @FXML
    public void sendCommandFromField() {
        sendCommand(inputTextField.getText());
    }

    public void sendCommand(String message) {
        String command = "%" + message + "*";
        printSendedMessage(command);
        try {
            socketClient.sendMessage(command);
        } catch (NullPointerException e) {
            System.out.println(e.getClass().getName());
        } catch (IOException e) {
            System.out.println(e.getClass().getName());
            doDisonnectActions();
        }
        System.out.println(command);
    }

    private void doConnectActions() {
        connectButton.setText("disconnect");
        connectButton.setSelected(true);
    }

    private void doDisonnectActions() {
        connectButton.setText("connect");
        connectButton.setSelected(false);
    }

    /*protected void printServiceMessage(String message) {
        mainTextFlow.appendText(message);
    }*/

    protected void printReceivedMessage(String message) {
        Text text = new Text();
        text.getStyleClass().add("text-empty");
        text.setText(message);
        mainTextFlow.getChildren().addAll(text);
    }

    public void printSendedMessage(String message) {
        Text text = new Text();
        text.getStyleClass().add("text-fill");
        text.setText(message);
        mainTextFlow.getChildren().addAll(text);
    }

    public TextField getInputTextField() {
        return inputTextField;
    }
}
