import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class capteur_num extends capteur{
    
    protected List<Float> values ; // Liste des valeurs relevées par le capteur

    protected final int MAX = 100;
    private float[] Seuils ; // Valeur MinGénéral - min Avertissement - maxAvertissement - maxCritique

    /**
     * Constructeur de la classe capteur numérique avec une zone géographique avec le statut set by default
     * @param z
     */
    public capteur_num (Zone_geographique z){
        super(z);
        this.values = new ArrayList<>();
        this.Seuils = new float[4];
    }

    /**
     * Constructeur de la classe capteur numérique avec une zone géographique et le statut du capteur
     * @param z
     * @param s
     */
    public capteur_num (Zone_geographique z,StatusCapteur s ){
        super(z, s);
        this.values = new ArrayList<>();
        this.Seuils = new float[4];
    }

    /**
     * Retourne la valeur prélevée avec les types de mesures pris en charge pas les différents capteurs
     * @param m
     * @return
     */
    public Object[] getVal ;

    /**
     * Enregistre les valeurs seuils du capteurs
     */
    public void setSeuils(float[] plage){
        if (plage.length > 4){
            System.out.println("Le tableau donnée dépasse la taille limite de la plage de seuils");
        }else {
            Arrays.sort(plage); // Ordonner les valeurs du tableau
            for(int j=0; j < 4; j++ ){
                this.Seuils[j]= plage[j];
            }
        }
    }

    /**
     * Retourne les plages de seuils du capteur
     * @return
     */
    public float[] getSeuils () {
        float[] values = new float[4];
        for(int j=0; j < 4; j++ ){
            values[j] =this.Seuils[j];
        }
        return values;
    }

    /**
     * Retourne une valeur du tableau de Seuils
     * @param i
     * @return
     * @throws OutsideTableException
     */
    public float getSeuilByIndex (int i) throws OutsideTableException {
        if(i< 0 || i > 3) throw new OutsideTableException();
        return Seuils[i];
    }
}