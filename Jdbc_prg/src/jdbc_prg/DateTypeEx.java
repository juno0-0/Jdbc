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
			System.out.println("접속 성공!");
			PreparedStatement pstmt = null;
			String sql = "insert into person values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 2);
			pstmt.setString(2, "아이유");
			pstmt.setDouble(3, 39.8);

			Date date = new Date();//java.util.Date
			
			//Oracle의 date형식과 연동되는 java의 Date는 java.sql.Date
			//java.util.Date클래스로는 오라클의 Date형식과 연동할 수 없다.
			//java.sql.Date birthday = new java.sql.Date(date.getTime());
			//getTime() : 현재 시간을 알아오는 메소드
			//pstmt.setDate(4, birthday);
			
			//java.sql.Date : 년, 월, 일까지
			//java.sql.Timestamp : 년, 월, 일, 시, 분, 초까지
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
