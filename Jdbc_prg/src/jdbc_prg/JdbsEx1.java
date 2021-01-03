package jdbc_prg;

import java.sql.*;

public class JdbsEx1 {
	public static void main(String[] args) {
		
		
		//1. 드라이버 로딩
		//java.lang패키지에 있는 클래스 --> Class를 이용하여 jdbc 드라이버를 로딩
		//Class.forName("클래스 이름"); <- 동적바인딩 메소드(프로그램 실행시 드라이버를 로딩)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버를 로딩했습니다.");
			
			//2. 데이터베이스와 연결
			//연결하기 위해서는 url이 필요하기 때문에 url 위치 설정하기, DBMS가 설치되어 있는 ip주소가 필요
			//thin : 자바로만 구현된 드라이버 타입
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String user = "System";
			String pw = "1234";
			//DriverManager : 메모리에 로딩한 드라이버를 관리하는 클래스
			Connection con = DriverManager.getConnection(url, user, pw);//데이터베이스에 접속을 시도
			System.out.println("데이터베이스에 접속 성공!!!");
			
			//3. 데이터베이스에 sql문을 전송을 하기 위한 쿼리문 작성(Statement 객체)
			String sql = "select name from Member";
			Statement st = con.createStatement();//Connection의 객체인 con은 연결 정보를 가지고 있다.
												 //createStatement() : Statement 객체를 리턴
												 //이 부분이 OutputStream의 역할
			
			//4. 데이터베이스에 sql문을 전송
			//excuteQuery()로 질의문 전송, 전송한 데이터의 결과값을 반환한 것을 ResultSet으로 받음
			ResultSet rs = st.executeQuery(sql);
			
			//5. 데이터베이스에서 데이터를 가져와서 출력
			while(rs.next()) {
				String name = rs.getString(1);
				System.out.println(name);
			}
			
			//6. 데이터베이스와 연결된 자원을 반납(close())
			if(rs!=null)rs.close();
			if(st!=null)st.close();
			if(con!=null)con.close();		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 




		 
	}
}
