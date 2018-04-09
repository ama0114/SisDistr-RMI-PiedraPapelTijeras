set CLASSPATH=.\target\Practica2RMI-0.0.1-SNAPSHOT.jar

java -Djava.security.policy=registerit.policy ^
-Djava.rmi.server.codebase=http://localhost:8080/Practica2RMI-Web/ ^
es.ubu.lsi.server.GameServerDynamic