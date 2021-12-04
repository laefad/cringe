# /bin/bash

docker container stop $(docker container ls -aq)
docker container prune -f

docker rmi -f $(docker images -aq)