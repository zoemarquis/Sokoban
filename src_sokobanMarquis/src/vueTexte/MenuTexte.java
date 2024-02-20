package vueTexte;
import java.io.IOException;
import java.util.HashMap;

/**Classe qui modélise le menu en mode textuel. */
public class MenuTexte {
    
    /** Constructeur de MenuTexte.
     * Affiche les commandes et permet la sélection d'un niveau.
     * @throws IOException lance une exception si la carte est introuvable
     */
    public MenuTexte() throws IOException{
        HashMap<Character, String> hm = new HashMap<Character, String>();
        hm.put('1', "map/map1.txt");
        hm.put('2', "map/map2.txt");
        hm.put('3', "map/map3.txt");
        System.out.println("Bienvenue dans le jeu du Sokoban !\n");
        System.out.println("Liste des correspondances :");
        System.out.println("# : mur");
        System.out.println("/ : vide");
        System.out.println("  : sol");
        System.out.println(". : destination");
        System.out.println("@ : robot sur sol");
        System.out.println("$ : caisse sur sol");
        System.out.println("+ : robot sur destination");
        System.out.println("* : caisse sur destination\n");
        System.out.println("Vous devez aider le robot à ranger les caisses sur les destinations.");
        System.out.println("Pour cela, déplacez-vous à l'aide des touches 'qzsd' et/ou 'kolm'.");
        System.out.println("Pour annuler un déplacement, tapez '!'.");
        System.out.println("Pour recommencer, tapez '?'.");
        System.out.println("Pour quitter, tapez '.'.\n");
        System.out.println("Sélectionnez un niveau (1 à 3):");
        char c = Outil.lireCaractere();
        while (c!='.' && !hm.containsKey(c)){
            System.out.println("Sélectionnez un niveau (1 à 3):");
            c = Outil.lireCaractere();
        }
        if (c!='.'){
            new ModeTexte(hm.get(c));
        }
    }

}
