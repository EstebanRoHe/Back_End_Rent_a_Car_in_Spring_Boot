# Usa una imagen base de Java
FROM amazoncorretto:8-alpine-jdk


# Copia el archivo JAR construido en la carpeta target del proyecto a la imagen
COPY target/Rent_a_Car-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080 para la aplicación
#EXPOSE 8080

# Comando que se ejecuta al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]