package fleet.management.system.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fleet.management.system.model.IFleetRegister;
import fleet.management.system.model.FleetItems;
import fleet.management.system.model.IFMS;

/**
 * 
 * @author Ammad Aslam
 *Implemented interface of Fleet Management System.
 *
 */

public class FMS implements IFMS 
{
	
	
  private IFleetRegister fleetRegister;
  private FleetItems fleetItems;

  public FMS()
  {
    newFMS();
  }
  
  @Override
  public IFleetRegister getFleetRegister()
  {
    return fleetRegister;
  }
  
  @Override
  public void newFMS()
  {
    fleetRegister = new FleetRegisterMap();
    fleetItems = new FleetItems();
  }
  
 
  @Override
  public boolean open(String filename)
  {
	
    boolean success = false;
    try
    {
    	File source = new File("m");
      //File source = new File(dir+filename);
      //File source = new File(FileManager.dir_userAcc+filename+".ser");
      ObjectInputStream is = new ObjectInputStream(new FileInputStream(source));
      fleetRegister = (FleetRegisterMap)is.readObject();
      
      
      is.close();
      success = true;
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return success;
  }

  
  
  
  @Override
  public boolean save(String filename)
  {
    boolean success = false;
    try
    {
        File destination = new File(FileManager.dir_userAcc+filename+".ser");
        //File destination = new File(filename);
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(destination));
        os.writeObject(fleetRegister);
        os.close();
        success = true;
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return success;
  }

  
}