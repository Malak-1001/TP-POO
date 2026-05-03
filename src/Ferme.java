public class Ferme{
private Zone_geographique[] liste_de_zones = new Zone_geographique[50];
private int cpt_zones=0;
private Alerte[] liste_alerte=new Alerte[50];
private int cpt_alerte=0;
private Releve[] liste_de_releve=new Releve[50];
private int cpt_releve=0;
public void ajouter_zone(Zone_geographique zone){
    if(cpt_zones<50){
liste_de_zones[cpt_zones]=zone;
cpt_zones = cpt_zones+1;
}
else{return;}
}
public void ajouter_zone(String type ,String nom){

Zone_geographique zone;
if (type=="culture" || type =="Culture" || type=="CULTURE"){
zone=new Zone_culture(nom);
liste_de_zones[cpt_zones]=zone;
cpt_zones = cpt_zones+1;
}
if (type=="aquacole" || type =="Aquacole" || type=="AQUACOLE"){
zone=new Zone_aquacole(nom);
liste_de_zones[cpt_zones]=zone;
cpt_zones = cpt_zones+1;
}
}


public void ajouter_zone( String nom, type_elevage elevage){
Zone_geographique zone;
zone = new Zone_elevage(  nom,elevage );
liste_de_zones[cpt_zones]=zone;
cpt_zones = cpt_zones+1;
}
public void ajouter_alerte(Alerte alerte){
liste_alerte[cpt_alerte]=alerte;
cpt_alerte = cpt_alerte+1;
return;
}
public void releve(Releve releve){
liste_de_releve[cpt_releve]=releve;
cpt_releve = cpt_releve+1;
return;
}
}
