public abstract class Zone_geographique implements interface_relevee_affiche,interface_activation{
protected static int id=0;
 protected String nom;
protected status_zone active;

private Releve[] list_de_releve=new Releve[50];
private capteur[] list_de_capteur=new capteur[50];
public void modifier_zone(String nom){
this.nom=nom;
}
}
