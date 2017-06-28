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
$ TOKEN=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE0OTg2NjkxNDQsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiMmU0N2FiZWItYjQzNC00MzlhLWI0NDUtN2U0YTlhNzE4ODlkIiwiY2xpZW50X2lkIjoic2FtcGxlIiwic2NvcGUiOlsicmVhZCIsImRlbGV0ZSJdfQ.YjKEO_p765h1_XJxEbv1jXSqFptMPuIj9kW47enGNXUu3Vxh0er2BGY4pGVYWtgLCALcGgagC_JcB6JWfqo0oGlRJOM5SeHGj-jh2-Zk-bTf7RfXPlRxqfZEg3bCUL19NwCbMvzqm3vVq6CuDYF3UYSOLCphRnhW1rKtj3MVQKHN2MIHVXHW9aPxvc4A3olpkbbTtHzhJ6WzptCqmlfW9buN57mUnRWC6rExDb0aCD_dZXd0EHJdQJCjHaMXLHZpnjWkCvfA_8KDs2lkcGw8Xrk120Cp7fIYMJDLtQ8Q_yEtc26AODmj5uDSnhI8vjnkUKl4w_juXxcyxJhq4hwSgg
$ curl -H "Authorization: Bearer $TOKEN" -v localhost:9999/resource-server/
$ curl -H "Authorization: Bearer $TOKEN" -v localhost:9999/resource-server/user
```