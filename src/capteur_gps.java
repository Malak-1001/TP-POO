public class capteur_gps extends capteur{
    private final int MAX = 100 ;
    private float[] [] coordonnee  ;
    private int i ;

    /**
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
     * Modifie la latitude
     * @param lat
     */
    public void setCoordonnee( float lat, float lon){
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