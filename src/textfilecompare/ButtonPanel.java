package textfilecompare;

import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Cian McIntyre
 *
 */

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
	private JButton openButt, compareButt, saveButt, exitButt;
	private Rectangle r;
	
//	CONSTRUCTOR METHOD
	public ButtonPanel(TextFileCompareMain parent) {
		panelSettings(parent);
		populatePanel(parent);
		parent.desktop.add(this);
	}

//	SETTINGS FOR PANEL
	void panelSettings(TextFileCompareMain parent) {
		r = parent.getBounds();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setVisible(true);
		this.setBounds(0, 0, r.width, 30);
	}
	
//	FILL THE PANEL WITH BUTTONS
	public void populatePanel(TextFileCompareMain parent){	
		openButt = new JButton("Open");
		compareButt = new JButton("Compare");
		saveButt = new JButton("Save");
		exitButt = new JButton("Exit");
		
		// "Compare" and "Save" are both disabled until certain conditions are met
		saveButt.setEnabled(false);
		compareButt.setEnabled(false);
		createButton(openButt, parent);
		createButton(compareButt, parent);
		createButton(saveButt, parent);
		createButton(exitButt, parent);
	}

	// CREATE BUTTONS TO ADD TO PANEL
	public void createButton(JButton butt, TextFileCompareMain parent){
		butt.addActionListener(parent);
		this.add(butt);
	}
	
	// DISABLE PARTICULAR BUTTON
	public void disableButton(int butt){
		if (butt == 1){
			openButt.setEnabled(false);
		}
		
		else if (butt == 2){
			compareButt.setEnabled(false);
		}
		
		else if (butt == 3){
			saveButt.setEnabled(false);
		}
	}
	
	// ENABLE PARTICULAR BUTTON
	public void enableButton(int butt){
		if (butt == 1){
			openButt.setEnabled(true);
		}
		
		else if (butt == 2){
			compareButt.setEnabled(true);
		}
		
		else if (butt == 3){
			saveButt.setEnabled(true);
		}
	}
}
