public class Culture{
private String date_de_plantation;
private String date_de_recolte;
private String nomcult;//nom de la culture
private Croissance stade_de_croissance;//stade de croissance
private Exigences_pedologiques EP;
private Famille_cult type;//cereal fruit ou legume
//constructeur 
public Culture(){
this.date_de_plantation="";
this.date_de_recolte="";

}
//constructeur avec tout les attribut en entrer
public Culture(String date_de_plantation, String date_de_recolte ,Famille_cult type,Exigences_pedologiques EP,String nom){
this.date_de_plantation=date_de_plantation;
this.date_de_recolte=date_de_recolte;
this.type=type;
this.EP=EP;
this.nomcult=nom;
}
//constructeur cree et ajouter exigence p
public Culture(String date_de_plantation, String date_de_recolte ,Famille_cult type,
               float ph_min, float ph_max, float humidite_min, float humidite_max,
               String nom){
this.date_de_plantation=date_de_plantation;
this.date_de_recolte=date_de_recolte;
this.type=type;
this.EP=new Exigences_pedologiques(ph_min, ph_max, humidite_min, humidite_max);
this.nomcult=nom;
}
//metre a jour la croissance 
public void mettre_a_jour_croissance(Croissance stade_de_croissance){
    this.stade_de_croissance=stade_de_croissance;


}
//methode pour le test
public void afficher_info() {
    System.out.println("Nom : " + nomcult);
    System.out.println("Type : " + type);
    System.out.println("Date plantation : " + date_de_plantation);
    System.out.println("Date récolte : " + date_de_recolte);
    System.out.println("Stade : " + stade_de_croissance);
    System.out.println("Exigences : " + EP.affiche_ex());
    
   
}
//getters
public String getnomcult(){return nomcult;}
public Croissance getStade(){return stade_de_croissance; }
public Famille_cult getType(){return type;}
public String getDate_de_plantation(){return date_de_plantation;}
public String getDate_de_recolte(){return date_de_recolte;}
public Exigences_pedologiques getExigences(){return EP;}
}
