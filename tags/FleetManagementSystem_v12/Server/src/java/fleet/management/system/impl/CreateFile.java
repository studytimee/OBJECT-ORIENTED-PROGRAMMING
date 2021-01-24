package fleet.management.system.impl;

import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

public class CreateFile {
	String fileName;
	String dirName;

	public void name(String dirName, String fileName) {
		this.dirName = dirName;
		this.fileName = fileName;

		try {
			FileWriter writer = new FileWriter(dirName+fileName, true);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
