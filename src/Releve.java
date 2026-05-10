import java.time.LocalDate;

public abstract class Releve{
    protected gravite gravity;
    
    //private String text;
    protected LocalDate date;

    /**
     * Constructeur d'un relevé avec Un niveau de Gravité Normal par Défaut
     * @param 
     */
    public Releve (){
        this.gravity =gravite.NORMAL ; // la gravite est normale pour la version simple
        //this.text = ""; // vide 
        this.date = LocalDate.now();
    }

    /**
     * Constructeur d'un relevé avec un niveau de gravité prédéfini selon la valeur donné par le capteur
     * 
     * @param G
     */
    public Releve (gravite G) {
        this.date = LocalDate.now();
        //this.text = "" ; //vide
        this.gravity = G;
    }

    /**
     * Retourne un string permettant d'afficher le relevé
     * @return
     */
    public abstract String Display();

    /**
     * Retourne la date du relevé
     * @return
     */
    public LocalDate getDate(){
        return date;
    }

    

}