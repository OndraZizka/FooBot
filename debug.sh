

DEBUG="-Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=y";
JAR="/home/ondra/work-unrelated/RedBot/target/RedBot-1.0-SNAPSHOT.jar"

MAP=src/test/maps/city.txt


java $DEBUG -jar $JAR $MAP 3 