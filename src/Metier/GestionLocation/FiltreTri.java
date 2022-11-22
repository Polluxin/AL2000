package Metier.GestionLocation;

/**
 *
 * @author Geoffrey DAVID
 * @version 0
 */
public class FiltreTri {

    private final Tri tri;

    private final String valeurDeRecherche;

    public FiltreTri(Tri t, String v){
        tri = t;
        valeurDeRecherche = v;
    }

    public String getValeurDeRecherche() {
        return valeurDeRecherche;
    }

    public Tri getTri() {
        return tri;
    }
}
