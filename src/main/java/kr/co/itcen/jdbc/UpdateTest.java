package kr.co.itcen.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {

	public static void main(String[] args) {
		update(1L,"총무 1팀");
		update(2L,"영업 1팀");
		update(3L,"인사 1팀");

	}
	public static Boolean update2(Long no, String name) {
		Boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;


		try {
			// 1.JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2.연결하기
			String url = "jdbc:mariadb://192.168.1.73:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3.SQL문 준비시키기
			String sql = "update department set name =? where no =?";
			pstmt =connection.prepareStatement(sql);

			//4.바인딩
			pstmt.setString(1, name);
			pstmt.setLong(2, no);
			
			//5.SQL문 실행
			
			int count =pstmt.executeUpdate(sql);
			
			result = (count==1);

			// 5.결과 가져오기

				System.out.println(  );

			
	

		} catch (ClassNotFoundException e) { // Driver 클래스가 클래스 path안에 없으면 error
			System.out.println("Fail to Loading Driver:" + e);
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			try {
				
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;

		
	}
	public static Boolean update(Long no, String name) {
		Boolean result = false;
		Connection connection = null;
		Statement stmt = null;


		try {
			// 1.JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2.연결하기
			String url = "jdbc:mariadb://192.168.1.73:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			

			// 3. Statment 객체 생성(받아오기)
			stmt = connection.createStatement();

			// 4.SQL문 실행
			String sql = "update department set name ='"+name+"' where no =" +  no;
			int count =stmt.executeUpdate(sql);
			
			result = (count==1);

			// 5.결과 가져오기


				System.out.println(  );

			
	

		} catch (ClassNotFoundException e) { // Driver 클래스가 클래스 path안에 없으면 error
			System.out.println("Fail to Loading Driver:" + e);
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			try {
				
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;

		
	}

}
