package Noyau;

import java.util.Timer;

/**
 * Police vérifie périodiquement s'il y a des locations non rendues depuis plus de
 * 5 jours. Si oui, alors un débit sur compte bancaire est effectué.
 * @author Geoffrey DAVID
 * @version 0
 */
public class Police {

    HistoLoc histo;
    Timer chrono;

    Police(HistoLoc historique, int delai){
        histo = historique;
        chrono = new Timer();
    }
}
