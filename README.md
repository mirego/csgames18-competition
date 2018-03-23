# CS Games 2018 - Compétition mobile

Bienvenue à la **compétition mobile des CS Games 2018**!

[(English below ↓)](#cs-games-2018---mobile-competition)

## Introduction

L'arrivée des pingouins de l'espace a fait tout un émoi dans la communauté locale. Inhabitués aux présences vivantes extra-terrestres, les lieux de cultes ont investi toute leur énergie pour offrir de l'aide à ces nouveaux arrivants. Des efforts de masse ont été déployés pour retrouver les pièces du vaisseau éparpillées partout à travers le pays. Une plateforme de *crowdsourcing* a été créée pour documenter les pièces potentiellement identifiées par les tribus. La banque de données résultante a été partagée publiquement à l'adresse suivante:

<p align="center">
  <strong><a>https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json</a></strong>
</p>

Heureux de cette assistance impromptue, les pingouins ont voulu bénéficier de la technologie disponible à leur portée pour utiliser cette banque de données au maximum. Ils ont commencé à développer une application mobile, appelée **Map Ping**, qui leur permettra de consulter cette liste de pièces et de faciliter leur rapatriement. Hélas, leurs jours sont comptés et la technologie étant compliquée pour eux, ils ont besoin d'aide pour compléter leur application et se tournent donc vers vous, les fidèles.

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
      <td align="center"><a href="https://github.com/mirego/csgames18-competition/tree/master/android" target="_blank"><br><img src="https://cloud.githubusercontent.com/assets/4378424/13625718/90ca7e30-e588-11e5-9cd1-7fcc06d4a62a.png" height="100"><br><br>Application Android</a></td>
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

Certaines composantes n'ont pas de coordonnées GPS et d'autres n'ont pas d'adresse civile. Si vous souhaitez les afficher aussi, il sera de votre ressort de retrouver l'un à partir de l'autre si nécessaire. Vous pouvez vous aider d'un API de géocodage.

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

Une fois les pièces affichées, beaucoup de fonctions peuvent être ajoutées à l'application pour aider les pingouins à retrouver les pièces de leur vaisseau. Quelques exemples:

- **Détails des pièces**: La vue par liste affiche seulement un résumé de chaque pièce et la carte ne fait que les positionner. Un nouvel écran "Détail d'une pièce" permettrait de consulter, entre autres, ses notes d'identification et son adresse civile.

- **Ramassage de pièce**: Vu la quantité de pièces à chercher, il serait pratique de pouvoir marquer les pièces lorsqu'elles ont été ramassées, pour pouvoir savoir lesquelles sont rescapées et lesquelles demeurent perdues.

- **Notes de ramassage**: Lorsque les pingouins récupéreront la pièce, il pourrait être pertinent pour eux aussi d'ajouter des informations. Photo de la trouvaille, notes de cueillette, etc. - un formulaire pourrait leur être présenté pour entrer ces détails.

- **Persistence**: Pour toutes les données entrées, il est intéressant de les ajouter localement dans l'application, mais cela n'en est rien si les données disparaissent suite à la fermeture de l'application. Implanter une sorte de stockage local permetterait d'assurer la persistance.

Les fonctionnalités potentielles sont infinies, votre créativité est sollicitée pour rendre **Map Ping** la plus fonctionnellement complète pour nos amis pingouins en détresse.

## Évaluation

Évidemment, on ne vous demande pas de connecter la source, de faire fonctionner **tous** les modes d'affichages et d'ajouter **toutes** les fonctionnalités listées ci-haut. En 3 heures, on vous demande de faire le maximum possible et d'utiliser de votre créativité – le but ultime est de nous impressionner.

### Critères

Nous évaluerons votre travail en fonction de **_comment vous l'avez fait_**, et non de la **_quantité d'ajouts effectués_**. Il est mieux de faire une seule chose très bien que de faire plusieurs choses incomplètes.

La grille suivante sera utilisée pour la correction:

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
        <p><strong>Solution</strong></p>
        <ul>
          <li>Résolution générale du problème</li>          <li>Originalité de la solution</li>          <li>Qualité de l'intégration (apparence et utilisation)</li>
          <li>Respect du thème</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 35</strong></td>
    </tr>
    <tr>
      <td>
        <p><strong>Implémentation</strong></p>
        <ul>
          <li>Qualité générale du code</li>          <li>Bonne utilisation des patterns de programmation</li>          <li>Bonne utilisation des librairies et ressources externes</li>
          <li>Respect des standards de la plateforme</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 30</strong></td>
    </tr>
    <tr>
      <td>
        <p><strong>Qualité</strong></p>
        <ul>
          <li>Respect de la structure du projet</li>          <li>Respect de la syntaxe du langage</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 10</strong></td>
    </tr>
    <tr>
      <td>
        <p><strong>Remise</strong></p>
        <ul>
          <li>Pull Request en bonne et du forme</li>
          <li>Description fournie et bien documentée</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 25</strong></td>
    </tr>
  </tbody>
  <tfoot>
    <tr>
      <td><strong>Total</strong></td>
      <td align="right"><strong>/ 100</strong></td>
    </tr>
  </tfoot>
</table>

Autres points importants:

- Un projet qui ne **builde pas** se verra accorder une note de **0 / 100** (en suite d'un effort minimal). Assurez-vous de bien détailler votre soumission si des étapes de build sont nécessaires.

- Le code doit avoir été construit à partir des projets de base et il doit être facile d'identifier vos changements via un diff. Un répertoire restructuré qui demande de repasser vos fichiers un par un pour retrouver vos changements donnera **0 / 25** dans la partie "Remise".

- L'heure de soumission de votre code ne doit pas dépasser la fin de la compétition. Une archive des branches sera effectuée en sortant des locaux – inutile de tenter d'amender vos commits dans les heures qui suivent. Une tricherie identifiée recevra aussi **0 / 100**.

### Remise

<table><thead><tr><th align="center">
⚠️ Prévoyez au moins 10 minutes avant la fin de l'épreuve pour préparer votre soumission ⚠️ 
</th></tr></thead></table>

La correction sera effectuée à l'aide de **Pull Requests** sur le répertoire Github.

> Si vous n'êtes pas familiers avec ce concept, référez-vous à ces articles: 
> - [Fork a repo](https://help.github.com/articles/fork-a-repo/)
> - [Creating a pull request from a fork](https://help.github.com/articles/creating-a-pull-request-from-a-fork/).

Votre **Pull Request** doit respecter la structure suivante:

- **Titre**: Code de votre équipe (exemple: `sherbrooke1`)
- **Description**: Utiliser le template Markdown ci-bas en remplaçant les textes entre `{}`

```markdown
## Équipe

**Nom de l'équipe:** {DaStreez}
**Code de l'équipe:** {mirego1}
**Université:** {Université de Griffintown}

**Auteurs:**
- {Hugo Lefrançois}
- {Mathieu Larue}

## Solution

**Plateforme:** {iOS | Android}

**Fonctionnalités:**
1. {Mode carte: connexion de la datasource et affichage de toutes les pins}
2. {Ramassage de pièce: possibilité de marquer une pièce ramassée}
3. {Siri: demander à Siri la pièce la plus proche de ma position actuelle}
4. {etc.}

**Étapes de build:**
- {On a ajouté la librairie GooglePlaces au Podfile, il faut rouler `pod install` avant de builder}
- {On a activé la capability "Siri", connecter un compte Apple Developer pour tester}

**Ce qui a bien été ou mal été:**
- {Le projet de base était vraiment bien fait, ça a aidé pour commencer}
- {Le clustering on a eu pas mal de misère, les librairies qu'on a essayé marchaient mal}

**Fiertés et déceptions:**
- {On est assez fier d'avoir pu faire marcher Siri, c'était hors de nos attentes et ça fonctionne aux toasts}
- {On est déçus de ne pas avoir pu attaquer le mode AR, ça aurait été sick}

```

> **NOTE:** Si vous n'avez pas de compte Github, levez la main au début de l'épreuve, nous pourrons vous fournir un compte générique.

## Licence

Cette compétition est © 2018 [Mirego](http://www.mirego.com) et peut être librement distribuée sous la [license BSD](http://opensource.org/licenses/BSD-3-Clause).
Voir le fichier [`LICENSE.md`](https://github.com/mirego/csgames18-competition/blob/master/LICENSE.md).

## Remerciements

Les icônes de pièces ont été conçues par [Eucalyp](https://creativemarket.com/eucalyp) de [Flaticon.com](https://www.flaticon.com/). Merci 👌

## À propos de Mirego

[Mirego](http://mirego.com) est une équipe de gens passionnés qui croit que le travail est un lieu agréable propice à l’innovation. Nous sommes une équipe de [personnes talentueuses](http://life.mirego.com) qui construisent des applications Web et Mobile. Nous nous réunissons pour partager nos idées et [changer le monde](http://mirego.org).

Nous aimons aussi les [logiciels open-source](http://open.mirego.com) et tentons de redonner le plus possible dans la communauté.


---

# CS Games 2018 - Mobile Competition

Welcome to the **CS Games 2017 Mobile Competition**!

[(French above ↑)](#cs-games-2018---compétition-mobile)

## Introduction

The space pinguins arrival on Earth has caused a great stir in the local community. Unused to live extraterrestial presences, the places of worship invested all their energy to help these newcomers. Mass efforts were deployed to find the vessel parts spreaded all over the country. A *crowdsourcing* platform was erected to document missing parts, as potentially identified by the tribes. The resulting data source was shared publicly here:

<p align="center">
  <strong><a>https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json</a></strong>
</p>

Satisfied by this unexpected assistance, the pinguins wanted to make the most out of the technology available to use this datasource intelligently. They started to develop a mobile application, called **Map Ping**, which allows them to see this list of parts and facilitate their repatriation. Alas, their days are numbered and the technology being complex, the pinguins need help to finish their application and they're reaching to you, the participants.

<table>
<thead><tr><th colspan="4">Map Ping</th></tr></thead>
<tbody><tr>
<td><a href="https://user-images.githubusercontent.com/4378424/37754070-5d9eff26-2d76-11e8-9ae2-997a32fae982.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/37754070-5d9eff26-2d76-11e8-9ae2-997a32fae982.png" alt="SPLASH"></a></td>
<td><a href="https://user-images.githubusercontent.com/4378424/37754069-5d8ffd46-2d76-11e8-9e1d-0a48df719e89.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/37754069-5d8ffd46-2d76-11e8-9e1d-0a48df719e89.png" alt="LIST"></a></td>
<td><a href="https://user-images.githubusercontent.com/4378424/37754068-5d822162-2d76-11e8-8b2c-2850d64b70b1.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/37754068-5d822162-2d76-11e8-8b2c-2850d64b70b1.png" alt="MAP"></a></td>
<td><a href="https://user-images.githubusercontent.com/4378424/37754067-5d70fe82-2d76-11e8-9c8c-22609a3fd3b8.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/37754067-5d70fe82-2d76-11e8-9c8c-22609a3fd3b8.png" alt="AR"></a></td>
</tr>
<tr>
<td><em>Splash Screen</em></td>
<td><em>Part List</em></td>
<td><em>Part Map</em></td>
<td><em>Augmented Reality</em></td>
</tr>
</tbody>
</table>


## To Begin

You will find the base code of **Map Ping** in the subfolders [`ios/`](https://github.com/mirego/csgames18-competition/tree/master/ios) and [`android/`](https://github.com/mirego/csgames18-competition/tree/master/android) of this Github repository:

<table width="100%">
  <thead>
    <tr>
      <th colspan="2">Applications mobiles</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/mirego/csgames18-competition/tree/master/ios" target="_blank"><br><img src="https://cloud.githubusercontent.com/assets/4378424/13625721/90d6d7de-e588-11e5-83d9-b16f14b6cfaa.png" height="100"><br><br>iOS Application </a></td>
      <td align="center"><a href="https://github.com/mirego/csgames18-competition/tree/master/android" target="_blank"><br><img src="https://cloud.githubusercontent.com/assets/4378424/13625718/90ca7e30-e588-11e5-9cd1-7fcc06d4a62a.png" height="100"><br><br>Android Application</a></td>
    </tr>
  </tbody>
</table>

All the instructions to build these base projects should be covered in the above links. If you need any assistance, please reach out to us.

## The Challenge

**Map Ping** plans to display the identified vessel parts and help the pinguins to find them in the wild. Currently, the [data source](https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json) is not connected and all screens are purely static.

### Data Connection

You first mission is to connect the data to display the parts in the app. Every identified part was detailed with many info:

- `name`: Part name
- `component`: Vessel component associated with the part
- `notes`: Identification notes (how to find the part)
- `type`: Part type (30 existing types)
- `lat`: Latitude (GPS coordinate)
- `lon`: Longitude (GPS coordinate)
- `address`: Nearby civil address

Some components don't have GPS coordinates while some others don't have a civil address. If you want to display them, you will need to figure out one from the other. This can be done using a geocoding API.

### Display Modes

The app has 3 display modes, which only shows the expected appearance using a fake vessel part. These modes can be completed by implementing the following features, for example:

#### <img src="https://user-images.githubusercontent.com/4378424/37808993-ac71646a-2e23-11e8-9b8a-eb467fc6cc67.png" height="13" width="17"> Part List
* Afficher chacune des pièces dans une liste défilable à cellules réutilisables
* Afficher le sommaire des pièces à partir de la banque de données
* Afficher l'icône de chaque type de pièce dans chacune des cellules
* Afficher la distance relative entre la pièce et l'emplacement actuel du téléphone
* Ordonner les pièces par proximité (du plus près au plus éloigné)
* Filter les pièces par type ou par composant du vaisseau

#### <img src="https://user-images.githubusercontent.com/4378424/37808994-ac7c2cd8-2e23-11e8-8b7f-d499210cf74e.png" height="16" width="20"> Part Map
* Afficher chacune des pièces sur des épingles de la carte (📍)
* Afficher le nom et le composant de la pièce suite à l'appui de l'épingle
* Afficher l'icône de chaque type de pièce dans les épingles
* Filter les pièces par type ou par composant du vaisseau
* Gérer le regroupement (clustering) à la réduction du niveau de zoom
* Positionner la carte autour de la position actuelle (et afficher celle-ci)
* Donner les directions entre la position actuelle et une pièce choisie

#### <img src="https://user-images.githubusercontent.com/4378424/37808992-ac65adfa-2e23-11e8-8350-65befbe98843.png" height="16" width="20"> Augmented Reality
* Afficher les pièces dans l'espace tridimensionnel géolocalisées
* Afficher la distance entre la pièce dans l'espace et l'emplacement actuel

This list is only a summary of possibilities, you are free to add anything to render one or more of these display modes more useful. The mockups in the introduction can serve as inspiration.

### Additionnal Features

One the parts shown, many features can be added to the app to help pinguins find back their vessel parts. For example:

- **Part detail**: The List View only shows a summary of each part, and the map only positions them. A new "Part Detail" screen could show more specific information, like the identification notes and the civil address.

- **Part pickup**: While there are many parts to pick up, it could be useful to mark them as "picked up" to easily see those that were found and those that are left.

- **Pickup notes**: When parts are picked up, it could also be useful to add information from the situation. Part picture, pickup notes, etc - a form could be displayed to enter these details.

- **Persistence**: While entering data in the app has its value, it's rendered useless if everything is forgotten once the app is closed. Implementing a local storage would insure persistence.

The potential features are infinite, we sollicitate your creativity to make the most out of **Map Ping** and give a big hand to our pinguin friends in distress.

## Evaluation

Évidemment, on ne vous demande pas de connecter la source, de faire fonctionner **tous** les modes d'affichages et d'ajouter **toutes** les fonctionnalités listées ci-haut. En 3 heures, on vous demande de faire le maximum possible et d'utiliser de votre créativité – le but ultime est de nous impressionner.

### Criteria

Nous évaluerons votre travail en fonction de **_comment vous l'avez fait_**, et non de la **_quantité d'ajouts effectués_**. Il est mieux de faire une seule chose très bien que de faire plusieurs choses incomplètes.

La grille suivante sera utilisée pour la correction:

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
        <p><strong>Solution</strong></p>
        <ul>
          <li>Résolution générale du problème</li>          <li>Originalité de la solution</li>          <li>Qualité de l'intégration (apparence et utilisation)</li>
          <li>Respect du thème</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 35</strong></td>
    </tr>
    <tr>
      <td>
        <p><strong>Implémentation</strong></p>
        <ul>
          <li>Qualité générale du code</li>          <li>Bonne utilisation des patterns de programmation</li>          <li>Bonne utilisation des librairies et ressources externes</li>
          <li>Respect des standards de la plateforme</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 30</strong></td>
    </tr>
    <tr>
      <td>
        <p><strong>Qualité</strong></p>
        <ul>
          <li>Respect de la structure du projet</li>          <li>Respect de la syntaxe du langage</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 10</strong></td>
    </tr>
    <tr>
      <td>
        <p><strong>Remise</strong></p>
        <ul>
          <li>Pull Request en bonne et du forme</li>
          <li>Description fournie et bien documentée</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 25</strong></td>
    </tr>
  </tbody>
  <tfoot>
    <tr>
      <td><strong>Total</strong></td>
      <td align="right"><strong>/ 100</strong></td>
    </tr>
  </tfoot>
</table>

Autres points importants:

- Un projet qui ne **builde pas** se verra accorder une note de **0 / 100** (en suite d'un effort minimal). Assurez-vous de bien détailler votre soumission si des étapes de build sont nécessaires.

- Le code doit avoir été construit à partir des projets de base et il doit être facile d'identifier vos changements via un diff. Un répertoire restructuré qui demande de repasser vos fichiers un par un pour retrouver vos changements donnera **0 / 25** dans la partie "Remise".

- L'heure de soumission de votre code ne doit pas dépasser la fin de la compétition. Une archive des branches sera effectuée en sortant des locaux – inutile de tenter d'amender vos commits dans les heures qui suivent. Une tricherie identifiée recevra aussi **0 / 100**.

### Remise

<table><thead><tr><th align="center">
⚠️ Please keep 10 minutes before the end of the competition to prepare the submission ⚠️ 
</th></tr></thead></table>

The submission will be done by adding a **Pull Request** to this Github repository.

> If you are not familiar with the concept, please refer to these articles:
> - [Fork a repo](https://help.github.com/articles/fork-a-repo/)
> - [Creating a pull request from a fork](https://help.github.com/articles/creating-a-pull-request-from-a-fork/).

Your **Pull Request** must respect the following pattern:

- **Title**: Your team code (exemple: `sherbrooke1`)
- **Description**: Use the Markdown template below by replacing texte in `{}`

```markdown
## Team

**Team Name:** {DaStreez}
**Team Code:** {mirego1}
**University:** {Griffintown University}

**Authors:**
- {Hugo Lefrançois}
- {Mathieu Larue}

## Solution

**Plateform:** {iOS | Android}

**Features:**
1. {Map mode: connect the data source to display every pin}
2. {Pin pick-up: found parts can be marked as "picked-up"}
3. {Siri: ask Siri which part is the nearest to current position}
4. {etc.}

**Build Steps:**
- {We added the GooglePlaces lib to the Podfile, you need to run `pod install` before build}
- {We activated the "Siri" capability, connect an Apple Developer account to test}

**What went well or wrong:**
- {The base project was really well done, it helped a lot to begin}
- {The clustering was really tricky, we had to try a couple of libraries...}

**What you are proud or disappointed of:**
- {We're quite proud to have succeeded in connecting Siri, it was above our expectations and it works quite well}
- {We're sad we couldn't attack the AR mode, it looked very promising}

```

> **NOTE:** If you don't have a Github account, raise your hand at the beginning of the challenge, we can provide you with a generic account.

## License

This competition is © 2018 [Mirego](http://www.mirego.com) and may be freely
distributed under the [New BSD license](http://opensource.org/licenses/BSD-3-Clause).
See the [`LICENSE.md`](https://github.com/mirego/csgames18-competition/blob/master/LICENSE.md) file.

## Acknowledgements

Component icons were designed by [Eucalyp](https://creativemarket.com/eucalyp) from [Flaticon.com](https://www.flaticon.com/). Thanks 👌

## About Mirego

[Mirego](http://mirego.com) is a team of passionate people who believe that work is a place where you can innovate and have fun. We're a team of [talented people](http://life.mirego.com) who imagine and build beautiful Web and mobile applications. We come together to share ideas and [change the world](http://mirego.org).

We also [love open-source software](http://open.mirego.com) and we try to give back to the community as much as we can.
