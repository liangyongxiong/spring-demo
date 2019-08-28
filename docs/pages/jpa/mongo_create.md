
#### 创建Mongo实体

```
POST /jpa/mongo/create
```

###### request body

字段|定义|必选|描述
:--:|:--:|:--:|:--
name|string|Y|联盟名称

###### response body

```json
{
    "id": 联盟ID
    "name": 联盟名称,
    "users": [
        {
            "name": 姓名,
            "age": 年龄,
            "hobby": {
                "name": 爱好名称
            },
            "union": 从属联盟ID
        },
        { 用户JSON },
        { 用户JSON },
        ...
    ]
}
```

###### example

```bash
curl -i -XPOST \
"http://ENDPOINT/jpa/mongo/create"
-H 'Content-Type: application/json' \
-d '{
    "name": "XXX"
}'
```
