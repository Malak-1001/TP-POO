import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.chart.*;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.classfile.Label;
//import java.lang.classfile.Label;
//import java.lang.classfile.Label;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import javax.swing.text.html.ListView;

/*import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import javax.swing.text.html.ListView;*/

/*import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import javax.swing.text.html.ListView;*/


public class InterfaceFerme extends Application {

    private final Ferme ferme = new Ferme();

    private TableView<LigneZone> tableZones;//table des zone
    private ObservableList<LigneZone> donneesZones;//ligne de la zone 
    private TextArea console;//console
    private Label barreEtat;//message succes ou echec
    private Stage stagePrincipal;//fenetre principale de lapp

    // selection de zones 
    private ComboBox<String> comboZonesToutes;//liste deroulante des zone dans longlet alerte
    private ComboBox<String> comboZonesCulture;//liste deroulante des zone culture
    private ComboBox<String> comboZonesElevage;//liste deroulante des zone elevage
    private ComboBox<String> comboZonesAquacole;//liste deroulante des zone aquacole
    private ComboBox<String> comboZonesCapteur;//liste deroulante des zone dans longletcapteur
    private ComboBox<String> comboCapteurs;//liste deroulante des capteurs de la zone choisie
    private ComboBox<String> typeCapteur; // New add

    // New for time management
    private Timeline timelineReleves = null;   // le timer périodique
    private int ligneEnCours = 0;              // index de la prochaine ligne à lire
    private List<String> lignesReleves = new ArrayList<>(); // lignes pré-chargées

    //------Malak
    /*private void afficherGraphique(capteur_num c) {
        // Axe X = index du relevé (1, 2, 3...), Axe Y = valeur
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Relevé n°");
        yAxis.setLabel("Valeur");

        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle("Évolution des relevés - " + c.getType() + " (Zone: " + c.getZone().get_nom() + ")");
        chart.setCreateSymbols(true);
        chart.setAnimated(false);

        // On regroupe par TypeMesure pour avoir une série par mesure
        java.util.Map<TypeMesure, XYChart.Series<Number, Number>> seriesMap = new java.util.LinkedHashMap<>();

        List<Releve> releves = c.getListReleve();
        for (int i = 0; i < releves.size(); i++) {
            Releve r = releves.get(i);
            if (r instanceof releve_num) {
                releve_num rn = (releve_num) r;
                // On a besoin du TypeMesure — il faut un getter (voir note ci-dessous)
                TypeMesure m = rn.getUnite();
                seriesMap.putIfAbsent(m, new XYChart.Series<>());
                seriesMap.get(m).setName(m.name());
                seriesMap.get(m).getData().add(new XYChart.Data<>(i + 1, rn.getVal()));
            }
        }

        chart.getData().addAll(seriesMap.values());

        Stage s = new Stage();
        s.setTitle("Graphique - " + c.getType());
        s.setScene(new Scene(chart, 650, 400));
        s.show();
    }*/

    private Node ongletTableauBord() {
        ComboBox<String> comboZone = new ComboBox<>();
        comboZone.setPrefWidth(260);
        comboZone.setPromptText("Choisir une zone...");

        VBox panneauIndicateurs = new VBox(10);
        panneauIndicateurs.setPadding(new Insets(10));

        Button rafraichirBtn = new Button("Afficher le tableau de bord");
        rafraichirBtn.setOnAction(e -> {
            Zone_geographique z = ferme.chercher_zone(comboZone.getValue());
            if (z == null) { erreur("Choisissez une zone."); return; }
            panneauIndicateurs.getChildren().clear();

            for (capteur cap : z.getListCapteurs()) {
                if (!(cap instanceof capteur_num)) continue;
                capteur_num cn = (capteur_num) cap;

                // Titre du capteur
                Label titreCap = new Label("Capteur " + cn.getId() + " — " + cn.getType() + " (" + cn.getStatut() + ")");
                titreCap.setFont(Font.font("System", FontWeight.BOLD, 13));
                panneauIndicateurs.getChildren().add(titreCap);

                // Dernier relevé de chaque mesure
                List<Releve> releves = cn.getListReleve();
                if (releves.isEmpty()) {
                    panneauIndicateurs.getChildren().add(new Label("   Aucun relevé."));
                    continue;
                }

                // On cherche le dernier relevé par TypeMesure
                java.util.Map<TypeMesure, releve_num> derniers = new java.util.LinkedHashMap<>();
                for (Releve r : releves) {
                    if (r instanceof releve_num) {
                        releve_num rn = (releve_num) r;
                        derniers.put(rn.getUnite(), rn);
                    }
                }

                for (java.util.Map.Entry<TypeMesure, releve_num> entry : derniers.entrySet()) {
                    releve_num rn = entry.getValue();
                    gravite g = rn.gravity;

                    // Couleur selon gravité
                    String couleur;
                    String symbole;
                    if (g == gravite.CRITIQUE) {
                        couleur = "#c0392b"; symbole = "🔴";
                    } else if (g == gravite.AVERTISSEMENT) {
                        couleur = "#e67e22"; symbole = "🟠";
                    } else {
                        couleur = "#1a7f37"; symbole = "🟢";
                    }

                    Label indicateur = new Label(
                        symbole + "  " + entry.getKey().name()
                        + " : " + rn.getVal() + " " + entry.getKey().getUnite()
                        + "   [" + g.name() + "]"
                        + "   " + rn.date
                    );
                    indicateur.setStyle("-fx-text-fill: " + couleur + "; -fx-font-size: 13px;");
                    panneauIndicateurs.getChildren().add(indicateur);
                }

                panneauIndicateurs.getChildren().add(new Separator());
            }

            if (panneauIndicateurs.getChildren().isEmpty()) {
                panneauIndicateurs.getChildren().add(new Label("Aucun capteur numérique dans cette zone."));
            }
        });

        // Rafraîchir la combo quand on entre dans l'onglet
        comboZone.setOnMouseClicked(e -> remplir(comboZone, ferme.get_liste_de_zones()));

        ScrollPane scroll = new ScrollPane(panneauIndicateurs);
        scroll.setFitToWidth(true);

        VBox box = new VBox(10,
                titreSection("Tableau de bord — derniers relevés par zone"),
                new HBox(8, new Label("Zone"), comboZone, rafraichirBtn),
                new Separator(),
                scroll);
        VBox.setVgrow(scroll, Priority.ALWAYS);
        box.setPadding(new Insets(10));
        return box;
    }

