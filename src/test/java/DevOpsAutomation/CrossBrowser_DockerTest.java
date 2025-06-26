package DevOpsAutomation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CrossBrowser_DockerTest {
    WebDriver driver;

    @Parameters("bname")
    @Test
    public void browserTest(String bname) {
        try {
            if (bname.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                System.out.println("✅ Connected to Chrome");
            } else if (bname.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                System.out.println("✅ Connected to Firefox");
            } else if (bname.equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                System.out.println("✅ Connected to Edge");
            } else {
                System.out.println("❌ Invalid browser name: " + bname);
                return;
            }

            driver.get("https://www.amazon.in");
            Thread.sleep(5000);  // Simulate wait for loading
            System.out.println("🚀 Test Executed on: " + bname);

        } catch (Exception e) {
            System.out.println("❌ Error in " + bname + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}

