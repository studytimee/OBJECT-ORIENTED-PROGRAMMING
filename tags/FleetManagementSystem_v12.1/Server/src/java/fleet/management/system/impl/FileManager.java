package fleet.management.system.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManager
{
	
	static public String dir_root = "C:\\FleetManagementSystem_DB/";
	static public String dir_userAcc = "C:\\FleetManagementSystem_DB\\UserAccount/";
	static public String dir_FleetReg = "C:\\FleetManagementSystem_DB\\FleetRegister/";


  public void  filecopy(String destination) throws  IOException
  {
	 
    
	FileInputStream fstream = new FileInputStream(FileManager.dir_root+"temp.txt");
	DataInputStream in = new DataInputStream(fstream);
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    FileWriter writer= new FileWriter(destination,false);
    String strLine="";
    while ((strLine = br.readLine()) != null)
    {
      writer.write(strLine);
      writer.write(System.getProperty( "line.separator" ));
    }
    writer.close();
    br.close();
  }
}
