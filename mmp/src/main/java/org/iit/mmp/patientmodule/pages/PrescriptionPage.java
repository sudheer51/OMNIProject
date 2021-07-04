package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PrescriptionPage {

	WebDriver driver;
	public PrescriptionPage(WebDriver driver)
	{
		this.driver=driver;
		 
	}
	public HashMap<String, String> fetchPrescriptionData(HashMap<String,String> hMap)
	{
		    HashMap<String,String> actualHMap = new HashMap<String,String>();
			actualHMap.put("prec",
				  		driver.findElement(By.xpath("//ul/li[contains(.,'"+hMap.get("presc") + "')]")).getText());
			
			actualHMap.put("dateOfAppointment",
			  		driver.findElement(By.
			  				xpath("//ul/li[contains(text(),'"+hMap.get("presc") + "')]/div/p[contains(text(),'"+hMap.get("dateOfAppointment") + "')]")).getText());
		
			
			driver.findElement(By.xpath("//ul/li[contains(.,'"+hMap.get("presc") + "')]")).click();
			
			//p[contains(normalize-space(),'take every 6 hrs')]
			
			actualHMap.put("prescDesc",
			  		driver.findElement(By.xpath("//p[contains(normalize-space(),'"+hMap.get("presc") + "')]")).getText());
	
			return actualHMap;
	}

}
