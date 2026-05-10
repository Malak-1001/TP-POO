import java.util.*;

public abstract class capteur implements interface_relevee_affiche,interface_activation {
    private static int i ; // Nombre  de capteurs
    //private Releve[] list_releve = new Releve[50];
    private List<Releve> Liste ;
    private int id;
    private Zone_geographique zone;
    private StatusCapteur statue;
    

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

    

    // Une méthode qui s'occupe de l'affichage des relevés
    public void afficher_relevee(){
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
     * Ajouter un releve à la liste de Releve du capteur
     * @param r
     */
    public void addReleve (Releve r){
        Liste.add(r);
    }

    /**
     * Retourne le dernier releve de la liste de relevé du capteur en question 
     * @return
     */
    public Releve getReleve (){
        return Liste.getLast();
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
     * Retourne une string ayant toute la liste des relevés displayed
     * @return
     */
    public String DisplayListReleve() {
        StringBuilder S = new StringBuilder();
        for (Releve releve : Liste) {
            S.append(releve.Display());
        }

        return S.toString();
    }

}