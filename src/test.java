public class test{
  public static void main(String[] args) {
      Ferme ferme = new Ferme();//on cree une ferme 
      Affichage affiche = new Affichage();//on cree un affichage
      
      Limite_geographique l1=new Limite_geographique(23,54,7);//on cree des limite pour la zone
      Zone_culture zc1 = new Zone_culture("zc1",l1, ferme);//on cree une zone culture
      // la zone sajoute directement a la liste de zone (depuis son constrcteur 
       System.out.println("-----------------------------------");
      System.out.println("Afficher les zones de la ferme:");
      System.out.println("-----------------------------------");
      
      affiche.afficher_zones(ferme);//on liste la list de zone 
      
      System.out.println("-----------------------------------");
      
      
      Famille_cult f1= Famille_cult.CEREAL;//cree un fc pour la culture
      Exigences_pedologiques ep = new Exigences_pedologiques(3,8,12,54);//cree les exigence pour la cuture
      Culture cult1 = new Culture("01/02/2020", "02/03/2021" ,f1,ep,"cult1");//cree la culture
      
      zc1.affecter_cultures(cult1);//on affecte la culture cree a la zone
       System.out.println("-----------------------------------");
      System.out.println("affiche les culture de la zone zc1");
      
      System.out.println("-----------------------------------");
      
      zc1.afficher_cultures();//on affiche tout les culture de la zone
      
      
      System.out.println("-----------------------------------");
      
      Croissance c1 = Croissance.SEMI;//on cree un etat de croissance
      cult1.mettre_a_jour_croissance(c1);//mise a jour du stade de croissance
      System.out.println("-----------------------------------");
      System.out.println("afficher les culture apres mise a jour de du stade de croissance");
       System.out.println("-----------------------------------");
      zc1.afficher_cultures();//on affiche tout les culture de la zone apres modification etat de la culture
      System.out.println("-----------------------------------");
      System.out.println("on affiche le rapport de croissance des culture de la zone zc1");
      System.out.println("-----------------------------------");
      
      zc1.rapport_croissance();//afficher le rapport des culture dans la zone zc1
      System.out.println("-----------------------------------");
      zc1.enregistrer_rendement_culture("pomme",5);//enregistrer le rendement 
      
      Limite_geographique l2=new Limite_geographique(26,58,6); 
      type_elevage t1 = type_elevage.VOLAIL;
      Zone_elevage ze1 = new Zone_elevage("ze1", t1,l2,ferme);
      
      System.out.println("-----------------------------------");
      System.out.println("afficher les zone de la ferme ");
       System.out.println("-----------------------------------");
      affiche.afficher_zones(ferme);//on liste la list de zone
      System.out.println("-----------------------------------");
      sante s1=sante.SAIN;
      Animeaux a1=new Animeaux("poule",4,1,s1);//cree un animal
      ze1.affecter_animal(a1);//lajouter a la zone
      System.out.println("-----------------------------------");
      System.out.println("afficher les info de lanimal cree");
       System.out.println("-----------------------------------");
      a1.afficher_animal();//on affiche les info de lanimal 
      System.out.println("-----------------------------------");
      System.out.println("afficher zone de la ferme ");
       System.out.println("-----------------------------------");
      affiche.afficher_zones(ferme);//le nombre dentiter a augmenter
      System.out.println("-----------------------------------");
      
      
      a1.ajouter_maladie("rage");//on ajoute une maladie a la liste des maladie de lanimal
      a1.ajouter_poids(2);// on met a jour son poid 
      System.out.println("-----------------------------------");
      System.out.println("levolution de letat de sante maladie de lanimal");
       System.out.println("-----------------------------------");
      a1.afficher_maladie_evolution();//affiche elovution des maladie pour lanimal
      System.out.println("-----------------------------------");
      System.out.println("levolution du poid de lanimal");
       System.out.println("-----------------------------------");
      a1.afficher_poid_evolution();// afiiche levolution du poid chez lanimal
      System.out.println("-----------------------------------");
      
      programme_alimentaire p1 = new programme_alimentaire(12,"ble");
      ze1.ajouter_programme_alimentaire(p1);//ajouter la programme allimentaire a la zone
      System.out.println("-----------------------------------");
      System.out.println("afficher les programmes alimentaire de la zone ze1");
       System.out.println("-----------------------------------");
      ze1.afficher_programme_alimentaire();//afficher les programe de la zone
      System.out.println("-----------------------------------");
      
      Limite_geographique l3=new Limite_geographique(20,43,19);
      Zone_aquacole za1 = new Zone_aquacole("za1",10,l3,ferme);
      System.out.println("-----------------------------------");
      System.out.println("info de la zonne aquacole za1");
       System.out.println("-----------------------------------");
      za1.afficher_info();//afficher les info de la zone
      System.out.println("-----------------------------------");
      
      za1.ajouter_animeaux(12);//on ajoute 12 animeaux a la zone(au bassin de la zone)
      System.out.println("-----------------------------------");
      System.out.println("info de la zonne aquacole za1 apres avoir agmentrer le nb danimeaux");
       System.out.println("-----------------------------------");
      za1.afficher_info();//le nombre dentite qui augmente 
      System.out.println("-----------------------------------");
      
      za1.suspension();//on suspend la zone
       System.out.println("-----------------------------------");
        System.out.println("afficher la zone apres suspenssion");
       za1.afficher_info();//la zone est suspendu 
        System.out.println("-----------------------------------");
         System.out.println("Afficher toute les zone de la ferme ");
          System.out.println("-----------------------------------");
      affiche.afficher_zones(ferme);//on liste la list de zone de la ferme
    }

}