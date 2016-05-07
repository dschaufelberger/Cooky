package de.cookyapp.service;

import de.cookyapp.service.mocks.AuthenticationMock;
import de.cookyapp.service.mocks.CookbookRepositoryMock;
import de.cookyapp.service.mocks.RecipeRepositoryMock;
import de.cookyapp.service.services.CookbookContentService;
import de.cookyapp.service.services.interfaces.ICookbookContentService;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Dominik Schaufelberger on 07.05.2016.
 */
public class CookbookContentServiceTest {
    private ICookbookContentService contentService;
    private CookbookRepositoryMock cookbookRepositoryMock;
    private RecipeRepositoryMock recipeRepositoryMock;
    private AuthenticationMock authenticationMock;

    @Before
    public void setUp() throws Exception {
        this.cookbookRepositoryMock = new CookbookRepositoryMock();
        this.recipeRepositoryMock = new RecipeRepositoryMock();
        this.authenticationMock = new AuthenticationMock( "CookyTester" );
        this.contentService = new CookbookContentService( this.recipeRepositoryMock, this.cookbookRepositoryMock, this.authenticationMock );
    }

    @Test
    public void testAddRecipeToCookbook() throws Exception {

    }

    @Test
    public void testAddRecipesToCookbook() throws Exception {

    }

    @Test
    public void testRemoveRecipeFromCookbook() throws Exception {

    }

    @Test
    public void testRemoveAllRecipesFromCookbook() throws Exception {

    }

    @Test
    public void testMoveRecipeBetweenCookbooks() throws Exception {

    }
}