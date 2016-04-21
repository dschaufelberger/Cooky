package de.cookyapp.service.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import de.cookyapp.enums.AccountState;
import de.cookyapp.enums.Gender;
import de.cookyapp.persistence.entities.UserEntity;

/**
 * Created by Dominik Schaufelberger on 03.04.2016.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String forename;
    private String surname;
    private String email;
    private Gender gender;
    private LocalDate birthdate;
    private LocalDateTime registrationDate;
    private LocalDateTime lastLoginDate;
    private AccountState accountState;

    public User() {
    }

    public User( UserEntity userEntity ) {
        setId( userEntity.getId() );
        setUsername( userEntity.getUsername() );
        setPassword( userEntity.getPassword() );
        setForename( userEntity.getForename() );
        setSurname( userEntity.getSurname() );
        setEmail( userEntity.getEmail() );
        setGender( userEntity.getGender() );
        setBirthdate( userEntity.getBirthdate() );
        setRegistrationDate( userEntity.getRegistrationDate() );
        setLastLoginDate( userEntity.getLastLoginDate() );
        setAccountState( userEntity.getAccountState() );
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }


    public String getForename() {
        return forename;
    }

    public void setForename( String forename ) {
        this.forename = forename;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname( String surname ) {
        this.surname = surname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender( Gender gender ) {
        this.gender = gender;
    }


    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate( LocalDate birthdate ) {
        this.birthdate = birthdate;
    }


    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate( LocalDateTime registrationDate ) {
        this.registrationDate = registrationDate;
    }


    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate( LocalDateTime lastLoginDate ) {
        this.lastLoginDate = lastLoginDate;
    }


    public AccountState getAccountState() {
        return accountState;
    }

    public void setAccountState( AccountState accountState ) {
        this.accountState = accountState;
    }

    
   /* public Collection<CookbookEntity> getCookbooks() {
        return cookbooks;
    }

    public void setCookbooks( Collection<CookbookEntity> cookbooks ) {
        this.cookbooks = cookbooks;
    }

    *//*
    
    public Collection<CommentEntity> getComments() {
        return comments;
    }

    public void setComments( Collection<CommentEntity> comments ) {
        this.comments = comments;
    }*//*

    
    public Collection<UserPreferenceEntity> getPreferences() {
        return preferences;
    }

    public void setPreferences( Collection<UserPreferenceEntity> preferences ) {
        this.preferences = preferences;
    }

    
    
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress( AddressEntity address ) {
        this.address = address;
    }

    
    public Collection<MessageEntity> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages( Collection<MessageEntity> sentMessages ) {
        this.sentMessages = sentMessages;
    }

    
    public Collection<MessageEntity> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages( Collection<MessageEntity> receivedMessages ) {
        this.receivedMessages = receivedMessages;
    }

    
    public Collection<ShoppingListEntity> getShoppingListEntries() {
        return shoppingListEntries;
    }

    public void setShoppingListEntries( Collection<ShoppingListEntity> shoppingListEntries ) {
        this.shoppingListEntries = shoppingListEntries;
    }

    
    public Collection<FriendshipEntity> getOutgoingFriendships() {
        return outgoingFriendships;
    }

    public void setOutgoingFriendships( Collection<FriendshipEntity> outgoingFriendships ) {
        this.outgoingFriendships = outgoingFriendships;
    }

    
    public Collection<FriendshipEntity> getIncomingFriendships() {
        return incomingFriendships;
    }

    public void setIncomingFriendships( Collection<FriendshipEntity> incomingFriendships ) {
        this.incomingFriendships = incomingFriendships;
    }*/

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        User that = (User) o;

        if ( id != that.id )
            return false;
        if ( username != null ? !username.equals( that.username ) : that.username != null )
            return false;
        if ( password != null ? !password.equals( that.password ) : that.password != null )
            return false;
        if ( forename != null ? !forename.equals( that.forename ) : that.forename != null )
            return false;
        if ( surname != null ? !surname.equals( that.surname ) : that.surname != null )
            return false;
        if ( email != null ? !email.equals( that.email ) : that.email != null )
            return false;
        if ( gender != null ? !gender.equals( that.gender ) : that.gender != null )
            return false;
        if ( birthdate != null ? !birthdate.equals( that.birthdate ) : that.birthdate != null )
            return false;
        if ( registrationDate != null ? !registrationDate.equals( that.registrationDate ) : that.registrationDate != null )
            return false;
        if ( lastLoginDate != null ? !lastLoginDate.equals( that.lastLoginDate ) : that.lastLoginDate != null )
            return false;
        if ( accountState != null ? !accountState.equals( that.accountState ) : that.accountState != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (forename != null ? forename.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (lastLoginDate != null ? lastLoginDate.hashCode() : 0);
        result = 31 * result + (accountState != null ? accountState.hashCode() : 0);
        return result;
    }
}
