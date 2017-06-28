# Spring Security OAuth2(use JWT)

## authorization-server

### 啟動

```
$ cd authorization-server
$ mvn spring-boot:run
```

### 取得 code

[http://localhost:8888/authorization-server/oauth/authorize?response_type=code&client_id=sample&redirect_uri=http://example.com](http://localhost:8888/authorization-server/oauth/authorize?response_type=code&client_id=sample&redirect_uri=http://example.com)

### 用 code 換 token

```
$ curl sample:samplesecret@localhost:8888/authorization-server/oauth/token -d grant_type=authorization_code -d client_id=sample -d redirect_uri=http://example.com -d code=kd1Rg1
(code 換成自己取得的)
```

### 建立自己的 keystore

generate keystore:

```
$ keytool -genkeypair -alias jwt -keyalg RSA -keystore jwt.jks -keypass mySampleKey -storepass mySampleKey -dname "CN=sample, OU=keeplearning, C=Taipei, C=TW"
```

可替換成自己產生的 `jwt.jks`

## resource-server

### 啟動

```
$ cd resource-server
$ mvn spring-boot:run
```

### 對 resource server 發送請求

```
$ TOKEN=f8d33487-b560-4564-adb3-ea88de7e49dd
$ curl -H "Authorization: Bearer $TOKEN" -v localhost:9999/resource-server/
$ curl -H "Authorization: Bearer $TOKEN" -v localhost:9999/resource-server/user
```