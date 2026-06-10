import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class capteur_num extends capteur{
    
    protected List<Float> values ; // Liste des valeurs relevées par le capteur

    protected List<Plage_Seuils> Ranges; // Les plages Seuils pour chaque mesure pour chaque 

    protected List<TypeMesure> Unites;

    protected final int MAX = 100;

    /**
     * Constructeur de la classe capteur numérique avec une zone géographique avec le statut set by default
     * @param z
     */
    public capteur_num (Zone_geographique z){
        super(z);
        this.values = new ArrayList<>();
        this.Ranges = new ArrayList<>();
    }

    /**
     * Constructeur de la classe capteur numérique avec une zone géographique et le statut du capteur
     * @param z
     * @param s
     */
    public capteur_num (Zone_geographique z,StatusCapteur s ){
        super(z, s);
        this.values = new ArrayList<>();
        this.Ranges = new ArrayList<>();
    }

    /**
     * Retourne la valeur prélevée avec les types de mesures pris en charge pas les différents capteurs
     * @param m
     * @return
     */
    public Object[] getVal ;

    /**
     * Retourne les unites liées au capteur numérique
     * @param plage
     * @param m
     */
    public List<TypeMesure> getUnites(){
        return Unites;
    }

    
   /**
     * Enregistre les valeurs seuils du capteurs
     */
    public void setSeuils(List<Float> plage, TypeMesure m){
        try{
            if (plage.size() > 4){
                System.out.println("Le tableau donnée dépasse la taille limite de la plage de seuils");
            }else {
                if(! (Unites.contains(m))) throw new WrongUniteException();
                Plage_Seuils S = new Plage_Seuils(plage, m);
                Ranges.add(S);
            }
        }catch(WrongUniteException e){
            System.out.println("Le type de Mesure "+ m+ " n'est pas disponible dans ce capteur");
        }
    };

    /**
     * Ajoute une plage de Seuils à la liste des plages de seuils du capteur
     */
    public void addPlageSeuils(Plage_Seuils e){
        boolean t = false ; // helps us find les doublons
        int i = 0 ;
        if(Ranges.size() != 0){
            while(!t || i == Ranges.size()){

                Plage_Seuils s = Ranges.get(i);
                if(s.getMesure() == e.getMesure()){
                    t = true ; // We found un doublon
                    System.out.println("La plage de Seuils est déja existentes pour cette unité de Mesure. Veuillez la modifier seulement ");
                    break ;
                }
                i++;
            }
        }
        Ranges.add(e);
    };
    

    /**
     * Retourne la plage de Seuils pour le type de mesure donnée comme parametre uniquement
     * @param m
     * @return Object ayant la liste des Seuils ainsi que leur type de mesure
     */
    public Plage_Seuils getSeuils(TypeMesure m)throws WrongThreadException{
        Plage_Seuils s;
        for (Plage_Seuils plage_Seuil : Ranges) {
            TypeMesure m1 = plage_Seuil.getMesure();
            if(m1 == m){ 
                s = plage_Seuil;
                return s;
            }
        }
        System.err.println("Il n'y a pas de plage Seuils pour la mesure choisie");
        return null; // Dans le cas où y pas de Seuils pour cette mesure
    }


    /**
     * Retourne la valeur prélevée avec les types de mesures pris en charge pas le capteur environmentales
     * @param m
     * @return
     */
    public Object[] getVal(TypeMesure m) throws WrongUniteException {
        if(!(Unites.contains(m))) throw new WrongUniteException();

        float val = values.getLast();
        return new Object[]{val , m};
        
    }

     /**
     * Ajoute une valeur au tableau de valeurs relevées par le capteur
     * @param v
     */
    public void setVal(float v, TypeMesure m, Ferme f) throws OutsideTableException , WrongUniteException, CapteurNonActifException {
        if(! (Unites.contains(m))) throw new WrongUniteException();
        if(!(this.getStatut().equals(StatusCapteur.ACTIF))) throw new CapteurNonActifException();
        for (Plage_Seuils plage_Seuils : Ranges) {
            if(plage_Seuils.getMesure() == m){
                if(v < plage_Seuils.getMinVal() || v> plage_Seuils.getMaxVal())throw new OutsideTableException();
                values.add(v);
                releve_num R ;
                if(v > plage_Seuils.getminAvertissement() && v < plage_Seuils.getminCritiqueVal()){
                    R = new releve_num(v, gravite.AVERTISSEMENT, m);
                    LocalDate d = R.getDate();
                    Alerte A = new Alerte(zone, R, gravite.AVERTISSEMENT, type, d );
                    f.ajouter_alerte(A);
                }else if(v > plage_Seuils.getminCritiqueVal() &&  v < plage_Seuils.getMaxVal()){
                    R = new releve_num(v, gravite.CRITIQUE, m);
                    LocalDate d = R.getDate();
                    Alerte A = new Alerte(zone, R, gravite.CRITIQUE, type, d);
                    f.ajouter_alerte(A);
                }else{
                    R = new releve_num(v, m);
                }
                super.addReleve(R);
            }
        } 
    }

}