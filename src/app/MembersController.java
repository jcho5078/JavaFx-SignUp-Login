package app;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class MembersController implements Initializable{
	@FXML private AnchorPane login;
	@FXML private TextField name;
	@FXML private TextField id;
    @FXML private PasswordField pwd;
    @FXML private Button signup;//fxml 파일의 버튼 컨트롤러 객체 선언.
    @FXML private Button cancelBtn;
    
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cancelBtn.setOnAction(e->cancelAction(e));//fxml파일에서의 버튼 객체에 이벤트 발생시 함수 실행.
		signup.setOnAction(e->membersAction(e));//
	}
    
	public void cancelAction(ActionEvent e){//회원가입 취소 버튼. 다시 forstPage.fxml로 이동.
		StackPane root = (StackPane) cancelBtn.getScene().getRoot();
		root.getChildren().remove(login);
	}
	
	public void membersAction(ActionEvent event){//member.fxml에서 회원가입 완료 버튼 함수.
		String uName = name.getText();
	    String uId = id.getText();
	    String uPwd = pwd.getText();  
	 // MySQL 접속 경로, 기본 포트는 3306, DB명 
		  String jdbcUrl = "jdbc:mysql://localhost:3306/firdb";
		  // MySQL 계정
		  String dbId = "root";
		  // MySQL 계정 비밀번호
		  String dbPw = "1234";	  
		  Connection con = null;
		  PreparedStatement pstmt = null;
		  
		  String sql = "";
		  new MembersController();
		  String name = uName;
		  String id = uId;
		  String pwd = uPwd;
		  
		  try {
		   Class.forName("com.mysql.jdbc.Driver");
		   // 디비 연결
		   con = DriverManager.getConnection(jdbcUrl, dbId, dbPw);	     
		     
		     sql = "insert into signup values(?,?,?)";
		     pstmt = con.prepareStatement(sql);
		     pstmt.setString(1, name);
		     pstmt.setString(2, id);
		     pstmt.setString(3, pwd);
		     pstmt.executeUpdate();	     
		     pstmt.close();
		     }
	 catch (Exception e) {
		   e.printStackTrace();
		  } finally{
		   if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		   if(con!=null) try{con.close();}catch(SQLException ex){}
		  }
		  StackPane root = (StackPane) cancelBtn.getScene().getRoot();
			root.getChildren().remove(login);
			
	 }
	
	
}
