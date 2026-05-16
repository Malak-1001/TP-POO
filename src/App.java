import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        Ferme ferme = new Ferme();

        type_elevage t1 = type_elevage.RUMINANT;
        type_elevage t2 = type_elevage.VOLAIL;



        Zone_culture Zc1 = new Zone_culture("Zc1");
        Zone_aquacole Za1 = new Zone_aquacole("Za1");
        Zone_elevage Ze1 = new Zone_elevage("Ze1",t1 );
        Zone_elevage Ze2 = new Zone_elevage("Ze2", t2);

        

        Famille_cult famille = Famille_cult.CEREAL;
        Exigences_pedologiques e = new Exigences_pedologiques("exigences pédologiques");

        Culture c = new Culture("12/05/2026", "30/05/2026", famille, e, "Cult1");
        
        Zc1.affecter_cultures(c);
        System.out.println("------------------------------------------------------------");
        Zc1.afficher_info();
        
        Croissance r = Croissance.GERMINATION;
        c.mettre_a_jour_croissance(r);
        Zc1.afficher_cultures();

        System.out.println("------------------------------------------------------------");

        Za1.ajouter_animeaux(12);
        Za1.afficher_info();

        System.out.println("------------------------------------------------------------");

        sante s = sante.QUARANTAINE ;
        Animeaux a1 = new Animeaux("Vaches", 3, 10);
        Animeaux a2 = new Animeaux("Poules", 4, 3, s);
        Animeaux a3 = new Animeaux("Moutons", 3, 10);

        Ze1.affecter_animal(a1);
        Ze2.affecter_animal(a2);
        Ze1.affecter_animal(a3);

        Ze1.afficher_info();

        System.out.println("------------------------------------------------------------");

        Ze2.afficher_info();
        System.out.println("------------------------------------------------------------");

        Zc1.suspension();
        Zc1.afficher_info();

        System.out.println("------------------------------------------------------------");

        Zc1.enregistrer_rendement_culture("rendement1", 120); // Ajoute à la zone cultures un rendement à au record

        Za1.enregistrer_rendement(30); // Ajouter au tableau poids de la récolte à la zone Aquacole

        Ze1.enregistrer_rendement(12); // Ajouter au tableau rendement production de la zone elevage


        System.out.println("------------------------------------------------------------");

        capteur_bio bio1 = new capteur_bio(Ze2);
        capteur_eau eau1 = new capteur_eau(Za1);
        capteur_env env1 = new capteur_env(Zc1);
        capteur_sol s1 = new capteur_sol(Zc1);

        capteur_gps gps1 = new capteur_gps(Ze1);
        
        Limite_geographique L = new Limite_geographique(50, 15, 20);
        Ze1.setLimites(L);

        TypeMesure m = TypeMesure.TEMPERATURE;
        Plage_Seuils Sbio = new Plage_Seuils(10, 32, 42, 50,m);
        m = TypeMesure.OXYGENE;
        Plage_Seuils Seau = new Plage_Seuils(13, 34, 35, 55,m);
        m = TypeMesure.PH;
        Plage_Seuils Ssol = new Plage_Seuils(16, 30, 42, 50,m);
        m = TypeMesure.PLUVIOMETRIE;
        Plage_Seuils Senv = new Plage_Seuils(16, 30, 42, 50,m);

        Zc1.AjouterCapteur(s1);
        s1.addPlageSeuils(Ssol);
        

        Zc1.AjouterCapteur(env1);
        env1.addPlageSeuils(Senv);

        Za1.AjouterCapteur(eau1);
        eau1.addPlageSeuils(Seau);

        Ze2.AjouterCapteur(bio1);
        bio1.addPlageSeuils(Sbio);

        Ze1.AjouterCapteur(gps1);

        gps1.setCoordonnee(20, 30, ferme);
        

        bio1.suspension();

        eau1.Defaillant();

        m = TypeMesure.PH;

        try{
            s1.setVal(20, m,ferme);
            s1.setVal(35, m, ferme);
            m =TypeMesure.PLUVIOMETRIE;
            env1.setVal(43, m, ferme);
            m =TypeMesure.TEMPERATURE;
            bio1.setVal(33, m, ferme);
            
        }catch(WrongUniteException exception){
            System.out.println("L'unité de mesure donnée est erronée");
        }catch(OutsideTableException exception){
            System.out.println("La valeur donnée est outside la plage de Seuils");
        }
            
        

        // Affiche les releve du capteur s1
        System.out.println(s1.DisplayListReleve());
        System.out.println("------------------------------------------------------------");

        // Afficher les releves du capteur bio1
        System.out.println(bio1.DisplayListReleve());
        System.out.println("------------------------------------------------------------");

        System.out.println(gps1.DisplayListReleve());
        System.out.println("------------------------------------------------------------");

        System.out.println(env1.DisplayListReleve());
        System.out.println("------------------------------------------------------------");

        LocalDate d1 = LocalDate.of(2026, 02, 13);
        LocalDate d2 = LocalDate.of(2026, 06, 20);
        try{
            System.out.println(s1.DisplayReleveFiltre(d1, d2));
        }catch(BeforeDateException exception){
            System.out.println("La date de début est avant la date d'arrivée");
        }
        
        System.out.println(ferme.DisplayAlertes());
        System.out.println("------------------------------------------------------------");

        System.out.println(ferme.DisplayAlertesZone(Zc1));
        System.out.println("------------------------------------------------------------");

        Type_Capteurs t = Type_Capteurs.ENVIRONMENTAL;
        System.out.println(ferme.DisplayAlertesCapteur(t));
        System.out.println("------------------------------------------------------------");

        gravite g = gravite.AVERTISSEMENT;
        System.out.println(ferme.DisplayAlertesNiveau(g));
        System.out.println("------------------------------------------------------------");

        try{
            System.out.println(ferme.DisplayAlertesPeriode(d1, d2));
        }catch(BeforeDateException exception){
            System.out.println("La date de début est avant la date d'arrivée");
        }
        System.out.println("------------------------------------------------------------");

        System.out.println(ferme.DisplayAlertesActive());
        System.out.println("------------------------------------------------------------");


        
        
    }


}
