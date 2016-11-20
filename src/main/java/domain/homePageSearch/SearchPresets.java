package domain.homePageSearch;

import java.util.HashMap;
import java.util.Map;

public class SearchPresets {

    private static Map<SearchOption, Object> searchOptions = new HashMap<>();

    public static Map<SearchOption, Object> getDefaultSearchOptions() {
        searchOptions.put(SearchOption.FROM, "Berlin");
        searchOptions.put(SearchOption.TO, "Prague");
        searchOptions.put(SearchOption.INCLUDE_AIRBNB, false);
        return searchOptions;
    }

}



