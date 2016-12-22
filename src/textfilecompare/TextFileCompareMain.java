package textfilecompare;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import textfilecompare.ButtonPanel;



/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * REQUIRES TextView.java
 */////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class TextFileCompareMain extends JFrame implements ActionListener, InternalFrameListener {

	private static final long serialVersionUID = 1L;
	private static OpenFile intframe1, intframe2; // Used to create the JInternalFrames for opening documents
	private static ButtonPanel panel;
	private static Boolean box=false;
	private static int count=0; // keeps track of the number of opened text files

	JDesktopPane desktop;
	CompareFile compareView = null;

/*	The main() method. It doesn't really do anything except kickstart a series of other methods that will be
 * 	called. The first of which will open a window in the program like we want it to.
 */
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowApplication();
			}
		});
	}

/*
 * 	Called by the main() method. This will run the TextFileCompareMain() method in a new frame
 */
	private static void createAndShowApplication() {
		//Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(false);
		
		//Create and set up the window.
		TextFileCompareMain frame = new TextFileCompareMain();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Display the window.
		frame.setVisible(true);
	}

	
	public TextFileCompareMain() {
		super("Text Compare");
		box = false; // reset value for all new objects
		// Make the big window be indented 50 pixels from each edge of the screen.
		int inset = 50;
		
		// Dimension is a class containing 2 variables: width and height. This function sets these variables to be
		// equal to the height and width of the user's screen.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Set the window size to take up the full screen minus a multiple of the inset value declared above.
		setBounds(inset, inset, screenSize.width - inset*2, screenSize.height - inset*2);
		
		//Set up the GUI.
		desktop = new JDesktopPane(); //a specialized layered pane
		setContentPane(desktop);
		
		// Add panel of buttons to top
		panel = new ButtonPanel(this);
		
	}

	// CHECK WHICH TEXT BOX TO OPEN
	public void preOpenFileCheck() throws PropertyVetoException, InterruptedException, IOException {
		// If the first box is not open, we want to open that one next. After doing so, we want to change the status of
		// the "box" boolean to indicate that the first box is now open. This will cause the next box we open to be
		// shifted to the center of the screen so the two don't overlap. In addition, we need to keep track of the
		// first box (labeled "intframe1") because we need to listen in case this particular internal frame is closed.
		// When it is closed, box will be set back to zero again.
		if (!box){
			intframe1 = new OpenFile(this, box);
			box = true;
		}
		else{
			intframe2 = new OpenFile(this, box);
		}
		
		count++;
//		When we have two windows opened, we disable the "Open" button and enable the "Compare" button.
		if (count >= 2){
			panel.disableButton(1);
			panel.enableButton(2);
		}
	}
	
	
	//	COMPARE THE TWO FILES
	public void compareFiles() throws Exception{
		new CompareFile(this, intframe1, intframe2, count);
		// Enable the "Save" button after performing comparison	
		panel.enableButton(3);
	}
	
	//	SAVE NEW FILE
	public void saveFile() throws IOException{
		new SaveFile (this, compareView);
	}
	
	//	QUIT THE APPLICATION
	public void exitApplicationUserAction(){
		System.exit(0);
	}

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * 	ACTION LISTENER IMPLEMENTATIONS
 */////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	// This section listens for the user to click one of the buttons and runs a function to respond accordingly
	public void actionPerformed(ActionEvent e){
		try{
	
			if ("Open".equals(e.getActionCommand())){
				preOpenFileCheck();
			}
			
			else if ("Compare".equals(e.getActionCommand())){
				compareFiles();
			}
			
			else if ("Save".equals(e.getActionCommand())){
				saveFile();
			}
			
			else if ("Exit".equals(e.getActionCommand())){
				exitApplicationUserAction();
			}
		}
		catch ( Exception error){
			error.printStackTrace();
		}
	}

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * 	INTERNAL FRAME LISTENER IMPLEMENTATIONS
 */////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
//		This section runs whenever an internal frame holding one of the documents closes. When this happens, we want to
//		re-enable the "Open" button and disable the "Compare" button. Also, if the box that is closed is the first box,
//		we reset the "box" variable to false, so the next time a window is opened, it will be at that location.
		panel.enableButton(1);
		panel.disableButton(2);
		count--;
		if (arg0.getSource().equals(intframe1)){
			box = false;
		}
	}
	
	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent arg0) {
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent arg0) {
	}


}