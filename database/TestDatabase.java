package database;

import java.util.ArrayList;

public class TestDatabase {

	public static void main(String[] args) {

		Database database = new Database();

		//database.writeUserToDatabase("Ten", "000000");

		//Query - Select username and decrypted password columns from the table
		String query = "SELECT username, aes_decrypt(password,'key') FROM GameUser;";

		ArrayList<String> arrayList = database.query(query);

		for (String i : arrayList) {
			System.out.println(i);
		}
		//int score = Database.getScore("Joseph");

		//Database.updateScores("Ha", 5);
	}

}
