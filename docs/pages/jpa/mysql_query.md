
#### 查询Mongo实体

```
GET /jpa/mysql/query
```

###### request body

字段|定义|必选|描述
:--:|:--:|:--:|:--
page|integer|Y|页码数
per_page|integer|Y|页大小

###### response body

```json
{
    "emtpy": false,
    "first": false,
    "last": true,
    "size": 10,
    "number": 1,
    "numberOfElements": 1,
    "totalElements": 2,
    "totalPage": 1,
    "pageable": { Pageable JSON },
    "sort": { Sort JSON },
    "content": [
        { 工人JSON },
        { 工人JSON },
        ...
    ]
}
```

###### example

```bash
curl -i -XGET "http://ENDPOINT/jpa/mysql/query?page=1&per_page=10"
```
