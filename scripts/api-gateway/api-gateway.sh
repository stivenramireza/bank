docker build -t api-gateway .
docker run -d --name api-gateway -p 8081:8080 --link eureka:eureka api-gateway
docker logs -f api-gateway
