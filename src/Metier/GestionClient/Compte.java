package Metier.GestionClient;

import Metier.Exception.FormulaireInvalide;
import Metier.Exception.MauvaisMotDePasse;
import Metier.Exception.PaiementRefuse;
import Metier.GestionLocation.Genre;
import Metier.GestionMachine.FormulaireInscription;

/**
 * Classe permettant d'authentifier un client, c'est depuis cette classe que toutes les opérations relatives au compte s'effectuent.
 *
 *
 * @author Geoffrey DAVID
 * @author Armand GRENIER
 * @version 0
 */
public class Compte {

    Client client = null;

    public Compte() {
    }

    /**
     * Grâce au formulaire f, tente d'inscrire le client en consultant la base de données.
     * si la création de compte se passe bien, le compte est connecté
     *
     * @param f le forumlaire d'inscription
     *          préciser l'exception
     * @throws FormulaireInvalide
     */
    public void inscrire(FormulaireInscription f) throws FormulaireInvalide {
        //TODO
        //appel vers la bd pour creer un compte
        throw new FormulaireInvalide();
        // connexion à faire
    }

    /**
     * Grâce à la carte c et le mot de passe mdp, tente de connecter l'abonné à la machine.
     *
     * @param c   la carte de l'abonné
     * @param mdp le mot de passe entré
     */

    /**
     * Essaie de connecter l'abonné un abonné identifier par sa carte avec son mot de passe.
     *
     * @param c carte de l'abonnée
     * @param mdp mot de passe
     * @throws MauvaisMotDePasse
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
     * Déconnecte le client de la machine (Compte.client vaut alors null)
     */
    public void deconnexion() {
        this.client = null;
    }

    /**
     * Teste si le compte dispose de moyen suffisant pour payer une certain montant
     *
     * @param montant le montant nécéssaire
     * @return vrai si la carte contient les fonds suffisants
     */
    public boolean verifier_fonds(float montant) {
        return client.carte.verifier_fonds(montant);
    }

    /**
     * Recharge le solde du compte (Abonnée) du client courant grâce à une CB.
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
     * Vide le Solde du compte (ABonnée) du client vers une cb
     *
     * @param cb
     * @throws PaiementRefuse
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
        client.reglerInterdits(L);
    }

    /**
     * Associe une carte banquaire avec un compte (Anonyme)
     * @param cb
     */
    public void AnonymeSetCB(CB cb) {
        Anonyme cA = (Anonyme) client;
        cA.carte = cb;
    }
}
