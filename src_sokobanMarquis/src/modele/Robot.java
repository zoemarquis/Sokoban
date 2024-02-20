package modele;
/** Classe qui modélise le robot. */
public class Robot{

    private int x;
    private int y;
    private Direction etat;
    /**Constructeur du robot.
     * @param x la ligne de la case où il se situe à sa création
     * @param y la colonne de la case où il se situe à sa création
     */
    public Robot(int x, int y){
        this.x = x;
        this.y = y;
        this.etat = Direction.BAS;
    }
    /** Retourne le numéro de ligne où est le robot.
     * @return le numéro de ligne
     */
    public int getX() {
        return x;
    }
    /** Retourne le numéro de colonne où est le robot.
     * @return le numéro de colonne
     */
    public int getY() {
        return y;
    }
    /** Déplace le robot vers dir.
     * @param dir la direction dans laquelle se déplace le robot
     */
    public void deplace(Direction dir){
        this.x += dir.getX();
        this.y += dir.getY();
    }
    /** Retourne la dernière direction que le robot a empruntée.  
     * @return la derniere direction
    */
    public Direction getEtat() {
        return etat;
    }
    /** Modifie l'état / la derniere direction empruntée par le robot.
     * @param etat le nouvel etat
     */
    public void setEtat(Direction etat) {
        this.etat = etat;
    }

}