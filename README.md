# CeriAvignon-StageRechercheOp-rationnelle

------
README
------

1. Contenu

Cette archive contient un dossier Parcours correspondant au projet qui contient:
- Un dossier doc contenant la javadoc
- Un dossier src contenant les fichiers source .javadoc
- Un dossier style contenant la feuille de style des graphes (non utilisée)

-------------------------------
	
2. Déploiement

Cette application est fonctionnelle moyennant quelques bugs.

En revanche plusieurs problèmes sont présents au niveau du déploiement de l'applet:
- Il faut signer l'archive jar une fois créée
- Firefox et plusieurs autres navigateurs ne supportent plus Java

Pour déployer cette application sur un site, il est donc nécessaire d'utiliser la technologie Java Web Start.
Il faut également signer l'archive jar.
Il est également possible qu'il soit nécessaire de spécifier les paramètres de sécurité de l'archive.

-------------------------------

3. Test du code source

Il est possible de tester le code source de l'application en utilisant le visualiseur d'applet d'Eclipse.
La classe principale (contenant la méthode init()) est située dans Frame.java.




