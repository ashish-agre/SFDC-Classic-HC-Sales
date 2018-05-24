package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ExcelUtility;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BenefitRequestForm {

	By btnApprove = By.name("goNext");
	By btnBenefitRequest = By.name("new_benefit_request_form1");
	By btnOk = By.xpath("//input[@value='OK']");
	By btnSave = By.name("save");
	By btnSearch = By.id("phSearchButton");
	By btnSaveStatus = By.name("inlineEditSave");
	By drpBenReqFormStatus = By.id("00N6F00000Hgk9E");
	By drpBenReqStatus = By.id("00N6F00000Hgk9E");
	By drpCalenderCon = By.id("00N6F00000Hgk8G");
	By drpContractPeriod = By.id("00N6F00000Hgk8B");
	By drpControlPlan = By.id("00N6F00000Hgk8L");
	By drpFundingArrangement = By.id("00N6F00000HgmTQ");
	By drpGrandfStatus = By.id("00N6F00000Hgk8V");
	By drpOppo = By.id("opp11");
	By drpReqType = By.id("00N6F00000Hgk7w");
	By drpSalesType = By.id("00N6F00000Hgk81");
	By frame = By.id("RLPanelFrame");
	By lnkApprove = By.linkText("Approve / Reject");
	By lblBenefitEffDate = By.cssSelector("td.labelCol.requiredInput");
	By lblBenReqStatus = By.id("00N6F00000Hgk9E_ileinner");
	By lblErrorText = By.xpath("//div[@id='errorDiv_ep']");
	By lblStage = By.id("opp11_ileinner");
	By lnkBenefitRequest = By.linkText("Benefit Request Forms");
	By lnkHelp = By.xpath("//span[text()='Help for this Page']");
	By lnkNotesAttachment = By.xpath("//span[text()='Notes & Attachments']");
	By lnkOpportunity = By.xpath("//div[@id='CF00N6F00000Hgk7r_ileinner']//a");

	By lnkProposedProduct = By.xpath("//span[text()='Proposed Products']");
	By txtApprovalStatus = By.id("00N6F00000HglIb_ileinner");
	By txtBenefitEffDate = By.id("00N6F00000Hgk86");
	By txtComments = By.id("Comments");
	By txtContractCount = By.id("00N6F00000Hgk8f");
	By txtSearch = By.id("phSearchInput");

	ExcelUtility util = new ExcelUtility();

	/**
	 ********************************************************************** 
	 * Method Name: createBenefit
	 * @Description : Clicking on Benefit Request button and creating new Benefit Request
	 * @Author: Nishika Nigam
	 *********************************************************************** 
	 */
	public void createBenefit(WebDriver driver, String requestType, String salesSegmentType, String benefitEffectDate, String contractPeriod, String calenderContract, String controlPlan, String grandfatheredStatus, String fundArrange, String benefitReqStatus, ExtentTest child)
	{

		try
		{

			//Select Request type
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(drpReqType));
			Select reqTypeDD = new Select(driver.findElement(drpReqType));
			reqTypeDD.selectByVisibleText(requestType);


			//Select Sales Segment type
			Select salesTypeDD = new Select(driver.findElement(drpSalesType));
			salesTypeDD.selectByVisibleText(salesSegmentType);


			//Select Benefit Effective Date
			driver.findElement(txtBenefitEffDate).sendKeys(benefitEffectDate);
			driver.findElement(lblBenefitEffDate).click();

			//Select Control Plan		
			Select conPlan = new Select(driver.findElement(drpControlPlan));
			conPlan.selectByVisibleText(controlPlan);

			//Select Grandfathered Status	
			Select grandStatus = new Select(driver.findElement(drpGrandfStatus));
			grandStatus.selectByVisibleText(grandfatheredStatus);

			//Select Contract Period			
			Select conPeriod = new Select(driver.findElement(drpContractPeriod));
			conPeriod.selectByVisibleText(contractPeriod);


			//Select Calender Contract		
			Select calContract = new Select(driver.findElement(drpCalenderCon));
			calContract.selectByVisibleText(calenderContract);

			//Select Benefit Request Form Status
			Select benRequestStatus = new Select(driver.findElement(drpBenReqFormStatus));
			benRequestStatus.selectByVisibleText(benefitReqStatus);


			//Select Funding Arrangement	
			Select fundingArrange = new Select(driver.findElement(drpFundingArrangement));
			fundingArrange.selectByVisibleText(fundArrange);

			//Enter contract count 			
			driver.findElement(txtContractCount).sendKeys("10");


			//click on Save button
			driver.findElement(btnSave).click();
			wait.until(ExpectedConditions.elementToBeClickable(lnkHelp));

			//check if error message is displayed
			boolean flag = driver.findElement(lblErrorText).isDisplayed();
			System.out.println("Flag is "+flag);
			if (flag == true)
			{

				String errorText = driver.findElement(lblErrorText).getText();
				child.log(LogStatus.FAIL, "Benefit Request Form could not be created due to "+errorText);

			}
			else
			{

				wait.until(ExpectedConditions.elementToBeClickable(lnkProposedProduct));
				String screenshotPath = util.getScreenshot(driver, "BRF", "BRF_Created");
				child.log(LogStatus.PASS, "Benefit Request Form with Name "+driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText()+" is created successfully");
				child.log(LogStatus.INFO,"To View Screenshot of Created Benefit Request Form <a href=\"file:///"+screenshotPath+"\">Click Here</a>");

			}

		}
		catch(Exception e)
		{

			System.out.println("Benefit Request Form could not be created due to error "+e.toString());
			e.printStackTrace();
			child.log(LogStatus.FAIL, "Benefit Request Form could not be created due to error "+e.toString());

		}

	}

	/**
	 ********************************************************************** 
	 * Method Name: approveBenefit
	 * @Description : Approve the Benefit Request form and adding the quotes
	 * @Author: Sunil Chellwani
	 *********************************************************************** 
	 */
	public void approveBenefit(WebDriver driver, String idBRF, ExtentTest child)
	{

		By lnkBRFid = By.linkText(idBRF);
		try
		{

			//Search the BRF id
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(lnkBenefitRequest));
			driver.findElement(lnkBenefitRequest).click();
			wait.until(ExpectedConditions.elementToBeClickable(txtSearch));
			driver.findElement(txtSearch).sendKeys(idBRF);
			driver.findElement(btnSearch).click();

			//click on the searched BRF id
			wait.until(ExpectedConditions.elementToBeClickable(lnkBRFid));
			driver.findElement(lnkBRFid).click();

			//Approve the Benefit Request Form
			wait.until(ExpectedConditions.elementToBeClickable(lnkApprove));
			driver.findElement(lnkApprove).click();


			wait.until(ExpectedConditions.elementToBeClickable(txtComments));
			driver.findElement(txtComments).sendKeys("Approved");


			wait.until(ExpectedConditions.elementToBeClickable(btnApprove));
			driver.findElement(btnApprove).click();
			wait.until(ExpectedConditions.elementToBeClickable(lnkProposedProduct));

			((JavascriptExecutor)driver).executeScript("scroll(0,500)");
			String approvalStatus= driver.findElement(txtApprovalStatus).getText();

			System.out.println(approvalStatus);

			String screenshotPath = util.getScreenshot(driver, "BRF", "BRF_Approval_Status");
			child.log(LogStatus.PASS, "Status of Benefit Request form with ID "+idBRF+" is "+approvalStatus);	
			child.log(LogStatus.INFO,"To View Screenshot of Benefit Request Form Status <a href=\"file:///"+screenshotPath+"\">Click Here</a>");

		}
		catch(Exception e)
		{

			System.out.println("Benefit Request Form could not be approved due to error "+e.toString());
			e.printStackTrace();
			child.log(LogStatus.FAIL, "Benefit Request Form could not be approved due to error "+e.toString());

		}
	}

	/**
	 ********************************************************************** 
	 * Method Name: updateBRFandOppoAsSold
	 * @Description : Update the Benefit Request form and Opportunity as Sold
	 * @Author: Sunil Chellwani
	 *********************************************************************** 
	 */
	public void updateBRFandOppoAsSold(WebDriver driver, String idBRF, String statusBRF, String statusOppo,ExtentTest child)
	{

		By lnkBRFid = By.linkText(idBRF);
		try
		{

			//Search the BRF id
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(lnkBenefitRequest));
			driver.findElement(lnkBenefitRequest).click();
			wait.until(ExpectedConditions.elementToBeClickable(txtSearch));
			driver.findElement(txtSearch).sendKeys(idBRF);
			driver.findElement(btnSearch).click();

			//click on the searched BRF id
			wait.until(ExpectedConditions.elementToBeClickable(lnkBRFid));
			driver.findElement(lnkBRFid).click();

			//update the status of BEnefit Request Form
			wait.until(ExpectedConditions.elementToBeClickable(lnkProposedProduct));
			Actions act= new Actions(driver);
			act.doubleClick(driver.findElement(lblBenReqStatus)).build().perform();

			//Select Benefit Request Form Status
			wait.until(ExpectedConditions.elementToBeClickable(drpBenReqStatus));
			Select status = new Select(driver.findElement(drpBenReqStatus));
			status.selectByVisibleText(statusBRF);


			//click on Save button
			wait.until(ExpectedConditions.elementToBeClickable(btnSaveStatus));
			driver.findElement(btnSaveStatus).click();
			wait.until(ExpectedConditions.elementToBeClickable(lnkProposedProduct));

			String screenshotPath = util.getScreenshot(driver, "BRF", "BRF_Updated_Sold");
			child.log(LogStatus.PASS, "Benefit Request Form Status is Updated as "+driver.findElement(lblBenReqStatus).getText());
			child.log(LogStatus.INFO,"To View Screenshot of Benefit Request Form Updated as Sold <a href=\"file:///"+screenshotPath+"\">Click Here</a>");

			//Click opportunity to change the status

			wait.until(ExpectedConditions.elementToBeClickable(lnkOpportunity));
			Thread.sleep(1000);
			driver.findElement(lnkOpportunity).click();
			Thread.sleep(1000);

			//DoubleClick Stage text
			wait.until(ExpectedConditions.elementToBeClickable(lnkNotesAttachment));
			act.doubleClick(driver.findElement(lblStage)).build().perform();

			//Select Opportunity Stage
			wait.until(ExpectedConditions.elementToBeClickable(drpOppo));
			Select stage = new Select(driver.findElement(drpOppo));
			stage.selectByVisibleText(statusOppo);

			//Click Ok button
			wait.until(ExpectedConditions.elementToBeClickable(btnOk));
			driver.findElement(btnOk).click();

			//click on Save button
			wait.until(ExpectedConditions.elementToBeClickable(btnSaveStatus));
			driver.findElement(btnSaveStatus).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(lnkNotesAttachment));

			screenshotPath = util.getScreenshot(driver, "Opportunity", "Opportunity_Updated_Sold");
			child.log(LogStatus.PASS, "Opportunity Stage Updated as "+driver.findElement(lblStage).getText());
			child.log(LogStatus.INFO,"To View Screenshot of Opportunity Stage Updated as Sold <a href=\"file:///"+screenshotPath+"\">Click Here</a>");
		}
		catch(Exception e)
		{
			System.out.println("Status of Benefit Request Form and Opportunity could not be approved due to error "+e.toString());
			e.printStackTrace();
			child.log(LogStatus.FAIL, "Status of Benefit Request Form and Opportunity could not be approved due to error "+e.toString());

		}
	}

}
