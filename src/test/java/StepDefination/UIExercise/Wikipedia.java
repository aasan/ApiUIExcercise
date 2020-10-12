package StepDefination.UIExercise;

import Common.BasePage;
import PageObjects.UIExcercise.WikipediaPageObj;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;


/**
 * Created by atariq on 12/10/2020.
 */
public class Wikipedia extends BasePage {
    private WebDriver currentDriver = setupWebDriver("chrome");
    private List<String> allLanguageMenus;
    private String errorMessage;
    WikipediaPageObj wikipediaPageObj = new WikipediaPageObj(currentDriver);

    @Given("^I navigate to wikipedia website$")
    public void i_navigate_to_wikipedia_website() throws Throwable {
        currentDriver.get("https://www.wikipedia.org/");
    }

    @Then("^I should see Search textbox$")
    public void i_should_see_Search_textbox() throws Throwable {
        errorMessage = "Search text box is not displayed as expected";
        Assert.assertEquals(errorMessage, true, wikipediaPageObj.searchInput.isDisplayed());
    }

    @Then("^By default language is selected as \"([^\"]*)\"$")
    public void by_default_language_is_selected_as(String defaultLanguage) throws Throwable {
        Select languageDropDown = new Select(wikipediaPageObj.languageDropDown);
        WebElement option = languageDropDown.getFirstSelectedOption();
        errorMessage = "Drop down default value is " + option.getText() + " while expected is " + defaultLanguage;
        Assert.assertEquals(errorMessage, true, option.getText().equals(defaultLanguage));
    }

    @Then("^I enter \"([^\"]*)\" in the search textbox$")
    public void i_enter_in_the_search_textbox(String searchItem) throws Throwable {
        wikipediaPageObj.searchInput.sendKeys(searchItem);
    }

    @When("^I click search button on the page$")
    public void i_click_search_button_on_the_page() throws Throwable {
        wikipediaPageObj.submitButton.click();
    }

    @Then("^Search result page should displayed with heading \"([^\"]*)\"$")
    public void search_result_page_should_displaye_with_heading(String heading) throws Throwable {
        errorMessage = "Page heading is  " + wikipediaPageObj.pageHeading.getText() + " while expected is " + heading;
        Assert.assertEquals(true, wikipediaPageObj.pageHeading.getText().equalsIgnoreCase(heading));
    }

    @Then("^I should see other languages available for translate$")
    public void i_should_see_other_languages_available_for_translate() throws Throwable {
        allLanguageMenus = wikipediaPageObj.getAllLanguagesLinks();
        errorMessage = "Translated Language option is " + allLanguageMenus.size() + " while expected more than 1 ";
        Assert.assertEquals(errorMessage, true, allLanguageMenus.size() > 0);

    }

    @Given("^I navigate to result in other language to verify it contains English version link$")
    public void i_navigate_to_search_result_in_other_language_to_verify_it_contains_English_version_link() throws Throwable {
        String linkURL = currentDriver.getCurrentUrl();
        for (String elementText : allLanguageMenus) {
            currentDriver.findElement(By.linkText(elementText)).click();
            errorMessage = "English link with URL is not displayed on Translated Page " + elementText;
            Assert.assertEquals(true, wikipediaPageObj.verifyEnglishLink(linkURL));
            currentDriver.navigate().back();
        }
        driverObj.quit();
    }
}
