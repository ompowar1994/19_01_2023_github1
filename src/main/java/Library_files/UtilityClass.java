package Library_files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class UtilityClass
{
	
	//This method is use to get TesData from excel sheet
	//Need to pass 2 parameters: 1.rowIndex 2. cellIndex
	//@AuthorName: Sanjay
	public static String getTD(int rowIndex,int cellIndex) throws EncryptedDocumentException, IOException
	{
		FileInputStream file=new FileInputStream("C:\\Users\\Omkar\\eclipse-workspace\\maven_project1\\testdata\\12 March A.xlsx");
		 Sheet sh = WorkbookFactory.create(file).getSheet("DDF");
		String value = sh.getRow(rowIndex).getCell(cellIndex).getStringCellValue();			
		return value;
	}
	
	//This method is use to capture screenshot of webpage
	//Need to pass 2 inputs: 1.webDriver object  2. TestCaseID
	//@AuthorName: Sanjay
	public static void captureScreenshot(WebDriver driver,int TCID) throws IOException 
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest=new File("C:\\Users\\Omkar\\eclipse-workspace\\maven_project1\\screenshots\\TestCaseID\"+TCID+\".jpg");
		FileHandler.copy(src, dest);
	}
	
	
	
	public static String getPFData(String key) throws IOException
	{
		FileInputStream file=new FileInputStream("C:\\Users\\Omkar\\eclipse-workspace\\maven_project1\\propertifile.properties");
		
		Properties p=new Properties();
		p.load(file);
		
		String value = p.getProperty(key);
		
		return value;
	}
	
	

}
