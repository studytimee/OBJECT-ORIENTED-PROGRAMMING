package fleet.management.system.model;

import java.io.PrintWriter;


public interface IFleetRegister
{
  public abstract void clear();

  public abstract FleetItems getCurrentKM(String currentKM);
  
  public abstract FleetItems getnextServiceKM(String nextServiceKM);

  public abstract FleetItems getlastServiceDate(String lastServiceDate);
  
  public abstract FleetItems getlastServiceKM(String lastServiceKM);

  public abstract void addItem(FleetItems Item);

  public abstract int numberOfItems();

  public abstract void removeItem(String lastName);

  public abstract String listItems(PrintWriter out);
  
  
} 