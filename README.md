# Inventory Management - Springboot + kafka Sample

This sample project demonstrates how to use spring boot with kafka

------ @author: Kien Tran ---------

Follow this instruction to deploy Inventory Management on a local Kubernetes cluster

### Prerequisite
- Docker Desktop installed locally (https://www.docker.com/products/docker-desktop)
- Kubernetes cluster set up with Docker Desktop. (https://docs.docker.com/desktop/kubernetes/)
- A Docker Hub account to push and pull service image (https://hub.docker.com/)

### Dependencies
- Java 1.8
- Apache Maven 3.8.1

### Setup Nginx Ingress Controller
- Ngixn Ingress Controller installed in kubernetes controller (https://kubernetes.github.io/ingress-nginx/deploy/#quick-start)
```sh
$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.0.4/deploy/static/provider/cloud/deploy.yaml
```
### Deploy to kubernetes cluster
Make sure you have access to kubernetes cluster and kubectl is configured
```sh
$ cd inventory-management/deployment

$ ./install.sh
$ 
$ kubectl config set-context $(kubectl config current-context) --namespace=inventory-management
$ 
$ kubectl get all
$ 
$ Make sure all service, deployement and pod are ready..... It will take 10 to 15 minutes to ready
```

### View api endpoints and description

- Please access http://localhost/api/ims/v1/swagger-ui/index.html?url=/api/ims/v1/v3/api-docs

### Access the inventory management service

- From now on the ims service can be accessed at http://localhost/api/ims/v1/[endpoints].

- If you are using <b>postman</b>, you can import the predefined collection of request to postman to quickly verify the ims service. The api json collection of postman can be found in the folder <b>[inventory-management/deployment/postman]</b>



### Build from source

This program use Apache Maven to manage dependencies and build
Make sure you have Apache Maven 3x in your classpath

1. Build the source code
```sh
$ mvn -v
$ cd inventory-management
$ ./mvnw clean install
```
2. Build docker image
```sh
$ ./mvnw spring-boot:build-image
```
3. Tag and push image to image repository such as Docker Hub
```sh
$ docker tag inventory-management:0.0.1-SNAPSHOT [repository]/ims:[version]
$ docker push [repository]/ims:[version] 
```

4. Update the image version of ims-service deployment in /deployment/ims.yaml 
```sh
spec:
      containers:
        - image: [repository]/ims:[version]
          name: ims
          imagePullPolicy: Always 
```
5. Deploy. Make sure you have a kubernetes cluster ready and kubectl is configured with that cluster
```sh
$ cd inventory-management/deployment
$ ./install.sh
```
6. Using postman to access api endpoints at http://localhost/api/ims/v1/.....


### Clean up

From root folder of project source, run following command

```sh
$ cd deployment
$ ./teardown.sh
```


**Enjoy, Yeah!**