    // Graphique pour un seul capteur — une série par TypeMesure
private void afficherGraphiqueCapteur(capteur_num c) {
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Relevé n°");
    yAxis.setLabel("Valeur");

    LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
    chart.setTitle("Capteur " + c.getType() + " — Zone: " + c.getZone().get_nom());
    chart.setAnimated(false);

    java.util.Map<TypeMesure, XYChart.Series<Number, Number>> seriesMap = new java.util.LinkedHashMap<>();
    List<Releve> releves = c.getListReleve();
    for (int i = 0; i < releves.size(); i++) {
        if (releves.get(i) instanceof releve_num rn) {
            TypeMesure m = rn.getUnite();
            seriesMap.putIfAbsent(m, new XYChart.Series<>());
            seriesMap.get(m).setName(m.name() + " (" + m.getUnite() + ")");
            seriesMap.get(m).getData().add(new XYChart.Data<>(i + 1, rn.getVal()));
        }
    }
    chart.getData().addAll(seriesMap.values());

    Stage s = new Stage();
    s.setTitle("Graphique capteur " + c.getId());
    s.setScene(new Scene(chart, 700, 420));
    s.show();
}

// Graphique pour toute une zone — une série par capteur numérique
private void afficherGraphiqueZone(Zone_geographique z) {
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Relevé n°");
    yAxis.setLabel("Valeur");

    LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
    chart.setTitle("Zone: " + z.get_nom() + " — tous les capteurs numériques");
    chart.setAnimated(false);

    for (capteur c : z.getListCapteurs()) {
        if (!(c instanceof capteur_num cn)) continue;
        List<Releve> releves = cn.getListReleve();
        // une série par (capteur + mesure)
        java.util.Map<TypeMesure, XYChart.Series<Number, Number>> seriesMap = new java.util.LinkedHashMap<>();
        for (int i = 0; i < releves.size(); i++) {
            if (releves.get(i) instanceof releve_num rn) {
                TypeMesure m = rn.getUnite();
                seriesMap.putIfAbsent(m, new XYChart.Series<>());
                seriesMap.get(m).setName("Cap." + c.getId() + " - " + m.name());
                seriesMap.get(m).getData().add(new XYChart.Data<>(i + 1, rn.getVal()));
            }
        }
        chart.getData().addAll(seriesMap.values());
    }

    Stage s = new Stage();
    s.setTitle("Graphique zone " + z.get_nom());
    s.setScene(new Scene(chart, 750, 450));
    s.show();
}

    @Override//redefinission de start
    public void start(Stage primaryStage) {
        this.stagePrincipal = primaryStage;

        BorderPane racine = new BorderPane();//layout de la fenetre 
        racine.setPadding(new Insets(12));

        racine.setTop(entete());

        console = new TextArea();
        console.setEditable(false);
        //console.setPrefRowCount(8);
        console.setPrefRowCount(6);
        console.setStyle("-fx-font-family: monospace;");
        redirigerConsole();//creeation de la console

        TabPane onglets = new TabPane();
        onglets.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);//unavailable de fermer les onglets
        onglets.getTabs().addAll(
                new Tab("Zones", ongletZones()),
                new Tab("Cultures", ongletCultures()),
                new Tab("Elevage", ongletElevage()),
                new Tab("Aquacole", ongletAquacole()),
                new Tab("Capteurs & Releves", ongletCapteurs()),
                new Tab("Alertes", ongletAlertes()),//on remplit les onglets
                new Tab("Tableau de bord", ongletTableauBord()));
        onglets.getSelectionModel().selectedItemProperty()
                .addListener((o, a, b) -> rafraichirTout());//listeneur changement longlet

        VBox centre = new VBox(10, onglets, new Label("Console"), console);
        VBox.setVgrow(onglets, Priority.ALWAYS);
        racine.setCenter(centre);//prendre tout lespace disponible pour les onglets et la console
        racine.setBottom(barreEtat());

        Scene scene = new Scene(racine, 1050, 720);//on cree la scene
        primaryStage.setTitle(" Gestion de la ferme");
        primaryStage.setScene(scene);
        primaryStage.show();

