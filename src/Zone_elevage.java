import java.util.List;
import java.util.ArrayList;
public  class Zone_elevage extends Zone_geographique{
private List<Animeaux> list_animeaux = new ArrayList<>();
private List<programme_alimentaire> PA = new ArrayList<>();

private List<Float> rendement_production = new ArrayList<>();

private TypeMesure unite;
private type_elevage type;
private type_production rendement;
public void afficher_relevee(){
}
public TypeMesure get_unit(){return unite;}
public void activation(){this.active = status_zone.ACTIF;}
public void suspension(){this.active = status_zone.SUSPENDU;}
public Zone_elevage( String nom, type_elevage type) {
      
        this.nom = nom;
        this.active = status_zone.ACTIF;
        this.type = type;
        this.id =cpt;
        cpt++ ;
        if(type==type_elevage.VOLAIL){
            rendement=type_production.OEUF;
         unite=TypeMesure.OEUFS;
        
        }
        if(type==type_elevage.RUMINANT){
            rendement=type_production.LAITIER;
         unite=TypeMesure.LITRE;
        
        }
    }
public void affecter_animal(Animeaux animal){

list_animeaux.add(animal);

}
public void affecter_animal(String espece,int age,int poid){
Animeaux animal;
animal=new Animeaux(espece, age, poid);
list_animeaux.add(animal);

}
public void affecter_animal(String espece,int age,int poid,sante etat_de_sante){
Animeaux animal;
animal=new Animeaux(espece, age,poid,etat_de_sante);
list_animeaux.add(animal);

}
public void enregistrer_rendement(float val){

    
     rendement_production.add(val);
     
}
public void afficher_info(){
System.out.println(nom);
System.out.println(active);
System.out.println("nombre entites : ");
System.out.println(+list_animeaux.size());
}
}
