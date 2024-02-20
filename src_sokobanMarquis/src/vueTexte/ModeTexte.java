package vueTexte;
import java.io.IOException;
import java.util.HashMap;
import modele.Carte;
import modele.Direction;

/** Classe qui modélise un niveau du jeu Sokoban en mode textuel. */
public class ModeTexte {
    private Carte map;
    private HashMap<Character, Direction> hm = new HashMap<Character, Direction>();

    /** Constructeur de ModeTexte. */
    public ModeTexte(String fichier) throws IOException{
        this.map = new Carte(fichier);
        hm.put('s',Direction.BAS);
        hm.put('l',Direction.BAS);
        hm.put('z',Direction.HAUT);
        hm.put('o',Direction.HAUT);
        hm.put('q',Direction.GAUCHE);
        hm.put('k',Direction.GAUCHE);
        hm.put('d',Direction.DROITE);
        hm.put('m',Direction.DROITE);
        lancePartie();
    }

    /** Dialogue entre le joueur et le jeu.
     * Tant que le caractère n'est pas valide, le joueur retente.
     * @return le caracrtère pour essayer de bouger le robot
     */
    public char dialogue(){
        System.out.println("Prochaine destination :");
        char c = Outil.lireCaractere();
        while (c!='.' && c!='?' && c!='!' && !hm.containsKey(c)){
            System.out.println("Caractère erroné !");
            c = Outil.lireCaractere();
        }
        return c;
    }

    /** Recommencer le niveau. */
    public void recommencer(){
        System.out.println("\n-----------------------\n");
        System.out.println("RESET");
        map.recommencer();
    }

    /** Annuler le dernier déplacement. */
    public void annuler(){
        System.out.println("\n-----------------------\n");
        System.out.println("UNDO");
        map.annulerUnMouvement();
    }

    /** Gère la partie.
     * Lance les dialogues, met à jour la Carte et affiche les informations en conséquences.
     */
    public void lancePartie(){
        char c;
        do{
            System.out.println("\n-----------------------\n");
            System.out.println(map);
            c = dialogue();
            if (c=='?'){
                recommencer();
            } else if (c=='!'){
                annuler();
            } else if (c!='.'){
                map.deplaceRobot(hm.get(c));
            }
        }while (c!='.' && !map.finDePartie());
        if (c!='.'){
            System.out.println(map);
            System.out.println("GAGNÉ !");
        }else {
            System.out.println("STOP");
        }
    }
}
