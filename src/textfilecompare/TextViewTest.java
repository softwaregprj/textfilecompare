package textfilecompare;

import static org.junit.Assert.*;
import java.io.File;
import java.net.URL;

import org.junit.Test;

public class TextViewTest {

	@Test // Two different files inputted.
	public void test_compareTextDocuments_diff() {
		
		URL url1 = getClass().getResource("Input_File1.txt");
		File file1 = new File(url1.getPath());
		
		URL url2 = getClass().getResource("Input_File2.txt");
		File file2 = new File(url2.getPath());
		
		URL url3 = getClass().getResource("Merged_file.txt");
		File merged_file_expected = new File(url3.getPath());
		
		TextView object = new TextView(); 
		merged_file_actual = object.compareTextDocuments(file1, file2);
		
		static List_merged_file_expected readLines(merged_file_expected);
		static List_merged_file_actual readLines(merged_file_actual);
		
		assertSame("The expected merged file should be equal to the actual. ", List_merged_file_expected, List_merged_file_actual);

	}
	
	@Test //Two identical files inputted.
	public void test_compareTextDocuments_same() {
		
		URL url1 = getClass().getResource("Input_File1.txt");
		File file1 = new File(url1.getPath());
		
		TextView object = new TextView(); 
		merged_file_actual = object.compareTextDocuments(file1, file1);
		
		static List_merged_file_expected readLines(file1);
		static List_merged_file_actual readLines(merged_file_actual);
		
		assertSame("The expected merged file should be equal to the actual. ", List_merged_file_expected, List_merged_file_actual);

	}
	
	@Test //Test that openTextDocument returns true - success
	public void test_openTextDocument() {
	
	TextView object = new TextView();
	boolean result; 
	result = object.openTextDocument();
	assertTrue("Open Text Document check. ", result)
	
	
	
	}

}
