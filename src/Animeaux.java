import java.util.List;
import java.util.ArrayList;
public  class Animeaux{

private List<capteur> list_capteur = new ArrayList<>();//liste des capteur pour un animal
private static int cpt=1;//compteur du nombre danimeaux
private int id;//id de chaque animal
private String espece;//espece
private int age;//age de lanimal

private List<Integer> poid = new ArrayList<>();//evolution du poid de lanimal

private sante etat_de_sante;//etat de santer actuel de lanimal

private List<String> maladie = new ArrayList<>();//liste des maladie qua eu lanimal
//constructeur et etat de sante sain par defaut
public Animeaux(String espece,int age,int poid){

this.age=age;
this.espece=espece;
this.poid.add(poid);
this.id=Animeaux.cpt;
Animeaux.cpt++;
this.etat_de_sante=sante.SAIN;
}
//constructeur et etat de santer init
public Animeaux(String espece,int age,int poid,sante etat_de_sante){
  this.age=age;
  this.id =this.cpt;
  cpt++ ;
this.espece=espece;
this.poid.add(poid);
this.id=Animeaux.cpt;
Animeaux.cpt++;
this.etat_de_sante=etat_de_sante; 
}
// ajouter une maladie a lanimal 
public void ajouter_maladie(String maladie){
    
this.maladie.add(maladie);


}

public void ajouter_poids(int nouveau_poid){
  this.poid.add(nouveau_poid);
}

public void set_etat_de_sante(sante etat){
  this.etat_de_sante = etat;
}
public void afficher_animal(){
System.out.println("age: "+age);
System.out.println("espece: "+espece);
System.out.println("etat de sante : "+etat_de_sante);

}
public void afficher_maladie_evolution(){
for (String m : maladie){
    int i=1; 
System.out.println("maladie"+i+" "+m);
i++;

}
}
public void afficher_poid_evolution(){
for (float p :poid ){
    int i=1; 
System.out.println("poid"+i+" "+p);
i++;

}
}
public int get_id(){return id;}
public String get_espece(){return espece;}
public int get_age(){return age;}
public sante get_etat_de_sante(){return etat_de_sante;}
public int get_dernier_poid(){
  if (poid.isEmpty()) return 0;
  return poid.get(poid.size()-1);
}
public ArrayList<Integer> get_poids_evolution(){return (ArrayList<Integer>)poid; }
public ArrayList<String> get_maladie_evolution(){return (ArrayList<String>)maladie; }
}
