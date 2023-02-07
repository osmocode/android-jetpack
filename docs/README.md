# Plutus Developer Documentation

## List of UC (Use Case)

# Niveau <0>

## Transaction 

- comprend un texte libre
- date
- étiquettes -> mots clés pour mieux classer et chercher la transaction
- montant

## Etiquette -> catégoriser une transaction

- mots-clé
- format `-` = dépense
- format `+` = revenus
- format `=` = compte sur lequel à lieu la transac

## Stockage du carnet de comptes

- stockés en `SQLite` -> ORM (`Room`)

## Création et choix de carnets de compte

- création
- duplication
- suppresson

## Création, visualisation et édition de transaction

- ajouter/modifier montant
- ajouter/modifier étiquettes

## Recherche de transactions et rapports

- filtrer sur l'étiquette
- lister toutes les étiquettes utilisées
- critères longs (`transactions de dépenses alimentaires avec l'étiquette -food réalisées en 2022 à moins de 2 km de Champs-sur-Marne portant sur un montant inférieur à 20 euros`)
- production de rapport synthétiques -> somme des montants

# Niveau <1>

## Transaction

- formater le texte avec des `balises Markdown`

## Budget

- définir budget pour étiquette donnée

## Stockage du carnet de comptes

- exporter tout ou une partie du carnet sous la forme d'un fichier

## Recherche de transactions et rapports

- filtrer sur une fourchette de date
- filtrer sur une fourchette de montant
- résultats triable sur montant et/ou date
- exploitation d'index sur étiquettes
- production de rapport synthétiques -> diagramme des montants selon temps avec comparaison avec le budjet<br>
									 -> répartition transactions selon étiquettes

# Niveau <2>

## Transaction 

- des éléments multimédias que l'utilisateur peut acquérir ou pouvant être selectionnés comme déjà enregistrés sur l'appareil
- géolocalisation

## Etiquette

- `@todo` = transaction pas encore réalisée
- jeu d'étiquette prédéfini -> faciliter l'auto complétion
- usage antérieur d'étiquette -> faciliter l'auto complétion

## Stockage du carnet de comptes

- multimédias stockés sous forme de fichiers
- importer un fichier dans carnet -> éviter doublons
- fichier exporté chiffrable par mdp secret -> bibliothèque cryptographique 

## Création et choix de carnets de compte

- fusion transactions lors de l'importation d'un carnet

## Création, visualisation et édition de transaction

- ajouter/modifier géolocalisation
- ajouter/modifier multimédias

## Recherche de transactions et rapports

- filtrer sur la géolocalisation
- filtrer sur mots-clés dans texte libre
- exploitation d'index sur montants/dates

## Notifications de dépassement de budget

- activable
- alerte lors du dépassement de budget
- notification dans tiroir de notif jusqu'a être supprimée par user

## Rappel de transactions

- activer rappel sur emplacement géolocalisé
- notification lorsque l'utilisation se rend sur ce lieu pour saisir une nouvelle transaction
- pareil pour les transaction `@todo` mais sur la date et non le lieu

# Niveau <3>

## Devise

- associer montant avec devise 
- conversion automatique avec API web

## Stockage du carnet de comptes

- sauvegarde automatique -> héberger le fichier exporté, chiffré et uniquement déchiffrable par l'utilisateur

## Recherche de transactions et rapports

- résultats affichable sur carte avec géolocalisation




## User flow

## Wireframes
