package textfilecompare;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameEvent;

import org.junit.Test;

// LIST OF ALL METHODS IN TextFileCompareMain.java

	// GUI Creation Methods
// public static void main(String[] args)
// private static void createAndShowApplication()
// public TextFileCompareMain()
// public void createPanel(JPanel inpanel)
// public void populatePanel(JPanel inpanel)
// public void createButton(JButton butt,JPanel panel)

	// Button Methods
// private void preOpenFileCheck() throws PropertyVetoException 
// public void openFile(TextView intframe)
// protected void compareFiles() throws Exception
// private void createCompareFrame(File file1, File file2) throws Exception, PropertyVetoException 
// protected void saveFile()
// protected void exitApplicationUserAction()

	// Listen Methods
// public void actionPerformed(ActionEvent e)
// public void internalFrameClosed(InternalFrameEvent arg0)

@SuppressWarnings("unused")


public class TextFileCompareMainTest {

	static String[] args = {"one", "two"}, args1 = {"one", "two"};
	static Component[] frames;
	static int len;
	
	// Check to make sure only 1 frame is open at the beginning (i.e. the panel containing buttons).
	@Test
	public void preOpenFileCheckTest() throws PropertyVetoException {
		System.out.println("preOpenFileCheckTest");
		TextFileCompareMain testobject = new TextFileCompareMain();
		TextFileCompareMain.main(args);
		frames = testobject.desktop.getComponents();
		len = frames.length;
		assertEquals("There should now be 1 frame", 1, len);

	}
	
	// Make sure that opening a new file works correctly and that it does register as being opened. This is done by 
	// counting the frames, there should be 1 more than the previous case if the file opens correctly.
	@Test
	public void preOpenFileCheck1Test() throws PropertyVetoException, InterruptedException {
		System.out.println("preOpenFile1CheckTest");
		TextFileCompareMain testobject = new TextFileCompareMain();
		testobject.preOpenFileCheck();
		frames = testobject.desktop.getComponents();
		len = frames.length;
		assertEquals("There should be 2 frames opened", 2 , len);
	}
	
	// Open button should remain enabled after just one window is opened.
	@Test
	public void OpenButtonTest1() throws PropertyVetoException, InterruptedException {
		System.out.println("OpenButtonTest");
		TextFileCompareMain testobject = new TextFileCompareMain();
		testobject.preOpenFileCheck();
		Boolean check = testobject.openButt.isEnabled();
		assertTrue("The button should be enabled", check);
	}	
	
	// Test that program can open two documents for side-by-side comparison. If so, there will be yet one more frame
	// as compared to the previous case.
	@Test
	public void preOpenFileCheck2Test() throws PropertyVetoException, InterruptedException {
		System.out.println("preOpenFile2CheckTest");
		TextFileCompareMain testobject = new TextFileCompareMain();
		testobject.preOpenFileCheck();
		testobject.preOpenFileCheck();
		frames = testobject.desktop.getComponents();
		len = frames.length;
		assertEquals("There should be 3 frames opened", 3, len);
	}
	
	// After two documents have been opened, the button should now be disabled.
	@Test
	public void OpenButtonTest2() throws PropertyVetoException, InterruptedException {
		System.out.println("OpenButtonTest");
		TextFileCompareMain testobject = new TextFileCompareMain();
		testobject.preOpenFileCheck();
		testobject.preOpenFileCheck();
		Boolean check = testobject.openButt.isEnabled();
		assertFalse("The button should be disabled", check);
	}
	
	
}
