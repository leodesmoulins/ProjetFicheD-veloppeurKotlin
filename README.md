# Profil développeur Compose

## Description

Cette application Android affiche une fiche « Profil développeur » construite avec Jetpack Compose.
L'écran présente un nom, un rôle, une courte description, une liste de compétences et des informations de contact.
Un badge cliquable indique la disponibilité et un bouton « Contacter » permet de déclencher une action.
L'interface est découpée en petits composables réutilisables, alimentés par des paramètres,
ce qui permet d'afficher plusieurs profils différents (voir les deux previews).

## Notions utilisées

- fonctions composables (`@Composable`)
- paramètres et composants réutilisables
- `Modifier` passé par le parent (`modifier: Modifier = Modifier`)
- chaînage de modifiers (`fillMaxWidth().clickable(role = Role.Button, ...).semantics { ... }`)
- padding externe / padding interne
- callbacks (`onClick`, `onContactClick`, `onAvailabilityClick`)
- `Text`, `Button`, `Surface`, `Column`
- `Scaffold` et `innerPadding`
- thème généré du projet (`MaterialTheme`)
- `@Preview` (deux variantes)
- accessibilité de base (`Role.Button`, `contentDescription`)

## Explication

**1. Quels composables avez-vous créés ?**
`ProfileScreen`, `ProfileCard`, `ProfileHeader`, `SectionTitle`, `SkillItem`, `SecondaryInfo`,
`AvailabilityZone` et `ProfileActionButton` (dans `ui/profile/ProfileComponents.kt`),
ainsi que deux previews.

**2. Quel composable représente l'écran principal ?**
`ProfileScreen`. Il est appelé depuis `MainActivity`, dans le `Scaffold`, avec `Modifier.padding(innerPadding)`.

**3. Où utilisez-vous un padding externe ?**
Dans `ProfileScreen`, lorsque le parent place la fiche : `ProfileCard(modifier = Modifier.fillMaxWidth().padding(16.dp))`.
C'est le parent qui décide de l'espace *autour* de la carte. Même chose dans `ProfileCard` lorsqu'il espace
les sections entre elles (`padding(top = 20.dp)` passé à `SectionTitle`, `AvailabilityZone`, etc.).

**4. Où utilisez-vous un padding interne ?**
Dans `ProfileCard`, la `Column` à l'intérieur de la `Surface` a un `padding(20.dp)` qui éloigne le contenu
des bords de la carte. `SkillItem` et `AvailabilityZone` appliquent aussi un padding au `Text` situé
*dans* leur `Surface` : le composant gère lui-même l'espacement de son contenu.

**5. Quel composant reçoit un callback ?**
`ProfileActionButton` (`onClick`) et `AvailabilityZone` (`onClick`). Ces callbacks remontent par
`ProfileCard` et `ProfileScreen` jusqu'à `MainActivity`, où ils ne contiennent qu'un commentaire.
Les composants signalent l'événement sans décider de la logique.

**6. Quelle information d'accessibilité avez-vous ajoutée ?**
`AvailabilityZone` est une zone cliquable personnalisée (une `Surface`, pas un `Button`).
On lui ajoute donc `clickable(role = Role.Button, ...)` pour indiquer que c'est un bouton,
et un `semantics { contentDescription = ... }` qui décrit l'action au lecteur d'écran.
Le `Button` « Contacter » n'en a pas besoin : il expose déjà nativement le rôle de bouton et son texte.

**7. Pourquoi vos composants peuvent-ils être considérés comme réutilisables ?**
Ils ne contiennent aucune donnée en dur : tout arrive par paramètres (nom, rôle, compétence, texte du bouton,
callback…). Ils acceptent tous un `modifier` qui laisse le parent choisir leur placement.
Par exemple, `SkillItem` est appelé pour chaque compétence, `SectionTitle` sert pour deux sections,
et les deux previews affichent deux profils différents avec exactement les mêmes composables.

## Capture

![Preview du profil](docs/capture-preview.png)

## Lancer le projet

Ouvrir le dossier dans Android Studio, laisser Gradle se synchroniser, puis lancer l'application
ou ouvrir `ProfileComponents.kt` en vue *Split* pour voir les previews.
