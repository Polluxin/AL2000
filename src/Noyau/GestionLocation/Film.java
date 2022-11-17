package Noyau.GestionLocation;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class Film {
    String titre, date, realisateur, duree;
    Genre genre;

    public Film(String titre, String date, String realisateur, String duree, Genre genre) {
        this.titre = titre;
        this.date = date;
        this.realisateur = realisateur;
        this.duree = duree;
        this.genre = genre;
    }
}
