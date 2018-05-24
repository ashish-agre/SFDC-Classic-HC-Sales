package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ExcelUtility {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception 
	{   

		String[][] tabArray = null;

		try 
		{
			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			
			

			int startRow = 1;

			int startCol = 0;

			int ci,cj;

			int totalRows = ExcelWSheet.getLastRowNum();

			// you can write a function as well to get Column count

			int totalCols = ExcelWSheet.getRow(0).getLastCellNum() ;

			tabArray=new String[totalRows][totalCols];

			ci=0;

			for (int i=startRow;i<=totalRows;i++, ci++) 
			{           	   
				cj=0;
				for (int j=startCol;j<totalCols;j++, cj++)
				{
					tabArray[ci][cj]=getCellData(i,j);
					System.out.println(tabArray[ci][cj]);  
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}

		catch (IOException e)
		{
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return(tabArray);
	}

	public static String getCellData(int RowNum, int ColNum) throws Exception 
	{
		try
		{
			FormulaEvaluator eval = ExcelWBook.getCreationHelper().createFormulaEvaluator();

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			DataFormatter formatter = new DataFormatter();

			String CellData = formatter.formatCellValue(Cell, eval);//Cell.getStringCellValue();
			return CellData;

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw (e);
		}
	}


	@SuppressWarnings("static-access")
	public static void setCellData(String Result,  int RowNum, int ColNum, String filepath, String sheetname) throws Exception	{

		try
		{
			Row  = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) 
			{
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} 
			else 
			{
				Cell.setCellValue(Result);
			}

			// Constant variables Test Data path and Test Data file name

			FileOutputStream fileOut = new FileOutputStream(filepath+"//"+sheetname);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}
		catch(Exception e)
		{
			throw (e);
		}
	}

	/**
	 ********************************************************************** 
	 * Method Name: getScreenshot
	 * @Description : Capturing screenshot and storing in the folder
	 * @Author :  Sunil Chellwani
	 *********************************************************************** 
	 */
	public String getScreenshot(WebDriver driver, String folderName, String screenShotName) throws Exception
	{
		
		String screenshotpath = System.getProperty("user.dir")+"\\Screenshots\\"+folderName+"\\"+screenShotName+".png";
		try 
		{
			
			Thread.sleep(3000);
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			File destDir=new File(screenshotpath);
			FileUtils.copyFile(source, destDir);
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			
		}
		return screenshotpath;

	}

}
