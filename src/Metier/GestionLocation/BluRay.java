package Metier.GestionLocation;
/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class BluRay extends Support {
    int id;
    Film film;

    public BluRay(int id, Film film) {
        this.id = id;
        this.film = film;
    }

    public Film getFilm() {
        return film;
    }

    public int getId() {
        return id;
    }
}