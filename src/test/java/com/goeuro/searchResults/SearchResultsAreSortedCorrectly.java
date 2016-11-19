package com.goeuro.searchResults;

import static org.junit.Assert.assertTrue;

import com.goeuro.pageObjects.searchResults.HomePage;
import com.goeuro.pageObjects.searchResults.SearchResultsPage;
import domain.homePageSearch.SearchPresets;
import domain.homePageSearch.SearchOption;
import org.junit.Test;

import com.goeuro.BaseWebFixture;

import java.util.ArrayList;
import java.util.Map;

public class SearchResultsAreSortedCorrectly extends BaseWebFixture {

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        homePage = HomePage.open(driver);
    }

    @Test
    public void trainSearchResultsAreSortedByCheapestPrice() throws Exception {
        Map<SearchOption, Object> searchOptions = SearchPresets.getDefaultSearchOptions();

        searchResultsPage = homePage.search(searchOptions);
        searchResultsPage.selectTrainsMode();
        searchResultsPage.choosePriceSortingOption();
        ArrayList<Double> pricesList = searchResultsPage.getResultsPricesList();
        boolean isSorted = checkPriceListIsSortedInAscendingOrder(pricesList);

        assertTrue("The list of train options is not sorted by price from the cheapest to more expensive.",
                isSorted);

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
