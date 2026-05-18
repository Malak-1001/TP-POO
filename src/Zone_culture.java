import java.util.ArrayList;
import java.util.List;
public class Zone_culture extends Zone_geographique{
private List<Culture> cultures = new ArrayList<>();//liste de culture
private record rendement_culture (String nom,float val){};//type et valeur du rendement 
private List<rendement_culture> RC = new ArrayList<>();//liste des rendement 


//constructeur seulement avc nom
public Zone_culture( String nom,Limite_geographique limites,Ferme ferme) {
        this.nom = nom;
        this.active = status_zone.ACTIF;
        id=cpt;
        this.ferme=ferme;
         this.limites = limites;
        cpt++;
        ferme.ajouter_zone(this);
        ferme.ajouter_zone_culture(this);
    }
//ajouter une culture a la zone
public void affecter_cultures(Culture culture){
   cultures.add(culture);
}
//cree et ajouter culture a la zone 
public void affecter_cultures(String date_de_plantation, String date_de_recolte ,Famille_cult type,Exigences_pedologiques EP,String nomcult){
    cultures.add(new Culture(date_de_plantation,  date_de_recolte ,type, EP,nomcult));
}
//enregistrer un rendement 
public void enregistrer_rendement_culture(String nom,float val){

RC.add(new rendement_culture( nom, val));

}
//affichage pour le test 
public void afficher_cultures(){
for ( Culture c : cultures) {
            c.afficher_info();
        }


}
//mettre a jour letat de croissance dune culture 
public void mettre_a_jour_croissance(String nomCulture, Croissance stade) {
    for (Culture c : cultures) {
        if (c.getnomcult().equalsIgnoreCase(nomCulture)) {
            c.mettre_a_jour_croissance(stade);
            return;
        }
    }
    System.out.println("Culture introuvable.");
}
// rapport afichage pour le test 
public void rapport_croissance() {
    int semis = 0, germination = 0, croissance = 0, maturite = 0, recolte = 0;

    for (Culture c : cultures) {
        Croissance stade = c.getStade();
        if (stade == null) continue;
        switch (stade) {
            case SEMI:       semis++;       break;
            case GERMINATION: germination++; break;
            case CROISSANCE:  croissance++;  break;
            case MATURITE:    maturite++;    break;
            case RECOLTE:     recolte++;     break;
        
        }
        System.out.println("nom de la culture:"+c.getnomcult());
    System.out.println("SEMI:"+semis);
    System.out.println("GERMINATION:"+germination);
    System.out.println("CROISSANCE:"+croissance);
    System.out.println("MATURITE:"+maturite);
    System.out.println("RECOLTE:"+recolte);
    }
    
    
    
    
}

}
