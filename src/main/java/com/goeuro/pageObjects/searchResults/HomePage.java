package com.goeuro.pageObjects.searchResults;

import com.goeuro.pageObjects.PageObject;
import domain.homePageSearch.SearchOption;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;
import java.util.Map;

public class HomePage extends PageObject {

    private static String url = "http://www.goeuro.com";

    @FindBy(id = "filters")
    private WebElement searchFilters;

    @FindBy(id = "from_filter")
    private WebElement fromInput;

    @FindBy(id = "to_filter")
    private WebElement toInput;

    @FindBy(id = "search-form__submit-btn")
    private WebElement searchButton;

    @FindBy(css = "#hotel_checkbox_checked ~ span")
    private WebElement airBnbCheckbox;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ExpectedCondition<?> pageIsLoaded() {
        return ExpectedConditions.visibilityOf(searchFilters);
    }

    public static HomePage open(WebDriver driver) {
        driver.get(url);
        return new HomePage(driver);
    }

    public SearchResultsPage search(Map<SearchOption, Object> details) {
        specifyFrom(details.get(SearchOption.FROM).toString());
        specifyTo(details.get(SearchOption.TO).toString());
        specifyWhetherToSearchForAccommodation((Boolean) details.get(SearchOption.INCLUDE_AIRBNB));
        searchButton.click();
        return new SearchResultsPage(driver);
    }

    private void specifyWhetherToSearchForAccommodation(Boolean flag) {
        if (flag == true) {
            if (!airBnbCheckbox.getAttribute("class").contains("checked"))
                airBnbCheckbox.click();
        } else {
            if (airBnbCheckbox.getAttribute("class").contains("checked"))
                airBnbCheckbox.click();
        }
    }

    private void specifyTo(String to) {
        toInput.clear();
        toInput.sendKeys(to);
        toInput.sendKeys(Keys.ENTER);
    }

    private void specifyFrom(String from) {
        fromInput.clear();
        fromInput.sendKeys(from);
        toInput.sendKeys(Keys.ENTER);
    }
}
