set CLASSPATH=.\target\Practica02RMI-0.0.1-SNAPSHOT.jar

java -Djava.security.policy=registerit.policy ^
-Djava.rmi.server.codebase=http://localhost:8080/Practica02RMI-Web/ ^
es.ubu.lsi.client.GameClientDynamic %1 %2