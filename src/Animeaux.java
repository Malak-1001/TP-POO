public  class Animeaux{
private capteur[] list_capteur=new capteur[50];
private static int id=0;
private String espece;
private int age;
private int[] poid=new int[50];
private int cpt_update_poid=0;
private sante etat_de_sante;
private String[] maladie=new String[50];
private int cpt_maladie=0;
public Animeaux(String espece,int age,int poid){

this.age=age;
this.espece=espece;
this.poid[0]=poid;
cpt_update_poid=cpt_update_poid+1;
this.etat_de_sante=sante.SAIN;
}
public Animeaux(String espece,int age,int poid,sante etat_de_sante){
  this.age=age;
this.espece=espece;
this.poid[0]=poid;
cpt_update_poid=cpt_update_poid+1;
this.etat_de_sante=etat_de_sante; 
}
public void ajouter_maladie(String maladie){
    if(cpt_maladie<50){
this.maladie[cpt_maladie]=maladie;
cpt_maladie=cpt_maladie+1;
}
}
}
