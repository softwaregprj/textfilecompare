package textfilecompare;

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
	
//	CONSTRUCTOR METHOD
	public ButtonPanel(int width, TextFileCompareMain parent) {
		panelSettings(width);
		populatePanel(parent);
		parent.desktop.add(this);
	}

//	SETTINGS FOR PANEL
	void panelSettings(int width) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setVisible(true);
		this.setBounds(0, 0, width, 30);
	}
	
//	FILL THE PANEL WITH BUTTONS
	public void populatePanel(TextFileCompareMain parent){	
		openButt = new JButton("Open");
		compareButt = new JButton("Compare");
		compareButt.setEnabled(false);
		saveButt = new JButton("Save");
		saveButt.setEnabled(false);
		exitButt = new JButton("Exit");
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
	
//	Used so main function can easily enable/disable buttons
	public JButton giveButton(int butt){
		if (butt == 1){
			return openButt;
		}
		
		else if (butt == 2){
			return compareButt;
		}
		
		else if (butt == 3){
			return saveButt;
		}
		
		else if (butt == 4){
			return exitButt;
		}
		
		else{
			return null;
		}
		
	}
}
