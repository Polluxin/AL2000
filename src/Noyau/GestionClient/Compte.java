package Noyau.GestionClient;

import Noyau.Exception.MauvaisMotDePasse;
import Noyau.Exception.PaiementRefuse;
import Noyau.GestionLocation.Genre;
import Noyau.GestionMachine.FormulaireInscription;
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
    public void inscrire(FormulaireInscription f) {
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
     * Teste si la carte de l'abonné connecté dispose du montant nécéssaire
     *
     * @param montant le montant nécéssaire
     * @return vrai si la carte contient les fonds suffisants
     */
    public boolean verifier_fonds(float montant) {
        return client.carte.verifier_fonds(montant);
    }

    /**
     * Recharge le solde du compte du client courant grâce à une CB.
     *
     * @param montant la somme à créditer
     * @param cb      la carte à débiter
     */
    public void rechargerSolde(float montant, CB cb) throws PaiementRefuse {
        // payer avec carte
        cb.payer(montant);
        // augmenter le solde
        ((Abonne) client).recharger(montant);
    }

    /**
     * Vide le compte du client courant en créditant la cb.
     *
     */
    public void retirerSolde(CB cb) throws PaiementRefuse {
        CarteAbo cA = ((Abonne) client).carte;
        float montant = cA.getSolde();
        // recharger carte
        cb.recharger(montant);
        // vider compte
        cA.setSolde(0.F);
    }

    /**
     * Modifie les préférences liées au compte du client actuellement connecté
     *
     * @param L La nouvelle liste de genres interdits
     */
    public void reglerInterdits(Genre[] L) {
        client.setInterdits(L);
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
