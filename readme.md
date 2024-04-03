Assurez vous que le port 3306 est ouvert et libre puis executer la commande suivante pour creer une image de mysql qui se nomme mysql, le nom de la base de donnees sera climaxdb

docker run -p 3306:3306 --name climaxdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=climaxdb -d mysql

Generate a new docker image from a microservice
mvn compile jib:dockerBuild