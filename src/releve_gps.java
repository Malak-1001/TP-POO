import java.time.LocalDate;

public class releve_gps extends Releve{

    private Zone_geographique z ; //Pour pouvoir avoir ses limites géographiques
    private float latitude; // y
    private float longitude; // x

    private float distance ; // c'est la distance du point de position à O

    /**
     * Constructeur par défaut de la classe releve GPS
     */
    public releve_gps(Zone_geographique zone, float x, float y, float d, gravite g){
        this.date = LocalDate.now();
        this.latitude = y;
        this.longitude = x;
        this.distance = d ;
        this.z = zone;
        gravity = g;
    }

    
    /**
     * Retourne la distance du point ( donc selon les coordonnées) au centre
     * @return
     */
    public float getDistance(){
        return distance;
    }


    /**
     * Constructeur par défaut de la classe releve GPS
     */
    public releve_gps(Zone_geographique zone){
        this.date = LocalDate.now();
        this.latitude = 0;
        this.longitude = 0;
        this.distance = 0;
        this.z = zone;
    }


    /**
     * Retourne une String ayant les données du relevé numérique 
     */
    public String Display(){
        
        StringBuilder S = new StringBuilder();
        S.append("Releve - date :").append(super.date).append(" \n");
        S.append(" - Coordonnées: ").append("\n");
        S.append("   x = ").append(longitude).append("   y =").append(latitude);
        S.append("    Distance = ").append(distance);
        S.append(" - Niveau de Gravité: ").append(gravity);

        return S.toString() ;
    }
}