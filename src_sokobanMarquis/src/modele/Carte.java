package modele;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

/** Classe qui modélise le modèle d'une carte / d'un niveau de jeu. */
public class Carte {
    private int nbLigne;
    private int tailleUneLigne;
    private Case[][] map;
    private Robot robot;
    private List<Couple> lesDests = new ArrayList<Couple>();
    private List<Couple> lesSols = new ArrayList<Couple>();
    private List<String> mapCaractere;
    private Deque<Deplacement> pile = new ArrayDeque<Deplacement>();

    /** COnstructeur d'une Carte.
     * @param fichier le nom du fichier où se trouve la map
     * @throws IOException lance une exception si cette map est introuvable
     */
    public Carte(String fichier) throws IOException{
        Lecture l = new Lecture(fichier);
        mapCaractere = l.getMapCaractere();
        this.nbLigne = l.getNbLigne();
        this.tailleUneLigne = l.getTailleUneLigne();
        this.map = new Case[nbLigne][tailleUneLigne];
        recommencer();
    }

    /** Retourne la Carte sous forme une chaine de caractères.
     * @return une chaine de caractères
     */
    public String toString(){
        StringBuffer chr = new StringBuffer();
        for(int i = 0; i<nbLigne; i++){
            for (int j = 0; j<tailleUneLigne; j++){
                chr.append(map[i][j].toString());
            }
            chr.append("\n");
        }
        return chr.toString();
    }

    /** Retourne le nombre de ligne.
     * @return le nombre de ligne
     */
    public int getNbLigne() {
        return nbLigne;
    }
    /** Retourne le nombre de colonne.
     * @return le nombre de colonne
     */
    public int getTailleUneLigne() {
        return tailleUneLigne;
    }

    /** Retourne le caractère contenu dans une case du plateau.
     * @param x la ligne de la case
     * @param y la colonne de la case
     * @return le caractère sous forme de chaine de caractères
     */
    public String getCase(int x, int y) {
        return map[x][y].toString();
    }

    /** Déplace le robot dans la direction donnée si c'est possible
     * puis met à jour le plateau de cases et la pile des déplacements.
     * @param dir le robot doit essayer de se déplacer dans cette direction
     * @return vrai si le robot s'est déplacé
     */
    public boolean deplaceRobot(Direction dir){
        // dans la map
        if ((this.robot.getX() + dir.getX()) >= 0 && (this.robot.getX() + dir.getX()) < this.nbLigne
        && (this.robot.getY() + dir.getY()) >=0 && (this.robot.getY() + dir.getY() < this.tailleUneLigne)){
            if (this.map[(robot.getX() + dir.getX())][(robot.getY() + dir.getY())].peutAccueillirJoueurOuCaisse()){
               
                boolean caisseDeplacee = false;
                if (this.map[(robot.getX() + dir.getX())][(robot.getY() + dir.getY())].caisseIci()){
                    caisseDeplacee = deplaceCaisse(dir, (robot.getX() + dir.getX()), (robot.getY() + dir.getY()));
                    if (!caisseDeplacee){
                        return false;
                    }
                }

                this.map[robot.getX()][robot.getY()].setRobotParti();
                this.map[(robot.getX() + dir.getX())][(robot.getY() + dir.getY())].setRobotIci();

                robot.deplace(dir);

                if (caisseDeplacee){
                    pile.push(new Deplacement(true, dir));
                } else {
                    pile.push(new Deplacement(false, dir));
                }
                return true;
            }
        }
        return false;
    }

     /** Déplace une caisse dans la direction donnée si c'est possible
     * puis met à jour le plateau de cases.
     * @param dir la caisse doit essayer de se déplacer dans cette direction
     * @return vrai si la caisse s'est déplacée
     */
    public boolean deplaceCaisse(Direction dir, int caissex, int caissey){
        // dans la map
        if ((caissex + dir.getX()) >= 0 && (caissex + dir.getX()) < this.nbLigne
        && (caissey + dir.getY()) >=0 && (caissey + dir.getY() < this.tailleUneLigne)){ 

            // est une case qui peut accueillir une caisse (ou un robot)
            if (this.map[(caissex + dir.getX())][(caissey + dir.getY())].peutAccueillirJoueurOuCaisse()){

                // si il y a une caisse dans la classe d'après : ça bloque
                if (this.map[(caissex + dir.getX())][(caissey + dir.getY())].caisseIci()){
                    return false;
                }

                // enlever le robot de la case avant 
                this.map[caissex][caissey].setCaisseParti();
                // mettre le robot dans la nouvelle case
                this.map[(caissex + dir.getX())][(caissey + dir.getY())].setCaisseIci();

                return true;
            }
        }
        return false;
    }

