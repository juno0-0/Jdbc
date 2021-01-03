package jdbc_prg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcEx5 {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "system";
			String pw = "1234";
			Connection con = DriverManager.getConnection(url, id, pw);
			System.out.println("���� ����!");
			String sql = "insert into member2 values(?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(args[0]));//�޾ƿ� ���� �ְڴ�.
			pstmt.setString(2, args[1]);
			pstmt.setString(3, args[2]);
			pstmt.setString(4, args[3]);
			//�̷��� ����� ��� ������ Run Configurations���� arguments �κп�
			//���� �Է����ش�.(������ �������� �Ѵ�.)
			
			int n = pstmt.executeUpdate();
			
			System.out.println(n+"���� �ο� ����");
			
			if(pstmt!=null)pstmt.close();
			if(con!=null)con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}
