package classesForFinal;

import java.io.File;

import javax.swing.DefaultListModel;

public class test {
	
	
	void testSearch1() throws InterruptedException{				//Search all files for tester.txt
		Search tester = new Search();
		DefaultListModel testerData = new DefaultListModel();
		File testerFile = new File("C:\\Users\\smithm19\\Documents");
		tester.createArrayList(testerFile, "tester.txt", testerData);
		System.out.println(testerData);
	}
	void testSearch2() throws InterruptedException{				//Search all files for tester
		Search tester = new Search();								//will get all results from the first, plus other types of files
		DefaultListModel testerData = new DefaultListModel();
		File testerFile = new File("C:\\Users\\smithm19\\Documents");
		tester.createArrayList(testerFile, "tester", testerData);
		System.out.println(testerData);
	}
	void testSearch3() throws InterruptedException{				//Search all files for cOD, to make sure case is not affecting the search
		Search tester = new Search();								
		DefaultListModel testerData = new DefaultListModel();
		File testerFile = new File("C:\\Users\\smithm19\\Documents");
		tester.createArrayList(testerFile, "cOD", testerData);
		System.out.println(testerData);
	}
	
	public static void main(String[] args) {
		test testing = new test();
		try {
			testing.testSearch1();
			testing.testSearch2();
			testing.testSearch3();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
