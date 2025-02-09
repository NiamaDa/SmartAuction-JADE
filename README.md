# Simulateur d'Enchères avec JADE

## 📌 Présentation du projet

Ce projet est une simulation d'enchères utilisant la plateforme **JADE** (Java Agent DEvelopment Framework). Il met en œuvre un système multi-agents où différents acteurs interagissent pour simuler une vente aux enchères.

## 🏛 Structure du projet

Le projet comporte plusieurs agents, chacun ayant un rôle spécifique :

- **AgentVendeur** : Met un objet aux enchères et lance la procédure via l'AgentCommissairePriseur.
- **AgentCommissairePriseur** : Joue le rôle d'un commissaire-priseur, collectant les offres des acheteurs et déterminant le gagnant.
- **AgentAcheteur** : Représente un acheteur qui fait des propositions en fonction des enchères ouvertes.
- **DFService** : Un service d'annuaire permettant aux agents de s'enregistrer et de rechercher leurs services respectifs.
- **Interfaces Graphiques (GUI)** : Chaque agent dispose d'une interface utilisateur facilitant l'interaction avec le système.

## 🚀 Installation et exécution

### 📌 Prérequis

- Java JDK 8+ installé
- JADE téléchargé (lien officiel)
- Un environnement de développement Java (IntelliJ, Eclipse ou NetBeans recommandé)

### 📥 Étapes d'installation

1. **Cloner le projet**
   ```bash
   git clone https://github.com/asmaemgr/EncheresJADE.git
   cd EncheresJADE
   ```

### 2. Ajouter JADE au classpath

Dans votre IDE, ajoutez `jade.jar` dans le projet.

### 3. Compiler le projet

```bash
javac -cp jade.jar:. TPGUI/*.java
```

### 4. Exécuter l'application

```bash
java -cp jade.jar:. TPGUI.MainContainer
```

## 🛠 Fonctionnalités principales

- ✅ **Enregistrement des agents** auprès du service d'annuaire DF
- ✅ **Recherche dynamique des services** (les acheteurs trouvent le commissaire-priseur et inversement)
- ✅ **Lancement des enchères** par le vendeur
- ✅ **Réception et traitement des offres** par le commissaire-priseur
- ✅ **Sélection automatique du meilleur acheteur**
- ✅ **Interfaces graphiques (GUI)** pour chaque agent

## 📜 Explication du déroulement d'une enchère

1. Le vendeur demande au commissaire-priseur de lancer une enchère.
2. Le commissaire-priseur envoie un appel d'offres aux acheteurs enregistrés.
3. Les acheteurs font leurs propositions via leur interface.
4. Le commissaire-priseur choisit la meilleure offre et annonce le gagnant.
5. Le vendeur est informé du résultat de l'enchère.

## 📄 Fichiers principaux

- **MainContainer.java** → Démarre le conteneur JADE et crée les agents.
- **AgentVendeur.java** → Enregistre le vendeur et initie les enchères.
- **AgentCommissairePriseur.java** → Gère le processus d'enchères et choisit le gagnant.
- **AgentAcheteur.java** → Reçoit l'appel d'offres et envoie des propositions.
- **GUI** → Interfaces utilisateur pour chaque agent.
