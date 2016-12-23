package textfilecompareTest;

import static org.junit.Assert.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.JTextPane;
import textfilecompare.CompareFile;
import org.junit.Test;


public class CompareFile_Test {
	

	@Test // Two different files inputted.
	public void test_compareTextDocuments_diff() throws Exception {
		System.out.println("test_compareTextDocuments_diff Begin");
		
		String file111 = "Input_File2.txt";
		String file222 = "Input_File1.txt";
		
		File file1 = new File(file222);
		File file2 = new File(file111);
		
		CompareFile object = new CompareFile(null, null, null, 0); 
		JTextPane merged_file_actual = object.compareTextDocuments(file1, file2);
		String s = merged_file_actual.getText();
		ArrayList<String>List_merged_file_actual = new ArrayList<>(Arrays.asList(s));
		String listString = String.join("", List_merged_file_actual);
		
		String merged_file_expected = new String(Files.readAllBytes(Paths.get("Merged_file.txt")), StandardCharsets.UTF_8);
		String List_merged_file_expected = "";
		List_merged_file_expected = merged_file_expected.toString();
		
		boolean check = Objects.equals(List_merged_file_expected, listString);
		
		assertTrue("Check if strings are equal", check);
		
		System.out.println("test_compareTextDocuments_diff End");
	}
	

	
	@Test //Two identical files inputed.
	public void test_compareTextDocuments_same() throws Exception {
		System.out.println("test_compareTextDocuments_same begin");
		
		String file222 = "Input_File1.txt";
		File file1 = new File(file222);
		
		CompareFile object = new CompareFile(null, null, null, 0); 
		JTextPane merged_file_actual = object.compareTextDocuments(file1, file1);
		String s = merged_file_actual.getText();
		ArrayList<String>List_merged_file_actual = new ArrayList<>(Arrays.asList(s));
		String listString = String.join("", List_merged_file_actual);		
		
		String merged_file_expected = new String(Files.readAllBytes(Paths.get("Merged_file1.txt")), StandardCharsets.UTF_8);
		String List_merged_file_expected = "";
		List_merged_file_expected = merged_file_expected.toString();
		
		boolean check = Objects.equals(List_merged_file_expected, listString);

		assertTrue("Check if strings are equal", check);

		System.out.println("test_compareTextDocuments_same end");
	}

}
