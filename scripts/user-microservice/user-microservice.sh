docker build -t user .
docker run -d --name user-microservice -p 9093:9093 --link eureka:eureka --link postgres:postgres user
docker logs -f user-microservice
