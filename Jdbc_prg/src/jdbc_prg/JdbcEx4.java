package jdbc_prg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcEx4 {
	
	public static void main(String[] args) {
		Connection con;
		Statement st;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "system","1234");
			System.out.println("���� ����");
			st = con.createStatement();
			BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
			String sql = "";
			System.out.print("SQL���� �Է��ϼ��� : ");
			while((sql = buf.readLine())!=null) {
				boolean b = st.execute(sql);//���ϰ��� ���� ���� �� ��� ���
											//true�� ResultSetŸ���� ����
											//false�� intŸ���� ����
				ResultSet rs = null;
				int cnt = 0;
				if(b) {
					rs = st.getResultSet();
					while(rs.next()) {
						int no = rs.getInt("no");
						String name = rs.getString("name");
						String hp = rs.getString("hp");
						String addr = rs.getString("addr");
						System.out.println(no+". "+name+", "+hp+", "+"addr");
					}
				}else {
					cnt = st.getUpdateCount();
					System.out.println(cnt+"���� �ο찡 ����Ǿ����ϴ�.");
				}
				
				if(rs!=null)rs.close();
				if(st!=null)rs.close();
			}
			if(buf!=null)buf.close();
			if(con!=null)con.close();

		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	
	}
}
