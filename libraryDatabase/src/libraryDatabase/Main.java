package libraryDatabase;

import java.sql.*;

public class Main {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/sys";

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
			sql = "SELECT Book_number, Book_name, Author_number, Pub_number, Date_published FROM BOOK";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				int Book_number = rs.getInt("Book_number");
				String Book_name = rs.getString("Book_name");
				int Author_number = rs.getInt("Author_number");
				int Pub_number = rs.getInt("Pub_number");
				String Date_published = rs.getString("Date_published");

				System.out.print("Book number: " + Book_number);
				System.out.print(", Book name: " + Book_name);
				System.out.print(", Author number: " + Author_number);
				System.out.print(", Publisher number: " + Pub_number);
				System.out.println(", Date published: " + Date_published);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			} catch(SQLException se2){
			} try{
				if(conn!=null)
					conn.close();
			} catch(SQLException se){
				se.printStackTrace();
			}
		}
	}
}