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
    @FXML private Button signup;//fxml ������ ��ư ��Ʈ�ѷ� ��ü ����.
    @FXML private Button cancelBtn;
    
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cancelBtn.setOnAction(e->cancelAction(e));//fxml���Ͽ����� ��ư ��ü�� �̺�Ʈ �߻��� �Լ� ����.
		signup.setOnAction(e->membersAction(e));//
	}
    
	public void cancelAction(ActionEvent e){//ȸ������ ��� ��ư. �ٽ� forstPage.fxml�� �̵�.
		StackPane root = (StackPane) cancelBtn.getScene().getRoot();
		root.getChildren().remove(login);
	}
	
	public void membersAction(ActionEvent event){//member.fxml���� ȸ������ �Ϸ� ��ư �Լ�.
		String uName = name.getText();
	    String uId = id.getText();
	    String uPwd = pwd.getText();  
	 // MySQL ���� ���, �⺻ ��Ʈ�� 3306, DB�� 
		  String jdbcUrl = "jdbc:mysql://localhost:3306/firdb";
		  // MySQL ����
		  String dbId = "root";
		  // MySQL ���� ��й�ȣ
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
		   // ��� ����
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
