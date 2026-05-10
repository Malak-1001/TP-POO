import java.util.List;
import java.util.ArrayList;
public class Bassin{
private List<String> espece_aquacole = new ArrayList<>();
private int nbanimeaux;
private programme_alimentaire PA;
public Bassin(){
this.nbanimeaux=0;
PA=new programme_alimentaire();
}
public Bassin(int nbanimeaux){
this.nbanimeaux=nbanimeaux;
PA=new programme_alimentaire();
}
public Bassin(int nbanimeaux, programme_alimentaire PA){
this.nbanimeaux=nbanimeaux;
this.PA=PA;
}
public Bassin(int nbanimeaux,double quantite,String type_aliment ){
this.nbanimeaux=nbanimeaux;
PA=new programme_alimentaire(quantite,type_aliment);
}
public int get_nbanimeaux(){return nbanimeaux;}
public void ajouter_animeaux(int i){this.nbanimeaux=nbanimeaux+i;}
}
