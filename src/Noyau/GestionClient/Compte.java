package Noyau.GestionClient;

import Noyau.Exception.MauvaisMotDePasse;
import Noyau.GestionMachine.Formulaire;
import Noyau.GestionLocation.Location;

import java.util.List;
import java.util.prefs.Preferences;

/**
 * Classe permettant d'authentifier un client, c'est depuis cette classe que toutes les opérations relatives au compte s'effectuent.
 *
 * @author Geoffrey DAVID
 * @author Armand GRENIER
 * @version 0
 */
public class Compte {

    Client client;

    Compte() {

    }

    /**
     * Grâce au formulaire f, tente d'inscrire le client en consultant la base de données.
     *
     * @param f le forumlaire d'inscription
     *          préciser l'exception
     */
    public void inscrire(Formulaire f) {
        //TODO
        //appel vers la bd pour creer un compte
        // propager exception si besoin
        // connexion à faire
    }

    /**
     * Grâce à la carte c et le mot de passe mdp, tente de connecter l'abonné à la machine.
     *
     * @param c   la carte de l'abonné
     * @param mdp le mot de passe entré
     */
    public void connexion(CarteAbo c, String mdp) throws MauvaisMotDePasse {
        Abonne abo = null;
        //TODO
        //lire abo dans la bd avec la carteAbo c Throw exception depuis le DAO !
        if (mdp.compareTo(abo.getMotDePasse()) != 0) {
            throw new MauvaisMotDePasse();
        }
        // tout s'est bien passé, le client est connecté
        this.client = abo;
    }


    /**
     * Déconnecte le client de la machine (Compte.abo vaut alors null)
     */
    public void deconnexion() {
        this.client = null;
    }

    /**
     * Teste si la carte d'un abonné c contient un solde suffisant pour effectuer la commande l.
     *
     * @param c la carte à vérifier
     * @param montant la liste des locations
     * @return vrai si la carte contient les fonds suffisants
     */
    public boolean verifier_fonds(Carte c, float montant) {
        return false;
    }

    /**
     * Crédite le compte du client courant grâce à une CB.
     *
     * @param montant la somme à créditer
     * @param cb      la carte à débiter
     * @return vrai si le compte est bien crédité
     */
    public boolean recharger(float montant, CB cb) {
        return true;
    }

    /**
     * Vide le compte du client courant en créditant la cb.
     *
     * @return vrai si le compte est bien crédité et le solde vidé
     */
    public boolean retirerSolde(CB cb) {
        return true;
    }

    /**
     * Modifie les préférences liées au compte du client actuellement connecté
     *
     * @param p les préférences
     */
    public void reglerPreferences(Preferences p) {

    }

    /**
     * Renvoie l'abonné actuellement connecté.
     *
     * @return l'anonné connecté, null sinon.
     */
    public Abonne compteConnecte() {
        return null;
    }

}
