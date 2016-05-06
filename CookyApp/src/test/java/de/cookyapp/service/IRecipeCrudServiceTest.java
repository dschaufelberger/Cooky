package de.cookyapp.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.service.dto.Recipe;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.mocks.AuthenticationMock;
import de.cookyapp.service.mocks.RecipeRepositoryMock;
import de.cookyapp.service.mocks.UserRepositoryMock;
import de.cookyapp.service.services.RecipeCrudService;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Dominik Schaufelberger on 25.04.2016.
 */
public class IRecipeCrudServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private RecipeRepositoryMock recipeRepositoryMock;
    private UserRepositoryMock userRepositoryMock;
    private AuthenticationMock authenticationMock;
    private IRecipeCrudService service;

    @Before
    public void setUp() throws Exception {
        recipeRepositoryMock = new RecipeRepositoryMock();
        authenticationMock = new AuthenticationMock( "CookyTester" );
        this.userRepositoryMock = new UserRepositoryMock();
        this.service = new RecipeCrudService( recipeRepositoryMock, authenticationMock, userRepositoryMock );
    }

    @Test
    public void testDeleteExistingRecipe() throws Exception {
        // arrange
        RecipeEntity entityUnderTest = new RecipeEntity();
        entityUnderTest.setId( 3 );

        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 1 );
        userDummy.setUsername( "CookyTester" );
        entityUnderTest.setAuthor( userDummy );

        List<RecipeEntity> dummyList = this.createDummyList( 10, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        // act
        this.service.deleteRecipe( entityUnderTest.getId() );


        // assert
        RecipeEntity dummy = this.recipeRepositoryMock.findOne( entityUnderTest.getId() );
        Assert.assertNull( dummy );
        Assert.assertEquals( dummyList.size() - 1, this.recipeRepositoryMock.getEntities().size() );
    }

    @Test
    public void testDeleteRecipeByUnauthorizedUser() {
        // arrange
        RecipeEntity entityUnderTest = new RecipeEntity();
        entityUnderTest.setId( 3 );

        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 1 );
        userDummy.setUsername( "NotCookyTester" );
        entityUnderTest.setAuthor( userDummy );

        List<RecipeEntity> dummyList = createDummyList( 10, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        // act
        this.service.deleteRecipe( entityUnderTest.getId() );

        // assert
        RecipeEntity dummy = this.recipeRepositoryMock.findOne( entityUnderTest.getId() );
        Assert.assertNotNull( dummy );
        Assert.assertEquals( dummyList.size(), this.recipeRepositoryMock.getEntities().size() );
    }

    @Test
    public void testDeleteNonexistingRecipe() throws Exception {
        //arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 1 );
        userDummy.setUsername( "CookyTester" );

        // create a dummy user since the service tests the authorization of the user against the authors username
        List<RecipeEntity> dummyList = createDummyList( 10, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        //act
        this.service.deleteRecipe( 11 );

        //assert
        Assert.assertEquals( dummyList.size(), this.recipeRepositoryMock.getEntities().size() );
    }

    @Test
    public void testDeleteNonexistingRecipeFromEmptyRepository() throws Exception {
        this.service.deleteRecipe( 5 );
    }

    @Test
    public void testCreateRecipe() throws Exception {
        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 1 );
        userDummy.setUsername( "CookyTester" );
        LinkedList<UserEntity> userDummyList = new LinkedList<>();
        userDummyList.add( userDummy );
        this.userRepositoryMock.setUsers( userDummyList );

        //create Dummy Recipe List
        List<RecipeEntity> dummyList = this.createDummyList( 10, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        //initialize Recipe
        Recipe newRecipe = new Recipe();
        newRecipe.setAuthor( new User( userDummy ) );
        // act
        this.service.createRecipe( newRecipe );

        // assert
        Assert.assertEquals( dummyList.size() + 1, this.recipeRepositoryMock.getEntities().size() );
    }

    @Test
    public void testUpdateRecipe() throws Exception {
        // arrange
        RecipeEntity newRecipeEntity = new RecipeEntity();
        newRecipeEntity.setId( 10 );

        List<RecipeEntity> recipeEntities = new ArrayList<>();
        recipeEntities.add( newRecipeEntity );

        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 1 );
        userDummy.setUsername( "CookyTester" );
        newRecipeEntity.setAuthor( userDummy );

        //initalize Recipe
        Recipe newRecipe = new Recipe();
        newRecipe.setId( newRecipeEntity.getId() );
        newRecipe.setAuthor( new User( newRecipeEntity.getAuthor() ) );

        //act
        this.recipeRepositoryMock.setEntities( recipeEntities );
        //this.recipeRepositoryMock.save( newRecipeEntity );
        newRecipe.setShortDescription( "UpdateTest" );
        this.service.updateRecipe( newRecipe );

        Assert.assertEquals( newRecipe.getShortDescription(), this.recipeRepositoryMock.findOne( 10 ).getShortDescription() );
    }

    @Test
    public void testGetRecipe() throws Exception {
        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 1 );
        userDummy.setUsername( "CookyTester" );

        //create Dummy Recipe List
        List<RecipeEntity> dummyList = this.createDummyList( 10, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        Recipe recipe = this.service.getRecipe( 5 );
        Assert.assertEquals( recipe.getId(), 5 );
    }

    @Test
    public void testGetAllRecipes() throws Exception {
        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 1 );
        userDummy.setUsername( "CookyTester" );

        //create Dummy Recipe List
        List<RecipeEntity> dummyList = this.createDummyList( 10, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );
        Assert.assertEquals( this.service.getAllRecipes().size(), dummyList.size() );
    }

    @Test
    public void testGetAllRecipesByName() throws Exception {
        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 1 );
        userDummy.setUsername( "CookyTester" );

        //create Dummy Recipe List
        List<RecipeEntity> dummyList = this.createDummyListWithUniqueNames( 10, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );
        Assert.assertEquals( 1, this.service.getAllRecipesByName( "CookyTest1" ).size() );
    }

    @Test
    public void testSearchRecipesContaining() throws Exception {
        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setId( 1 );
        userDummy.setUsername( "CookyTester" );

        //create Dummy Recipe List
        List<RecipeEntity> dummyList = this.createDummyList( 10, userDummy );
        this.recipeRepositoryMock.setEntities( dummyList );

        Assert.assertEquals( this.service.searchRecipesContaining( "Cooky" ).size(), dummyList.size() );
        Assert.assertEquals( this.service.searchRecipesContaining( "kyT" ).size(), dummyList.size() );
        Assert.assertEquals( this.service.searchRecipesContaining( "Test" ).size(), dummyList.size() );
    }

    private List<RecipeEntity> createDummyList( int numberOfRecipes, UserEntity dummyAuthor ) {
        List<RecipeEntity> dummyList = new ArrayList<>();
        for ( int i = 1; i <= numberOfRecipes; i++ ) {
            RecipeEntity dummy = new RecipeEntity();
            dummy.setId( i );
            dummy.setAuthor( dummyAuthor );
            dummy.setName( "CookyTest" );
            dummyList.add( dummy );
        }
        return dummyList;
    }

    private List<RecipeEntity> createDummyListWithUniqueNames( int numberOfRecipes, UserEntity dummyAuthor ) {
        List<RecipeEntity> dummyList = new ArrayList<>();
        for ( int i = 1; i <= numberOfRecipes; i++ ) {
            RecipeEntity dummy = new RecipeEntity();
            dummy.setId( i );
            dummy.setAuthor( dummyAuthor );
            dummy.setName( "CookyTest" + i );
            dummyList.add( dummy );
        }
        return dummyList;
    }
}