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

    public abstract boolean creer(T obj);

    public abstract T lire(int id);

    public abstract boolean modifier(T obj);

    public abstract boolean supprimer(T obj);

}