
#### 切换DB

```
GET /redis/db
```

###### request params

字段|定义|必选|描述
:--:|:--:|:--:|:--
db|integer|Y|DB序号
key|string|Y|键名

###### example

```bash
curl -i -XGET "http://ENDPOINT/redis/db?db=1&key=x"
```
