package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import tests.LoginTest;

public class MyListener implements ITestListener {
	public static ExtentReports ex;
	ExtentTest test;
	@Override
	public void onStart(ITestContext context) {
		ex = new ExtentReports();
		 ExtentSparkReporter sparkreporter = new ExtentSparkReporter("report1.html");
		 ex.setSystemInfo("OS", System.getProperty("os.name"));
         ex.setSystemInfo("Java Version", System.getProperty("java.version"));
		 ex.setSystemInfo("Browser","Chrome");
		 try {
			ex.setSystemInfo("Host Name", java.net.InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 ex.attachReporter(sparkreporter);
		 test=ex.createTest("LoginTest");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		Object testClass = result.getInstance();
		WebDriver driver = ((LoginTest) testClass).getDriver();
		System.out.println("Driver is " + driver);
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotName = "F:\\Mphasis Practice Eclipse\\TricentisPracticeExercise\\Screenshots\\failureScreenshot_" + result.getName()+ ".png";
		File destFile = new File(screenshotName);

		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.pass(result.getThrowable());
	}
	@Override
	public void onFinish(ITestContext context) {
		ex.flush();
		try {
			Desktop.getDesktop().browse(new File("report1.html").toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ExtentReports getInstance() {
		return ex;
	}

}
