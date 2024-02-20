package modele;
import java.io.*;
import java.util.*;
/** Classe qui permet de transformer le fichier map.txt en liste de chaines de caractères. */
public class Lecture {

    private int nbLigne;
    private int tailleUneLigne;
    private List<String> mapCaractere = new ArrayList<String>();

    /**Constructeur de Lecture.
     * @param fichier le nom du fichier à recopier
     * @throws IOException lance une exception si la map est introuvable
     */
    public Lecture(String fichier) throws IOException {
        BufferedReader scan = null;
        try {
            scan = new BufferedReader(new FileReader(fichier));

            String l;
            while ((l = scan.readLine()) != null){
                mapCaractere.add(l);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scan!=null){
                scan.close();
                tailleUneLigne = mapCaractere.get(0).length();
                nbLigne = mapCaractere.size();
            }
            
        }
    }
    /** retourne la liste de chaine de caractères représentant le plateau copiée du fichier
     * @return la liste de chaine de carcatères
     */
    public List<String> getMapCaractere() {
        return mapCaractere;
    }
    /** retourne le nombre de ligne dans le fichier
     * @return le nombre de ligne du plateau
     */
    public int getNbLigne() {
        return nbLigne;
    }
    /** retourne le nombre de colonne dans le fichier
     * @return le nombre de colonne du plateau
     */
    public int getTailleUneLigne() {
        return tailleUneLigne;
    }

}
