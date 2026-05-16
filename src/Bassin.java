import java.util.List;
import java.util.ArrayList;
public class Bassin{
private List<String> espece_aquacole = new ArrayList<>();//list des espece dans le bassin
private int nbanimeaux;//nombre danimeaux dans le basssin
private programme_alimentaire PA;// programme alimentaire des animeaux 
//constructeur 
public Bassin(){
this.nbanimeaux=0;
PA=new programme_alimentaire();
}
// contructeur avec le nombre animeaux 
public Bassin(int nbanimeaux){
this.nbanimeaux=nbanimeaux;
PA=new programme_alimentaire();
}
// contructeur avec le nombre animeaux  et leur programme alimentaire 
public Bassin(int nbanimeaux, programme_alimentaire PA){
this.nbanimeaux=nbanimeaux;
this.PA=PA;
}
// contructeur avec le nombre animeaux cree et ajouter leur programme alimentaire 
public Bassin(int nbanimeaux,double quantite,String type_aliment ){
this.nbanimeaux=nbanimeaux;
PA=new programme_alimentaire(quantite,type_aliment);
}
//getter
public int get_nbanimeaux(){return nbanimeaux;}
//ajouter des animeaux au bassin
public void ajouter_animeaux(int i){this.nbanimeaux=nbanimeaux+i;}
}
