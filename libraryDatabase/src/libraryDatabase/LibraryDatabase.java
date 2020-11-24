package libraryDatabase;

import java.sql.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class LibraryDatabase extends JFrame {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/sys";

	static final String USER = "root";
	static final String PASS = "password";
	
	static JFrame frame;
	static JTable table;
	
	static String[] bookColumns = new String[] {"Book_number", "Book_name", "Author_number", "Pub_number", "Date_published"};
	static String[] pubColumns = new String[] {"Pub_number", "Pub_name", "City", "Country", "Telephone"};
	static String[] authorColumns = new String[] {"Author_number", "Person_number"};
	static String[] borrowerColumns = new String[] {"Borrow_number", "Person_number", "Book_number", "Date_borrowed", "Date_due"};
	static String[] personColumns = new String[] {"Person_number", "First_name", "Last_name", "City", "Country"};
	static String[] branchColumns = new String[] {"Branch_number", "City", "Country", "Telephone"};
	static String[] librarianColumns = new String[] {"Lib_number", "Person_number", "Branch_number", "Wage"};
	static String[] journalColumns = new String[] {"Journal_number", "Author_number", "News_number", "Journal_name"};
	static String[] articleColumns = new String[] {"Article_number", "Author_number", "News_number", "Article_name"};
	static String[] newsletterColumns = new String[] {"News_number", "Date_published"};

	public static void main(String[] args) {
		LibraryDatabase library = new LibraryDatabase();
		System.out.println("Choose table to display:\n1. Book\n2. Publisher\n3. Author\n4. Borrower\n5. Person\n6. Branch\n7. Librarian\n8. Journal\n9. Article\n10. Newsletter\n0: Exit program");
		Scanner scan = new Scanner(System.in);
		int input = -1;
		while (input != 0) {
			input = scan.nextInt();
			switch (input) {
			case 1: library.createUI(frame, table, bookColumns, "book"); break;
			case 2: library.createUI(frame, table, pubColumns, "publisher"); break;
			case 3: library.createUI(frame, table, authorColumns, "author"); break;
			case 4: library.createUI(frame, table, borrowerColumns, "borrower"); break;
			case 5: library.createUI(frame, table, personColumns, "person"); break;
			case 6: library.createUI(frame, table, branchColumns, "branch"); break;
			case 7: library.createUI(frame, table, librarianColumns, "librarian"); break;
			case 8: library.createUI(frame, table, journalColumns, "journal"); break;
			case 9: library.createUI(frame, table, articleColumns, "article"); break;
			case 10: library.createUI(frame, table, newsletterColumns, "newsletter"); break;
			default: break;
			}
		}
	}
	
	public void createUI(JFrame frame, JTable table, String[] columns, String tableName) {
		frame = new JFrame(tableName.substring(0, 1).toUpperCase() + tableName.substring(1).toLowerCase() + " table");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		String[] values = new String[columns.length];
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = null;
			String sql = "select * from " + tableName.toUpperCase();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				for (int i = 0; i < columns.length; i++) {
					values[i] = rs.getString(columns[i]);
				}
				model.addRow(values);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		frame.add(scroll);
		frame.setVisible(true);
		frame.setSize(720, 360);
	}
}