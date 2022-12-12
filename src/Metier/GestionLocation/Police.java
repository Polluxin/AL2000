package Metier.GestionLocation;

import Metier.Exception.PaiementRefuse;

import java.sql.Date;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Police vérifie périodiquement s'il y a des locations non rendues depuis plus de
 * 5 jours. Si oui, alors un débit sur compte bancaire est effectué.
 * @author Armand GRENIER
 * @version 0
 */
public class Police implements Runnable {

    HistoLoc histo;
    static Police police;

    public Police(HistoLoc historique){
        histo = historique;
    }
    public static int daysApart(Date time1, Date time2){
        // TODO A TESTER
        // vérifier comment bien utiliser cette fonction pour compter les jours depuis la date de location
        //return (int)((time2.getTime() - time1.getTime()) / (1000*60*60* 24L));
        return (int)((time2.getTime() - time1.getTime()));
    }
    private void CheckPaiements(){
        for (Location l :histo.voirHistorique()){
            // vérifier l'etat
            if (l.getEtat() == Etat.TERMINEE) continue;
            if (l.getEtat() == Etat.APAYER){
                // effectuer le paiement
                try {
                    l.payer();
                    l.setEtat(Etat.TERMINEE);
                } catch (PaiementRefuse e) {
                    // rien faire puis réessayer la prochaine fois
                    System.out.println("Paiement refusé pour location APAYER");
                    // throw new RuntimeException(e);
                }
                continue;
            }
            // vérifier le temps de location
            if (daysApart(l.getDate(), new Date(System.currentTimeMillis())) >= 10){
                // location en retard
                try {
                    l.payerRetard();
                    l.setEtat(Etat.TERMINEE);
                } catch (PaiementRefuse e) {
                    // paiement échoué
                    System.out.println("Paiement refusé pour location en retard");
                    //throw new RuntimeException(e);
                }
            }
        }
    }

    static public void activerPolice(HistoLoc histo, int delaisSecondes) {
        Police.police = new Police(histo);
        // activer la Police
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(police, 0, delaisSecondes, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        System.out.println("début de run dans Police");
        System.out.println("Afficher l'histo : ");
        for (Location l :histo.voirHistorique()){
            System.out.println(l);
        }
        System.out.println("----\n");
        CheckPaiements();
        System.out.println("Afficher l'histo : ");
        for (Location l :histo.voirHistorique()){
            System.out.println(l);
        }
        System.out.println("----");
        System.out.println("fin de run dans Police\n");
    }
}
