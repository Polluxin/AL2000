package Tests;
import java.util.Scanner;
import Metier.Exception.FondsInsuffisants;
import Metier.Exception.FormulaireInvalide;
import BaseDeDonnees.*;
import Controle.*;
import Metier.Exception.CarteIllisible;
import Metier.Exception.ConnexionImpossible;
import Metier.Exception.MauvaisMotDePasse;
import Metier.GestionLocation.Genre;
import Metier.GestionMachine.FormulaireInscription;

public class Test2 {
 

    /**
     * @param arg
     * @throws FondsInsuffisants
     * @throws CarteIllisible
     * @throws ConnexionImpossible
     * @throws MauvaisMotDePasse
     */
    public static void main(String[] arg) throws FondsInsuffisants,CarteIllisible, ConnexionImpossible, MauvaisMotDePasse{
        int i=0;
        int  idfilm;
        String scChar;
        Scanner cs=new Scanner(System.in);
        FormulaireInscription form;
//connextion du client 
        System.out.println("inscription  :");
        System.out.println("Veuillez entrer votre adresse mail");
        String nom=cs.next();
       System.out.println("Veuillez entrer votre adresse mail");
       String prenom=cs.next();
        System.out.println("Veuillez entrer votre adresse mail");
        String mail=cs.next();
        System.out.println("Veuillez entrer votre adresse");
        String adr=cs.next();
        System.out.println("Veuillez entrer votre mot de passe ");
        String md1=cs.next();
        System.out.println("Veuillez confirmer votre mot de passe  ");
        String md2=cs.next();
        Genre[] inter=new Genre[3];
            
            inter[0]=Genre.ACTION;
            inter[1]=Genre.SF;
            inter[2]=Genre.FANTAISIE;
        FormulaireInscription f=new FormulaireInscription(nom,prenom,adr, mail, inter, md1);
        
        AL2000 aL2000=new AL2000(new Session());
        try {
            aL2000.inscription(f);
        } catch (FormulaireInvalide e) {
            System.out.println("erreur formulaire ");
            e.printStackTrace();
        }
        System.out.println("inscription reussi ");
//afficher le catalogue :  
    }}
