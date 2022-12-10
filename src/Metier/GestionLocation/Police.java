package Metier.GestionLocation;

import Metier.Exception.PaiementRefuse;

import java.sql.Date;
import java.util.Timer;

/**
 * Police vérifie périodiquement s'il y a des locations non rendues depuis plus de
 * 5 jours. Si oui, alors un débit sur compte bancaire est effectué.
 * @author Geoffrey DAVID
 * @version 0
 */
public class Police {

    HistoLoc histo;
    // chrono probablement à retirer
    Timer chrono;

    public Police(HistoLoc historique, int delai){
        histo = historique;
        chrono = new Timer();
    }
    private static int daysApart(Date time1, Date time2){
        // TODO A TESTER
        // vérifier comment bien utiliser cette fonction pour compter les jours depuis la date de location
        return (int)((time2.getTime() - time1.getTime()) / (1000*60*60* 24L));
    }
    private void CheckPaiements(){
        for (Location l :histo.voirHistorique()){
            if (l.getEtat() == Etat.APAYER){
                // location à payer
                try {
                    l.payer();
                    l.setEtat(Etat.TERMINEE);
                } catch (PaiementRefuse e) {
                    // que faire quand le paiement a échoué ???
                    // rien puis réessayer la prochaine fois ?
                    //throw new RuntimeException(e);
                }
            } else if (daysApart(l.getDate(), new Date(System.currentTimeMillis())) >= 3){
                // location en retard
                try {
                    l.payerRetard();
                    l.setEtat(Etat.TERMINEE);
                } catch (PaiementRefuse e) {
                    // paiement échoué
                    //throw new RuntimeException(e);
                }
            }
        }
    }
}
