package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ExcelUtility;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Opportunity {
	
	By btnNewProposalReqForm = By.name("new_proposal_request_form");
	By btnNewOpportunity = By.name("newOpp");
	By btnOppSave = By.name("save");
	By btnSearch = By.id("phSearchButton");
	By chkAccLegalVerify = By.id("00N6F00000Hgtbg");
	By drpLeadSource = By.id("opp6");
	By drpStage = By.id("opp11");
	By drpType = By.id("opp5");
	By lblCloseDate = By.cssSelector("td.labelCol.requiredInput");
	By lblOppName = By.xpath("//h2[@class='pageDescription']");
	By lnkCreatedAcc = By.linkText("Account_Legal_Name_Verification");
	By lnkOpportunities = By.xpath("//span[text()='Opportunities']");
	By lnkProposalReqForm = By.xpath("//span[text()='Proposal Request Forms']");
	By txtCloseDate = By.id("opp9");
	By txtOppName = By.id("opp3");
	By txtSearch = By.id("phSearchInput");

	ExcelUtility util = new ExcelUtility();
	
	/**
	 ********************************************************************** 
	 * Method Name: createOpportunity
	 * @Description : Clicking on new Opportunity button and creating new Opportunity
	 * @Author: Sunil
	 *********************************************************************** 
	 */
	public void createOpportunity(WebDriver driver, String oppName, String oppCloseDate, String oppCase, ExtentTest child)
	{
		
		try
		{
			
			//Enter the opportunity name
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(txtOppName));
			driver.findElement(txtOppName).sendKeys(oppName);
			
			//Enter the opportunity close date
			driver.findElement(txtCloseDate).sendKeys(oppCloseDate);
			driver.findElement(lblCloseDate).click();
			
			//Select opportunity Stage
			Select oppStage = new Select(driver.findElement(drpStage));
			oppStage.selectByVisibleText("Prospecting");
			
			//Select opportunity Type
			Select oppType = new Select(driver.findElement(drpType));
			oppType.selectByVisibleText("New Customer");
			
			//Select opportunity Lead Source
			Select oppLeadSource = new Select(driver.findElement(drpLeadSource));
			oppLeadSource.selectByVisibleText("Web");
			
			if(oppCase.equalsIgnoreCase("POSITIVE"))
			{

				//Check the Account Legal Name verified checkbox
				driver.findElement(chkAccLegalVerify).click();

			}
			
			//click on save button
			driver.findElement(btnOppSave).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'"+oppName+"')]")));
			
			String screenshotPath = util.getScreenshot(driver, "Opportunity", "Opportunity_Created");
			child.log(LogStatus.PASS, "Opportunity with Opportunity Name "+driver.findElement(lblOppName).getText()+" is created successfully");
			child.log(LogStatus.INFO,"To View Screenshot of Created Opportunity <a href=\"file:///"+screenshotPath+"\">Click Here</a>");
			
			//click on Proposal Request form
			driver.findElement(lnkProposalReqForm).click();
			wait.until(ExpectedConditions.elementToBeClickable(btnNewProposalReqForm));
			
			//click on New Proposal Request Form
			driver.findElement(btnNewProposalReqForm).click();
			
		}
		catch(Exception e)
		{
			
			System.out.println("Opportunity could not be created due to error "+e.toString());
			e.printStackTrace();
			child.log(LogStatus.FAIL, "Opportunity could not be created due to error "+e.toString());
			
		}
		
	}
	
	/**
	 ********************************************************************** 
	 * Method Name: createNegativeOpportunity
	 * @throws Exception 
	 * @Description : Searching already created Account and check for negative opportunity creation
	 * @Author: Sunil
	 *********************************************************************** 
	 */
	public void createNegativeOpportunity(WebDriver driver, String oppName, String oppCloseDate, String oppCase, ExtentTest child) throws Exception
	{
		
		try
		{
			
			//Search the Account
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(txtSearch));
			driver.findElement(txtSearch).sendKeys("Account_Legal_Name_Verification");
			driver.findElement(btnSearch).click();
			
			//click on the searched Account
			wait.until(ExpectedConditions.elementToBeClickable(lnkCreatedAcc));
			driver.findElement(lnkCreatedAcc).click();
			
			//click on Opportunities link
			driver.findElement(lnkOpportunities).click();
			wait.until(ExpectedConditions.elementToBeClickable(btnNewOpportunity));

			//click on New opportunity button
			driver.findElement(btnNewOpportunity).click();
			
			//Create Negative case opportunity
			createOpportunity(driver, oppName, oppCloseDate, oppCase, child);
			
		}
		catch(Exception e)
		{
			
			System.out.println("Opportunity could not be created due to error "+e.toString());
			e.printStackTrace();
			String screenshotPath = util.getScreenshot(driver, "Opportunity", "Opportunity_NOT_Created");
			child.log(LogStatus.FAIL, "Opportunity could not be created due to error "+e.toString());
			child.log(LogStatus.INFO, "To View Screenshot <a href=\"file:///"+screenshotPath+"\">Click Here</a>");
			
		}
	}

}
