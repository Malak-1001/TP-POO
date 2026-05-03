public class Bassin{
private String[] espece_aquacole=new String[10];
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
}
