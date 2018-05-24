package pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;






import utility.ExcelUtility;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class Document
{

	By btnAttach = By.name("attachFile");
	By btnDone = By.name("cancel");
	By btnEdit = By.name("edit");
	By btnFile = By.id("file");
	By btnNewDoc = By.name("new00N6F00000Hgk9Y");
	By btnOk = By.xpath("//input[@value='OK']");
	By btnSave = By.name("save");
	By btnSaveStatus = By.name("inlineEditSave");
	By btnAttachFile = By.id("Attach");
	By drpBenReqStatus = By.id("00N6F00000Hgk9E");
	By drpDocType = By.id("00N6F00000Hgk9T");
	By drpOppo = By.id("opp11");
	By lnkNotesAttachment = By.xpath("//span[text()='Notes & Attachments']");
	By lnkBenReqForm = By.partialLinkText("BRF-0");
	By lnkNewDoc = By.xpath("//span[text()='Document']");
	By lnkOpportunity = By.xpath("//div[@id='CF00N6F00000Hgk7r_ileinner']//a");
	By frame1 = By.id("RLPanelFrame");
	By lblFileUploaded = By.xpath("//table[@class='detailList']//tr[1]//td[2]");
	By lblBenReqStatus = By.id("00N6F00000Hgk9E_ileinner");
	By lblStage = By.id("opp11_ileinner");

	ExcelUtility util = new ExcelUtility();

	/**
	 ********************************************************************** 
	 * Method Name: createDocument
	 * @Description : Creating a new document
	 * @Author Nishika Nigam
	 *********************************************************************** 
	 */
	public void createDocument(WebDriver driver, String docType, String fileName, ExtentTest child)
	{

		try
		{

			//click on New Document button
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(lnkNewDoc));
			driver.findElement(lnkNewDoc).click();
			wait.until(ExpectedConditions.elementToBeClickable(btnNewDoc));
			driver.findElement(btnNewDoc).click();

			//Select Document Type

			wait.until(ExpectedConditions.elementToBeClickable(drpDocType));
			Select docuType = new Select(driver.findElement(drpDocType));
			docuType.selectByVisibleText(docType);


			//click on Save button
			wait.until(ExpectedConditions.elementToBeClickable(btnSave));
			driver.findElement(btnSave).click();
			wait.until(ExpectedConditions.elementToBeClickable(lnkNotesAttachment));

			String screenshotPath = util.getScreenshot(driver, "Document", docType+"_Document");
			child.log(LogStatus.PASS, docType+" Document with document Name "+driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText()+" is created successfully");
			child.log(LogStatus.INFO,"To View Screenshot of Created "+docType+"_Document <a href=\"file:///"+screenshotPath+"\">Click Here</a>");
			

			//Attaching Notes for Document 1

			wait.until(ExpectedConditions.elementToBeClickable(lnkNotesAttachment));
			driver.findElement(lnkNotesAttachment).click();
			wait.until(ExpectedConditions.elementToBeClickable(btnAttach));
			driver.findElement(btnAttach).click();


			//To input the filename along with path
			File f1 =new File(System.getProperty("user.dir")+"//src//test//java//testData//"+fileName);
			driver.findElement(btnFile).sendKeys(f1.getAbsolutePath());


			// To click on the Attach File(submit)button (Not the browse button)
			wait.until(ExpectedConditions.elementToBeClickable(btnAttachFile));
			driver.findElement(btnAttachFile).click();
			Thread.sleep(5000);

			wait.until(ExpectedConditions.elementToBeClickable(lblFileUploaded));
			String checkFileName = driver.findElement(lblFileUploaded).getText();

			screenshotPath = util.getScreenshot(driver, "Document", "File_"+checkFileName);
			child.log(LogStatus.PASS, "File with name "+checkFileName+" is attached successfully");
			child.log(LogStatus.INFO,"To View Screenshot of Attached File "+checkFileName+" <a href=\"file:///"+screenshotPath+"\">Click Here</a>");

			// Clicking Done after attaching File
			wait.until(ExpectedConditions.elementToBeClickable(btnDone));
			driver.findElement(btnDone).click();

		}
		catch(Exception e)
		{
			
			System.out.println("Document could not be created due to error "+e.toString());
			e.printStackTrace();
			child.log(LogStatus.FAIL, "Document could not be created due to error "+e.toString());
			
		}

	}



	public void createNewDoc(WebDriver driver, String docType, String fileName, ExtentTest child)
	{


		try
		{
			//Clicking Benefit Request Form link to create new document
			WebDriverWait wait = new WebDriverWait(driver,60);

			wait.until(ExpectedConditions.elementToBeClickable(lnkBenReqForm));
			driver.findElement(lnkBenReqForm).click();


			/*Calling the createDocument function to 
			create new document and attaching file for Document 2
			 for the same Benefit Request Form */

			createDocument(driver, docType, fileName, child);

			//click on Benefit Request form id and fetch the status
			wait.until(ExpectedConditions.elementToBeClickable(lnkBenReqForm));
			driver.findElement(lnkBenReqForm).click();
			wait.until(ExpectedConditions.elementToBeClickable(lnkNewDoc));
			child.log(LogStatus.INFO, "Benefit Request Form Status is "+driver.findElement(lblBenReqStatus).getText());

		}

		catch(Exception e)
		{
			System.out.println("Document could not be created due to error "+e.toString());
			e.printStackTrace();
			child.log(LogStatus.FAIL, "Document could not be created due to error "+e.toString());
		}


	}

}


