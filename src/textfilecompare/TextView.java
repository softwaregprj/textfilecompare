package textfilecompare;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/* Used by TextFileCompareMain.java. */
public class TextView extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;
	
	protected JTextPane textArea=null;
	private JScrollPane scroll=null;
	private TextFileCompareMain parent=null;
	protected File file = null;
	protected Rectangle r;
	
	
	public TextView() {
		super("Document #" + (++openFrameCount),
				false, //resizable
				true, //closable
				false, //maximizable
				false);//iconifiable i.e. minimisable
		this.createTextWindow();
	}
	
	
	public TextView(TextFileCompareMain parent) {
		super("Document #" + (++openFrameCount),
			false, //resizable
			true, //closable
			false, //maximizable
			false);//iconifiable i.e. minimisable
		
		// Create the GUI and put it in the window.
		// Then set the window size or call pack.
		this.parent = parent;
		this.createTextWindow();
	}
	
	protected void createTextWindow(){
		this.textArea = new JTextPane();
		// Create the StyleContext, the document and the pane
		this.scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public void saveTextDocument(){
		JFileChooser chooser = new JFileChooser();
		if (chooser.showSaveDialog(parent) !=
			JFileChooser.APPROVE_OPTION)
			return;
		File file = chooser.getSelectedFile();
		if (file == null)
			return;
		
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			textArea.write(writer);
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(this.parent,
			"File Not Saved", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException x) {}
			}
		}
	}
}