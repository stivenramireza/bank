docker build -t balance .
docker run -d --name balance-microservice -p 9091:9091 --link eureka:eureka --link postgres:postgres balance
docker logs -f balance-microservice
