package PageObjects.UIExcercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

/**
 * Created by atariq on 12/10/2020.
 */
public class WikipediaPageObj {


    private WebDriver driver;
    private WebElement menu;
    private List<WebElement> allLanguageLinks;

    public @FindBy(how = How.ID, using = "searchInput")
    WebElement searchInput;

    public @FindBy(how = How.ID, using = "searchLanguage")
    WebElement languageDropDown;

    public @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    WebElement submitButton;

    public @FindBy(how = How.ID, using = "firstHeading")
    WebElement pageHeading;

    public @FindBy(how = How.ID, using = "p-lang")
    WebElement languageSection;

    //Constructor
    public WikipediaPageObj(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    // Get All Language Links displayed on Wiki Page
    public List<String> getAllLanguagesLinks() {
        WebElement menu = languageSection.findElement(By.tagName("ul"));
        List < WebElement > allLanguageLinks = menu.findElements(By.tagName("a"));
        List < String > displayedLinks = new ArrayList<>();

        System.out.println("Total Size " + allLanguageLinks.size());
        for (WebElement currentLink : allLanguageLinks)
        {
            if (currentLink.isDisplayed()) {
                System.out.println("--- " + currentLink.getText());
                displayedLinks.add(currentLink.getText());
            }
        }
        return displayedLinks;
    }

    // Verify that English link is displayed and contain URL passed
    public boolean verifyEnglishLink(String linkURL) {
        menu = languageSection.findElement(By.tagName("ul"));
        allLanguageLinks = menu.findElements(By.tagName("a"));
        boolean result = false;
        for (WebElement currentLink : allLanguageLinks)
        {
            if (currentLink.isDisplayed()) {
                if (currentLink.getText().equals("English")
                        && currentLink.getAttribute("href").equals(linkURL))
                    result = true;
            }
        }
        if (result == false) System.out.println("English Link is not found on the Page ");
        return result;
    }

}