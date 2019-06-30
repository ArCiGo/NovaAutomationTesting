package utilities.ExtentReports;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if(extent == null) {
            //extent = new ExtentReports("/Users/arcigo/Documents/Projects/ExtentReportResults.html", true);
            extent = new ExtentReports(System.getProperty("user.dir")+ "\\ExtentReportResults.html", true);
        }

        return extent;
    }
}
