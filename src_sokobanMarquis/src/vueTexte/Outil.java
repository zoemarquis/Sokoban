package vueTexte;
import java.io.IOException;
/** Classe pour récupérer l'action du joueur. */
public class Outil {

    /** Lit le premier caractère de la chaine rentrée par le joueur.
     * @return le premier caractère 
    */
    public static char lireCaractere(){
        int rep= ' ';
        int buf;
        try{
            rep = System.in.read();
            buf = rep;
            while (buf != '\n')
                buf = System.in.read();
        } 
        catch (IOException e) {};
        return (char) rep;
    }

}