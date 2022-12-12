package Tests;
import java.util.List;
import java.util.Scanner;
import Metier.Exception.FondsInsuffisants;
import BaseDeDonnees.*;
import Controle.*;
import Metier.Exception.CarteIllisible;
import Metier.Exception.ConnexionImpossible;
import Metier.Exception.MauvaisMotDePasse;
import Metier.GestionClient.CarteAbo;
import Metier.GestionLocation.FilmEtFormat;
import Metier.GestionLocation.FiltreTri;
import Metier.GestionLocation.Support;
import Metier.GestionLocation.Tri;
public class Test1 {
 

    public static void main(String[] arg) throws FondsInsuffisants,CarteIllisible, ConnexionImpossible, MauvaisMotDePasse{
        int i=0;
        int  idfilm;
        String scChar;
        Scanner cs=new Scanner(System.in);

//connextion du client 
        System.out.println("connextion  :");
        System.out.println("donner un numero de carte ");
        String numcarte;
        numcarte=cs.next();
      
        System.out.println("donner votre mot de passe  ");
        String md="CHARLESbogoss2002";
        scChar=cs.next();
        AL2000 aL2000=new AL2000(new Session());
        CarteAbo c=aL2000.simulerInsertionCA(numcarte);
        aL2000.connexion(c, md);

//afficher le catalogue :  
            boolean b =true;
        List<FilmEtFormat> catalogue=aL2000.donnerCatalogue(new FiltreTri(Tri.TITRE,""));
        while(b==true){
        System.out.println("le catalogue :");
        for (FilmEtFormat f :catalogue ){
            System.out.println(i+" "+f);
            i++;
        }

        System.out.println("choisir un film entre 0 et  "+i);
        idfilm=cs.nextInt();

        if(catalogue.get(idfilm).estDispoEnPhysique()){
            System.out.println("dis format bluray");
        }else{
            System.out.println("dis format qrcode");
        }
        System.out.println("voulez vous le fim au format bluray entrer 'b' sinon qrcode entrer 'q' ");
        
        String ret=cs.next(); 
        Support achat =aL2000.getSupport(catalogue.get( idfilm).getFilm(),ret=="b");

        aL2000.ajouterPanier(achat);
        System.out.println("bien été ajouter");
        System.out.println("voullez vous ajouter un autre film 'oui' 'non'");
    String r=cs.next();
    b=r=="oui";
    }
    System.out.println(aL2000.consulterPanier());
    
       try {
        aL2000.validerPanier();
       } catch (FondsInsuffisants e) {
        System.out.println("fond insufissant");
       }
    }
    }
