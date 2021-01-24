package fleet.management.system.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import fleet.management.system.model.FleetItems;
import fleet.management.system.model.IFMS;

public class FMSConsole implements Runnable {
	// private static final String = null;
	private Map<String, FleetItems> items;
	private IFMS fms;
	private Scanner scanner;
	private FleetItems item;
	private User objuser;
	private String command;
	private PrintWriter out;
	private Scanner in;
	private ArrayList<Socket> links = new ArrayList<Socket>();
	private Socket link;

	public FMSConsole(PrintWriter out, Scanner in, ArrayList<Socket> links, Socket link) {
		objuser = new User();
		scanner = new Scanner(System.in);
		fms = new FMS();
		this.out = out;
		this.in = in;
		this.links = links;
		this.link = link;
	}

	public void run(PrintWriter out, Scanner in) throws IOException {
		while (true) {
			do {

				command = in.nextLine();
			
				if (command.equals("createuser")) {

					out.println("Enter user name:\t");
					out.flush();
					String username = in.nextLine();

					out.println("Enter user Business ID:\t");
					out.flush();
					String businessID = in.nextLine();

					out.println("Enter user emailid:\t");
					out.flush();
					String email = in.nextLine();

					out.flush();

					if (!objuser.createuser(username, businessID, email)) {
						out.println("user not created");
					}

					out.println(
							"Hi '" + username + "' your account has successfully created! \tType 'help' for Options");

				} else if (command.equals("deleteuser")) {
					out.println("please enter username\t");
					out.flush();
					String username = in.nextLine();
					out.flush();

					if (!objuser.deleteuser(username, out))
						out.println("user not found");
				} else if (command.equals("help")) {
					help(out);
				} else if (command.equals("login")) {

					int check;

					out.println("please enter username");

					out.flush();
					username = in.nextLine();

					out.println("please enter Business ID");

					out.flush();
					String businessID = in.nextLine();

					check = objuser.loginCheck(username, businessID);

					if (check == 1)// login successfull
					{
						username = objuser.getusername();
						out.println("Hello,  '" + username
								+ "'  You Successfully Logged In. \tType 'help' for More Options");

						for (Socket link : links) {
							if (!link.equals(this.link)) {
								PrintWriter os1 = new PrintWriter(link.getOutputStream(), true);

							}
						}

						if (new File(username+".txt").exists()) {
							list(out);
						} else

							new File(username+".txt").createNewFile();

						if (new File(username).exists()) {
							list(out);
						} else
							// fms.save(dir+username);
							fms.save(username);
						do {

							out.println("Type 'help' for Options");

							out.flush();
							command = in.nextLine();

							if (command.equals("help")) {
								out.println("'Type Options' [addMachine, listMachine, deleteMachine, logout,]");
							} else if (command.equals("addMachine")) {
								out.println("My Machines");
								open();
								out.print(out);
								//list(out);
								add(out, in);
								save();
								out.println("Machine Saved");
							} else if (command.equals("deleteMachine")) {
								out.println("Machine Name: ");
								out.flush();
								String machinename = in.nextLine();
								delete(machinename, out);
								out.println("Machine Name,  '" + machinename + "'  Successfully Deleted.");
								out.println("Type 'help' for Options");

							}

							else if (command.equals("listMachine")) {
								open();
								out.println(out);
								list(out);
								//save();
							}

						} while (!(command.equals("logout")));
						for (Socket link : links) {
							if (!link.equals(this.link)) {
								PrintWriter os1 = new PrintWriter(link.getOutputStream(), true);
								os1.println(this.username + " is offline");
							}
						}
						out.println("Successfully Logout");
						out.println("Type 'help' for Options");

						out.flush();
					} else {
						out.println("invalid user");
					}
				} else {
					out.println("Type help for list of commands");
					out.flush();
				}

			} while (!(command.equals("exit")));
			out.println("Goodbye.");
		}
	}

	private void open() throws IOException {
		try {
			if (!fms.open(username)) {
				System.out.println("Error opening file");
			}
		} catch (Exception ex) {
			System.out.println("error");
		}
	}

	private void save() {
		try {
			if (!fms.save(username)) {
				System.out.println("Error saving file");
			}
		} catch (Exception ex) {
			System.out.println("error");
		}
	}

	private void add(PrintWriter out, Scanner in) {
		FleetItems item = readItemFromConsole(out, in);

		if (item != null) {
			fms.getFleetRegister().addItem(item);
		}
	}

	public void delete(String name, PrintWriter out) {
		fms.getFleetRegister().removeItem(name);
		save();

	}

	private void list(PrintWriter out) {
		String temp = fms.getFleetRegister().listItems(out);
		out.println(temp);

	}

	private void clear() {
		fms.getFleetRegister().clear();
	}

	private void help(PrintWriter out) {
		out.println("createuser,deleteuser,login,exit");
		out.flush();
	}

	private FleetItems readItemFromConsole(PrintWriter out, Scanner in) {
		out.println("Machine Name: ");
		out.flush();
		String name = in.nextLine();

		out.println("Vendor: ");
		out.flush();
		String vendor = in.nextLine();

		out.println("Valuation: ");
		out.flush();
		String valuation = in.nextLine();

		out.println("Machine Age: ");
		out.flush();
		int age = in.nextInt();

		out.println("Machine ID: ");
		out.flush();
		int machineID = in.nextInt();

		out.println("Club ID: ");
		out.flush();
		int clubID = in.nextInt();

		out.println("Last Service (KM): ");
		out.flush();
		int lastServiceKM = in.nextInt();

		out.println("Last Service Date: ");
		out.flush();
		int lastServiceDate = in.nextInt();

		out.println("Next Service (KM): ");
		out.flush();
		int nextServiceKM = in.nextInt();

		out.println("Current (KM): ");
		out.flush();
		int currentKM = in.nextInt();

		out.flush();

		// item2 c = new item2(firstName, email);
		FleetItems c = new FleetItems(name, age, machineID, clubID, vendor, clubID, lastServiceKM, lastServiceDate,
				nextServiceKM, currentKM);
		return c;
	}

	String username;

	@Override
	public void run() {
		try {
			run(out, in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
