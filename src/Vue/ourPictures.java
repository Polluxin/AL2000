package Vue;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * ourPictures loads all the pictures used in the program to avoid having to load them multiple times.
 */
public final class ourPictures {
    private static StretchIcon admin = loadPicture("src/ressources/admin.png");
    private static StretchIcon afficherHistorique = loadPicture("src/ressources/afficherHistorique.png");
    private static StretchIcon afficherStatistiques = loadPicture("src/ressources/afficherStatistiques.png");
    private static StretchIcon ajouterPanier = loadPicture("src/ressources/ajouterPanier.png");
    private static StretchIcon Background = loadPicture("src/ressources/Background.png");
    private static StretchIcon bluray = loadPicture("src/ressources/bluray.png");
    private static StretchIcon button = loadPicture("src/ressources/button.png");
    private static StretchIcon compte = loadPicture("src/ressources/compte.png");
    private static StretchIcon connexion = loadPicture("src/ressources/connexion.png");
    private static StretchIcon disconnect = loadPicture("src/ressources/disconnect.png");
    private static StretchIcon inscription = loadPicture("src/ressources/inscription.png");
    private static StretchIcon inscriptionpayer = loadPicture("src/ressources/inscriptionpayer.png");
    private static StretchIcon ouvrirAL2000 = loadPicture("src/ressources/ouvrirAL2000.png");
    private static StretchIcon panier = loadPicture("src/ressources/panier.png");
    private static StretchIcon parametres = loadPicture("src/ressources/parametres.png");
    private static StretchIcon QRCode = loadPicture("src/ressources/QRCode.png");
    private static StretchIcon recharger = loadPicture("src/ressources/recharger.png");
    private static StretchIcon rendredvd = loadPicture("src/ressources/rendredvd.png");
    private static StretchIcon rendrefilms = loadPicture("src/ressources/rendrefilms.png");
    private static StretchIcon retirer = loadPicture("src/ressources/retirer.png");
    private static StretchIcon retour = loadPicture("src/ressources/retour.png");
    private static StretchIcon search = loadPicture("src/ressources/search.png");
    private static StretchIcon signalerProbleme = loadPicture("src/ressources/signalerProbleme.png");
    private static StretchIcon voirfilms = loadPicture("src/ressources/voirfilms.png");

    private static Map<String, StretchIcon> map = hashMapCreate();

    private static Map<String, StretchIcon> hashMapCreate(){
        Map<String, StretchIcon> out = new HashMap<String, StretchIcon>();
        out.put("src/ressources/admin.png", admin);
        out.put("src/ressources/afficherHistorique.png", afficherHistorique);
        out.put("src/ressources/afficherStatistiques.png", afficherStatistiques);
        out.put("src/ressources/ajouterPanier.png", ajouterPanier);
        out.put("src/ressources/Background.png", Background);
        out.put("src/ressources/bluray.png", bluray);
        out.put("src/ressources/button.png", button);
        out.put("src/ressources/compte.png", compte);
        out.put("src/ressources/connexion.png", connexion);
        out.put("src/ressources/disconnect.png", disconnect);
        out.put("src/ressources/inscription.png", inscription);
        out.put("src/ressources/inscriptionpayer.png", inscriptionpayer);
        out.put("src/ressources/ouvrirAL2000.png", ouvrirAL2000);
        out.put("src/ressources/panier.png", panier);
        out.put("src/ressources/parametres.png", parametres);
        out.put("src/ressources/QRCode.png", QRCode);
        out.put("src/ressources/recharger.png", recharger);
        out.put("src/ressources/rendredvd.png", rendredvd);
        out.put("src/ressources/rendrefilms.png", rendrefilms);
        out.put("src/ressources/retirer.png", retirer);
        out.put("src/ressources/retour.png", retour);
        out.put("src/ressources/search.png", search);
        out.put("src/ressources/signalerProbleme.png", signalerProbleme);
        out.put("src/ressources/voirfilms.png", voirfilms);
        return out;
    }
    public static StretchIcon loadPicture(String path){
        try {
            File f = new File(path);
            BufferedImage bimg = ImageIO.read(f);
            int width = bimg.getWidth();
            int height = bimg.getHeight();
            String absPath = f.getAbsolutePath();
            StretchIcon si = new StretchIcon((absPath));
            si.setDescription(width+" "+height);
            return si;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static StretchIcon getPicture(String name){
        return map.get(name);
    }

    public static Dimension getDimensions(StretchIcon s){
        String[] dims = s.getDescription().split(" ");
        return new Dimension(Integer.parseInt(dims[0]), Integer.parseInt(dims[1]));
    }

    public static Dimension scaleDimensionByHeight(Dimension in, double maxHeight){
        /**
         *  Get in a dimension and an expected width and outputs a dimension with the expected width and the new height.
         */
        System.out.println("In W: "+in.getWidth()+"  H: "+in.getHeight());
        Dimension out = new Dimension((int)((maxHeight*in.getWidth())/in.getHeight()), (int)maxHeight);
        System.out.println("Out W: "+out.getWidth()+"  H: "+out.getHeight());
        return out;
    }

    public static StretchIcon getAdmin() {
        return admin;
    }

    public static StretchIcon getAfficherHistorique() {
        return afficherHistorique;
    }

    public static StretchIcon getAfficherStatistiques() {
        return afficherStatistiques;
    }

    public static StretchIcon getAjouterPanier() {
        return ajouterPanier;
    }

    public static StretchIcon getBackground() {
        return Background;
    }

    public static StretchIcon getBluray() {
        return bluray;
    }

    public static StretchIcon getButton() {
        return button;
    }

    public static StretchIcon getCompte() {
        return compte;
    }

    public static StretchIcon getConnexion() {
        return connexion;
    }

    public static StretchIcon getDisconnect() {
        return disconnect;
    }

    public static StretchIcon getInscription() {
        return inscription;
    }

    public static StretchIcon getInscriptionpayer() {
        return inscriptionpayer;
    }

    public static StretchIcon getOuvrirAL2000() {
        return ouvrirAL2000;
    }

    public static StretchIcon getPanier() {
        return panier;
    }

    public static StretchIcon getParametres() {
        return parametres;
    }

    public static StretchIcon getQRCode() {
        return QRCode;
    }

    public static StretchIcon getRecharger() {
        return recharger;
    }

    public static StretchIcon getRendredvd() {
        return rendredvd;
    }

    public static StretchIcon getRendrefilms() {
        return rendrefilms;
    }

    public static StretchIcon getRetirer() {
        return retirer;
    }

    public static StretchIcon getRetour() {
        return retour;
    }

    public static StretchIcon getSearch() {
        return search;
    }

    public static StretchIcon getSignalerProbleme() {
        return signalerProbleme;
    }

    public static StretchIcon getVoirfilms() {
        return voirfilms;
    }
}
