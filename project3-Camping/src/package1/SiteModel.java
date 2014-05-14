package package1;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**********************************************************************
 * Performs actions for the GUI
 * 
 * @author John Tunisi
 * @version 10/29/13
 *********************************************************************/
public class SiteModel extends AbstractTableModel {

	/**An array list to hold all current sites*/
	private ArrayList<Site> listSites;

	/**An array of strings containing the names of each column*/
	private String[] columnNames = {"Name Reserving", "Days Staying",
			"Checked in", "Site #", "Tent/RV info"};


	/******************************************************************
	 * A getter that gets the column length
	 *****************************************************************/
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/******************************************************************
	 * A getter that gets the row size
	 *****************************************************************/
	@Override
	public int getRowCount() {
		return listSites.size();
	}

	/******************************************************************
	 * A getter that gets the name of a column
	 *****************************************************************/
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/******************************************************************
	 * A getter that gets the value at a specific row 
	 *****************************************************************/
	@Override
	public Object getValueAt(int row, int col) {

		switch (col) {
		case 0: 
			return (listSites.get(row).getNameReserving());

		case 1: 
			return (listSites.get(row).getDaysStaying());

		case 2:
			return (DateFormat.getDateInstance(DateFormat.SHORT)
					.format(listSites.get(row).getCheckIn().getTime()));

		case 3: 
			return (listSites.get(row).getSiteNumber());

		case 4:
			if (listSites.get(row) instanceof Tent) 
				return (" Tenters: " + ((Tent) listSites.get(row)).getNumOfTenters());
			else
				return (" Amps: " + ((RV) listSites.get(row)).getPower());

		default:
			return null;
		}
	}

	/******************************************************************
	 * A constructor that calls the super
	 *****************************************************************/
	public SiteModel() {
		super();
		listSites = new ArrayList<Site>();
	}

	/******************************************************************
	 * Removes a specific site
	 * 
	 * @param i the position in the array for a site
	 *****************************************************************/
	public void removeSite(int i) {	
		DialogCheckOut x = new DialogCheckOut(null, listSites.get(i));
		listSites.remove(i);
		fireTableRowsDeleted(0, listSites.size());
	}

	/******************************************************************
	 * Adds a site
	 * 
	 * @param a the site being added
	 *****************************************************************/
	public void addSite (Site a) {
		if(isSiteTaken(a))
			return;
		listSites.add(a);
		fireTableRowsInserted( 0, listSites.size());
	}

	/******************************************************************
	 * Checks if a site is taken
	 * 
	 * @param a the site in question
	 * @return true if the site is taken
	 * @return false if the site is not taken
	 *****************************************************************/
	public boolean isSiteTaken(Site a){
		for(int i=0; i < listSites.size(); i++){

			if(listSites.get(i).getSiteNumber() == a.getSiteNumber()){
				JOptionPane.showMessageDialog(null, "Site taken");
				return true;
			}
		}
		return false;
	}

	/******************************************************************
	 * Gets the site at a specific location in the arraylist
	 * 
	 * @param i the location of a site in the arraylist
	 * @return listSites.get(i) the site wanted
	 *****************************************************************/
	public Site getSite (int i) {
		return listSites.get(i);
	}

	/******************************************************************
	 * Gets the size of the arraylist of sites
	 * 
	 * @return listSites.size() the size of the arraylist
	 *****************************************************************/
	public int getSize() {
		return listSites.size();
	}

	/******************************************************************
	 * Saves the database as a serializable file
	 * 
	 * @param filename the name of the file being saved
	 *****************************************************************/
	public void saveDatabase(String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(listSites);
			os.close();
			JOptionPane.showMessageDialog(null, "File saved!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/******************************************************************
	 * Loads a database as serializable file
	 * 
	 * @param filename the name of the file being loaded
	 *****************************************************************/
	public void loadDatabase(String filename) {
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream is = new ObjectInputStream(fis);

			listSites = (ArrayList<Site>) is.readObject();
			fireTableRowsInserted(0, listSites.size() - 1);
			is.close();
			JOptionPane.showMessageDialog(null, "File loaded!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/******************************************************************
	 * Saves a database as a text file
	 * 
	 * @param filename
	 * @return
	 *****************************************************************/
	public boolean saveAsText(String filename) {
		if (filename.equals("")) {
			return false;
		}
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			Site s;
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

			out.println(listSites.size());
			for (int i = 0; i < listSites.size(); i++) {
				s = listSites.get(i);
				out.println(s.getClass().getName());
				out.println(s.getNameReserving());
				out.println(df.format(listSites.get(i).getCheckIn().getTime()));
				out.println(s.getDaysStaying());
				out.println(s.getSiteNumber());

				if (s instanceof Tent)
					out.println(((Tent)s).getNumOfTenters());
				else
					out.println(((RV)s).getPower());
			}
			out.close();
			return true;
		} catch (IOException ex) {
			System.out.println ("IO Error!");
			return false;
		}
	}

	/******************************************************************
	 * Loads database from a text file
	 * 
	 * @param filename
	 *****************************************************************/
	public void loadFromText(String filename) {
		listSites.clear();
		fireTableRowsDeleted( 0, listSites.size());

		try {
			Scanner scanner = new Scanner(new File(filename));
			int count = Integer.parseInt(scanner.nextLine().trim());
			for (int i = 0; i < count; i++) {

				String type = scanner.nextLine().trim();
				String name = scanner.nextLine().trim();

				GregorianCalendar checkedIn = null;
				try {
					DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					Date date = formatter.parse(scanner.nextLine().trim());
					checkedIn = new GregorianCalendar();
					checkedIn.setTime(date);
				} catch (ParseException ex) {
					ex.printStackTrace();
				}

				int daysStaying = Integer.parseInt(scanner.nextLine().trim());
				int siteNumber = Integer.parseInt(scanner.nextLine().trim());

				if (type.contains("Tent")) {
					int numOfTenters = Integer.parseInt(scanner.nextLine().trim());
					Tent t = new Tent(name, checkedIn, daysStaying, null, siteNumber, numOfTenters);
					listSites.add(t);
					fireTableRowsInserted(listSites.size() - 1, listSites.size() - 1);

				} else {

					int power = Integer.parseInt(scanner.nextLine().trim());
					RV r = new RV(name, checkedIn, daysStaying, null, siteNumber, power);
					listSites.add(r);
					fireTableRowsInserted(listSites.size() - 1, listSites.size() - 1);
				}
			}
			scanner.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}