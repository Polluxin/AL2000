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
    private static final StretchIcon admin = loadPicture("res/ressources/admin.png");
    private static final StretchIcon afficherHistorique = loadPicture("res/ressources/afficherHistorique.png");
    private static final StretchIcon afficherStatistiques = loadPicture("res/ressources/afficherStatistiques.png");
    private static final StretchIcon ajouterPanier = loadPicture("res/ressources/ajouterPanier.png");
    private static final StretchIcon Background = loadPicture("res/ressources/Background.png");
    private static final StretchIcon bluray = loadPicture("res/ressources/bluray.png");
    private static final StretchIcon button = loadPicture("res/ressources/button.png");
    private static final StretchIcon compte = loadPicture("res/ressources/compte.png");
    private static final StretchIcon connexion = loadPicture("res/ressources/connexion.png");
    private static final StretchIcon disconnect = loadPicture("res/ressources/disconnect.png");
    private static final StretchIcon inscription = loadPicture("res/ressources/inscription.png");
    private static final StretchIcon inscriptionpayer = loadPicture("res/ressources/inscriptionpayer.png");
    private static final StretchIcon panier = loadPicture("res/ressources/panier.png");
    private static final StretchIcon parametres = loadPicture("res/ressources/parametres.png");
    private static final StretchIcon QRCode = loadPicture("res/ressources/QRCode.png");
    private static final StretchIcon recharger = loadPicture("res/ressources/recharger.png");
    private static final StretchIcon rendredvd = loadPicture("res/ressources/rendredvd.png");
    private static final StretchIcon insertion = loadPicture("res/ressources/insertion.png");
    private static final StretchIcon rendrefilms = loadPicture("res/ressources/rendrefilms.png");
    private static final StretchIcon retirer = loadPicture("res/ressources/retirer.png");
    private static final StretchIcon retour = loadPicture("res/ressources/retour.png");
    private static final StretchIcon search = loadPicture("res/ressources/search.png");
    private static final StretchIcon signalerProbleme = loadPicture("res/ressources/signalerProbleme.png");
    private static final StretchIcon voirfilms = loadPicture("res/ressources/voirfilms.png");
    private static final StretchIcon annuler = loadPicture("res/ressources/annuler.png");
    private static final StretchIcon valider = loadPicture("res/ressources/valider.png");
    private static final StretchIcon admin_pressed = loadPicture("res/ressources/admin.png_pressed.png");
    private static final StretchIcon afficherHistorique_pressed = loadPicture("res/ressources/afficherHistorique.png_pressed.png");
    private static final StretchIcon afficherStatistiques_pressed = loadPicture("res/ressources/afficherStatistiques.png_pressed.png");
    private static final StretchIcon ajouterPanier_pressed = loadPicture("res/ressources/ajouterPanier.png_pressed.png");
    private static final StretchIcon bluray_pressed = loadPicture("res/ressources/bluray.png_pressed.png");
    private static final StretchIcon compte_pressed = loadPicture("res/ressources/compte.png_pressed.png");
    private static final StretchIcon connexion_pressed = loadPicture("res/ressources/connexion.png_pressed.png");
    private static final StretchIcon disconnect_pressed = loadPicture("res/ressources/disconnect.png_pressed.png");
    private static final StretchIcon inscription_pressed = loadPicture("res/ressources/inscription.png_pressed.png");
    private static final StretchIcon inscriptionpayer_pressed = loadPicture("res/ressources/inscriptionpayer.png_pressed.png");
    private static final StretchIcon panier_pressed = loadPicture("res/ressources/panier.png_pressed.png");
    private static final StretchIcon parametres_pressed = loadPicture("res/ressources/parametres.png_pressed.png");
    private static final StretchIcon QRCode_pressed = loadPicture("res/ressources/QRCode.png_pressed.png");
    private static final StretchIcon recharger_pressed = loadPicture("res/ressources/recharger.png_pressed.png");
    private static final StretchIcon rendredvd_pressed = loadPicture("res/ressources/rendredvd.png_pressed.png");
    private static final StretchIcon rendrefilms_pressed = loadPicture("res/ressources/rendrefilms.png_pressed.png");
    private static final StretchIcon retirer_pressed = loadPicture("res/ressources/retirer.png_pressed.png");
    private static final StretchIcon retour_pressed = loadPicture("res/ressources/retour.png_pressed.png");
    private static final StretchIcon search_pressed = loadPicture("res/ressources/search.png_pressed.png");
    private static final StretchIcon signalerProbleme_pressed = loadPicture("res/ressources/signalerProbleme.png_pressed.png");
    private static final StretchIcon voirfilms_pressed = loadPicture("res/ressources/voirfilms.png_pressed.png");
    private static final StretchIcon annuler_pressed = loadPicture("res/ressources/annuler.png_pressed.png");
    private static final StretchIcon valider_pressed = loadPicture("res/ressources/valider.png_pressed.png");
    private static final StretchIcon couverture = loadPicture("res/ressources/couverture.png");
    private static final StretchIcon couverture_pressed = loadPicture("res/ressources/couverture.png_pressed.png");
    private static final StretchIcon chargementGif = loadPicture("res/ressources/Spinner-2.gif");
    private static final Map<String, StretchIcon> map = hashMapCreate();

    private static Map<String, StretchIcon> hashMapCreate(){
        Map<String, StretchIcon> out = new HashMap<String, StretchIcon>();
        out.put("res/ressources/admin.png", admin);
        out.put("res/ressources/afficherHistorique.png", afficherHistorique);
        out.put("res/ressources/afficherStatistiques.png", afficherStatistiques);
        out.put("res/ressources/ajouterPanier.png", ajouterPanier);
        out.put("res/ressources/Background.png", Background);
        out.put("res/ressources/bluray.png", bluray);
        out.put("res/ressources/button.png", button);
        out.put("res/ressources/compte.png", compte);
        out.put("res/ressources/connexion.png", connexion);
        out.put("res/ressources/disconnect.png", disconnect);
        out.put("res/ressources/inscription.png", inscription);
        out.put("res/ressources/inscriptionpayer.png", inscriptionpayer);
        out.put("res/ressources/panier.png", panier);
        out.put("res/ressources/parametres.png", parametres);
        out.put("res/ressources/QRCode.png", QRCode);
        out.put("res/ressources/recharger.png", recharger);
        out.put("res/ressources/rendredvd.png", rendredvd);
        out.put("res/ressources/rendrefilms.png", rendrefilms);
        out.put("res/ressources/retirer.png", retirer);
        out.put("res/ressources/retour.png", retour);
        out.put("res/ressources/search.png", search);
        out.put("res/ressources/signalerProbleme.png", signalerProbleme);
        out.put("res/ressources/voirfilms.png", voirfilms);
        out.put("res/ressources/valider.png", valider);
        out.put("res/ressources/annuler.png", annuler);
        out.put("res/ressources/admin.png_pressed.png", admin_pressed);
        out.put("res/ressources/afficherHistorique.png_pressed.png", afficherHistorique_pressed);
        out.put("res/ressources/afficherStatistiques.png_pressed.png", afficherStatistiques_pressed);
        out.put("res/ressources/ajouterPanier.png_pressed.png", ajouterPanier_pressed);
        out.put("res/ressources/bluray.png_pressed.png", bluray_pressed);
        out.put("res/ressources/compte.png_pressed.png", compte_pressed);
        out.put("res/ressources/connexion.png_pressed.png", connexion_pressed);
        out.put("res/ressources/disconnect.png_pressed.png", disconnect_pressed);
        out.put("res/ressources/inscription.png_pressed.png", inscription_pressed);
        out.put("res/ressources/inscriptionpayer.png_pressed.png", inscriptionpayer_pressed);
        out.put("res/ressources/panier.png_pressed.png", panier_pressed);
        out.put("res/ressources/parametres.png_pressed.png", parametres_pressed);
        out.put("res/ressources/QRCode.png_pressed.png", QRCode_pressed);
        out.put("res/ressources/recharger.png_pressed.png", recharger_pressed);
        out.put("res/ressources/rendredvd.png_pressed.png", rendredvd_pressed);
        out.put("res/ressources/rendrefilms.png_pressed.png", rendrefilms_pressed);
        out.put("res/ressources/retirer.png_pressed.png", retirer_pressed);
        out.put("res/ressources/retour.png_pressed.png", retour_pressed);
        out.put("res/ressources/search.png_pressed.png", search_pressed);
        out.put("res/ressources/signalerProbleme.png_pressed.png", signalerProbleme_pressed);
        out.put("res/ressources/voirfilms.png_pressed.png", voirfilms_pressed);
        out.put("res/ressources/valider.png_pressed.png", valider_pressed);
        out.put("res/ressources/annuler.png_pressed.png", annuler_pressed);
        out.put("res/ressources/couverture.png", couverture);
        out.put("res/ressources/couverture.png_pressed.png", couverture_pressed);
        out.put("res/ressources/Spinner-2.gif", chargementGif);
        out.put("res/ressources/insertion.png", insertion);
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
    /**
     *  Get in a dimension and an expected width and outputs a dimension with the expected width and the new height.
     */
    public static Dimension scaleDimensionByHeight(Dimension in, double maxHeight){
        return new Dimension((int)((maxHeight*in.getWidth())/in.getHeight()), (int)maxHeight);
    }

}
