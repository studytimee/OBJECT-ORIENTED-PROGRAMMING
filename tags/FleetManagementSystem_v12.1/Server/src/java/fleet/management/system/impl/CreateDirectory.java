package fleet.management.system.impl;

import java.nio.file.Paths;
import java.io.File;

import java.io.File;

public class CreateDirectory {
	public void name(String dirName) {
		
		File file = new File(dirName);
		// true if the directory was created, false otherwise
		if (file.exists()) {
			System.out.println("Directory name '"+file.getName()+"' already exist!");
		} else {
			file.mkdirs();
			System.out.println( file.getName()+" Directory Created!");
		}
	}

}