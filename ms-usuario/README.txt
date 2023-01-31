#Crear JAR
mvn clean
mvn package
mvn package -DskipTests
#construir Docker El "." es la raiz donde esta el Dockerfile
docker build .
docker run fc5660ee9e8a #falla
docker run -p 8001:8080 idImagen #funciona
