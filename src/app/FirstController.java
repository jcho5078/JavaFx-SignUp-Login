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
	String dbId = "root";// MySQL ����
	String dbPw = "1234";// MySQL ���� ��й�ȣ
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		membersBtn.setOnAction(e->membersAction(e)); //��ư ������ ������ �̵�.
		login.setOnAction(e->LoginAction(e));//�α��� �̿ϼ�.
	}
    
	
	private void LoginAction(ActionEvent event) {//�α��� �Լ�.
		// TODO Auto-generated method stub
		try {
			uId = id.getText();
		    uPwd = pwd.getText();
			if(id.getText().length() != 0 && pwd.getText().length() != 0) {//fx �ؽ�Ʈ �ʵ忡�� getText()�� Null�� �ν��� ���ؼ�, length()�� ���̰� 0�̸� null�� ���.
				this.uId = id.getText();
				this.uPwd = pwd.getText();
				
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
				String sql="select * from signup where id = ? and pw = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, uId);
				pstmt.setString(2, uPwd);
				rs = pstmt.executeQuery();//�ش� ������ DB�� ����
				if(rs.next()) {
					if(rs.getString("id").equals(uId) && rs.getString("pw").equals(uPwd)) {
						System.out.println(rs.getString("name"));
						success.setText(rs.getString("name") +" �� �α����� ȯ���մϴ�.");//first.fxml���Ͽ��� id�� fx:id�� �ν��� ���ؼ� �����߻�.
					}
				}
				rs.close();
				pstmt.close();
				con.close();
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("���̵�� ��й�ȣ�� �Է��Ͻÿ�.");
				alert.showAndWait();
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("�α��� �Լ� ����.");
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
