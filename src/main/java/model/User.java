/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author m.perot
 */
public class User {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String identifiant;
    private String password;

    public User(String nom, String prenom, String email, String identifiant, String password) {

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.identifiant = identifiant;
        this.password = password;
    }

    public User(int id, String nom, String prenom, String email, String identifiant, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.identifiant = identifiant;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return this.email;
    }

    public void setMail(String mail) {
        this.email = mail;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdentifiant() {
        return this.identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
