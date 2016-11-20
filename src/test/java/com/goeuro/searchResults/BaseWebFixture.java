package com.goeuro.searchResults;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"}, features = "src/test/resources")
public abstract class BaseWebFixture {
    protected WebDriver driver;

    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    public void tearDown() {
        driver.quit();
    }
}
