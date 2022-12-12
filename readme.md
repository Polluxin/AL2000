# Projet AL2000 - *Novembre 2022*
## Description globale de l'état du code
Lien du git : https://github.com/Polluxin/AL2000.git
### Cahier des charges
* Le cahier des charges de départ se situe dans la [SRS rédigée](res/Documents/SRS_GL_Projet_M1_Info.pdf).
* Le diagramme UML du projet se situe dans le fichier [diagramme_plantUML](res/Documents/diagramme_plantUML) (format plantUML) et [diagramme_UML](res/Documents/diagramme_UML.png).
* La spécification de la BD se trouve dans le fichier [Spec_BD.pdf].
* Toutes les fonctionnalités ne sont pas présentes, et certaines ont été modifiées.
* Une fonctionnalité est dite présente si elle est complète et a été testée.
* Certaines fonctionnalités absentes sur le produit final ont été implémentés dans le noyau fonctionnel (non testées).
### Fonctions présentes
* Location de film en QRCode et en BluRay avec une carte d'abonné ou une CB.
* Rendu d'un BluRay après une location.
* Afficher le catalogue des films disponibles selon les genres interdits par l'abonné le cas échéant.
* Inscrire un client.
* Connecter un abonné.
* Déconnecter un abonné.
* Recharger le compte d'un abonné à l'aide d'une carte bancaire.
* Ajouter une location au panier et la retirer.
* Consulter le panier.
* Signaler un problème.
* Fonctions de simulations d'insertions de carte d'abonné, de techncien, et bancaires.
### Fonctions absentes
* Voir un catalogue et pouvoir le trier (implémentée partiellement).
* Connecter un technicien pour faire des opérations de maintenance (implémentée partiellement).
* Changer le support d'une location dans le panier (implémentée partiellement).
* Voir l'historique du client et de la machine (implémentée partiellement).
* Retirer le solde d'un compte sur une CB.
* Régler les genres interdits du compte.
* Faire payer les clients (classe Police, implémentée partiellement).
* Manque de feedback sur les interactions.
### Fonctions et contraintes ajoutées
* Un client ne peut avoir pas plus de 5 locations à la fois (géré par Trigger en BD).
## Comment compiler et exécuter le projet ?
### Etape 1 : se connecter au VPN
- Lancer le VPN Cisco fourni par l'UGA
### Etape 2 (optionnelle) : peupler la BD
- Lancer les scripts : *destruction_base.sql*, *creation_base.sql* et *peupler_base.sql* sur le serveur Oracle (c.f. Session)
Attention : Session permet une connexion à la base par l'utilisateur davidge (mdp : 365214), le peuplement de la base doit donc se faire aussi sur ce schéma.
### Etape 3 : compiler et créer le Jar (*IntelliJ*, optionnel)
#### Il est possible de recréer le jar (remplacer *AL2000.jar*) :
* Aller dans "Project Structure..." -> "Artifacts"
* Ajouter ("+") -> JAR -> "From modules with dependencies"
* Choisir la classe **Main()** et cocher **Extract to the target JAR**
* Pour le générer, faire ensuite "Build" -> "Build Artifacts..."
Le .JAR est alors généré dans le dossier du projet
### Etape 4 : exécuter le programme
* Avoir une version de java supérieure à 17
* Lancer la commande *java -jar AL2000.jar* dans le répertoire contenant le fichier.

## Liste des tests des parties fonctionnelles
* Les tests effectués se situent dans le dossier src/Tests.
