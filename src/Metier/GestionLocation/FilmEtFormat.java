package Metier.GestionLocation;

public class FilmEtFormat {
    private final Film film;
    private final boolean estDispoEnPhysique;
    public FilmEtFormat(Film film, boolean b){
        this.film = film;
        estDispoEnPhysique = b;
    }

    public boolean estDispoEnPhysique(){
        return estDispoEnPhysique;
    }

    public Film getFilm() {
        return film;
    }

    @Override
    public String toString() {
        return "FilmEtFormat{" +
                "film=" + film +
                ", estDispoEnPhysique=" + estDispoEnPhysique +
                '}';
    }
}
