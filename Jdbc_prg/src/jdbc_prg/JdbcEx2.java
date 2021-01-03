package jdbc_prg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcEx2 {
	Connection con;
	Statement st;
	BufferedReader buf;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "System";
	String pw = "1234";
	
	public JdbcEx2() throws SQLException, IOException {
		dbConn();
		makeSql();
		close();
	}//end Construct
	
	//db에 접속하는 메소드
	public void dbConn() {
		try {
			//OracleDriver를 메모리에 로딩, DriverManager에 등록
			Class.forName(driver);//해당 드라이버가 없을 경우를 대비해서 예외처리
			con = DriverManager.getConnection(url, id, pw);//연결이 안되는 경우를 대비해서 예외처리
			System.out.println("접속 성공!");
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
	}//end dbConn
	
	//SQL문을 만드는 메소드
	public void makeSql() throws SQLException, IOException {
		buf = new BufferedReader(new InputStreamReader(System.in));
		//InputStreamReader : 바이트 스트림을 문자 스트림으로 변경
		//BufferedReader : 메모리 버퍼에서 보관했다가 고속으로 들어옴(속도향상)
		//바이트 스트림을 문자 스트림으로 변경해서 메모리 버퍼에 보관했다가 고속으로 들어오는 것
		
		String sql = "";
		System.out.print("SQL문 입력하세요 : ");
		st = con.createStatement();
		
		while((sql = buf.readLine()) != null) {
			int cnt = st.executeUpdate(sql.trim());//몇 개의 레코드가 추가되었는지 리턴
			System.out.println(cnt+"개의 레코드가 수정되었음");
		}//end while 
	}//end makeSql
	
	public void close() throws SQLException, IOException {
		if(con!=null)con.close();
		if(st!=null)st.close();
		if(buf!=null)buf.close();
	}
	
	
	public static void main(String[] args) {
		try {
			new JdbcEx2();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//end main
}
