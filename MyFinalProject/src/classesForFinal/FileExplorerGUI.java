package classesForFinal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;


@SuppressWarnings("unused")
public class FileExplorerGUI {

	private JTree fileTree; // create JTree
	private JScrollPane treeScroll;
	private JScrollPane popScroll;
	private JFrame frame;
	private JFrame popFrame;
	private JList popList;
	private JTextField itemToSearch;
	private DefaultListModel listCreationData = new DefaultListModel();
	int total = 0;
	protected FileFindResults resultsPanel = null;

	public static void main(String[] args) {
		FileExplorerGUI test = new FileExplorerGUI();
		test.fileHierarchy("C:\\Users\\smithm19\\Documents");
		test.createGUI();

	}

	private void createGUI() {
		frame = new JFrame("File Explorer");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		itemToSearch = new JTextField();
		treeScroll = new JScrollPane(fileTree); // Make scroll bar and add to
												// fileTree
		treeScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.setLayout(new BorderLayout()); // Type of layout for JFrame. NORTH
												// SOUTH EAST WEST CENTER
		frame.add(treeScroll, BorderLayout.CENTER); // adds the JTree
		frame.add(itemToSearch, BorderLayout.NORTH);
		frame.setLocationRelativeTo(null); // brings window to center of screen
		// frame.pack(); //reduces size
		frame.setVisible(true);
		itemToSearch.setActionCommand("Enter");

		itemToSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (arg0.getActionCommand().equals("Enter")) {
					if (itemToSearch.getText() != null
							&& !itemToSearch.getText().equals("")) {

						popFrame = new JFrame("Results");
						popFrame.setLocationRelativeTo(null);
						popFrame.setSize(300, 200);
						popScroll = new JScrollPane(popList);
						popScroll
								.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						popFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						popFrame.setVisible(true);
						File baseFile = new File("C:\\Users\\smithm19\\Documents");
						listCreationData.clear();
						try {
							String matcher = itemToSearch.getText();
							createArrayList(baseFile,matcher);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						if (listCreationData.size()<1)
						{
							listCreationData.addElement("No Results Found");
						}
						popList = new JList(listCreationData);
						popList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
						popList.setLayoutOrientation(JList.VERTICAL);
						popList.setVisibleRowCount(-1);
						popFrame.setLayout(new BorderLayout());
						popFrame.add(popList, BorderLayout.CENTER);
						popFrame.pack();
						

					}
				}
			}
		});
	}


	private void createArrayList(File base, String itemToSearch) throws InterruptedException {

		
		
		File root = null;
		root = base;
		File[] files = root.listFiles();
		String match = itemToSearch.toLowerCase();
		if (files!= null){
		for (int i = 0; i < files.length; i++)
		{
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
				createArrayList(files[i],itemToSearch);
			}
		}
		}
	}
	
	
	


	private void fileHierarchy(String path) {
		File root = new File(path);
		DefaultMutableTreeNode rootDir = new DefaultMutableTreeNode(root);
		createChildren(root, rootDir);
		fileTree = new JTree(rootDir);
	}

	private void createChildren(File fileRoot, DefaultMutableTreeNode node) {
		File[] files = fileRoot.listFiles();
		if (files == null) {
			return;
		}
		for (File file : files) {
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file);
			
			node.add(childNode);
			if (file.isDirectory()) {
				createChildren(file, childNode);
			}
		}
	}

}
