package pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
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

public class ProposedProducts {

	String BenReqFormName;
	By btnApprove = By.name("goNext");
	By btnBenefitReqSave = By.name("inlineEditSave");
	By btnProposedProduct = By.name("new_proposed_product1");
	By btnSave = By.name("save");
	By btnSubmit = By.name("submit");
	By drpBlueRewards = By.id("00N6F00000HgmV2");
	By drpCDH = By.id("00N6F00000HgmUi");
	By drpCDHType = By.id("00N6F00000HgmUs");
	By drpCDHFundType = By.id("00N6F00000HgmUx");
	By drpMHP = By.id("00N6F00000HgmVC");
	By drpMHPReq = By.id("00N6F00000HgmWe_ileinner");
	By drpMHPResult = By.id("00N6F00000HgmZd_ileinner");
	By drpMHPReq1 = By.id("00N6F00000HgmWe");
	By drpMHPResult1 = By.id("00N6F00000HgmZd");
	By frame1 = By.id("RLPanelFrame");
	By frame2 = By.id("resultsFrame");
	By lnkProposedProduct = By.xpath("//span[text()='Proposed Products']");
	By lnkApprove = By.linkText("Approve / Reject");
	By lnkApprovalHistory = By.xpath("//span[text()='Approval History']");
	By lblApprovalStatus = By.xpath("//tr[td[text()='Approval Status']]//td[2]");
	By plan = By.xpath("//*[@id='CF00N6F00000HgmSm_lkwgt']/img");
	By prodCat = By.id("00N6F00000HgmUJ");
	By prodCateSel1 = By.xpath("//a[text()='P-0001']");
	By prodCateSel2 = By.xpath("//a[text()='P-0002']");
	By prodCateSel3 = By.xpath("//a[text()='P-0003']");
	By prodCateSel4 = By.xpath("//a[text()='P-0004']");
	By prodLine = By.id("00N6F00000HgmUY");
	By txtApprovalStatus = By.id("00N6F00000HglIb_ileinner");
	By txtBenReqForm = By.id("CF00N6F00000HgmSh");
	By txtComments = By.id("Comments");

	ExcelUtility util = new ExcelUtility();


	/**
	 ********************************************************************** 
	 * Method Name: createProposedProduct
	 * @Description : Clicking on New Proposed Product button and creating new Proposed Product
	 * @Author: Nishika Nigam
	 *********************************************************************** 
	 */

