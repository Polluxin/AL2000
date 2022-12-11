package Metier.GestionMachine;

import BaseDeDonnees.DAOs.BluRayDAO;
import BaseDeDonnees.Session;
import Metier.GestionLocation.BluRay;
import Metier.GestionLocation.Film;
import Metier.GestionLocation.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO: Tester

/**
 * Gestion des stocks de BluRays disponibles physiquement dans la machine.
 * Directement reliée à la BD, met à jour la table LesStocks de manière
 * à maintenir la liste des disques présents dans la machine lors des
 * locations et des rendus.
 * Une session à la BD est donc ouverte à chaque ajout de location, et
 * lors de rendus de disques.
 * @author Geoffrey DAVID
 */
@SuppressWarnings("unused")
public class Inventaire {

    private final List<BluRay> liste_BluRays;

    private Session session;

    private final int idMachineAssocie;

    public Inventaire(int idMachineAssociee, Session s){
        session = s;
        this.idMachineAssocie = idMachineAssociee;
        liste_BluRays = new ArrayList<>();
    }

    private Session getSession(){
        if (session == null){
            session = new Session();
        }
        return session;
    }

    /**
     * Initialise l'inventaire en mettant à jour la liste des BluRays dans la machine. En cas réél, cette fonction
     * identifie juste tout les BluRays présents physiquement, mais ici, elle consulte la BD pour assurer la cohérence
     * des données.
     */
    public void initialiser(){
        BluRayDAO dao = new BluRayDAO(session.getSession());
        try{
            ResultSet res = getSession().getSession().createStatement().executeQuery("SELECT IDBLURAY FROM LESSTOCKS WHERE IDMACHINE="+idMachineAssocie);
            while (res.next()){
                liste_BluRays.add(dao.lire(res.getInt("IdBluRay")));
            }
            System.out.println("Inventaire initialisé : "+this);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Echec dans l'initialisation de l'inventaire");
        }
    }

    /**
     * Utilisé lors du rendu d'un BLuRay après une location, cette fonction indique dans la base de données que
     * le BluRay rendu se situe dans la machine identifiée par idMachineAssociée (attribut de la classe), et met à jour
     * l'inventaire local.
     * @param b le BluRay rendu
     */
    public void ajouterBluRay(BluRay b){
        ajouterBluRayBD(b.getId(), idMachineAssocie);
        liste_BluRays.add(b);
    }

    /**
     * Fonction qui ajoute le BluRay dans la table LesStocks en l'associant à une machine.
     * @param idBluRay l'id du BluRay à ajouter
     * @param idMachineAssocie l'id de la machine qui contient le BluRay
     */
    private void ajouterBluRayBD(int idBluRay, int idMachineAssocie){
        // TODO A TESTER
        getSession().open();
        String requete = "INSERT INTO LESSTOCKS VALUES("+idBluRay+", "+idMachineAssocie+")";
        try{
            getSession().getSession().createStatement().executeUpdate(requete);
            System.out.println("[BD] BluRay numéro "+idBluRay+" ajouté");
        } catch (SQLException e){
            e.printStackTrace();
        }
        getSession().commit();
        getSession().close();
    }

    /**
     * Utilisé lors d'une location d'un BluRay, cette fonction indique dans la base de données que le BluRay ne se
     * situe plus dans la machine, et met à jour l'inventaire local.
     * @param b le BluRay loué
     */
    public void supprimerBluRay(BluRay b){
        supprimerBluRayBD(b.getId());
        liste_BluRays.remove(b);
    }

    /**
     * Fonction qui retire le BluRay dans la table LesStocks.
     * @param idBluRay l'id du BluRay à retirer
     */
    private void supprimerBluRayBD(int idBluRay){
        // TODO A TESTER
        getSession().open();
        String requete = "DELETE FROM LESSTOCKS WHERE idBluRay="+idBluRay;
        try{
            getSession().getSession().createStatement().executeUpdate(requete);
            System.out.println("[BD] BluRay numéro "+idBluRay+" retiré");
        } catch (SQLException e){
            e.printStackTrace();
        }
        getSession().commit();
        getSession().close();
    }

    public List<BluRay> getListeBluRays(){
        return liste_BluRays;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{ ");
        for (BluRay b: liste_BluRays){
            str.append(b.getId());
            str.append(" ");
        }
        str.append("}");
        return str.toString();
    }

    public BluRay chercherBluRay(Film f){
        for (BluRay bluray : liste_BluRays){
            if (bluray.getFilm() == f) {
                return bluray;
            }
        }
        return null;
    }
}
