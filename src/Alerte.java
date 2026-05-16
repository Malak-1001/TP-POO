import java.time.LocalDate;

public class Alerte implements Comparable<Alerte>{
    private Releve releve;
    private Zone_geographique zone;
    private gravite niveau;
    private statue_alerte statut;
    private LocalDate date;
    private Type_Capteurs type;

    /**
     * Constructeur de la classe Alerte
     * @param r
     */
    public Alerte(Zone_geographique z,Releve r, gravite g, Type_Capteurs t, LocalDate s){
        this.releve = r ;
        this.niveau = g;
        this.statut = statue_alerte.ACTIF;
        this.type = t ;
        this.date = s ; 
        this.zone = z ;
    }

    public int compareTo( Alerte A){
        if(A.statut.equals(statue_alerte.ACTIF)){
            return 0;
        }else{
            return 1;
        }
    }

    /**
     * Change le statut de l'Alerte ( ACQUITTE )
     */
    public void Acquitter(){
        this.statut = statue_alerte.AQUITTE;
    }

    /**
     * Retourne le statut de l'alerte
     * @return
     */
    public statue_alerte getStatut(){
        return statut;
    }

    /**
     * Retourne la date de création de l'alerte
     * @return
     */
    public LocalDate getDate(){
        return date;
    }

    /**
     * Retourne le type de capteurs de l'alerte
     * @return
     */
    public Type_Capteurs getTypeCapteurs (){
        return type;
    }

    /**
     * Retourne le niveau de gravité de l'alerte
     * @return
     */
    public gravite getNiveau(){
        return niveau;
    }

    /**
     * Retourne la zone géographique de l'alerte
     * @return
     */
    public Zone_geographique getZone(){
        return zone;
    }

    /**
     * Change le statut l'Alerte ( SUPPRIME )
     */
    public void Supprimer(){
        this.statut = statue_alerte.SUPPRIME;
    }

    /**
     * Retourne une String ayant les données du relevé numérique 
     */
    public String Display(){
        
        StringBuilder S = new StringBuilder();
        
        S.append("\n Alerte - date :").append(date).append(" \n");
        S.append("Zone: "+ zone.get_nom());
        S.append(" - Statut de l'alerte: ").append(statut).append(" \n");
        S.append(" - Niveau de gravité: ").append(niveau).append(" \n");
        S.append(" - Déclenché par Capteur de type : ").append(type).append("\n");
        return S.toString() ;
    }

}