        rafraichirTout();
    }


    private VBox entete() {
        Label titre = new Label("Gestion de la ferme");
        titre.setFont(Font.font("System", FontWeight.BOLD, 22));
        Label sous = new Label("Exploitation agricole - zones, capteurs, alertes");
        sous.setStyle("-fx-text-fill: #555;");
        VBox b = new VBox(2, titre, sous);
        b.setPadding(new Insets(0, 0, 10, 0));
        return b;
    }//entete de la fenetre

    private HBox barreEtat() {
        barreEtat = new Label("Pret.");
        HBox b = new HBox(barreEtat);
        b.setPadding(new Insets(8, 0, 0, 0));
        return b;
    }//barre d'etat en bas  de la fenetre


    private Node ongletZones() {
        ComboBox<String> type = new ComboBox<>(FXCollections.observableArrayList(
                "Culture", "Elevage", "Aquacole"));//liste deroulante du type de zone a creer
        type.getSelectionModel().selectFirst();//selection par defaut du type de zone

        TextField nom = new TextField();      nom.setPromptText("Nom");
        TextField x = new TextField();        x.setPromptText("x");
        TextField y = new TextField();        y.setPromptText("y");
        TextField r = new TextField();        r.setPromptText("rayon");
        x.setPrefWidth(70); y.setPrefWidth(70); r.setPrefWidth(70);

        ComboBox<String> typeElevage = new ComboBox<>(FXCollections.observableArrayList(
                "VOLAIL", "RUMINANT"));
        typeElevage.getSelectionModel().selectFirst();
        TextField nbAnim = new TextField(); nbAnim.setPromptText("Nombre d'animaux");

        HBox ligneElevage = new HBox(8, new Label("Type elevage"), typeElevage);
        ligneElevage.setAlignment(Pos.CENTER_LEFT);
        HBox ligneAqua = new HBox(8, new Label("Nb animaux"), nbAnim);
        ligneAqua.setAlignment(Pos.CENTER_LEFT);
        type.setOnAction(e -> {
            boolean el = "Elevage".equals(type.getValue());
            boolean aq = "Aquacole".equals(type.getValue());
            ligneElevage.setVisible(el);  ligneElevage.setManaged(el);
            ligneAqua.setVisible(aq);     ligneAqua.setManaged(aq);
        });
        type.fireEvent(new javafx.event.ActionEvent());

        GridPane g = new GridPane();
        g.setHgap(8); g.setVgap(8);
        g.addRow(0, new Label("Type"), type);
        g.addRow(1, new Label("Nom"), nom);
        g.addRow(2, new Label("Position x / y"), new HBox(6, x, y));
        g.addRow(3, new Label("Rayon"), r);

        Button creer = new Button("Creer la zone");
        creer.setDefaultButton(true);
        creer.setOnAction(e -> {
            try {
                String n = obligatoire(nom, "nom");
                Limite_geographique lim = new Limite_geographique(
                        flottant(r, "rayon"), flottant(x, "x"), flottant(y, "y"));
                switch (type.getValue()) {
                    case "Culture":
                        new Zone_culture(n, lim, ferme); break;
                    case "Elevage":
                        type_elevage te = "RUMINANT".equals(typeElevage.getValue())
                                ? type_elevage.RUMINANT : type_elevage.VOLAIL;
                        new Zone_elevage(n, te, lim, ferme); break;
                    case "Aquacole":
                        int nb = nbAnim.getText().trim().isEmpty() ? 0
                                : Integer.parseInt(nbAnim.getText().trim());
                        new Zone_aquacole(n, nb, lim, ferme); break;
                }
                nom.clear(); x.clear(); y.clear(); r.clear(); nbAnim.clear();
                rafraichirTout();
                info("Zone \"" + n + "\" creee.");
            } catch (ChampInvalide ex) {
                erreur(ex.getMessage());
            } catch (Exception ex) {
                erreur("Erreur : " + ex.getMessage());
            }
        });

        Button activer = new Button("Activer");
        Button suspendre = new Button("Suspendre");
        activer.setOnAction(e -> statutZone(true));
        suspendre.setOnAction(e -> statutZone(false));

        Button charger = new Button("Charger depuis un fichier...");
        charger.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Choisir un fichier de zones");
            fc.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Fichiers texte", "*.txt"));
            File f = fc.showOpenDialog(stagePrincipal);
            if (f == null) return;
            int n = new Chargeur_zones(ferme).charger_depuis_fichier(f.getAbsolutePath());
            rafraichirTout();
            info(n + " zone(s) chargee(s).");
        });

        Button afficher = new Button("Afficher toutes les zones (console)");
        afficher.setOnAction(e -> new Affichage().afficher_zones(ferme));

        HBox actions = new HBox(8, activer, suspendre, charger, afficher);

        tableZones = new TableView<>();
        donneesZones = FXCollections.observableArrayList();
        tableZones.setItems(donneesZones);
        tableZones.setPlaceholder(new Label("Aucune zone."));
        tableZones.getColumns().add(colonne("ID", "id", 50));
        tableZones.getColumns().add(colonne("Nom", "nom", 170));
        tableZones.getColumns().add(colonne("Type", "type", 110));
        tableZones.getColumns().add(colonne("Statut", "statut", 100));
        tableZones.getColumns().add(colonne("Position (x, y, r)", "position", 180));

        VBox box = new VBox(10,
                titreSection("Ajouter une zone"), g, ligneElevage, ligneAqua, creer,
                new Separator(),
                titreSection("Zones existantes"), tableZones, actions);
        VBox.setVgrow(tableZones, Priority.ALWAYS);
        box.setPadding(new Insets(10));
        return box;
    }

    private void statutZone(boolean activer) {
        LigneZone s = tableZones.getSelectionModel().getSelectedItem();
        if (s == null) { erreur("Selectionnez une zone dans le tableau."); return; }
        if (activer) { ferme.activer_zone(s.getNom()); info("Zone activee."); }
        else { ferme.suspension_zone(s.getNom()); info("Zone suspendue."); }
        rafraichirTout();
    }

    // ==================================================================
    //  Onglet 2 : Cultures
    // ==================================================================

    private Node ongletCultures() {
        comboZonesCulture = new ComboBox<>();
        comboZonesCulture.setPrefWidth(260);

        TextField nomC = new TextField();   nomC.setPromptText("Nom de la culture");
        TextField datP = new TextField();   datP.setPromptText("Date plantation");
        TextField datR = new TextField();   datR.setPromptText("Date recolte");
        ComboBox<Famille_cult> fam = new ComboBox<>(
                FXCollections.observableArrayList(Famille_cult.values()));
        fam.getSelectionModel().selectFirst();
        TextField phMin = new TextField();  phMin.setPromptText("pH min");
        TextField phMax = new TextField();  phMax.setPromptText("pH max");
        TextField hMin = new TextField();   hMin.setPromptText("Humidite min");
        TextField hMax = new TextField();   hMax.setPromptText("Humidite max");

        GridPane g = new GridPane();
        g.setHgap(8); g.setVgap(8);
        g.addRow(0, new Label("Zone culture"), comboZonesCulture);
        g.addRow(1, new Label("Nom culture"), nomC);
        g.addRow(2, new Label("Plantation / Recolte"), new HBox(6, datP, datR));
        g.addRow(3, new Label("Famille"), fam);
        g.addRow(4, new Label("pH min / max"), new HBox(6, phMin, phMax));
        g.addRow(5, new Label("Humidite min / max"), new HBox(6, hMin, hMax));

        Button ajouter = new Button("Affecter la culture");
        ajouter.setOnAction(e -> {
            Zone_culture z = (Zone_culture) zoneParNom(comboZonesCulture);
            if (z == null) { erreur("Choisissez une zone culture."); return; }
            try {
                Exigences_pedologiques ep = new Exigences_pedologiques(
                        flottant(phMin, "pH min"), flottant(phMax, "pH max"),
                        flottant(hMin, "humidite min"), flottant(hMax, "humidite max"));
                z.affecter_cultures(datP.getText().trim(), datR.getText().trim(),
                        fam.getValue(), ep, obligatoire(nomC, "nom culture"));
                rafraichirTout();
                info("Culture \"" + nomC.getText().trim() + "\" ajoutee.");
                nomC.clear();
            } catch (ChampInvalide ex) {
                erreur(ex.getMessage());
            } catch (Exception ex) {
                erreur("Erreur : " + ex.getMessage());
            }
        });

        // Mise a jour du stade de croissance
        TextField nomMaj = new TextField(); nomMaj.setPromptText("Nom culture");
        ComboBox<Croissance> stade = new ComboBox<>(
                FXCollections.observableArrayList(Croissance.values()));
        stade.getSelectionModel().selectFirst();
        Button majBtn = new Button("Mettre a jour la croissance");
        majBtn.setOnAction(e -> {
            Zone_culture z = (Zone_culture) zoneParNom(comboZonesCulture);
            if (z == null) { erreur("Choisissez une zone culture."); return; }
            z.mettre_a_jour_croissance(nomMaj.getText().trim(), stade.getValue());
            info("Croissance mise a jour.");
        });

        // Enregistrement d'un rendement
        TextField nomRdt = new TextField(); nomRdt.setPromptText("Libelle rendement");
        TextField valRdt = new TextField(); valRdt.setPromptText("Valeur");
        Button rdtBtn = new Button("Enregistrer le rendement");
        rdtBtn.setOnAction(e -> {
            Zone_culture z = (Zone_culture) zoneParNom(comboZonesCulture);
            if (z == null) { erreur("Choisissez une zone culture."); return; }
            try {
                z.enregistrer_rendement_culture(
                        obligatoire(nomRdt, "libelle"), flottant(valRdt, "valeur"));
                info("Rendement enregistre.");
            } catch (ChampInvalide ex) { erreur(ex.getMessage()); }
        });

        Button afficher = new Button("Afficher les cultures (console)");
        afficher.setOnAction(e -> {
            Zone_culture z = (Zone_culture) zoneParNom(comboZonesCulture);
            if (z == null) { erreur("Choisissez une zone culture."); return; }
            z.afficher_cultures();
        });

        VBox box = new VBox(10,
                titreSection("Affecter une culture a une zone"), g, ajouter,
                new Separator(),
                titreSection("Mise a jour de la croissance"),
                new HBox(8, nomMaj, stade, majBtn),
                new Separator(),
                titreSection("Rendement de culture"),
                new HBox(8, nomRdt, valRdt, rdtBtn),
                new Separator(), afficher);
        box.setPadding(new Insets(10));
        return box;
    }

    // ==================================================================
    //  Onglet 3 : Elevage
    // ==================================================================

    private Node ongletElevage() {
        comboZonesElevage = new ComboBox<>();
        comboZonesElevage.setPrefWidth(260);

        TextField espece = new TextField(); espece.setPromptText("Espece");
        TextField age = new TextField();    age.setPromptText("Age");
        TextField poids = new TextField();  poids.setPromptText("Poids");
        ComboBox<sante> etat = new ComboBox<>(
                FXCollections.observableArrayList(sante.values()));
        etat.getSelectionModel().selectFirst();

        Button addAnim = new Button("Affecter l'animal");
        addAnim.setOnAction(e -> {
            Zone_elevage z = (Zone_elevage) zoneParNom(comboZonesElevage);
            if (z == null) { erreur("Choisissez une zone d'elevage."); return; }
            try {
                z.affecter_animal(obligatoire(espece, "espece"),
                        entier(age, "age"), entier(poids, "poids"), etat.getValue());
                rafraichirTout();
                info("Animal ajoute.");
                espece.clear(); age.clear(); poids.clear();
            } catch (ChampInvalide ex) { erreur(ex.getMessage()); }
        });

        TextField qte = new TextField();  qte.setPromptText("Quantite par repas");
        TextField alim = new TextField(); alim.setPromptText("Type d'aliment");
        Button addPA = new Button("Ajouter le programme alimentaire");
        addPA.setOnAction(e -> {
            Zone_elevage z = (Zone_elevage) zoneParNom(comboZonesElevage);
            if (z == null) { erreur("Choisissez une zone d'elevage."); return; }
            try {
                z.ajouter_programme_alimentaire(
                        Double.parseDouble(qte.getText().trim()), alim.getText().trim());
                info("Programme alimentaire ajoute.");
            } catch (NumberFormatException ex) {
                erreur("Quantite invalide.");
            }
        });

        TextField rdt = new TextField(); rdt.setPromptText("Rendement (lait / oeufs)");
        Button addRdt = new Button("Enregistrer le rendement");
        addRdt.setOnAction(e -> {
            Zone_elevage z = (Zone_elevage) zoneParNom(comboZonesElevage);
            if (z == null) { erreur("Choisissez une zone d'elevage."); return; }
            try {
                z.enregistrer_rendement(flottant(rdt, "rendement"));
                info("Rendement enregistre.");
            } catch (ChampInvalide ex) { erreur(ex.getMessage()); }
        });

        Button infoBtn = new Button("Afficher la zone (console)");
        infoBtn.setOnAction(e -> {
            Zone_elevage z = (Zone_elevage) zoneParNom(comboZonesElevage);
            if (z == null) { erreur("Choisissez une zone d'elevage."); return; }
            z.afficher_info();
        });

        VBox box = new VBox(10,
                titreSection("Zone d'elevage"), comboZonesElevage,
                new Separator(),
                titreSection("Affecter un animal"),
                new HBox(8, espece, age, poids, etat, addAnim),
                new Separator(),
                titreSection("Programme alimentaire"),
                new HBox(8, qte, alim, addPA),
                new Separator(),
                titreSection("Rendement de production"),
                new HBox(8, rdt, addRdt),
                new Separator(), infoBtn);
        box.setPadding(new Insets(10));
        return box;
    }

    // ==================================================================
    //  Onglet 4 : Aquacole
    // ==================================================================

    private Node ongletAquacole() {
        comboZonesAquacole = new ComboBox<>();
        comboZonesAquacole.setPrefWidth(260);

        TextField nb = new TextField(); nb.setPromptText("Nombre d'animaux a ajouter");
        Button addAnim = new Button("Ajouter au bassin");
        addAnim.setOnAction(e -> {
            Zone_aquacole z = (Zone_aquacole) zoneParNom(comboZonesAquacole);
            if (z == null) { erreur("Choisissez une zone aquacole."); return; }
            try {
                z.ajouter_animeaux(entier(nb, "nombre"));
                info("Animaux ajoutes au bassin.");
                nb.clear();
            } catch (ChampInvalide ex) { erreur(ex.getMessage()); }
        });

        TextField rdt = new TextField(); rdt.setPromptText("Poids de recolte");
        Button addRdt = new Button("Enregistrer le rendement");
        addRdt.setOnAction(e -> {
            Zone_aquacole z = (Zone_aquacole) zoneParNom(comboZonesAquacole);
            if (z == null) { erreur("Choisissez une zone aquacole."); return; }
            try {
                z.enregistrer_rendement(flottant(rdt, "poids"));
                info("Rendement enregistre.");
            } catch (ChampInvalide ex) { erreur(ex.getMessage()); }
        });

        Button infoBtn = new Button("Afficher la zone (console)");
        infoBtn.setOnAction(e -> {
            Zone_aquacole z = (Zone_aquacole) zoneParNom(comboZonesAquacole);
            if (z == null) { erreur("Choisissez une zone aquacole."); return; }
            z.afficher_info();
        });

        VBox box = new VBox(10,
                titreSection("Zone aquacole"), comboZonesAquacole,
                new Separator(),
                titreSection("Bassin"),
                new HBox(8, nb, addAnim),
                new Separator(),
                titreSection("Rendement"),
                new HBox(8, rdt, addRdt),
                new Separator(), infoBtn);
        box.setPadding(new Insets(10));
        return box;
    }

    // ==================================================================
    //  Onglet 5 : Capteurs & Releves
    // ==================================================================

    private Node ongletCapteurs() {
        comboZonesCapteur = new ComboBox<>();
        comboZonesCapteur.setPrefWidth(260);
        comboCapteurs = new ComboBox<>();
        comboCapteurs.setPrefWidth(260);

        // ----- Malak
        /*ComboBox<String> typeCapteur = new ComboBox<>(FXCollections.observableArrayList(
                "Biometrique", "Environnemental", "Sol", "Eau", "GPS"));
        typeCapteur.getSelectionModel().selectFirst();*/
        typeCapteur = new ComboBox<>();
        typeCapteur.setPrefWidth(160);

        Button creerCapteur = new Button("Creer et rattacher le capteur");
        creerCapteur.setOnAction(e -> {
            Zone_geographique z = zoneParNom(comboZonesCapteur);
            if (z == null) { erreur("Choisissez une zone."); return; }
            capteur c;
            switch (typeCapteur.getValue()) {
                case "Biometrique":      c = new capteur_bio(z); break;
                case "Environnemental":  c = new capteur_env(z); break;
                case "Sol":              c = new capteur_sol(z); break;
                case "Eau":              c = new capteur_eau(z); break;
                default:                 c = new capteur_gps(z); break;
            }
            z.AjouterCapteur(c);
            rafraichirCapteurs();
            info("Capteur " + typeCapteur.getValue() + " cree et rattache.");
        });

        //----- Malak
        //comboZonesCapteur.setOnAction(e -> rafraichirCapteurs());
        comboZonesCapteur.setOnAction(e -> {
            rafraichirCapteurs();
            // Mettre à jour la liste des types selon SpecifiqueCapteurs de la zone
           // Rafraîchir aussi la liste des types de capteurs
        Zone_geographique zCap = zoneParNom(comboZonesCapteur);
        if (typeCapteur != null) {
            typeCapteur.getItems().clear();
            if (zCap != null && zCap.getSpecifiqueCapteurs() != null) {
                for (Type_Capteurs tc2 : zCap.getSpecifiqueCapteurs()) {
                    switch (tc2) {
                        case BIOMETRIQUE:   typeCapteur.getItems().add("Biometrique"); break;
                        case ENVIRONMENTAL: typeCapteur.getItems().add("Environnemental"); break;
                        case SOL:           typeCapteur.getItems().add("Sol"); break;
                        case EAU:           typeCapteur.getItems().add("Eau"); break;
                        case GPS:           typeCapteur.getItems().add("GPS"); break;
                    }
                }
                if (!typeCapteur.getItems().isEmpty())
                    typeCapteur.getSelectionModel().selectFirst();
            }
        }
        });

        // --- Capteur numerique : seuils + valeur ---
        /*ComboBox<TypeMesure> mesure = new ComboBox<>(
                FXCollections.observableArrayList(TypeMesure.values()));
        mesure.getSelectionModel().selectFirst();*/
        ComboBox<TypeMesure> mesure = new ComboBox<>();
        TextField s1 = new TextField(); s1.setPromptText("Min");
        TextField s2 = new TextField(); s2.setPromptText("Min avert.");
        TextField s3 = new TextField(); s3.setPromptText("Min critique");
        TextField s4 = new TextField(); s4.setPromptText("Max");
        Button seuilsBtn = new Button("Definir les seuils");
        seuilsBtn.setOnAction(e -> {
            capteur c = capteurSelectionne();
            if (!(c instanceof capteur_num)) {
                erreur("Selectionnez un capteur numerique (non GPS)."); return;
            }
            try {
                List<Float> plage = Arrays.asList(
                        flottant(s1, "min"), flottant(s2, "min avert"),
                        flottant(s3, "min critique"), flottant(s4, "max"));
                ((capteur_num) c).setSeuils(plage, mesure.getValue());
                info("Seuils definis pour " + mesure.getValue() + ".");
            } catch (ChampInvalide ex) { erreur(ex.getMessage()); }
        });

        TextField valeur = new TextField(); valeur.setPromptText("Valeur relevee");
        Button valBtn = new Button("Ajouter le releve");
        valBtn.setOnAction(e -> {
            capteur c = capteurSelectionne();
            if (!(c instanceof capteur_num)) {
                erreur("Selectionnez un capteur numerique (non GPS)."); return;
            }
            try {
                ((capteur_num) c).setVal(
                        flottant(valeur, "valeur"), mesure.getValue(), ferme);
                rafraichirTout();
                info("Releve ajoute (alerte generee si hors seuils).");
            } catch (ChampInvalide ex) {
                erreur(ex.getMessage());
            } catch (OutsideTableException ex) {
                erreur("Valeur hors de la plage de seuils.");
            } catch (WrongUniteException ex) {
                erreur("Type de mesure non gere par ce capteur.");
            }catch(CapteurNonActifException exception){
                erreur("Le capteur choisi est non actif pour le moment, vous ne pouvez pas ajouter de valeurs");
            }
        });

        // --- Capteur GPS ---
        TextField lon = new TextField(); lon.setPromptText("Longitude (x)");
        TextField lat = new TextField(); lat.setPromptText("Latitude (y)");
        Button gpsBtn = new Button("Ajouter une position GPS");
        gpsBtn.setOnAction(e -> {
            capteur c = capteurSelectionne();
            if (!(c instanceof capteur_gps)) {
                erreur("Selectionnez un capteur GPS."); return;
            }
            try {
                ((capteur_gps) c).setCoordonnee(
                        flottant(lon, "longitude"), flottant(lat, "latitude"), ferme);
                rafraichirTout();
                info("Position GPS enregistree.");
            } catch (ChampInvalide ex) { erreur(ex.getMessage()); }
            catch(CapteurNonActifException exception){
                erreur("Le capteur choisi est non actif pour le moment, vous ne pouvez pas ajouter de valeurs");
            }
        });
        // ----MAlak
        Button chargerReleves = new Button("Charger relevés depuis fichier...");
        chargerReleves.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Fichier de relevés");
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers texte", "*.txt"));
            File f = fc.showOpenDialog(stagePrincipal);
            if (f == null) return;
            int n = new Chargeur_releves(ferme).charger_depuis_fichier(f.getAbsolutePath());
            rafraichirTout();
            info(n + " relevé(s) chargé(s).");
        });

        // --- Releves ---
        Button listeBtn = new Button("Afficher les releves (console)");
        listeBtn.setOnAction(e -> {
            capteur c = capteurSelectionne();
            if (c == null) { erreur("Selectionnez un capteur."); return; }
            console.appendText(c.DisplayListReleve() + "\n");
        });

        // --- Statut du capteur ---
        Button activerCap = new Button("Activer");
        activerCap.setOnAction(e -> {
            capteur c = capteurSelectionne();
            if (c == null) { erreur("Selectionnez un capteur."); return; }
            c.activation();
            rafraichirCapteurs();
            info("Capteur activé.");
        });

        Button suspendreCap = new Button("Suspendre");
        suspendreCap.setOnAction(e -> {
            capteur c = capteurSelectionne();
            if (c == null) { erreur("Selectionnez un capteur."); return; }
            c.suspension();
            rafraichirCapteurs();
            info("Capteur suspendu.");
        });

        Button defaillantCap = new Button("Défaillant");
        defaillantCap.setOnAction(e -> {
            capteur c = capteurSelectionne();
            if (c == null) { erreur("Selectionnez un capteur."); return; }
            c.Defaillant();
            rafraichirCapteurs();
            info("Capteur marqué défaillant.");
        });

        //--- Malak 
        /*Button graphBtn = new Button("Graphique des relevés");
        graphBtn.setOnAction(e -> {
            capteur c = capteurSelectionne();
            if (c == null) { erreur("Selectionnez un capteur."); return; }
            if (!(c instanceof capteur_num)) { erreur("Graphique disponible uniquement pour capteurs numeriques."); return; }
            afficherGraphique((capteur_num) c);
        });*/
        Button graphCapteurBtn = new Button("📈 Graphique - ce capteur");
        graphCapteurBtn.setOnAction(e -> {
            capteur c = capteurSelectionne();
            if (c == null) { erreur("Selectionnez un capteur."); return; }
            if (!(c instanceof capteur_num)) { erreur("Graphique uniquement pour capteurs numeriques."); return; }
            afficherGraphiqueCapteur((capteur_num) c);
        });

        Button graphZoneBtn = new Button("📊 Graphique - toute la zone");
        graphZoneBtn.setOnAction(e -> {
            Zone_geographique z = zoneParNom(comboZonesCapteur);
            if (z == null) { erreur("Selectionnez une zone."); return; }
            afficherGraphiqueZone(z);
        });

        DatePicker d1 = new DatePicker();
        DatePicker d2 = new DatePicker();
        Button filtreBtn = new Button("Filtrer les releves par periode");
        filtreBtn.setOnAction(e -> {
            capteur c = capteurSelectionne();
            if (c == null) { erreur("Selectionnez un capteur."); return; }
            if (d1.getValue() == null || d2.getValue() == null) {
                erreur("Choisissez les deux dates."); return;
            }
            try {
                console.appendText(
                        c.DisplayReleveFiltre(d1.getValue(), d2.getValue()) + "\n");
            } catch (BeforeDateException ex) {
                erreur("La date de fin precede la date de debut.");
            }
        });

        /*VBox box = new VBox(10,
                titreSection("Creer un capteur"),
                new HBox(8, new Label("Zone"), comboZonesCapteur,
                        new Label("Type"), typeCapteur, creerCapteur),
                new HBox(8, new Label("Capteur"), comboCapteurs),
                new Separator(),
                titreSection("Capteur numerique : seuils"),
                new HBox(8, new Label("Mesure"), mesure, s1, s2, s3, s4, seuilsBtn),
                titreSection("Capteur numerique : nouveau releve"),
                new HBox(8, valeur, valBtn),
                new Separator(),
                titreSection("Capteur GPS : position"),
                new HBox(8, lon, lat, gpsBtn),
                /** MALAk */
                /*new Separator(),
                titreSection("Chargement automatique"),
                new HBox(8, chargerReleves),
                // ---MALAK
                new Separator(),
                titreSection("Releves"),
                //----- Malak
                new HBox(8, listeBtn),
                //new HBox(8, listeBtn, graphBtn),
                new HBox(8, graphCapteurBtn, graphZoneBtn),
                new HBox(8, new Label("Du"), d1, new Label("au"), d2, filtreBtn));*/
                // Section seuils — cachée par défaut, visible seulement pour capteur_num
        VBox sectionSeuils = new VBox(8,
                titreSection("Capteur numerique : seuils"),
                new HBox(8, new Label("Mesure"), mesure, s1, s2, s3, s4, seuilsBtn),
                titreSection("Capteur numerique : nouveau releve"),
                new HBox(8, valeur, valBtn));
        sectionSeuils.setVisible(false);
        sectionSeuils.setManaged(false);

        // Section GPS — cachée par défaut, visible seulement pour capteur_gps
        VBox sectionGPS = new VBox(8,
                titreSection("Capteur GPS : position"),
                new HBox(8, lon, lat, gpsBtn));
        sectionGPS.setVisible(false);
        sectionGPS.setManaged(false);

