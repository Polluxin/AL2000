package Tests.BD;

import Metier.GestionClient.Abonne;

@SuppressWarnings("unused")
public class TestCarte {
    public static void main(String[] args) {
        // On suppose la base créée

        System.out.println("Non implémenté");

//        Session session = new Session();
//        System.out.println("Carte :");
//        AbonneDAO dao = new AbonneDAO(session.getSession());
//        session.open();
//        try {
//            ResultSet res = session.getSession().createStatement().executeQuery("SELECT MAX(idCarte) FROM LESCARTES");
//            int id = res.getInt("idabo") + 1;
//            // Pour la CA : Ajout d'une carte
//            Abonne abo1 = new Abonne(null, null, "Test", "Testeur",
//                    "test@test.fr", "2 avenue du Test 36400 Grenoble", "test");
//            dao.creer(abo1);
//            // Questionnement de la base pour vérifier
//            boolean test1 = testConcordanceEcritureBD(abo1, "SELECT * FROM LESCA WHERE idCarte="+id);
//            System.out.println("Test d'inscription: "+test1);
//            // Lecture de la même carte pour vérifier
//            Abonne abo2 = dao.lire(id);
//            boolean test2 = testConcordanceLectureBD(abo1, abo2);
//            System.out.println("Test de connexion: "+test2);
//            // Pour la CB
//
//        } catch (SQLException e){ e.printStackTrace(); return;}
    }

    public static boolean testConcordanceEcritureBD(Abonne a, String req){
        return false;
    }

    public static boolean testConcordanceLectureBD(Abonne abo1, Abonne abo2){
        return false;
    }
}