	public String createProposedProduct(WebDriver driver, String productCategory, String productLine, String cdhValue, String blueRewards, String cdhTypeValue, String cdhFundTypeValue, String mhpValue, String testType, ExtentTest child)
	{

		try
		{

			//click on Proposed Products link
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(lnkProposedProduct));
			driver.findElement(lnkProposedProduct).click();


			//click on new Proposed Product button to create new BProposed Product
			wait.until(ExpectedConditions.elementToBeClickable(btnProposedProduct));
			driver.findElement(btnProposedProduct).click();

			wait.until(ExpectedConditions.elementToBeClickable(txtBenReqForm));
			BenReqFormName=driver.findElement(txtBenReqForm).getAttribute("value");


			//Select Product Category
			Select prodCatDD = new Select(driver.findElement(prodCat));
			prodCatDD.selectByVisibleText(productCategory);


			//Select Product Line
			Select prodLineDD = new Select(driver.findElement(prodLine));
			prodLineDD.selectByVisibleText(productLine);


			//Select Plan according to Product_Category

			String parent=driver.getWindowHandle();  //saves the current handle

			wait.until(ExpectedConditions.elementToBeClickable(plan));
			driver.findElement(plan).click();
			Thread.sleep(10000);

			String subWindow=null;
			Set<String>s1=driver.getWindowHandles();  //saves all the open windows

			Iterator<String> I1= s1.iterator();      //iterates over open windows

			while(I1.hasNext())
			{

				subWindow=I1.next();

				if(!parent.equals(subWindow))
				{

					driver.switchTo().window(subWindow);
					System.out.println(driver.switchTo().window(subWindow).getTitle());


					//Switching to frame as the plans are available inside frame
					Thread.sleep(3000);
					driver.switchTo().frame(driver.findElement(frame2)); 


					//Selecting plan according to Product Category
					if(productCategory.equalsIgnoreCase("Dental"))
					{
						driver.findElement(prodCateSel1).click();
					}
					else if (productCategory.equalsIgnoreCase("Drug"))
					{
						driver.findElement(prodCateSel2).click();
					}

					else if (productCategory.equalsIgnoreCase("Medical"))
					{
						driver.findElement(prodCateSel3).click();
					}

					else
						driver.findElement(prodCateSel4).click();
				}

			}

			driver.switchTo().window(parent);   //Returns back to main parent window

			
			//Select Blue Rewards
			Select blueRewardsDrp = new Select(driver.findElement(drpBlueRewards));
			blueRewardsDrp.selectByVisibleText(blueRewards);
			
			//Select MHP Test Result
			Select mhpResult = new Select(driver.findElement(drpMHP));
			mhpResult.selectByVisibleText(mhpValue);
			
			
			if(testType.equalsIgnoreCase("ENHANCE"))
			{
				
				//select CDH
				wait.until(ExpectedConditions.elementToBeClickable(drpCDH));
				Select cdh = new Select(driver.findElement(drpCDH));
				cdh.selectByVisibleText(cdhValue);
				
				//click on Save button
				Thread.sleep(2000);
				driver.findElement(btnSave).click();
				wait.until(ExpectedConditions.elementToBeClickable(btnSave));
				
				//Select CDH Type
				Thread.sleep(2000);
				Select cdhType = new Select(driver.findElement(drpCDHType));
				cdhType.selectByVisibleText(cdhTypeValue);
				
				//click on Save button
				driver.findElement(btnSave).click();
				wait.until(ExpectedConditions.elementToBeClickable(btnSave));
				Thread.sleep(2000);

				//Select CDH Fund Type
				Select cdhFundType = new Select(driver.findElement(drpCDHFundType));
				cdhFundType.selectByVisibleText(cdhFundTypeValue);
				
			}
			else
			{
				
				//Select CDH
				/*wait.until(ExpectedConditions.elementToBeClickable(drpCDH));
				Select cdh = new Select(driver.findElement(drpCDH));
				cdh.selectByVisibleText(cdhValue);

				//Select CDH Type
				Select cdhType = new Select(driver.findElement(drpCDHType));
				cdhType.selectByVisibleText(cdhTypeValue);

				//Select CDH Fund Type
				Select cdhFundType = new Select(driver.findElement(drpCDHFundType));
				cdhFundType.selectByVisibleText(cdhFundTypeValue);*/
				
			}

			//click on Save button
			driver.findElement(btnSave).click();
			wait.until(ExpectedConditions.elementToBeClickable(lnkProposedProduct));

			//Open the Benefit Request Form created before
			System.out.println(BenReqFormName);

			String screenshotPath = util.getScreenshot(driver, "ProposedProd", "Proposed_Prod_Added");
			child.log(LogStatus.PASS, "Proposed Product with Benefit Request Form Name " +BenReqFormName+ " is created successfully");
			child.log(LogStatus.INFO,"To View Screenshot of Created Proposed Product <a href=\"file:///"+screenshotPath+"\">Click Here</a>");


			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='sidebarModuleBody']//span[text()='"+BenReqFormName+"']")));
			driver.findElement(By.xpath("//div[@class='sidebarModuleBody']//span[text()='"+BenReqFormName+"']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(lnkProposedProduct));

			((JavascriptExecutor)driver).executeScript("scroll(0,400)");

			Actions ac= new Actions(driver);
			ac.doubleClick(driver.findElement(drpMHPReq)).build().perform();

			wait.until(ExpectedConditions.elementToBeClickable(drpMHPReq1));

			//Select MHP Request
			Select mhpReq= new Select(driver.findElement(drpMHPReq1));
			mhpReq.selectByVisibleText("Yes");


			ac.doubleClick(driver.findElement(drpMHPResult)).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(drpMHPResult1));

			//Select MHP Request
			Select mhpRes= new Select(driver.findElement(drpMHPResult1));
			mhpRes.selectByVisibleText("Pass");

			//click on Save button
			driver.findElement(btnBenefitReqSave).click();


			//Submit for Approval
			wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
			Thread.sleep(2000);
			driver.findElement(btnSubmit).click();
			Thread.sleep(2000);
			Alert alert=driver.switchTo().alert();
			alert.accept();
			wait.until(ExpectedConditions.elementToBeClickable(lnkProposedProduct));
			
			//Fetch Approval status
			String approvalStatus = driver.findElement(lblApprovalStatus).getText();
			child.log(LogStatus.INFO, "Status of Benefit Request form with ID "+BenReqFormName+" is "+approvalStatus);

		}
		catch(Exception e)
		{

			System.out.println("Proposed Product could not be created due to error "+e.toString());
			e.printStackTrace();
			child.log(LogStatus.FAIL, "Proposed Product could not be created due to error "+e.toString());
			
		}
		
		return BenReqFormName;
		
	}

}
