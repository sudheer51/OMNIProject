package org.iit.mmp.adminmodule.pages;

import java.util.HashMap;
import java.util.List;

import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateReportAdminPage {

	WebDriver driver;
	HelperClass helperObj;
	By searchNameAndSSN = By.name("search");
	By search = By.xpath("//input[@type='button']");
	By patientName = By.xpath("//div[@id='show']//table//tbody//tr//td[1]/a");
	
	public CreateReportAdminPage(WebDriver driver) {
		this.driver = driver ;
	}
	public void patientSearchBySSN(String ssn) throws InterruptedException
	{
		driver.findElement(searchNameAndSSN).sendKeys(ssn);
		driver.findElement(search).click();
	    HelperClass helperObj = new HelperClass(driver);
	    WebElement e =helperObj.visibilityofElementLocated(patientName, 30);
		e.click();
	}

	public void searchRecord(String searchName, String searchSSN) {

		
		WebDriverWait  wait = new WebDriverWait(driver, 20);
		By resultRecord = By.xpath("//div[@id='show']//table//tbody//tr");	
		wait.until(ExpectedConditions.visibilityOfElementLocated(resultRecord));
		List<WebElement> resultName = driver.findElements(resultRecord);
		for(int i=0; i<= resultName.size(); i++) {
			WebElement cellSSN = resultName.get(i).findElement(By.xpath("((//div[@id='show']//table//tbody//tr)["+(i+1)+"]/td)[2]"));
			WebElement cellName = resultName.get(i).findElement(By.xpath("((//div[@id='show']//table//tbody//tr)["+(i+1) +"]/td)[1]/a"));
			String ssnValue = cellSSN.getText();
			String nameValue = cellName.getText();
			if((nameValue.equals(searchName)) && (ssnValue.equals(searchSSN)))
			{
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cellName.click();
				break;
			}

		}
	}

	public void patientDetails(String ssn)  {
		
		By createReport = By.xpath("//input[@value='Reports']");
		WebElement ssnValue = driver.findElement(By.xpath("((//tr)[3]//td)[2]"));
		String ssnVal = ssnValue.getText();
		if(ssn.equals(ssnVal)) {
		 System.out.println("Both the SSN values matched.");
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(createReport).click();

	}


	public boolean reportDetails(String reptName, String reptDesc, String fileName) {
		
	 	String selectAppointments = "//select[@name='app_date']";
		WebElement we2 = driver.findElement(By.xpath(selectAppointments));
		Select selectAppt = new Select(we2);
		selectAppt.selectByIndex(3);
		String reportName = "//input[@id='exampleInputcardnumber1']";	
		driver.findElement(By.xpath(reportName)).sendKeys(reptName);
		WebElement we1 = driver.findElement(By.xpath("//input[@id='file']"));
		we1.sendKeys(fileName);
		String reportDesc = "//label[text()='Report Description:']/following-sibling::textarea[@name='report_desc']";
		driver.findElement(By.xpath(reportDesc)).sendKeys(reptDesc);
		String submit = "//input[@type='submit']";
		driver.findElement(By.xpath(submit)).click();
		return true;
		
	}
	
	public HashMap<String, String> reportDetail(String reptName, String reptDesc, String fileName) throws InterruptedException {

		HashMap<String, String> hMap = new HashMap<String, String>();
		
		String selectAppointments = "//select[@name='app_date']";
		WebElement we2 = driver.findElement(By.xpath(selectAppointments));
		Select selectAppt = new Select(we2);
		selectAppt.selectByIndex(3);
		

		String reportName = "//input[@id='exampleInputcardnumber1']";	
		driver.findElement(By.xpath(reportName)).sendKeys(reptName);
		hMap.put("reportName", reptName);
		
		WebElement we1 = driver.findElement(By.xpath("//input[@id='file']"));
		we1.sendKeys(fileName);
		hMap.put("fileName", fileName);

		String reportDesc = "//label[text()='Report Description:']/following-sibling::textarea[@name='report_desc']";
		driver.findElement(By.xpath(reportDesc)).sendKeys(reptDesc);
		hMap.put("reportDesc", reptDesc);

		String submit = "//input[@type='submit']";
		driver.findElement(By.xpath(submit)).click();

		return hMap;

	}
}
