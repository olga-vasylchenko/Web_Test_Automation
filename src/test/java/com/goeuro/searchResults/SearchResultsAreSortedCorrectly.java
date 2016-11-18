package com.goeuro.searchResults;

import static org.junit.Assert.assertTrue;

import com.goeuro.pageObjects.searchResults.HomePage;
import com.goeuro.pageObjects.searchResults.SearchResultsPage;
import domain.homePageSearch.SearchPresets;
import domain.homePageSearch.SearchOption;
import org.junit.Test;

import com.goeuro.BaseWebFixture;

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
    public void searchResultsAreSortedByPlice() {
        Map<SearchOption, Object> searchOptions = SearchPresets.getDefaultSearchOptions();
        searchResultsPage = homePage.search(searchOptions);
    }
}
