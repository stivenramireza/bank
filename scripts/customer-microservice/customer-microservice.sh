docker build -t customer .
docker run -d --name customer-microservice -p 9092:9092 --link eureka:eureka --link postgres:postgres customer
docker logs -f customer-microservice
