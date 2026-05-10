public class Culture{
private String date_de_plantation;
private String date_de_recolte;
private String nomcult;
private Croissance stade_de_croissance;
private Exigences_pedologiques EP;
private Famille_cult type;
public Culture(){
this.date_de_plantation="";
this.date_de_recolte="";

}
public Culture(String date_de_plantation, String date_de_recolte ,Famille_cult type,Exigences_pedologiques EP,String nom){
this.date_de_plantation=date_de_plantation;
this.date_de_recolte=date_de_recolte;
this.type=type;
this.EP=EP;
this.nomcult=nom;
}
public Culture(String date_de_plantation, String date_de_recolte ,Famille_cult type,String EX,String nom){
this.date_de_plantation=date_de_plantation;
this.date_de_recolte=date_de_recolte;
this.type=type;
Exigences_pedologiques EP=new Exigences_pedologiques(EX);
this.nomcult=nom;
}
public void mettre_a_jour_croissance(Croissance stade_de_croissance){
    this.stade_de_croissance=stade_de_croissance;


}
public void afficher_info() {
    System.out.println("Nom : " + nomcult);
    System.out.println("Type : " + type);
    System.out.println("Date plantation : " + date_de_plantation);
    System.out.println("Date récolte : " + date_de_recolte);
    System.out.println("Stade : " + stade_de_croissance);
    System.out.println("Exigences : " + EP);
   
}
public String getnomcult(){return nomcult;}
public Croissance getStade(){return stade_de_croissance; }
}
