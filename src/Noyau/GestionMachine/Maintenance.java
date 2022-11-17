package Noyau.GestionMachine;
/**
 * Ensemble d'opérations physiques sur la machine permettant sa
 * maintenance et la consultation de ses données par un humain
 * (technicien).
 * @author Geoffrey DAVID
 * @version 0
 */
public interface Maintenance {

    /**
     * Ouvre la trappe de la machine pour permettre au technicien d'effectuer des
     * opérations de maintenance
     */
    void ouvrir();

    /**
     * Ferme la trappe de maintenance de la machine.
     */
    void fermer();

    /**
     * Donne les statistiques de la machine.
     * @return les statistiques
     */
    Statistiques voirStatistiques();

    /**
     * Donne l'inventaire des films BluRays présents dans la machine.
     * @return l'inventaire des BluRays
     */
    Inventaire donnerInventaire();
}
