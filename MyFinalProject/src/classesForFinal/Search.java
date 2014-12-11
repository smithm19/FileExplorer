package classesForFinal;

import java.io.File;

import javax.swing.DefaultListModel;

public class Search {
	void createArrayList(File base, String itemToSearch,
			DefaultListModel listCreationData) throws InterruptedException {
		
		File root = null;
		root = base;    //set root to base
		File[] files = root.listFiles();	//put all files in root into variable files
		String match = itemToSearch.toLowerCase();		//create a String to match against files
		if (files != null) {
			for (int i = 0; i < files.length; i++) {		//loop to get through all files
				String fileName = files[i].getName();

				// Search
				
				if (fileName.toLowerCase().indexOf(match)!= -1)		//search the fileName for 
				{
					listCreationData.addElement(files[i]);	//add element to the listModel
					
				}

				if (files[i].isDirectory()) {
					System.out.println(files[i]);
					createArrayList(files[i], itemToSearch, listCreationData);		//recursion to search through deeper layers of the tree
				}
			}
		}
	}

}