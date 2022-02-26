docker build -t eureka .
docker run -d --name eureka -p 1111:1111 eureka
docker logs -f eureka
