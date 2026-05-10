import java.util.List;
import java.util.ArrayList;
public  class Animeaux{

private List<capteur> list_capteur = new ArrayList<>();
private static int id=0;
private String espece;
private int age;

private List<Integer> poid = new ArrayList<>();

private sante etat_de_sante;

private List<String> maladie = new ArrayList<>();

public Animeaux(String espece,int age,int poid){

this.age=age;
this.espece=espece;
this.poid.add(poid);

this.etat_de_sante=sante.SAIN;
}
public Animeaux(String espece,int age,int poid,sante etat_de_sante){
  this.age=age;
this.espece=espece;
this.poid.add(poid);

this.etat_de_sante=etat_de_sante; 
}
public void ajouter_maladie(String maladie){
    
this.maladie.add(maladie);


}
}
