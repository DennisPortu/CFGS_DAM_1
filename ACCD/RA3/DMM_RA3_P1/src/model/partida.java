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
@Table (name = "partidas")

public class partida {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    protected int id_partida;
     
    @Basic
    @Column (name = "idjug1", length = 50, nullable = true)
    protected int idjug1;
    
    @Basic
    @Column (name = "idjug2", length = 50, nullable = true)
    protected int idjug2;
    
    @Basic
    @Column (name = "resultat", length = 50, nullable = true)
    protected String resultat;
    
    @Basic
    @Column (name = "temps", nullable = true)
    protected String temps;
    
    @Basic
    @Column (name = "tipus", nullable = true)
    protected String tipus;

    public partida(int idjug1, int idjug2, String resultat, String temps, String tipus) {
        this.idjug1 = idjug1;
        this.idjug2 = idjug2;
        this.resultat = resultat;
        this.temps = temps;
        this.tipus = tipus;
    }

     public partida() {
    }
    
    public int getId_partida() {
        return id_partida;
    } public void setId_partida(int id_partida) {
        this.id_partida = id_partida;
    }

    public int getIdjug1() {
        return idjug1;
    } public void setIdjug1(int idjug1) {
        this.idjug1 = idjug1;
    }
    
    public int getIdjug2() {
        return idjug2;
    } public void setIdjug2(int idjug2) {
        this.idjug2 = idjug2;
    }
    
    public String getTemps() {
        return temps;
    } public void setTemps(String temps) {
        this.temps = temps;
    }
    
    public String getResultat() {
        return resultat;
    } public void setResultat(String resultat) {
        this.resultat = resultat;
    }
    
    public String getTipus() {
        return tipus;
    } public void setTipus(String tipus) {
        this.tipus = tipus;
    }
    
    public void mostrarPartida()
    {
        System.out.println("ID_Partida: " + this.id_partida);
        System.out.println("Id_Jug_1: " + this.idjug1);
        System.out.println("Id_Jug_2: " + this.idjug2);
        System.err.println("Resultat: " + this.resultat);
        System.out.println("Temps: " + this.temps);
        System.out.println("Tipus: " + this.tipus);
    }
    
   
}
