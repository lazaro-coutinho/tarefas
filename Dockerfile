FROM openjdk

WORKDIR /app

COPY /target/tarefas-0.0.1-SNAPSHOT.jar /app/tarefas.jar

ENTRYPOINT ["java", "-jar", "tarefas.jar"]