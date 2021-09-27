package saveAndLoad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Paths;

public class SaveData implements Serializable {

	private static final long serialVersionUID = 8799586945404931738L;

	private File filePath;

	/**
	 * 
	 * parameter fileName, identifies the name of the new created file
	 * 
	 * @param obj
	 * @param filename
	 */
	public void saveData(Object obj, String fileName) {
		filePath = new File(Paths.get("").toAbsolutePath().toString() + "\\Gamefiles\\" + fileName);
		try {
			FileOutputStream fileOutput = new FileOutputStream(filePath);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutput);
			objectOutputStream.writeObject(obj);
			fileOutput.close();
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
