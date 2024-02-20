package modele;
/** Enumeration qui représente chaque direction possible du robot. */
public enum Direction {
    HAUT(-1,0),
    BAS(1,0),
    GAUCHE(0,-1),
    DROITE(0,1);

    private int x;
    private int y;

    /** Constructeur d'une Direction.
     * @param x la ligne
     * @param y la colonne
     */
    private Direction(int x, int y){
        this.x = x;
        this.y = y;
    }
    /** Retourne le déplacement à effetuer sur la ligne
     * @return un nombre représentant le déplacement sur la ligne
     */
    public int getX() {
        return x;
    }
    /** Retourne le déplacement à effetuer sur la colonne
     * @return un nombre représentant le déplacement sur la colonne
     */
    public int getY() {
        return y;
    }
    /** Retourne la direction sous forme de chaine de caractères.
     * @return une chaine de caractères.
     */
    public String toString(){
        return super.toString();
    }
    /** Retourne la direction opposée à la direction courante.
     * @return la direction opposée
     */
    public Direction directionInverse(){
        switch(this){
            case GAUCHE: 
            return DROITE;
            case DROITE: 
            return GAUCHE;
            case HAUT: 
            return BAS;
            default:
            return HAUT;
        }
    }
}