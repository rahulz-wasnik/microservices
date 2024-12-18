### Build package
mvn clean package

### Build image
docker build -t teebone1985/customer-service:1.0 .

### Run container
docker run -p 8080:8080 --env SPRING_DATASOURCE_HOST=host.docker.internal teebone1985/customer-service:1.0

### Apply kubernetes changes
kubectl apply -f .\k8\

### If type of service is ClusterIP then use the command below and finally access the api using localhost:9090
kubectl port-forward svc/customer-service-svc 9090:80 - 

### If type of service is LoadBalancer then use command below and access the api as per the port configured in application.yml
minikube tunnel

### If type of service is NodePort then use the command below and access the api using the url displayed in the cmd prompt
kubectl service customer-service-app

### Delete kubernetes changes
kubectl delete -f .\k8\
