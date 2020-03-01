package app;

import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.login.LoginContext;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    @FXML private Label success;
    
    FxDAO dao = new FxDAO();
    String uId = id.getText();
    String uPwd = pwd.getText();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		membersBtn.setOnAction(e->membersAction(e)); //버튼 누르면 페이지 이동.
		login.setOnAction(e->LoginAction(e));//로그인 미완성.
	}
    
	
	private void LoginAction(ActionEvent event) {//로그인 함수.
		// TODO Auto-generated method stub
		try {
			if(id != null && pwd != null) {
				dao.Login();
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("아이디와 비밀번호를 입력하시오.");
				alert.showAndWait();
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
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
