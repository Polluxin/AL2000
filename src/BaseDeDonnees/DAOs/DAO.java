package BaseDeDonnees.DAOs;

import java.sql.Connection;
/**
 * Classe abstraite mettant en œuvre toutes les méthodes CRUD :
 *
 * @author Geoffrey DAVID
 * @author Armand GRENIER
 * @version 0
 */
public abstract class DAO<T> {
    protected Connection connect;
    public DAO(Connection conn){ this.connect = conn; }

    public boolean creer(T obj){
        return false;
    }

    public T lire(int id){
        return null;
    }

    public boolean modifier(T obj){
        return false;
    }

    public boolean supprimer(T obj){
        return false;
    }

}
