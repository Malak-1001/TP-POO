public class capteur_gps extends capteur{
    private float latitude = 0 ;
    private float longitude = 0 ;

    /**
     * Permets d'envoyer la latitude et la longitude dans un tableau
     * @return la position actuelle dans le tableau de coordonnées
     */
    public float[] EnvoiePosition(){
        float[] coordonnee = new float [2];
        coordonnee[0] = latitude;
        coordonnee[1] = longitude;
        return coordonnee;
    }

    /**
     * Modifie la latitude
     * @param lat
     */
    public void setLatitude( float lat){
        this.latitude = lat;
    }

    
    /**
     * Permets de retourner la latitude enregistré par le capteur
     * @return latitude (float)
     */
    public float getLatitude (){
        return this.latitude;
    }

    /**
     * Modifie la longitude
     * @param lon
     */
    public void setLongitude( float lon){
        this.longitude = lon;
    }
    
    /**
     * Permets de retourner la longitude enregistré par le capteur
     * @return longitude (float)
     */
    public float getLongitude (){
        return this.longitude;
    }


    public void afficher_relevee(){
    }
    public void activation(){}
    public void suspension(){}
}