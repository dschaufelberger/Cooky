package de.cookyapp.web.viewmodel;

import java.util.List;

/**
 * Created by Jasper on 07.05.2016.
 */
public class Search {

    private String searchQuery;

    private List<String> filter;

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery( String searchQuery ) {
        this.searchQuery = searchQuery;
    }

    public List<String> getFilter() {
        return filter;
    }

    public void setFilter( List<String> filter ) {
        this.filter = filter;
    }
}
