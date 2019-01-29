package database;

import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;

public class Database {

	//Private variable declarations
	private static Connection conn;
	private static Statement stmt;
	private static ResultSet rs;
	private static ResultSetMetaData rmd;

	//Constructor
	public Database() {
		//Read from the properties named db.properties
		Properties prop = new Properties();
		FileInputStream fis = null;

		//Create Connection
		try {
			fis = new FileInputStream("database/db.properties");
			prop.load(fis);
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String pass = prop.getProperty("password");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//Add any other data fields you like – at least a Connection object is mandatory
	public void setConnection(String fn) throws Exception {
		//Read from the properties named db.properties
		Properties prop = new Properties();
		FileInputStream fis = null;

		//Create Connection
		fis = new FileInputStream(fn);
		prop.load(fis);
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password");
		conn = DriverManager.getConnection(url, user, pass);
	}

	public Connection getConnection() {
		return conn;
	}

	public boolean writeUserToDatabase(String username, String password) {

		String query = "INSERT INTO GameUser VALUES ('" + username.toString() + "',aes_encrypt('" + password.toString() + "','key'), 0);";

		try {
			//Create a statement
			stmt = conn.createStatement();
			//Insert username and password into the database
			stmt.execute(query);
			rs = stmt.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return true;
	}

	//Returns true if the user is in the database
	public boolean usernameInUse(String username) {

		String dbUsername;
		boolean user = false;
		String query = "SELECT username FROM GameUser;";

		try {
			//Create a statement
			stmt = conn.createStatement();
			//Execute selection query
			stmt.executeQuery(query);
			rs = stmt.getResultSet();

			while (rs.next()) {
				dbUsername = rs.getString("username");
				// Becomes true if user is found
				if (dbUsername.equalsIgnoreCase(username))
					user = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return user;
	}

	// Returns true if the username and password are valid
	public boolean loginSuccessful(String username, String password) {

		String dbUsername, dbPassword;
		String decrypt = "aes_decrypt('" + password.toString() + "','key')";
		boolean user = false;
		String query = "SELECT username, aes_decrypt(password,'key') FROM GameUser;";

		try {
			//Create a statementd
			stmt = conn.createStatement();
			//Execute selection query
			stmt.executeQuery(query);
			rs = stmt.getResultSet();

			while (rs.next()) {
				dbUsername = rs.getString("username"); //usernames in the database
				dbPassword = rs.getString("aes_decrypt(password,'key')"); //decrypted passwords in the database

				if (dbUsername.equalsIgnoreCase(username) && dbPassword.equals(password))
					user = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return user;
	}

	//Gets the score for a specific user
	public int getScore(String username) {

		String dbUsername;
		int dbScore;
		String query = "SELECT username, score FROM GameUser;";

		try {
			//Create a statement
			stmt = conn.createStatement();
			//Execute selection query
			stmt.executeQuery(query);
			rs = stmt.getResultSet();

			while (rs.next()) {
				dbUsername = rs.getString("username");
				dbScore = rs.getInt("score");
				// returns score
				if (dbUsername.equalsIgnoreCase(username))
					return dbScore;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		System.out.println("Get Score Failed");
		return 0;
	}

	// update user scores
	public void updateScores(String username, int score) {

		String query = "UPDATE GameUser SET score = " + (score += 1) + " WHERE username = '" + username + "';";

		try {
			//Create a statement
			stmt = conn.createStatement();
			//Execute selection query
			stmt.executeUpdate(query);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Update Success");
	}

	public ArrayList<String> query(String query) {
		ArrayList<String> arrayList = new ArrayList<String>();

		try {
			// Create a statement
			stmt = conn.createStatement();

			// Take input parameter query and use in executeQuery()
			stmt.execute(query);
			rs = stmt.executeQuery(query);

			// Take the resultSet and extract each column in each row
			while (rs.next()) {
				// Create a String from the columns - concatnate to create a comma delimited String field1, field2...field3
				String columns = rs.getString(1) + "," + rs.getString(2);
				// Store each String in the ArrayList
				arrayList.add(columns);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		if (arrayList.isEmpty())
			return null;
		return arrayList;
	}
}
