import java.util.List;
import java.util.ArrayList;
public abstract class Zone_geographique implements interface_activation{
    protected int id;
    protected static int cpt = 1;
    protected String nom;
    protected status_zone active;
    protected Limite_geographique limites;

    private List<Releve> list_de_releve = new ArrayList<>();
    private List<capteur> list_de_capteur = new ArrayList<>();


    public void afficher_info(){
    System.out.println(nom);
    System.out.println(active);

    }
    public void set_nom(String nom){this.nom=nom;}
    public String get_nom(){return nom;}
    public void AjouterCapteur(capteur c) {
        list_de_capteur.add(c);
    }

    public List<capteur> getListCapteurs(){
        return list_de_capteur;
    }

    public void setLimites(Limite_geographique l){
        limites = l ;
    }

    public boolean equals(Zone_geographique z){
        return z.nom.equals(nom);
    }
}
