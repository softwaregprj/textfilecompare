package textfilecompare;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.KeyStroke;


/*
 * TextFileCompareMain.java requires:
 *   TextView.java
 */
public class TextFileCompareMain extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JDesktopPane desktop;
	TextView compareView = null;

    public TextFileCompareMain() {
        super("Text Compare");

        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                  screenSize.width  - inset*2,
                  screenSize.height - inset*2);

        //Set up the GUI.
        desktop = new JDesktopPane(); //a specialized layered pane
        //createFrame(); //create first "window"
        setContentPane(desktop);
        setJMenuBar(createMenuBar());

        //Make dragging a little faster but perhaps uglier.
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }

    // CREATE MENU BAR
    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //Set up the lone menu.
        JMenu menu = new JMenu("Document");
        menuBar.add(menu);

        //Set up the first menu item.
        JMenuItem menuItem = new JMenuItem("Open");
        menuItem.setActionCommand("open");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Set up the first menu item.
        menuItem = new JMenuItem("Compare");
        menuItem.setActionCommand("compare");
        menuItem.addActionListener(this);
        menu.add(menuItem);


        //Set up the first menu item.
         menuItem = new JMenuItem("Save As");
         menuItem.setActionCommand("save");
         menuItem.addActionListener(this);
         menu.add(menuItem);

        //Set up the second menu item.
        menuItem = new JMenuItem("Quit");
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menuBar;
    }

    //React to menu selections.
    public void actionPerformed(ActionEvent e)
    {
    	try
    	{

            if ("open".equals(e.getActionCommand()))
            {
    			openFileUserAction();
            }
            else if ("compare".equals(e.getActionCommand()))
            {
            	compareFilesUserAction();
            }
            else if ("save".equals(e.getActionCommand()))
            {
            	saveFileUserAction();
            }
            else if ("quit".equals(e.getActionCommand()))
            {
            	exitApplicationUserAction();
            }
    	}
    	catch ( Exception error)
    	{
    		error.printStackTrace();
    	}
    }

    protected void compareFilesUserAction() throws Exception
    {
    	Component[] frames = desktop.getComponents();
    	if( (null != frames) && ( 2 == frames.length ) )
    	{
	    	TextView textView1 = (TextView)frames[0];
	    	TextView textView2 = (TextView)frames[1];
	    	File file1 = textView1.getFile();
	    	File file2 = textView2.getFile();
	    	TextView frame = TextView.newInstance(this,true);
	        frame.compareTextDocuments(file1,file2);
	        desktop.add(frame);
	        frame.setVisible(true);
	        frame.setSelected(true);
	        compareView=frame;
    	}
    }

    protected void saveFileUserAction()
    {
    	compareView.saveTextDocument();
    }

    //Create a new internal frame.
    protected void openFileUserAction() throws PropertyVetoException {
        TextView frame = TextView.newInstance(this,false);
        if( frame.openTextDocument() == true)
        {
        	frame.setTitle(frame.getFile().getName());
        	frame.setVisible(true);
        	desktop.add(frame);
        	frame.setSelected(true);
        }
        else
        {
        	frame = null;
        }
    }
    //Quit the application.
    protected void exitApplicationUserAction()
    {
        System.exit(0);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
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

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	createAndShowApplication();
            }
        });
    }
}
