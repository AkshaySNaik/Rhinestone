package com.rhinestone.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.rhinestone.testcase.Baseclass;

public class ExtentListener extends Baseclass implements ITestListener {

	ExtentSparkReporter httpreport;
	ExtentReports report;
	ExtentTest test;
	ThreadLocal<ExtentTest> extenttestthread = new ThreadLocal<ExtentTest>();

	public void cofigReport() {

		String timestap = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		String reportname = "Rhinestone" + timestap + ".html";
		String reportpath = System.getProperty("user.dir") + "//extentreports//" + reportname;

		httpreport = new ExtentSparkReporter(reportpath);
		httpreport.config().setDocumentTitle("Test Report");
		httpreport.config().setReportName("Rhinestone Report");
		httpreport.config().setTheme(Theme.STANDARD);

		report = new ExtentReports();
		report.attachReporter(httpreport);
		report.setSystemInfo("Username", System.getProperty("user.name"));
		report.setSystemInfo("Operting System", System.getProperty("os.name"));
		report.setSystemInfo("Java Version", System.getProperty("java.version"));
		report.setSystemInfo("Browser Name", config.getBrowser());

	}

	@Override
	public void onStart(ITestContext context) {

		cofigReport();
		log.info("Test Started For The Given Features");
	}

	@Override
	public void onFinish(ITestContext context) {

		log.info("Test Completed For The Given Features");
		report.flush();

	}

	@Override
	public void onTestStart(ITestResult result) {

		log.info("Test Started For:" + result.getName());
		test = report.createTest(result.getName());
		extenttestthread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		log.info("Test Passed:" + result.getName());
		extenttestthread.get().log(Status.PASS,
				MarkupHelper.createLabel("Test Passed: " + result.getName(), ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {

		log.info("Test Failed:" + result.getName());
		extenttestthread.get().log(Status.FAIL,
				MarkupHelper.createLabel("Test Failed: " + result.getName(), ExtentColor.RED));

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		captureScreenshot(driver, result.getName());

		String scrfile = System.getProperty("user.dir") + "//screenshots//" + result.getName() + ".png";
		File file = new File(scrfile);

		if (file.exists()) {

			extenttestthread.get().addScreenCaptureFromPath(scrfile, result.getName());
			extenttestthread.get().fail(result.getThrowable());
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		log.info("Test Skipped:" + result.getName());
		extenttestthread.get().log(Status.SKIP,
				MarkupHelper.createLabel("Test Skipped: " + result.getName(), ExtentColor.YELLOW));
	}

}
