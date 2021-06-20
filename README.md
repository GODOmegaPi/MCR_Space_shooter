# MCR - Paterne constructeur - Space shooter

## Mise en place de l'environnement de développement
### Prérequis
* Java 11
* IntelliJ 2020.3

### Installation du projet
1) Télécharger localement le repo GIT:
```bash
git clone git@github.com:GODOmegaPi/MCR_Space_shooter.git
```
2) Ouvrir IntelliJ.
3) Aller dans `Projects > Open > Open file of project` et sélectionner `MCR_Space_shooter`.
Un message vas apparaître vous demandant si vous souhaitez faire confiance au projet Gradle sur le point d'être ouvert. Cliquez sur le bouton `Trust Project`.
4) Une fois le projet complètement chargé, ouvrez la liste des actions Gradle qui se trouve normalement en haut à droite d'IntelliJ.
5) Pour lancer le projet, sélectionnez l'option `MCR_Space_shooter > desktop > other > run` dans la fenêtre qui s'est ouverte au poit précédent.
6) Le projet vas se compiler et s'executer

## Comment jouer
### Installation
1) Téléchargez le JAR `desktop-1.0.jar` dans la dernière realease du projet.
2) Une fois télécharger, ouvrez un batch dans le dossier où se trouve le fichier précédement téléchargé et tapez la commande:
```batch
java -jar desktop-1.0.jar
```
### Comment jouer ?
#### Les touches
W -> Avancer

A -> Aller à gauche

S -> Reculer

D -> Aller à droite

SPACEBAR -> Laisser appuyé pour tirer

(Code de triche)
P I O U -> Appuyer les quatres touches en même temps pour tirer ... 4 balles à la fois !!!
#### Le jeu
##### Menu de bienvenue
Lorsque le jeu est lancé, vous verez un écran de bienvenue. Sélectionnez `Aller au garage` pour créer votre vaisseau ou `Quitter` pour quitter le jeu.

##### Menu du garage
Dans le garage, vous pouvez choisir votre fuselage, votre arme et votre bouclier en utilisant les flèches rouges et en cliquant sur `Equiper` lorsque votre choix est fait. Vous pouvez à tout moment cliquez sur `Desequiper` pour désequiper votre choix.

Vous avez un total de crédits affichés à droite que vous ne pouvez pas dépasser pour construire votre vaisseau.

Vous devez en premier temps sélectionner un fuselage avant d'ajouter une arme ou un bouclier.

Un vaisseau est valide s'il possède au moins un fuselage et une arme et qu'il n'exècede pas le nombre de crédits maximum.

En cas d'assemblage impossible de votre vaisseau, une message d'erreur vous indiquant quel est/sont le(s) problème(s) s'affichera.

Pour ce qui est du fuselage : plus il donne de PV, plus il coûte cher.

Pour ce qui est de l'arme   : plus elle tire vite, plus elle coûte cher.

Pour ce qui est du bouclier : plus il donne des PV supplémentaires, plus il coûte cher.

Une fois votre vaisseau prêt, cliquez sur `Jouer` pour lancer le jeu ou cliquez sur `Quitter` pour quitter le jeu.

##### Le jeu
Un objectif: **Survivre !**

Pour ce faire, tirez sur les astéroides tout en vous déplaçant sans vous faire toucher.

Votre score est indiqué en haut à gauche et vos PV ainsi que les PV de votre bouclier sont indiqués en haut à gauche.

##### GAMEOVER
Une fois tous vos PV à zéro, vous vous retrouverez à l'écran de Gameover. Vous pourrez voir votre score et décidez de retourner au garage et de rejouer une partie en cliquant sur `Retour au menu` ou quitter le jeu en cliquant sur `Quitter`.
