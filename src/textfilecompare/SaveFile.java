package textfilecompare;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;


public class SaveFile extends TextView {

	private static final long serialVersionUID = 1L;
	private TextFileCompareMain parent; 
	private CompareFile compareView; // The file we're saving

	
	// CONSTRUCTOR METHOD
	public SaveFile(TextFileCompareMain parent, CompareFile compareView) throws IOException {
		this.parent = parent;
		this.compareView = compareView;
		saveTextDocument();
	}
	
	// WRITE FILE TO CHOSEN DESTINATION
	public boolean saveTextDocument() throws IOException{
		
		File file = openBrowser();
		
		FileWriter writer = null;
		writer = new FileWriter(file);
		compareView.textArea.write(writer);
		return true;
		
	}

	// BROWSE FOR LOCATION TO SAVE FILE TO
	public File openBrowser() {
		JFileChooser chooser = new JFileChooser();
		if (chooser.showSaveDialog(parent) != JFileChooser.APPROVE_OPTION)
			return null;
		
		File file = chooser.getSelectedFile();
		
		if (file == null)
			return null;
		return file;
	}
		
}
