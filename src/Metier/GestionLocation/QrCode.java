package Metier.GestionLocation;


import Metier.GestionMachine.Distributeur;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
@SuppressWarnings("unused")
public class QrCode extends Support {

    float prixMax = 5.f;
    float prixAboJour = 4.F;
    float prixBaseJour = 5.F;
        public QrCode(int id, Film film) {
            this.id = id;
            this.film = film;
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
}

