package mainScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    private Button btn_recentChat;

    @FXML
    private Button btn_newChat;

    @FXML
    void btn_newChatClicked(ActionEvent event) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/server/server.fxml"));
            Stage stage = (Stage) btn_newChat.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Server Side");
            LinkedList.removeAtEnd();
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            Parent root2 = FXMLLoader.load(getClass().getResource("/client/client.fxml"));
            Stage stage2 = (Stage) btn_newChat.getScene().getWindow();
            stage2 = new Stage();
            Scene scene2 = new Scene(root2);
            stage2.setScene(scene2);
            stage2.setTitle("Client Side");
            stage2.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void btn_recentChatClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/recentButtonScreen/recentButtonScreen.fxml"));
        Stage stage = (Stage) btn_newChat.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
