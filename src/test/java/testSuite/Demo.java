package testSuite;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import pages.Account;
import pages.BenefitRequestForm;
import pages.Document;
import pages.Login;
import pages.Opportunity;
import pages.ProposalRequestForm;
import pages.ProposedProducts;
import utility.ExtentReporterNG;
import utility.ExtManager;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.io.IOException;

public class Demo {
	
	WebDriver driver;
	private ExtentReports extent;
	Account account = new Account();
	BenefitRequestForm benefirReq = new BenefitRequestForm();
	Document document = new Document();
	Login login = new Login();
	Opportunity opportunity = new Opportunity();
	ProposalRequestForm proposalReqForm = new ProposalRequestForm();
	ProposedProducts proposedProd = new ProposedProducts();

	@BeforeClass
	public void setUP()
	{
		extent = ExtentReporterNG.getReporter(System.getProperty("user.dir") +"\\Report\\ExecutionReport.html");
	}
	
	@Test(dataProvider="data")
	public void accountImplementation (String browser, String url, String username, String password, String accountName, String accountPhone,
			 			   String accountType, String accountStreet, String accountCity, String accountState, String accountPostCode,
			 			   String accountEffDate, String accRenewMonth,
			 			  String oppName, String oppCloseDate ,String benefitEffDate,
			 			  String adminUsername, String adminPassword, String finalBRFStatus, String finalOppoStatus)
	{
		
		ExtentTest test = ExtManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(),
				" " + " AccountImplementation ");
		try
		{
			
			String idBRF;
			ExtentTest child = ExtManager.startTest(
					Thread.currentThread().getStackTrace()[1].getMethodName(), " ");
			
			driver = login.getBrowser(driver, browser);
			login.enterLoginDetails(driver, url, username, password);
			account.createAccount(driver, accountName, accountPhone, accountType, accountStreet, accountCity, accountState, accountPostCode, accountEffDate, accRenewMonth, "YES", child);
			opportunity.createOpportunity(driver, oppName, oppCloseDate,"POSITIVE", child);
			proposalReqForm.createProposalReqForm(driver, child);
			benefirReq.createBenefit(driver, "Prospect/New", "Commercial 200-999", benefitEffDate, "Standard - 12 Months", "Calendar", "690", "Grandfathered", "ASO", "New", child);
			idBRF=proposedProd.createProposedProduct(driver, "Medical", "PPO", "Yes", "Standard", "HRA Integrated", "BlueFund", "Pass", "Normal", child);
			idBRF = idBRF.trim();
			login.enterLoginDetails(driver, url, adminUsername, adminPassword);
			benefirReq.approveBenefit(driver, idBRF, child);
			document.createDocument(driver, "Unsigned Quote","UnSigned Quote Rate.txt", child);
			document.createNewDoc(driver, "Signed Quote", "Signed Quote Rate.txt", child);
			login.enterLoginDetails(driver, url, username, password);
			benefirReq.updateBRFandOppoAsSold(driver, idBRF, finalBRFStatus, finalOppoStatus, child);
			
			login.logOut(driver);
			test.appendChild(child);

			ExtManager.endTest();
			extent.endTest(test);
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
		
	}
	@DataProvider(name="data")
	public Object[][] getDataFromDataProviderFSO() throws Exception
	{
		Object[][] testObjArray = utility.ExcelUtility.getTableArray(System.getProperty("user.dir")+"//src//test////java//testData//FlowData.xlsx","Data");
		return (testObjArray);
	}
	
	
	@Test(dataProvider="data")
	public void createAccount(String browser, String url, String username, String password, String accountName, String accountPhone,
			   String accountType, String accountStreet, String accountCity, String accountState, String accountPostCode,
			   String accountEffDate, String accRenewMonth,
			  String oppName, String oppCloseDate ,String benefitEffDate,
			  String adminUsername, String adminPassword, String finalBRFStatus, String finalOppoStatus)
	{
		
		ExtentTest test = ExtManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(),
				" " + " AccountCreation ");
		try
		{
			
			ExtentTest child = ExtManager.startTest(
					Thread.currentThread().getStackTrace()[1].getMethodName(), " ");
			
			driver = login.getBrowser(driver, browser);
			login.enterLoginDetails(driver, url, username, password);
			account.createAccount(driver, accountName, accountPhone, accountType, accountStreet, accountCity, accountState, accountPostCode, accountEffDate, accRenewMonth, "NO", child);
			login.logOut(driver);
			test.appendChild(child);

			ExtManager.endTest();
			extent.endTest(test);
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
				
	}
	
