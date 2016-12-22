/**
 * 
 */
package textfilecompare;

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
	
	public OpenFile(TextFileCompareMain parent) {			
			// Create the GUI and put it in the window.
			// Then set the window size or call pack.
			this.parent = parent;
			this.init();		
	}
	
	public boolean openTextDocument() throws IOException {
		
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(parent) != JFileChooser.APPROVE_OPTION){
			return false;
		}

		this.file = chooser.getSelectedFile();
		if(chooser.getSelectedFile()== null){
			return false;
		}
	
		FileReader reader = null;
		reader = new FileReader(file);
		textArea.read(reader, null);

		
		this.setTitle(this.getFile().getName());
		this.textArea.setEditable(false);
		return true;
	}
		
}

