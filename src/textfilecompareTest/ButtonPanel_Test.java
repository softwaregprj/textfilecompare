package textfilecompareTest;

import static org.junit.Assert.*;
import java.beans.PropertyVetoException;
import java.io.IOException;
import org.junit.Test;
import textfilecompare.ButtonPanel;
import textfilecompare.TextFileCompareMain;


public class ButtonPanel_Test {
	// Check that Open Button gets Enabled

	@Test
	public void EnableOpenButtTest(){
	System.out.println("EnableOpenButtonTest");
	TextFileCompareMain testobject=new TextFileCompareMain();
	ButtonPanel panel = new ButtonPanel(testobject); 
	panel.enableButton(1);
	Boolean check = panel.openButt.isEnabled();
	assertTrue("Open Button has not been enabled",check);
	}

	// Check that Compare Button gets Enabled

	@Test
	public void EnableCompareButtTest(){
	System.out.println("EnableCompareButtonTest");
	TextFileCompareMain testobject=new TextFileCompareMain();
	ButtonPanel panel = new ButtonPanel(testobject); 
	panel.enableButton(2);
	Boolean check = panel.compareButt.isEnabled();
	assertTrue("Compare Button has not been enabled",check);
	}

	// Check that Save Button gets Enabled

	@Test
	public void EnableSaveButtTest(){
	System.out.println("EnableSaveButtonTest");
	TextFileCompareMain testobject=new TextFileCompareMain();
	ButtonPanel panel = new ButtonPanel(testobject); 
	panel.enableButton(3);
	Boolean check = panel.saveButt.isEnabled();
	assertTrue("Save Button has not been enabled",check);
	}

	// Check that Open Button gets Disabled

	@Test
	public void DisableOpenButtTest(){
	System.out.println("DisableOpenButtonTest");
	TextFileCompareMain testobject=new TextFileCompareMain();
	ButtonPanel panel = new ButtonPanel(testobject); 
	panel.disableButton(1);
	Boolean check = panel.openButt.isEnabled();
	assertFalse("Open Button has not been disabled",check);
	}

	// Check that Compare Button gets Disabled

	@Test
	public void DisableCompareButtTest(){
	System.out.println("DisableCompareButtonTest");
	TextFileCompareMain testobject=new TextFileCompareMain();
	ButtonPanel panel = new ButtonPanel(testobject); 
	panel.disableButton(2);
	Boolean check = panel.compareButt.isEnabled();
	assertFalse("Compare Button has not been disabled",check);
	}

	// Check that Save Button gets Disabled

	@Test
	public void DisableSaveButtTest(){
	System.out.println("DisableSaveButtonTest");
	TextFileCompareMain testobject=new TextFileCompareMain();
	ButtonPanel panel = new ButtonPanel(testobject); 
	panel.disableButton(3);
	Boolean check = panel.saveButt.isEnabled();
	assertFalse("Save Button has not been disabled",check);
	}

	// Open button should remain enabled after just one window is opened.

	@Test
	public void OpenButtonTestafter1Window() throws PropertyVetoException, InterruptedException, IOException {
	System.out.println("OpenButtonTestafter1Window");
	TextFileCompareMain testobject = new TextFileCompareMain();
	testobject.preOpenFileCheck();
	ButtonPanel panel = new ButtonPanel(testobject);
	Boolean check = panel.openButt.isEnabled();
	assertTrue("The open button should be enabled", check);
	}

//	// Open button should be disabled after two windows are opened.
//
//	@Test
//	public void OpenButtonTestafter2Window() throws PropertyVetoException, InterruptedException, IOException {
//	System.out.println("OpenButtonTestafter2Window");
//	TextFileCompareMain testobject = new TextFileCompareMain();
//	System.out.println("TextFileCompareMain object made");
//	testobject.preOpenFileCheck();
//	System.out.println("1st preOpenFileCheck complete");
//	testobject.preOpenFileCheck();
//	System.out.println("2nd preOpenFileCheck complete");
//	ButtonPanel panel = new ButtonPanel(testobject);
//	System.out.println("ButtonPanel called");
//	Boolean check = panel.openButt.isEnabled();
//	System.out.println("Boolean check in openButt is enabled");
//	assertFalse("The open button should be disabled", check);
//	}

//	// Compare button should remain disabled after just one window is opened.
//
//	@Test
//	public void CompareButtonTestafter1Window() throws PropertyVetoException, InterruptedException, IOException {
//	System.out.println("CompareButtonTestafter1Window");
//	TextFileCompareMain testobject = new TextFileCompareMain();
//	testobject.preOpenFileCheck();
//	ButtonPanel panel = new ButtonPanel(testobject);
//	Boolean check = panel.compareButt.isEnabled();
//	assertFalse("The compare button should be disabled", check);
//	}

	// Compare button should be enabled after two windows are opened.

//	@Test
//	public void CompareButtonTestafter2Windows() throws PropertyVetoException, InterruptedException, IOException {
//	System.out.println("CompareButtonTestafter2Windows");
//	TextFileCompareMain testobject = new TextFileCompareMain();
//	testobject.preOpenFileCheck();
//	testobject.preOpenFileCheck();
//	ButtonPanel panel = new ButtonPanel(testobject);
//	Boolean check = panel.compareButt.isEnabled();
//	assertTrue("The compare button should be enabled", check);
//	}

//	// Save button should remain disabled after just one window is opened.
//
//	@Test
//	public void SaveButtonTestafter1Window() throws PropertyVetoException, InterruptedException, IOException {
//	System.out.println("SaveButtonTestafter1Window");
//	TextFileCompareMain testobject = new TextFileCompareMain();
//	testobject.preOpenFileCheck();
//	ButtonPanel panel = new ButtonPanel(testobject);
//	Boolean check = panel.saveButt.isEnabled();
//	assertFalse("The save button should be disabled", check);
//	}

//	// Save button should remain disabled after two windows are opened.
//
//	@Test
//	public void SaveButtonTestafter2Windows() throws PropertyVetoException, InterruptedException, IOException {
//	System.out.println("SaveButtonTestafter2Windows");
//	TextFileCompareMain testobject = new TextFileCompareMain();
//	testobject.preOpenFileCheck();
//	testobject.preOpenFileCheck();
//	ButtonPanel panel = new ButtonPanel(testobject);
//	Boolean check = panel.saveButt.isEnabled();
//	assertFalse("The save button should be disabled", check);
//	}

}
