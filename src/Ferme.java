import java.util.List;
import java.util.ArrayList;
public class Ferme{

private List<Zone_geographique> liste_de_zones = new ArrayList<>();


private List<Alerte> liste_alerte = new ArrayList<>();


private List<Releve> liste_de_releve = new ArrayList<>();

public void ajouter_zone(Zone_geographique zone){
    
liste_de_zones.add(zone);

}
public void ajouter_zone(String type ,String nom){

Zone_geographique zone;
if (type.equalsIgnoreCase("culture")){
zone=new Zone_culture(nom);
liste_de_zones.add(zone);
}
if (type.equalsIgnoreCase("aquacole")){
zone=new Zone_aquacole(nom);
liste_de_zones.add(zone);
}
}
public Zone_geographique chercher_zone(String nom){
for (Zone_geographique z : liste_de_zones) {
        if (z.nom.equalsIgnoreCase(nom)) {
           
            return z; 
        }
    }
    return null;
}
public void suspension_zone(String nom){
for (Zone_geographique z : liste_de_zones) {
        if (z.nom.equalsIgnoreCase(nom)) {
            z.suspension();
            
            return; 
        }
    }
    System.out.println("Zone '" + nom + "' introuvable.");


}
public void ajouter_zone( String nom, type_elevage elevage){
Zone_geographique zone;
zone = new Zone_elevage(  nom,elevage );
liste_de_zones.add(zone);
}
public void ajouter_alerte(Alerte alerte){
liste_alerte.add(alerte);

}
public void releve(Releve releve){
liste_de_releve.add(releve);

}
public List get_liste_de_zones(){return liste_de_zones; }
}