	@Test(dataProvider="data")
	public void createOpportunity(String browser, String url, String username, String password, String accountName, String accountPhone,
			   String accountType, String accountStreet, String accountCity, String accountState, String accountPostCode,
			   String accountEffDate, String accRenewMonth,
			  String oppName, String oppCloseDate ,String benefitEffDate,
			  String adminUsername, String adminPassword, String finalBRFStatus, String finalOppoStatus)
	{
		
		ExtentTest test = ExtManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(),
				" " + " OpportunityCreation ");
		try
		{
			
			ExtentTest child = ExtManager.startTest(
					Thread.currentThread().getStackTrace()[1].getMethodName(), " ");
			
			driver = login.getBrowser(driver, browser);
			login.enterLoginDetails(driver, url, username, password);
			account.createAccount(driver, accountName, accountPhone, accountType, accountStreet, accountCity, accountState, accountPostCode, accountEffDate, accRenewMonth, "YES", child);
			opportunity.createOpportunity(driver, oppName, oppCloseDate,"POSITIVE", child);
			login.logOut(driver);
			test.appendChild(child);

			ExtManager.endTest();
			extent.endTest(test);
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
				
	}
	
	@Test(dataProvider="data")
	public void createOpportunityNegative(String browser, String url, String username, String password, String accountName, String accountPhone,
			   String accountType, String accountStreet, String accountCity, String accountState, String accountPostCode,
			   String accountEffDate, String accRenewMonth,
			  String oppName, String oppCloseDate ,String benefitEffDate,
			  String adminUsername, String adminPassword, String finalBRFStatus, String finalOppoStatus)
	{
		
		ExtentTest test = ExtManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(),
				" " + " OpportunityCreationNegative ");
		try
		{
			
			ExtentTest child = ExtManager.startTest(
					Thread.currentThread().getStackTrace()[1].getMethodName(), " ");
			
			driver = login.getBrowser(driver, browser);
			login.enterLoginDetails(driver, url, username, password);
			opportunity.createNegativeOpportunity(driver, oppName, oppCloseDate,"NEGATIVE", child);
			proposalReqForm.createProposalReqForm(driver, child);
			login.logOut(driver);
			test.appendChild(child);

			ExtManager.endTest();
			extent.endTest(test);
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
				
	}
	
	
	@Test(dataProvider="data")
	public void createBRF(String browser, String url, String username, String password, String accountName, String accountPhone,
			   String accountType, String accountStreet, String accountCity, String accountState, String accountPostCode,
			   String accountEffDate, String accRenewMonth,
			  String oppName, String oppCloseDate ,String benefitEffDate,
			  String adminUsername, String adminPassword, String finalBRFStatus, String finalOppoStatus)
	{
		
		ExtentTest test = ExtManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(),
				" " + " BRFCreation ");
		try
		{
			
			ExtentTest child = ExtManager.startTest(
					Thread.currentThread().getStackTrace()[1].getMethodName(), " ");
			
			driver = login.getBrowser(driver, browser);
			login.enterLoginDetails(driver, url, username, password);
			account.createAccount(driver, accountName, accountPhone, accountType, accountStreet, accountCity, accountState, accountPostCode, accountEffDate, accRenewMonth, "YES", child);
			opportunity.createOpportunity(driver, oppName, oppCloseDate,"POSITIVE", child);
			proposalReqForm.createProposalReqForm(driver, child);
			benefirReq.createBenefit(driver, "Prospect/New", "Commercial 200-999", benefitEffDate, "Standard - 12 Months", "Calendar", "690", "Grandfathered", "ASO", "New", child);
			login.logOut(driver);
			test.appendChild(child);

			ExtManager.endTest();
			extent.endTest(test);
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
				
	}
	
