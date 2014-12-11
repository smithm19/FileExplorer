package classesForFinal;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;


@SuppressWarnings("unused")
public class FileExplorerGUI {

	private JTree fileTree; // create JTree
	private JScrollPane treeScroll;			//create all other GUI objects
	private JScrollPane popScroll;
	private JFrame frame;
	private JFrame popFrame;
	private JList popList;
	private JTextField itemToSearch;
	private DefaultListModel listCreationData = new DefaultListModel();
	int total = 0;
	File baseFile = new File("C:\\Users\\smithm19\\Documents");				//set Documents as a File to open it easier
	//File parentSearcher;

	public static void main(String[] args) {
		
		FileExplorerGUI creator = new FileExplorerGUI();
		
		creator.fileHierarchy(creator.baseFile.toString());		
		creator.createGUI();
	}

	private void createGUI() {
		frame = new JFrame("File Explorer");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);						//create frame
		itemToSearch = new JTextField("[Search]");	//label the text field
		treeScroll = new JScrollPane(fileTree); // Make scroll bar and add to
												// fileTree
		treeScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.setLayout(new BorderLayout()); // Type of layout for JFrame. NORTH
												// SOUTH EAST WEST CENTER
		
		frame.add(treeScroll, BorderLayout.CENTER); // adds the JTree
		frame.add(itemToSearch, BorderLayout.NORTH);		
		frame.setLocationRelativeTo(null); // brings window to center of screen
		
		frame.setVisible(true);

		itemToSearch.setActionCommand("Enter");			//so itemToSearch responds to Enter being hit
		
		fileTree.addMouseListener(new MouseListener() {		//aallll of the mouseListeners


			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {		//open the child directory if double clicked
				if(e.getClickCount() >= 2){				

					FileExplorerGUI recreate = new FileExplorerGUI();
					String pather = "";
					int deleter=1;								
					pather =fileTree.getSelectionPath().toString();
					deleter=pather.lastIndexOf(",");				//sets the index of first "," because selectionPath gives a long string 
					
					pather = pather.substring(deleter+2, pather.length()-1);		//create the substring that is just the path
					if(pather.toLowerCase().indexOf(".txt")!=-1)			
					{
						File txtFile = new File(pather);
						try {
							Desktop.getDesktop().open(txtFile);			//opens .txts in default text editor
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					else	//don't go down a level in the tree if file is opened
					{
					


					frame.getContentPane().remove(treeScroll);   //remove Jtree
					recreate.fileHierarchy(pather);				//give tree new values

					frame.revalidate();
					frame.repaint();

					frame.setVisible(false);				//ugly makeshift way of getting rid of the window
					
					recreate.createGUI();				//make a new frame with a new tree because I am bad at coding
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {		//listener to move up the tree		
			}});

		fileTree.addKeyListener(new KeyListener(){		//allllll the keyListeners for jtree


			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {		//Move up the tree if Backspace is hit
				if(arg0.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				FileExplorerGUI moveUp = new FileExplorerGUI();
				String parent = "";
				int deleter;		//same job as previous deleter variable
				parent =fileTree.getPathForRow(0).toString();
				deleter=parent.lastIndexOf("\\");
				parent = parent.substring(1, deleter);			//remove the \ to get the parent 

				frame.getContentPane().remove(treeScroll);
				moveUp.fileHierarchy(parent);
				
				frame.revalidate();
				frame.repaint();
				frame.setVisible(false);		//same ugly code as above
				moveUp.createGUI();
		}
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {	
			}
			
		});
		
		itemToSearch.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
			
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {		//search action command
					if (itemToSearch.getText() != null
							&& !itemToSearch.getText().equals("") && !itemToSearch.getText().equals("[Search]")) {	//don't search if the text 
																															//field is null, empty or the label
						popFrame = new JFrame("Results");		//create the jlist frame
						popFrame.setLocationRelativeTo(null);
						popFrame.setSize(300, 200);
						

						popFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						popFrame.setVisible(true);
						
						listCreationData.clear();		//clears data for new searches
						
						try {
							String matcher = itemToSearch.getText(); 	//get what they are trying to search
							Search searcher = new Search();
							
							searcher.createArrayList(baseFile,matcher,listCreationData);		//get all the results of the search
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						if (listCreationData.size()<1)
						{
							listCreationData.addElement("No Results Found");			//display no results... if no results 
						}
						popList = new JList(listCreationData);
						popScroll = new JScrollPane(popList);		//create scroll bar for the lits
						popScroll
								.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						popList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
						popList.setLayoutOrientation(JList.VERTICAL);
						popList.setVisibleRowCount(-1);
						popList.addMouseListener(new MouseListener() {		//allll the mouse Listeners

							@Override
							public void mouseClicked(MouseEvent arg0) {	
							}

							@Override
							public void mouseEntered(MouseEvent e) {	
							}

							@Override
							public void mouseExited(MouseEvent e) {
							}

							@Override
							public void mousePressed(MouseEvent e) {		
								if(e.getClickCount() >= 2){						//double click on list item and.....
									FileExplorerGUI popReDraw = new FileExplorerGUI();
									
									String popParent = "";
									int deleter;
									popParent =popList.getSelectedValue().toString();
									if(popParent.toLowerCase().indexOf(".txt")!=-1)	//open if it is a .txt file
									{
										File txtFile = new File(popParent);
										try {
											Desktop.getDesktop().open(txtFile);
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
									deleter=popParent.lastIndexOf("\\");			
									
									popParent = popParent.substring(0, deleter);		//sets the parent of the selected item
									
									popFrame.dispose();							//delete jlist
									
									frame.getContentPane().remove(treeScroll);
									popReDraw.fileHierarchy(popParent);
									
									frame.revalidate();
									frame.repaint();
									frame.setVisible(false);
									
									popReDraw.createGUI();
								}
								
							}

							@Override
							public void mouseReleased(MouseEvent e) {
								
								
							}
							
						});
						popFrame.setLayout(new BorderLayout());
						popFrame.setTitle(listCreationData.getSize() +" Results");	//displays number of results in the title
						popFrame.add(popScroll, BorderLayout.CENTER);		//add list and scrollbar to the frame
						popFrame.setSize(800, 400);

					}
				}
			
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				
				
			}
			
		});
		

	}
	
	private void fileHierarchy(String path) {		//create the file tree
		File root = new File(path);
		DefaultMutableTreeNode rootDir = new DefaultMutableTreeNode(root);
		createChildren(root, rootDir);		//creates the child nodes
		fileTree = new JTree(rootDir);		//sets elements to fileTree
	}
	
	private void createChildren(File fileRoot, DefaultMutableTreeNode node) {
		File[] files = fileRoot.listFiles();		
		if (files == null) {
			return;
		}
		for (File file : files) {	//searches through all files on the top layer of the parent
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file);
			childNode.setAllowsChildren(true);
			node.add(childNode);
		}
	}

}
