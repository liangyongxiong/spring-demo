##### JPA Mongo Create

```shell
curl -i -XPOST -H 'Content-Type: application/json' "http://localhost:8888/jpa/mongo/create" -d '
{
  "users": [
    { "name": "user1", "age": 35 },
    { "name": "user2", "age": 45 },
  ]
}'
```

##### JPA MySQL Create

```shell
curl -i -XPOST -H 'Content-Type: application/json' "http://localhost:8888/jpa/mysql/create" -d '
{
  "workers": [
    { "name": "worker1", "age": 35 },
    { "name": "worker2", "age": 45 },
  ]
}'
```
