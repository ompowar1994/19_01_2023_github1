package Model1_login_test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Library_files.BaseClass;
import Library_files.UtilityClass;
import Model1_login.KiteHomePage;
import Model1_login.KiteLogin1Page;
import Model1_login.KiteLogin2Page;

//sub class or TestClass

public class KiteLoginTest extends BaseClass
{
	KiteLogin1Page login1;           //declare globally
	KiteLogin2Page login2;
	KiteHomePage home;
	int TCID;
	
	
	@BeforeClass
	public void openBrowser() throws EncryptedDocumentException, IOException
	{		
		 initializeBrowser();
		 
		 login1=new KiteLogin1Page(driver);                //initialize locally
		 login2=new KiteLogin2Page(driver);
		 home=new KiteHomePage(driver);				
	}
	
	
	@BeforeMethod
	public void loginToApp() throws EncryptedDocumentException, IOException 
	{
		login1.setKiteLogin1PageUsername(UtilityClass.getPFData("UN"));
		login1.setKiteLogin1PagePassword(UtilityClass.getPFData("PWD"));
		login1.clickKiteLogin1PageLoginBtn();
		login2.setKiteLogin2PagePin(UtilityClass.getPFData("PIN"));
		login2.clickKiteLogin2PageCntBtn();		
	}
	
	
	
	@Test
	public void VerifyUserID() throws EncryptedDocumentException, IOException
	{
		TCID=101;
		String actUserID=home.getKiteHomepageUserID();
		String expUserID=UtilityClass.getTD(0, 3);
		Assert.assertEquals(actUserID, expUserID,"Failed: act & exp results are diff");
	}
	
	
	@AfterMethod
	public void logoutFromApp(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE) 
		{
			UtilityClass.captureScreenshot(driver, TCID);
		}
				
	}
	
	@AfterClass
	public void closeBrowser() throws InterruptedException 
	{
		Thread.sleep(3000);
		driver.quit();		
	}
	
}
