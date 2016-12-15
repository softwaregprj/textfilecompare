package textfilecompare;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;



/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * REQUIRES TextView.java
 */////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class TextFileCompareMain extends JFrame implements ActionListener, InternalFrameListener {

	private static final long serialVersionUID = 1L;
	private static JButton butt1, butt2, butt3, butt4;
	private static TextView intframe1, intframe2; // Used to create the JInternalFrames for opening documents
	private static JPanel panel;
	private static Boolean box1=false;
	private static Rectangle r;

	JDesktopPane desktop;
	TextView compareView = null;

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
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		//Create and set up the window.
		TextFileCompareMain frame = new TextFileCompareMain();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Display the window.
		frame.setVisible(true);
	}

	
	public TextFileCompareMain() {
		super("Text Compare");
		
		// Make the big window be indented 50 pixels from each edge of the screen.
		int inset = 50;
		
		// Dimension is a class containing 2 variables: width and height. This function sets these variables to be
		// equal to the height and width of the user's screen.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Set the window size to take up the full screen minus a multiple of the inset value declared above.
		setBounds(inset, inset, screenSize.width - inset*2, screenSize.height - inset*2);
		
		// Get window dimensions for setting various boundaries
		r = this.getBounds();
		
		//Set up the GUI.
		desktop = new JDesktopPane(); //a specialized layered pane
		setContentPane(desktop);
		
		// Add panel of buttons to top of window
		panel = new JPanel();
		createPanel(panel);
		desktop.add(panel);
	}

	// CREATE PANEL AT TOP OF THE PAGE TO HOLD BUTTONS
	public void createPanel(JPanel inpanel){
		inpanel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		inpanel.setVisible(true);
		inpanel.setBounds(0, 0, r.width, 30);
		populatePanel(inpanel);

	}
	
	// FILL THE PANEL UP WITH BUTTONS
	public void populatePanel(JPanel inpanel){
		butt1 = new JButton("Open");
		butt2 = new JButton("Compare");
		butt3 = new JButton("Save");
		butt4 = new JButton("Exit");
		createButton(butt1, inpanel);
		createButton(butt2, inpanel);
		createButton(butt3, inpanel);
		createButton(butt4, inpanel);
	}
	
	// CREATE BUTTONS TO ADD TO PANEL
	public void createButton(JButton butt,JPanel panel){
		butt.addActionListener(this);
		panel.add(butt);
	}
	
	// CHECK WHICH FILE TO OPEN
	private void preOpenFileCheck() throws PropertyVetoException {
		if (!box1){
			intframe1 = TextView.newInstance(this,false);
			openFile(intframe1);
			box1 = true;
		}
		else{
			intframe2 = TextView.newInstance(this,false);
			openFile(intframe2);
		}
		Component[] frames = desktop.getComponents();
		if (frames.length >= 3){
			butt1.setEnabled(false);
			butt2.setEnabled(true);
		}
	}
	
	// OPEN FILE
	public void openFile(TextView intframe) throws PropertyVetoException{
		if (intframe.openTextDocument()==true){
			intframe.setTitle(intframe.getFile().getName());
			intframe.setVisible(true);
			//Rectangle r = this.getBounds();
			if (!box1)
				intframe.setBounds(0, 30, r.width/3, r.height-60);
			else
				intframe.setBounds(r.width/3, 30, r.width/3, r.height-60);
			desktop.add(intframe);
			intframe.setSelected(true);
			intframe.addInternalFrameListener(this);
		}
		
		else{
			intframe = null;
		}
	}

	//	COMPARING THE TWO FILES
	protected void compareFiles() throws Exception{
		// "Frames" an object of type "Component". It is an array containing all of the already opened frames.
		Component[] frames = desktop.getComponents();
		// Only run comparison if there are two frames opened
		if( (frames != null) && (frames.length == 3  ) ){
			TextView textView1 = (TextView)frames[0];
			TextView textView2 = (TextView)frames[1];
			File file1 = textView1.getFile();
			File file2 = textView2.getFile();
			
			createCompareFrame(file1, file2);
			
			butt3.setEnabled(true);	
		}
	}

	// CREATE FRAME FOR HOLDING MERGED FILE
	private void createCompareFrame(File file1, File file2) throws Exception, PropertyVetoException {
		// Create new frame that will show the comparison between the two text documents
		TextView frame = TextView.newInstance(this,true);
		frame.setTitle("Comparison");
		frame.compareTextDocuments(file1,file2);
		frame.setBounds(2*r.width/3, 30, r.width/3, r.height-60);
		frame.setVisible(true);
		// Add the frame to the window
		desktop.add(frame);
		frame.setSelected(true);
		compareView=frame;
	}
	
	//	SAVE NEW FILE
	protected void saveFile(){
		compareView.saveTextDocument();
	}
	
	//	QUIT THE APPLICATION
	protected void exitApplicationUserAction(){
		System.exit(0);
	}

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * 	ACTION LISTENER IMPLEMENTATIONS
 */////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
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
		butt1.setEnabled(true);
		butt2.setEnabled(false);
		if (arg0.getSource().equals(intframe1)){
			box1 = false;
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
