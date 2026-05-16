import java.util.ArrayList;
import java.util.List;
public class Plage_Seuils {
    
    private List<Float> Valeurs; // Valeur MinGénéral - min Avertissement - maxAvertissement - maxCritique
    private TypeMesure Mesure;

    /**
     * Constructeur de la classe de plage de Seuils
     * @param values
     * @param m
     */
    public Plage_Seuils(List<Float> values, TypeMesure m){
        Valeurs = new ArrayList<>();
        for (Float value : values) {
            Valeurs.add(value);
        }
        Mesure = m ;
    }

    /**
     * Constructeur de la classe de plage de Seuils
     * @param values
     * @param m
     */
    public Plage_Seuils(float v1, float v2, float v3, float v4, TypeMesure m){
        Valeurs = new ArrayList<>();
        Valeurs.add(v1);
        Valeurs.add(v2);
        Valeurs.add(v3);
        Valeurs.add(v4);
        Mesure = m ;
    }

    /**
     * Retourne l'unité de la plage de Seuils 
     * @return
     */
    public TypeMesure getMesure(){
        return Mesure;
    }

    /**
     * Retourne la plage de Seuils
     * @return
     */
    public List<Float> getValeursSeuils(){
        return Valeurs ;
    }

    /**
     * Change le seuil minimal
     * @param v
     */
    public void setMinVal(float v){
        Valeurs.set(0, v);
    }

    /**
     * Retourne le seuil minimal
     * @param v
     */
    public float getMinVal(){
        return Valeurs.get(0);
    }

    /**
     * Change le 2eme min
     * @param v
     */
    public void setminAvertissementVal (float v){
        Valeurs.set(1, v);
    }

    /**
     * Retourne le 2eme seuil minimal
     * @param v
     */
    public float getminAvertissement(){
        return Valeurs.get(1);
    }

    /** Change le seuil min Critique */
    public void setminCritiqueVal (float v){
        Valeurs.set(1, v);
    }

    /**
     * Retourne le seuil minimal critique
     * @param v
     */
    public float getminCritiqueVal(){
        return Valeurs.get(2);
    }

    /**
     * Change le seuil max critique
     * @param v
     */
    public void setmaxVal(float v){
        Valeurs.set(3, v);
    }

    /**
     * Retourne le seuil minimal
     * @param v
     */
    public float getMaxVal(){
        return Valeurs.get(3);
    }
}
