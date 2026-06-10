import java.util.List;

public class capteur_eau extends capteur_num{

    /**
     * Constructeur
     * @param z
     */
    public capteur_eau(Zone_geographique z){
        super(z);
        super.type = Type_Capteurs.EAU;
        super.Unites = List.of(
            TypeMesure.TEMPERATURE, 
            TypeMesure.OXYGENE,
            TypeMesure.PH
        );
        
    }

    /**
     * Constructeur
     * @param z
     */
    public capteur_eau(Zone_geographique z, StatusCapteur c){
        super(z, c);
        super.type = Type_Capteurs.EAU;
        super.Unites = List.of(
            TypeMesure.TEMPERATURE, 
            TypeMesure.OXYGENE
        );
    }


    // Retourne la valeur prélevée avec les type de mesures pris en charge par le capteur d'eau 
    /*public Object[] getVal(TypeMesure m) {
        if((m.equals(TypeMesure.TEMPERATURE) || m.equals(TypeMesure.OXYGENE))){
            return new Object[]{val, m};
        }else {
            System.out.println("Le type de mesure donné est erroné");
            return null;
        }
    }*/



}