# 🏆 Simulateur d'Enchères avec JADE

## 🔍 Présentation

Ce projet propose une simulation d'enchères basée sur le framework **JADE** (Java Agent DEvelopment Framework). Il exploite un système multi-agents où plusieurs entités interagissent pour simuler une mise aux enchères dynamique et automatisée.

## 🏗️ Architecture du projet

Le système repose sur plusieurs agents aux rôles distincts :

- **AgentVendeur** : Propose un objet aux enchères et initie le processus.
- **AgentCommissairePriseur** : Agit comme modérateur en collectant les offres et en désignant le gagnant.
- **AgentAcheteur** : Participe aux enchères en soumettant des propositions d’achat.
- **DFService** : Annuaire permettant aux agents de s'enregistrer et de rechercher les services disponibles.
- **Interfaces Graphiques (GUI)** : Facilite l’interaction entre les agents et l’utilisateur.

## 🚀 Installation et Exécution

### 📌 Prérequis

- Java JDK 8+ installé
- JADE téléchargé depuis le site officiel
- Un IDE Java compatible (IntelliJ IDEA, Eclipse, NetBeans, etc.)

### 📥 Étapes d'installation

1. **Cloner le dépôt**
   ```bash
   git clone https://github.com/asmaemgr/EncheresJADE.git
   cd EncheresJADE
   ```
2. **Ajouter JADE au classpath**
   - Importer `jade.jar` dans votre projet.

3. **Compiler le projet**
   ```bash
   javac -cp jade.jar:. TPGUI/*.java
   ```

4. **Lancer l’application**
   ```bash
   java -cp jade.jar:. TPGUI.MainContainer
   ```

## ⚙️ Fonctionnalités principales

- 📌 **Enregistrement des agents** auprès du DFService
- 🔍 **Découverte des services** entre acheteurs et commissaire-priseur
- 🏷️ **Création et lancement des enchères** par le vendeur
- 💰 **Gestion des offres et sélection du meilleur acheteur**
- 🖥️ **Interfaces utilisateur dédiées** pour chaque type d’agent

## 🔄 Déroulement d'une enchère

1. Le vendeur confie son produit au commissaire-priseur.
2. Le commissaire-priseur diffuse un appel d’offres aux acheteurs disponibles.
3. Les acheteurs soumettent leurs enchères via leur interface.
4. Le commissaire-priseur sélectionne la meilleure offre et attribue l’objet au gagnant.
5. Le vendeur reçoit la confirmation du résultat de la vente.

## 📂 Principaux fichiers

- **MainContainer.java** → Initialise l’environnement JADE et les agents.
- **AgentVendeur.java** → Crée les enchères et les propose au commissaire-priseur.
- **AgentCommissairePriseur.java** → Supervise le déroulement des enchères et sélectionne le vainqueur.
- **AgentAcheteur.java** → Réagit aux enchères et envoie des offres.
- **GUI/** → Interfaces graphiques interactives pour chaque agent.

🎯 Ce projet illustre les principes de l'intelligence artificielle distribuée en utilisant JADE pour simuler des interactions complexes entre agents autonomes.

