
#### Request

###### [ GET ]

参数|描述
:--:|:--
page|页码, 默认为 1
per_page|单页大小, 默认为 10
fields|指定返回字段, 多个字段用 "," 分隔, 如：a,b,c
sort|指定排序字段, 多个字段用 "," 分隔, 如：+x,-y,-z

```bash
curl -i -XGET "http://api.domain.com/model/list?page=2&per_page=20&fields=a,b,c&sort=+x,-y,-z"
```

###### [ POST ]

```bash
# Content-Type 为 application/json
curl -i -XPOST \
"http://api.domain.com/model/update" \
-H 'Content-Type: application/json' \
-d '{
    "name": "new name",
    "phone": "new phone"
}'

# Content-Type 为 multipart/form-data
curl -i -XPOST \
"http://api.domain.com/model/upload" \
-F 'media=@avatar.jpg' \
-F 'scene=1'
```

#### Response

```json
{
    "err": 错误码,          // integer
    "msg": 返回消息,        // string
    "data": 数据结构,       // json
    "log": 错误日志         // string
}
```

> err = 0, 表示返回正常

> err < 0, 表示系统异常, log 返回异常日志

> err > 0, 表示业务逻辑异常, msg 返回服务器消息

##### 数据类型

* 数据类型分为：整型(integer), 浮点型(float), 字符串(string), 数组(list), 字典(json)
* 时间类型统一用时间戳表示，精度为毫秒