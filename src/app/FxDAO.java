package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FxDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	String jdbcUrl = "jdbc:mysql://localhost:3306/firdb";
	String dbId = "root";// MySQL 계정
	String dbPw = "1234";// MySQL 계정 비밀번호
	String suc = null;
	FirstController fir = new FirstController();
	
	public List Login() {//로그인
		List list = new ArrayList();
		System.out.println("1");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			String sql="select * from signup;";
			pstmt = con.prepareStatement(sql);
			System.out.println("2");
			rs = pstmt.executeQuery();
			System.out.println("3");
			
			while(rs.next()) { //list에 넣어 db 탐색.
				String uName = rs.getString("name");
				String uId = rs.getString("id");
				String uPwd = rs.getString("pw");
				
				FxVO vo = new FxVO();
				
				vo.setuName(uName);
				vo.setuId(uId);
				vo.setuPwd(uPwd);
				list.add(vo);
			}
			System.out.println("4");
			rs.close();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("DAO 로그인 함수 오류.");
		}
		return list;
	}
}
