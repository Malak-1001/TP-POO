import java.time.LocalDate;

public class Alerte{
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
    }

    /**
     * Change le statut de l'Alerte ( ACQUITTE )
     */
    public void Acquitter(){
        this.statut = statue_alerte.AQUITTE;
    }

    /**
     * Change le statut l'Alerte ( SUPPRIME )
     */
    public void Supprimer(){
        this.statut = statue_alerte.SUPPRIME;
    }

}