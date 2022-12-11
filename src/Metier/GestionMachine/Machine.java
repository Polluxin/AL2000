package Metier.GestionMachine;

import BaseDeDonnees.DAOs.BluRayDAO;
import BaseDeDonnees.DAOs.CarteAboDAO;
import BaseDeDonnees.DAOs.TechnicienDAO;
import BaseDeDonnees.Session;
import Metier.Exception.BluRayInvalide;
import Metier.Exception.CarteIllisible;
import Metier.Exception.ConnexionImpossible;
import Metier.GestionClient.CB;
import Metier.GestionClient.CarteAbo;
import Metier.GestionLocation.BluRay;
import Metier.GestionLocation.Film;
import Metier.GestionLocation.QrCode;
import Metier.GestionLocation.Support;

import java.util.List;

/**
 * Infrastructure physique du distributeur de films.
 * @author Geoffrey DAVID
 * @version 0
 */
@SuppressWarnings("RedundantThrows")
public class Machine implements Distributeur, Maintenance {

    Inventaire inventaire;

    Statistiques statistiques;

    Session bd;

    public Machine(Inventaire i, Statistiques s, Session contactBD){
        bd = contactBD;
        inventaire = i;
        statistiques = s;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    @Override
    public CB lireCB(String infosCarte) throws CarteIllisible {
        // TODO A TESTER
        // Rappel format des infos de la carte: "5341 2154 2225 4448-04 25-Paul Fort-888-"
        String[] infos = infosCarte.split("-");
        System.out.println("-> Infos de la carte lue dans le lecteur: ");
        System.out.println("- Numéro : "+infos[0]);
        System.out.println("- Date d'expiration : "+infos[1]);
        System.out.println("- Nom et prénom : "+infos[2]);
        System.out.println("- Cryptogramme : "+infos[3]);
        if (!infos[0].matches("(\\d{4} ){3}\\d{4}"))
            throw new CarteIllisible("Numéro invalide");
        if (!infos[1].matches("\\d{2} \\d{2}"))
            throw new CarteIllisible("Date d'expiration invalide");
        if (!infos[2].matches("(\\W|^)\\w+\\s(\\w)+(\\W|$)"))
            throw new CarteIllisible("Nom et prénom invalides");
        if (!infos[3].matches("^\\d{3}"))
            throw new CarteIllisible("Cryptogramme invalide");
        String[] nomprenom = infos[2].split(" ");
        return new CB(nomprenom[0], nomprenom[1], infosCarte);
    }

    @Override
    public Technicien lireCTechnicien(String id) throws CarteIllisible, ConnexionImpossible {
        // TODO A TESTER
        if (!id.matches("^\\d+$"))
            throw new CarteIllisible("Format identifiant incorrect");
        bd.open();
        TechnicienDAO dao = new TechnicienDAO(bd.getSession());
        Technicien t = dao.lire(Integer.parseInt(id));
        bd.close();
        if (t == null)
            throw new ConnexionImpossible("Erreur d'identification du technicien");
        return t;
    }

    @Override
    public CarteAbo lireCarteAbo(String id) throws CarteIllisible, ConnexionImpossible {
        // TODO A TESTER
        if (!id.matches("^\\d+$"))
            throw new CarteIllisible("Format identifiant incorrect");
        bd.open();
        CarteAboDAO dao = new CarteAboDAO(bd.getSession());
        CarteAbo c = dao.lire(Integer.parseInt(id));
        bd.close();
        if (c == null)
            throw new ConnexionImpossible("Erreur d'identification d' l'abonné");
        return c;
    }

    @Override
    public void livrerFilms(List<Support> l) {
        for (Support f: l){
            f.sortirFilm(this);
        }
        System.out.println("-> "+l.size()+" films ont été livrés");
    }

    @Override
    public void livrerBluRay(BluRay b) {
        // retrait du BluRay de l'inventaire
        inventaire.supprimerBluRay(b);
        System.out.println("-> Livraison du BluRay de '"+b.getFilm()+"'");
    }

    @Override
    public void imprimerQRCode(QrCode qr) {
        System.out.println("-> Impression du QR Code de '"+qr.getFilm()+"'");
    }

    @Override
    public void avalerBluRay(String id) throws BluRayInvalide {
        // TODO A TESTER
        System.out.print("-> Authentification du BluRay ");
        try {
            int lu = Integer.parseInt(id);
            if (lu == 0) throw new BluRayInvalide();
            System.out.println("numéro "+lu+": OK");
            // Ajout du BluRay dans l'inventaire
            bd.open();
            BluRayDAO dao = new BluRayDAO(bd.getSession());
            BluRay b = dao.lire(lu);
            bd.close();
            inventaire.ajouterBluRay(b);
        } catch (BluRayInvalide e) {
            throw new BluRayInvalide();
        }
    }

    @Override
    public void ouvrir() {
        System.out.println("-> Trappe de la machine ouverte");
        statistiques.ajouterOuverture();
    }

    @Override
    public void fermer() {
        System.out.println("-> Trappe de la machine fermée");
    }

    @Override
    public Statistiques voirStatistiques() {
        return statistiques;
    }

    @Override
    public Inventaire donnerInventaire() {
        return inventaire;
    }

    public BluRay chercherBluRay(Film f) {
            return inventaire.chercherBluRay(f);
    }
}
