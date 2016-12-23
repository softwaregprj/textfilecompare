package textfilecompareTest;

import org.junit.Test;
import textfilecompare.CompareFile;
import textfilecompare.SaveFile;
import textfilecompare.TextFileCompareMain;


public class SaveFile_Test {

	// Check that file Browser doesn't return null indicating an error
	@Test
	public void SaveFile_Constructor_Test() throws Exception{
	System.out.println("SaveFile_Constructor_Test");
	TextFileCompareMain testobject=new TextFileCompareMain();	
	CompareFile compareView = new CompareFile(null, null, null, 0);	
	SaveFile testobject1 = new SaveFile(testobject,compareView);
	System.out.println("SaveFile constructed");
	System.out.println(testobject1);

	}
	
	
	
//	@Test //Test that openTextDocument returns true - success
//	public void test_saveTextDocument() throws Exception {
//		System.out.println("test_saveTextDocument Begin");
//		
//		TextFileCompareMain parent = new TextFileCompareMain();
//		System.out.println("TextFileCompareMain constructed");
//
//		CompareFile compareView = new CompareFile(null, null, null, 0); 
//		System.out.println("CompareFile constructed");
//
//		SaveFile object = new SaveFile(parent, compareView);
//		System.out.println("SaveFile constructed");
//
//		boolean result = object.saveTextDocument();
//		System.out.println("saveTextDocument function run");
//
//		assertTrue("Open Text Document check. ", result);
//		
//		System.out.println("test_saveTextDocument End");
//
//	}		
}
