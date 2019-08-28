#!/bin/bash

count=6
names=""

# create folders
for i in $(seq 1 ${count})
do
    target="/opt/push/redis/cluster/n$i"
    mkdir -p $target
    cp redis-cluster.conf $target/redis.conf
    names="${names} redis_cluster_n${i}"
done

# create nodes
docker-compose -f docker-compose-cluster.yml up -d
echo ${names}

# print ip:port of nodes
nodes=`docker inspect ${names} | grep '"IPAddress"' | awk -F '"' '{ print $4":6379" }' | uniq | sort | sed ':a;N;s/\n/ /;t a;'`
echo ${nodes}

