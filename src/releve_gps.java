public class releve_gps extends Releve{

    private Map<float, float> coordonnee = new

    /**
     * Retourne une String ayant les données du relevé numérique 
     */
    public String Display(){
        
        StringBuilder S = new StringBuilder();
        S.append("Releve - date :").append(super.date).append(" \n");
        S.append(" - Valeur: ").append(val).append(unite).append("\n");
        S.append(" - Niveau de gravité: ").append(gravite);

        return S.toString() ;
    }
}