package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ExcelUtility;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProposalRequestForm {
	
	By btnBenefitReqForm = By.name("new_benefit_request_form1");
	By btnSave = By.name("save");
	By drpHealthReformStatus = By.id("00N6F00000Hgk8u");
	By drpTypeWork = By.id("00N6F00000Hgk8p");
	By lblErrorText = By.xpath("//div[@id='errorDiv_ep']");
	By lblProposalName = By.xpath("//tr[td[contains(text(),'Proposal Request Form')]]//div");
	By lnkBenefitReqForm = By.xpath("//span[text()='Benefit Request Forms']");
	By lnkHelp = By.xpath("//span[text()='Help for this Page']");
	
	ExcelUtility util = new ExcelUtility();

	/**
	 ********************************************************************** 
	 * Method Name: createProposalReqForm
	 * @Description : Clicking on new Proposal Request form button and creating new Proposal
	 * @Author: Sunil
	 *********************************************************************** 
	 */
	public void createProposalReqForm(WebDriver driver, ExtentTest child)
	{
		
		try
		{
		
			//Select the type of work
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(drpTypeWork));
			Select typeWork = new Select(driver.findElement(drpTypeWork));
			typeWork.selectByVisibleText("Project");
			
			//Select the HealthCare Reform Grandfather Status
			Select healthReform = new Select(driver.findElement(drpHealthReformStatus));
			healthReform.selectByVisibleText("Grandfathered");
			
			//click on Save button
			driver.findElement(btnSave).click();
			wait.until(ExpectedConditions.elementToBeClickable(lnkHelp));
			
			//check if error message is displayed
			boolean flag = driver.findElement(lblErrorText).isDisplayed();
			System.out.println("Flag is "+flag);
			if (flag == true)
			{
				
				String errorText = driver.findElement(lblErrorText).getText();
				String screenshotPath = util.getScreenshot(driver, "Proposal_Form", "Proposal_Request_Form_Error");
				child.log(LogStatus.FAIL, "Proposal Request Form could not be created due to "+errorText);
				child.log(LogStatus.INFO,"To View Screenshot of Proposal Request Form error <a href=\"file:///"+screenshotPath+"\">Click Here</a>");
			}
			else
			{

				wait.until(ExpectedConditions.elementToBeClickable(lblProposalName));

				String screenshotPath = util.getScreenshot(driver, "Proposal_Form", "Proposal_Request_Form_Created");
				child.log(LogStatus.PASS, "Proposal Request Form with Name "+driver.findElement(lblProposalName).getText()+" is created successfully");
				child.log(LogStatus.INFO,"To View Screenshot of Created Proposal Request Form <a href=\"file:///"+screenshotPath+"\">Click Here</a>");

				//Click on Benefit Request Form link
				driver.findElement(lnkBenefitReqForm).click();
				wait.until(ExpectedConditions.elementToBeClickable(btnBenefitReqForm));

				//Click on Benefit Request Form button
				driver.findElement(btnBenefitReqForm).click();
			}
			
		}
		catch(Exception e)
		{
			
			System.out.println("Proposal Request Form could not be created due to error "+e.toString());
			e.printStackTrace();
			child.log(LogStatus.FAIL, "Proposal Request Form could not be created due to error "+e.toString());
			
		}
		
	}

}
