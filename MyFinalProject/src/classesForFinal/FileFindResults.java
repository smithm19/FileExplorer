package classesForFinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;




public class FileFindResults extends JPanel {
		
		protected DefaultListModel model = null;
		protected JList fileList = null;
		protected JScrollPane resultsScroller;
		protected File fileinih = null;

		@SuppressWarnings("unchecked")
		FileFindResults() {
			super();
			setLayout(new BorderLayout());

			model = new DefaultListModel();

			fileList = new JList(model);
			fileList.setDoubleBuffered(true);

			fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			fileList.setCellRenderer(new FileFindResultsCellRenderer());
			resultsScroller = new JScrollPane(fileList);
			resultsScroller.setDoubleBuffered(true);
			add(resultsScroller, BorderLayout.CENTER);

			// double click listener and refresh myfilexplorer directory default
			MouseListener mouseListener = new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						try {
							int index = fileList.locationToIndex(e.getPoint());
							fileinih = (File) model.elementAt(index);
							// System.out.println("fileku"+
							// fileinih.toString());
							// show the file name directory in jfilechooser
							// after you double click...
							//getChooser();
						} catch (Throwable err) { // ignore
						}
					}
				}
			};
			fileList.addMouseListener(mouseListener);
		}

		@SuppressWarnings("unchecked")
		public void append(File f) {
			model.addElement(f);
			invalidate();
			repaint();

		}

		public int getMatches() {
			return model.size();
		}

		public void clear() {
			if (model != null) {
				model.removeAllElements();
				invalidate();
				repaint();
			}
		}

		public File getFileSelect() {
			return fileinih;
		}

		/**
		 * Convenience class for rendering cells in the results list.
		 */
		class FileFindResultsCellRenderer extends JLabel implements
				ListCellRenderer {
			// icon for file in list (you can look java documentation of this
			// class)
			protected ImageIcon EMAK_ICON = new ImageIcon(
					ClassLoader.getSystemResource("folder.png"));
			protected ImageIcon TEXT_ICON = new ImageIcon(
					ClassLoader.getSystemResource("kviewshell.png"));
			protected ImageIcon WEB_ICON = new ImageIcon(
					ClassLoader.getSystemResource("mozilla.png"));
			protected ImageIcon IMAGE_ICON = new ImageIcon(
					ClassLoader.getSystemResource("kpaint.png"));
			protected ImageIcon SHEET_ICON = new ImageIcon(
					ClassLoader.getSystemResource("kspread.png"));
			protected ImageIcon DOKU_ICON = new ImageIcon(
					ClassLoader.getSystemResource("kword.png"));
			protected ImageIcon JAVA_ICON = new ImageIcon(
					ClassLoader.getSystemResource("cup.png"));
			protected ImageIcon MEDIA_ICON = new ImageIcon(
					ClassLoader.getSystemResource("multimedia.png"));
			protected ImageIcon RUN_ICON = new ImageIcon(
					ClassLoader.getSystemResource("launcher.png"));
			protected ImageIcon ZIP_ICON = new ImageIcon(
					ClassLoader.getSystemResource("packet.png"));
			protected ImageIcon CLASS_ICON = new ImageIcon(
					ClassLoader.getSystemResource("duke.png"));

			FileFindResultsCellRenderer() {
				setOpaque(true);
			}

			public Icon getIcon(File f) {
				// you add more file's icon here
				String name = f.getName().toLowerCase();
				if (name.endsWith(".doc"))
					return DOKU_ICON;
				else if (name.endsWith(".txt"))
					return TEXT_ICON;
				else if (name.endsWith(".xls"))
					return SHEET_ICON;
				else if (name.endsWith(".jpg") || name.endsWith(".png")
						|| name.endsWith(".gif") || name.endsWith(".bmp")
						|| name.endsWith(".tif"))
					return IMAGE_ICON;
				else if (name.endsWith(".jar") || name.endsWith(".java"))
					return JAVA_ICON;
				else if (name.endsWith(".html") || name.endsWith(".htm")
						|| name.endsWith(".php"))
					return WEB_ICON;
				else if (name.endsWith(".zip") || name.endsWith(".gz"))
					return ZIP_ICON;
				else if (name.endsWith(".au") || name.endsWith(".mpeg"))
					return MEDIA_ICON;
				else if (name.endsWith(".exe") || name.endsWith(".com")
						|| name.endsWith(".sh") || name.endsWith(".bat"))
					return RUN_ICON;
				else if (name.endsWith(".class"))
					return CLASS_ICON;
				else if (f.isFile())
					return DOKU_ICON;
				if (f.isDirectory())
					return EMAK_ICON;
				return null;
			}

			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				if (index == -1) {
					//
					int selected = list.getSelectedIndex();
					if (selected == -1)
						return this;
					else
						index = selected;
				}

				setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
				String s = value.toString();
				// get icon
				setIcon(getIcon(new File(s)));
				// show absolute path of file
				File file = (File) model.elementAt(index);
				setText(file.getAbsolutePath());
				setDoubleBuffered(true);
				// selection characteristics
				if (isSelected) {
					setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
					setBackground(list.getSelectionBackground());
					setForeground(list.getSelectionForeground());
				} else {
					setBackground(Color.white);
					setForeground(Color.black);
				}
				return this;
			}
		}
	}

