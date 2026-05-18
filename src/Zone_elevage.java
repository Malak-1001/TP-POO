import java.util.ArrayList;
import java.util.List;
public  class Zone_elevage extends Zone_geographique{
private List<Animeaux> list_animeaux = new ArrayList<>();//liste danimeaux
private List<programme_alimentaire> PA = new ArrayList<>();//programme alimentaire 

private List<Float> rendement_production = new ArrayList<>();//production de la zone 

private TypeMesure unite;//unite de mesure de la production sois litre ou oeuf
private type_elevage type;//volail ou ruminant 
private type_production rendement;//lait ou oeuf
//getter de luniter de mesure de la production 
public TypeMesure get_unit(){return unite;}
<<<<<<< Updated upstream


=======
>>>>>>> Stashed changes
//constructeur avec nom et type delevage
public Zone_elevage( String nom, type_elevage type,Limite_geographique limites,Ferme ferme) {
      
        this.nom = nom;
        this.active = status_zone.ACTIF;
        this.type = type;
        this.ferme=ferme;
        this.limites = limites;
         id=cpt;
        cpt++;
        if(type==type_elevage.VOLAIL){
            rendement=type_production.OEUF;
         unite=TypeMesure.OEUFS;
        
        }
        if(type==type_elevage.RUMINANT){
            rendement=type_production.LAITIER;
         unite=TypeMesure.LITRE;
        
        }
        ferme.ajouter_zone(this);
        ferme.ajouter_zone_elevage(this);
    }
  //ajouter un animal a la zone   
public void affecter_animal(Animeaux animal){

list_animeaux.add(animal);

}
//cree et ajouter un animal 
public void affecter_animal(String espece,int age,int poid){
Animeaux animal;
animal=new Animeaux(espece, age, poid);
list_animeaux.add(animal);

}
// cree er ajouter un animal et mettre a jour son etat de sante
public void affecter_animal(String espece,int age,int poid,sante etat_de_sante){
Animeaux animal;
animal=new Animeaux(espece, age,poid,etat_de_sante);
list_animeaux.add(animal);

}
//sauvegarder le rendement de la zone 
public void enregistrer_rendement(float val){

    
     rendement_production.add(val);
     
}
// methode pour le test
public void afficher_info(){
System.out.println("nom:"+nom);
System.out.println("id="+id);
System.out.println("etat:"+active);
System.out.println("nombre entites : "+list_animeaux.size());

}
public void ajouter_programme_alimentaire(programme_alimentaire p){
PA.add(p);

}
public void ajouter_programme_alimentaire(double quantite,String type_aliment){
programme_alimentaire p = new programme_alimentaire( quantite,type_aliment);
PA.add(p);

}
public void afficher_programme_alimentaire(){
for ( programme_alimentaire p : PA) {
     int i=1;
     System.out.println("programme"+i+":");
     p.info_program();   
     i++;
    }

}
}
