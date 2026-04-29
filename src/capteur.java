public abstract class capteur implements interface_relevee_affiche,interface_activation {
private Releve[] list_releve=new Releve[50];
private int id;
private Zone_geographique zone;
private StatusCapteur statue;
private float[] Seuils = new float[4]; // Valeur MinGénéral - min Avertissement - maxAvertissement - maxCritique

}