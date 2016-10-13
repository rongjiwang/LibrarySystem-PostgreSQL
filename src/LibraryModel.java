/*
 * LibraryModel.java
 * Author:
 * Created on:
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

public class LibraryModel {

	// For use in creating dialogs and making them modal
	private JFrame dialogParent;
	private String userid;
	private String password;
	// link connection
	private Connection con;
	private String url;

	public LibraryModel(JFrame parent, String userid, String password) {
		dialogParent = parent;
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
			System.out.println("ERROR: DRIVER CONNECTION ISSUE");
			e.printStackTrace();
		}
		// start a transaction (BEGIN POINT)
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("ERROR: SET CONNECTION TO BEGIN POINT");
			e.printStackTrace();
		}
	}

	public String bookLookup(int isbn) throws SQLException {
		String select = "SELECT * FROM BOOK WHERE isbn = " + isbn;
		PreparedStatement prstmt = con.prepareStatement(select);

		ResultSet rs = prstmt.executeQuery(select);

		int tempID = 0;
		String title;
		int edition_no;
		int numofcop;
		int numleft;

		while (rs.next()) {
			tempID = rs.getInt("isbn");
		}
		return "tempID" + " TEST";

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