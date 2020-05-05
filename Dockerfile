# /==============================
# / JAR Compile step
# /==============================

FROM gradle:6.3.0-jdk8 AS gradle-build

# Prepare workspace
RUN mkdir /build
WORKDIR /build

ADD . .
RUN mv service/src/main/java/thepublictransport/schildbach/pte/service/framework/api/APIKeys.template service/src/main/java/thepublictransport/schildbach/pte/service/framework/api/APIKeys.kt

# Build
RUN gradle :service:bootJar

# /==============================
# / GraalVM native image compilation & execution
# /==============================
FROM oracle/graalvm-ce

# Prepare workspace
RUN mkdir /service
WORKDIR /service

RUN gu install native-image
COPY --from=gradle-build /build/service/build/libs/service-0.0.1-SNAPSHOT.jar .

# Compile to native
RUN native-image -jar service-0.0.1-SNAPSHOT.jar --enable-http --enable-https -O1

EXPOSE 4823

ENTRYPOINT ["/service/service-0.0.1-SNAPSHOT"]
