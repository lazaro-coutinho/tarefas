FROM openjdk

WORKDIR /app

COPY /target/tarefas-0.0.1-SNAPSHOT.jar /app/tarefas.jar

CMD java -XX:+UseContainerSupport -Xmx512m -jar tarefas.jar --server.port=$PORT