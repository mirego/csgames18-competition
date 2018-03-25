# CS Games 2018 - Mobile Competition

Welcome to the **CS Games 2018 Mobile Competition**!

## Team

**Team Name:** Battlestar Concordia
**Team Code:** concordia1
**University:** Concordia University

**Authors:**
- Jacob Gagne
- Lance Lafontaine

## Solution

**Platform:** Android

**Client Features:**
1. Created list of parts to see
2. Appropriate icons for part type in List
3. Clicking on item in list brings you to appropriate location on map with appropriate pin and artwork
4. After populating the list by fetching it from our server, we display the "penguin teleportation distance" (Pythagorean distance) between your current GPS location and all parts.

**Server Features (for after our Series A funding) :**
1. Authentication endpoints (register, login and logout)
2. User management endpoints (getAllUsers, getUserById)
3. Mapping endpoints (getAllTypes)
4. Generated a new mapping.json by parsing it and obtaining lat/lon through Google geocoding API
5. Endpoint to calculate the "penguin teleportation distance" (Pythagorean distance) between the user's GPS location and all part locations in real-time

The server is also being hosted live at http://concordia-at-csgames.com:4000/

**Client Build Steps:**
No changes were made to the client build steps

**Server Build Steps:**

1. `cd server`
2. `npm install` to install all the node dependencies
3. `npm run geocode` to generate the new mapping.json with geocoding information
4. `npm start` to start the server

You can see also server API docs in `server/README.md`

**What went well:**
- Defining the service using retrofit went smoothly and worked as expected
- Thanks for providing the icons!
- Kotlin was a welcome addition (we like Kotlin even though we suck at it)

**What went wrong:**
- After competing in the Mobile competitions for 2 years in a row, we were really expecting requirements for an Express server..
- We had issues with the Google Play services in the emulator so we had to downgrade to version 11
- We didn't immediately realize that the icons were provided

**What you are proud of:**
- We're proud of delivering features that we think fit well with the scope of the application
- We're proud that we were able to delegate some tasks to our server to put it to good use

**What you are disappointed of:**
- More Express web servers please!
- AR didn't work because we were using the emulator :(

## Introduction

The space penguins arrival on Earth has caused a great stir in the local community. Unused to live extraterrestial presences, the places of worship invested all their energy to help these newcomers. Mass efforts were deployed to find the vessel parts spreaded all over the country. A *crowdsourcing* platform was erected to document missing parts, as potentially identified by the tribes. The resulting data source was shared publicly here:

<p align="center">
  <strong><a>https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json</a></strong>
</p>

Satisfied by this unexpected assistance, the penguins wanted to make the most out of the technology available to use this data source intelligently. They started to develop a mobile application, called **Map Ping**, which allows them to see this list of parts and facilitate their repatriation. Alas, their days are numbered and the technology being complex, the penguins need help to finish their application and they're reaching to you, the participants.

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