    /** Teste la fin de partie.
     * @return vrai si la partie est terminée : toutes les destinations ont une caisse.
     */
    public boolean finDePartie(){
        // pour chaque destination, y a t il une caisse dessus ?
        for (Couple couple : lesDests){
            if(!this.map[couple.getX()][couple.getY()].caisseIci()){
                return false;
            }
        }
        return true;
    }

    /** Retoure la liste des couples où se trouve les destinations.
     * @return une liste de couple
     */
    public List<Couple> getLesDests() {
        return lesDests;
    }
    /** Retoure la liste des couples où se trouve les sols.
     * @return une liste de couple
     */
    public List<Couple> getLesSols() {
        return lesSols;
    }

    /** Retourne l'état du robot.
     * @return la dernière direction du robot
     */
    public Direction getEtatRobot(){
        return robot.getEtat();
    }
    /** Modifie l'état du robot.
     * @param etat le nouvel état du robot
     */
    public void setEtatRobot(Direction etat){
        robot.setEtat(etat);
    }
    /** Retourne le numéro de ligne où se trouve le robot.
     * @return la ligne du robot
     */
    public int getRobotX(){
        return robot.getX();
    }
    /** Retourne le numéro de colonne où se trouve le robot.
     * @return la colonne du robot
     */
    public int getRobotY(){
        return robot.getY();
    }

    /** Permet de recommencer une partie : 
     * réinitialiser le plateau de cases.
     */
    public void recommencer(){
        lesSols.clear();
        lesDests.clear();
        pile.clear();
        for (int i = 0; i<this.nbLigne; i++){
            for (int j = 0; j<this.tailleUneLigne; j++){
                char carActuel = mapCaractere.get(i).charAt(j);
                switch (carActuel){
                    case '/':
                    this.map[i][j] = new Vide();
                    break;
                    case '#':
                    this.map[i][j] = new Mur();
                    break;
                    case ' ':
                    this.map[i][j] = new Sol();
                    lesSols.add(new Couple(i,j));
                    break;
                    case '.':
                    this.map[i][j] = new Dest();
                    lesDests.add(new Couple(i,j));
                    break;
                    case '$':
                    this.map[i][j] = new Sol();
                    this.map[i][j].setCaisseIci();
                    lesSols.add(new Couple(i,j));
                    break;
                    case '@':
                    this.map[i][j] = new Sol();
                    this.map[i][j].setRobotIci();
                    robot = new Robot(i,j);
                    lesSols.add(new Couple(i,j));
                    break;
                    case '+':
                    this.map[i][j] = new Dest();
                    this.map[i][j].setRobotIci();
                    robot = new Robot(i,j);
                    lesDests.add(new Couple(i,j));
                    break;
                    case '*':
                    this.map[i][j] = new Dest();
                    this.map[i][j].setCaisseIci();
                    lesDests.add(new Couple(i,j));
                    break;
                }
            }
        }
    }

    /** Permet d'annuler le dernier mouvement du robot.
     * @return vrai si l'annulation z eu lieu
     */
    public boolean annulerUnMouvement(){
        try{
            Deplacement ancien = pile.pop();
            if (ancien.getCaisseDeplacee()){
                this.map[robot.getX()+ ancien.getDirectionAvant().getX()][robot.getY()+ ancien.getDirectionAvant().getY()].setCaisseParti();
                this.map[robot.getX()][robot.getY()].setCaisseIci();
            }
        
            this.map[robot.getX()][robot.getY()].setRobotParti();
            this.map[(robot.getX() - ancien.getDirectionAvant().getX())][(robot.getY() - ancien.getDirectionAvant().getY())].setRobotIci();

            setEtatRobot(ancien.getDirectionAvant());
            robot.deplace(ancien.getDirectionAvant().directionInverse());
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }

}