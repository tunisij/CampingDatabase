package package1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.WindowConstants;

/**********************************************************************
 * A dialog that displays the current status of the campground
 * 
 * @author John Tunisi
 * @version 10/29/13
 *********************************************************************/
public class CampFullStatus extends JDialog implements ActionListener {

	/**Determines if closeable*/
	public static final int OK = 0;
	
	/**Determines if closeable*/
	public static final int CANCEL = 1;
	
	/**The status of the dialog*/
	private int closeStatus;
	
	/**Confirms and closes dialog*/
	private JButton okButton;
	
	/**Closes dialog*/
	private JButton cancelButton;
    
	/**The date to check about campground*/
    private GregorianCalendar statusDate;

    /******************************************************************
     * Instantiate a Custom Dialog as 'modal' and wait for the
	 * user to provide data and click on a button.
     * 
     * @param paOccupy reference to the JFrame application
     * @param cal an instantiated object to be filled with data
     *****************************************************************/
	public CampFullStatus(JFrame paOccupy) {
		// call parent and create a 'modal' dialog
		super(paOccupy);
		setTitle("Camp Status");
		closeStatus = OK;
		setSize(400,200);

		// Instantiate and display two buttons
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		statusDate();
		setVisible (true);
		
		

	}
	
	/******************************************************************
	 * Creates a spinner to select the date to view
	 *****************************************************************/
	public void statusDate(){
		SpinnerDateModel statusModel = new SpinnerDateModel();
	    JSpinner spinner = new JSpinner(statusModel);
	    JComponent editor = new JSpinner.DateEditor(spinner, "MM/dd/yyyy");
	    spinner.setEditor(editor);
		JOptionPane.showMessageDialog(this, spinner);
		
		Date d1 = (Date) spinner.getValue();
		statusDate = new GregorianCalendar();
		statusDate.setTime(d1);
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