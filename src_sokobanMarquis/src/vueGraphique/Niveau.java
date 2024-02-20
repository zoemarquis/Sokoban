package vueGraphique;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**Classe qui lance un niveau.*/
public class Niveau extends JButton implements ActionListener{
    private String fichier;
    private JFrame fenetre;

    /**Constructeur d'un Niveau.
     * @param nom le texte du bouton dans le menu
     * @param fichier le nom du fichier map correspondant à la carte à ouvrir
     */
    public Niveau(String nom, String fichier, JFrame fenetre){
        super(nom);
        this.setIcon(new ImageIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/canard.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setFocusable(false);
        this.fichier = fichier;
        this.fenetre = fenetre;
    }

    /**Lors d'un clic sur le bouton correspondant au niveau,
     * soit la carte du niveau s'ouvre et le menu se ferme,
     * soit une exception est levée est "carte introuvable" apparaît sur le terminal.
     * @param e l'action du clic
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            new VueSokoban(fichier);
            fenetre.dispose();
        } catch (IOException evt){
            System.out.println("carte introuvable");
        }
    }
}
