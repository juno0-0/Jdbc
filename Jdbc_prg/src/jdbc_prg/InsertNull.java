package jdbc_prg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

public class InsertNull {
	public static void main(String[] args) {
		String sql = "insert into person values(?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("접속 완료!");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
			pstmt = con.prepareStatement(sql);
			//preparedStatement의 setNull(index, Types.INTEGER)
			//정수형 타입을 null로 설정 : java.sql.Types.INTEGER -> INTEGER = 상수로 정의되어 있는 멤버
			//pstmt.setInt(1, new Integer(null)); 사용할 수 없음
			//실수형 타입을 null로 설정 : java.sql.Types.DOUBLE
			//숫자형 타입을 null로 설정 : java.sql.Types.NUMERIC -> 정수 or 실수를 저장할 수 있다
			//문자형 타입을 null로 설정 : java.sql.Types.VARCHAR / setString(pindex, null)도 상관없음
			
			pstmt.setNull(1, Types.INTEGER);
			pstmt.setString(2, null);
			pstmt.setNull(3, Types.DOUBLE);
			pstmt.setNull(4, Types.DATE);
			pstmt.executeUpdate();
			
			if(pstmt!=null)pstmt.close();
			if(con!=null)con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}
