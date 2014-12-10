package classesForFinal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;


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
	static File baseFile = new File("C:\\Users\\smithm19\\Documents");

	public static void main(String[] args) {
		FileExplorerGUI test = new FileExplorerGUI();
		test.fileHierarchy(baseFile.toString());
		test.createGUI();
	}

	private void createGUI() {
		frame = new JFrame("File Explorer");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		itemToSearch = new JTextField("[Search]");
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
		
		fileTree.addMouseListener(new MouseListener() {


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
				if(e.getClickCount() >= 2){

					FileExplorerGUI recreate = new FileExplorerGUI();
					String pather = "";
					int deleter=1;
					pather =fileTree.getSelectionPath().toString();
					deleter=pather.lastIndexOf(",");
					System.out.println(deleter);
					pather = pather.substring(deleter+2, pather.length()-1);
					System.out.println(pather);

					frame.getContentPane().remove(treeScroll);
					recreate.fileHierarchy(pather);
//					frame.add(fileTree, BorderLayout.CENTER);
					frame.revalidate();
					frame.repaint();
					
					
					
					frame.setVisible(false);
//					frame.remove(fileTree);
					
//					System.out.println(fileTree.getSelectionPath().toString());
//					frame.setVisible(true);
					
					recreate.createGUI();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {				
			}});

		fileTree.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				FileExplorerGUI moveUp = new FileExplorerGUI();
				String parent = "";
				int deleter;
				parent =fileTree.getPathForRow(0).toString();
				deleter=parent.lastIndexOf("\\");
				System.out.println(deleter);
				parent = parent.substring(1, deleter);
				System.out.println(parent);
				frame.getContentPane().remove(treeScroll);
				moveUp.fileHierarchy(parent);
				
				frame.revalidate();
				frame.repaint();
				frame.setVisible(false);
				moveUp.createGUI();
		}
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {	
			}
			
		});
		itemToSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (arg0.getActionCommand().equals("Enter")) {
					if (itemToSearch.getText() != null
							&& !itemToSearch.getText().equals("") && !itemToSearch.getText().equals("[Search]")) {

						popFrame = new JFrame("Results");
						popFrame.setLocationRelativeTo(null);
						popFrame.setSize(300, 200);
						

						popFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						popFrame.setVisible(true);
						
						listCreationData.clear();
						try {
							String matcher = itemToSearch.getText();
							Search searcher = new Search();
							
							searcher.createArrayList(baseFile,matcher,listCreationData);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						if (listCreationData.size()<1)
						{
							listCreationData.addElement("No Results Found");
						}
						popList = new JList(listCreationData);
						popScroll = new JScrollPane(popList);
						popScroll
								.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						popList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
						popList.setLayoutOrientation(JList.VERTICAL);
						popList.setVisibleRowCount(-1);
						popList.addMouseListener(new MouseListener() {

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
								if(e.getClickCount() >= 2){
									FileExplorerGUI popReDraw = new FileExplorerGUI();
									System.out.println(popList.getSelectedValue());
									String popParent = "";
									int deleter;
									popParent =popList.getSelectedValue().toString();
									deleter=popParent.lastIndexOf("\\");
									System.out.println(deleter);
									popParent = popParent.substring(0, deleter);
									System.out.println(popParent);
									
									popFrame.dispose();
									
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
								// 
								
							}
							
						});
						popFrame.setLayout(new BorderLayout());
						popFrame.add(popScroll, BorderLayout.CENTER);
						popFrame.setSize(800, 400);

					}
				}
			}
		});
	}
	
	private void fileHierarchy(String path) {
		// was String path
		File root = new File(path);
		//File root = path;
		DefaultMutableTreeNode rootDir = new DefaultMutableTreeNode(root);
		createChildren(root, rootDir);
		fileTree = new JTree(rootDir);
	}
	
	private void makeNewTree(String path){
		File root = new File(path);
		DefaultMutableTreeNode rootDir = new DefaultMutableTreeNode(root);
		createChildren(root, rootDir);
		frame.remove(treeScroll);
		fileTree = new JTree(rootDir);

		//frame.setVisible(false);
		
		frame.add(fileTree, BorderLayout.CENTER);
		//frame.setVisible(true);
	}
	


	private void createChildren(File fileRoot, DefaultMutableTreeNode node) {
		File[] files = fileRoot.listFiles();
		if (files == null) {
			return;
		}
		for (File file : files) {
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file);
			childNode.setAllowsChildren(true);
			node.add(childNode);
			
			if (file.isDirectory()) {
				
//				createChildren(file, childNode);
			}
		}
	}

}
