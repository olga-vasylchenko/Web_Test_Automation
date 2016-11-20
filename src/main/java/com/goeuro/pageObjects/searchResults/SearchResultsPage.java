package com.goeuro.pageObjects.searchResults;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.goeuro.pageObjects.PageObject;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SearchResultsPage extends PageObject {

    protected WebDriver driver;

    @FindBy(className = "resultTabs")
    private WebElement resultsTable;

    @FindBy(css = ".resultTabs ul>li:nth-child(1)>a")
    private WebElement trainsTab;

    @FindBy(css = ".resultTabs ul>li:nth-child(2)>a")
    private WebElement flightsTab;

    @FindBy(css = ".resultTabs ul>li:nth-child(3)>a")
    private WebElement busesTab;

    @FindBy(css = ".resultTabs [data-test='Result']")
    private List<WebElement> searchResultsList;

    private final String priceSortingOptionXPath = "//*[contains(@class,'SortingBar')]//*[contains(@data-key,'sorting.price')]";

    @FindBy(xpath = priceSortingOptionXPath)
    private WebElement priceSortingOption;

    @FindBy(xpath = priceSortingOptionXPath + "/..")
    private WebElement priceSortingOptionParentElement;

    private String mainPriceCss = "[class*='Result__priceMain']";

    private String priceFractionCss = "[class*='Result__priceFraction']";

    protected SearchResultsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    protected ExpectedCondition<?> pageIsLoaded() {
        return ExpectedConditions.visibilityOf(resultsTable);
    }

    public void selectTrainsMode() {
        if (!getActiveTransportModeTabCondition(trainsTab)) {
            trainsTab.click();
            waitForActiveTransportModeTab(trainsTab);
        }
    }

    public ArrayList<Double> getResultsPricesList() throws NoSuchElementException {
        if (!searchResultsList.isEmpty()) {
            return getPricesList(searchResultsList);
        }

        throw new NoSuchElementException("No search results were displayed!");
    }

    public void choosePriceSortingOption() {
        if (!getActiveSortingOptionCondition(priceSortingOptionParentElement)) {
            priceSortingOption.click();
            waitForActiveSortingOption(priceSortingOptionParentElement);
        }
    }

    private ArrayList<Double> getPricesList(List<WebElement> list) {
        ArrayList<Double> pricesList = new ArrayList();

        for (Iterator<WebElement> i = list.iterator(); i.hasNext(); ) {
            WebElement result = i.next();
            pricesList.add(Double.parseDouble(getFullPrice(result)));
        }
        return pricesList;
    }

    private String getFullPrice(WebElement element) {
        String mainPrice = element.findElement(By.cssSelector(mainPriceCss)).getText();
        String priceFraction = element.findElement(By.cssSelector(priceFractionCss)).getText();
        return mainPrice.concat(".").concat(priceFraction);
    }

    private void waitForActiveTransportModeTab(final WebElement modeElement) {
        new WebDriverWait(this.driver, 5).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean condition = getActiveTransportModeTabCondition(modeElement);
                return condition == true;
            }
        });
    }

    private void waitForActiveSortingOption(final WebElement sortingOptionParentElement) {
        new WebDriverWait(this.driver, 5).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean condition = getActiveSortingOptionCondition(sortingOptionParentElement);
                return condition == true;
            }
        });
    }

    private boolean getActiveTransportModeTabCondition(WebElement modeElement) {
        return modeElement.getAttribute("class").contains("activeTab");
    }

    private boolean getActiveSortingOptionCondition(WebElement sortingOptionParentElement) {
        return sortingOptionParentElement.getAttribute("class").contains("mainActive");
    }
}
