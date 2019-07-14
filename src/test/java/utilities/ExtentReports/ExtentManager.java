package utilities.ExtentReports;

import com.relevantcodes.extentreports.ExtentReports;
import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if(extent == null) {
            extent = new ExtentReports(System.getProperty("user.dir") + File.separator + "ExtentReportResults.html", true);
        }

        return extent;
    }
}
