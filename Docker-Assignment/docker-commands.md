## Docker Commands Used

### 1. Build the JAR file
```bash
mvn clean package
```
![Alt text](screenshots/SS1.png)
### 2.Run the Generated JAR File
```bash
cd target
java -jar Docker-Assignment-1.0-SNAPSHOP.jar
```
![Alt text](screenshots/SS2.png)
### 3. Create a Dockerfile
![Alt text](screenshots/SS3.png)
### 4.Build the Docker Image
```bash
docker build -t docker-assignment .
```

![Alt text](screenshots/SS4.png)

### 5.Run the Docker Image
```bash
docker run docker-assignment .
```

![Alt text](screenshots/SS5.png)

### 6. List All Docker Images
```bash
docker images
```

![Alt text](screenshots/SS6.png)

### 7. Remove the Docker Image
```bash
docker ps -a
docker stop 440967e2b869
docker rm 440967e2b869
docker rmi docker-assignment
```

### 8.Run the Docker Image
```bash
docker images
```
![Alt text](screenshots/SS7.png)

### 9. Pull the Hello World Image
```bash
docker pull hello-world
```

![pull hello world image](screenshots/SS8.png)

### 10. Run Hello World
```bash
docker run hello-world
```

![Alt text](screenshots/SS10.png)

### 11. List Docker Images Again
```bash
docker images
```

![Alt text](screenshots/SS9.png)

### 12.Pull and Run MongoDB as Docker Container
```bash
docker pull mongo
docker run -d --name my-mongo -p 27017:27017 mongo
```

![Alt text](screenshots/SS12.png)

### 13. Open Mongo Shell
```bash
docker exec -it my-mongo mongosh
show dbs
```

![Alt text](screenshots/SS11.png)



