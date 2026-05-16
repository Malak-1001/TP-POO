import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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

    public List<Zone_geographique> get_liste_de_zones(){return liste_de_zones; }

    /**
     * Retourne la liste des alertes actives
     * @return
     */
    public List<Alerte> getListAlertesActives(){
        List<Alerte> AlertesActives = new ArrayList<>();
        for (Alerte alerte : liste_alerte) {
            if(alerte.getStatut().equals(statue_alerte.ACTIF)){
                AlertesActives.add(alerte);
            }
        }
        return AlertesActives;
    }

    /**
     * Affiche la liste des alertes
     */
    public String DisplayAlertes(){
        StringBuilder S = new StringBuilder();

        System.out.println("----------------------------------------------------");
        for (Alerte alerte : liste_alerte) {
            S.append(alerte.Display());
            S.append("    ******  ******  ******  ******    ******");
        }

        return S.toString();
    }

    /**
     * Affiche l'alerte selon la zone donnée
     * @param z
     * @return
     */
    public String DisplayAlertesZone(Zone_geographique z){
        StringBuilder S = new StringBuilder();
        S.append("La liste des alertes filtrés par Zone - " + z.get_nom()+" - : \n");
        for (Alerte alerte : liste_alerte) {
            if (alerte.getZone().get_nom().equals(z.get_nom())){
                S.append(alerte.Display());
            }
        }
        return S.toString();
    }

    /**
     * Affiche l'alerte selon le type de Capteur donnée
     * @param z
     * @return
     */
    public String DisplayAlertesCapteur(Type_Capteurs t){
        StringBuilder S = new StringBuilder();
        S.append("La liste des alertes filtrés par Type de Capteur - " + t.name()+" - : \n");
        for (Alerte alerte : liste_alerte) {
            if (alerte.getTypeCapteurs().equals(t)){
                S.append(alerte.Display());
            }
        }
        return S.toString();
    }

    /**
     * Affiche l'alerte selon le niveau de gravité
     * @param z
     * @return
     */
    public String DisplayAlertesNiveau (gravite g){
        StringBuilder S = new StringBuilder();
        S.append("La liste des alertes filtrés par Niveau de Gravité - " + g.name()+" - : \n");
        for (Alerte alerte : liste_alerte) {
            if (alerte.getNiveau().equals(g)){
                S.append(alerte.Display());
            }
        }
        return S.toString();
    }

    /**
     * Affiche l'alerte par période
     * @param z
     * @return
     */
    public String DisplayAlertesPeriode (LocalDate d1 , LocalDate d2)throws BeforeDateException{
        StringBuilder S = new StringBuilder();

        if(d2.isBefore(d1)) throw new BeforeDateException();

        S.append("La liste des alertes filtrés par période: \n");
        S.append("  La période : entre "+d1+" et " + d2);
        for (Alerte alerte : liste_alerte) {
            if (alerte.getDate().isAfter(d1) && alerte.getDate().isBefore(d2)){
                S.append(alerte.Display());
            }
        }
        return S.toString();
    }

    /**
     * Retourne la liste des alertes actives
     * @return
     */
    public String DisplayAlertesActive(){
        List<Alerte> alertes = this.getListAlertesActives();
        Collections.sort(alertes);

        StringBuilder S = new StringBuilder();
        S.append("Panneau des alertes actives triées \n");

        for (Alerte alerte : alertes) {
            S.append(alerte.Display());
        }
        return S.toString();
    }
    
    /**
     * Retourne une liste d'alertes selon la date
     * @param d1
     * @return
     */
    public List<Alerte> getAlerte(LocalDate d1){
        List<Alerte> Alertes = new ArrayList<>();

        for (Alerte alerte : liste_alerte) {
            if(alerte.getDate().isEqual(d1)){
                Alertes.add(alerte);
            }
        }
        return Alertes;
    }

    /**
     * Retourne une liste d'alertes selon la date et le type de capteurs
     * @param d1
     * @param t
     * @return
     */
    public List<Alerte> getAlerte(LocalDate d1, Type_Capteurs t){
        List<Alerte> Alertes = new ArrayList<>();
        for (Alerte alerte : liste_alerte) {
            if(alerte.getDate().isEqual(d1) && alerte.getTypeCapteurs().equals(t)){
                Alertes.add(alerte);
            }
        }
        return Alertes;
    }

    /**
     * Change le statut de l'alerte ( Actif - Acquitté - Supprimé )
     * @param s
     */
    public void ChangerStatutAlerte (statue_alerte s){
        System.out.println(DisplayAlertes());
        
    }
}
