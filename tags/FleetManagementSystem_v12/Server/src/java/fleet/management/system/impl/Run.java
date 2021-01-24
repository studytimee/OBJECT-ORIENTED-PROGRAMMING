package fleet.management.system.impl;

//import imp.facade.AdminSession;
//import imp.facade.IAdminSession;

//import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Run implements Runnable {

	private ServerSocket servSock;
	private Socket link;
	private String command;
	private PrintWriter out;
	private Scanner in;
	private boolean isStopped=false;
	private ArrayList<Socket> clientLinks;

	public Run(Socket socket, ArrayList<Socket> links)throws IOException {
		this.link = socket;
		this.clientLinks = links;
	}
	
	@Override
	public void run()
	{
		try {
			run2();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws Exception {
		CreateDirectory mkdir = new CreateDirectory();
		CreateFile cFile = new CreateFile();
		
		mkdir.name(FileManager.dir_root);
		mkdir.name(FileManager.dir_userAcc);
		mkdir.name(FileManager.dir_FleetReg);
		cFile.name(FileManager.dir_root, "temp");
		
		
		
		
	    ServerSocket ssock = new ServerSocket(1234);
	    ArrayList<Socket> links= new ArrayList<Socket>();
	    System.out.println("Listening");
	    while (true) {
	      Socket sock = ssock.accept();
	      links.add(sock);
	      System.out.println("Connected");
	      new Thread(new Run(sock, links)).start();
	    }
	  }
	
	private void run2() throws IOException {
		System.out.println(clientLinks.size());
		in = new Scanner(new InputStreamReader(link.getInputStream()));
		out = new PrintWriter(link.getOutputStream(), true);
		//System.out.println(clientLinks.size());
		try
		{
			
			//  fmsConsoleApp.run(command,out,in);
			new Thread(new FMSConsole(out, in, clientLinks, link)).start();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		/*while (!command.equals("close"))
		{
			command = in.nextLine();
		}*/
		
	}
}
