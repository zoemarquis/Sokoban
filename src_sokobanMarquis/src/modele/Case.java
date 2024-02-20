package modele;
/**Classe abstraite pour modéliser les cases. */
public abstract class Case {
    private String car;
    /** Constructeur d'une case.
     * @param car le caractere représentant le type de case.
     */
    public Case(String car){
        this.car = car;
    }

    /**Pour savoir si le robot peut visiter cette case.
     * @return vrai si c'est un sol ou une destination, faux sinon
     */
    public abstract boolean peutAccueillirJoueurOuCaisse();
    /** Pour savoir si le robot est sur la case.
     * @return vrai si le robot est sur cette case
     */
    public abstract boolean robotIci();
    /** Pour savoir si une caisse est sur la case.
     * @return vrai si une caisse est sur cette case
     */
    public abstract boolean caisseIci();
    /** Mettre le robot sur la case. */
    public abstract void setRobotIci();
    /** Mettre une caisse sur la case. */
    public abstract void setCaisseIci();
    /** Enlever le robot de la case. */
    public abstract void setRobotParti();
    /** Enlever la caisse de la case. */
    public abstract void setCaisseParti();

    /** Retourne ma case sous forme de chaine de caractères.
     * @return une chaine de caractères
     */
    public String toString(){
        return car;
    }

}