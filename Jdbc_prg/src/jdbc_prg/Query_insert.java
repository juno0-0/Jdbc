package jdbc_prg;

import java.sql.*;

public class Query_insert {
	public static void main(String[] args) {
		//1. jdbc ����̹��� �޸𸮿� �ε�
		//2. jdbc ����̹� ��ü�� �����.
		//3. ����̹� ��ü�� DriverManager�� ���
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid="System";
		String passwd = "1234";
		   
		try {
			Class.forName(driver);
			System.out.println("����̹� �ε��Ǿ����ϴ�.");
			Connection con = DriverManager.getConnection(url, userid, passwd);
			System.out.println("�����ͺ��̽��� ���� ���� !");
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
