import java.util.List ;
public class capteur_sol extends capteur_num{

    /**
     * Constructeur de la classe capteur environnement
     * @param z
     */
    public capteur_sol(Zone_geographique z){
        super(z);
        super.type = Type_Capteurs.SOL;
        super.Unites = List.of(
            TypeMesure.PH,
            TypeMesure.HUMIDITE, 
            TypeMesure.AZOTE
        );
    }

    /**
     * Constructeur de la classe capteur environnement
     * @param z
     */
    public capteur_sol(Zone_geographique z, StatusCapteur c){
        super(z, c);
        super.type = Type_Capteurs.SOL;
        super.Unites = List.of(
            TypeMesure.PH,
            TypeMesure.HUMIDITE, 
            TypeMesure.AZOTE
        );
    }

    
}

