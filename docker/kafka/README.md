
##### Kafka

```bash
# ip
ifconfig en0 | grep inet | grep -v inet6 | cut -d ' ' -f2

# startup
docker-compose -f docker-compose.yml up -d

# cli
cd /opt/kafka
bin/kafka-topics.sh --list --zookeeper zookeeper:2181
bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic KAFATOPIC
bin/kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic KAFATOPIC
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic KAFATOPIC
bin/kafka-console-consumer.sh --zookeeper zookeeper:2181 --topic KAFATOPIC --from-beginning

# timezone
docker-compose exec kafka apk update
docker-compose exec kafka apk add tzdata
```
