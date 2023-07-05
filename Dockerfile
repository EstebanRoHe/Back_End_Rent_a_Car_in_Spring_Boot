FROM openjdk:17-jdk

COPY target/Rent_a_Car-0.0.1-SNAPSHOT.jar app.jar

VOLUME /files

EXPOSE 8081

ENV SPRING_PROFILES_ACTIVE=production
ENV SPRING_MAIL_HOST=smtp.gmail.com
ENV SPRING_MAIL_PORT=587
ENV SPRING_MAIL_USERNAME=rentacarspringboot@gmail.com
ENV SPRING_MAIL_PASSWORD=onpmnegqwzpvvlgg
ENV SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH=true
ENV SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE=true


ENTRYPOINT ["java", "-jar", "app.jar"]


