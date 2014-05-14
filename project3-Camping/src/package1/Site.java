package package1;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**********************************************************************
 * Site creates the functions of the program
 * 
 * @author John Tunisi
 * @version 10/21/13
 *********************************************************************/
public class Site implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The name of the person who is occupying the Site */
	protected String nameReserving;

	/** The date the Site was checked-in (occupied) */
	protected GregorianCalendar checkIn;

	/** The estimated number of days the person is reserving */
	/** This is just an estimate when the camper is */
	/** checking in */
	protected int daysStaying; 

	/** The date the Site was checked out */
	/** This is the exact day they checked-out */
	protected GregorianCalendar checkOutOn;
	
	/** The Site number */
	protected int siteNumber;  

	/******************************************************************
	 * A constructor that defaults values
	 *****************************************************************/
	public Site(){
	}
	
	/******************************************************************
	 * A constructor to initialize variables
	 * 
	 * @param nameReserving the name of the reserver
	 * @param checkIn the date the site was checked in 
	 * @param daysStaying the estimated number of days staying
	 * @param checkOutOn the day site was checked out
	 * @param siteNumber the number of the site
	 *****************************************************************/
	public Site(String nameReserving, GregorianCalendar checkIn,
			int daysStaying, GregorianCalendar checkOutOn, int siteNumber) {
		super();
		this.nameReserving = nameReserving;
		this.checkIn = checkIn;
		this.daysStaying = daysStaying;
		this.checkOutOn = checkOutOn;
		this.siteNumber = siteNumber;
	}

	/******************************************************************
	 * A getter method for the variable nameReserving
	 * 
	 * @return nameReserving the name of the reserver
	 *****************************************************************/
	public String getNameReserving() {
		return nameReserving;
	}

	/******************************************************************
	 * A setter method for the variable nameReserving
	 * 
	 * @param nameReserving the name of the reserver
	 *****************************************************************/
	public void setNameReserving(String nameReserving) {
		this.nameReserving = nameReserving;
	}

	/******************************************************************
	 * A getter method for the variable checkIn
	 * 
	 * @return checkIn the date the site was checked in
	 *****************************************************************/
	public GregorianCalendar getCheckIn() {
		return checkIn;
	}

	/******************************************************************
	 * A setter method for the variable checkIn
	 * 
	 * @param checkIn the date the site was checked in
	 *****************************************************************/
	public void setCheckIn(GregorianCalendar checkIn) {
		this.checkIn = checkIn;
	}

	/******************************************************************
	 * A getter method for the variable daysStaying
	 * 
	 * @return daysStaying the estimated number of days staying
	 *****************************************************************/
	public int getDaysStaying() {
		return daysStaying;
	}

	/******************************************************************
	 * A setter method for the variable daysStaying
	 * 
	 * @param daysStaying the estimated number of days staying
	 *****************************************************************/
	public void setDaysStaying(int daysStaying) {
		this.daysStaying = daysStaying;
	}

	/******************************************************************
	 * A getter method for the variable checkOutOn
	 * 
	 * @return checkOutOn the date site is checked out on
	 *****************************************************************/
	public GregorianCalendar getCheckOutOn() {
		return checkOutOn;
	}

	/******************************************************************
	 * A setter method for the variable checkOutOn
	 * 
	 * @param checkOutOn the date site is checked out on
	 *****************************************************************/
	public void setCheckOutOn(GregorianCalendar checkOutOn) {
		this.checkOutOn = checkOutOn;
	}

	/******************************************************************
	 * A getter method for the variable siteNumber
	 * 
	 * @return siteNumber the number of the site
	 *****************************************************************/
	public int getSiteNumber() {
		return siteNumber;
	}

	/******************************************************************
	 * A setter method for the variable siteNumber
	 * 
	 * @param siteNumber the number of the site
	 *****************************************************************/
	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}

	/******************************************************************
	 * A getter method for the variable serialVersionUID
	 * 
	 * @return serialVersionUID serializes the class
	 *****************************************************************/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}  
}
