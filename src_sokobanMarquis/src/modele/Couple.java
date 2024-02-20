package modele;
/** Classe qui modélise un couple de coordonnées. */
public class Couple {

    private int x;
    private int y;
    /** Constructeur d'un Couple.
     * @param x la coordonnée de la ligne
     * @param y la coordonnée de la colonne
     */
    public Couple(int x, int y){
        this.x = x;
        this.y = y;
    }
    /** Retourne la coordonnée en abscisse.
     * @return la ligne
     */
    public int getX() {
        return x;
    }
    /** Retourne la coordonnée en ordonnée.
     * @return la colonne
     */
    public int getY() {
        return y;
    }
}
