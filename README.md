# Spring Security OAuth2

* [詳細介紹](https://keeplearning.blog/2017/06/28/java-spring-security-oauth2/)

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


## resource-server

### 啟動

```
$ cd resource-server
$ mvn spring-boot:run
```

### 對 resource server 發送請求

```
$ TOKEN=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE0OTk0Mzg1MjUsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiZGI4OTRmMmItYTIyOS00YTQ5LTkyYzgtZDY5NTUwMDVkNzYyIiwiY2xpZW50X2lkIjoic2FtcGxlIiwic2NvcGUiOlsicmVhZCIsImRlbGV0ZSJdfQ.NC8jm1bPL5E0dHT-tuDVeDrMNmVVWL9jmXTJWwZ7TX8cV65bkk6i15u_nEA8xWZTZsA6M5o-1yiUqK_iKFN1_xZgpwR607lisAMIIZod_CqwjQc_Vuhm1BfuR8ByTbBvTh1eZiAmZi2IyQxawodEC9a_peX3gZ6XrDl6xbxb4kXtFLfiWz9jXv4I7zBfHKnlIiDAp6FKHx1Axev416SMmY9NPGt3yX3UY2xYOwX7nLMTr2l__5yGMkzK_JhVVlbgRl0Xu90SwyoKuxaPuAoyQTqUCwYtIKY-0Wx8X0hh2ku6SzYpmT-ViEl8Xj9NXRv4Iq5yuT78_r_WgdKVh9URBw
$ curl -H "Authorization: Bearer $TOKEN" -v localhost:9999/resource-server/
```

### You can also see 

* [BasicSetup-inDB](https://github.com/teyushen/oauth2/tree/BasicSetup-inDB)
* [BasicSetup-inMemory](https://github.com/teyushen/oauth2/tree/BasicSetup-inMemory)
* [useJWT](https://github.com/teyushen/oauth2/tree/useJWT)