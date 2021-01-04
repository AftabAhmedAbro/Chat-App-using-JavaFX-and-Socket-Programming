package server;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import mainScreen.LinkedList;
import mainScreen.MsgWrite;
import mainScreen.MyTrie;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.*;
import java.util.Timer;
import org.controlsfx.control.textfield.TextFields;

public class ServerController implements Initializable {

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


    DataInputStream inputFromClient;
    DataOutputStream outputToClient;
    String textFromClient, msg;
    boolean isClientConnected;
    public static boolean isTyping = false;
    MyTrie m = new MyTrie();

    @FXML
    void btn_backClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/mainScreen/mainScreen.fxml"));
        Stage stage = (Stage) btn_back.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
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

            if (!isClientConnected){
                chatBox.getChildren().add(new Label("Client Not Available Right Now."));
            }
            outputToClient.writeUTF(msg);
            MsgWrite.write("Server : " + msg);
            outputToClient.flush();

        } catch (Exception e) { }
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

    @FXML
    void txtMsgKeyPressed(KeyEvent event) throws InterruptedException {
        if (event.getCode() == KeyCode.ENTER){
            sendAction();
        }
        else{
            lbl_activeNow.setText("typing...");

            Timer myTimer = new Timer();
            myTimer.schedule(new TimerTask(){
                @Override
                public void run() {
                    Platform.runLater(() ->{

                        try {
                            Thread.sleep(150); }
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
        TextFields.bindAutoCompletion(txtMsg,listOfWords);


        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(7777);
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected To Server");
                isClientConnected = true;

                inputFromClient = new DataInputStream(socket.getInputStream());
                outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    textFromClient = inputFromClient.readUTF();
                    Platform.runLater(() -> {
                        chatBox.getChildren().add(new Label(textFromClient));
                    });
                }

            }catch(Exception e) { e.printStackTrace(); }

        }).start();




    }














}
