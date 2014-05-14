package package1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;;

/**********************************************************************
 * Displays the main GUI for the program
 * 
 * @author John Tunisi
 * @version 10/29/13
 *********************************************************************/
public class GUICampingReg extends JFrame implements ActionListener {
	
	/**A menu bar to hold menus*/
	private JMenuBar menus;
	
	/**A menu that contains save and load functions*/
	private JMenu fileMenu;
	
	/**A menu that contains check-in functions*/
	private JMenu actionMenu;
	
	/**A menu that contains check-out functions*/
	private JMenu checkOutMenu;
	
	/**A menu item to open as serializable files*/
	private JMenuItem openSerItem;
	
	/**A menu item that exits the program*/
	private JMenuItem exitItem;
	
	/**A menu item to save as serializable files*/
	private JMenuItem saveSerItem;
	
	/**A menu item to open as text files*/
	private JMenuItem openTextItem;
	
	/**A menu item to save as text files*/
	private JMenuItem saveTextItem;
	
	/**A menu item to open a campground status dialog*/
	private JMenuItem status;
	
	/**A menu item to add a tent instance*/
	private JMenuItem addTItem;
	
	/**A menu item to add an RV instance*/
	private JMenuItem addRVItem;
	
	/**A menu item to remove a site*/
	private JMenuItem removeItem;
	
	/**A table to hold site information*/
	private JTable jTableArea;
	
	/**A model to hold GUI button functions*/
	private SiteModel dTableModel;
	
	/**A scroll pane to allow scrolling*/
    private JScrollPane scrollPane;
	
    /******************************************************************
     * A constructor to initialize instance variables
     *****************************************************************/
	public GUICampingReg () {
		
		//creates file menu and items
		fileMenu = new JMenu("File");
		openSerItem = new JMenuItem("Open Serial");
		saveSerItem = new JMenuItem("Save Serial");
		openTextItem = new JMenuItem("Open Text");
		saveTextItem = new JMenuItem("Save Text");
		exitItem = new JMenuItem("Exit");
		status = new JMenuItem("Status");

		//adds items to menu
		fileMenu.add(status);
		fileMenu.add(openSerItem);
		fileMenu.add(saveSerItem);
		fileMenu.add(openTextItem);
		fileMenu.add(saveTextItem);
		fileMenu.add(exitItem);
		
		//adds actionlisteners to items
		openSerItem.addActionListener(this);
		exitItem.addActionListener(this);
		saveSerItem.addActionListener(this);
		openTextItem.addActionListener(this);
		saveTextItem.addActionListener(this);
		status.addActionListener(this);
		
		//creates new menu and items
		checkOutMenu = new JMenu("Check-Out");
		actionMenu = new JMenu ("Check-In");
		addTItem = new JMenuItem("Check-in Tent");
		addRVItem = new JMenuItem("Check-in RV");
		removeItem = new JMenuItem("Check-out");
		
		//adds items to menu
		actionMenu.add(addTItem);
		actionMenu.add(addRVItem);
		checkOutMenu.add(removeItem);
		
		//add actionlisteners to items
		addTItem.addActionListener(this);
		addRVItem.addActionListener(this);
		removeItem.addActionListener(this);
		
		//adds items to menu
		menus = new JMenuBar();
		menus.add(fileMenu); 
		menus.add(actionMenu);
		menus.add(checkOutMenu);
		setJMenuBar(menus);
		
		//creates table area
		dTableModel = new SiteModel();
		jTableArea = new JTable(dTableModel);
		scrollPane = new JScrollPane(jTableArea);
		add (scrollPane);
				
		setVisible(true);
		setSize(600,400);
	}
	
	/******************************************************************
	 * A main method to create an instance of the GUI
	 * 
	 * @param args
	 *****************************************************************/
	public static void main (String[] args) {
		new GUICampingReg();
	}
	
	/******************************************************************
	 * Action performed method to perform button operations
	 *****************************************************************/
	public void actionPerformed(ActionEvent e) {
		
		if (openSerItem == e.getSource()) {
			
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(chooser);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				String file = chooser.getSelectedFile().getAbsolutePath();
				dTableModel.loadDatabase(file);
			}
		}
		
		if (openTextItem == e.getSource()) {
			
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(chooser);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				String file = chooser.getSelectedFile().getAbsolutePath();
				dTableModel.loadFromText(file);
			}
		}

		if (exitItem == e.getSource()) {
			System.exit(0);
		}

		if (saveSerItem == e.getSource()) {
			dTableModel.saveDatabase("Camping.ser");
		}

		if (saveTextItem == e.getSource()) {
			dTableModel.saveAsText("Camping.txt");
		}
		
		if(status == e.getSource()){
			CampFullStatus c = new CampFullStatus(this);
		}

		if (addTItem == e.getSource()) {
			Tent t = new Tent();
			DialogCheckInTent x = new DialogCheckInTent(this, t);
			
			if (x.getCloseStatus() == x.OK) {
				dTableModel.addSite(t);
			}
		}

		if (addRVItem == e.getSource()) {
			RV r = new RV();
			DialogCheckInRV x = new DialogCheckInRV(this, r);
			
			if (x.getCloseStatus() == x.OK) {
				dTableModel.addSite(r);
			}
		}

		if (removeItem == e.getSource()) {
			int index = jTableArea.getSelectedRow();
			if (index == -1)
				JOptionPane.showMessageDialog(this,"No site selected");
			else
				dTableModel.removeSite(index);
		}
	}
}