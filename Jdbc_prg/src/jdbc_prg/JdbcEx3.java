package jdbc_prg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcEx3 {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "System";
			String pw = "1234";
			Connection con = DriverManager.getConnection(url, user, pw);
			System.out.println("접속 성공!!!");
			
			String sql = "select * from member2 order by no desc";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);//여기서 rs가 테이블의 로우들을 가지고 있다. 
			while(rs.next()) {//rs가 가지고 있는 테이블의 로우들을 하나씩 꺼내오는 것
				int num = rs.getInt("no");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String addr = rs.getString("addr");
				System.out.println(num+". "+name+", "+hp+", "+addr);
			}
			if(rs!=null)rs.close();
			if(st!=null)st.close();
			if(con!=null)con.close();			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
