package jdbc_prg;

import java.sql.*;

public class JdbsEx1 {
	public static void main(String[] args) {
		
		
		//1. ����̹� �ε�
		//java.lang��Ű���� �ִ� Ŭ���� --> Class�� �̿��Ͽ� jdbc ����̹��� �ε�
		//Class.forName("Ŭ���� �̸�"); <- �������ε� �޼ҵ�(���α׷� ����� ����̹��� �ε�)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹��� �ε��߽��ϴ�.");
			
			//2. �����ͺ��̽��� ����
			//�����ϱ� ���ؼ��� url�� �ʿ��ϱ� ������ url ��ġ �����ϱ�, DBMS�� ��ġ�Ǿ� �ִ� ip�ּҰ� �ʿ�
			//thin : �ڹٷθ� ������ ����̹� Ÿ��
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String user = "System";
			String pw = "1234";
			//DriverManager : �޸𸮿� �ε��� ����̹��� �����ϴ� Ŭ����
			Connection con = DriverManager.getConnection(url, user, pw);//�����ͺ��̽��� ������ �õ�
			System.out.println("�����ͺ��̽��� ���� ����!!!");
			
			//3. �����ͺ��̽��� sql���� ������ �ϱ� ���� ������ �ۼ�(Statement ��ü)
			String sql = "select name from Member";
			Statement st = con.createStatement();//Connection�� ��ü�� con�� ���� ������ ������ �ִ�.
												 //createStatement() : Statement ��ü�� ����
												 //�� �κ��� OutputStream�� ����
			
			//4. �����ͺ��̽��� sql���� ����
			//excuteQuery()�� ���ǹ� ����, ������ �������� ������� ��ȯ�� ���� ResultSet���� ����
			ResultSet rs = st.executeQuery(sql);
			
			//5. �����ͺ��̽����� �����͸� �����ͼ� ���
			while(rs.next()) {
				String name = rs.getString(1);
				System.out.println(name);
			}
			
			//6. �����ͺ��̽��� ����� �ڿ��� �ݳ�(close())
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
