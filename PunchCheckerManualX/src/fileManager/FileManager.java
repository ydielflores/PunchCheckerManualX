package fileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import dbManager.DBManager;


public class FileManager {
	private String filePath;
	private String directoryPath;

	private HashMap<String, String> storesAndIDs;
	private HashMap<String, ArrayList<String>> storeNameAndSavedDataFromCSV;

	public FileManager(String filePath, String directoryPath) {
		this.filePath = filePath;
		this.directoryPath = directoryPath;
		
		
	}

	public void startProcess() {
		
		DBManager db = new DBManager();
		
		db.connect();
		storesAndIDs=db.storeLoader();
		db.disconnect();
		
		processFile();
	}

	private void processFile() {

		String line;
		String storeName;
		String storeNumber;
		
		storeNameAndSavedDataFromCSV = new HashMap<String, ArrayList<String>>();
		try {
			@SuppressWarnings("resource")
			BufferedReader csvReader = new BufferedReader(new FileReader(this.filePath));

			while((line = csvReader.readLine())!=null) {
				
				//This means there's employee number in this csv line.
				if(line.charAt(0)!=',') {
					storeNumber = findStoreNumber(line);
					storeName = storesAndIDs.get(storeNumber);
					/*check if this hashmap contains this store already, 
					 * if it does not, add the new store Name (key) 
					 * and a new list (value) containing the data for the store (key)
					 */
					if(!storeNameAndSavedDataFromCSV.containsKey(storeName)) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(line);
						storeNameAndSavedDataFromCSV.put(storeName, list);//findStoreName(findStoreNumber(line));
					}else {
						//the list already contains the store, update the list to add new data.
						storeNameAndSavedDataFromCSV.get(storeName).add(line);
					}
					
				}

			}
			writeFiles();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void writeFiles() {
		Set<String> storeNames = storeNameAndSavedDataFromCSV.keySet();
		Iterator<String> iteratorOfStoreNames = storeNames.iterator();
		PrintWriter pw;
		String storeName;
		while(iteratorOfStoreNames.hasNext()) {
			storeName = iteratorOfStoreNames.next();
			try {
				
				pw = new PrintWriter(new File(this.directoryPath+"\\"+storeName+"\\punchxt.csv"));
				for(String data : storeNameAndSavedDataFromCSV.get(storeName)) {
					pw.write(data+"\n");
					pw.flush();
				}
				pw.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String findStoreNumber(String line) {
	
		String returnable = line.split(",")[2];
		return  returnable;
	}

}
