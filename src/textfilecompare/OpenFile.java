/**
 * 
 */
package textfilecompare;

import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 * @author Cian McIntyre
 *
 */
public class OpenFile extends TextView {

	private static final long serialVersionUID = 1L;

	private TextFileCompareMain parent=null;
	private boolean box;
	private Rectangle r;
	
	public OpenFile(TextFileCompareMain parent, boolean box) throws InterruptedException, IOException, PropertyVetoException {			
		this.parent = parent;
		this.box = box;
		OpenFileCheck();
}
	
	public void OpenFileCheck() throws InterruptedException, IOException, PropertyVetoException{
		r = parent.getBounds();
		openTextDocument();
		if (!box){
			this.setBounds(0, 30, r.width/3, r.height-60);
		}
		else{
			this.setBounds(r.width/3, 30, r.width/3, r.height-60);
		}
		Thread.sleep(100); // Added for testing purposes
		parent.desktop.add(this);
		this.setSelected(true);
		this.addInternalFrameListener(parent);
	}
	
	public boolean openTextDocument() throws IOException {
		
		openBrowser();
	
		FileReader reader = null;
		reader = new FileReader(file);
		textArea.read(reader, null);
		
		this.setTitle(this.getFile().getName());
		this.textArea.setEditable(false);
		return true;
	}

	void openBrowser() {
		// Open browser window to find file
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(parent) != JFileChooser.APPROVE_OPTION){
			return;
		}

		// Set local file variable equal to the file chosen
		this.file = chooser.getSelectedFile();
		if(chooser.getSelectedFile()== null){
			return;
		}
		
	}
		
}

