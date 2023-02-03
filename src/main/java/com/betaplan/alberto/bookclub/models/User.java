package com.betaplan.alberto.bookclub.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    // //// VARIABLES /////
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "¡Se requiere Username!")
    @Size(min = 3, max = 30, message = "Username debe tener entre 3 y 30 caracteres")
    private String userName;

    @NotEmpty(message = "¡Se requiere Email!")
    @Email(message = "¡Ingrese un Email válido!")
    private String email;

    @NotEmpty(message = "¡Se requiere contraseña!")
    @Size(min = 8, max = 128, message = "La contraseña debe tener entre 8 y 128 caracteres")
    private String password;

    @Transient
    @NotEmpty(message = "Se requiere confirmar la contraseña!")
    @Size(min = 8, max = 128, message = "La confirmacion de la contraseña debe tener entre 8 y 128 caracteres")
    private String confirm;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Book> books;

    // //// CONTRUCTOR ////
    public User() {
    }
    // //// GETTERS AND SETTERS ////
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }



}
