package utility;

	
import java.util.Calendar;
import java.util.Date;
import com.relevantcodes.extentreports.ExtentReports;
	
	 
	public class ExtentReporterNG //implements IReporter 
	{
	    private static ExtentReports extent;
	    
	    public synchronized static ExtentReports getReporter(String filePath) {
	        if (extent == null) {
	            extent = new ExtentReports(filePath, true);
	            
	            /*extent
	                .addSystemInfo("Host Name", "Palo Alto Networks")
	                .addSystemInfo("Environment", "QA Environment");*/
	        }        
	        return extent;
	    }
	 
	   /* @Override
	    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
	        extent = new ExtentReports(outputDirectory + File.separator + "Extent_TestReports.html", true);
	        
	        for (ISuite suite : suites) {
	            Map<String, ISuiteResult> result = suite.getResults();
	 
	            for (ISuiteResult r : result.values()) {
	                ITestContext context = r.getTestContext();
	          
	                
	                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
	                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
	                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
	            }
	        }
	 
	        extent.flush();
	       extent.close();
	    }
	 
	    private void buildTestNodes(IResultMap tests, LogStatus status) {
	        ExtentTest test;
	 
	        if (tests.size() > 0) {
	            for (ITestResult result : tests.getAllResults()) {
	                test = extent.startTest(result.getMethod().getMethodName());
	 
	                test.setStartedTime(getTime(result.getStartMillis()));
	                test.setEndedTime(getTime(result.getEndMillis()));
	 
	                for (String group : result.getMethod().getGroups())
	                    test.assignCategory(group);
	 
	                if (result.getThrowable() != null) {
	                    test.log(status, result.getThrowable());
	                }
	                else {
	                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
	                }
	 
	                extent.endTest(test);
	            }
	        }
	    }*/
	 
	    private Date getTime(long millis) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTimeInMillis(millis);
	        return calendar.getTime();        
	    }
	    
	    public synchronized static ExtentReports getReporter() {
	        return extent;
	    }
	}

