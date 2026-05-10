import java.time.LocalDate;

public abstract class Releve{
    protected gravite gravite;
    
    //private String text;
    protected LocalDate date;

    /**
     * Constructeur d'un relevé avec Un niveau de Gravité Normal par Défaut
     * @param value
     */
    public Releve (){
        this.gravite = gravite.NORMAL; // la gravite est normale pour la version simple
        //this.text = ""; // vide 
        this.date = LocalDate.now();
    }

    /**
     * Constructeur d'un relevé avec un niveau de gravité prédéfini selon la valeur donné par le capteur
     * @param value
     * @param G
     */
    public Releve (gravite G) {
        this.date = LocalDate.now();
        //this.text = "" ; //vide
        this.gravite = G;
    }

    /**
     * Retourne un string permettant d'afficher le relevé
     * @return
     */
    public abstract String Display();

    

}