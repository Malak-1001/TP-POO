import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Affichage aff = new Affichage();
        Ferme ferme = new Ferme();

        int choix = 0;

        while (choix != 8) {
            System.out.println("\n===== MENU FERME =====");
            System.out.println("1. Ajouter une zone");
            System.out.println("2. Modifier une zone");
            System.out.println("3. Désactiver une zone");
            System.out.println("4. Afficher les zones");
            System.out.println("5. Enregistrer production");
            System.out.println("6. mettre a jour et afficher stade de croissance dune culture");
            System.out.println("7. rapport etat de zone");
            System.out.println("8. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {

                case 1:
                    System.out.println("Type de zone : 1.Culture  2.Elevage  3.Aquacole");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nom de la zone : ");
                    String nom = scanner.nextLine();

                    if (type == 1) {
                        Zone_culture zone = new Zone_culture(nom);
                        ferme.ajouter_zone(zone);
                        System.out.println("zone culture ajoutée ");

                    } else if (type == 2) {
                        System.out.println("Type élevage : 1.Ruminant  2.Volail");
                        int elev = scanner.nextInt();
                        scanner.nextLine();
                        type_elevage te = (elev == 1) ? type_elevage.RUMINANT : type_elevage.VOLAIL;
                        Zone_elevage zone = new Zone_elevage(nom, te);
                        ferme.ajouter_zone(zone);
                        System.out.println("Zone élevage ajoutée ");

                    } else if (type == 3) {
                        System.out.print("Nombre d'animaux : ");
                        int nb = scanner.nextInt();
                        scanner.nextLine();
                        Zone_aquacole zone = new Zone_aquacole(nom, nb);
                        ferme.ajouter_zone(zone);
                        System.out.println("Zone aquacole ajoutée ");
                    }
                    break;
                 case 2:
    if (ferme.get_liste_de_zones().isEmpty()) {
        System.out.println("Aucune zone disponible.");
        break;
    }
    aff.lister_zones(ferme);

    System.out.println("Type de zone à modifier :");
    System.out.println("1. Culture");
    System.out.println("2. Elevage");
    System.out.println("3. Aquacole");
    System.out.print("Votre choix : ");
    int typeZone = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Nom de la zone : ");
    String nomZone = scanner.nextLine();

    
    Zone_geographique zoneChoisie = null;
    
    zoneChoisie=ferme.chercher_zone(nomZone);
    if (zoneChoisie == null) {
        System.out.println("Zone introuvable.");
        break;
    }

    
    if (typeZone == 1 && zoneChoisie instanceof Zone_culture) {
        Zone_culture zc = (Zone_culture) zoneChoisie;

        System.out.println("1. Créer une culture");
        System.out.println("2. Ajouter une culture existante");
        System.out.println("3. Créer et ajouter une culture");
        System.out.print("Votre choix : ");
        int choixCulture = scanner.nextInt();
        scanner.nextLine();

        if (choixCulture == 1 || choixCulture == 3) {
            System.out.print("nom culture: ");
            String nomcult = scanner.nextLine();
            System.out.print("Date de plantation : ");
            String datePlant = scanner.nextLine();
            System.out.print("Date de récolte : ");
            String dateRecolt = scanner.nextLine();
             System.out.println("Type élevage : 1.cereales  2.fruits   3.legumes ");
                        int clt = scanner.nextInt();
                        scanner.nextLine();
                        Famille_cult fc=null;
                        if(clt==1){fc=Famille_cult.CEREAL;}
                        if(clt==2){fc=Famille_cult.FRUIT;}
                        if(clt==3){fc=Famille_cult.LEGUMES;}
            System.out.print("Exigences pédologiques  : ");
            String ex = scanner.nextLine();

            Culture nouvelleCulture = new Culture(datePlant, dateRecolt, fc, ex,nomcult);

            if (choixCulture == 3) {
                zc.affecter_cultures(nouvelleCulture);
                System.out.println("Culture créée et ajoutée !");
            } else {
                System.out.println("Culture créée ");
            }

        } else if (choixCulture == 2) {
            
            Culture c = new Culture();
            zc.affecter_cultures(c);
            System.out.println("Culture ajoutée ");
        }

    
    } else if (typeZone == 2 && zoneChoisie instanceof Zone_elevage) {
        Zone_elevage ze = (Zone_elevage) zoneChoisie;

        System.out.println("1. Créer un animal");
        System.out.println("2. Ajouter un animal existant");
        System.out.println("3. Créer et ajouter un animal");
        System.out.print("Votre choix : ");
        int choixAnimal = scanner.nextInt();
        scanner.nextLine();

        if (choixAnimal == 1 || choixAnimal == 3) {
            System.out.print("Espèce : ");
            String espece = scanner.nextLine();
            System.out.print("Age : ");
            int age = scanner.nextInt();
            System.out.print("Poids : ");
            int poids = scanner.nextInt();
            scanner.nextLine();

            Animeaux nouvelAnimal = new Animeaux(espece, age, poids);

            if (choixAnimal == 3) {
                ze.affecter_animal(nouvelAnimal);
                System.out.println("Animal créé et ajouté !");
            } else {
                System.out.println("Animal créé !");
            }

        } else if (choixAnimal == 2) {
            System.out.print("Espèce : ");
            String espece = scanner.nextLine();
            System.out.print("Age : ");
            int age = scanner.nextInt();
            System.out.print("Poids : ");
            int poids = scanner.nextInt();
            scanner.nextLine();
            ze.affecter_animal(espece, age, poids);
            System.out.println("Animal ajouté !");
        }

    
    } else if (typeZone == 3 && zoneChoisie instanceof Zone_aquacole) {
        Zone_aquacole za = (Zone_aquacole) zoneChoisie;

        System.out.print("Nombre d'espaces à ajouter : ");
        int espaces = scanner.nextInt();
        za.ajouter_animeaux(espaces);
        

    } else {
        System.out.println("Type de zone incompatible avec la zone choisie.");
    }
    break;

                case 3:
                    if (ferme.get_liste_de_zones().isEmpty()) {
                        System.out.println("Aucune zone disponible.");
                        break;
                    }
                    aff.lister_zones(ferme);
                     System.out.print("nom de la zone a suspendre : ");
                    String nom1 = scanner.nextLine();
                    ferme.suspension_zone(nom1);
                    System.out.println("Zone désactivée !");
                    break;

                case 4:
                    if (ferme.get_liste_de_zones().isEmpty()) {
                        System.out.println("Aucune zone disponible.");
                        break;
                    }
                    aff.afficher_zones(ferme);
                    break;
              case 5:
    if (ferme.get_liste_de_zones().isEmpty()) {
        System.out.println("Aucune zone disponible.");
        break;
    }
    aff.lister_zones(ferme);

    System.out.println("Type de zone  :");
    System.out.println("1. Culture");
    System.out.println("2. Elevage");
    System.out.println("3. Aquacole");
    System.out.print("Votre choix : ");
    int typeZone1 = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Nom de la zone : ");
    String nomZone1 = scanner.nextLine();

    
    Zone_geographique zoneChoisie1 = null;
    
    zoneChoisie1=ferme.chercher_zone(nomZone1);
    if (zoneChoisie1 == null) {
        System.out.println("Zone introuvable.");
        break;
    }

    
    if (typeZone1 == 1 && zoneChoisie1 instanceof Zone_culture) {
        Zone_culture zc = (Zone_culture) zoneChoisie1;
        System.out.print("type de rendement  : ");
        String tr = scanner.nextLine();
        System.out.print("valeur du rendement  : ");
        int val=scanner.nextInt();
        zc.enregistrer_rendement_culture(tr, val);
    }

    
      if (typeZone1 == 2 && zoneChoisie1 instanceof Zone_elevage) {
        Zone_elevage ze = (Zone_elevage) zoneChoisie1;

        System.out.println("donner la valuer du rendement en "+ze.get_unit());
        float valeur = scanner.nextFloat();
        ze.enregistrer_rendement(valeur);
 

    } 
    
if (typeZone1 == 3 && zoneChoisie1 instanceof Zone_aquacole) {
    Zone_aquacole za = (Zone_aquacole) zoneChoisie1;

    System.out.println("donner la valeur du rendement en " + za.get_unite());
    float valeur = scanner.nextFloat(); 
    scanner.nextLine();
    za.enregistrer_rendement(valeur);
}
    
    break;
    case 6:
    if (ferme.get_liste_de_zones().isEmpty()) {
        System.out.println("Aucune zone disponible.");
        break;
    }
    aff.lister_zones(ferme);

    System.out.print("Nom de la zone culture : ");
    String nomZone6 = scanner.nextLine();

    Zone_geographique zone6 = ferme.chercher_zone(nomZone6);
    if (zone6 == null || !(zone6 instanceof Zone_culture)) {
        System.out.println("Zone culture introuvable.");
        break;
    }

    Zone_culture zc6 = (Zone_culture) zone6;

    // Afficher les cultures disponibles
    zc6.afficher_cultures();

    System.out.print("Nom de la culture : ");
    String nomCulture6 = scanner.nextLine();

    System.out.println("Stade de croissance :");
    System.out.println("1. SEMIS");
    System.out.println("2. GERMINATION");
    System.out.println("3. CROISSANCE");
    System.out.println("4. MATURITE");
    System.out.println("5. RECOLTE");
    System.out.print("Votre choix : ");
    int choixStade = scanner.nextInt();
    scanner.nextLine();

    Croissance stade = null;
    if (choixStade == 1) stade = Croissance.SEMI;
    else if (choixStade == 2) stade = Croissance.GERMINATION;
    else if (choixStade == 3) stade = Croissance.CROISSANCE;
    else if (choixStade == 4) stade = Croissance.MATURITE;
    else if (choixStade == 5) stade = Croissance.RECOLTE;

    if (stade != null) {
        zc6.mettre_a_jour_croissance(nomCulture6, stade);
        System.out.println("Stade de croissance mis à jour !");
    } else {
        System.out.println("Choix invalide.");
    }
    break;

case 7:
    if (ferme.get_liste_de_zones().isEmpty()) {
        System.out.println("Aucune zone disponible.");
        break;
    }
    aff.lister_zones(ferme);

    System.out.print("Nom de la zone culture : ");
    String nomZone7 = scanner.nextLine();

    Zone_geographique zone7 = ferme.chercher_zone(nomZone7);
    if (zone7 == null || !(zone7 instanceof Zone_culture)) {
        System.out.println("Zone culture introuvable.");
        break;
    }

    Zone_culture zc7 = (Zone_culture) zone7;
    zc7.rapport_croissance();
    break;
                case 8:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide, réessayez.");
            }
        }
        scanner.close();
    }
}
