package jdbc_prg;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

public class DateTypeEx2 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "system";
		String pw = "1234";
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,id,pw);
			System.out.println("���� ����!");
			PreparedStatement pstmt = null;
			String sql = "select * from person where name = '������'";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				double weight = rs.getDouble("weight");
				Date date = rs.getDate("birthday");//��¥��
				Timestamp ts = rs.getTimestamp("birthday");//��¥�� �ð�
				Time time = rs.getTime("birthday");//�ð���
				System.out.println(no+". "+name+" "+weight+"kg "+date+"\n"+ts+"\n"+time);
			}
			if(rs!=null)rs.close();
			if(con!=null)con.close();
			if(pstmt!=null)pstmt.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}
