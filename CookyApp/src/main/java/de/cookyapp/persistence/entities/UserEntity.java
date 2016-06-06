package de.cookyapp.persistence.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.cookyapp.enums.AccountState;
import de.cookyapp.enums.Gender;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "User", schema = "Cooky_Dev" )
public class UserEntity {
    private Integer id;
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

    private AddressEntity address;
    private Collection<CookbookEntity> cookbooks;
    /*private Collection<CommentEntity> comments;*/
    private Collection<UserPreferenceEntity> preferences;
    private Collection<MessageEntity> sentMessages;
    private Collection<MessageEntity> receivedMessages;
    private Collection<ShoppingListEntity> shoppingListEntries;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID", nullable = false )
    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }


    @Basic
    @Column( name = "Username", nullable = false, length = 30 )
    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    @Basic
    @Column( name = "Password", nullable = false, length = 32 )
    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    @Basic
    @Column( name = "Forename", nullable = false, length = 30 )
    public String getForename() {
        return forename;
    }

    public void setForename( String forename ) {
        this.forename = forename;
    }

    @Basic
    @Column( name = "Surname", nullable = false, length = 30 )
    public String getSurname() {
        return surname;
    }

    public void setSurname( String surname ) {
        this.surname = surname;
    }

    @Basic
    @Column( name = "Email", nullable = false, length = 50 )
    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    @Basic
    @Enumerated( EnumType.STRING )
    @Column( name = "Gender", nullable = true, length = 10 )
    public Gender getGender() {
        return gender;
    }

    public void setGender( Gender gender ) {
        this.gender = gender;
    }

    @Basic
    @Column( name = "Birthdate", nullable = true )
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate( LocalDate birthdate ) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column( name = "RegistrationDate", nullable = true )
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate( LocalDateTime registrationDate ) {
        this.registrationDate = registrationDate;
    }

    @Basic
    @Column( name = "LastLoginDate", nullable = true )
    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate( LocalDateTime lastLoginDate ) {
        this.lastLoginDate = lastLoginDate;
    }

    @Basic
    @Enumerated( EnumType.STRING )
    @Column( name = "AccountState", nullable = true, length = 10 )
    public AccountState getAccountState() {
        return accountState;
    }

    public void setAccountState( AccountState accountState ) {
        this.accountState = accountState;
    }

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "owner" )
    public Collection<CookbookEntity> getCookbooks() {
        return cookbooks;
    }

    public void setCookbooks( Collection<CookbookEntity> cookbooks ) {
        this.cookbooks = cookbooks;
    }

    /*
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "author" )
    public Collection<CommentEntity> getComments() {
        return comments;
    }

    public void setComments( Collection<CommentEntity> comments ) {
        this.comments = comments;
    }*/

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "user" )
    public Collection<UserPreferenceEntity> getPreferences() {
        return preferences;
    }

    public void setPreferences( Collection<UserPreferenceEntity> preferences ) {
        this.preferences = preferences;
    }

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn(name = "AddressID")
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress( AddressEntity address ) {
        this.address = address;
    }

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "sender" )
    public Collection<MessageEntity> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages( Collection<MessageEntity> sentMessages ) {
        this.sentMessages = sentMessages;
    }

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "receiver" )
    public Collection<MessageEntity> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages( Collection<MessageEntity> receivedMessages ) {
        this.receivedMessages = receivedMessages;
    }

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "owner" )
    public Collection<ShoppingListEntity> getShoppingListEntries() {
        return shoppingListEntries;
    }

    public void setShoppingListEntries( Collection<ShoppingListEntity> shoppingListEntries ) {
        this.shoppingListEntries = shoppingListEntries;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        UserEntity that = (UserEntity) o;

        return id.equals( that.id );

    }

    @Override
    public int hashCode() {
        return 31 * id;
    }
}
