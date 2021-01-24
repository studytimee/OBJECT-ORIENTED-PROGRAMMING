package fleet.management.system.impl;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import fleet.management.system.model.FleetItems;
import fleet.management.system.model.IFleetRegister;

public class FleetRegisterMap implements IFleetRegister, Serializable {
	private Map<String, FleetItems> items;

	public FleetRegisterMap() {
		items = new HashMap<String, FleetItems>();
	}

	@Override
	public void addItem(FleetItems item) {

		items.put(item.getName(), item);
	}

	@Override
	public void clear() {
		items.clear();
	}

	@Override
	public String listItems(PrintWriter out) {
		StringBuffer allEntries = new StringBuffer();

		for (FleetItems item : items.values()) {

			allEntries.append(item + "\n");
		}

		String temp = allEntries.toString();
		// out.println(temp);
		// item c = new item(allEntries.toString(), null);

		return temp;
	}

	@Override
	public int numberOfItems() {
		return items.size();
	}

	@Override
	public void removeItem(String firstname) {
		items.remove(firstname);
	}

	@Override
	public FleetItems getCurrentKM(String currentKM) {
		// TODO Auto-generated method stub
		return items.get(currentKM);
	}

	@Override
	public FleetItems getnextServiceKM(String nextServiceKM) {
		// TODO Auto-generated method stub
		return items.get(nextServiceKM);
	}

	@Override
	public FleetItems getlastServiceDate(String lastServiceDate) {
		// TODO Auto-generated method stub
		return items.get(lastServiceDate);
	}

	@Override
	public FleetItems getlastServiceKM(String lastServiceKM) {
		// TODO Auto-generated method stub
		return items.get(lastServiceKM);
	}
}
