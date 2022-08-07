- ./gradlew build java -jar product-service/build/libs/*.jar & kill $(jobs -p)
- curl http://localhost:7001/product/123
- curl http://localhost:7000//product-composite/213 -s | jq .

- aeby@DESKTOP-NEMABNV MINGW64 ~/IdeaProjects/ted-micro-service
- $ ./gradlew product-server:build
- $ ./gradlew recommend-server:build
- $ ./gradlew review-server:build
- $ ./gradlew product-server:build

- docker ps
- cd product-server
- docker build -t product-server .
- docker images | grep product-server
- docker run --rm -p8080:8080 -e "SPRING_PROFILES_ACTIVE=docker" product-server
- docker run -d -p8080:8080 -e "SPRING_PROFILES_ACTIVE=docker" --name product-server product-server
- docker logs product-server -f
- docker rm -f product-server
- docker rmi product-server
- curl http://localhost:8080/product/3
- docker-compose build
- docker images | grep ted-micro-service
- docker-compose up -d
- docker-compose logs -f
- docker-compose down
- docker-compose ps
- docker-compose logs product
- docker-compose system prune -f --volumes
- docker-compose up -d --scale product=0
- docker-compose up -d --scale product=1
- ./gradlew clean build && docker-compose build && ./test-em-all.bash start stop
- ./gradlew build && docker-compose build && docker-compose up -d
- docker ps --format {{.Names}}
- curl -X GET "http://localhost:7000/product-aggregate/1" -H  "accept: application/json"
- docker-compose exec mongodb mongo product-db --quiet --eval "db.products.find()"
- docker-compose exec mysql mysql -uuser -p review-db -e "select * from reviews"



