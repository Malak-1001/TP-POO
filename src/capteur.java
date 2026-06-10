import java.time.LocalDate;
import java.util.*;

public abstract class capteur implements interface_activation {
    private static int i  ; // Nombre  de capteurs
    //private Releve[] list_releve = new Releve[50];
    private List<Releve> Liste ;
    private int id;
    protected Zone_geographique zone;
    private StatusCapteur statue;
    protected Type_Capteurs type;
    
    /**
     * Retourne le type du capteur
     * @return
     */
    public Type_Capteurs getType(){
        return type;
    }

    /**
     * Retourne l id du capteur 
     * @return
     */
    public int getId(){
        return id;
    }

    /**
     * Retourne le statue du capteur
     * @return
     */
    public StatusCapteur getStatut(){
        return statue;
    }

    /**
     * Initialisation du capteur avec une zone géographique, statut
     * @param z
     * @param statut
     */
    public capteur(Zone_geographique z, StatusCapteur statut){
        id = i;
        i++;
        zone = z ;
        statue = statut; 
        this.Liste = new ArrayList<>();
    }

    /**
     * Initalisation du capteur avec le statut actif par défault
     * @param z
     */
    public capteur (Zone_geographique z) {
        id = i ;
        i++;
        this.zone = z ;
        this.statue = StatusCapteur.ACTIF ;
        this.Liste = new ArrayList<>();
    }

    
    /**
     * S'occupe de l'activation du capteur
     */
    public void activation(){
        this.statue = StatusCapteur.ACTIF;
    }

    /**
     * S'occupe de la suspension du capteur
     */
    public void suspension(){
        this.statue = StatusCapteur.SUSPENDU;
    }

    /**
     * Change le statut du capteur à défaillant
     */
    public void Defaillant() {
        this.statue = StatusCapteur.DEFAILLANT;
    }

    /**
     * Retourne une string qui affiche les informations basics du capteur
     */
    public String Display(){
        StringBuilder S = new StringBuilder();
        S.append("\n Capteur ID " + id +"\n" );
        S.append( " - Type: "+ type + "\t  - Statut: " + statue +"\n");
        return S.toString();
    }

    /**
     * Ajouter un releve à la liste de Releve du capteur
     * @param r
     */
    public void addReleve (Releve r){
        Liste.add(r);
    }

    
    /**
     * Retourne le releve ayant le meme indice que celui donné
     * @param i
     * @return
     */
    public Releve getReleveByIndex(int i) {
        return Liste.get(i);
    }

    /**
     * Retourne la liste compléte des Relevés du capteur
     * @return
     */
    public List<Releve> getListReleve(){
        return Liste ;
    }

    /**
     * Retourne le dernier releve de la liste de relevé du capteur en question 
     * @return
     */
    public Releve getReleve (){
        return Liste.getLast();
    }

    /**
     * Retourne une string ayant toute la liste des relevés displayed
     * @return
     */
    public String DisplayListReleve() {
        StringBuilder S = new StringBuilder();
        if(Liste.size() == 0){
            S.append("Aucun releve n'est existent pour ce capteur");
        } else {
            
        for (Releve releve : Liste) {
            S.append(releve.Display());
        }
        }
        return S.toString();
    }

    /**
     * Retourne une string de la liste de relevés filtrés par plage de dates
     * @param d1
     * @param d2
     * @return
     */
    public String DisplayReleveFiltre (LocalDate d1, LocalDate d2) throws BeforeDateException{
        if(d2.isBefore(d1)) throw new BeforeDateException();

        StringBuilder S = new StringBuilder();

        for (Releve releve : Liste) {
            if(releve.date.isAfter(d1) && releve.date.isBefore(d2)){
                S.append(releve.Display());
            }
        }
        return S.toString();
    }

    /**
     * Retourne la zone du capteur
     */
    public Zone_geographique getZone() {
        return zone;
    }


}