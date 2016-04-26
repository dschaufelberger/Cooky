package de.cookyapp.service;

import java.util.ArrayList;
import java.util.List;

import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.service.dto.Recipe;
import de.cookyapp.service.exceptions.InvalidRecipeID;
import de.cookyapp.service.mocks.AuthenticationMock;
import de.cookyapp.service.mocks.RecipeRepositoryMock;
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

    private RecipeRepositoryMock repositoryMock;
    private AuthenticationMock authenticationMock;
    private IRecipeCrudService service;

    @Before
    public void setUp() throws Exception {
        repositoryMock = new RecipeRepositoryMock();
        authenticationMock = new AuthenticationMock( "CookyTester" );
        this.service = new RecipeCrudService( repositoryMock, authenticationMock );
    }

    @Test
    public void testDeleteExistingRecipe() throws Exception {
        // arrange
        RecipeEntity entityUnderTest = new RecipeEntity();
        entityUnderTest.setId( 3 );

        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setUsername( "CookyTester" );
        entityUnderTest.setAuthor( userDummy );

        List<RecipeEntity> dummyList = this.createDummyList( 10, userDummy );

        this.repositoryMock.setEntities( dummyList );

        // act
        this.service.deleteRecipe( entityUnderTest.getId() );


        // assert
        RecipeEntity dummy = this.repositoryMock.findOne( entityUnderTest.getId() );
        Assert.assertNull( dummy );
        Assert.assertEquals( dummyList.size() - 1, this.repositoryMock.getEntities().size() );
    }

    @Test
    public void testDeleteRecipeByUnauthorizedUser() {
        // arrange
        RecipeEntity entityUnderTest = new RecipeEntity();
        entityUnderTest.setId( 3 );

        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setUsername( "NotCookyTester" );
        entityUnderTest.setAuthor( userDummy );

        List<RecipeEntity> dummyList = createDummyList( 10, userDummy );
        this.repositoryMock.setEntities( dummyList );

        // act
        this.service.deleteRecipe( entityUnderTest.getId() );

        // assert
        RecipeEntity dummy = this.repositoryMock.findOne( entityUnderTest.getId() );
        Assert.assertNotNull( dummy );
        Assert.assertEquals( dummyList.size(), this.repositoryMock.getEntities().size() );
    }

    @Test
    public void testDeleteNonexistingRecipe() throws Exception {
        //arrange
        UserEntity userDummy = new UserEntity();
        userDummy.setUsername("CookyTester");

        //expected
        thrown.expect( InvalidRecipeID.class );
        thrown.expectMessage( "Recipe does not exist" );

        //act
        this.service.deleteRecipe(3);
    }

    @Test
    public void testDeleteExistingRecipeFromEmptyRepository() throws Exception {

    }

    @Test
    public void testDeleteNonexistingRecipeFromEmptyRepository() throws Exception {

    }

    @Test
    public void testCreateRecipe() throws Exception {
        // arrange
        RecipeEntity entityUnderTest = new RecipeEntity();
        entityUnderTest.setId( 5 );

        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setUsername( "CookyTester" );
        entityUnderTest.setAuthor( userDummy );

        //create Dummy Recipe List
        List<RecipeEntity> dummyList = this.createDummyList( 10, userDummy );

        this.repositoryMock.setEntities( dummyList );

        //initialize Recipe
        Recipe newRecipe = new Recipe();
        newRecipe.setId( entityUnderTest.getId() );
        newRecipe.setAuthor( entityUnderTest.getAuthor() );
        // act
        this.service.createRecipe( newRecipe );

        // assert
        RecipeEntity dummy = this.repositoryMock.findOne( entityUnderTest.getId() );
        Assert.assertNotNull( dummy );
        Assert.assertEquals( dummyList.size() + 1, this.repositoryMock.getEntities().size() );
    }

    @Test
    public void testUpdateRecipe() throws Exception {
        // arrange
        RecipeEntity newRecipeEntity = new RecipeEntity();
        newRecipeEntity.setId( 10 );

        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setUsername( "CookyTester" );
        newRecipeEntity.setAuthor( userDummy );

        //initalize Recipe
        Recipe newRecipe = new Recipe();
        newRecipe.setId( newRecipeEntity.getId() );
        newRecipe.setAuthor( newRecipeEntity.getAuthor() );

        //act
        this.repositoryMock.save( newRecipeEntity );
        newRecipe.setShortDescription( "UpdateTest" );
        this.service.updateRecipe( newRecipe );

        Assert.assertEquals( newRecipe.getShortDescription(), this.repositoryMock.findOne( 10 ).getShortDescription() );

    }

    @Test
    public void testGetRecipe() throws Exception {
        // arrange
        RecipeEntity newRecipeEntity = new RecipeEntity();
        newRecipeEntity.setId( 5 );

        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setUsername( "CookyTester" );
        newRecipeEntity.setAuthor( userDummy );

        //act
        this.repositoryMock.save( newRecipeEntity );
        Recipe recipe = this.service.getRecipe( 5 );

        //TODO: implement another comparison
        // NOT Recipe, RecipeEntity
        Assert.assertEquals( recipe, this.repositoryMock.findOne( 5 ) );
    }

    @Test
    public void testGetAllRecipes() throws Exception {
        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setUsername( "CookyTester" );

        //create Dummy Recipe List
        List<RecipeEntity> dummyList = this.createDummyList( 10, userDummy );

        this.repositoryMock.setEntities( dummyList );

        Assert.assertEquals( this.service.getAllRecipes().size(), dummyList.size() );
    }

    @Test
    public void testGetAllRecipesByName() throws Exception {
        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setUsername( "CookyTester" );

        //create Dummy Recipe List
        List<RecipeEntity> dummyList = this.createDummyList( 10, userDummy );

        Assert.assertEquals( this.service.getAllRecipesByName( "CookyTest" ).size(), this.repositoryMock.findByName( "CookyTest" ).size() );
    }

    @Test
    public void testSearchRecipesContaining() throws Exception {
        // create a dummy user since the service tests the authorization of the user against the authors username
        UserEntity userDummy = new UserEntity();
        userDummy.setUsername( "CookyTester" );

        //create Dummy Recipe List
        List<RecipeEntity> dummyList = this.createDummyList( 10, userDummy );

        Assert.assertEquals( this.service.searchRecipesContaining( userDummy.getUsername() ).size(), this.repositoryMock.findByNameLike( userDummy.getUsername() ).size() );
        //TODO Which fields is findByNameLike searching for? All Fields?
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
}