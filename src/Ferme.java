import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


public class Ferme {

    private List<Zone_geographique> liste_de_zones = new ArrayList<>();
    private List<Zone_culture> liste_de_zones_culture = new ArrayList<>();
    private List<Zone_elevage> liste_de_zones_elevage = new ArrayList<>();
    private List<Zone_aquacole> liste_de_zones_aquacole = new ArrayList<>();
    private List<Alerte> liste_alerte = new ArrayList<>();
    

    // appelees par les constructeurs des zones
    public void ajouter_zone(Zone_geographique zone) {
        liste_de_zones.add(zone);
    }
    public void ajouter_zone_culture(Zone_culture zone) {
        liste_de_zones_culture.add(zone);
    }
    public void ajouter_zone_elevage(Zone_elevage zone) {
        liste_de_zones_elevage.add(zone);
    }
    public void ajouter_zone_aquacole(Zone_aquacole zone) {
        liste_de_zones_aquacole.add(zone);
    }

    // chercher une zone par son nom
    public Zone_geographique chercher_zone(String nom) {
        for (Zone_geographique z : liste_de_zones) {
            if (z.get_nom().equalsIgnoreCase(nom)) return z;
        }
        return null;
    }

    /**
     * Ajoute un capteur à une zone donnée
     * @param z
     * @param c
     */
    public void AjouterCapteurZone (Zone_geographique z, capteur c){
        z.AjouterCapteur(c);
    }

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
    public void ChangerStatutAlerte (statue_alerte s, int i)throws NonExistentAlerteException{
        Alerte alerte = liste_alerte.get(i);
        if(alerte == null) throw new NonExistentAlerteException () ;
        System.out.println(alerte.Display());
        if(s.equals(statue_alerte.AQUITTE) && !(alerte.getStatut().equals(statue_alerte.AQUITTE))){
            alerte.Acquitter();
        }else if(s.equals(statue_alerte.SUPPRIME) && !(alerte.getStatut().equals(statue_alerte.SUPPRIME))){
            alerte.Supprimer();
        }
        System.out.println("-------------------------");
        System.out.println(alerte.Display());
        
    }
    // suspendre une zone par son nom
    public void suspension_zone(String nom) {
        for (Zone_geographique z : liste_de_zones) {
            if (z.get_nom().equalsIgnoreCase(nom)) {
                z.suspension();
                return;
            }
        }
        System.out.println("Zone '" + nom + "' introuvable.");
    }
    // activer une zone par son nom
    public void activer_zone(String nom) {
        for (Zone_geographique z : liste_de_zones) {
            if (z.get_nom().equalsIgnoreCase(nom)) {
                z.activation();
                return;
            }
        }
        System.out.println("Zone '" + nom + "' introuvable.");
    }
    public Zone_geographique getZone(String nomzone) {
    for (Zone_geographique z : liste_de_zones) {
        if (z.get_nom().equalsIgnoreCase(nomzone)) return z;
    }
    return null;
}
    //ajouter  alertes 
    public void ajouter_alerte(Alerte alerte) { liste_alerte.add(alerte); }
    
    // getters
    public List<Zone_geographique> get_liste_de_zones() { return liste_de_zones; }
    public List<Zone_culture> get_liste_de_zones_culture() { return liste_de_zones_culture; }
    public List<Zone_elevage> get_liste_de_zones_elevage() { return liste_de_zones_elevage; }
    public List<Zone_aquacole> get_liste_de_zones_aquacole() { return liste_de_zones_aquacole; }
    public List<Alerte> get_liste_alerte() { return liste_alerte; }
    
}
