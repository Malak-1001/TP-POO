import java.util.List;
import java.util.ArrayList;
public class Zone_culture extends Zone_geographique{
private List<Culture> cultures = new ArrayList<>();
private record rendement_culture (String nom,float val){};
private List<rendement_culture> RC = new ArrayList<>();

public void afficher_relevee(){
}
public void activation(){this.active = status_zone.ACTIF;}
public void suspension(){this.active = status_zone.SUSPENDU;}
public Zone_culture( String nom) {
        this.nom = nom;
        this.active = status_zone.ACTIF;
        this.id =cpt;
        cpt++ ;
    }
public void affecter_cultures(Culture culture){
   cultures.add(culture);
}
public void affecter_cultures(String date_de_plantation, String date_de_recolte ,Famille_cult type,Exigences_pedologiques EP,String nomcult){
    cultures.add(new Culture(date_de_plantation,  date_de_recolte ,type, EP,nomcult));
}
public void enregistrer_rendement_culture(String nom,float val){

RC.add(new rendement_culture( nom, val));

}
public void afficher_cultures(){
for ( Culture c : cultures) {
            c.afficher_info();
        }


}
public void mettre_a_jour_croissance(String nomCulture, Croissance stade) {
    for (Culture c : cultures) {
        if (c.getnomcult().equalsIgnoreCase(nomCulture)) {
            c.mettre_a_jour_croissance(stade);
            return;
        }
    }
    System.out.println("Culture introuvable.");
}
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
    }

    System.out.println("\n===== RAPPORT ÉTAT DES CULTURES =====");
    System.out.println("SEMIS       : " + semis       + " culture(s)");
    System.out.println("GERMINATION : " + germination + " culture(s)");
    System.out.println("CROISSANCE  : " + croissance  + " culture(s)");
    System.out.println("MATURITE    : " + maturite    + " culture(s)");
    System.out.println("RECOLTE     : " + recolte     + " culture(s)");
    System.out.println("TOTAL       : " + cultures.size() + " culture(s)");
}

}
