package Noyau.GestionMachine;

import com.sun.tools.javac.Main;

/**
 *
 * @author
 * @version
 */
public interface Maintenance {

    void ouvrir();

    void fermer();

    Statistiques voirStatistiques();

    Inventaire donnerInventaire();
}
