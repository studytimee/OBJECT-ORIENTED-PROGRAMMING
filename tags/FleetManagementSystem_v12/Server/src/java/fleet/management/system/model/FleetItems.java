package fleet.management.system.model;

import java.io.Serializable;

public class FleetItems implements Serializable {

	/**
	 * @author Ammad Aslam
	 * @version 12
	 * @since 1.8
	 * 
	 *The ObjectOutputStream class is used to serialize an Object.
	 * The FleetItems object serializes it to a file.
	 * When the program is done executing, a file named <b>C:\FleetManagementSystem_DB\FleetRegister/[username].ser</B> is created.
	 * 
	 * Note − When serializing an object to a file, the standard convention in Java is to give the file a .ser extension. 
	 * 
	 */
	
	private String name;
	private int age;
	private int machineID;
	private int clubID;
	private String vendor;
	private int valuation;
	private int lastServiceKM;
	private int lastServiceDate;
	private int nextServiceKM;
	private int currentKM;

	public FleetItems()
	{
		
	}
	
	public FleetItems(String name, int age, int machineID, int clubID, String vendor, int valuation, int lastServiceKM,
			int lastServiceDate, int nextServiceKM, int currentKM) {
		this.name = name;
		this.age = age;
		this.machineID = machineID;
		this.clubID = clubID;
		this.vendor = vendor;
		this.valuation = valuation;
		this.lastServiceKM = lastServiceKM;
		this.lastServiceDate = lastServiceDate;
		this.nextServiceKM = nextServiceKM;
		this.currentKM = currentKM;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-" +"Machine Name: "+ name + ",\n\t"+"Machine Age: "+ age + ",\n\t"+"Machine ID: " +machineID + ",\n\t" +"Club ID: "+clubID + ",\n\t" +"Machine Vendor: " + vendor + ",\n\t" +"Valuation: " + valuation
				+ ",\n\t" +"Last Service(KM): " + lastServiceKM + ",\n\t"  +"Last Service Date: "+ lastServiceDate + ",\n\t" +"Next Service(KM): " + nextServiceKM + ",\n\t" +"Current(KM): " + currentKM + "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMachineID() {
		return machineID;
	}

	public void setMachineID(int machineID) {
		this.machineID = machineID;
	}

	public int getClubID() {
		return clubID;
	}

	public void setClubID(int clubID) {
		this.clubID = clubID;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public int getValuation() {
		return valuation;
	}

	public void setValuation(int valuation) {
		this.valuation = valuation;
	}

	public int getLastServiceKM() {
		return lastServiceKM;
	}

	public void setLastServiceKM(int lastServiceKM) {
		this.lastServiceKM = lastServiceKM;
	}

	public int getLastServiceDate() {
		return lastServiceDate;
	}

	public void setLastServiceDate(int lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}

	public int getNextServiceKM() {
		return nextServiceKM;
	}

	public void setNextServiceKM(int nextServiceKM) {
		this.nextServiceKM = nextServiceKM;
	}

	public int getCurrentKM() {
		return currentKM;
	}

	public void setCurrentKM(int currentKM) {
		this.currentKM = currentKM;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FleetItems other = (FleetItems) obj;
		return true;
	}

}
