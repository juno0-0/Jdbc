package jdbc_prg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementEx1 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "system";
		String pw = "1234";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,id,pw);
			System.out.println("���� ����");
			String sql = "select count(*) from member2 where addr like ?";
			String addr = args[0];
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, addr);//ù��° �Ű������� ����ǥ ����, �ι�°�� ��
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int n = rs.getInt(1);
			if(n>0) {					
				System.out.println(addr+"������ ��� ����� "+n+"��");
				//addr�� ��� ����� ������ ���
				String sql2 = "select * from member2 where addr = ?";
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, addr);
				ResultSet rs2 = pstmt2.executeQuery();
				while(rs2.next()) {			
					int no = rs2.getInt("no");
					String name = rs2.getString("name");
					String hp = rs2.getString("hp");
					String address = rs2.getString("addr");
					System.out.println(no+" "+name+" "+hp+" "+address);
				}
				if(rs2!=null)rs2.close();
				if(pstmt2!=null)pstmt2.close();			
			}else {
				System.out.println(addr+"������ ��� ȸ���� �����ϴ�.");
			}
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null)con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
