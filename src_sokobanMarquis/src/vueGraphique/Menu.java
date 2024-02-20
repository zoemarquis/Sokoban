package vueGraphique;
import java.awt.*;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.*;

/**Classe qui modélise un menu graphique. */
public class Menu extends JFrame{
    
    private HashMap<Integer, ImageIcon> hm_image = new HashMap<Integer, ImageIcon>();
    private int tempo;
    private Timer timer;

    /**Constructeur d'un Menu.*/
    public Menu(){
        super("Menu");
       
        hm_image.put(0, new ImageIcon(this.getClass().getClassLoader().getResource("img/Bas.png")));
        hm_image.put(1, new ImageIcon(this.getClass().getClassLoader().getResource("img/Gauche.png")));
        hm_image.put(2, new ImageIcon(this.getClass().getClassLoader().getResource("img/Haut.png")));
        hm_image.put(3, new ImageIcon(this.getClass().getClassLoader().getResource("img/Droite.png")));
        hm_image.put(4, new ImageIcon(this.getClass().getClassLoader().getResource("img/caisse1.png")));

        this.setLayout(new BorderLayout());

        JPanel gaucheDroite = new JPanel();
        gaucheDroite.setLayout(new GridLayout(1,2));
        this.add(gaucheDroite);

        Container lesBoutons = new Container();
        lesBoutons.setLayout(new BoxLayout(lesBoutons, BoxLayout.Y_AXIS));

        Niveau niveau1 = new Niveau("Niveau 1","map/map1.txt",this);
        Niveau niveau2 = new Niveau("Niveau 2","map/map2.txt",this);
        Niveau niveau3 = new Niveau("Niveau 3","map/map3.txt",this);
        niveau1.addActionListener(niveau1);
        niveau2.addActionListener(niveau2);
        niveau3.addActionListener(niveau3);
        lesBoutons.add(niveau1);
        lesBoutons.add(niveau2);      
        lesBoutons.add(niveau3);
        gaucheDroite.add(lesBoutons);

        JLabel robot = new JLabel(hm_image.get(tempo));
        JPanel images = new JPanel();
        GridLayout layout = new GridLayout(3,3);
        images.setLayout(layout);
        for (int i = 0; i<4; i++){
            images.add(new JLabel(hm_image.get(4)));
        }
        images.add(robot);
        for (int i = 0; i<4; i++){
            images.add(new JLabel(hm_image.get(4)));
        }
        ActionListener tourne = new ActionListener(){
            /**Permet à la case représentant le robot de tourner
             * (de changer de vue) toutes les quelques secondes.
             * @param evt déclencheur 
             */
            public void actionPerformed(ActionEvent evt){
                tempo++;
                if (tempo==4){
                    tempo = 0;
                }
                robot.setIcon(hm_image.get(tempo));
            }
        };
        timer = new Timer(400, tourne);
        timer.start();
        gaucheDroite.add(images);

        JMenuBar menubar = new JMenuBar();
        JMenu aide = new JMenu("À propos");
        JMenuItem apropos = new JMenuItem(new APropos());
        JMenu jeu = new JMenu("Jeu");
        JMenuItem regle = new JMenuItem(new Regle());
        JMenuItem commandes = new JMenuItem(new Commandes());
        aide.add(apropos);
        jeu.add(regle);
        jeu.add(commandes);
        menubar.add(jeu);
        menubar.add(aide);
        this.add(menubar, BorderLayout.NORTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        Dimension dimEcran = this.getToolkit().getScreenSize();
        int largeur = (int) dimEcran.getWidth()/2 - this.getWidth()/2;
        int hauteur = (int) dimEcran.getHeight()/2 - this.getHeight()/2;
        this.setLocation(largeur, hauteur);
        this.setVisible(true);
    }
}
