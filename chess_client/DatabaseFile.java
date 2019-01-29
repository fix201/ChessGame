package chess_client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

public class DatabaseFile {
	private File file = null;
	private FileOutputStream fileout = null;
	private FileInputStream filein = null;
	private int z = 0;
	private int v = 0;
	@SuppressWarnings("rawtypes")
	private Vector usrnme = new Vector();
	@SuppressWarnings("rawtypes")
	private Vector pss = new Vector();

	public DatabaseFile(boolean bool) throws IOException {
		// TODO Auto-generated constructor stub
		file = new File("UsernamePassword.txt");
		if (!file.exists()) {
			file.createNewFile();
		} else if (bool)
			file.delete();
	}

	public void writeCredentials(String username, String password) throws IOException {

		try {
			String saveString = username + "/" + password;
			fileout = new FileOutputStream(file, true);
			filein = new FileInputStream(file);
			fileout.flush();
			String check = "|";
			byte[] textToSaveBytes = saveString.getBytes();
			byte[] textTocheckBytes = check.getBytes();
			fileout.write(textTocheckBytes);
			fileout.write(textToSaveBytes);

			fileout.close();
			// TODO Auto-generated catch block

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public boolean checkCredentials(String username, String password) throws IOException {
		filein = new FileInputStream(file);

		int i = 0;
		char c;
		String s = "";

		StringBuffer fileContent = new StringBuffer("");

		byte[] buffer = new byte[1024];

		while ((i = filein.read(buffer)) != -1) {
			fileContent.append(new String(buffer, 0, i));
		}

		int count = 0;
		for (int z = this.z; z < fileContent.length(); z++) {
			if (fileContent.charAt(z) == '|' || z == 0) {
				for (int j = z + 1; j < fileContent.length(); j++) {
					if (fileContent.charAt(j) == '/') {
						this.z = j;
						break;
					}
					c = (fileContent.charAt(j));
					s = s + c;
					this.z = j;
				}
				if (usrnme.contains(s)) {
					return true;
				} else {
					usrnme.add(s);
					return false;
				}
			}
			count = z;
		}
		this.z = count;

		filein.close();
		return false;
	}

	public boolean checkLogin(String username, String password) throws IOException {

		filein = new FileInputStream(file);
		String usrpass = username + "/" + password;

		System.out.print("At the top " + usrpass);
		int i = 0;
		char c;
		String s = "";
		int count = 0;
		StringBuffer fileContent = new StringBuffer("");

		byte[] buffer = new byte[1024];

		while ((i = filein.read(buffer)) != -1) {
			fileContent.append(new String(buffer, 0, i));
		}

		for (int z = this.v; z < fileContent.length(); z++) {
			System.out.println(fileContent.length());
			if (fileContent.charAt(z) == '|' || z == 0) {
				for (int j = z + 1; j < fileContent.length(); j++) {
					if (fileContent.charAt(j) == '|') {
						this.z = j;
						break;
					}
					c = (fileContent.charAt(j));
					s = s + c;
					this.v = j;
				}

				if (usrpass.contains(s)) {
					System.out.println("In if " + s);
					return true;
				} else {
					if (z == fileContent.length())
						return false;
				}
			}
			count = z;
		}
		this.v = 0;

		System.out.print("AT THE END");
		filein.close();
		return false;
	}

	public String[] load_data() {
		String s = "";

		try {
			filein = new FileInputStream(file);
			int content;
			while ((content = filein.read()) != -1) {
				char c = (char) content;
				s = s + String.valueOf(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (filein != null)
					filein.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return s.split("\n");
	}

	@SuppressWarnings("finally")
	public void write_data(String s) {

		try {
			fileout = new FileOutputStream(file, true);
			filein = new FileInputStream(file);

			char[] c = s.toCharArray();

			for (int i = 0; i < c.length; i++) {
				fileout.write(((byte) c[i]));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileout.close();
				filein.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return;
		}

	}

}
