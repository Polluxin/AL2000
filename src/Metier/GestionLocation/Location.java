package Metier.GestionLocation;

import Metier.Exception.PaiementRefuse;
import Metier.GestionClient.Client;
import Metier.GestionMachine.Distributeur;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
@SuppressWarnings("unused")
public class Location {
    Date date;
    Etat etat;
    int id = -1;
    Support support;
    Client client;

    public Location(Date date, Etat etat, Support support, Client client) {
        this.date = date;
        this.etat = etat;
        this.support = support;
        this.client = client;
    }

    //<editor-fold desc="getter/setter">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public Client getClient() {
        return client;
    }

    public Date getDate() {
        return date;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    //</editor-fold>

    /**
     * Calcul les fonds nécéssaire pour pouvoir payer une location.
     *
     * @param l liste de locations
     * @return les fonds minimum pour la location
     */
    public static float fondsNecessaire(Location l){
        return l.support.getPrixMax();
    }

    /**
     * Calcul les fonds nécéssaire pour pouvoir louer toutes les locations de la liste.
     *
     * @param L liste de locations
     * @return les fonds minimum pour les locations
     */
    public static float fondsNecessaire(Location[] L){
        float sum = 0.F;
        for (Location loc : L) {
            sum += fondsNecessaire(loc);
        }
        return sum;
    }

    /**
     * Calcul les fonds nécessaires pour pouvoir louer toutes les locations de la liste.
     *
     * @param locations liste de location dont le coût est calculé
     * @return la somme des fonds max pour chacune des locations
     */
    public static float fondsNecessaire(List<Location> locations) {
        return fondsNecessaire(locations.toArray());
    }

    private static float fondsNecessaire(Object[] toArray) {
        float sum =0.F;
        for (Object o : toArray){
            sum += fondsNecessaire((Location) o);
        }
        return sum;
    }

    /**
     * Fait payer la location au client en fonction du nombre
     * de jours depuis le début de la location.
     */
    public void payer() throws PaiementRefuse {
        int joursAPayer;
        // TODO
        Date sqlDate = new Date(System.currentTimeMillis());
        joursAPayer = Police.daysApart(date, sqlDate);
        joursAPayer = Math.min(joursAPayer, 3);
        client.payer(support, joursAPayer);
        setEtat(Etat.TERMINEE);
    }

    public void payerRetard() throws PaiementRefuse {
        client.payerRetard(support);
        setEtat(Etat.TERMINEE);
    }


    public void changerSupport(Distributeur dist) {
        support = support.supportInverse(dist);
    }

    @Override
    public String toString() {
        return "Location{" +
                "date=" + date +
                ", etat=" + etat +
                ", support=" + support +
                ", client=" + client +
                '}';
    }
}
