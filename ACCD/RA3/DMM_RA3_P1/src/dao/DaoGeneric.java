/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.jugador;
import model.partida;
import model.clan;
import org.hibernate.*;
import util.NewHibernateUtil;

/**
 *
 * @author Usuari
 */
public class DaoGeneric {
    
    public static DaoGeneric daoGeneric = null;
    
    private SessionFactory factory;
    private Transaction transaction;

    public DaoGeneric() {
        factory = NewHibernateUtil.getSessionFactory();
    }
    
    public static DaoGeneric getInstance(){
        if(daoGeneric==null){
            daoGeneric=new DaoGeneric();
        }
        return daoGeneric;
    }
    
    public boolean create(Object o){
        boolean retorn = false;
        Session sessio = factory.openSession();
        try{
            transaction = sessio.beginTransaction();
            sessio.save(o);
            transaction.commit();
            retorn=true;
            
        }
        catch (Exception e){
            transaction.rollback();
            retorn = false;
            e.printStackTrace();
        } finally{
            sessio.close();
        }
        return retorn;
    }
    public boolean deleteJugador(int id) {
       boolean retorn = false;
        Session sessio = factory.openSession();
        try{
            transaction = sessio.beginTransaction();
            
            jugador o = (jugador) sessio.load(jugador.class, id);
       
      //first load() method example
     
            sessio.delete(o);
            transaction.commit();
            retorn=true;
            
        }
        catch (Exception e){
            transaction.rollback();
            retorn = false;
            e.printStackTrace();
        } finally{
            sessio.close();
        }
        return retorn;
    }
    public List readJugador(){
        List result = null;
        try {
            Session sessio = factory.openSession();
            sessio.beginTransaction();
            Query q = sessio.createQuery("from jugador");
            result = (List) q.list();

            //sessio.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return result;
    }
    
    public boolean deletePartida(int id_partida) {
       boolean retorn = false;
        Session sessio = factory.openSession();
        try{
            transaction = sessio.beginTransaction();
            
            partida u = (partida) sessio.load(partida.class, id_partida);
       
      //first load() method example
     
            sessio.delete(u);
            transaction.commit();
            retorn=true;
            
        }
        catch (Exception ex){
            transaction.rollback();
            retorn = false;
            ex.printStackTrace();
        } finally{
            sessio.close();
        }
        return retorn;
    }
    public List readPartida(){
        List result = null;
        try {
            Session sessio = factory.openSession();
            sessio.beginTransaction();
            Query qu = sessio.createQuery("from partida");
            result = (List) qu.list();

            //sessio.getTransaction().commit();
        } catch (HibernateException hiex) {
            hiex.printStackTrace();
        }
        return result;
    }
    
    public boolean deleteClan(int id_clan) { // Aqui entrem a una funcio tipus boolean i ens demanen un enter de id_clan
       boolean retorn = false;
        Session sessio = factory.openSession();
        try{ // Obrim la sessio clan, borrem el clan i fem un commit
            transaction = sessio.beginTransaction();
            
            clan e = (clan) sessio.load(clan.class, id_clan);
       
            //first load() method example
     
            sessio.delete(e);
            transaction.commit();
            retorn=true;
            
        }
        catch (Exception exc){ // Si no pot fer el try ens donara una excepcio i tornara al ultim commit 
            transaction.rollback();
            retorn = false;
            exc.printStackTrace();
        } finally{ // I per ultim tenca la sessio
            sessio.close();
        }
        return retorn;
    }
    
    public List readClan(){ // Aqui entrem en una funcio tipus llista
        List result = null; // Borrem l'anterior llista
        try {  // I Obrim la sessio, fem una query "from clan" i ens dona la llista del resultat de la query
            Session sessio = factory.openSession();
            sessio.beginTransaction();
            Query que = sessio.createQuery("from clan");
            result = (List) que.list();

            //sessio.getTransaction().commit();
        } catch (HibernateException hibexc) { // Si no pot fer el try ens donara una excepcio
            hibexc.printStackTrace();
        }
        return result; // I per ultim retorna el resultat de la query
    }
}
