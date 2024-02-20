package modele;
/** Classe qui modélise une case qui ne peut pas accueillir un robot ou une caisse. */
public class CaseNonDeplacement extends Case{
    /** Constructeur d'une CaseNonDeplacement. */
    public CaseNonDeplacement(String car){
        super(car);
    }

    @Override
    public boolean peutAccueillirJoueurOuCaisse() {
        return false;
    }
    @Override
    public boolean robotIci() {
        return false;
    }
    @Override
    public boolean caisseIci() {
        return false;
    }

    @Override
    public void setRobotIci() {
    }

    @Override
    public void setCaisseIci() {
    }

    @Override
    public void setRobotParti() {
    }

    @Override
    public void setCaisseParti() {
    }
    
}