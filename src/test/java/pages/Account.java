package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ExcelUtility;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Account {
	
	By btnNew = By.name("new");
	By btnNewOpportunity = By.name("newOpp");
	By btnSave = By.name("save");
	By drpRenewMonth = By.id("00N6F00000Hgubm");
	By drpType = By.id("acc6");
	By lblAccName = By.xpath("//h2[@class='topName']");
	By lblAccEffectDate = By.xpath("//td[label[text()='Account Effective Date']]");
	By lnkCopyaddress = By.linkText("Copy Billing Address to Shipping Address");
	By lnkOpportunities = By.xpath("//span[text()='Opportunities']");
	By tabAccounts = By.xpath("//a[text()='Accounts']");
	By txtAccDescription = By.id("acc20");
	By txtAccountName = By.id("acc2");
	By txtAccLegalName = By.id("00N6F00000HgttL");
	By txtAccEffectDate = By.id("00N6F00000Hgubh");
	By txtBillingStreet = By.id("acc17street");
	By txtBillingCity = By.id("acc17city");
	By txtBillingState = By.id("acc17state");
	By txtBillingPostalCode = By.id("acc17zip");
	By txtPhone = By.id("acc10");
	
	ExcelUtility util = new ExcelUtility();
	
	/**
	 ********************************************************************** 
	 * Method Name: createAccount
	 * @Description : Clicking on new Account button and creating new Account
	 * @Author: Sunil Chellwani
	 *********************************************************************** 
	 */
	public void createAccount(WebDriver driver, String accountName, String accountPhone, String accountType, String accountStreet, String accountCity, String accountState, String accountPostCode, String accountEffDate, String accRenewMonth, String createOppo, ExtentTest child)
	{
		
		try
		{
			
			//click on Accounts tab
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(tabAccounts));
			driver.findElement(tabAccounts).click();
			
			//click on new button to create new account
			wait.until(ExpectedConditions.elementToBeClickable(btnNew));
			driver.findElement(btnNew).click();
			
			//Enter the Account Name
			wait.until(ExpectedConditions.elementToBeClickable(txtAccountName));
			driver.findElement(txtAccountName).sendKeys(accountName);
			
			//Enter Account Legal Name
			driver.findElement(txtAccLegalName).sendKeys(accountName);
			
			//Enter Phone number
			driver.findElement(txtPhone).sendKeys(accountPhone);
			
			//Select account type
			Select accType = new Select(driver.findElement(drpType));
			accType.selectByVisibleText(accountType);
			
			//Enter the address details
			driver.findElement(txtBillingStreet).sendKeys(accountStreet);
			driver.findElement(txtBillingCity).sendKeys(accountCity);
			driver.findElement(txtBillingState).sendKeys(accountState);
			driver.findElement(txtBillingPostalCode).sendKeys(accountPostCode);
			
			//Copy Billing address as Shipping address
			driver.findElement(lnkCopyaddress).click();
			
			//Enter Account Effective Date
			driver.findElement(txtAccEffectDate).sendKeys(accountEffDate);
			driver.findElement(lblAccEffectDate).click();
			
			//Select Account Renew Month
			Select accRenew = new Select(driver.findElement(drpRenewMonth));
			accRenew.selectByVisibleText(accRenewMonth);
			
			//Enter Account Description
			driver.findElement(txtAccDescription).clear();
			driver.findElement(txtAccDescription).sendKeys("New Account "+accountName+" implementation with Benefit Setup for CDH products");
			
			//click on Save button
			driver.findElement(btnSave).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'"+accountName+"')]")));
			
			String screenshotPath = util.getScreenshot(driver, "Account", "Acc_Created");
			child.log(LogStatus.PASS, "Account with Account Name "+driver.findElement(lblAccName).getText()+" is created successfully");
			child.log(LogStatus.INFO,"To View Screenshot of Created Account <a href=\"file:///"+screenshotPath+"\">Click Here</a>");
			
			if(createOppo.equalsIgnoreCase("YES"))
			{

				//click on Opportunities link
				driver.findElement(lnkOpportunities).click();
				wait.until(ExpectedConditions.elementToBeClickable(btnNewOpportunity));

				//click on New opportunity button
				driver.findElement(btnNewOpportunity).click();

			}
			
		}
		catch(Exception e)
		{
			
			System.out.println("Account could not be created due to error "+e.toString());
			e.printStackTrace();
			child.log(LogStatus.FAIL, "Account could not be created due to error "+e.toString());
		}
	}

}
