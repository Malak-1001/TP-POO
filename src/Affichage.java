import java.util.List;
public class Affichage{
    
  public void afficher_zones(Ferme ferme){
 List<Zone_geographique> zones = ferme.get_liste_de_zones();

 for (Zone_geographique z : zones) {
            z.afficher_info();
        }


}

public void lister_zones(Ferme ferme){
 List<Zone_geographique> zones = ferme.get_liste_de_zones();

 for (Zone_geographique z : zones) {
           System.out.println( z.get_nom());
        }


}






}
