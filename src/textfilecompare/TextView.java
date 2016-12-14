package textfilecompare;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/* Used by TextFileCompareMain.java. */
public class TextView extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    private JTextPane textArea=null;
    private JScrollPane scroll=null;
    private TextFileCompareMain parent=null;
    private File file = null;

    private TextView(TextFileCompareMain parent) {
        super("Document #" + (++openFrameCount),
              false, //resizable
              true, //closable
              false, //maximizable
              false);//iconifiable

        //...Create the GUI and put it in the window...

        //...Then set the window size or call pack...
        this.parent = parent;
    }
    
    
    public static TextView newInstance(TextFileCompareMain parent, boolean editable)
    {
    	TextView textView = new TextView(parent);
    	textView.init(editable);
		return textView;

    }
    public void init(boolean editable)
    {
    	createTextWindow(editable);
    	setSize(300,300);
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }

    protected void createTextWindow(boolean editable)
    {
		this.textArea = new JTextPane();
		 // Create the StyleContext, the document and the pane
	    //StyleContext sc = new StyleContext();
	    //final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
	    //this.textArea = new JTextPane(doc);
		this.scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//textArea.setLineWrap(true);
		this.textArea.setEditable(editable);
		this.add(scroll, BorderLayout.CENTER);
    }

    // Query user for a filename and attempt to open and read the file into the
    // text component.
    public boolean openTextDocument()
    {
      JFileChooser chooser = new JFileChooser();
      if (chooser.showOpenDialog(parent) != JFileChooser.APPROVE_OPTION)
        return false;

      this.file = chooser.getSelectedFile();
      if( null == chooser.getSelectedFile())
      {
    	  return false;
      }

      this.file = chooser.getSelectedFile();
      FileReader reader = null;
      try
      {
        reader = new FileReader(file);
        textArea.read(reader, null);
      }
      catch (IOException ex)
      {
        JOptionPane.showMessageDialog(this.parent,
        "File Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);
      }
      finally
      {
        if (reader != null)
        {
          try
          {
            reader.close();
          } catch (IOException x) {}
        }
      }
      return true;
    }


    public void saveTextDocument()
    {
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
    
    
    /*
     * This method is called directly from the TextFileCompareMain java file
     */
    public void compareTextDocuments(File file1, File file2)throws Exception
    {
		FileReader fR1 = new FileReader(file1);
		FileReader fR2 = new FileReader(file2);

		BufferedReader reader1 = new BufferedReader(fR1);
		BufferedReader reader2 = new BufferedReader(fR2);

		String line1 = null;
		String line2 = null;
		while ( ((line1 = reader1.readLine()) != null) && ( (line2 = reader2.readLine()) != null) )
		{
			appendTextLine( "\n", Color.BLACK);
		    if (!line1.equals(line2))
		    {
		    	appendTextLine( line1, Color.RED);
		    	appendTextLine( "\n", Color.BLACK);
		    	appendTextLine( line2, Color.BLUE);
		    }
		    else
		    {
		    	appendTextLine( line1, Color.BLACK);
		    }
		}
		reader1.close();
		reader2.close();
    }
    
    
    private void appendTextLine(String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
        //aset = sc.addAttribute(aset, StyleConstants.FontFamily, "serif");
        //aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = this.textArea.getDocument().getLength();
        this.textArea.setCaretPosition(len);
        this.textArea.setCharacterAttributes(aset, false);
        this.textArea.replaceSelection(msg);
    }

    public File getFile()
    {
    	return file;
    }
}
