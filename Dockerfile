#FROM amazoncorretto:8-alpine-jdk
FROM openjdk:17-jdk

# Copia el archivo JAR construido en la carpeta target del proyecto a la imagen
COPY target/Rent_a_Car-0.0.1-SNAPSHOT.jar app.jar


# Comando que se ejecuta al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]
