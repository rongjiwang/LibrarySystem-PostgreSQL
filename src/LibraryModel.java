
/*
 * LibraryModel.java
 * Author:
 * Created on:
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LibraryModel {

	// For use in creating dialogs and making them modal
	private JFrame dialogParent;
	private String userid;
	private String password;
	// link connection
	private Connection con;
	private String url;
	private JOptionPane quitPanel;

	public LibraryModel(JFrame parent, String userid, String password) {
		dialogParent = parent;
		quitPanel = new JOptionPane();
		this.userid = userid;
		this.password = password;
		try {
			Class.forName("org.postgresql.Driver");
			url = "jdbc:postgresql:" + "//db.ecs.vuw.ac.nz/" + userid + "_jdbc";

		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: CANNOT CREATE DRIVE");
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, userid, password);
		} catch (SQLException e) {
			System.out.println("ERROR: WRONG USER NAME OR WRONG PASSWORD");
			quitPanel.showMessageDialog(dialogParent, "ERROR: WRONG USER NAME OR WRONG PASSWORD");
			System.exit(0);
		}
	}

	public String bookLookup(int isbn) {
		int ans = 0;
		String title = "";
		Statement s = null;
		try {
			con.setAutoCommit(false);
			s = con.createStatement();
		} catch (SQLException sqlex) {
			System.out.println(
					"An exception" + "while creating a statement," + "probably means I am no longer" + "connected");
		}
		ResultSet rs = null;
		try {

			rs = s.executeQuery("SELECT * FROM BOOK WHERE isbn = " + isbn);
		} catch (SQLException sqlex) {
			System.out.println("An exception" + "while executing a query, probably" + "means my SQL is invalid");
		}

		try {
			while (rs.next()) {
				 ans = rs.getInt("isbn");
				 title = rs.getString("title");
			}
		} catch (SQLException sqlex) {
			System.out.println("An exception" + "while processing a result, probably" + "means I have done something"
					+ "really bad");
		}
		try {
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			System.out.println("ERROR: PUBLISH COMMIT ISSUE");
			e.printStackTrace();
		}
		return (""+ans+" "+title);

	}

	public String showCatalogue() {
		return "Show Catalogue Stub";
	}

	public String showLoanedBooks() {
		return "Show Loaned Books Stub";
	}

	public String showAuthor(int authorID) {
		return "Show Author Stub";
	}

	public String showAllAuthors() {
		return "Show All Authors Stub";
	}

	public String showCustomer(int customerID) {
		return "Show Customer Stub";
	}

	public String showAllCustomers() {
		return "Show All Customers Stub";
	}

	public String borrowBook(int isbn, int customerID, int day, int month, int year) {
		return "Borrow Book Stub";
	}

	public String returnBook(int isbn, int customerid) {
		return "Return Book Stub";
	}

	public void closeDBConnection() {
	}

	public String deleteCus(int customerID) {
		return "Delete Customer";
	}

	public String deleteAuthor(int authorID) {
		return "Delete Author";
	}

	public String deleteBook(int isbn) {
		return "Delete Book";
	}
}