package textfilecompare;

import java.awt.Color;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;


public class CompareFile extends TextView {

	private static final long serialVersionUID = 1L;
	private TextFileCompareMain parent=null;
	private File file1, file2;
	
//	CONSTRUCTOR
	public CompareFile(TextFileCompareMain parent, OpenFile intframe1, OpenFile intframe2, int count) throws Exception {
//		Only run if there are two text files already open.
		if (count == 2){
			this.createTextWindow();
			this.parent=parent;
			setupComparison(intframe1, intframe2);
		}
	}

//	PREPARE FILES FOR COMPARISON ALGORITHM
	void setupComparison(OpenFile intframe1, OpenFile intframe2) throws Exception, PropertyVetoException {
		// Get the files from both frames
		file1 = intframe1.getFile();
		file2 = intframe2.getFile();
		
		// Run files through comparison algorithm
		compareTextDocuments(file1,file2);
		
		// Set frame settings
		frameSettings();
		
		// Add the frame to the window
		parent.desktop.add(this);
		this.setSelected(true);
		parent.compareView = this;
	}

	// SET THE FRAME SETTINGS
	void frameSettings() throws PropertyVetoException {
		r = parent.getBounds();
		this.setTitle("Comparison");
		this.setBounds( (2*r.width)/3 - 5, 30, r.width/3 - 5, r.height-70);
		this.setVisible(true);	
	}	
	
	// COMPARISON ALGORITHM
	public JTextPane compareTextDocuments(File file1, File file2)throws IOException{
		//Initialisations
		FileReader fR1 = new FileReader(file1);
		FileReader fR2 = new FileReader(file2);
	
		BufferedReader reader1 = new BufferedReader(fR1, 80000);
		BufferedReader reader2 = new BufferedReader(fR2, 80000);
	
		String line1 = null;
		String line2 = null;
		String block_merge_text = null;
		String line_reset1 = null;
		String line_reset2 = null;
		
		//block_test: Used to state whether a block of text differences was printed to the output file
		boolean block_text = false;
	
		//Read text files until the line = null (Both files finished)
		while ( ((line1 = reader1.readLine()) != null) && ( (line2 = reader2.readLine()) != null) )
		{
			//Set markers for line1 and line2
			line_reset1 = line1;
			line_reset2 = line2;
			reader1.mark(80000);
			reader2.mark(80000);
	
			//If difference is found - loop
			if (!line1.equals(line2)){
				block_text = false;
				block_merge_text = line2 + "\n";
				
				//Loop through file 2, while checking each line against line1
				while((line2 = reader2.readLine()) != null){	
					//No similarities found - loop
					if(!line1.equals(line2)){
						//Create text block of different text (block_merge_text)
						//Will be added to merged file if a similarity is found further down the file
						block_merge_text += line2 + "\n"; 
					}
					else{ //Similar text lines found
					
						block_text = true; // A block of text is printed to output file
						appendTextLine(block_merge_text, Color.BLUE); //difference
						appendTextLine( line1, Color.BLACK); //Similar line
						appendTextLine( "\n", Color.BLACK);
					}
						
				}
				//Similarity still not found after looping through file 2
				//Loop through file 1 in the same way as seen above.
				if (block_text == false){
					// Reset pointer position of file 1 & 2 readers to marked point ( reader2.mark() )
					reader1.reset();
					reader2.reset();
					reader1.mark(80000);
					reader2.mark(80000);
					line2 = line_reset2;
					block_merge_text = line1+"\n";
					
					while((line1 = reader1.readLine()) != null)	{
						if(!line2.equals(line1)){
							block_merge_text += line1 + "\n";
						}
						else{
							block_text = true;
							appendTextLine( block_merge_text, Color.RED);
							appendTextLine( line2, Color.BLACK);
							appendTextLine( "\n", Color.BLACK);
						}	
					}
					
					if(block_text == false)	{
						line1 = line_reset1;
						appendTextLine( line1, Color.RED);
						appendTextLine( "\n", Color.BLACK);
						appendTextLine( line2, Color.BLUE);
						appendTextLine( "\n", Color.BLACK);
					}
				}
				reader1.reset();
				reader2.reset();
				
			}
		
			else {//Lines are the same, print line to merged file
			
				appendTextLine( line1, Color.BLACK);
				appendTextLine( "\n", Color.BLACK);
			}
		}
		reader1.close();
		reader2.close();
		return this.textArea;
	}


	private void appendTextLine(String msg, Color c){
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
		
		int len = this.textArea.getDocument().getLength();
		this.textArea.setCaretPosition(len);
		this.textArea.setCharacterAttributes(aset, false);
		this.textArea.replaceSelection(msg);
	}


}
