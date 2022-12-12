package Vue;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * OurPictures loads all the pictures used in the program to avoid having to load them multiple times.
 *
 * This is very bulky and repetitive because it's intended to be used in static.
 */
public final class OurPictures {
    private static final StretchIcon admin = loadPicture("src/ressources/admin.png");
    private static final StretchIcon afficherHistorique = loadPicture("src/ressources/afficherHistorique.png");
    private static final StretchIcon afficherStatistiques = loadPicture("src/ressources/afficherStatistiques.png");
    private static final StretchIcon ajouterPanier = loadPicture("src/ressources/ajouterPanier.png");
    private static final StretchIcon Background = loadPicture("src/ressources/Background.png");
    private static final StretchIcon bluray = loadPicture("src/ressources/bluray.png");
    private static final StretchIcon button = loadPicture("src/ressources/button.png");
    private static final StretchIcon compte = loadPicture("src/ressources/compte.png");
    private static final StretchIcon connexion = loadPicture("src/ressources/connexion.png");
    private static final StretchIcon disconnect = loadPicture("src/ressources/disconnect.png");
    private static final StretchIcon inscription = loadPicture("src/ressources/inscription.png");
    private static final StretchIcon inscriptionpayer = loadPicture("src/ressources/inscriptionpayer.png");
    private static final StretchIcon panier = loadPicture("src/ressources/panier.png");
    private static final StretchIcon parametres = loadPicture("src/ressources/parametres.png");
    private static final StretchIcon QRCode = loadPicture("src/ressources/QRCode.png");
    private static final StretchIcon recharger = loadPicture("src/ressources/recharger.png");
    private static final StretchIcon rendredvd = loadPicture("src/ressources/rendredvd.png");
    private static final StretchIcon insertion = loadPicture("src/ressources/insertion.png");
    private static final StretchIcon rendrefilms = loadPicture("src/ressources/rendrefilms.png");
    private static final StretchIcon retirer = loadPicture("src/ressources/retirer.png");
    private static final StretchIcon retour = loadPicture("src/ressources/retour.png");
    private static final StretchIcon search = loadPicture("src/ressources/search.png");
    private static final StretchIcon signalerProbleme = loadPicture("src/ressources/signalerProbleme.png");
    private static final StretchIcon voirfilms = loadPicture("src/ressources/voirfilms.png");
    private static final StretchIcon annuler = loadPicture("src/ressources/annuler.png");
    private static final StretchIcon valider = loadPicture("src/ressources/valider.png");
    private static final StretchIcon admin_pressed = loadPicture("src/ressources/admin.png_pressed.png");
    private static final StretchIcon afficherHistorique_pressed = loadPicture("src/ressources/afficherHistorique.png_pressed.png");
    private static final StretchIcon afficherStatistiques_pressed = loadPicture("src/ressources/afficherStatistiques.png_pressed.png");
    private static final StretchIcon ajouterPanier_pressed = loadPicture("src/ressources/ajouterPanier.png_pressed.png");
    private static final StretchIcon bluray_pressed = loadPicture("src/ressources/bluray.png_pressed.png");
    private static final StretchIcon compte_pressed = loadPicture("src/ressources/compte.png_pressed.png");
    private static final StretchIcon connexion_pressed = loadPicture("src/ressources/connexion.png_pressed.png");
    private static final StretchIcon disconnect_pressed = loadPicture("src/ressources/disconnect.png_pressed.png");
    private static final StretchIcon inscription_pressed = loadPicture("src/ressources/inscription.png_pressed.png");
    private static final StretchIcon inscriptionpayer_pressed = loadPicture("src/ressources/inscriptionpayer.png_pressed.png");
    private static final StretchIcon panier_pressed = loadPicture("src/ressources/panier.png_pressed.png");
    private static final StretchIcon parametres_pressed = loadPicture("src/ressources/parametres.png_pressed.png");
    private static final StretchIcon QRCode_pressed = loadPicture("src/ressources/QRCode.png_pressed.png");
    private static final StretchIcon recharger_pressed = loadPicture("src/ressources/recharger.png_pressed.png");
    private static final StretchIcon rendredvd_pressed = loadPicture("src/ressources/rendredvd.png_pressed.png");
    private static final StretchIcon rendrefilms_pressed = loadPicture("src/ressources/rendrefilms.png_pressed.png");
    private static final StretchIcon retirer_pressed = loadPicture("src/ressources/retirer.png_pressed.png");
    private static final StretchIcon retour_pressed = loadPicture("src/ressources/retour.png_pressed.png");
    private static final StretchIcon search_pressed = loadPicture("src/ressources/search.png_pressed.png");
    private static final StretchIcon signalerProbleme_pressed = loadPicture("src/ressources/signalerProbleme.png_pressed.png");
    private static final StretchIcon voirfilms_pressed = loadPicture("src/ressources/voirfilms.png_pressed.png");
    private static final StretchIcon annuler_pressed = loadPicture("src/ressources/annuler.png_pressed.png");
    private static final StretchIcon valider_pressed = loadPicture("src/ressources/valider.png_pressed.png");
    private static final StretchIcon couverture = loadPicture("src/ressources/couverture.png");
    private static final StretchIcon couverture_pressed = loadPicture("src/ressources/couverture.png_pressed.png");
    private static final StretchIcon chargementGif = loadPicture("src/ressources/Spinner-2.gif");
    private static final Map<String, StretchIcon> map = hashMapCreate();

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
        out.put("src/ressources/valider.png", valider);
        out.put("src/ressources/annuler.png", annuler);
        out.put("src/ressources/admin.png_pressed.png", admin_pressed);
        out.put("src/ressources/afficherHistorique.png_pressed.png", afficherHistorique_pressed);
        out.put("src/ressources/afficherStatistiques.png_pressed.png", afficherStatistiques_pressed);
        out.put("src/ressources/ajouterPanier.png_pressed.png", ajouterPanier_pressed);
        out.put("src/ressources/bluray.png_pressed.png", bluray_pressed);
        out.put("src/ressources/compte.png_pressed.png", compte_pressed);
        out.put("src/ressources/connexion.png_pressed.png", connexion_pressed);
        out.put("src/ressources/disconnect.png_pressed.png", disconnect_pressed);
        out.put("src/ressources/inscription.png_pressed.png", inscription_pressed);
        out.put("src/ressources/inscriptionpayer.png_pressed.png", inscriptionpayer_pressed);
        out.put("src/ressources/panier.png_pressed.png", panier_pressed);
        out.put("src/ressources/parametres.png_pressed.png", parametres_pressed);
        out.put("src/ressources/QRCode.png_pressed.png", QRCode_pressed);
        out.put("src/ressources/recharger.png_pressed.png", recharger_pressed);
        out.put("src/ressources/rendredvd.png_pressed.png", rendredvd_pressed);
        out.put("src/ressources/rendrefilms.png_pressed.png", rendrefilms_pressed);
        out.put("src/ressources/retirer.png_pressed.png", retirer_pressed);
        out.put("src/ressources/retour.png_pressed.png", retour_pressed);
        out.put("src/ressources/search.png_pressed.png", search_pressed);
        out.put("src/ressources/signalerProbleme.png_pressed.png", signalerProbleme_pressed);
        out.put("src/ressources/voirfilms.png_pressed.png", voirfilms_pressed);
        out.put("src/ressources/valider.png_pressed.png", valider_pressed);
        out.put("src/ressources/annuler.png_pressed.png", annuler_pressed);
        out.put("src/ressources/couverture.png", couverture);
        out.put("src/ressources/couverture.png_pressed.png", couverture_pressed);
        out.put("src/ressources/Spinner-2.gif", chargementGif);
        out.put("src/ressources/insertion.png", insertion);
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
            System.out.println("Could not load picture : "+path);
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
        Dimension out = new Dimension((int)((maxHeight*in.getWidth())/in.getHeight()), (int)maxHeight);
        return out;
    }

    public static StretchIcon getBluray() {
        return bluray;
    }

    public static StretchIcon getConnexion() {
        return connexion;
    }

}
