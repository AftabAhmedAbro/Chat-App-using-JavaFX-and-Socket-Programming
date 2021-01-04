package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import mainScreen.MsgWrite;
import org.controlsfx.control.textfield.TextFields;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.net.Socket;
import java.net.URL;
import java.util.*;

public class ClientController implements Initializable {

    @FXML
    private TextArea txt_msgArea;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox chatBox;

    @FXML
    private TextField txtMsg;
    @FXML
    private Label lbl_activeNow;

    @FXML
    private Button btn_send;
    @FXML
    private Button btn_back;


    DataInputStream fromServer;
    DataOutputStream toServer;
    String textFromServer, msg;
    boolean isServerConnected;
    public static boolean isTyping = false;

    @FXML
    void btn_backClick(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    void  btn_sendAction(ActionEvent event) {
        sendAction();
    }

    private void sendAction(){
        try {

            msg = txtMsg.getText().trim();
            txtMsg.setText("");
            txtMsg.requestFocus();

            chatBox.getChildren().add(makeLabels());

            if (!isServerConnected){
                chatBox.getChildren().add(new Label("Server Not Available Right Now."));
            }

            toServer.writeUTF(msg);
            MsgWrite.write("Client : " + msg);
            toServer.flush();

        } catch (Exception e) { }
    }

    @FXML
    void txtMsgKeyPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER){
            sendAction();
        }
        else{
            ClientController.isTyping = true;
            lbl_activeNow.setText("typing...");
            Timer myTimer = new Timer();

            myTimer.schedule(new TimerTask(){
                @Override
                public void run() {
                    Platform.runLater(() ->{

                        try { Thread.sleep(150); }
                        catch (InterruptedException e) { e.printStackTrace(); }
                        lbl_activeNow.setText("Active Now");

                    });
                }
            }, 2000);


        }

    }

    private HBox makeLabels(){
        HBox box = new HBox();
        Label label = new Label();
        box.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        label.setTextAlignment(TextAlignment.RIGHT);
        label.setText(msg);
        box.getChildren().add(label);
        return box;
    }


    String subWord;
    void sort() {
        int position = txtMsg.getCaretPosition();
        String text = txtMsg.getText();
        int start = Math.max(0, position - 1);
        while (start > 0) {
            if (!Character.isWhitespace(text.charAt(start))) {
                start--;
            } else {
                start++;
                break;
            }
        }
        if (start > position) {
            return;
        }
        subWord = text.substring(start, position);
        System.out.println(subWord);
        if (subWord.length() < 2) {
            return;
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtMsg.requestFocus();

        ArrayList<String> listOfWords = new ArrayList<>();
        try{
            FileReader file = new FileReader("src/file/dict.txt");
            Scanner scan = new Scanner(file);
            while(scan.hasNext()){

                listOfWords.add(scan.nextLine());
            }
        } catch(Exception e) { e.printStackTrace(); }

        sort();
        TextFields.bindAutoCompletion(txtMsg, listOfWords);


        new Thread(() -> {
            try {

                Socket socket = new Socket("localhost", 7777);
                isServerConnected = true;
                fromServer = new DataInputStream(socket.getInputStream());
                toServer = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    textFromServer = fromServer.readUTF();
                    Platform.runLater(() -> {
                        chatBox.getChildren().add(new Label(textFromServer));
                    });
                }
            }catch(Exception e) { e.printStackTrace(); }
        }).start();



    }


}
