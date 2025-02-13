/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author AluCiclesGS1
 */

@Entity
@Table (name = "jugadors")

public class jugador {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    protected int id;
    
    @Basic
    @Column (name = "nom", length = 50, nullable = true)
    protected String nom;
    
    @Basic
    @Column (name = "nivell", nullable = true)
    protected int nivell;
    
    @Basic
    @Column (name = "copes", nullable = true)
    protected int copes;
    
    @Basic
    @Column (name = "oro", nullable = true)
    protected int oro;
    
    @Basic
    @Column (name = "gemes", nullable = true)
    protected int gemes;
    
    @Basic
    @Column (name = "estrelles", nullable = true)
    protected int estrelles;

    public jugador(int id, String nom, int nivell, int copes, int oro, int gemes, int estrelles) {
        this.id = id;
        this.nom = nom;
        this.nivell = nivell;
        this.copes = copes;
        this.oro = oro;
        this.gemes = gemes;
        this.estrelles = estrelles;
    }

    public jugador() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNivell() {
        return nivell;
    }

    public void setNivell(int nivell) {
        this.nivell = nivell;
    }

    public int getCopes() {
        return copes;
    }

    public void setCopes(int copes) {
        this.copes = copes;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public int getGemes() {
        return gemes;
    }

    public void setGemes(int gemes) {
        this.gemes = gemes;
    }

    public int getEstrelles() {
        return estrelles;
    }

    public void setEstrelles(int estrelles) {
        this.estrelles = estrelles;
    }
    
    public void mostrarJugador()
    {
        System.out.println("ID: " + this.id);
        System.out.println("Nom: " + this.nom);
        System.out.println("Nivell: " + this.nivell);
        System.out.println("Copes: " + this.copes);
        System.out.println("Oro: " + this.oro);
        System.out.println("Gemes: " + this.gemes);
        System.out.println("Estrelles: " + this.estrelles);
    }
    
    public jugador(int id, String nom) 
    {
        this.id = id;
        this.nom = nom;
        this.nivell = 0;
        this.copes = 0;
        this.oro = 0;
        this.gemes = 0;
        this.estrelles = 0;
    }   
}
