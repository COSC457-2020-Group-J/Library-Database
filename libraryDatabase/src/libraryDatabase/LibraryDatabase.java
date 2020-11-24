package libraryDatabase;

import java.sql.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryDatabase extends JFrame implements ActionListener {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/sys";

	static final String USER = "root";
	static final String PASS = "password";

	JButton[] buttons = new JButton[10];
	
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
		library.createTableSelectionUI();
	}
	
	public void createTableSelectionUI() {
		JFrame frame = new JFrame("Library Database Tables");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		DefaultTableModel model = new DefaultTableModel();
		
		String[] buttonNames = new String[] {"Book table", "Publisher table", "Author table",
				"Borrower table", "Person table", "Branch table", "Librarian table",
				"Journal table", "Article table", "Newsletter table"};
		
		for (int i = 0; i < 10; i++) {
			buttons[i] = new JButton(buttonNames[i]);
			buttons[i].setName(Integer.toString(i));
			buttons[i].addActionListener(this);
			frame.add(buttons[i]);
		}
		frame.setVisible(true);
		frame.setSize(720, 120);
	}
	
	@Override public void actionPerformed(ActionEvent ae) {
		int clickedBtn = -1;
		for (int i = 0; i < 10; i++) {
			if (ae.getSource() == buttons[i]) {
				clickedBtn = i;
				break;
			}
		}
		switch (clickedBtn) {
		case 0: createTableUI(bookColumns, "book"); break;
		case 1: createTableUI(pubColumns, "publisher"); break;
		case 2: createTableUI(authorColumns, "author"); break;
		case 3: createTableUI(borrowerColumns, "borrower"); break;
		case 4: createTableUI(personColumns, "person"); break;
		case 5: createTableUI(branchColumns, "branch"); break;
		case 6: createTableUI(librarianColumns, "librarian"); break;
		case 7: createTableUI(journalColumns, "journal"); break;
		case 8: createTableUI(articleColumns, "article"); break;
		case 9: createTableUI(newsletterColumns, "newsletter"); break;
		default: break;
		}
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
			JOptionPane.showMessageDialog(null,  e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		frame.add(scroll);
		frame.setVisible(true);
		frame.setSize(720, 360);
	}
}