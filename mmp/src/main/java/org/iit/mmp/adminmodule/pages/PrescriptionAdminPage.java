package org.iit.mmp.adminmodule.pages;

import java.util.HashMap;
import java.util.Random;

import org.iit.mmp.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class PrescriptionAdminPage {
	
	WebDriver driver;
	By  prescriptionButton = By.xpath("//input[@value='Add Precription']");
	By appointment=By.id("app_date");
	By prescriptionName = By.id("exampleInputcardnumber1");
	By prescriptionDesc = By.name("p_desc");
	By submitButton = By.xpath("//input[@value='submit']");
	
	public PrescriptionAdminPage(WebDriver driver)
	{
		this.driver=driver;
	}
	public HashMap<String, String> addPrescription()
	{
		    HashMap<String,String> prescHMap = new HashMap<String,String>();
		    
			driver.findElement(prescriptionButton).click();
			Select appt=new Select(driver.findElement(appointment));
			 
			int apptSize = appt.getAllSelectedOptions().size();
			Random rand = new Random();
			int index = rand.nextInt(apptSize);
			appt.selectByIndex(index);
			System.out.println("The Selected Option Value is " + appt.getFirstSelectedOption().getText());
			prescHMap.put("dateOfAppointment",appt.getFirstSelectedOption().getText());
			
			String presc = "Prescription" + Utility.getRandomString(5);
			driver.findElement(prescriptionName).sendKeys(presc);
			prescHMap.put("presc",presc);
			
			String prescDesc = "Description"+ Utility.getRandomString(5);
			driver.findElement(prescriptionDesc).sendKeys(prescDesc);
			prescHMap.put("prescDesc",prescDesc);
			
			driver.findElement(submitButton).click();
			
			return prescHMap;
			
			
			
	}
}


