package jdbc_prg;

import java.sql.*;

public class Query_insert {
	public static void main(String[] args) {
		//1. jdbc 드라이버를 메모리에 로딩
		//2. jdbc 드라이버 객체를 만든다.
		//3. 드라이버 객체를 DriverManager에 등록
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid="System";
		String passwd = "1234";
		   
		try {
			Class.forName(driver);
			System.out.println("드라이버 로딩되었습니다.");
			Connection con = DriverManager.getConnection(url, userid, passwd);
			System.out.println("데이터베이스에 접속 성공 !");
			String sql = "select * from member2";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				System.out.println(name);
			}
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null)con.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
