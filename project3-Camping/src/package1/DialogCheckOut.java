package package1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.WindowConstants;

public class DialogCheckOut extends JDialog implements ActionListener {

	/**The date of actual check out*/
	private Date checkOut;

	/**Represents okay to close*/
	public static final int OK = 0;
	
	/**Represents not okay to close*/
	public static final int CANCEL = 1;
	
	/**Represents whether it is okay to close*/
	private int closeStatus;
	
	/**A button to confirm input*/
	private JButton okButton;
	
	/**A button to cancel input*/
	private JButton cancelButton;

	/**A model for the check out spinner*/
	private SpinnerDateModel checkOutModel;
	
	/**A spinner for the checkout date*/
	private JSpinner spinner1;

	/**A tent instance*/
	private Tent t;
	
	/**An RV instance*/
	private RV r;
	
	/**The actual cost to stay at campground*/
	private int cost;
	
	/**A site instance*/
	private Site s;

	/******************************************************************
	 * Instantiate a Custom Dialog as 'modal' and wait for the
	 * user to provide data and click on a button.
	 * 
	 * @param paOccupy reference to the JFrame application
	 * @param s an instantiated object to be filled with data
	 *****************************************************************/
	public DialogCheckOut(JFrame paOccupy, Site s) {
		// call parent and create a 'modal' dialog
		super(paOccupy);
		setTitle("Check-out Bill");
		closeStatus = CANCEL;
		setSize(400,200);
		try{
			this.t = (Tent) s;
		}catch(ClassCastException e){
			this.r = (RV) s;	
		}
		this.s = s;

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(6,2));

		textPanel.add(new JLabel("Date of check-out"));
		checkOutModel = new SpinnerDateModel();
		spinner1 = new JSpinner(checkOutModel);
		JComponent editor = new JSpinner.DateEditor(spinner1, "MM/dd/yyyy");
		spinner1.setEditor(editor);
		textPanel.add(spinner1);

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
	
	/******************************************************************
	 * Sets the close status of the dialog
	 * 
	 * @param closeStatus the status of the dialog
	 *****************************************************************/
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

			int days = 0;

			try {
				//makes date into GregorianCalendar
				checkOut = (Date) spinner1.getValue();
				GregorianCalendar closed = new GregorianCalendar();
				closed.setTime(checkOut);

				//counts days stayed
				while(closed.after(s.getCheckIn())){
					closed.add(GregorianCalendar.DAY_OF_MONTH, -1);
					days++;
				}

				//determines cost for tent
				cost = days * 3 * t.getNumOfTenters();

			} catch (Exception e1) {
				//determines cost for RV
				cost = days * 30;
			}

			String str = "You owe $" + cost;
			JOptionPane.showMessageDialog(null, str);
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