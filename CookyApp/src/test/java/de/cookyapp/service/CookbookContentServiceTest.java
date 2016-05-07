package de.cookyapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.cookyapp.persistence.entities.CookbookEntity;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.service.exceptions.InvalidCookbookId;
import de.cookyapp.service.exceptions.InvalidRecipeId;
import de.cookyapp.service.exceptions.UserNotAuthorized;
import de.cookyapp.service.mocks.AuthenticationMock;
import de.cookyapp.service.mocks.CookbookRepositoryMock;
import de.cookyapp.service.mocks.RecipeRepositoryMock;
import de.cookyapp.service.services.CookbookContentService;
import de.cookyapp.service.services.interfaces.ICookbookContentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Dominik Schaufelberger on 07.05.2016.
 */
public class CookbookContentServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
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
    public void addRecipeToCookbook() throws Exception {
        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "CookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 1, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.addRecipeToCookbook( 1, 0 );

        // assert
        Assert.assertEquals( 1, this.cookbookRepositoryMock.getEntities().get( 0 ).getRecipes().size() );
    }

    @Test
    public void addRecipeToCookbookAsUnauthorizedUser() throws Exception {
        thrown.expect( UserNotAuthorized.class );

        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "NotCookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 1, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.addRecipeToCookbook( 1, 0 );

        // assert
    }

    @Test
    public void addRecipeToNonexistingCookbook() throws Exception {
        thrown.expect( InvalidCookbookId.class );

        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "CookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 1, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.addRecipeToCookbook( cookbookUnderTest.getId() + 1, 0 );
    }

    @Test
    public void addNonexistingRecipeToCookbook() throws Exception {
        thrown.expect( InvalidRecipeId.class );

        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "CookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 1, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.addRecipeToCookbook( 1, dummyList.size() + 1 );
    }

    @Test
    public void addRecipesToCookbook() throws Exception {
        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "CookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 10, userDummy );
        List<Integer> dummyIds = dummyList.stream().map( dummy -> dummy.getId() ).collect( Collectors.toList() );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.addRecipesToCookbook( 1, dummyIds );

        // assert
        Assert.assertEquals( dummyList.size(), this.cookbookRepositoryMock.getEntities().size() );
    }

    @Test
    public void addRecipesToCookbookAsUnauthorizedCookbookOwner() throws Exception {
        thrown.expect( UserNotAuthorized.class );

        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "NotCookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 10, userDummy );
        List<Integer> dummyIds = dummyList.stream().map( dummy -> dummy.getId() ).collect( Collectors.toList() );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.addRecipesToCookbook( 1, dummyIds );

        // assert
    }

    @Test
    public void addRecipesToCookbookAsUnauthorizedRecipeAuthor() throws Exception {
        thrown.expect( UserNotAuthorized.class );

        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "CookyTester" );
        UserEntity notCookyTesterDummy = new UserEntity();
        notCookyTesterDummy.setId( 42 );
        notCookyTesterDummy.setUsername( "NotCookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 3, userDummy );
        RecipeEntity notCookyTestersRecipe = new RecipeEntity();
        notCookyTestersRecipe.setId( 43 );
        notCookyTestersRecipe.setAuthor( notCookyTesterDummy );
        dummyList.add( notCookyTestersRecipe );

        List<Integer> dummyIds = dummyList.stream().map( dummy -> dummy.getId() ).collect( Collectors.toList() );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.addRecipesToCookbook( 1, dummyIds );

        // assert
    }

    @Test
    public void addRecipesToNonexistantCookbook() throws Exception {
        thrown.expect( InvalidCookbookId.class );

        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "CookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 3, userDummy );

        List<Integer> dummyIds = dummyList.stream().map( dummy -> dummy.getId() ).collect( Collectors.toList() );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.addRecipesToCookbook( 2, dummyIds );

        // assert
    }

    @Test
    public void addOneNonexistingOutOfMultipleRecipesToCookbook() throws Exception {
        thrown.expect( InvalidRecipeId.class );

        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "CookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 3, userDummy );
        List<Integer> dummyIds = dummyList.stream().map( dummy -> dummy.getId() ).collect( Collectors.toList() );
        dummyIds.add( 404 );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.addRecipesToCookbook( 1, dummyIds );

        // assert
    }

    @Test
    public void removeRecipeFromCookbook() throws Exception {
        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "CookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 5, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        cookbookUnderTest.getRecipes().addAll( dummyList );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.removeRecipeFromCookbook( cookbookUnderTest.getId(), 3 );

        // assert
        Assert.assertEquals( dummyList.size() - 1, this.recipeRepositoryMock.getEntities().size() );

        for ( CookbookEntity entity : this.cookbookRepositoryMock.getEntities() ) {
            if ( entity.getId().equals( 3 ) ) {
                Assert.fail();
            }
        }
    }

    @Test
    public void removeRecipeFromCookbookAsUnauthorizedUser() throws Exception {
        thrown.expect( UserNotAuthorized.class );

        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "NotCookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 5, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        cookbookUnderTest.getRecipes().addAll( dummyList );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.removeRecipeFromCookbook( cookbookUnderTest.getId(), 3 );

        // assert
    }

    @Test
    public void removeRecipeFromNonexistingCookbook() throws Exception {
        thrown.expect( InvalidCookbookId.class );

        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "CookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 5, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        cookbookUnderTest.getRecipes().addAll( dummyList );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.removeRecipeFromCookbook( cookbookUnderTest.getId() + 1, 3 );

        // assert
    }

    @Test
    public void removeNonexistingRecipeFromCookbook() throws Exception {
        thrown.expect( InvalidRecipeId.class );

        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "CookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 5, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        cookbookUnderTest.getRecipes().addAll( dummyList );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.removeRecipeFromCookbook( cookbookUnderTest.getId(), 404 );

        // assert
    }

    @Test
    public void removeAllRecipesFromCookbook() throws Exception {
        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "CookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 5, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        cookbookUnderTest.getRecipes().addAll( dummyList );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.removeAllRecipesFromCookbook( cookbookUnderTest.getId() );

        // assert
        Assert.assertEquals( 0, this.cookbookRepositoryMock.getEntities().get( 0 ).getRecipes().size() );
    }

    @Test
    public void removeAllRecipesFromCookbookAsUnauthorizedUser() throws Exception {
        thrown.expect( UserNotAuthorized.class );

        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "NotCookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 5, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        cookbookUnderTest.getRecipes().addAll( dummyList );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.removeAllRecipesFromCookbook( cookbookUnderTest.getId() );

        // assert
    }

    @Test
    public void removeAllRecipesFromNonexistingCookbook() throws Exception {
        thrown.expect( InvalidCookbookId.class );

        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "CookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 5, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity cookbookUnderTest = new CookbookEntity();
        cookbookUnderTest.setId( 1 );
        cookbookUnderTest.setOwner( userDummy );
        cookbookUnderTest.setOwnerId( userDummy.getId() );
        cookbookUnderTest.getRecipes().addAll( dummyList );
        this.cookbookRepositoryMock.addEntitiy( cookbookUnderTest );

        // act
        this.contentService.removeAllRecipesFromCookbook( cookbookUnderTest.getId() + 1 );

        // assert
    }

    @Test
    public void moveRecipeBetweenCookbooks() throws Exception {
        // arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 42 );
        userDummy.setUsername( "CookyTester" );

        List<RecipeEntity> dummyList = createRecipeDummyList( 3, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        CookbookEntity sourceCookbook = new CookbookEntity();
        sourceCookbook.setId( 1 );
        sourceCookbook.setOwner( userDummy );
        sourceCookbook.setOwnerId( userDummy.getId() );
        sourceCookbook.getRecipes().addAll( dummyList );
        CookbookEntity targetCookbook = new CookbookEntity();
        targetCookbook.setId( 2 );
        targetCookbook.setOwner( userDummy );
        targetCookbook.setOwnerId( userDummy.getId() );

        this.cookbookRepositoryMock.addEntitiy( sourceCookbook );
        this.cookbookRepositoryMock.addEntitiy( targetCookbook );

        int targetCookbookRecipeCount = targetCookbook.getRecipes().size();

        // act
        this.contentService.moveRecipeBetweenCookbooks( 1, sourceCookbook.getId(), targetCookbook.getId() );

        // assert
        Assert.assertEquals( targetCookbookRecipeCount + 1, this.cookbookRepositoryMock.getEntities().get( 1 ).getRecipes().size() );

        for ( RecipeEntity entity : this.cookbookRepositoryMock.getEntities().get( 0 ).getRecipes() ) {
            if ( entity.getId() == 1 ) {
                Assert.fail();
            }
        }

        boolean containsNewRecipe = false;
        for ( RecipeEntity entity : this.cookbookRepositoryMock.getEntities().get( 1 ).getRecipes() ) {
            containsNewRecipe |= entity.getId() == 1;
        }
        Assert.assertTrue( containsNewRecipe );
    }

    private ArrayList<RecipeEntity> createRecipeDummyList( int count, UserEntity author ) {
        ArrayList<RecipeEntity> resultList = new ArrayList<>( count );

        for ( int i = 0; i < count; i++ ) {
            RecipeEntity dummy = new RecipeEntity();
            dummy.setId( i );
            dummy.setAuthor( author );
            resultList.add( dummy );
        }

        return resultList;
    }
}