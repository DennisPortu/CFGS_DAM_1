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
@Table (name = "clans")

public class clan { // Aqui creem la classe clan
    
    // Aqui posem que volem una id PK que es generi automaticament amb el nom id_clan
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    protected int id_clan;
     
    // Aqui posem que volem una columna basica amb el nom "nom" una allargada de 50 caracters i tipus string
    @Basic
    @Column (name = "nom", length = 50, nullable = true)
    protected String nom;
    
    // Aqui posem que volem una columna basica amb el nom "nivell" una allargada de 50 caracters i tipus int
    @Basic
    @Column (name = "nivell", length = 50, nullable = true)
    protected int nivell;
    
    // Aqui posem que volem una columna basica amb el nom "copes" una allargada de 50 caracters i tipus int
    @Basic
    @Column (name = "copes", length = 50, nullable = true)
    protected int copes;
    
    // Aqui posem que volem una columna basica amb el nom "jugador admin" i tipus int
    @Basic
    @Column (name = "jugadorAdmin", nullable = true)
    protected int jugadorAdmin;
    
    // Aqui posem que volem una columna basica amb el nom "tipus" i tipus string
    @Basic
    @Column (name = "tipus", nullable = true)
    protected String tipus;

    // Aqui li diem al POJO que les anotacións volem que tingin el nom que volem nosaltres
    public clan (String nom, int nivell, int copes, int jugadorAdmin, String tipus) {
        this.nom = nom;
        this.nivell = nivell;
        this.copes = copes;
        this.jugadorAdmin = jugadorAdmin;
        this.tipus = tipus;
    }

    // Y aqui creem els constructors
    // Comencem amb el constructor per defecte
    public clan() {
    }
    
    // Aqui tenim els getId_clan i setID_clan
    public int getId_clan() {
        return id_clan;
    } public void setId_clan(int id_clan) {
        this.id_clan = id_clan;
    }

    // Aqui tenim els get_nom i set_nom
    public String get_nom() {
        return nom;
    } public void set_nom(String nom) {
        this.nom = nom;
    }
    
    // Aqui tenim els get_nivell i set_nivell
    public int get_nivell() {
        return nivell;
    } public void set_nivell(int nivell) {
        this.nivell = nivell;
    }
    
    // Aqui tenim els get_copes i set_copes
    public int get_copes() {
        return copes;
    } public void set_copes(int copes) {
        this.copes = copes;
    }
    
    // Aqui tenim els get_jugadorAdmin i set_jugadorAdmin
    public int get_jugadorAdmin() {
        return jugadorAdmin;
    } public void set_jugadorAdmin(int jugadorAdmin) {
        this.jugadorAdmin = jugadorAdmin;
    }
    
    // Aqui tenim els get_tipus i set_tipus
    public String get_tipus() {
        return tipus;
    } public void set_tipus(String tipus) {
        this.tipus = tipus;
    }
     
    // I per ultim tenim el format que volem cuan cridem la funcio mostrarClan
    public void mostrarClan()
    {
        System.out.println("ID_Clan: " + this.id_clan);
        System.out.println("Nom: " + this.nom);
        System.out.println("Nivell: " + this.nivell);
        System.err.println("Copes: " + this.copes);
        System.out.println("Jugador Admin: " + this.jugadorAdmin);
        System.out.println("Tipus: " + this.tipus);
    }   
}
