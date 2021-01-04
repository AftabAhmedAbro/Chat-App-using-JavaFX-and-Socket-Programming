package recentButtonScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import mainScreen.MsgRead;

import java.net.URL;
import java.util.ResourceBundle;

public class RecentChatsController implements Initializable {

    @FXML
    private Button btn_back2;
    @FXML
    private VBox chatBox;

//    ArrayList<String> msgs;
    String allMsgs = "";


    private Label makeLabels(int btnNum){
        Label label = new Label();
        label.setTextAlignment(TextAlignment.LEFT);
        label.setText("");
        for(String x: MsgRead.readMsg(btnNum)){
            allMsgs = allMsgs + x + System.lineSeparator();
        }
        label.setText(allMsgs);
        return label;
    }

    @FXML
    void btn_back2Click(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/recentButtonScreen/recentButtonScreen.fxml"));
        Stage stage = (Stage) btn_back2.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chatBox.getChildren().add(makeLabels(RecentController.chatNumber));
    }


}
