# Projet AL2000 - *Novembre 2022*
## Conventions de code
- Commentaires et code en français
- Javadoc pour toutes les classes avec nom(s) de(s) auteur(s)
## Comment écrire de la doc java ?
Un exemple :
```java
/**
* Début de commentaire spécifique à la javadoc. On
* commence par une description générale de la classe.
* Le texte peut être enrichi de balises html :
* <ul>
* <li>pour créer des listes ;</li>
* <li>pour <tt>mettre en valeur</tt> du texte.</li>
* </ul>
*
* @author meta-information
* @version spécifique au module
*/
class Point {
    /** Abscisse */
    private double x;
    /** Ordonnée */
    private double y;
    /**
     * Description de la méthode, ici un recupérateur.
     * @return valeur de l'abscisse.
     */
    double x() {
        return x;
    }
    /**
     * Et ici un modificateur.
     * @param x nouvelle abscisse
     * @param y nouvelle ordonnée
     */
    void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
```
