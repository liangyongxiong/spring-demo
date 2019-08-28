
### standalone

```bash
# OSX
launchctl unload ~/Library/LaunchAgents/homebrew.mxcl.redis.plist

# Centos
yum -y install centos-release-scl-rh
yum -y install rh-ruby23
scl enable rh-ruby23 bash
gem install redis
```

### cluster

```bash
rm -rf /opt/push/redis/cluster/*
docker ps -a | grep redis_cluster | awk '{ print $NF }' | xargs -I {} docker rm -f {}

./cluster.sh
./redis-trib.rb create --replicas 1 [NODES]

redis-cli -h IP -p PORT

cluster info
cluster nodes
```
