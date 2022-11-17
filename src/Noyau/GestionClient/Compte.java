package Noyau.GestionClient;

import Noyau.GestionMachine.Formulaire;
import Noyau.GestionLocation.Location;

import java.util.List;
import java.util.prefs.Preferences;

/**
 * Classe permettant d'authentifier un client, c'est depuis cette classe que toutes les opérations relatives au compte s'effectuent.
 * @author Geoffrey DAVID
 * @version 0
 */
public class Compte {
    Abonne abo;

    Compte() {

    }

    /**
     * Grâce au formulaire f, tente d'inscrire le client en consultant la base de données.
     *
     * @param f le forumlaire d'inscription
     */
    public void inscrire(Formulaire f) {

    }

    /**
     * Grâce à la carte c et le mot de passe mdp, tente de connecter l'abonné à la machine.
     * @param c la carte de l'abonné
     * @param mdp le mot de passe entré
     */
    public void connexion(CarteAbo c, String mdp){
    }

    /**
     * Déconnecte le client de la machine (Compte.abo vaut alors null)
     */
    public void deconnexion(){

    }

    /**
     * Teste si la carte c contient un solde suffisant pour effectuer la commande l.
     * @param c la carte à vérifier
     * @param l la liste des locations
     * @return vrai si la carte contient les fonds suffisants
     */
    public boolean verifier_fonds(Carte c, List<Location> l){
        return true;
    }

    /**
     * Crédite le compte du client courant grâce à une CB.
     * @param montant la somme à créditer
     * @param cb la carte à débiter
     */
    public void recharger(float montant, CB cb){
    }

    /**
     * Vide le compte du client courant en créditant la cb.
     */
    public void retirerSolde(CB cb){
    }

    /**
     * Modifie les préférences liées au compte du client actuellement connecté
     * @param p les préférences
     */
    public void reglerPreferences(Preferences p){

    }

    /**
     * Renvoie l'abonné actuellement connecté.
     * @return l'anonné connecté, null sinon.
     */
    public Abonne compteConnecte(){
        return null;
    }

}
