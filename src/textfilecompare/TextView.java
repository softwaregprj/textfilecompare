package textfilecompare;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.File;


/* Used by TextFileCompareMain.java. */
public abstract class TextView extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;
	
	protected JTextPane textArea=null;
	private JScrollPane scroll=null;
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

	protected void createTextWindow(){
		this.textArea = new JTextPane();
		this.scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll, BorderLayout.CENTER);
		this.setVisible(true);
	}
}