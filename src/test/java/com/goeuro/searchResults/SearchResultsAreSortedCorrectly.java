package com.goeuro.searchResults;

import static org.junit.Assert.assertTrue;

import com.goeuro.pageObjects.searchResults.HomePage;
import com.goeuro.pageObjects.searchResults.SearchResultsPage;
import cucumber.api.Scenario;
import domain.homePageSearch.SearchPresets;
import domain.homePageSearch.SearchOption;
import cucumber.api.java8.En;

import java.util.ArrayList;
import java.util.Map;

public class SearchResultsAreSortedCorrectly extends BaseWebFixture implements En {

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;

    public SearchResultsAreSortedCorrectly() {

        Before((Scenario scenario) -> {
            super.setUp();
            homePage = HomePage.open(driver);
        });

        Given("^I perform a search for today$", () -> {
            Map<SearchOption, Object> searchOptions = SearchPresets.getDefaultSearchOptions();
            searchResultsPage = homePage.search(searchOptions);
        });

        When("^I select Train mode and Cheapest sorting options on Search Results page$", () -> {
            searchResultsPage.selectTrainsMode();
            searchResultsPage.choosePriceSortingOption();
        });

        Then("^the results are sorted by price in ascending order$", () -> {
            ArrayList<Double> pricesList = searchResultsPage.getResultsPricesList();
            boolean isSorted = checkPriceListIsSortedInAscendingOrder(pricesList);

            assertTrue("The list of train options is not sorted by price from the cheapest to more expensive.",
                    isSorted);
        });

        After((Scenario scenario) -> {
            super.tearDown();
        });
    }

    private boolean checkPriceListIsSortedInAscendingOrder(ArrayList<Double> pricesList) {
        boolean isSorted = true;
        for (int i = 0; i < pricesList.size(); i++) {
            if (i < pricesList.size() - 1) {
                if (Double.compare(pricesList.get(i), pricesList.get(i + 1)) == 1) {
                    isSorted = false;
                    break;
                }
            }
        }
        return isSorted;
    }
}
