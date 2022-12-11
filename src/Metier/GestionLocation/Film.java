package Metier.GestionLocation;

import java.sql.Date;
import java.util.Objects;

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

    /**
     * Film Generique
     */
    public Film(){
        this("Eric le generique",
                "Eric Lanvin",
                new Date(System.currentTimeMillis()),
                "2h00",
                Genre.HORREUR);
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

    @Override
    public String toString() {
        return "Film{" +
                "titre='" + titre + '\'' +
                ", realisateur='" + realisateur + '\'' +
                ", date=" + date +
                ", duree='" + duree + '\'' +
                ", genre=" + genre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(titre, film.titre) && Objects.equals(realisateur, film.realisateur) && Objects.equals(date, film.date) && Objects.equals(duree, film.duree) && genre == film.genre;
    }

}
