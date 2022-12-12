package Controle;

import java.util.HashMap;
import java.util.Map;

/**
 * Une classe qui sert à diminuer les dépendances entre les objets. Ici, tous les événements passent par lui, et c'est
 * lui qui décide d'appeler les fonctions des classes abonnées pour la publication d'un événement donné.
 * Il implémente l'abonnement pour avoir un comportement asynchrone lors de la lecture d'une carte et l'insertion de
 * BluRays.
 * @author Geoffrey DAVID
 */
@SuppressWarnings("unused")
public class Mediateur{
    Map<String, Handler> handlers;

    AL2000 logiciel;

    public Mediateur(){
        handlers = new HashMap<>();
    }

    /**
     * Utilisée par l'interface utilisateur pour attendre une certaine action (par exemple l'insertion d'un BluRay.)
     * @param evenement la catégorie de l'événement
     * @param h l'action à effectuer en réponse (relative à l'AL2000)
     */
    public void abonner(String evenement, Handler h) {
        handlers.put(evenement, h);
    }

    /**
     * Utilisée pour stopper l'attente d'évenement.
     * @param evenement la catégorie de l'événement
     */
    public void desabonner(String evenement) {
        handlers.remove(evenement);
    }

    /**
     * Permet d'activer le handler associé à l'événement considéré. Utilisé par l'interface utilisateur pour effectuer
     * une opération après une attente, comme identifier une CB insérée dans le lecteur, via l'AL2000. Les données
     * découlant de l'évènement se trouvent dans une classe implémentant DonneesEvenement.
     * @param evenement l'événement produit
     * @param e les données relatives à l'événement
     */
    public void publier(String evenement, DonneesEvenement e) {
        handlers.get(evenement).handle(e);
    }

    public void setLogiciel(AL2000 l ){
        this.logiciel = l;
    }
}
