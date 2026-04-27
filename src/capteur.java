public abstract class capteur implements interface_relevee_affiche,interface_activation{
private Releve[] list_releve=new Releve[50];
private int id;
private Zone_geographique zone;
private status statue;
private int seuil_min;
private int seuil_max;
}