package BaseDeDonnees.DAOs;

import java.sql.Connection;
/**
 * Classe abstraite mettant en œuvre toutes les méthodes CRUD :
 *
 * @author Geoffrey DAVID
 * @author Armand GRENIER
 * @version 0
 */
@SuppressWarnings("unused")
public abstract class DAO<T> {
    protected Connection connect;
    public DAO(Connection conn){ this.connect = conn; }

    public boolean creer(T obj){
        System.out.println("!!! Non implémenté !!!");
        return false;
    }

    public T lire(int id){
        System.out.println("!!! Non implémenté !!!");
        return null;
    }

    public boolean modifier(T obj){
        System.out.println("!!! Non implémenté !!!");
        return false;
    }

    public boolean supprimer(T obj){
        System.out.println("!!! Non implémenté !!!");
        return false;
    }

}
