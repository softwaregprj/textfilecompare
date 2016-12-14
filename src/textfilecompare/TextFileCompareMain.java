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
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * REQUIRES TextView.java
 */////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class TextFileCompareMain extends JFrame implements ActionListener, MenuListener, InternalFrameListener {

	private static final long serialVersionUID = 1L;
	private static JButton butt1, butt2, butt3, butt4;
	private static TextView intframe1, intframe2; // Used to create the JInternalFrames for opening documents
	private static Boolean box1=false;

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
		// This will be the title of the window when it is opened
		super("Text Compare");
		
		// Make the big window be indented 50 pixels from each edge of the screen.
		int inset = 50;
		// Dimension is a class containing 2 variables: width and height. This function sets these variables to be
		// equal to the height and width of the user's screen.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// Set the window size to take up the full screen minus a multiple of the inset value declared above.
		setBounds(inset, inset, screenSize.width - inset*2, screenSize.height - inset*2);
		
		Rectangle r = this.getBounds();
		
		//Set up the GUI.
		desktop = new JDesktopPane(); //a specialized layered pane
		setContentPane(desktop);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setVisible(true);
		panel.setBounds(0, 0, r.width, 30);
		butt1 = new JButton("Open");
		butt1.addActionListener(this);
		butt2 = new JButton("Compare");
		butt2.addActionListener(this);
		butt2.setEnabled(false);
		butt3 = new JButton("Save");
		butt3.addActionListener(this);
		butt3.setEnabled(false);
		butt4 = new JButton("Exit");
		butt4.addActionListener(this);
		panel.add(butt1);
		panel.add(butt2);
		panel.add(butt3);
		panel.add(butt4);
		
		desktop.add(panel);
		
		//Make dragging a little faster but perhaps uglier.
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		}
	

	
	//React to menu selections. This only works because we made sure to do the "addActionListener" command earlier. The
	// function "actionPerformed" is a standard function in Java that will be called by these listeners without having
	// to be explicitly written in code.
	public void actionPerformed(ActionEvent e)
	{
		try
		{
	
			if ("Open".equals(e.getActionCommand()))
			{
				if (!box1){
					openFile1UserAction();
				}
				else
					openFile2UserAction();
				Component[] frames = desktop.getComponents();
				if (frames.length >= 3){
					butt1.setEnabled(false);
					butt2.setEnabled(true);
				}
			}
			
			else if ("Compare".equals(e.getActionCommand()))
			{
				compareFilesUserAction();
				butt3.setEnabled(true);
				
			}
			
			else if ("Save As".equals(e.getActionCommand()))
			{
				saveFileUserAction();
			}
			
			else if ("Quit".equals(e.getActionCommand()))
			{
				exitApplicationUserAction();
			}
			
			else if ("Save".equals(e.getActionCommand()))
			{
				saveFileUserAction();
			}
			
			else if ("Exit".equals(e.getActionCommand()))
			{
				exitApplicationUserAction();
			}

		}
		catch ( Exception error)
		{
			error.printStackTrace();
		}
	}
	
	/*
	 * 	OPEN FILE
	 */
	//Create a new internal frame.
	protected void openFile1UserAction() throws PropertyVetoException {
		intframe1 = TextView.newInstance(this,false);
		if( intframe1.openTextDocument() == true)
		{
			Rectangle r = this.getBounds();
			intframe1.setTitle(intframe1.getFile().getName());
			intframe1.setVisible(true);
			
			intframe1.addInternalFrameListener(this);
			
			intframe1.setBounds(0, 30, r.width/3, r.height-60);
			
			desktop.add(intframe1);
			intframe1.setSelected(true);
			box1 = true;
		}
		else
		{
			intframe1 = null;
		}
	}
	
	protected void openFile2UserAction() throws PropertyVetoException {
		intframe2 = TextView.newInstance(this,false);
		if( intframe2.openTextDocument() == true)
		{
			Rectangle r = this.getBounds();
			intframe2.setTitle(intframe2.getFile().getName());
			intframe2.setVisible(true);
			
			intframe2.addInternalFrameListener(this);
			
			intframe2.setBounds(r.width/3, 30, r.width/3, r.height-60);
			
			desktop.add(intframe2);
			intframe2.setSelected(true);
		}
		else
		{
			intframe2 = null;
		}
	}
	
	/*
	 * 	ALGORTHIM FOR COMPARING THE TWO FILES
	 */
	protected void compareFilesUserAction() throws Exception
	{
		// "Frames" an object of type "Component". It is an array containing all of the already opened frames.
		Component[] frames = desktop.getComponents();
		// Only run comparison if there are two frames opened
		if( (frames != null) && (frames.length == 3  ) )
		{
			TextView textView1 = (TextView)frames[0];
			TextView textView2 = (TextView)frames[1];
			File file1 = textView1.getFile();
			File file2 = textView2.getFile();
			
			// Create new frame that will show the comparison between the two text documents
			TextView frame = TextView.newInstance(this,true);
			frame.setTitle("Comparison");
			frame.compareTextDocuments(file1,file2);
			// Add the frame to the window
			desktop.add(frame);
			frame.setVisible(true);
			frame.setSelected(true);
			
			Rectangle r = this.getBounds();
			frame.setBounds(2*r.width/3, 30, r.width/3, r.height-60);
			
			compareView=frame;
		}
	}
	
	protected void saveFileUserAction()
	{
		compareView.saveTextDocument();
	}
	
	
	//Quit the application.
	protected void exitApplicationUserAction()
	{
		System.exit(0);
	}

	
	
	
/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * 	INTERNAL FRAME LISTENER IMPLEMENTATIONS
 */////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		//open.setEnabled(true);
		butt1.setEnabled(true);
		butt2.setEnabled(false);
		if (arg0.getSource().equals(intframe1)){
			box1 = false;
		}
		//count = count-1;
		
	}
	
	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * 	MENU LISTENER IMPLEMENTATIONS
 */////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuSelected(MenuEvent arg0) {

	}

}
