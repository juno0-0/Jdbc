package jdbc_prg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTypeEx {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "system";
			String pw = "1234";
			Connection con = DriverManager.getConnection(url,id,pw);
			System.out.println("���� ����!");
			PreparedStatement pstmt = null;
			String sql = "insert into person values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 2);
			pstmt.setString(2, "������");
			pstmt.setDouble(3, 39.8);

			Date date = new Date();//java.util.Date
			
			//Oracle�� date���İ� �����Ǵ� java�� Date�� java.sql.Date
			//java.util.DateŬ�����δ� ����Ŭ�� Date���İ� ������ �� ����.
			//java.sql.Date birthday = new java.sql.Date(date.getTime());
			//getTime() : ���� �ð��� �˾ƿ��� �޼ҵ�
			//pstmt.setDate(4, birthday);
			
			//java.sql.Date : ��, ��, �ϱ���
			//java.sql.Timestamp : ��, ��, ��, ��, ��, �ʱ���
			//Timestamp birthday_time = new Timestamp(new Date().getTime());
			//pstmt.setTimestamp(4, birthday_time);
			
			Time birthday_time2 = new Time(new Date().getTime());
			pstmt.setTime(4, birthday_time2);
			pstmt.executeUpdate();
			
			
			if(con!=null)con.close();
			if(pstmt!=null)pstmt.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
