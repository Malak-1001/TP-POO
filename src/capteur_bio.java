import java.util.List;

public class capteur_bio extends capteur_num{
    
    /**
     * Constructeur
     * @param z
     */
    public capteur_bio(Zone_geographique z) {
        super(z);
        super.type = Type_Capteurs.BIOMETRIQUE;
        super.Unites = List.of(
            TypeMesure.TEMPERATURE, 
            TypeMesure.ACTIVITE
        );
    }

    /**
     * Constructeur
     * @param z
     */
    public capteur_bio(Zone_geographique z, StatusCapteur c) {
        super(z,c);
        super.type = Type_Capteurs.BIOMETRIQUE;
        super.Unites = List.of(
            TypeMesure.TEMPERATURE, 
            TypeMesure.ACTIVITE
        );
    }

    // A CHECKER JUSTE APRES
    /**
     * Retourne une valeur
     */
    /*public Object[] getVal(TypeMesure m){
        if (!(m.equals(TypeMesure.TEMPERATURE) && m.equals(TypeMesure.ACTIVITE))){
            
        } else {
            return new Object[]{val, temperature};
        }
    }*/


}