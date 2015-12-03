package de.cookyapp.persistence.entities;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "Comment", schema = "Cooky_Dev" )
public class CommentEntity {
    private int id;
    private String text;
    private Byte rating;
    private Integer authorId;
    private Integer recipeId;
    private LocalDateTime creationTime;

    private UserEntity author;
    private RecipeEntity commentedRecipe;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID", nullable = false )
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    @Basic
    @Column( name = "Text", nullable = true, length = 1500 )
    public String getText() {
        return text;
    }

    public void setText( String text ) {
        this.text = text;
    }

    @Basic
    @Column( name = "Rating", nullable = true )
    public Byte getRating() {
        return rating;
    }

    public void setRating( Byte rating ) {
        this.rating = rating;
    }

    @Basic
    @Column( name = "AuthorID", nullable = true )
    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId( Integer authorId ) {
        this.authorId = authorId;
    }

    @Basic
    @Column( name = "RecipeID", nullable = true )
    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId( Integer recipeId ) {
        this.recipeId = recipeId;
    }

    @Basic
    @Column( name = "CreationTime", nullable = true )
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime( LocalDateTime creationTime ) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        CommentEntity that = (CommentEntity) o;

        if ( id != that.id )
            return false;
        if ( text != null ? !text.equals( that.text ) : that.text != null )
            return false;
        if ( rating != null ? !rating.equals( that.rating ) : that.rating != null )
            return false;
        if ( authorId != null ? !authorId.equals( that.authorId ) : that.authorId != null )
            return false;
        if ( recipeId != null ? !recipeId.equals( that.recipeId ) : that.recipeId != null )
            return false;
        if ( creationTime != null ? !creationTime.equals( that.creationTime ) : that.creationTime != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
        result = 31 * result + (recipeId != null ? recipeId.hashCode() : 0);
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        return result;
    }

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false )
    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor( UserEntity author ) {
        this.author = author;
    }

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false )
    public RecipeEntity getCommentedRecipe() {
        return commentedRecipe;
    }

    public void setCommentedRecipe( RecipeEntity commentedRecipe ) {
        this.commentedRecipe = commentedRecipe;
    }
}
