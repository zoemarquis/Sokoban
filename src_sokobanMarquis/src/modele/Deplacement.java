package modele;
/** Classe qui modélise les informations à enregistrer lors d'un déplacement du robot. */
public class Deplacement {
    private boolean caisseDeplacee;
    private Direction directionAvant;
    /** Constructeur d'un Déplacement.
     * @param caisseDeplacee booléen à vrai si une caisse a été déplacée
     * @param derniereDirection la direction dans laquelle a eu lieu le déplacement
     */
    public Deplacement(boolean caisseDeplacee, Direction derniereDirection){
        this.caisseDeplacee = caisseDeplacee;
        this.directionAvant = derniereDirection;
    }
    /** Retourne la direction dans laquelle s'est déplacée le robot.
     * @return la direction courante
     */
    public Direction getDirectionAvant() {
        return directionAvant;
    }
    /** Vérifie si une caisse a été déplacée.
     * @return vrai si une caisse a été poussée
     */
    public boolean getCaisseDeplacee(){
        return caisseDeplacee;
    }

}