package vueGraphique;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
/** Classe pour en savoir plus. */
public class APropos extends AbstractAction {
    /**Constructeur de APropos.*/
    public APropos(){
        super("À propos");
    }
    /**Lors d'un clic,
     * ouvre une fenetre de dialogue avec les informations du jeu. 
     * @param arg0 l'action du clic*/
    @Override
    public void actionPerformed(ActionEvent arg0) {
        JOptionPane.showMessageDialog(null, "Jeu du Sokoban\nAuteur : Zoé Marquis");
    }
}
