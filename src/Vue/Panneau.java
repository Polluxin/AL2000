package Vue;

import javax.swing.*;

/**
 * Panneau générique afin d'utiliser toujours activer et desactiver en changement d'etats
 * @author Matvei Pavlov
 */
public abstract class Panneau extends JPanel {
    InterfaceUtilisateur interfaceUtilisateur;
    /**
     * Action effectuée lorsque ce panneau devient le panneau courant.
     */
    public void activer(){}
    /**
     * Action effectuée lorsque ce panneau n'est plus panneau courant.
     */
    public void desactiver(){}

}
