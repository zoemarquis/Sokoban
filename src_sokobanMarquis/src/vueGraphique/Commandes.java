package vueGraphique;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

/** Classe qui affiche les commandes du jeu. */
public class Commandes extends AbstractAction {
    /** Constructeur de Commandes. */
    public Commandes(){
        super("Commandes");
    }
    /**Lors d'un clic,
     * affiche une fenetre de dialogue contenant les différentes commandes du jeu.
     *  @param arg0 l'action du clic*/
    @Override
    public void actionPerformed(ActionEvent arg0) {
        JOptionPane.showMessageDialog(null, "Déplacez le canard en utilisant les flèches (et/ou QZSD et/ou KOLM).\nPour retourner au menu, cliquez sur le premier boutoun.\nPour recommencer le niveau, sélectionnez le deuxième bouton.\nVous pouvez annuler un déplacement avec le dernier bouton.");
    }
}