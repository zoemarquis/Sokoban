package modele;
/** Classe qui modélise une case destination. */
public class Dest extends CaseDeplacement{
    /**Constructeur d'une destination. */
    public Dest(){
        super(".");
    }
    /** Retourne la case sous forme de chaine de caractères.
     * @return une chaine
     */
    public String toString(){
        if (robotIci()){
            return "+";
        } else if (caisseIci()){
            return "*";
        }
        return super.toString();
    }
    
}