public class Zone_aquacole extends Zone_geographique{
private Bassin bassin;
private float[] poid_de_recolte=new float[50];
private int cpt_pdr=0;
private TypeMesure unite=TypeMesure.POID;
public void afficher_relevee(){
}
public void activation(){this.active = status_zone.ACTIF;}
public void suspension(){this.active = status_zone.SUSPENDU;}
public Zone_aquacole( String nom) {
       
        this.nom = nom;
        this.active = status_zone.ACTIF;
        bassin=new Bassin();
        
    }
public void enregistrer_rendement(float val){
if(cpt_pdr<50){
poid_de_recolte[cpt_pdr]=val;
cpt_pdr=cpt_pdr+1;
}

}
}
