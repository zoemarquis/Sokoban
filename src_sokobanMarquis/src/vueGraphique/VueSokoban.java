package vueGraphique;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import modele.Carte;
import modele.Direction;
import modele.Couple;

/** Classe qui modélise un niveau de jeu. */
public class VueSokoban extends JFrame implements KeyListener{
    private Carte map;
    private HashMap<String, ImageIcon> hm_image = new HashMap<String, ImageIcon>();
    private HashMap<Integer, Direction> hm_deplacement = new HashMap<Integer, Direction>(); 
    private HashMap<Direction, ImageIcon> hm_robot = new HashMap<Direction, ImageIcon>();
    private JLabel[][] plateau;
    private int compteur;
    private int cptRetour;
    private JLabel coups;
    private JLabel gagne;
    private JLabel retour;
    private JButton back; 

    /** Lance le dialogue 
     * @param ke déclencheur lors d'un appui sur une touche
    */
    @Override
    public void keyPressed(KeyEvent ke) {
        dialogue(ke);
    }
    /** Ne fait rien. 
     * @param arg0 déclencheur touche relâchée
    */
    @Override
    public void keyReleased(KeyEvent arg0) {
    }
    /** Ne fait rien. 
     * @param arg0 déclencheur
    */
    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    /** Constructeur d'une VueSokoban.
     * @param fichier le nom de fichier de la carte 
     * @throws IOException lance une exception si la map n'existe pas
     */
    public VueSokoban(String fichier) throws IOException{
        super("Sokoban");
        this.map = new Carte(fichier);
        plateau = new JLabel[map.getNbLigne()][map.getTailleUneLigne()];
        
        hm_image.put(" ", new ImageIcon(this.getClass().getClassLoader().getResource("img/sol.png")));
        hm_image.put(".", new ImageIcon(this.getClass().getClassLoader().getResource("img/but.png")));
        hm_image.put("$", new ImageIcon(this.getClass().getClassLoader().getResource("img/caisse1.png")));
        hm_image.put("#", new ImageIcon(this.getClass().getClassLoader().getResource("img/mur.png")));
        hm_image.put("@", new ImageIcon(this.getClass().getClassLoader().getResource("img/Bas.png")));
        hm_image.put("+", new ImageIcon(this.getClass().getClassLoader().getResource("img/Bas.png")));
        hm_image.put("*", new ImageIcon(this.getClass().getClassLoader().getResource("img/caisse2.png")));
        hm_image.put("/", new ImageIcon(this.getClass().getClassLoader().getResource("img/vide.png")));

        hm_deplacement.put(KeyEvent.VK_LEFT, Direction.GAUCHE);
        hm_deplacement.put(KeyEvent.VK_K, Direction.GAUCHE);
        hm_deplacement.put(KeyEvent.VK_Q, Direction.GAUCHE);
        hm_deplacement.put(KeyEvent.VK_RIGHT, Direction.DROITE);
        hm_deplacement.put(KeyEvent.VK_M, Direction.DROITE);
        hm_deplacement.put(KeyEvent.VK_D, Direction.DROITE);
        hm_deplacement.put(KeyEvent.VK_UP, Direction.HAUT);
        hm_deplacement.put(KeyEvent.VK_O, Direction.HAUT);
        hm_deplacement.put(KeyEvent.VK_Z, Direction.HAUT);
        hm_deplacement.put(KeyEvent.VK_DOWN, Direction.BAS);
        hm_deplacement.put(KeyEvent.VK_S, Direction.BAS);
        hm_deplacement.put(KeyEvent.VK_L, Direction.BAS);

        hm_robot.put(Direction.BAS, new ImageIcon(this.getClass().getClassLoader().getResource("img/Bas.png")));
        hm_robot.put(Direction.HAUT, new ImageIcon(this.getClass().getClassLoader().getResource("img/Haut.png")));
        hm_robot.put(Direction.GAUCHE, new ImageIcon(this.getClass().getClassLoader().getResource("img/Gauche.png")));
        hm_robot.put(Direction.DROITE, new ImageIcon(this.getClass().getClassLoader().getResource("img/Droite.png")));
        
        this.addKeyListener(this);
        this.setLayout(new BorderLayout());

        Container contPlateau = new Container();
        contPlateau.setLayout(new GridLayout(map.getNbLigne(),map.getTailleUneLigne()));

        for (int i=0; i<map.getNbLigne(); i++){
            for (int j=0; j<map.getTailleUneLigne(); j++){
                plateau[i][j] = new JLabel(hm_image.get(map.getCase(i, j)));
                contPlateau.add(plateau[i][j]);
            }
        }

        this.add(contPlateau, BorderLayout.CENTER);

        JToolBar bar = new JToolBar();
        bar.setOrientation(JToolBar.VERTICAL);
        JButton retourMenu = new JButton(new ImageIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/menu.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        retourMenu.addActionListener(new ActionListener(){
            /** Ferme le niveau actuel et ouvre le menu.
             * @param e l'action du clic
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Menu();
            }
        });
        retourMenu.setFocusable(false);
        bar.add(retourMenu);
        
        JButton recommencer = new JButton(new ImageIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/recommencer.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        recommencer.addActionListener(new ActionListener(){
            /** Lors d'un clic sur le bouton,
             * redémarre la partie.
             * @param e l'action du clic
             */
            public void actionPerformed(ActionEvent e){
                map.recommencer();
                back.setEnabled(false);
                compteur = 0;
                cptRetour = 0;
                miseAJourToutPlateau();
                coups.setText("Nombre de déplacement");
                retour.setText("Nombre de retour ");
                gagne.setText("Sauvez les canetons !");
            }
        });
        recommencer.setFocusable(false);
        bar.add(recommencer);

        back = new JButton(new ImageIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/annuler.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        back.addActionListener(new ActionListener(){
            /**Lors d'un clic sur le bouton correspondant,
             * si c'est posssible, annule le dernier mouvement et
             * met à jour la vue graphique correspondante.
             * @param e l'action du clic
            */
            public void actionPerformed(ActionEvent e){
                boolean b = map.annulerUnMouvement();
                if (b){
                    compteur--;
                    cptRetour++;
                    miseAJourToutPlateau();
                }
            }
        });
        back.setFocusable(false);
        back.setEnabled(false);
        bar.add(back);
        this.add(bar, BorderLayout.WEST);

        Container bas = new Container();  
        bas.setLayout(new BorderLayout());
        gagne = new JLabel("Sauvez les canetons !");
        bas.add(gagne, BorderLayout.NORTH);
        coups = new JLabel("Nombre de déplacement");
        bas.add(coups, BorderLayout.WEST);
        retour = new JLabel("Nombre de retour ");
        bas.add(retour, BorderLayout.EAST);
        this.add(bas, BorderLayout.SOUTH);

        JMenuBar menubar = new JMenuBar();
        JMenu aide = new JMenu("?");
        JMenuItem apropos = new JMenuItem(new APropos());
        JMenu jeu = new JMenu("Jeu");
        JMenuItem regle = new JMenuItem(new Regle());
        JMenuItem commandes = new JMenuItem(new Commandes());
        JMenu niveaux = new JMenu("Niveaux");
        
        aide.add(apropos);
        jeu.add(regle);
        jeu.add(commandes);
        Niveau niveau1 = new Niveau("Niveau 1","map/map1.txt",this);
        Niveau niveau2 = new Niveau("Niveau 2","map/map2.txt",this);
        Niveau niveau3 = new Niveau("Niveau 3","map/map3.txt",this);
        niveaux.add(niveau1);
        niveaux.add(niveau2);
        niveaux.add(niveau3);
        niveau1.addActionListener(niveau1);
        niveau2.addActionListener(niveau2);
        niveau3.addActionListener(niveau3);
        menubar.add(jeu);
        menubar.add(niveaux);
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

    /** Lorsqu'une touche est enfoncée,
     * si elle permet le déplacement du robot, alors le robot se déplace.
     * (mise à jour de la Carte et de la vue graphique)
     * Sinon, "erreur touche" apparait sur la console.
     * Si la partie est terminée, met à jour la vue graphique.
     * @param ke l'action d'une touche enfoncée
     */
    public void dialogue(KeyEvent ke){
        if (!map.finDePartie()){
            if (hm_deplacement.get(ke.getKeyCode())==null){
                System.out.println("erreur touche !");
            } else {
                if(map.deplaceRobot(hm_deplacement.get(ke.getKeyCode()))){
                    map.setEtatRobot(hm_deplacement.get(ke.getKeyCode()));
                    compteur ++;
                    miseAJourToutPlateau();
                }
            }
            if (map.finDePartie()){
                gagne.setText("Gagné !!!");
                back.setEnabled(false);
            }
        }
    }

    /** Met à jour la vue graphique : les cases Sols et Destinations seulement
     * ainsi que les compteurs et la disponibilité du bouton pour annuler le dernier déplacement.
     */
    public void miseAJourToutPlateau(){
        for(Couple c : map.getLesDests()){
            plateau[c.getX()][c.getY()].setIcon(hm_image.get(map.getCase(c.getX(), c.getY())));
        }
        for(Couple c : map.getLesSols()){
            plateau[c.getX()][c.getY()].setIcon(hm_image.get(map.getCase(c.getX(), c.getY())));
        }
        plateau[map.getRobotX()][map.getRobotY()].setIcon(hm_robot.get(map.getEtatRobot()));
        if (compteur <= 1){
            coups.setText(String.valueOf(compteur)+" déplacement");
        } else {
            coups.setText(String.valueOf(compteur)+" déplacements");
        }
        if (compteur == 0){
            back.setEnabled(false);
        } else {
            back.setEnabled(true);
        }
        if (cptRetour <= 1){
            retour.setText(String.valueOf(cptRetour+" retour "));
        } else {
            retour.setText(String.valueOf(cptRetour+" retours "));
        }
    }
}
