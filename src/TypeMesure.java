public enum TypeMesure {
    TEMPERATURE("C°"),
    HUMIDITE("%"),
    PLUVIOMETRIE("L/m²"),
    PH(""),
    AZOTE("mg/L"),
    OXYGENE("mg/L"),
    ACTIVITE("pas/min");

    private final String unité ;
    TypeMesure (String unité) {
        this.unité = unité ;
    }

    public String getUnite (){
        return unité ;
    }
}
