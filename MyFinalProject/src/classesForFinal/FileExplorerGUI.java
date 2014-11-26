package classesForFinal;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileExplorerGUI {
	
	private JTree fileTree;				//create JTree
	private JScrollPane treeScroll;
	private JFrame frame;
	private JTextField itemToSearch;
	
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
				// TODO Auto-generated method stub
				if (arg0.getActionCommand().equals("Enter"))
				{
					if(itemToSearch.getText() != null && !itemToSearch.getText().equals(""))
					{
						if (itemToSearch.getText().indexOf("\\") !=-1)			//searches file for //
							{
								recreateTree();	
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
	// TODO Auto-generated method stub
	
		fileHierarchy(itemToSearch.getText());
		frame.setVisible(false);
		frame.remove(treeScroll);
		treeScroll = new JScrollPane(fileTree);
		frame.add(treeScroll,BorderLayout.CENTER);
		frame.setVisible(true);
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

