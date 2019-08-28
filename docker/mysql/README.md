
##### MySQL

```bash
ALTER USER root IDENTIFIED WITH mysql_native_password BY 'root';

SET GLOBAL binlog_format='STATEMENT';
SELECT @@binlog_format;
SHOW BINLOG EVENTS IN 'binlog.000001';

docker run -it --rm -v /opt:/opt mysql mysqlbinlog /opt/push/mysql/standalone/data/binlog.000001
```
