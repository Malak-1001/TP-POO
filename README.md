# Gestion de la ferme (Java)

## But poursuivi

Application Java de gestion d'une ferme agricole : suivi des **zones géographiques** (culture, élevage, aquaculture), des **capteurs** qui y sont installés, des **relevés** qu'ils produisent et des **alertes** déclenchées quand une valeur sort des seuils définis.

Le projet propose deux modes d'utilisation :
- une **interface graphique JavaFX** (`InterfaceFerme`) ;
- des **programmes de test en console** (`test.java`, `App.java`).

## Version / date

Version de travail, novembre 2026.

## Auteurs

À compléter (noms des membres du projet).

---

## Fonctionnalités

### Zones
- Trois types de zones : **Culture**, **Élevage** (volailles ou ruminants) et **Aquacole**.
- Chaque zone possède un id unique, un nom, un statut (actif / suspendu) et une limite géographique circulaire (centre x, y et rayon).
- Suspendre ou activer une zone suspend ou active tous ses capteurs.
- Chargement de zones depuis un fichier texte.

### Cultures
- Ajout de cultures à une zone (nom, dates de plantation et de récolte, famille, exigences pédologiques pH / humidité).
- Mise à jour du stade de croissance : `SEMI`, `GERMINATION`, `CROISSANCE`, `MATURITE`, `RECOLTE`.
- Rapport de croissance et enregistrement des rendements.

### Élevage
- Ajout d'animaux (espèce, âge, poids, état de santé : `SAIN`, `MALADE`, `QUARANTAINE`).
- Historique du poids et des maladies de chaque animal.
- Programmes alimentaires (type d'aliment, quantité par repas), rapport sanitaire, rendement de production (œufs ou lait selon le type d'élevage).

### Aquaculture
- Bassin avec nombre d'animaux, ajout d'animaux, enregistrement des récoltes (type de poisson, poids).

### Capteurs et relevés
- Types de capteurs : `BIOMETRIQUE`, `ENVIRONMENTAL`, `SOL`, `EAU`, `GPS`.
- Chaque type de zone n'accepte que certains capteurs :

| Zone | Capteurs acceptés |
|------|-------------------|
| Culture | Environnemental, Sol |
| Élevage | GPS, Biométrique |
| Aquacole | Eau |

- Statuts d'un capteur : `ACTIF`, `SUSPENDU`, `DEFAILLANT`. Seul un capteur actif peut enregistrer un relevé.
- **Capteurs numériques** : chaque mesure (`TypeMesure` : température, humidité, pluviométrie, pH, azote, oxygène, activité…) est comparée à une plage de seuils.
- **Capteur GPS** : vérifie que la position est dans le cercle de la zone.
- Filtrage des relevés par période, graphiques des relevés (capteur ou zone entière) et tableau de bord des derniers relevés.

### Alertes
- Une alerte est créée automatiquement quand une valeur dépasse un seuil d'avertissement ou critique, ou quand un capteur GPS sort de la zone.
- Statuts : `ACTIF`, `AQUITTE`, `SUPPRIME`.
- Filtres : par zone, type de capteur, niveau de gravité, période ; panneau des alertes actives triées.

### Plage de seuils
Une `Plage_Seuils` contient 4 valeurs, dans l'ordre : **min**, **min avertissement**, **min critique**, **max**.

---

## Structure du projet

### Modèle : zones et ferme
| Fichier | Rôle |
|---------|------|
| `Ferme.java` | Classe centrale : listes des zones et des alertes, filtres d'alertes |
| `Zone_geographique.java` | Classe abstraite mère des zones (capteurs, activation / suspension) |
| `Zone_culture.java`, `Zone_elevage.java`, `Zone_aquacole.java` | Zones spécialisées |
| `Limite_geographique.java` | Limite circulaire d'une zone (x, y, rayon) |
| `status_zone.java` | Enum : `ACTIF`, `SUSPENDU` |
| `interface_activation.java` | Interface `activation()` / `suspension()` (zones et capteurs) |

### Cultures, animaux, bassin
| Fichier | Rôle |
|---------|------|
| `Culture.java`, `Exigences_pedologiques.java` | Culture et ses exigences en pH et humidité |
| `Croissance.java`, `Famille_cult.java` | Enums : stades de croissance, familles (céréale, fruit, légumes) |
| `Animeaux.java`, `sante.java` | Animal et état de santé |
| `programme_alimentaire.java` | Programme alimentaire d'une zone d'élevage |
| `type_elevage.java`, `type_production.java` | Enums : volaille / ruminant, œuf / laitier |
| `Bassin.java` | Bassin d'une zone aquacole |
| `Evenement_sanitaire.java`, `type_evenement.java` | Ébauche d'événements sanitaires (non utilisée) |

