package com.goeuro.pageObjects.searchResults;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.goeuro.pageObjects.PageObject;

public class SearchResultsPage extends PageObject {

    @FindBy(className = "resultTabs")
    private WebElement resultsTable;

    protected SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ExpectedCondition<?> pageIsLoaded() {
        return ExpectedConditions.visibilityOf(resultsTable);
    }
}
