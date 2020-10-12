package Common;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import static Common.BasePage.driverObj;


public class Hooks {

    public static String currentScenarioName;
    public static Scenario currentScenario;

    /* After each scenario this block will run. */
    @After
    public void afterScenario() {
        // Save Screenshot of Failure with Report
        if (currentScenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driverObj).getScreenshotAs(OutputType.BYTES);
                currentScenario.embed(screenshot, "image/png");
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        currentScenario = scenario;
        currentScenarioName = currentScenario.getName();
        String message = "Scenario Started : " + currentScenarioName;
        System.out.println(message);
    }

}