# Spring Security OAuth2

## OAuth2-authserver

### 啟動

```
$ mvn spring-boot:run
```

### 新增資料

```
insert into oauth_client_details (client_id, client_secret, scope, authorized_grant_types) values ('sample', 'samplesecret', 'read,delete', 'authorization_code,password');
```
### 取得 code

[http://localhost:8888/oauth2-server/oauth/authorize?response_type=code&client_id=sample&redirect_uri=http://example.com](http://localhost:8888/oauth2-server/oauth/authorize?response_type=code&client_id=sample&redirect_uri=http://example.com)

### 用 code 換 token

```
$ curl sample:samplesecret@localhost:8888/oauth2-server/oauth/token -d grant_type=authorization_code -d client_id=sample -d redirect_uri=http://example.com -d code=mKkwZK 
=> code 換成自己取得的
``