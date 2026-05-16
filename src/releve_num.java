
public class releve_num extends Releve{
    private float val ; // C'est la valeur du relevé
    private TypeMesure unite;

    /**
     * Constructeur d'un relevé numérique avec Un niveau de Gravité Normal par Défaut
     * @param value
     */
    public releve_num (float value, TypeMesure m){
        super();
        this.val = value;
        this.unite = m;
    }

    /**
     * Constructeur d'un relevé numérique avec un niveau de gravité prédéfini selon la valeur donné par le capteur
     * @param value
     * @param G
     */
    public releve_num (float value, gravite G, TypeMesure m) {
        super(G);
        this.val = value;
        this.unite = m;
    }

    /**
     * Retourne une String ayant les données du relevé numérique 
     */
    public String Display(){
        
        StringBuilder S = new StringBuilder();
        S.append("\n Releve - date :").append(super.date).append(" \n");
        S.append(" - Valeur: ").append(val).append(unite.getUnite()).append("\n");
        S.append(" - Niveau de gravité: ").append(gravity).append(" \n");

        return S.toString() ;
    }
}