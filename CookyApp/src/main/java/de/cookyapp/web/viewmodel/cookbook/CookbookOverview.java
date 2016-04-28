package de.cookyapp.web.viewmodel.cookbook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.cookyapp.enums.CookbookVisibility;

/**
 * Created by Dominik Schaufelberger on 22.04.2016.
 */
public class CookbookOverview {
    private List<Cookbook> cookbooks;

    public CookbookOverview( List<Cookbook> cookbooks ) {
        setCookbooks( cookbooks );
    }

    public List<Cookbook> getCookbooks() {
        return cookbooks;
    }

    public void setCookbooks( List<Cookbook> cookbooks ) {
        this.cookbooks = cookbooks != null ? cookbooks : new ArrayList<>();
    }

    public List<Cookbook> getPublicCookbooks() {
        return filterCookbooksByVisibility( CookbookVisibility.PUBLIC );
    }

    public List<Cookbook> getPrivateCookbooks() {
        return filterCookbooksByVisibility( CookbookVisibility.PRIVATE );
    }

    public List<Cookbook> getSharedCookbooks() {
        return filterCookbooksByVisibility( CookbookVisibility.FRIENDS );
    }

    private List<Cookbook> filterCookbooksByVisibility(CookbookVisibility visibility) {
        return getCookbooks().stream().filter( cookbook -> cookbook.getVisibility() == visibility ).collect( Collectors.toList());
    }
}
