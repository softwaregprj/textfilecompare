package textfilecompareTest;

import org.junit.Test;
import textfilecompare.OpenFile;
import textfilecompare.TextFileCompareMain;
import java.beans.PropertyVetoException;
import java.io.IOException;     


public class OpenFile_Test {


	// Check that file Browser doesn't return null indicating an error
	@Test
	public void OpenFile_ConstructorTest() throws InterruptedException, IOException, PropertyVetoException{
		
		System.out.println("OpenFile_ConstructorTest");
		TextFileCompareMain testobject = new TextFileCompareMain();
		OpenFile testobject1 = new OpenFile(testobject, true); 
		System.out.println("OpenFile object created");
		System.out.println(testobject1);

	}
	
//	@Test //Test that openTextDocument returns true - success
//	public void test_openTextDocument() throws Exception {
//		System.out.println("test_openTextDocument Begin");
//		TextFileCompareMain parent = new TextFileCompareMain();
//		System.out.println("TextFileCompareMain Object created");
//		OpenFile object = new OpenFile(parent, false);
//		System.out.println("OpenFile object created ");
//		boolean result = object.openTextDocument();
//		System.out.println("Boolean check complete ");
//
//		assertTrue("Open Text Document check. ", result);
//		
//		System.out.println("test_openTextDocument End");
//
//	}		
	
	
		
}
