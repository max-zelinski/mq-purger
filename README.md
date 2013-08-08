Simple JMS consumer that purges any MQ 7 queue in one thread

Usage
=========
* mvn package
* java -Dhost=[MQ address] -Dport=[Queue Manager port] -DqueueManager=[Queue Manager] -Dchannel=[Server channel] -Dqueue=[Queue to purge] -jar purger-1.0-jar-with-dependencies.jar
