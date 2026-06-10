import java.util.ArrayList;
import java.util.List;
public class Zone_aquacole extends Zone_geographique{
private Bassin bassin;//bassin de la zone

private List<Float> poid_de_recolte = new ArrayList<>();//poid de la recolte 

private TypeMesure unite=TypeMesure.POIDS;//unite de mesure du poid 
//getter
public TypeMesure get_unite(){return unite;}

//constructeur avec le nom seulement 
public Zone_aquacole( String nom,Limite_geographique limites,Ferme ferme) {
       
        this.nom = nom;
        this.active = status_zone.ACTIF;
        bassin=new Bassin();
         id=cpt;
         this.ferme=ferme;
          this.limites = limites;
        cpt++;
        ferme.ajouter_zone(this);
        ferme.ajouter_zone_aquacole(this);
        super.SpecifiqueCapteurs = List.of(
            Type_Capteurs.EAU);
        
    }
    // constructeur avec nom de la zone et nombre danimeaux 
    public Zone_aquacole( String nom,int nbanim,Limite_geographique limites,Ferme ferme) {
       
        this.nom = nom;
        this.id =cpt;
        cpt++ ;
        this.active = status_zone.ACTIF;
        bassin=new Bassin(nbanim);
         
         this.ferme=ferme;
          this.limites = limites;
        
        ferme.ajouter_zone(this);
        ferme.ajouter_zone_aquacole(this);
        //---- Malak
        super.SpecifiqueCapteurs = List.of(
            Type_Capteurs.EAU);
        
    }
    //enregistrer le rendement 
public void enregistrer_rendement(float val){

poid_de_recolte.add(val);


}
// methode pour test over
public void afficher_info(){
System.out.println("nom:"+nom);
System.out.println("id="+id);
System.out.println("etat:"+active);
System.out.println("nombre entites : "+bassin.get_nbanimeaux());

}
//ajouter des animeaux au bassin
public void ajouter_animeaux(int v){bassin.ajouter_animeaux(v);}
}
