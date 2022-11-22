package Metier.GestionLocation;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
@SuppressWarnings("EnhancedSwitchMigration")
public enum Genre {
    WESTERN,
    ACTION,
    FANTAISIE,
    ANIME,
    HORREUR,
    SF,
    SUSPENSE,
    ROMANCE;

    public static Genre toGenre(String s){
        switch (s) {
            case "WESTERN":
                return Genre.WESTERN;
            case "ACTION":
                return Genre.ACTION;
            case "FANTAISIE":
                return Genre.FANTAISIE;
            case "ANIME":
                return Genre.ANIME;
            case "HORREUR":
                return Genre.HORREUR;
            case "SF":
                return Genre.SF;
            case "SUSPENSE":
                return Genre.SUSPENSE;
            case "ROMANCE":
                return Genre.ROMANCE;
            default:
                return null;
        }
    }
}