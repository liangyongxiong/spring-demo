
#### 查询Mongo实体

```
GET /jpa/mongo/query
```

###### request body

字段|定义|必选|描述
:--:|:--:|:--:|:--
page|integer|Y|页码数
per_page|integer|Y|页大小

###### response body

```json
{
    "emtpy": false,         // 是否无数据
    "first": false,         // 是否第一页
    "last": true,           // 是否最后一页
    "size": 10,             // 页大小
    "number": 1,            // 页码数
    "numberOfElements": 1,
    "totalElements": 2,
    "totalPage": 1,
    "pageable": { Pageable JSON },
    "sort": { Sort JSON },
    "content": [
        { 用户JSON },
        { 用户JSON },
        ...
    ]
}
```

###### example

```bash
curl -i -XGET "http://ENDPOINT/jpa/mongo/query?page=1&per_page=10"
```