You will find the base code of **Map Ping** in the subfolders [`ios/`](https://github.com/mirego/csgames18-competition/tree/master/ios) and [`android/`](https://github.com/mirego/csgames18-competition/tree/master/android) of this GitHub repository:

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

**Map Ping** plans to display the identified vessel parts and help the penguins to find them in the wild. Currently, the [data source](https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json) is not connected and all screens are purely static.

### Data Connection

You first mission is to connect the data to display the parts in the app. Every identified part was detailed with much info:

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

* Display every part in a scrollable list with reusable cells
* Display the part summaries using the data source
* Display the right icon for each part type in their cells
* Display the relative distance between the part and the phone current location
* Sort the parts by proximity (the closest to the farthest)
* Filter the parts by type or by vessel component

#### <img src="https://user-images.githubusercontent.com/4378424/37808994-ac7c2cd8-2e23-11e8-8b7f-d499210cf74e.png" height="16" width="20"> Part Map

* Display every part with pins on the map (📍)
* Display the part name and component on pin taps
* Display icons of part types in the pins
* Filter the parts by type or by vessel component
* Regroup the pins with clustering when dezooming the map
* Position the map around the current position (and display it)
* Give turn-by-turn directions between the current position and a selected part

#### <img src="https://user-images.githubusercontent.com/4378424/37808992-ac65adfa-2e23-11e8-8350-65befbe98843.png" height="16" width="20"> Augmented Reality
* Display the part in geolocalized tridimensional space
* Display the distance between the part and the current location

This list is only a summary of possibilities, you are free to add anything to render one or more of these display modes more useful. The mockups in the introduction can serve as inspiration.

### Additional Features

One the parts shown, many features can be added to the app to help penguins find back their vessel parts. For example:

- **Part detail**: The List View only shows a summary of each part, and the map only positions them. A new "Part Detail" screen could show more specific information, like the identification notes and the civil address.

- **Part pickup**: While there are many parts to pick up, it could be useful to mark them as "picked up" to easily see those that were found and those that are left.

- **Pickup notes**: When parts are picked up, it could also be useful to add information from the situation. Part photo, pickup notes, etc - a form could be displayed to enter these details.

- **Persistence**: While entering data in the app has its value, it's rendered useless if everything is forgotten once the app is closed. Implementing a local storage would insure persistence.

The potential features are infinite, we sollicit your creativity to make the most out of **Map Ping** and give a big hand to our penguin friends in distress.

## Evaluation

We're not asking to do connect the data source, implement **all** display modes and add **all** the features listed above. In 3 hours, we ask you to do as much as possible and make full use of your creativity – the ultimate goal is to impress us.

### Criteria

We will score your work based on **_how well you did on each feature_**, not **_the number of features you did try to implement_**. It's better to do one thing really well than do multiple things incorrectly or incompletely.

Your solutions will be marked using the following grid:

<table>
  <thead>
    <tr>
      <th>Criteria</th>
      <th>Points</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <p><strong>Solution</strong></p>
        <ul>
          <li>General resolution of the problem </li>          <li>Originality of the solution</li>          <li>Quality of the integration (look and feel)</li>
          <li>Respect of the theme</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 35</strong></td>
    </tr>
    <tr>
      <td>
        <p><strong>Implementation</strong></p>
        <ul>
          <li>General quality of the code</li>          <li>Good use of programming patterns</li>          <li>Good use of libraries and external resources</li>
          <li>Respect of the platform standards</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 30</strong></td>
    </tr>
    <tr>
      <td>
        <p><strong>Quality</strong></p>
        <ul>
          <li>Respect of the project structure</li>          <li>Respect of the language syntax</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 10</strong></td>
    </tr>
    <tr>
      <td>
        <p><strong>Submission</strong></p>
        <ul>
          <li>Pull Request correctly submitted</li>
          <li>Solution description provided and well-documented</li>
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

Other important points:

- A project that **doesn't build** will be awarded a note of **0 / 100** (after minimal effort). Make sure that your solution is well documented if any build steps are required.

- Your code must be based on our sample projects and your additions must be easily trackable using a **diff**. If you restructured the repository and we must go through every file to see your changes, you will get **0 / 25** on the "Submission" mark.

- The submission timestamp must not be greater than the end of the competition. We will archive all branches when leaving the room; trying to amend your commits afterwards is fairly useless. If we catch you cheating, you will also get **0 / 100**.

### Submission

<table><thead><tr><th align="center">
⚠️ Please keep 10 minutes before the end of the competition to prepare the submission ⚠️ 
</th></tr></thead></table>

The submission will be done by adding a **Pull Request** to this GitHub repository.

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

**Platform:** {iOS | Android}

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

> **NOTE:** If you don't have a GitHub account, raise your hand at the beginning of the challenge, we can provide you with a generic account.

## License

This competition is © 2018 [Mirego](http://www.mirego.com) and may be freely
distributed under the [New BSD license](http://opensource.org/licenses/BSD-3-Clause).
See the [`LICENSE.md`](https://github.com/mirego/csgames18-competition/blob/master/LICENSE.md) file.

## Acknowledgements

Component icons were designed by [Eucalyp](https://creativemarket.com/eucalyp) from [Flaticon.com](https://www.flaticon.com/). Thanks 👌

## About Mirego

[Mirego](http://mirego.com) is a team of passionate people who believe that work is a place where you can innovate and have fun. We're a team of [talented people](http://life.mirego.com) who imagine and build beautiful Web and mobile applications. We come together to share ideas and [change the world](http://mirego.org).

We also [love open-source software](http://open.mirego.com) and we try to give back to the community as much as we can.