### Capteurs et relevés
| Fichier | Rôle |
|---------|------|
| `capteur.java` | Classe abstraite mère (id, statut, liste de relevés) |
| `capteur_num.java` | Classe abstraite des capteurs numériques (valeurs, seuils, génération d'alertes) |
| `capteur_bio.java`, `capteur_env.java`, `capteur_sol.java`, `capteur_eau.java` | Capteurs numériques spécialisés |
| `capteur_gps.java` | Capteur de position |
| `Releve.java`, `releve_num.java`, `releve_gps.java` | Relevés (abstrait, numérique, GPS) |
| `Plage_Seuils.java`, `TypeMesure.java` | Seuils et types de mesure avec leur unité |
| `Type_Capteurs.java`, `StatusCapteur.java`, `gravite.java` | Enums |

### Alertes
| Fichier | Rôle |
|---------|------|
| `Alerte.java` | Alerte (zone, relevé, gravité, statut, date, type de capteur) |
| `statue_alerte.java` | Enum : `ACTIF`, `AQUITTE`, `SUPPRIME` |

### Exceptions
`BeforeDateException`, `CapteurNonActifException`, `NonExistentAlerteException`, `OutsideTableException` (valeur hors plage de seuils), `WrongUniteException` (mesure non gérée par le capteur).

### Chargement de fichiers
| Fichier | Rôle |
|---------|------|
| `Chargeur_zones.java` | Crée des zones depuis un fichier texte |
| `Chargeur_releves.java` | Ajoute des relevés depuis un fichier texte |

### Interface et programmes de test
| Fichier | Rôle |
|---------|------|
| `InterfaceFerme.java` | Interface graphique JavaFX (onglets Zones, Cultures, Élevage, Aquacole, Capteurs & Relevés, Alertes, Tableau de bord) |
| `Affichage.java` | Affichage console des zones |
| `test.java` | Scénario de test complet en console |
| `App.java` | Ancien scénario de test (voir « Points d'attention ») |
| `visualisation_graphiqueTest.java`, `visualisation_graphique.java` | Ébauches (test JUnit vide, classe non utilisée) |

---

## Comment invoquer ce projet

### Prérequis
- JDK 21 ou supérieur (le code utilise `List.getLast()`, les `record`, le pattern matching `instanceof`).
- **JavaFX** pour `InterfaceFerme` (SDK JavaFX ou dépendance Maven / Gradle).
- JUnit 5 uniquement pour compiler `visualisation_graphiqueTest.java`.

### Scénario de test en console
```bash
javac test.java
java test
```
(`javac test.java` compile aussi les classes dont il dépend ; sinon utiliser `javac *.java` si JavaFX et JUnit sont disponibles.)

### Interface graphique
```bash
javac --module-path /chemin/vers/javafx/lib --add-modules javafx.controls *.java
java  --module-path /chemin/vers/javafx/lib --add-modules javafx.controls InterfaceFerme
```
Avec un IDE (VS Code, IntelliJ, BlueJ…), ajouter les bibliothèques JavaFX au projet puis lancer `InterfaceFerme`.

---

## Formats des fichiers de chargement

Les lignes vides et celles qui commencent par `#` sont ignorées. Le séparateur est `|`.

### Zones (`Chargeur_zones`)
```
# CULTURE|nom|x|y|rayon
CULTURE|Zc1|20|30|50
# ELEVAGE|nom|VOLAIL ou RUMINANT|x|y|rayon
ELEVAGE|Ze1|VOLAIL|15|20|50
# AQUACOLE|nom|nombre_animaux|x|y|rayon
AQUACOLE|Za1|50|43|19|20
```

### Relevés (`Chargeur_releves`)
Le capteur est identifié par son **id** (compteur global qui démarre à 0), il doit déjà exister dans une zone.
```
# GPS|id_capteur|longitude|latitude
GPS|4|20|30
# TYPE|id_capteur|TYPE_MESURE|valeur   (tout capteur numérique)
SOL|0|PH|35
```

---

## Instructions à l'usager

1. Créer d'abord une ou plusieurs zones (onglet **Zones** ou fichier).
2. Affecter cultures, animaux ou animaux de bassin à chaque zone.
3. Dans **Capteurs & Relevés** : choisir une zone, créer un capteur, définir ses seuils (min, min avertissement, min critique, max) puis ajouter des relevés.
4. Consulter les alertes dans l'onglet **Alertes** et le résumé dans **Tableau de bord**.
5. Les affichages détaillés sont envoyés dans la console intégrée en bas de la fenêtre.

---

## Points d'attention connus

- **`App.java` ne compile plus** : il utilise d'anciens constructeurs (`new Zone_culture("Zc1")`, `new Zone_elevage("Ze1", t1)`, `new Zone_aquacole("Za1")`, `Za1.enregistrer_rendement(30)`) qui n'existent plus. Utiliser `test.java` à la place, ou mettre `App.java` à jour.
- `Animeaux` : le second constructeur incrémente le compteur d'id deux fois.
- `capteur_eau` : le constructeur avec statut ne déclare pas le pH, contrairement au constructeur simple.
- `capteur_bio` ne peut pas recevoir de seuils pour la mesure `ACTIVITE` sans que `Plage_Seuils` soit définie manuellement.
- `capteur_gps.getCoordonnee()` lit la case `i` (prochaine case libre) au lieu de `i - 1`.
- `Plage_Seuils.setminCritiqueVal` modifie l'indice 1 au lieu de l'indice 2.
- `Evenement_sanitaire` et `visualisation_graphique` sont des ébauches non branchées.

---
---

# 🇬🇧 English version

# Farm Management (Java)

## Purpose

A Java application for managing an agricultural farm: tracking **geographic zones** (crops, livestock, aquaculture), the **sensors** installed in them, the **readings** they produce, and the **alerts** triggered when a value goes outside its defined thresholds.

The project can be used in two ways:
- a **JavaFX graphical interface** (`InterfaceFerme`);
- **console test programs** (`test.java`, `App.java`).

## Version / date

Work in progress, November 2026.

## Authors

To be filled in (project members' names).

---

## Features

### Zones
- Three zone types: **Crop** (`Zone_culture`), **Livestock** (`Zone_elevage`, poultry or ruminants) and **Aquaculture** (`Zone_aquacole`).
- Each zone has a unique id, a name, a status (active / suspended) and a circular geographic boundary (center x, y and radius).
- Suspending or activating a zone suspends or activates all of its sensors.
- Zones can be loaded from a text file.

### Crops
- Add crops to a zone (name, planting and harvest dates, family, soil requirements: pH / humidity).
- Update the growth stage: `SEMI`, `GERMINATION`, `CROISSANCE`, `MATURITE`, `RECOLTE`.
- Growth report and yield recording.

### Livestock
- Add animals (species, age, weight, health status: `SAIN`, `MALADE`, `QUARANTAINE`).
- Weight and disease history for each animal.
- Feeding programs (feed type, quantity per meal), health report, production yield (eggs or milk depending on the livestock type).

### Aquaculture
- Pond with an animal count, add animals, record harvests (fish type, weight).

### Sensors and readings
- Sensor types: `BIOMETRIQUE`, `ENVIRONMENTAL`, `SOL`, `EAU`, `GPS`.
- Each zone type only accepts certain sensors:

| Zone | Accepted sensors |
|------|------------------|
| Crop | Environmental, Soil |
| Livestock | GPS, Biometric |
| Aquaculture | Water |

- Sensor statuses: `ACTIF`, `SUSPENDU`, `DEFAILLANT`. Only an active sensor can record a reading.
- **Numeric sensors**: each measurement (`TypeMesure`: temperature, humidity, rainfall, pH, nitrogen, oxygen, activity…) is checked against a threshold range.
- **GPS sensor**: checks that the position is inside the zone's circle.
- Readings can be filtered by period, plotted (one sensor or a whole zone), and summarized in a dashboard of latest readings.

### Alerts
- An alert is created automatically when a value exceeds a warning or critical threshold, or when a GPS sensor leaves its zone.
- Statuses: `ACTIF`, `AQUITTE` (acknowledged), `SUPPRIME` (deleted).
- Filters: by zone, sensor type, severity level, period; sorted panel of active alerts.

### Threshold range
A `Plage_Seuils` holds 4 values, in this order: **min**, **warning min**, **critical min**, **max**.

---

## Project structure

### Model: zones and farm
| File | Role |
|------|------|
| `Ferme.java` | Central class: lists of zones and alerts, alert filters |
| `Zone_geographique.java` | Abstract parent of all zones (sensors, activation / suspension) |
| `Zone_culture.java`, `Zone_elevage.java`, `Zone_aquacole.java` | Specialized zones |
| `Limite_geographique.java` | Circular boundary of a zone (x, y, radius) |
| `status_zone.java` | Enum: `ACTIF`, `SUSPENDU` |
| `interface_activation.java` | `activation()` / `suspension()` interface (zones and sensors) |

### Crops, animals, pond
| File | Role |
|------|------|
| `Culture.java`, `Exigences_pedologiques.java` | Crop and its pH / humidity requirements |
| `Croissance.java`, `Famille_cult.java` | Enums: growth stages, families (cereal, fruit, vegetables) |
| `Animeaux.java`, `sante.java` | Animal and health status |
| `programme_alimentaire.java` | Feeding program of a livestock zone |
| `type_elevage.java`, `type_production.java` | Enums: poultry / ruminant, egg / dairy |
| `Bassin.java` | Pond of an aquaculture zone |
| `Evenement_sanitaire.java`, `type_evenement.java` | Draft of health events (unused) |

### Sensors and readings
| File | Role |
|------|------|
| `capteur.java` | Abstract parent (id, status, list of readings) |
| `capteur_num.java` | Abstract numeric sensor (values, thresholds, alert generation) |
| `capteur_bio.java`, `capteur_env.java`, `capteur_sol.java`, `capteur_eau.java` | Specialized numeric sensors |
| `capteur_gps.java` | Position sensor |
| `Releve.java`, `releve_num.java`, `releve_gps.java` | Readings (abstract, numeric, GPS) |
| `Plage_Seuils.java`, `TypeMesure.java` | Thresholds and measurement types with their unit |
| `Type_Capteurs.java`, `StatusCapteur.java`, `gravite.java` | Enums |

### Alerts
| File | Role |
|------|------|
| `Alerte.java` | Alert (zone, reading, severity, status, date, sensor type) |
| `statue_alerte.java` | Enum: `ACTIF`, `AQUITTE`, `SUPPRIME` |

### Exceptions
`BeforeDateException`, `CapteurNonActifException` (inactive sensor), `NonExistentAlerteException`, `OutsideTableException` (value outside the threshold range), `WrongUniteException` (measurement not supported by the sensor).

### File loading
| File | Role |
|------|------|
| `Chargeur_zones.java` | Creates zones from a text file |
| `Chargeur_releves.java` | Adds readings from a text file |

### Interface and test programs
| File | Role |
|------|------|
| `InterfaceFerme.java` | JavaFX GUI (tabs: Zones, Crops, Livestock, Aquaculture, Sensors & Readings, Alerts, Dashboard) |
| `Affichage.java` | Console display of zones |
| `test.java` | Full console test scenario |
| `App.java` | Older test scenario (see "Known issues") |
| `visualisation_graphiqueTest.java`, `visualisation_graphique.java` | Drafts (empty JUnit test, unused class) |

---

## How to run

### Requirements
- JDK 21 or later (the code uses `List.getLast()`, `record`, and pattern matching for `instanceof`).
- **JavaFX** for `InterfaceFerme` (JavaFX SDK or a Maven / Gradle dependency).
- JUnit 5 only to compile `visualisation_graphiqueTest.java`.

### Console test scenario
```bash
javac test.java
java test
```
(`javac test.java` also compiles the classes it depends on; otherwise use `javac *.java` if JavaFX and JUnit are available.)

### Graphical interface
```bash
javac --module-path /path/to/javafx/lib --add-modules javafx.controls *.java
java  --module-path /path/to/javafx/lib --add-modules javafx.controls InterfaceFerme
```
With an IDE (VS Code, IntelliJ, BlueJ…), add the JavaFX libraries to the project and run `InterfaceFerme`.

---

## Input file formats

Empty lines and lines starting with `#` are ignored. The separator is `|`.

### Zones (`Chargeur_zones`)
```
# CULTURE|name|x|y|radius
CULTURE|Zc1|20|30|50
# ELEVAGE|name|VOLAIL or RUMINANT|x|y|radius
ELEVAGE|Ze1|VOLAIL|15|20|50
# AQUACOLE|name|animal_count|x|y|radius
AQUACOLE|Za1|50|43|19|20
```

### Readings (`Chargeur_releves`)
The sensor is identified by its **id** (a global counter starting at 0); it must already exist in a zone.
```
# GPS|sensor_id|longitude|latitude
GPS|4|20|30
# TYPE|sensor_id|MEASURE_TYPE|value   (any numeric sensor)
SOL|0|PH|35
```

---

## User instructions

1. First create one or more zones (**Zones** tab or file).
2. Assign crops, animals, or pond animals to each zone.
3. In **Sensors & Readings**: choose a zone, create a sensor, set its thresholds (min, warning min, critical min, max), then add readings.
4. Check alerts in the **Alerts** tab and the summary in the **Dashboard** tab.
5. Detailed output goes to the built-in console at the bottom of the window.

---

## Known issues

- **`App.java` no longer compiles**: it uses old constructors (`new Zone_culture("Zc1")`, `new Zone_elevage("Ze1", t1)`, `new Zone_aquacole("Za1")`, `Za1.enregistrer_rendement(30)`) that no longer exist. Use `test.java` instead, or update `App.java`.
- `Animeaux`: the second constructor increments the id counter twice.
- `capteur_eau`: the constructor with a status does not declare pH, unlike the simple constructor.
- `capteur_bio` cannot receive thresholds for the `ACTIVITE` measurement unless a `Plage_Seuils` is defined manually.
- `capteur_gps.getCoordonnee()` reads slot `i` (the next free slot) instead of `i - 1`.
- `Plage_Seuils.setminCritiqueVal` modifies index 1 instead of index 2.
- `Evenement_sanitaire` and `visualisation_graphique` are drafts that are not wired in.
