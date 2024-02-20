package modele;
/** Classe qui modélise une case qui peut accueillir un robot ou une caisse. */
public abstract class CaseDeplacement extends Case{   
    
    protected boolean robot;
    protected boolean caisse;
    /** Constructeur d'une CaseDeplacement. */
    public CaseDeplacement(String car){
        super(car);
    }

    @Override
    public boolean peutAccueillirJoueurOuCaisse() {
        return true;
    }

    @Override
    public boolean robotIci() {
        return robot;
    }

    @Override
    public boolean caisseIci() {
        return caisse;
    }

    @Override
    public void setRobotIci(){
        robot = true;
    }
    @Override
    public void setRobotParti(){
        robot = false;
    }

    @Override
    public void setCaisseIci(){
        caisse = true;
    }
    @Override
    public void setCaisseParti(){
        caisse = false;
    }
    
}