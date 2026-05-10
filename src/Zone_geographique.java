public abstract class Zone_geographique implements interface_activation{
private int id;
private String nom;
private status_zone active;
private  Zone_geographique type;
private Releve[] list_de_releve=new Releve[50];
private capteur[] list_de_capteur=new capteur[50];
public abstract void create_zone();
}