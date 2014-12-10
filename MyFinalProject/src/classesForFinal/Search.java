package classesForFinal;

import java.io.File;

import javax.swing.DefaultListModel;

public class Search {
	void createArrayList(File base, String itemToSearch,
			DefaultListModel listCreationData) throws InterruptedException {

		File root = null;
		root = base;
		File[] files = root.listFiles();
		String match = itemToSearch.toLowerCase();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getName();

				// Searches
				if (fileName.toLowerCase().startsWith(match)) {
					listCreationData.addElement(files[i]);
				}

				else if (fileName.toLowerCase().endsWith(match)) {
					listCreationData.addElement(files[i]);
				}

				if (files[i].isDirectory()) {
					System.out.println(files[i]);
					createArrayList(files[i], itemToSearch, listCreationData);
				}
			}
		}
	}

}