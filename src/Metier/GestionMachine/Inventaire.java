package Metier.GestionMachine;

import BaseDeDonnees.DAOs.BluRayDAO;
import BaseDeDonnees.Session;
import Metier.GestionLocation.BluRay;

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

    public void ajouterBluRays(List<BluRay> b){
        BluRayDAO dao = new BluRayDAO(getSession().getSession());
        dao.setIdMachineAssocie(idMachineAssocie);
        for (BluRay br: b){
            dao.creer(br);
            liste_BluRays.add(br);
        }
        session.close();
    }

    public void supprimerBluRay(BluRay b){
        BluRayDAO dao = new BluRayDAO(getSession().getSession());
        dao.setIdMachineAssocie(idMachineAssocie);
        liste_BluRays.remove(b);
        session.close();
    }

    public List<BluRay> getListeBluRays(){
        return liste_BluRays;
    }
}
