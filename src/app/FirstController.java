package app;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
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
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;

public class FirstController implements Initializable{
    @FXML private TextField id;
    @FXML private PasswordField pwd;
    @FXML private Button membersBtn;
    @FXML private Button login;
    @FXML private Button exit;
    @FXML private Label success;
    
    private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
    String uId=null;
    String uPwd=null;
    String jdbcUrl = "jdbc:mysql://localhost:3306/firdb";
	String dbId = "root";// MySQL 계정
	String dbPw = "1234";// MySQL 계정 비밀번호
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		membersBtn.setOnAction(e->membersAction(e)); //버튼 누르면 페이지 이동.
		login.setOnAction(e->LoginAction(e));//로그인 미완성.
	}
    
	
	private void LoginAction(ActionEvent event) {//로그인 함수.
		// TODO Auto-generated method stub
		try {
			uId = id.getText();
		    uPwd = pwd.getText();
			if(id.getText().length() != 0 && pwd.getText().length() != 0) {//fx 텍스트 필드에서 getText()가 Null을 인식을 못해서, length()로 길이가 0이면 null로 취급.
				this.uId = id.getText();
				this.uPwd = pwd.getText();
				
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
				String sql="select * from signup where id = ? and pw = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, uId);
				pstmt.setString(2, uPwd);
				rs = pstmt.executeQuery();//해당 쿼리문 DB로 전송
				if(rs.next()) {
					if(rs.getString("id").equals(uId) && rs.getString("pw").equals(uPwd)) {
						System.out.println(rs.getString("name"));
						success.setText(rs.getString("name") +" 님 로그인을 환영합니다.");//first.fxml파일에서 id를 fx:id로 인식을 못해서 오류발생.
					}
				}
				rs.close();
				pstmt.close();
				con.close();
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("아이디와 비밀번호를 입력하시오.");
				alert.showAndWait();
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("로그인 함수 오류.");
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
