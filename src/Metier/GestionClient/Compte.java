package Metier.GestionClient;

import BaseDeDonnees.DAOs.AbonneDAO;
import BaseDeDonnees.DAOs.CBDAO;
import BaseDeDonnees.Session;
import Metier.Exception.FormulaireInvalide;
import Metier.Exception.MauvaisMotDePasse;
import Metier.Exception.PaiementRefuse;
import Metier.GestionLocation.Genre;
import Metier.GestionLocation.HistoLoc;
import Metier.GestionMachine.FormulaireInscription;

/**
 * Classe permettant d'authentifier un client, c'est depuis cette classe que toutes les opérations relatives au compte s'effectuent.
 *
 * @author Geoffrey DAVID
 * @author Armand GRENIER
 * @version 0
 */
@SuppressWarnings({"JavadocDeclaration", "unused"})
public class Compte {

    Client client = new Anonyme(null);

    Session bd;

    public Compte(Session s) {
        this.bd = s;

    }

    public Client getClient() {
        return client;
    }

    /**
     * Grâce au formulaire f, tente d'inscrire le client en consultant la base de données.
     * Si la création de compte se passe bien, le compte est connecté
     *
     * @param f le forumlaire d'inscription
     *          préciser l'exception
     * @throws FormulaireInvalide
     */
    public void inscrire(FormulaireInscription f) throws FormulaireInvalide {
        // Vérification des informations du formulaire
        try {
            verifierFormulaireI(f);
        } catch (FormulaireInvalide e){
            e.printStackTrace();
            throw e;
        }
        Abonne a = new Abonne(f.getInterdits(),null, f.getNom(), f.getPrenom(),
            f.getAdresseMail(), f.getAdresse(), f.getMdp());
        //TODO
        //appel vers la bd pour creer un compte
        bd.open();
        AbonneDAO dao = new AbonneDAO(bd.getSession());
        // Cette fonction crée la carte d'abonnée en BD et la lie à l'abonné A
        dao.creer(a);
        bd.close();
        assert(a.getCarte() != null);
        // Connexion de l'abonné nouvellement inscrit
        client = a;
    }

    private void verifierFormulaireI(FormulaireInscription f) throws FormulaireInvalide{
        System.out.println("Verification du formulaire d'inscription :");
        System.out.print(" -> Nom : ");
        if (!f.getNom().matches("(\\W|^)[A-z]+(\\W|$)")){
            System.out.println("KO");
            throw new FormulaireInvalide("Format du nom incorrect");
        }
        System.out.print(" -> Prenom : ");
        if (!f.getPrenom().matches("(\\W|^)[A-z]+(\\W|$)")){
            System.out.println("KO");
            throw new FormulaireInvalide("Format du prénom incorrect");
        }
        System.out.print(" -> Adresse : ");
        if (f.getAdresse().isEmpty()){
            System.out.println("KO");
            throw new FormulaireInvalide("Champ de l'adresse vide");
        }
        System.out.print(" -> Mail : ");
        if (!f.getAdresseMail().matches(".+@.+")){
            System.out.println("KO");
            throw new FormulaireInvalide("Format du mail incorrect");

        }
        System.out.print(" -> Mot de passe : ");
        if (f.getMdp().isEmpty()){
            System.out.println("KO");
            throw new FormulaireInvalide("Champ du mot de passe vide");
        }
    }
    /**
     * Essaie de connecter l'abonné un abonné identifié par sa carte avec son mot de passe.
     *
     * @param c carte de l'abonnée
     * @param mdp mot de passe
     * @throws MauvaisMotDePasse si le mot de passe est incorrect
     */
    public void connexion(CarteAbo c, String mdp) throws MauvaisMotDePasse {
        Abonne abo;
        bd.open();
        AbonneDAO dao = new AbonneDAO(bd.getSession());
        abo = dao.lireDepuisCarte(c);
        bd.close();
        if (mdp.compareTo(abo.getMotDePasse()) != 0) {
            throw new MauvaisMotDePasse();
        }
        // tout s'est bien passé, le client est connecté
        System.out.println("Client authentifié");
        this.client = abo;
    }

    /**
     * Effectue une connexion, mais avec un anonyme
     * @param cb carte banquaire de l'anonyme
     */
    public void connexionAnonyme(CB cb) {
        bd.open();
        CBDAO cbdao = new CBDAO(bd.getSession());
        if (cbdao.lireID(cb) == -1) // Carte non enregistree en BD
            cbdao.creer(cb);
        cb.setId(cbdao.lireID(cb));
        bd.close();
        this.client = new Anonyme(cb);
    }


    /**
     * Déconnecte le client de la machine (client vaut alors null)
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
    public void rechargerSolde(float montant, CB cb) {
        // payer avec carte
        CarteAbo c = (CarteAbo) (client).getCarte();
        cb.payer(montant);
        // augmenter le solde
        (client).recharger(montant);
        bd.open();
        new AbonneDAO(bd.getSession()).modifierSolde( c.getId(), c.getSolde());
        bd.close();
        System.out.println("Compte rechargé de "+montant+" euros");
    }

    /**
     * Vide le Solde du compte (ABonnée) du client vers une cb
     *
     * @param cb
     * @throws PaiementRefuse
     */
    public void retirerSolde(CB cb) throws PaiementRefuse {
        // vider compte
        float montant = getClient().payerMax();
        // recharger carte
        cb.recharger(montant);
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

    public Genre[] getInterdits() {
        if (client == null) {
            return new Genre[]{};
        }
        return client.getInterdits();
    }

    public float fondsReserves(HistoLoc histo) {
        if (client != null) {
            return client.fondsReserves(histo);
        }
        return 0.F;
    }

}
