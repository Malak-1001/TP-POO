public class Culture{
private String date_de_plantation;
private String date_de_recolte;

private Croissance stade_de_croissance;
private Exigences_pedologiques EP;
private Famille_cult type;
public Culture(){
this.date_de_plantation="";
this.date_de_recolte="";

}
public Culture(String date_de_plantation, String date_de_recolte ,Famille_cult type,Exigences_pedologiques EP){
this.date_de_plantation=date_de_plantation;
this.date_de_recolte=date_de_recolte;
this.type=type;
this.EP=EP;
}
public void mettre_a_jour_croissance(Croissance stade_de_croissance){
    this.stade_de_croissance=stade_de_croissance;


}
}
