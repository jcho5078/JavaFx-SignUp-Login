package app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class FirstController implements Initializable{
    @FXML private TextField id;
    @FXML private PasswordField pwd;
    @FXML private Button membersBtn;
    @FXML private Button login;
    @FXML private Button exit;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		membersBtn.setOnAction(e->membersAction(e)); //��ư ������ ������ �̵�.
		login.setOnAction(e->LoginAction(e));//�α��� �̿ϼ�.
	}
    
	
	private Object LoginAction(ActionEvent e) {//�α��� �Լ�.
		// TODO Auto-generated method stub
		return null;
	}


	public void membersAction(ActionEvent event){
		try{
		Parent members = FXMLLoader.load(getClass().getResource("members.fxml"));
		StackPane root = (StackPane) membersBtn.getScene().getRoot();
		root.getChildren().add(members);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void handleBtnAction(ActionEvent e){
		Platform.exit();
	}
	
}
