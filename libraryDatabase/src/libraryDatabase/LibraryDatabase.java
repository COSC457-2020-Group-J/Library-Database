package libraryDatabase;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryDatabase {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/sys";

	static final String USER = "root";
	static final String PASS = "password";

	static final String[][] columns = new String[][] {
		{"Book_number", "Book_name", "Author_number", "Pub_number", "Date_published"},
		{"Pub_number", "Pub_name", "City", "Country", "Telephone"},
		{"Author_number", "Person_number"},
		{"Borrow_number", "Person_number", "Book_number", "Date_borrowed", "Date_due"},
		{"Person_number", "First_name", "Last_name", "City", "Country"},
		{"Branch_number", "City", "Country", "Telephone"},
		{"Lib_number", "Person_number", "Branch_number", "Wage"},
		{"Journal_number", "Author_number", "News_number", "Journal_name"},
		{"Article_number", "Author_number", "News_number", "Article_name"},
		{"News_number", "Date_published"}
	};

	static final String[] tableNames = new String[] {"Book", "Publisher", "Author",
			"Borrower", "Person", "Branch", "Librarian",
			"Journal", "Article", "Newsletter"};

	JButton[] tableBtns = new JButton[tableNames.length];

	public static void main(String[] args) {
		LibraryDatabase library = new LibraryDatabase();
		library.createTableSelectionUI();
	}

	public void createLoginUI() {
		JFrame frame = new JFrame("Library Database Login");
		JPanel panel = new JPanel();
		JTextField userField = new JTextField();
		JPasswordField passField = new JPasswordField();
		JButton loginBtn = new JButton();
	}

	public void createTableSelectionUI() {
		JFrame frame = new JFrame("Library Database Tables");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		for (int i = 0; i < tableNames.length; i++) {
			tableBtns[i] = new JButton(tableNames[i].substring(0, 1).toUpperCase() + tableNames[i].substring(1).toLowerCase() + " table");
			final int j = i;
			tableBtns[i].addActionListener(ae -> createTableUI(columns[j], tableNames[j]));
			frame.add(tableBtns[i]);
		}
		frame.setVisible(true);
		frame.setSize(720, 120);
	}

	private void createTableUI(String[] columns, String tableName) {
		JFrame frame = new JFrame(tableName.substring(0, 1).toUpperCase() + tableName.substring(1).toLowerCase() + " table");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		JTable table = new JTable();
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
			String sql = "select * from " + tableName.toUpperCase();
			Statement stmt = conn.createStatement();
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
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		frame.add(scroll);
		frame.setVisible(true);
		frame.setSize(720, 360);
	}
}