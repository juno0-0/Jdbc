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
	
	//db�� �����ϴ� �޼ҵ�
	public void dbConn() {
		try {
			//OracleDriver�� �޸𸮿� �ε�, DriverManager�� ���
			Class.forName(driver);//�ش� ����̹��� ���� ��츦 ����ؼ� ����ó��
			con = DriverManager.getConnection(url, id, pw);//������ �ȵǴ� ��츦 ����ؼ� ����ó��
			System.out.println("���� ����!");
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
	}//end dbConn
	
	//SQL���� ����� �޼ҵ�
	public void makeSql() throws SQLException, IOException {
		buf = new BufferedReader(new InputStreamReader(System.in));
		//InputStreamReader : ����Ʈ ��Ʈ���� ���� ��Ʈ������ ����
		//BufferedReader : �޸� ���ۿ��� �����ߴٰ� ������� ����(�ӵ����)
		//����Ʈ ��Ʈ���� ���� ��Ʈ������ �����ؼ� �޸� ���ۿ� �����ߴٰ� ������� ������ ��
		
		String sql = "";
		System.out.print("SQL�� �Է��ϼ��� : ");
		st = con.createStatement();
		
		while((sql = buf.readLine()) != null) {
			int cnt = st.executeUpdate(sql.trim());//�� ���� ���ڵ尡 �߰��Ǿ����� ����
			System.out.println(cnt+"���� ���ڵ尡 �����Ǿ���");
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
