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
			System.out.println("접속 성공");
			st = con.createStatement();
			BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
			String sql = "";
			System.out.print("SQL문을 입력하세요 : ");
			while((sql = buf.readLine())!=null) {
				boolean b = st.execute(sql);//리턴값이 뭐가 올지 모를 경우 사용
											//true면 ResultSet타입이 리턴
											//false면 int타입이 리턴
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
					System.out.println(cnt+"개의 로우가 변경되었습니다.");
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
