package classesForFinal;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;


@SuppressWarnings("unused")
public class FileExplorerGUI {
	
	private JTree fileTree;				//create JTree
	private JScrollPane treeScroll;
	private JFrame frame;
	private JFrame popFrame;
	private JList popList;
	private JTextField itemToSearch;
	private DefaultListModel listCreationData = new DefaultListModel();
	
	public static void main(String[] args)
	{
		FileExplorerGUI test = new FileExplorerGUI();
		test.fileHierarchy("C:\\Users\\smithm19\\Documents");
		test.createGUI();
		
		
	}
	
	private void createGUI()
	{
		frame = new JFrame("File Explorer");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		itemToSearch = new JTextField();
		treeScroll = new JScrollPane(fileTree);			//Make scroll bar and add to fileTree
		treeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.setLayout(new BorderLayout());		//Type of layout for JFrame. NORTH SOUTH EAST WEST CENTER
		frame.add(treeScroll, BorderLayout.CENTER);		//adds the JTree
		frame.add(itemToSearch, BorderLayout.NORTH);
		frame.setLocationRelativeTo(null);				//brings window to center of screen
		//frame.pack();		//reduces size
		frame.setVisible(true);
		itemToSearch.setActionCommand("Enter");
		
		itemToSearch.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				if (arg0.getActionCommand().equals("Enter"))
				{
					if(itemToSearch.getText() != null && !itemToSearch.getText().equals(""))
					{
						if (itemToSearch.getText().indexOf("\\") !=-1)			//searches file for \\
							{
								//recreateTree();	

								popFrame = new JFrame("Results");
								popFrame.setLocationRelativeTo(null);
								popFrame.setSize(300, 200);
								popFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								popFrame.setVisible(true);
								createArrayList();
								popList = new JList(listCreationData);
								popList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
								popList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
								popList.setVisibleRowCount(-1);								
								popFrame.setLayout(new BorderLayout());
								popFrame.add(popList,BorderLayout.CENTER);
							}
						else
						{
							
						}
					}
				}
			}
		});	
	}
	private void recreateTree() 
	{
//		fileHierarchy(itemToSearch.getText());
//		frame.setVisible(false);
//		frame.remove(treeScroll);
//		treeScroll = new JScrollPane(fileTree);
//		frame.add(treeScroll,BorderLayout.CENTER);
//		frame.setVisible(true);
	}
	
	private void createArrayList()
	{
		listCreationData.addElement("me0w");
		File root = new File("C:\\Users\\smithm19\\Documents");
		File[] files = root.listFiles();
		String match = itemToSearch.getText();
		for (File file : files)
		{
			String fileName = file.toString();
			listCreationData.addElement("in");
			if (fileName.toLowerCase().indexOf(match.toLowerCase()) >= 0)
				listCreationData.addElement(file);
		}
		
		for (int i = 0; i < files.length; i++)
		{
			
		}
		System.out.println(listCreationData.size());
	}
				
				
	private void fileHierarchy(String path)
	{
		File root = new File(path);
		DefaultMutableTreeNode rootDir = new DefaultMutableTreeNode(root);
		createChildren(root,rootDir);
		fileTree = new JTree(rootDir);
	}
	
	 private void createChildren(File fileRoot, DefaultMutableTreeNode node) 
	 {
         File[] files = fileRoot.listFiles();
         if (files == null) 
         {
        	 return;
         }
         for (File file : files) {
             DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file);
             node.add(childNode);
             if (file.isDirectory()) 
             {
                 createChildren(file, childNode);
             }
         }
     }
	
	

}