// Listener sur comboCapteurs : met à jour les sections et la liste de mesures
comboCapteurs.setOnAction(e -> {
    capteur c = capteurSelectionne();
    boolean estNum = c instanceof capteur_num;
    boolean estGps = c instanceof capteur_gps;

    sectionSeuils.setVisible(estNum);
    sectionSeuils.setManaged(estNum);
    sectionGPS.setVisible(estGps);
    sectionGPS.setManaged(estGps);

    mesure.getItems().clear();
    if (estNum) {
        List<TypeMesure> unites = ((capteur_num) c).getUnites();
        if (unites != null) {
            mesure.getItems().addAll(unites);
        }
        if (!mesure.getItems().isEmpty())
            mesure.getSelectionModel().selectFirst();
    }
});

VBox box = new VBox(10,
        titreSection("Creer un capteur"),
        new HBox(8, new Label("Zone"), comboZonesCapteur,
                new Label("Type"), typeCapteur, creerCapteur),
        new HBox(8, new Label("Capteur"), comboCapteurs),
        new HBox(8, new Label("Statut capteur :"), activerCap, suspendreCap, defaillantCap),
        new Separator(),
        sectionSeuils,
        sectionGPS,
        new Separator(),
        titreSection("Chargement automatique"),
        new HBox(8, chargerReleves),
        new Separator(),
        titreSection("Releves"),
        new HBox(8, listeBtn),
        new HBox(8, graphCapteurBtn, graphZoneBtn),
        new HBox(8, new Label("Du"), d1, new Label("au"), d2, filtreBtn));
        //box.setPadding(new Insets(10));
        //return box;
        box.setPadding(new Insets(10));
        ScrollPane scroll = new ScrollPane(box);
        scroll.setFitToWidth(true);
        return scroll;
    }

    private capteur capteurSelectionne() {
        Zone_geographique z = zoneParNom(comboZonesCapteur);
        int i = comboCapteurs.getSelectionModel().getSelectedIndex();
        if (z == null || i < 0 || i >= z.getListCapteurs().size()) return null;
        return z.getListCapteurs().get(i);
    }

    private void rafraichirCapteurs() {
        comboCapteurs.getItems().clear();
        Zone_geographique z = zoneParNom(comboZonesCapteur);
        if (z == null) return;
        int idx = 0;
        for (capteur c : z.getListCapteurs()) {
            comboCapteurs.getItems().add(
                    idx + " - " + c.getType() + " - " + c.getStatut());
            idx++;
        }
        if (!comboCapteurs.getItems().isEmpty())
            comboCapteurs.getSelectionModel().selectFirst();
    }

    // ==================================================================
    //  Onglet 6 : Alertes
    // ==================================================================

    private ListView<Alerte> listeAlertes;

    private Node ongletAlertes() {
        listeAlertes = new ListView<>();
        listeAlertes.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Alerte a, boolean vide) {
                super.updateItem(a, vide);
                if (vide || a == null) { setText(null); return; }
                setText(a.getDate() + " | " + a.getZone().get_nom()
                        + " | " + a.getNiveau() + " | " + a.getTypeCapteurs()
                        + " | " + a.getStatut());
            }
        });

        Button toutes = new Button("Toutes");
        toutes.setOnAction(e -> { rafraichirAlertes(); console.appendText(ferme.DisplayAlertes() + "\n"); });
        Button actives = new Button("Actives (triees)");
        actives.setOnAction(e -> console.appendText(ferme.DisplayAlertesActive() + "\n"));

        comboZonesToutes = new ComboBox<>();
        Button parZone = new Button("Par zone");
        parZone.setOnAction(e -> {
            Zone_geographique z = zoneParNom(comboZonesToutes);
            if (z == null) { erreur("Choisissez une zone."); return; }
            console.appendText(ferme.DisplayAlertesZone(z) + "\n");
        });

        ComboBox<Type_Capteurs> tc = new ComboBox<>(
                FXCollections.observableArrayList(Type_Capteurs.values()));
        tc.getSelectionModel().selectFirst();
        Button parType = new Button("Par type capteur");
        parType.setOnAction(e -> console.appendText(
                ferme.DisplayAlertesCapteur(tc.getValue()) + "\n"));

        ComboBox<gravite> gv = new ComboBox<>(
                FXCollections.observableArrayList(gravite.values()));
        gv.getSelectionModel().selectFirst();
        Button parNiveau = new Button("Par gravite");
        parNiveau.setOnAction(e -> console.appendText(
                ferme.DisplayAlertesNiveau(gv.getValue()) + "\n"));

        DatePicker p1 = new DatePicker();
        DatePicker p2 = new DatePicker();
        Button parPeriode = new Button("Par periode");
        parPeriode.setOnAction(e -> {
            if (p1.getValue() == null || p2.getValue() == null) {
                erreur("Choisissez les deux dates."); return;
            }
            try {
                console.appendText(
                        ferme.DisplayAlertesPeriode(p1.getValue(), p2.getValue()) + "\n");
            } catch (BeforeDateException ex) {
                erreur("La date de fin precede la date de debut.");
            }
        });

        Button acquitter = new Button("Acquitter");
        acquitter.setOnAction(e -> {
            Alerte a = listeAlertes.getSelectionModel().getSelectedItem();
            if (a == null) { erreur("Selectionnez une alerte."); return; }
            a.Acquitter(); rafraichirAlertes(); info("Alerte acquittee.");
        });
        Button supprimer = new Button("Supprimer");
        supprimer.setOnAction(e -> {
            Alerte a = listeAlertes.getSelectionModel().getSelectedItem();
            if (a == null) { erreur("Selectionnez une alerte."); return; }
            a.Supprimer(); rafraichirAlertes(); info("Alerte supprimee.");
        });

        VBox box = new VBox(10,
                titreSection("Filtres d'alertes"),
                new HBox(8, toutes, actives),
                new HBox(8, new Label("Zone"), comboZonesToutes, parZone),
                new HBox(8, new Label("Type capteur"), tc, parType),
                new HBox(8, new Label("Gravite"), gv, parNiveau),
                new HBox(8, new Label("Du"), p1, new Label("au"), p2, parPeriode),
                new Separator(),
                titreSection("Liste des alertes"),
                listeAlertes,
                new HBox(8, acquitter, supprimer));
                listeAlertes.setPrefHeight(300);
                listeAlertes.setMinHeight(200);
        VBox.setVgrow(listeAlertes, Priority.ALWAYS);
        box.setPadding(new Insets(10));
        return box;
    }

    private void rafraichirAlertes() {
        if (listeAlertes == null) return;
        listeAlertes.getItems().setAll(ferme.get_liste_alerte());
    }

    // ==================================================================
    //  Rafraichissement & utilitaires
    // ==================================================================

    private void rafraichirTout() {
        if (donneesZones != null) {
            donneesZones.clear();
            for (Zone_geographique z : ferme.get_liste_de_zones())
                donneesZones.add(new LigneZone(z, typeDeZone(z)));
        }
        remplir(comboZonesToutes, ferme.get_liste_de_zones());
        remplirC(comboZonesCulture, ferme.get_liste_de_zones_culture());
        remplirE(comboZonesElevage, ferme.get_liste_de_zones_elevage());
        remplirA(comboZonesAquacole, ferme.get_liste_de_zones_aquacole());
        remplir(comboZonesCapteur, ferme.get_liste_de_zones());
        rafraichirAlertes();

        //---- Malak
        Zone_geographique zCap = zoneParNom(comboZonesCapteur);
    if (typeCapteur != null) {
        typeCapteur.getItems().clear();
        if (zCap != null && zCap.getSpecifiqueCapteurs() != null) {
            for (Type_Capteurs tc2 : zCap.getSpecifiqueCapteurs()) {
                switch (tc2) {
                    case BIOMETRIQUE:   typeCapteur.getItems().add("Biometrique"); break;
                    case ENVIRONMENTAL: typeCapteur.getItems().add("Environnemental"); break;
                    case SOL:           typeCapteur.getItems().add("Sol"); break;
                    case EAU:           typeCapteur.getItems().add("Eau"); break;
                    case GPS:           typeCapteur.getItems().add("GPS"); break;
                }
            }
            if (!typeCapteur.getItems().isEmpty())
                typeCapteur.getSelectionModel().selectFirst();
        }
    }
    }

    private void remplir(ComboBox<String> c, List<Zone_geographique> zs) {
        if (c == null) return;
        String sel = c.getValue();
        c.getItems().clear();
        for (Zone_geographique z : zs) c.getItems().add(z.get_nom());
        if (sel != null && c.getItems().contains(sel)) c.setValue(sel);
    }
    private void remplirC(ComboBox<String> c, List<Zone_culture> zs) {
        if (c == null) return;
        String sel = c.getValue();
        c.getItems().clear();
        for (Zone_culture z : zs) c.getItems().add(z.get_nom());
        if (sel != null && c.getItems().contains(sel)) c.setValue(sel);
    }
    private void remplirE(ComboBox<String> c, List<Zone_elevage> zs) {
        if (c == null) return;
        String sel = c.getValue();
        c.getItems().clear();
        for (Zone_elevage z : zs) c.getItems().add(z.get_nom());
        if (sel != null && c.getItems().contains(sel)) c.setValue(sel);
    }
    private void remplirA(ComboBox<String> c, List<Zone_aquacole> zs) {
        if (c == null) return;
        String sel = c.getValue();
        c.getItems().clear();
        for (Zone_aquacole z : zs) c.getItems().add(z.get_nom());
        if (sel != null && c.getItems().contains(sel)) c.setValue(sel);
    }

    private Zone_geographique zoneParNom(ComboBox<String> c) {
        if (c == null || c.getValue() == null) return null;
        return ferme.chercher_zone(c.getValue());
    }

    private String typeDeZone(Zone_geographique z) {
        if (z instanceof Zone_culture)  return "Culture";
        if (z instanceof Zone_elevage)  return "Elevage";
        if (z instanceof Zone_aquacole) return "Aquacole";
        return "Inconnu";
    }

    private TableColumn<LigneZone, String> colonne(String t, String prop, int w) {
        TableColumn<LigneZone, String> c = new TableColumn<>(t);
        c.setCellValueFactory(new PropertyValueFactory<>(prop));
        c.setPrefWidth(w);
        return c;
    }

    private Label titreSection(String t) {
        Label l = new Label(t);
        l.setFont(Font.font("System", FontWeight.BOLD, 14));
        return l;
    }

    private void redirigerConsole() {
        OutputStream os = new OutputStream() {
            @Override public void write(int b) {
                Platform.runLater(() -> console.appendText(String.valueOf((char) b)));
            }
            @Override public void write(byte[] b, int off, int len) {
                String s = new String(b, off, len);
                Platform.runLater(() -> console.appendText(s));
            }
        };
        PrintStream ps = new PrintStream(os, true);
        System.setOut(ps);
        System.setErr(ps);
    }

    private void info(String m) {
        barreEtat.setStyle("-fx-text-fill: #1a7f37;");
        barreEtat.setText(m);
    }
    private void erreur(String m) {
        barreEtat.setStyle("-fx-text-fill: #c0392b;");
        barreEtat.setText(m);
        Alert a = new Alert(Alert.AlertType.WARNING, m, ButtonType.OK);
        a.setHeaderText(null);
        a.showAndWait();
    }

    // --- Lecture / validation des champs ---
    private static class ChampInvalide extends Exception {
        ChampInvalide(String m) { super(m); }
    }
    private String obligatoire(TextField t, String nom) throws ChampInvalide {
        String v = t.getText().trim();
        if (v.isEmpty()) throw new ChampInvalide("Le champ \"" + nom + "\" est obligatoire.");
        return v;
    }
    private float flottant(TextField t, String nom) throws ChampInvalide {
        try { return Float.parseFloat(t.getText().trim()); }
        catch (NumberFormatException e) {
            throw new ChampInvalide("Le champ \"" + nom + "\" doit etre un nombre.");
        }
    }
    private int entier(TextField t, String nom) throws ChampInvalide {
        try { return Integer.parseInt(t.getText().trim()); }
        catch (NumberFormatException e) {
            throw new ChampInvalide("Le champ \"" + nom + "\" doit etre un entier.");
        }
    }

    /** Adaptateur d'affichage d'une zone dans la TableView. */
    public static class LigneZone {
        private final SimpleStringProperty id, nom, type, statut, position;
        public LigneZone(Zone_geographique z, String type) {
            this.id = new SimpleStringProperty(String.valueOf(z.get_id()));
            this.nom = new SimpleStringProperty(z.get_nom());
            this.type = new SimpleStringProperty(type);
            this.statut = new SimpleStringProperty(String.valueOf(z.get_status()));
            Limite_geographique l = z.getLimites();
            this.position = new SimpleStringProperty(l == null ? "-"
                    : l.get_x() + ", " + l.get_y() + ", " + l.get_r());
        }
        public String getId()       { return id.get(); }
        public String getNom()      { return nom.get(); }
        public String getType()     { return type.get(); }
        public String getStatut()   { return statut.get(); }
        public String getPosition() { return position.get(); }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
