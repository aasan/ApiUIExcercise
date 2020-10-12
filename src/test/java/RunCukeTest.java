// Test Runner file

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false,
        format = {
                "pretty",
                "html:target/site/cucumber-pretty",
                "json:target/cucumber.json" }

        , tags = { "~@ignore" } , features = "src/test/resources/Features"
)
public class RunCukeTest   {
}