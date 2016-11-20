package com.goeuro.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageObject {
    protected final WebDriver driver;

    protected PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitForPageToLoad(driver);
    }

    private void waitForPageToLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(pageIsLoaded());
    }

    protected abstract ExpectedCondition<?> pageIsLoaded();

}
