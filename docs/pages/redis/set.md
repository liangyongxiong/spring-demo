
#### 写入

```
GET /redis/set
```

###### request params

字段|定义|必选|描述
:--:|:--:|:--:|:--
key|string|Y|键名
value|string|Y|值名

###### example

```bash
curl -i -XGET "http://ENDPOINT/redis/get?key=x&value=y"
```
