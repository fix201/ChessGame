package testing;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import database.Database;

public class DatabaseTest {

	//Variables
	Database db;
	private int randno;
	private String fn = "database/db.properties";

	//Test variables
	String[] users = { "jsmith@uca.edu", "msmith@uca.edu", "tjones@yahoo.com", "jjones@yahoo.com" };
	String[] passwords = { "hello123", "pass123", "123456", "hello1234" };
	private int rando;

	@Before
	public void setUp() throws Exception {
		db = new Database();
		db.setConnection(fn);
		rando = ((int) Math.random() * users.length);
	}

	//Test set connection method
	@Test
	public void testSetConnection() {
		Object obj = db.getConnection();
		assertNotNull("Check setConnection", obj);
	}

	//Test writeUserToDatabase method
	@Test
	public void testWriteUserToDatabase() throws Exception {

		//Open connection to database
		db.setConnection(fn);
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		//Random username (unique to some extent)
		java.util.Random r = new java.util.Random();
		String username = "Usr-" + Long.toString(Math.abs(r.nextInt()), 5);
		String password = "000000";

		boolean user = db.writeUserToDatabase(username, password);

		//Should be true if the user has been successfully written in the database
		assertTrue("Check writeUserToDabase method", user);
	}

	//Test usernameInUse method
	@Test
	public void testUsernameInUse() throws Exception {
		//Open connection to database
		db.setConnection(fn);
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		//Pick a random username that is in use
		String username = users[randno];
		boolean inUse = db.usernameInUse(username);

		//Should be true if the username is in use
		assertTrue("Check usernameInUse method", inUse);
	}

	//Test loginSuccessful method
	@Test
	public void testLoginSuccessful() throws Exception {
		//Open connection to database
		db.setConnection(fn);
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		String username = users[randno];
		String password = passwords[randno];

		boolean loginSuccess = db.loginSuccessful(username, password);

		//If log in is successful, the method should be true
		assertTrue("Check loginSuccessful method", loginSuccess);
	}

	//Test getScore method
	@Test
	public void testGetScore() throws Exception {

		//Open connection to database
		db.setConnection(fn);
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs;

		String username = users[randno];
		String query = "SELECT username, score FROM GameUser where username = '" + username + "';";

		//Create a statement
		stmt = conn.createStatement();
		//Execute selection query
		stmt.executeQuery(query);
		rs = stmt.getResultSet();

		int expectedScore = -1;

		while (rs.next()) {
			expectedScore = rs.getInt("score");
		}

		int actualScore = db.getScore(username);

		// Check if the expected score is the same as the actual score
		assertEquals("Check getScore method", expectedScore, actualScore);
	}

	//Test query method
	@Test
	public void testQuery() throws Exception {

		String password = passwords[randno];
		String query = "select * from GameUser";

		ArrayList<String> expected = new ArrayList<String>();
		Database exp = new Database();

		Connection conn = db.getConnection();
		Statement stmt;
		ResultSet rs;

		try {
			//Create a statement
			stmt = conn.createStatement();
			//Take input parameter query and use in executeQuery()
			stmt.execute(query);
			rs = stmt.executeQuery(query);
			//Take the resultSet and extract each column in each row
			while (rs.next()) {
				//Create a String from the columns - concatnate to create a comma delimited String field1, field2...field3
				String columns = rs.getString(1) + "," + rs.getString(2);
				//Store each String in the ArrayList
				expected.add(columns);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Compare expected and actual query method 
		ArrayList<String> actual = db.query(query);
		assertEquals("Check query", expected, actual);
	}

}
