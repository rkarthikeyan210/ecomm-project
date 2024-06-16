#Instal Redis using docker
```bash
docker run -d --name ecomm-redis -p 6379:6379 -v /home/concentrix/Documents/Learn/ecomm-project/data/redis:/data -e REDIS_ARGS="--requirepass password --appendonly yes" redis/redis-stack-server:latest
```