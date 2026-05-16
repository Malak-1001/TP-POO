import java.time.LocalDate;

public class capteur_gps extends capteur{
    private final int MAX = 100 ;
    private float[] [] coordonnee  ;
    private int i ;

    /*
     * Constructeur de la classe capteur_gps avec 
     * @param latitude
     * @param longitude
     */
    public capteur_gps(Zone_geographique z) {
        super(z);
        super.type = Type_Capteurs.GPS;
        this.coordonnee = new float [MAX][2];
    }

    public capteur_gps(Zone_geographique z, StatusCapteur s) {
        super(z, s);
        super.type = Type_Capteurs.GPS;
        this.coordonnee = new float [MAX][2];
    }



    /**
     * Retourne dans la distance calculé
     * @return
     */
    public float CalculDistance( float longitude, float latitude){
        Limite_geographique L = zone.limites ;
        float[] limites = L.get_limite();
        float cx = limites[0];
        float cy = limites[1];
        return (float) Math.sqrt(Math.pow(longitude -cx , 2) + Math.pow(latitude - cy, 2));
    }

    /**
     * Retourne un boolean qui détermine si le point est à l'intérieur de la zone (limites géographiques) ou non 
     * @return
     */
    public boolean DanslaZone(float longitude, float latitude){
        float rayon = zone.limites.get_r();
        float distance = CalculDistance(longitude, latitude);
        return distance <= rayon ;
    }
   



    /**
     * Ajoute des coordonnées x y au tableau de coordonnées du capteur tout en créant un relevé 
     * @param lat
     */
    public void setCoordonnee( float lon, float lat, Ferme f){
        releve_gps R;
        if(!DanslaZone(lon, lat)){
            R = new releve_gps(zone, lat, lon, CalculDistance(lon, lat), gravite.CRITIQUE);
            LocalDate d = R.getDate();
            Alerte A = new Alerte(super.zone, R, gravite.AVERTISSEMENT, type, d );
            f.ajouter_alerte(A);
        }else{
            R = new releve_gps(zone, lat, lon, CalculDistance(lon, lat), gravite.NORMAL);
        }
        super.addReleve(R);

        this.coordonnee[i][0] = lat ;
        this.coordonnee[i][1] = lon ;
        i++;
    }

    
    /**
     * Permets de retourner la latitude enregistré par le capteur
     * @return latitude (float)
     */
    public float[] getCoordonnee (){
        float[] ActualCoord = new float[2]; 
        ActualCoord[0] =this.coordonnee[i][0] ; //Latitude 
        ActualCoord[1] =this.coordonnee[i][1];  //Longitude
        return ActualCoord;
    }

    
}