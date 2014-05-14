package package1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;


public class DialogCheckInRV  extends JDialog implements ActionListener {
	
	/**A text field for the name of site holder to be inputted*/
	private JTextField nameTxt;

	/**Represents the site number*/
	private int site;
	
	/**Represents the number of days staying (estimate)*/
	private int days;
	
	/**Represents the amount of power*/
	private int power;
	
	/**The check in date of the site*/
	private Date checkIn;
	
	/**A button to confirm input*/
	private JButton okButton;
	
	/**A button to cancel input*/
	private JButton cancelButton;
	
	/**Represents whether it is okay to close*/
	private int closeStatus;

	/**Represents okay to close*/
	public static final int OK = 0;
	
	/**Represents not okay to close*/
	public static final int CANCEL = 1;
	
	/**An RV with its values for each variable*/
	private RV r;  
	
	/**A spinner model for site number*/
	private SpinnerNumberModel siteModel;
	
	/**A spinner model for days staying*/
	private SpinnerNumberModel stayingModel;
	
	/**A spinner model for amount of power*/
	private SpinnerNumberModel powerModel;
	
	/**A spinner model for check in date*/
	private SpinnerDateModel checkInModel;
	
	/**A spinner for site number*/
	private JSpinner spinner1;
	
	/**A spinner for days staying*/
	private JSpinner spinner2;
	
	/**A spinner for amount of power*/
	private JSpinner spinner3;
	
	/**A spinner for check in date*/
	private JSpinner spinner4;
		
	/*********************************************************
		 Instantiate a Custom Dialog as 'modal' and wait for the
		 user to provide data and click on a button.
		 
		 @param parent reference to the JFrame application
		 @param m an instantiated object to be filled with data
	 *********************************************************/
	
	public DialogCheckInRV(JFrame paOccupy, RV d) {
		// call parent and create a 'modal' dialog
		super(paOccupy, true);
		r = d;
		
		setTitle("Check out RV");
		closeStatus = CANCEL;
		setSize(400,200);
		 
		// prevent user from closing window
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		// instantiate and display text fields
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(6,2));
		
		//gets all inputs
		textPanel.add(new JLabel("Name of Reserver:"));
		nameTxt = new JTextField("Mary Doe",30);
		textPanel.add(nameTxt);
		
		textPanel.add(new JLabel("Requested site number:"));	
		siteModel = new SpinnerNumberModel(1, 1, 5, 1);
		spinner1 = new JSpinner(siteModel);
		textPanel.add(spinner1);
		
		textPanel.add(new JLabel("Checked In On:"));		
		checkInModel = new SpinnerDateModel();
	    spinner4 = new JSpinner(checkInModel);
	    JComponent editor = new JSpinner.DateEditor(spinner4, "MM/dd/yyyy");
	    spinner4.setEditor(editor);
	    textPanel.add(spinner4);
		
		textPanel.add(new JLabel("Days staying (estimate):"));
		stayingModel = new SpinnerNumberModel(3, 1, 1000000, 1);
		spinner2 = new JSpinner(stayingModel);
		textPanel.add(spinner2);
		
		
		textPanel.add(new JLabel("Power:"));
		powerModel = new SpinnerNumberModel(30, 1, 1000000, 1);
		spinner3 = new JSpinner(powerModel);
		textPanel.add(spinner3);
		
		
		getContentPane().add(textPanel, BorderLayout.CENTER);
		
		// Instantiate and display two buttons
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		setSize(300,300);
		setVisible (true);	
	}

	public void setCloseStatus(int closeStatus) {
		this.closeStatus = closeStatus;
	}

	/**************************************************************
		 Respond to either button clicks
		 @param e the action event that was just fired
	 **************************************************************/
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton) e.getSource();
		
		// if OK clicked the fill the object
		if (button == okButton) {
			// save the information in the object
			closeStatus = OK;

			try {
				
				//makes date into GregorianCalendar
				checkIn = (Date) spinner4.getValue();
				GregorianCalendar opened = new GregorianCalendar();
				opened.setTime(checkIn);

				//gets values from spinners
				site = (int) spinner1.getValue();
				days = (int) spinner2.getValue();
				power = (int) spinner3.getValue();

				//sets variables for RV
				r.setNameReserving(nameTxt.getText());
				r.setCheckIn(opened);
				r.setSiteNumber(site);
				r.setDaysStaying(days);
				r.setPower(power);
				
				//estimates the cost owed
				int cost = (30 * days);
				String str = "You will owe $" + cost + " at checkout";
				JOptionPane.showMessageDialog(null, str);

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Uh oh! Something went wrong. Try again.");
			}
		}

		// make the dialog disappear
		dispose();
	}
	
	/**************************************************************
		 Return a String to let the caller know which button
		 was clicked
		 
		 @return an int representing the option OK or CANCEL
	 **************************************************************/
	public int getCloseStatus(){
		return closeStatus;
	}
}