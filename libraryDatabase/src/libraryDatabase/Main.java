package libraryDatabase;

import java.sql.*;

public class Main {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306";

	static final String USER = "root";
	static final String PASS = "password";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM *";
			ResultSet rs = stmt.executeQuery(sql);
			
			rs.close();
			stmt.close();
			conn.close();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}