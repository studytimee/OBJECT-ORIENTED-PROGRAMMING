package fleet.management.system.model;

public interface IFMS 
{
  IFleetRegister getFleetRegister();

  void newFMS();

  boolean open(String filename);

  boolean save(String filename);
}
 