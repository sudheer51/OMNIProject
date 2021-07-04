package org.iit.mmp.adminmodule.tests;

import java.util.HashMap;

import org.iit.mmp.adminmodule.pages.CreateReportAdminPage;
import org.iit.mmp.adminmodule.pages.PrescriptionAdminPage;
import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.PrescriptionPage;
import org.iit.mmp.patientmodule.pages.ViewHistoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAndViewPrescriptionTests extends TestBase {

	String adminUser;
	String adminPassword;
	String urlAdmin;
	String searchPatient;
	String searchPatientSSN;
	String patientUser;
	String patientPassword;
	@Test(description="US_005 Create and View the Prescription",groups={"US_005","regression","sanity","adminmodule"})
	public void validatePrescription() throws Exception   {
		
		instantiateDriver();
		adminUser = pro.getProperty("adminUser");
		adminPassword = pro.getProperty("adminPassword");
		patientUser=			pro.getProperty("patientUser");
		patientPassword =				pro.getProperty("patientPassword");		
		urlAdmin = pro.getProperty("urlAdmin");
		searchPatient = pro.getProperty("searchPatient");
		searchPatientSSN = pro.getProperty("searchPatientSSN");
		
		
		HelperClass helperObj = new HelperClass(driver);
	
		
		helperObj.adminLogin(adminUser, adminPassword);
		helperObj.moduleNavigation("Patients");
		CreateReportAdminPage createReportObj = new CreateReportAdminPage(driver);
		createReportObj.patientSearchBySSN(searchPatientSSN);
		PrescriptionAdminPage prescriptionPage  =new PrescriptionAdminPage(driver);
		HashMap<String,String> expectedhMap = prescriptionPage.addPrescription();
     	helperObj.moduleNavigation("Logout");
	    
     	
     	helperObj.login(patientUser, patientPassword);
		helperObj.moduleNavigation("Profile");
		ViewHistoryPage vhPage = new ViewHistoryPage(driver);
		vhPage.clickOnViewHistoryButton();
		vhPage.clickPrescriptionButton();
		PrescriptionPage prescPage = new PrescriptionPage(driver);
		HashMap<String,String> actualHMap=prescPage.fetchPrescriptionData(expectedhMap);
	   	helperObj.moduleNavigation("Logout");
		Assert.assertEquals(actualHMap, expectedhMap);

	 
		
		 
		
		
		
		
		
		 
	}
}
