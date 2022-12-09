package Vue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Film {
    String titre;

    String realisateur;
    Date dateSortie;
    int duree;
    String genre;
    StretchIcon couverture;
    public Film(String titre, String couverture, String realisateur, Date date, int duree, String genre){
        this.titre = titre;
        this.realisateur = realisateur;
        this.dateSortie = date;
        this.duree = duree;
        this.genre = genre;
        this.couverture = OurPictures.loadPicture(couverture);
    }

    public Film(){
        this("Genial Eric le Generique","src/ressources/couverture.png", "Eric Besnard", new GregorianCalendar(2022, Calendar.NOVEMBER, 27).getTime(), 220, "Comédieé");
    }

    public String getTitre() {
        return titre;
    }


    public StretchIcon getCouverture() {
        return couverture;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public int getDuree() {
        return duree;
    }

    public String getGenre() {
        return genre;
    }

}
