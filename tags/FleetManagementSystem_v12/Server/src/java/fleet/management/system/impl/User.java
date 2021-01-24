package fleet.management.system.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class User {

	public FileManager fm;
	private FMS fms;
	private FMSConsole fmsCon3;
	String username;
	String businessID;

	User() {
		fm = new FileManager();

	}

	User(String username, String businessID) {
		try {
			fm = new FileManager();
			this.username = username;
			this.businessID = businessID;
			loginCheck(username, businessID);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public boolean createuser(String username, String businessID, String email) throws IOException {
		this.username = username;
		this.businessID = businessID;
		boolean s;
		try {

			FileWriter writer = new FileWriter(FileManager.dir_userAcc+"Users.txt", true);

			writer.write(username);
			writer.write(System.getProperty("line.separator"));
			writer.write(businessID);
			writer.write(System.getProperty("line.separator"));
			writer.write(email);
			writer.write(System.getProperty("line.separator"));
			s = true;

			writer.close();
		} catch (Exception ex) {
			s = false;
		}

		// modification
		// save();
		return s;
	}

	public int loginCheck(String username, String businessID) throws IOException {
		this.username = username;
		this.businessID = businessID;

		FileInputStream fstream = new FileInputStream(FileManager.dir_userAcc+"Users.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		int check = 0;
		String strusername;
		while ((strusername = br.readLine()) != null) { // starting while loop

			String usernameobj = strusername;
			String businessIDobj = br.readLine();
			String emailidobj = br.readLine();

			if ((usernameobj.equals(username)) && (businessIDobj.equals(businessID))) {
				check = 1;

			}

		}

		br.close();
		return check;
	}

	public boolean delete_0(String username) {
		boolean successfull = true;
		this.username = username;

		fms.getFleetRegister().removeItem(username);

		return successfull;
	}

	public boolean deleteuser(String username, PrintWriter out) throws IOException {
		boolean successful = false;
		Scanner scanner;
		this.username = username;
		FileInputStream fstream = new FileInputStream(FileManager.dir_userAcc+"Users.txt");

		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		FileWriter writer = new FileWriter(FileManager.dir_root+"temp.txt", false);

		int check = 0;
		String strLine;
		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			String usernameobj = strLine;
			String businessIDobj = br.readLine();
			String emailidobj = br.readLine();

			if ((usernameobj.equals(username))) {
				out.println("Username = " + this.username + " Successfuly Deleted ");
				out.println("Type 'help' for Options");
				successful = true;
			} else {
				// System.out.println("inside else");
				writer.write(usernameobj);
				writer.write(System.getProperty("line.separator"));
				writer.write(businessIDobj);
				writer.write(System.getProperty("line.separator"));
				writer.write(emailidobj);
				writer.write(System.getProperty("line.separator"));
			}

		}
		writer.close();
		br.close();
		fm.filecopy(FileManager.dir_userAcc+"Users.txt");
		return successful;
	}

	public String getusername() {
		return username;
	}

	public void loginOptions() {

	}

}
