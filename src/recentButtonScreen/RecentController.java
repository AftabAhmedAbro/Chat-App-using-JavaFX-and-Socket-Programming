package recentButtonScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static mainScreen.MsgRead.readMsg;

public class RecentController implements Initializable {

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_chat01;

    @FXML
    private Button btn_chat02;

    @FXML
    private Button btn_chat03;

    @FXML
    private Button btn_chat04;

    @FXML
    private Button btn_chat05;

    public static int chatNumber;

    @FXML
    void btn_chat01Clicked(ActionEvent event) throws Exception{

        chatNumber = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/recentButtonScreen/recentChatsScreen.fxml"));
        Stage stage = (Stage) btn_chat01.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void btn_chat02Clicked(ActionEvent event) throws Exception{
        chatNumber = 1;
        Parent root = FXMLLoader.load(getClass().getResource("/recentButtonScreen/recentChatsScreen.fxml"));
        Stage stage = (Stage) btn_chat01.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void btn_chat03Clicked(ActionEvent event) throws Exception{
        chatNumber = 2;
        Parent root = FXMLLoader.load(getClass().getResource("/recentButtonScreen/recentChatsScreen.fxml"));
        Stage stage = (Stage) btn_chat01.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void btn_chat04Clicked(ActionEvent event) throws Exception{
        chatNumber = 3;
        Parent root = FXMLLoader.load(getClass().getResource("/recentButtonScreen/recentChatsScreen.fxml"));
        Stage stage = (Stage) btn_chat01.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void btn_chat05Clicked(ActionEvent event) throws Exception{
        chatNumber = 4;
        Parent root = FXMLLoader.load(getClass().getResource("/recentButtonScreen/recentChatsScreen.fxml"));
        Stage stage = (Stage) btn_chat01.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    @FXML
    void btn_backClick(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/mainScreen/mainScreen.fxml"));
        Stage stage = (Stage) btn_chat01.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }


}
