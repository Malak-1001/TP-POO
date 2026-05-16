
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

}