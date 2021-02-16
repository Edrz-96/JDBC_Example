package com.qa.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.qa.util.DatabaseConfig;

public class DBConnector {

	private Statement statement;
	private Connection connect;

	public DBConnector() throws SQLException {
		// This is going to open our connection!
		// (jdbc:mysql:localhost;etc, root, root)
		connect = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
		this.statement = connect.createStatement();
		// statements.executeUpdate("sting of sql");
		// statements.executeQUery(sql);
	}

	// Create
	public void createPerson(String first, String last) throws SQLException {
		String sql = String.format("INSERT INTO Customer(`firstname`, `lastname`) VALUES ('%s', '%s');", first, last);
		statement.executeUpdate(sql);
	}

	// Read all
	public void readPeople() throws SQLException {
		String sql = "SELECT * FROM Customer";
		ResultSet rs = statement.executeQuery(sql);

		while (rs.next()) {
			System.out.println(String.format("ID: %d, Firstname: %s, Lastname: %s", rs.getInt("custID"),
					rs.getString("firstname"), rs.getString("lastname")));

		}
	}

	// Update
	public void updatePerson(int id, String fname, String lname) throws SQLException {
		String sql = String.format("\"UPDATE customer SET `firstname`= '%s', `lastname`='%s' WHERE custID='%d';", fname,
				lname, id);
		statement.executeUpdate(sql);
	}

	// Delete
	public void deletePerson(int id) throws SQLException {
		String sql = String.format("DELETE FROM customer where custID = '%d';", id);
		statement.executeUpdate(sql);
	}

}
