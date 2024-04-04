Assurez vous que le port 3306 est ouvert et libre puis executer la commande suivante pour creer une image de mysql qui se nomme mysql, le nom de la base de donnees sera climaxdb

docker run -p 3306:3306 --name climaxdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=climaxdb -d mysql

Il est constitue de 5 microservices
- Configserver sur le port 8071
- Eurekaserver sur le port 8070
- Gatewayserver sur le port 8072
- Clientserver sur le port 8069
- Fileprocessserver sur le port 8068

MySql marche sur le port 3306
Creer l'image de Mysql avec la commande suivante:

Generate a new docker image from a microservice
mvn compile jib:dockerBuild

Un fichier CSV pour tester est dans le front-end
Un fichier txt pour tester est dans le front-end
Un fichier json pour tester est dans le front-end

NB: Le back-end utilise JAVA 17 minimum
Le fichier CSV doit avoir l'entete formée ainsi: 
CSVFormat.EXCEL.withDelimiter(';').withHeader("nom", "prenom", "identifiant", "profession", "revenu")
