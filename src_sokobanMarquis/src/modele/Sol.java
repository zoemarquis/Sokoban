package modele;
/** Classe qui modélise une case sol. */
public class Sol extends CaseDeplacement{
    /**Constructeur d'un sol. */
    public Sol(){
        super(" ");
    }
    /** Retourne la case sous forme de chaine de caractères.
     * @return une chaine
     */
    public String toString(){
        if (robotIci()){
            return "@";
        } else if (caisseIci()){
            return "$";
        }
        return super.toString();
    }
    
}