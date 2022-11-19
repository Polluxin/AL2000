package Metier.GestionLocation;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class Film {
    String titre, realisateur;
    Date date;
    Time duree;
    Genre genre;

    public Film(String titre, String realisateur, Date date, Time duree, Genre genre) {
        this.titre = titre;
        this.realisateur = realisateur;
        this.date = date;
        this.duree = duree;
        this.genre = genre;
    }
}
