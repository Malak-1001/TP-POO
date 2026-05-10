
import java.util.List;

public class capteur_env extends capteur_num{
    
    /**
     * Constructeur de la classe capteur environnement
     * @param z
     */
    public capteur_env(Zone_geographique z){
        super(z);
        super.type = Type_Capteurs.ENVIRONMENTAL;
        super.Unites = List.of(
        TypeMesure.TEMPERATURE,
        TypeMesure.HUMIDITE, 
        TypeMesure.PLUVIOMETRIE
    );
        
    }

    /**
     * Constructeur de la classe capteur environnement
     * @param z
     */
    public capteur_env(Zone_geographique z, StatusCapteur c){
        super(z, c);
        super.type = Type_Capteurs.ENVIRONMENTAL;
        super.Unites = List.of(
        TypeMesure.TEMPERATURE,
        TypeMesure.HUMIDITE, 
        TypeMesure.PLUVIOMETRIE
    );
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
    public void setVal(float v, TypeMesure m) throws OutsideTableException , WrongUniteException {
        if(! (Unites.contains(m))) throw new WrongUniteException();
        if(v < getSeuilByIndex(0) || v > getSeuilByIndex(3) ) throw new OutsideTableException();
        values.add(v);
        releve_num R ;
        if(v > getSeuilByIndex(1) && v < getSeuilByIndex(2)){
            R = new releve_num(v, gravite.AVERTISSEMENT, m);
        }else if(v > getSeuilByIndex(2) && v < getSeuilByIndex(3)){
            R = new releve_num(v, gravite.CRITIQUE, m);
        }else{
            R = new releve_num(v,m);
        }
        super.addReleve(R);
    }

}