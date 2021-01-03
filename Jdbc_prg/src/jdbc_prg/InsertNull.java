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
			System.out.println("���� �Ϸ�!");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
			pstmt = con.prepareStatement(sql);
			//preparedStatement�� setNull(index, Types.INTEGER)
			//������ Ÿ���� null�� ���� : java.sql.Types.INTEGER -> INTEGER = ����� ���ǵǾ� �ִ� ���
			//pstmt.setInt(1, new Integer(null)); ����� �� ����
			//�Ǽ��� Ÿ���� null�� ���� : java.sql.Types.DOUBLE
			//������ Ÿ���� null�� ���� : java.sql.Types.NUMERIC -> ���� or �Ǽ��� ������ �� �ִ�
			//������ Ÿ���� null�� ���� : java.sql.Types.VARCHAR / setString(pindex, null)�� �������
			
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
