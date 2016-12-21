package textfilecompare;

import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
	private JButton openButt, compareButt, saveButt, exitButt;
	public JButton buttons[] = {openButt, compareButt , saveButt, exitButt };
	
	public ButtonPanel(int width) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setVisible(true);
		this.setBounds(0, 0, width, 30);
		populatePanel();
	}
	
	// FILL THE PANEL UP WITH BUTTONS
	public void populatePanel(){	
		openButt = new JButton("Open");
		compareButt = new JButton("Compare");
		compareButt.setEnabled(false);
		saveButt = new JButton("Save");
		saveButt.setEnabled(false);
		exitButt = new JButton("Exit");
		createButton(openButt);
		createButton(compareButt);
		createButton(saveButt);
		createButton(exitButt);
	}

	// CREATE BUTTONS TO ADD TO PANEL
	public void createButton(JButton butt){
//		butt.addActionListener(new ActionListener());
		this.add(butt);
	}
	
	public JButton giveButton(int butt){
		if (butt == 1){
			return openButt;
		}
		
		if (butt == 2){
			return compareButt;
		}
		
		if (butt == 3){
			return saveButt;
		}
		
		else if (butt == 4){
			return exitButt;
		}
		
		else{
			return null;
		}
		
	}


	public ButtonPanel(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ButtonPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public ButtonPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
