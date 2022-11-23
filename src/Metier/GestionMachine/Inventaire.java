package Metier.GestionMachine;

import BaseDeDonnees.Session;
import Metier.GestionLocation.BluRay;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO: Tester

/**
 * Gestion des stocks de BluRays disponibles physiquement dans la machine.
 * Directement reliée à la BD, met à jour la table LesBluRays de manière
 * à maintenir la liste des disques présents dans la machine lors des
 * locations et des rendus.
 * Une session à la BD est donc ouverte à chaque ajout de location, et
 * lors de rendus de disques, en utilisant le DAO de BluRay.
 * @author Geoffrey DAVID
 * @version 0
 */
@SuppressWarnings("unused")
public class Inventaire {

    private final List<BluRay> liste_BluRays;

    private Session session;

    private final int idMachineAssocie;

    public Inventaire(int idMachineAssociee){
        this.idMachineAssocie = idMachineAssociee;
        liste_BluRays = new ArrayList<>();
    }

    private Session getSession(){
        if (session == null){
            session = new Session();
        }
        session.open();
        return session;
    }

    /**
     * Utilisé lors du rendu d'un BLuRay après une location, cette fonction indique dans la base de données que
     * le BluRay rendu se situe dans la machine identifiée par idMachineAssociée (attribut de la classe).
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
        getSession().close();
    }

    public void supprimerBluRay(BluRay b){
        // TODO
    }

    public List<BluRay> getListeBluRays(){
        return liste_BluRays;
    }
}
