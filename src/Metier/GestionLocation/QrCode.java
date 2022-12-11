package Metier.GestionLocation;


import Metier.GestionMachine.Distributeur;
import Metier.GestionMachine.Inventaire;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
@SuppressWarnings("unused")
public class QrCode extends Support {
        public QrCode(int id, Film film) {
            this.id = id;
            this.film = film;
            prixMax = 5.f;
            prixAboJour = 4.F;
            prixBaseJour = 5.F;
        }

    @Override
    public void sortirFilm(Distributeur distributeur) {
        distributeur.imprimerQRCode(this);
    }

    // Car un QRCode n'a pas d'ID
    @Override
    public int getId(){
            return -1;
    }

    @Override
    public Support supportInverse(Distributeur dist) {
        return dist.chercherBluRay(film);

    }
}

