public class Zone_culture extends Zone_geographique{
private Culture[] cultures=new Culture[50];
private int cpt_culture=0;
private record rendement_culture (String nom,float val){};
private rendement_culture[] RC=new rendement_culture [50];
private int cpt_rc=0;
public void afficher_relevee(){
}
public void activation(){this.active = status_zone.ACTIF;}
public void suspension(){this.active = status_zone.SUSPENDU;}
public Zone_culture( String nom) {
        this.nom = nom;
        this.active = status_zone.ACTIF;
    }
public void affecter_cultures(Culture culture){
    if(cpt_culture<50){
cultures[cpt_culture]=culture;
cpt_culture=cpt_culture+1;
}
else{return;}
}
public void affecter_cultures(String date_de_plantation, String date_de_recolte ,Famille_cult type,Exigences_pedologiques EP){
    if(cpt_culture<50){
        
cultures[cpt_culture]=new Culture(date_de_plantation,  date_de_recolte ,type, EP);
cpt_culture=cpt_culture+1;
}
else{return;}
}
public void enregistrer_rendement_culture(String nom,float val){
if(cpt_rc<50){
RC[cpt_rc]=new rendement_culture( nom, val);
}
}
}
