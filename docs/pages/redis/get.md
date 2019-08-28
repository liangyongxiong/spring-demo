
#### 读取

```
GET /redis/get
```

###### request params

字段|定义|必选|描述
:--:|:--:|:--:|:--
key|string|Y|键名

###### example

```bash
curl -i -XGET "http://ENDPOINT/redis/get?key=x"
```
