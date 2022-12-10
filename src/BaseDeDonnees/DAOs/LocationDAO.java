package BaseDeDonnees.DAOs;

import Metier.GestionClient.Anonyme;
import Metier.GestionClient.Client;
import Metier.GestionLocation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO

@SuppressWarnings("unused")
public class LocationDAO extends DAO<Location>{

    int idMachine;

    public LocationDAO(Connection conn, int idMachine) {
        super(conn);
        this.idMachine = idMachine;
    }

    /**
     * Ajoute la location dans la BD.
     * Pré-conditions : Location.client.carte.id est correct.
     * @param loc la location à ajouter
     * @return vrai si la location a correctement été créée en BD.
     */
    @Override
    public boolean creer(Location loc) {
        assert(loc.getClient().getCarte().getId() > 0);
        // TODO A TESTER
        try{
          // Trouver l'id de la location
            ResultSet res = connect.createStatement().executeQuery(
                    "SELECT MAX(idlocation) FROM LESLOCATIONS");
            res.next();
            int idLoc = res.getInt("idcarte");
            // Ajouter la location selon son format
            int idSupport = loc.getSupport().getId();
            String table;
            Etat etat;
            int idAAjouter;
            if (idSupport == -1){     // QRCode
                table = "LESLOCATIONSQRCODE";
                etat = Etat.TERMINEE;
                // Recherche l'id du film à ajouter
                ResultSet resqr = connect.createStatement().executeQuery(
                        "SELECT IDFILM FROM LESFILMS WHERE TITRE='"+loc.getSupport().getFilm().getTitre()+"'");
                resqr.next();
                idAAjouter = res.getInt("idfilm");
            }
            else {                  // BluRay
                idAAjouter = idSupport;
                table = "LESLOCATIONSBLURAY";
                etat = Etat.ENCOURS;
            }
            // Ajout de la location
            connect.createStatement().executeUpdate(
                    "INSERT INTO LESLOCATIONS VALUES ("+idLoc+", current_date, "+etat
                    +", "+loc.getClient().getCarte().getId()+", "+idMachine+")");
            connect.createStatement().executeUpdate(
                    "INSERT INTO "+table+" VALUES ("+idLoc+", "+idAAjouter+")");
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Utilisé pour modifier l'état d'une location de BluRay.
     * Comme Location ne contient pas de numéro, la location est retrouvée
     * grâce au numéro de carte associé et la date de location.
     * Pré-condition : c'est une location de BluRay
     * @param obj la location
     * @return vrai si la modification est bien effectuée en BD
     */
    @Override
    public boolean modifier(Location obj) {
        // TODO A TESTER
        try{
            connect.createStatement().executeQuery(
                    "UPDATE LESLOCATIONS " +
                            "set etat ='"+obj.getEtat()+
                            "' where IDCARTE ="+obj.getClient().getCarte().getId()+" and DATELOCATION='"+obj.getDate()+"'");
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Utilisé lors du rendu d'un BluRay. À partir de ce BluRay, la location
     * est retrouvée et son état passe de ENCOURS à APAYER.
     * Pré-condition : la location est dans l'état ENCOURS
     * @param b le BluRay rendu
     * @return vrai si la location est bien passée dans l'état voulu
     */
    public boolean modifierDepuisBluRay(BluRay b){
        // TODO A TESTER
        try {
            // Identification de la location
            ResultSet res = connect.createStatement().executeQuery(
                    "SELECT L1.IDLOCATION " +
                            "FROM LESLOCATIONSBLURAY L1 " +
                            "JOIN LESLOCATIONS L on L1.IDLOCATION = L.IDLOCATION " +
                            "WHERE IDBLURAY="+b.getId()+" and ETAT='ENCOURS'");
            res.next();
            // Changement de l'état de la location
            connect.createStatement().executeUpdate(
                    "UPDATE LESLOCATIONS " +
                            "SET ETAT='APAYER' " +
                            "WHERE IDLOCATION="+res.getInt("idlocation"));
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Donne la liste des locations effectuées par la machine.
     * @return la liste des locations
     */
    public List<Location> lireLocationsMachine() {
        // TODO A TESTER
        List<Location> locations = new ArrayList<>();
        AbonneDAO abonneDAO = new AbonneDAO(connect);
        CarteAboDAO carteAboDAO = new CarteAboDAO(connect);
        CBDAO cbdao = new CBDAO(connect);
        try {
            // Récupération des locations de QRCode par CB
            ResultSet res = connect.createStatement().executeQuery(
                    "select L.IDLOCATION, DATELOCATION, etat, L2.IDCARTE, IDMACHINE, IDFILM, nom, prenom, infos " +
                            "from LESLOCATIONS " +
                            "         join LESLOCATIONSQRCODE L on LESLOCATIONS.IDLOCATION = L.IDLOCATION " +
                            "         join LESCARTES L2 on LESLOCATIONS.IDCARTE = L2.IDCARTE " +
                            "         join LESCB L3 on L2.IDCARTE = L3.IDCARTE;");
            while (res.next()) {
                locations.add(toLocationQRCode(res, "CB"));
            }
            // Récupération des locations de QRCode par CA
            res = connect.createStatement().executeQuery(
                    "select L.IDLOCATION, DATELOCATION, etat, L2.IDCARTE, IDMACHINE, IDFILM, nom, prenom, MAIL, ADRESSEPOSTALE, SOLDE, MDP " +
                            "from LESLOCATIONS " +
                            "         join LESLOCATIONSQRCODE L on LESLOCATIONS.IDLOCATION = L.IDLOCATION " +
                            "         join LESCARTES L2 on LESLOCATIONS.IDCARTE = L2.IDCARTE " +
                            "         join LESCA L3 on L2.IDCARTE = L3.IDCARTE;");
            while (res.next()) {
                locations.add(toLocationQRCode(res, "CA"));
            }
            // Récupération des locations de BluRay par CB

            res = connect.createStatement().executeQuery(
                    "select L.IDLOCATION, DATELOCATION, etat, L2.IDCARTE, IDMACHINE, IDBLURAY, nom, prenom, infos " +
                            "from LESLOCATIONS " +
                            "        join LESLOCATIONSBLURAY L on LESLOCATIONS.IDLOCATION = L.IDLOCATION " +
                            "        join LESCARTES L2 on LESLOCATIONS.IDCARTE = L2.IDCARTE " +
                            "        join LESCB L3 on L2.IDCARTE = L3.IDCARTE");
            while (res.next()) {
                locations.add(toLocationBluRay(res, "CB"));
            }
            // Récupération des locations de Bluray par CA
            res = connect.createStatement().executeQuery(
                    "select L.IDLOCATION, DATELOCATION, etat, L2.IDCARTE, IDMACHINE, IDBLURAY, nom, prenom, MAIL, ADRESSEPOSTALE, SOLDE, MDP " +
                            "from LESLOCATIONS " +
                            "        join LESLOCATIONSBLURAY L on LESLOCATIONS.IDLOCATION = L.IDLOCATION " +
                            "        join LESCARTES L2 on LESLOCATIONS.IDCARTE = L2.IDCARTE " +
                            "        join LESCA L3 on L2.IDCARTE = L3.IDCARTE;");
            while (res.next()) {
                locations.add(toLocationBluRay(res, "CA"));
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return locations;
    }

    /**
     * Fonction interne qui crée l'objet client associé à la ligne de BD de location.
     * @param ligne la ligne venant de la BD
     * @param mode CB ou CA pour définir si l'on cherche un client anonyme ou abonné
     * @return l'objet client crée (anonyme ou abonné)
     * @throws SQLException pour tout problème en BD
     */
    private Client recupererClient(ResultSet ligne, String mode) throws SQLException{
        Client c;
        if (Objects.equals(mode, "CB")){ // En CB, il faut juste créer un client anonyme attribuer la CB en DAO
            c = new Anonyme(new Genre[0], (new CBDAO(connect)).lire(ligne.getInt("idcarte")));
        } else { // En CA, il faut récupérer l'objet abonné associé grâce au DAO
            c= (new AbonneDAO(connect).lireDepuisCarte( (new CarteAboDAO(connect).lire(ligne.getInt("idcarte")))));
        }
        return c;
    }

    /**
     * Fonction interne qui créer l'objet location, en créant tout les objets qui en découlent.
     * Utilisé lors de la lecture d'un historique d'une machine.
     * @param ligne la ligne venant de la BD
     * @param mode CB ou CA pour définir si l'on cherche un client anonyme ou abonné
     * @return l'objet location créé
     * @throws SQLException pour tout problème en BD
     */
    private Location toLocationBluRay(ResultSet ligne, String mode) throws SQLException{
        // ETAPE 1 : Créer l'objet client correspondant
        Client c = recupererClient(ligne, mode);
        // ETAPE 2 : Créer l'objet BluRay et la location
        return toLocationBluRay(ligne, c);
    }

    /**
     * Fonction interne qui créer l'objet location, en créant tout les objets qui en découlent.
     * Utilisé lors de la lecture d'un historique d'une machine.
     * @param ligne la ligne venant de la BD
     * @param mode CB ou CA pour définir si l'on cherche un client anonyme ou abonné
     * @return l'objet location créé
     * @throws SQLException pour tout problème en BD
     */
    private Location toLocationQRCode(ResultSet ligne, String mode) throws SQLException{
        // ETAPE 1 : Créer l'objet client correspondant
        Client c = recupererClient(ligne, mode);
        // ETAPE 2 : Créer l'objet location
        return toLocationQRCode(ligne, c);
    }

    /**
     * Donne la liste des locations du client.
     * @return la liste des locations
     */
    public List<Location> lireLocationsCarte(Client a){
        // TODO A TESTER
        List<Location> locations = new ArrayList<>();
        try {
            // Récupération des locations de QRCode
            ResultSet qr = connect.createStatement().executeQuery(
                    "SELECT * FROM LESLOCATIONS JOIN LESLOCATIONSQRCODE L ON LESLOCATIONS.IDLOCATION = L.IDLOCATION " +
                            "WHERE IDCARTE="+a.getCarte().getId());
            while(qr.next()){
                locations.add(toLocationQRCode(qr, a));
            }
            // Récupération des locations de BluRay
            ResultSet br = connect.createStatement().executeQuery(
                    "SELECT * FROM LESLOCATIONS JOIN LESLOCATIONSBLURAY L ON LESLOCATIONS.IDLOCATION = L.IDLOCATION " +
                            "WHERE IDCARTE="+a.getCarte().getId());
            while(br.next()){
                locations.add(toLocationBluRay(br, a));
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return locations;
    }

    /**
     * Fonction interne permettant de convertir une ligne de location en objet Location.
     * @param ligne à convertir en objet location
     * @param c le client à associer à la location
     * @return l'objet location obtenu
     */
    private Location toLocationQRCode(ResultSet ligne, Client c) throws SQLException{
        // Récupération du support
        FilmDAO dao = new FilmDAO(connect);
        Film film = dao.lire(ligne.getInt("idFilm"));
        return new Location(ligne.getDate("datelocation"),
                Etat.valueOf(ligne.getString("etat")),
                new QrCode(-1, film), c);
    }

    /**
     * Fonction interne permettant de convertir une ligne de location en objet Location.
     * @param ligne à convertir en objet location
     * @param c le client à associer à la location
     * @return l'objet location obtenu
     */
    private Location toLocationBluRay(ResultSet ligne, Client c) throws SQLException{
        // Récupération du support
        BluRayDAO dao = new BluRayDAO(connect);
        BluRay support = dao.lire(ligne.getInt("idbluray"));
        return new Location(ligne.getDate("datelocation"),
                Etat.valueOf(ligne.getString("etat")),
                support, c);
    }
}
