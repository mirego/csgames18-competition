# CS Games 2018 - Compétition mobile

Bienvenue à la **compétition mobile des CS Games 2018**!
[(English below ↓)](#cs-games-2018---mobile-competition)

## Introduction

L'arrivée des pingouins de l'espace a fait tout un émoi dans la communauté locale. Inhabitués aux présences vivantes extra-terrestres, les lieux de cultes ont investi toute leur énergie pour offrir de l'aide à ces nouveaux arrivants. Des efforts de masse ont été déployés pour retrouver les pièces du vaisseau éparpillées partout à travers le pays. Une plateforme de *crowdsourcing* a été créée pour documenter les pièces potentiellement identifiées par les tribus. La banque de données résultante a été partagée publiquement à l'adresse suivante:

<p align="center">
  <strong><a>https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json</a></strong>
</p>

Heureux de cette assistance impromptue, les pingouins ont voulu bénéficier de la technologie disponible à leur portée pour utiliser cette banque de données au maximum. Ils ont commencé à développer une application mobile, appelée **Map Ping**, qui leur permettra de consulter cette liste de pièces et de faciliter leur rappatriement. Malheureusement, leurs jours sont comptés et la technologie étant compliquée pour eux, ils ont besoin d'aide pour compléter leur application et se tournent donc vers vous, les fidèles.

<table>
<thead><tr><th colspan="4">Map Ping</th></tr></thead>
<tbody><tr>
<td><a href="https://user-images.githubusercontent.com/4378424/37754070-5d9eff26-2d76-11e8-9ae2-997a32fae982.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/37754070-5d9eff26-2d76-11e8-9ae2-997a32fae982.png" alt="SPLASH"></a></td>
<td><a href="https://user-images.githubusercontent.com/4378424/37754069-5d8ffd46-2d76-11e8-9e1d-0a48df719e89.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/37754069-5d8ffd46-2d76-11e8-9e1d-0a48df719e89.png" alt="LIST"></a></td>
<td><a href="https://user-images.githubusercontent.com/4378424/37754068-5d822162-2d76-11e8-8b2c-2850d64b70b1.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/37754068-5d822162-2d76-11e8-8b2c-2850d64b70b1.png" alt="MAP"></a></td>
<td><a href="https://user-images.githubusercontent.com/4378424/37754067-5d70fe82-2d76-11e8-9c8c-22609a3fd3b8.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/37754067-5d70fe82-2d76-11e8-9c8c-22609a3fd3b8.png" alt="AR"></a></td>
</tr>
<tr>
<td><em>Écran d'acceuil</em></td>
<td><em>Liste de pièces</em></td>
<td><em>Carte de pièces</em></td>
<td><em>Réalité augmentée</em></td>
</tr>
</tbody>
</table>


## Pour débuter

Vous trouverez le code de base du projet **Map Ping** dans les sous-dossiers [`ios/`](https://github.com/mirego/csgames18-competition/tree/master/ios) et [`android/`](https://github.com/mirego/csgames18-competition/tree/master/android) de ce répertoire Github:

<table width="100%">
  <thead>
    <tr>
      <th colspan="2">Applications mobiles</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/mirego/csgames18-competition/tree/master/ios" target="_blank"><br><img src="https://cloud.githubusercontent.com/assets/4378424/13625721/90d6d7de-e588-11e5-83d9-b16f14b6cfaa.png" height="100"><br><br>Application iOS</a></td>
      <td align="center"><a href="https://github.com/mirego/csgames18-competition/tree/master/android" target="_blank"><br><img src="https://cloud.githubusercontent.com/assets/4378424/13625718/90ca7e30-e588-11e5-9cd1-7fcc06d4a62a.png" height="100"><br><br> Application Android</a></td>
    </tr>
  </tbody>
</table>

Toutes les instructions pour démarrer les applications de base devraient être couvertes au bout des liens respectifs. Si vous avez besoin d'assistance, n'hésitez pas à nous en faire part.


## Le défi

L'application **Map Ping** prévoit afficher les pièces identifiées et aider les pingouins à les retrouver sur la route. Pour l'instant, la [banque de données](https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json) n'est pas connectée et l'affichage est entièrement statique.


### Connexion des données

Votre mission, pour commencer, sera d'abord de connecter les données pour les afficher dans l'application. Chaque pièce retrouvée a été détaillée de plusieurs informations:

- `name`: Nom de la pièce
- `component`: Composant du vaisseau associé
- `notes`: Notes d'identification (comment retrouver la pièce)
- `type`: Type de pièce (30 types existants)
- `lat`: Latitude (coordonnée GPS)
- `lon`: Longitude (coordonnée GPS)
- `address`: Adresse civile à proximité

Certaines composantes n'ont pas de coordonnées GPS et d'autres n'ont pas d'adresse civile. Il sera de votre ressort de retrouver l'un à partir de l'autre si nécessaire. Vous pouvez vous aider d'un API de géocodage.

### Modes d'affichage

L'application comporte 3 modes d'affichage, qui pour l'instant ne font que démontrer le visuel à l'aide d'une pièce qui n'existe pas réellement. Ces différents modes peuvent être complétés en implémentant les fonctionnalités suivantes, par exemple:

#### <img src="https://user-images.githubusercontent.com/4378424/37808993-ac71646a-2e23-11e8-9b8a-eb467fc6cc67.png" height="13" width="17"> Liste de pièces
* Afficher chacune des pièces dans une liste défilable à cellules réutilisables
* Afficher le sommaire des pièces à partir de la banque de données
* Afficher l'icône de chaque type de pièce dans chacune des cellules
* Afficher la distance relative entre la pièce et l'emplacement actuel du téléphone
* Ordonner les pièces par proximité (du plus près au plus éloigné)
* Filter les pièces par type ou par composant du vaisseau

#### <img src="https://user-images.githubusercontent.com/4378424/37808994-ac7c2cd8-2e23-11e8-8b7f-d499210cf74e.png" height="16" width="20"> Carte de pièces
* Afficher chacune des pièces sur des épingles de la carte (📍)
* Afficher le nom et le composant de la pièce suite à l'appui de l'épingle
* Afficher l'icône de chaque type de pièce dans les épingles
* Filter les pièces par type ou par composant du vaisseau
* Gérer le regroupement (clustering) à la réduction du niveau de zoom
* Positionner la carte autour de la position actuelle (et afficher celle-ci)
* Donner les directions entre la position actuelle et une pièce choisie

#### <img src="https://user-images.githubusercontent.com/4378424/37808992-ac65adfa-2e23-11e8-8350-65befbe98843.png" height="16" width="20"> Réalité augmentée
* Afficher les pièces dans l'espace tridimensionnel géolocalisées
* Afficher la distance entre la pièce dans l'espace et l'emplacement actuel

Ceci n'est qu'une liste sommaire de possibilités, libre à vous de laisser aller votre imagination pour rendre utile un ou plusieurs de ces modes d'affichage. Les maquettes dans l'introduction ci-haut peuvent vous servir d'inspiration.

### Fonctions supplémentaires

Une fois les pièces affichées, beaucoup de fonctions peuvent être ajoutées à l'application pour aider les pingouins à retrouver leurs pièces. Quelques exemples:

- **Détails des pièces**: La vue par liste affiche seulement un résumé de chaque pièce et la carte ne fait que les positionner. Un nouvel écran "Détail d'une pièce" permettrait de consulter, entre autres, ses notes d'identification et son adresse physique.

- **Ramassage de pièce**: Vu la quantité de pièces à chercher, il serait pratique de pouvoir marquer les pièces lorsqu'elles ont été ramassées, pour pouvoir savoir lesquelles sont rescapées et lesquelles demeurent perdues.

- **Notes de ramassage**: Lorsque les pingouins récupéreront la pièce, il pourrait être pertinent pour eux aussi d'ajouter des informations. Photo de la trouvaille, notes de cueillette, etc. - un formulaire pourrait leur être présenté pour entrer ces détails.

- **Persistence**: Pour toutes les données entrées, il est intéressant de les ajouter localement dans l'application, mais cela n'en est rien si les données disparaissent suite à la fermeture de l'application. Implanter une sorte de stockage local permetterait d'assurer la persistance.

Les fonctionnalités potentielles sont infinies, votre créativité est sollicitée pour rendre **Map Ping** la plus fonctionnellement complète pour nos amis pingouins en détresse.

## Évaluation

Évidemment, on ne vous demande pas de connecter la source, de faire fonctionner **tous** les modes d'affichages et d'ajouter **toutes** les fonctionnalités listées ci-haut. En 3 heures, on vous demande de faire le maximum possible et d'utiliser de votre créativité – le but ultime est de nous impressionner.

### Critères

Nous évaluerons votre travail en fonction de **_comment vous l'avez fait_**, et non de la **_quantité d'ajouts effectués_**. Il est mieux de faire une seule chose très bien que de faire plusieurs choses incomplètes.

La grille suivante sera transmise avec nos évaluations:

<table>
  <thead>
    <tr>
      <th>Critère</th>
      <th>Points</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <strong>Solution</strong>
        <ul>
          <li>Résolution générale du problème</li>          <li>Originalité de la solution</li>          <li>Qualité de l'intégration (apparence et utilisation)</li>
          <li>Respect du thème</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 50</strong></td>
    </tr>
    <tr>
      <td>
        <strong>Implémentation</strong>
        <ul>
          <li>Qualité générale du code</li>          <li>Bonne utilisation des patterns de programmation</li>          <li>Bonne utilisation des librairies et ressources externes</li>
          <li>Respect des standards de la plateforme</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 30</strong></td>
    </tr>
    <tr>
      <td>
        <strong>Qualité</strong>
        <ul>
          <li>Respect de la structure du projet</li>          <li>Respect de la syntaxe du langage</li>          <li>Qualité de l'intégration (apparence et utilisation)</li>
          <li>Respect du thème</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 10</strong></td>
    </tr>
    <tr>
      <td>
        <strong>Remise</strong>
        <ul>
          <li>Pull Request en bonne et du forme</li>
          <li>Description fournie et bien documentée</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 10</strong></td>
    </tr>
  </tbody>
  <tfoot>
    <tr>
      <td><strong>Total</strong></td>
      <td align="right"><strong>/ 100</strong></td>
    </tr>
  </tfoot>
</table>

### Remise

<table><thead><tr><th align="center">
⚠️ Prévoyez au moins 10 minutes à la fin pour préparer votre soumission ⚠️ 
</th></tr></thead></table>

La correction sera effectuée à l'aide de **Pull Requests** sur le répertoire Github. Si vous n'êtes pas familiers avec ce concept, référez-vous à ces articles: 
- [Fork a repo](https://help.github.com/articles/fork-a-repo/)
- [Creating a pull request from a fork](https://help.github.com/articles/creating-a-pull-request-from-a-fork/).




---

# CS Games 2018 - Mobile Competition

Welcome to the **CS Games 2017 Mobile Competition**!
[(French above ↑)](#cs-games-2018---competition-mobile)

## License

This competition is © 2018 [Mirego](http://www.mirego.com) and may be freely
distributed under the [New BSD license](http://opensource.org/licenses/BSD-3-Clause).
See the [`LICENSE.md`](https://github.com/mirego/csgames18-competition/blob/master/LICENSE.md) file.

## Acknowledgements

Component icons were designed by [Eucalyp](https://creativemarket.com/eucalyp) from [Flaticon.com](https://www.flaticon.com/). Thanks 👌

## About Mirego

[Mirego](http://mirego.com) is a team of passionate people who believe that work is a place where you can innovate and have fun. We're a team of [talented people](http://life.mirego.com) who imagine and build beautiful Web and mobile applications. We come together to share ideas and [change the world](http://mirego.org).

We also [love open-source software](http://open.mirego.com) and we try to give back to the community as much as we can.
