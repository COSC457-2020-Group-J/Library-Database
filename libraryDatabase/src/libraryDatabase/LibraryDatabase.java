package libraryDatabase;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class LibraryDatabase {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/sys";

	static final String USER = "root";
	static final String PASS = "password";

	static final String[][] allColumns = new String[][] {
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
	
	static final Boolean[][] boolIsInt = new Boolean[][]{
		{true, false, true, true, false},
		{true, false, false, false, true},
		{true, true},
		{true, true, true, false, false},
		{true, false, false, false, false},
		{true, false, false, true},
		{true, true, true, true},
		{true, true, true, false},
		{true, true, true, false},
		{true, false}
	};

	static final String[] tableNames = new String[] {"Book", "Publisher", "Author",
			"Borrower", "Person", "Branch", "Librarian",
			"Journal", "Article", "Newsletter"};

	JButton[] tableBtns = new JButton[tableNames.length];

	public static void main(String[] args) {
		LibraryDatabase library = new LibraryDatabase();
		library.createLoginUI();
		//library.createTableUI(allColumns[0], tableNames[0], boolIsInt[0]);
	}

	public void createLoginUI() {
		JFrame frame = new JFrame("Library Database Login");
		JPanel panel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setContentPane(panel);
		frame.setLayout(null);
		JLabel loginLabel = new JLabel("Library Database Login");
		loginLabel.setBounds(170, 20, 180, 30);
		panel.add(loginLabel);
		JLabel userLabel = new JLabel("Username:");
		userLabel.setBounds(40, 60, 240, 30);
		panel.add(userLabel);
		JTextField userField = new JTextField();
		userField.setBounds(120, 60, 240, 30);
		panel.add(userField);
		userField.setColumns(10);
		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(40, 100, 240, 30);
		panel.add(passLabel);
		JPasswordField passField = new JPasswordField();
		passField.setBounds(120, 100, 240, 30);
		panel.add(passField);
		JButton loginBtn = new JButton("Login");
		loginBtn.setBounds(120, 140, 240, 30);
		loginBtn.addActionListener(ae -> {
			String username = userField.getText();
			@SuppressWarnings("deprecation")
			String password = passField.getText();
			try {
				Class.forName(JDBC_DRIVER);
				Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				String sql = "select Username, Password from USER where Username=? and Password=?";
				PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
				stmt.setString(1, username);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					rs.close();
					stmt.close();
					conn.close();
					createTableSelectionUI();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		panel.add(loginBtn);
		frame.setVisible(true);
		frame.setSize(480, 240);
	}

	private void createTableSelectionUI() {
		JFrame frame = new JFrame("Library Database Tables");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		for (int i = 0; i < tableNames.length; i++) {
			tableBtns[i] = new JButton(tableNames[i].substring(0, 1).toUpperCase() + tableNames[i].substring(1).toLowerCase() + " table");
			final int j = i;
			tableBtns[i].addActionListener(ae -> createTableUI(allColumns[j], tableNames[j], boolIsInt[j]));
			frame.add(tableBtns[i]);
		}
		frame.setVisible(true);
		frame.setSize(720, 120);
	}

	private void createTableUI(String[] columns, String tableName, Boolean[] isInt) {
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
		frame.add(scroll, BorderLayout.CENTER);

		JLabel[] columnLabels = new JLabel[columns.length];
		JTextField[] columnFields = new JTextField[columns.length];
		JPanel columnPanel = new JPanel();
		columnPanel.setLayout(new FlowLayout());
		columnPanel.setPreferredSize(new Dimension(120, 480));
		for (int i = 0; i < columns.length; i++) {
			columnLabels[i] = new JLabel(columns[i]);
			columnFields[i] = new JTextField();
			columnFields[i].setColumns(10);
			columnPanel.add(columnLabels[i]);
			columnPanel.add(columnFields[i]);
		}

		JButton add = new JButton("Add");
		add.addActionListener(ae -> {
			String concatColumns = "";
			String allValues = "";
			for (int i = 1; i < columns.length; i++) {
				concatColumns += columns[i] + ", ";
				if (isInt[i])
					allValues += columnFields[i].getText() + ", ";
				else
					allValues += "\'" + columnFields[i].getText() + "\', ";
			}
			concatColumns = concatColumns.substring(0, concatColumns.length() - 2);
			allValues = allValues.substring(0, allValues.length() - 2);
			try {
				Class.forName(JDBC_DRIVER);
				Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				String sql = "INSERT INTO " + tableName.toUpperCase() + "(" + concatColumns + ") VALUES(" + allValues + ")";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		JButton update = new JButton("Update");
		JButton delete = new JButton("Delete");

		columnPanel.add(add);
		columnPanel.add(update);
		columnPanel.add(delete);

		frame.add(columnPanel, BorderLayout.LINE_START);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(720, 480);
	}
}