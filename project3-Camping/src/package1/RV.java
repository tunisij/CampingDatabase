package package1;

import java.util.GregorianCalendar;

/**********************************************************************
 * RV is a type of site within the program
 * 
 * @author John Tunisi
 * @version 10/21/13
 *********************************************************************/
public class RV extends Site {

	/** Represents the power supplied to the site */
	private int power;   // 30, 40, 50 amps of service.

	/******************************************************************
	 * A getter method for the variable power
	 * 
	 * @return power the amount of power used by the RV
	 *****************************************************************/
	public int getPower() {
		return power;
	}

	/******************************************************************
	 * A setter method for the variable numOfTenters
	 * 
	 * @param numOfTenters the number of tenters on the site
	 *****************************************************************/
	public void setPower(int power) {
		this.power = power;
	}
	
	/******************************************************************
	 * A constructor that defaults values
	 *****************************************************************/
	public RV(){
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
	 * @param power the amount of power used by the RV
	 *****************************************************************/
	public RV(String nameReserving, GregorianCalendar checkIn,
			int daysStaying, GregorianCalendar checkOutOn,
			int siteNumber, int power){
		
		super(nameReserving, checkIn, daysStaying, checkOutOn, siteNumber);
		this.power = power;
	}
}