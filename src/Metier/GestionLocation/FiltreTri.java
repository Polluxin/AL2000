package Metier.GestionLocation;

/**
 * Classe permettant de caractériser un tri. Est composé de deux attributs : tri et valeurDeRecherche.
 * Si et seulement si tri est RECHERCHE, alors valeurDeRecherche est pertinente. Par exemple, tri=RECHERCHE et valeurDeRecherche='Attente',
 * alors le tri est une recherche du film nommé 'Attente'. Si tri=Genre, alors la sélection doit être triée par Genre.
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
