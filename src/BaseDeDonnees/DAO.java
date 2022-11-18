package BaseDeDonnees;

import java.sql.Connection;
/**
 * Classe abstraite mettant en œuvre toutes les méthodes CRUD :
 *
 * @author Geoffrey DAVID
 * @author Armand GRENIER
 * @version 0
 */
public abstract class DAO<T> {
    protected Connection connect = null;
    public DAO(Connection conn){ this.connect = conn; }

    public abstract boolean crer(T obj);

    public abstract T lire(int id);

    public abstract boolean modifier(T obj);

    public abstract boolean supprimer(T obj);

}
