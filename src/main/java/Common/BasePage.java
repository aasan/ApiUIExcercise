package Common;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.concurrent.TimeUnit;

/**
 * Created by atariq on 12/10/2020.
 */
public class BasePage {
    public static WebDriver driverObj;
    // Setup Required Driver
    public static WebDriver setupWebDriver(String driverName) {

        if (driverName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/webDrivers/macgeckodriver");
            System.out.println(System.getProperty("user.dir") + "/src/test/resources/webdriver/macgeckodriver");
            driverObj = new FirefoxDriver();
        }
        else
        if (driverName.equals("safari")) {
            SafariOptions options = new SafariOptions();
            options.setUseCleanSession(true);
            driverObj = new SafariDriver(options);
            // Javascript  Code used to Maximize Safari
            JavascriptExecutor jse = (JavascriptExecutor)driverObj;
            String screenWidth = jse.executeScript("return screen.availWidth").toString();
            String screenHeight = jse.executeScript("return screen.availHeight").toString();
            int intScreenWidth = Integer.parseInt(screenWidth);
            int intScreenHeight = Integer.parseInt(screenHeight);
            Dimension d = new Dimension(intScreenWidth - 10, intScreenHeight - 10);
            // Maximise Safari Browser Window
            driverObj.manage().window().setSize(d);

        }
        else
        // Default WebDriver chrome in case
        // if(driverName.equals("chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("enable-automation");
            options.addArguments("--disable-infobars");
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setJavascriptEnabled(true);
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/webDrivers/macchromedriver");
            driverObj = new ChromeDriver(options);
            driverObj.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        return driverObj;
    }
}
