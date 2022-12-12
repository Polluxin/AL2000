# Projet AL2000 - *Novembre 2022*
## Description globale de l'état du code

## Choix effectués

## Comment compiler et exécuter le projet ?
### Etape 1 : se connecter au VPN
- Lancer le VPN Cisco fourni par l'UGA
### Etape 2 (optionnelle) : peupler la BD
- Lancer les scripts : *destruction_base.sql*, *creation_base.sql* et *peupler_base.sql* sur le serveur Oracle (c.f. Session)
Attention : Session permet une connexion à la base par l'utilisateur davidge (mdp : 365214), le peuplement de la base doit donc se faire aussi sur ce schéma.
### Etape 3 : compiler (optionnel) et créer le Jar (*IntelliJ*)
#### Il est possible de recréer le jar (remplacer *AL2000.jar*) :
* Aller dans "Project Structure..." -> "Artifacts"
* Ajouter ("+") -> JAR -> "From modules with dependencies"
* Choisir la classe **Main()** et cocher **Extract to the target JAR**
* Pour le générer faire ensuite "Build" -> "Build Artifacts..."

Le .JAR est alors généré dans le dossier

## Liste des tests des parties fonctionnelles
