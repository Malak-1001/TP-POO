import java.util.List;
import java.util.ArrayList;
public class Zone_aquacole extends Zone_geographique{
private Bassin bassin;

private List<Float> poid_de_recolte = new ArrayList<>();

private TypeMesure unite=TypeMesure.POIDS;
public void afficher_relevee(){
}
public TypeMesure get_unite(){return unite;}
public void activation(){this.active = status_zone.ACTIF;}
public void suspension(){this.active = status_zone.SUSPENDU;}
public Zone_aquacole( String nom) {
       
        this.nom = nom;
        this.active = status_zone.ACTIF;
        bassin=new Bassin();
        this.id =cpt;
        cpt++ ;
        
    }
    public Zone_aquacole( String nom,int nbanim) {
       
        this.nom = nom;
        this.id =cpt;
        cpt++ ;
        this.active = status_zone.ACTIF;
        bassin=new Bassin(nbanim);
        
    }
public void enregistrer_rendement(float val){

poid_de_recolte.add(val);


}
public void afficher_info(){
System.out.println(nom);
System.out.println(active);
System.out.println("nombre entites : ");
System.out.println(+bassin.get_nbanimeaux());
}
public void ajouter_animeaux(int v){bassin.ajouter_animeaux(v);}
}
