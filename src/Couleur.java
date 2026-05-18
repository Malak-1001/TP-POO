public class Couleur {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String ORANGE = "\u001B[38;5;208m";

    /**
     * Retourne le texte en couleur
     * @param g
     * @param color
     * @return
     */
    public static String colorize(gravite g , String color) {
        return color + g + RESET ;
    }
}
