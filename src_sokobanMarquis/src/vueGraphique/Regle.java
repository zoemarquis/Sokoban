package vueGraphique;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

/** Classe pour connaitre les règles du jeu. */
public class Regle extends AbstractAction {
    /** Constructeur de Regle. */
    public Regle(){
        super("Règles du jeu");
    }
    /**Lors d'un clic,
     * affiche une fenetre de dialogue contenant les règles du jeu.
     *  @param arg0 l'action du clic*/
    @Override
    public void actionPerformed(ActionEvent arg0) {
        JOptionPane.showMessageDialog(null, "Aidez le canard à sauver ses canetons en les poussant sur les bouées.\nAttention ! Vous ne pouvez bouger qu'un caneton à la fois.\nUn canard ou un caneton ne peut pas aller sur les nénuphars.\n");
    }
}
