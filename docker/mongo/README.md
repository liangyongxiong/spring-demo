
##### MongoDB

```bash
show dbs;

use admin;
db.createUser({ user: "root", pwd: "root", roles: ["root"] })

use database;
db.createUser({ user: "USERNAME", pwd: "PASSWORD", roles: ["readWrite"] })
```
