
#### 创建MySQL实体

```
POST /jpa/mysql/create
```

###### request body

字段|定义|必选|描述
:--:|:--:|:--:|:--
name|string|Y|车间名称

###### response body

```json
{
    "id": 车间ID
    "name": 车间名称,
    "workers": [
        {
            "name": 姓名,
            "age": 年龄,
            "workshop": 从属车间ID
        },
        { 工人JSON },
        { 工人JSON },
        ...
    ]
}
```

###### example

```bash
curl -i -XPOST \
"http://ENDPOINT/jpa/mysql/create"
-H 'Content-Type: application/json' \
-d '{
    "name": "XXX"
}'
```
