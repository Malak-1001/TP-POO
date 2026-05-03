public  class Zone_elevage extends Zone_geographique{
private Animeaux[] list_animeaux=new Animeaux[50];
private int cpt_animal=0;
private programme_alimentaire[] PA=new programme_alimentaire[10];
private float[] rendement_production=new float[50];
private int cpt_rp=0;
private TypeMesure unite;
private type_elevage type;
private type_production rendement;
public void afficher_relevee(){
}
public void activation(){this.active = status_zone.ACTIF;}
public void suspension(){this.active = status_zone.SUSPENDU;}
public Zone_elevage( String nom, type_elevage type) {
      
        this.nom = nom;
        this.active = status_zone.ACTIF;
        this.type = type;
        if(type==type_elevage.VOLAIL){
            rendement=type_production.OEUF;
         unite=TypeMesure.OEUF;
        
        }
        if(type==type_elevage.RUMINANT){
            rendement=type_production.LAITIER;
         unite=TypeMesure.LITRE;
        
        }
    }
public void affecter_animal(Animeaux animal){
if(cpt_animal<50){
list_animeaux[cpt_animal]=animal;
cpt_animal=cpt_animal+1;
}

}
public void affecter_animal(String espece,int age,int poid){
    Animeaux animal;
    animal=new Animeaux(espece, age, poid);
if(cpt_animal<50){
list_animeaux[cpt_animal]=animal;
cpt_animal=cpt_animal+1;
}

}
public void affecter_animal(String espece,int age,int poid,sante etat_de_sante){
    Animeaux animal;
    animal=new Animeaux(espece, age,poid,etat_de_sante);
if(cpt_animal<50){
list_animeaux[cpt_animal]=animal;
cpt_animal=cpt_animal+1;
}

}
public void enregistrer_rendement(float val){

    if(cpt_rp<50){
     rendement_production[cpt_rp]=val;
     cpt_rp=cpt_rp+1;
    }
}
}
