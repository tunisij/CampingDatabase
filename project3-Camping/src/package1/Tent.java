package package1;

import java.util.GregorianCalendar;

/**********************************************************************
 * Tent is a type of site within the program
 * 
 * @author John Tunisi
 * @version 10/21/13
 *********************************************************************/
public class Tent extends Site {

	/** Represents the number of tenters on this site */
	private int numOfTenters;

	
	/******************************************************************
	 * A constructor that defaults values
	 *****************************************************************/
	public Tent(){
		super();
	}
	
	/******************************************************************
	 * A constructor to initialize the instance variables
	 * 
	 * @param nameReserving the name of the reserver
	 * @param checkIn the date of the check in
	 * @param daysStaying the estimate of number of days staying
	 * @param checkOutOn the date of the check out
	 * @param siteNumber the number of the site
	 * @param numOfTenters the number of tenters on the site
	 *****************************************************************/
	public Tent(String nameReserving, GregorianCalendar checkIn, 
			int daysStaying, GregorianCalendar checkOutOn, 
			int siteNumber, int numOfTenters){
		
		super(nameReserving, checkIn, daysStaying, checkOutOn, siteNumber);
		this.numOfTenters = numOfTenters;
	}
	
	/******************************************************************
	 * A getter method for the variable numOfTenters
	 * 
	 * @return numOfTenters the number of tenters on the site
	 *****************************************************************/
	public int getNumOfTenters() {
		return numOfTenters;
	}

	/******************************************************************
	 * A setter method for the variable numOfTenters
	 * 
	 * @param numOfTenters the number of tenters on the site
	 *****************************************************************/
	public void setNumOfTenters(int numOfTenters) {
		this.numOfTenters = numOfTenters;
	}
}