	@Test(dataProvider="data")
	public void createBRFAddProposedProd(String browser, String url, String username, String password, String accountName, String accountPhone,
			   String accountType, String accountStreet, String accountCity, String accountState, String accountPostCode,
			   String accountEffDate, String accRenewMonth,
			  String oppName, String oppCloseDate ,String benefitEffDate,
			  String adminUsername, String adminPassword, String finalBRFStatus, String finalOppoStatus)
	{
		
		ExtentTest test = ExtManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(),
				" " + " BRFCreationAddProposedProd ");
		try
		{
			
			ExtentTest child = ExtManager.startTest(
					Thread.currentThread().getStackTrace()[1].getMethodName(), " ");
			
			driver = login.getBrowser(driver, browser);
			login.enterLoginDetails(driver, url, username, password);
			account.createAccount(driver, accountName, accountPhone, accountType, accountStreet, accountCity, accountState, accountPostCode, accountEffDate, accRenewMonth, "YES", child);
			opportunity.createOpportunity(driver, oppName, oppCloseDate,"POSITIVE", child);
			proposalReqForm.createProposalReqForm(driver, child);
			benefirReq.createBenefit(driver, "Prospect/New", "Commercial 200-999", benefitEffDate, "Standard - 12 Months", "Calendar", "690", "Grandfathered", "ASO", "New", child);
			String idBRF=proposedProd.createProposedProduct(driver, "Medical", "PPO", "Yes", "Standard", "HRA Integrated", "BlueFund", "Pass", "Normal", child);
			idBRF = idBRF.trim();
			login.logOut(driver);
			test.appendChild(child);

			ExtManager.endTest();
			extent.endTest(test);
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
				
	}
	
	
	@Test(dataProvider="data")
	public void createProposedProdEnhance(String browser, String url, String username, String password, String accountName, String accountPhone,
			   String accountType, String accountStreet, String accountCity, String accountState, String accountPostCode,
			   String accountEffDate, String accRenewMonth,
			  String oppName, String oppCloseDate ,String benefitEffDate,
			  String adminUsername, String adminPassword, String finalBRFStatus, String finalOppoStatus)
	{
		
		ExtentTest test = ExtManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(),
				" " + " createProposedProdEnhance ");
		try
		{
			
			ExtentTest child = ExtManager.startTest(
					Thread.currentThread().getStackTrace()[1].getMethodName(), " ");
			
			driver = login.getBrowser(driver, browser);
			login.enterLoginDetails(driver, url, username, password);
			account.createAccount(driver, accountName, accountPhone, accountType, accountStreet, accountCity, accountState, accountPostCode, accountEffDate, accRenewMonth, "YES", child);
			opportunity.createOpportunity(driver, oppName, oppCloseDate,"POSITIVE", child);
			proposalReqForm.createProposalReqForm(driver, child);
			benefirReq.createBenefit(driver, "Prospect/New", "Commercial 200-999", benefitEffDate, "Standard - 12 Months", "Calendar", "690", "Grandfathered", "ASO", "New", child);
			String idBRF=proposedProd.createProposedProduct(driver, "Medical", "PPO", "Yes", "Standard", "HRA Integrated", "BlueFund", "Pass", "ENHANCE", child);
			idBRF = idBRF.trim();
			login.logOut(driver);
			test.appendChild(child);

			ExtManager.endTest();
			extent.endTest(test);
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
				
	}
	
	@AfterClass
	public void tearnDown() {
		try {
			extent.flush();
			//driver.close();
			driver.quit();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	@AfterSuite
	public void after() throws IOException {
		Process p = Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		System.out.println(p);
	}

}
