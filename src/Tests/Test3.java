package Tests;

import java.util.Scanner;

import BaseDeDonnees.Session;
import Controle.AL2000;
import Metier.Exception.BluRayInvalide;
import Metier.Exception.BluRayNonLoue;

public class Test3 {
    public static void main(String[] arg) throws BluRayInvalide, BluRayNonLoue {
        int i=0;
        int  idfilm;
        String scChar;
boolean b =true;
System.out.println("rendre dvd");
       while (b){
        AL2000 aL2000=new AL2000(new Session());
        System.out.println("donner le numero du dvd");
        Scanner cs=new Scanner(System.in);
        scChar=cs.next();
        aL2000.simulerInsertionBluRay(scChar);
        System.out.println("voullez vous rendre un autre bluray 'oui' 'non' ");
        scChar=cs.next();
        b=scChar=="oui";
    }

}}
