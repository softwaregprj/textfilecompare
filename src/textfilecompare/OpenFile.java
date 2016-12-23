package textfilecompare;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;


public class OpenFile extends TextView {

	private static final long serialVersionUID = 1L;

	private TextFileCompareMain parent=null;
	private boolean box;
	
	
	// CONSTRUCTOR METHOD
	public OpenFile(TextFileCompareMain parent, boolean box) throws InterruptedException, IOException, PropertyVetoException {			
		this.parent = parent;
		this.box = box;
		buildFrame();
	}
	
	
	public void buildFrame() throws InterruptedException, IOException, PropertyVetoException{
		openTextDocument();
		frameSettings();
		Thread.sleep(100); // Added for testing purposes
		parent.desktop.add(this);
		this.setSelected(true);

	}

	// OPEN THE ACTUAL TEXT DOCUMENT
	public boolean openTextDocument() throws IOException {
		
		// Open the browser to find the file
		file = openBrowser();
	
		// Scan the text from the file to the text area
		FileReader reader = null;
		reader = new FileReader(file);
		textArea.read(reader, null);
		
		return true;
	}
	
	// SET FRAME SETTINGS DEPENDING ON WHETHER BOX1 IS OPEN OR NOT
	void frameSettings() throws PropertyVetoException {
		r = parent.getBounds();
		if (!box){
			this.setBounds(0, 30, r.width/3, r.height-70);
		}
		else{
			this.setBounds(r.width/3, 30, r.width/3, r.height-70);
		}
		
		this.addInternalFrameListener(parent);
		this.setTitle(this.getFile().getName());
		this.textArea.setEditable(false);
	}

	// OPEN BROWSER TO FIND FILES TO COMPARE
	public File openBrowser() {
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(parent) != JFileChooser.APPROVE_OPTION){
			return null;
		}

		// Set local file variable equal to the file chosen
		this.file = chooser.getSelectedFile();
		if(chooser.getSelectedFile()== null){
			return null;
		}
		
		return file;
		
	}
	
	// RETURN FILE OF DOCUMENT THAT IS OPEN IN THIS FRAME
	public File getFile(){
		return file;
	}
		
}

