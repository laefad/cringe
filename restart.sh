# /bin/bash

docker-compose down;
yes | docker-compose rm;
docker volume rm postgres-source;
docker volume rm pg-admin;
docker-compose up --build;