package Metier.GestionLocation;

import java.sql.Date;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
@SuppressWarnings("unused")
public class Film {
    String titre, realisateur;
    Date date;
    String duree;
    Genre genre;

    public Film(String titre, String realisateur, Date date,  String duree, Genre genre) {
        this.titre = titre;
        this.realisateur = realisateur;
        this.date = date;
        this.duree = duree;
        this.genre = genre;
    }

    public String getTitre() {
        return titre;
    }

    public Date getDate() {
        return date;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public String getDuree() {
        return duree;
    }

}
