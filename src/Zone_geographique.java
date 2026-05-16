import java.util.ArrayList;
import java.util.List;
public abstract class Zone_geographique implements interface_activation{
protected static int cpt=1;//compteur nombre de zone
protected int id;//id de chaque zone unique 
protected String nom;//nom de la zone
protected status_zone active;//status de la zone actif ou suspendu
protected Limite_geographique limites;//limite geographique de la zone sous forme de rond
protected Ferme ferme;


private List<capteur> list_de_capteur = new ArrayList<>();//liste de capteur pour la zone


//methode daffichage uniquement pour le test
public void afficher_info(){
System.out.println(nom);
System.out.println(active);
System.out.println("id="+id);

}
//setter
public void set_nom(String nom){this.nom=nom;}
//getter
public String get_nom(){return nom;}
public int get_id(){return id;}
public status_zone get_status(){return active;}


public Limite_geographique getLimites(){
    return limites;
}
public void setLimites(Limite_geographique l){
        limites = l ;
    }
public List<capteur> getListCapteurs(){
        return list_de_capteur;
    }
public void AjouterCapteur(capteur c) {
        list_de_capteur.add(c);
    }

    public boolean equals(Zone_geographique z){
        return z.nom.equals(nom);
    }
}
