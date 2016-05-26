# 蓝色像素.EDU.auth-server
## 开启权限验证服务
Inside "auth-server" execute "gradle bootRun"

## 获取一个访问令牌 Access Token
令牌的解码遵循 http://jwt.io 标准,本服务中采用 io.jsonwebtoken / jjwt / 0.6.0 库

### 通过 GrantType password 获取令牌

Execute `curl -u my-trusted-client: http://localhost:8080/oauth/token -d "grant_type=password&username=user&password=testpass"`

### 通过 Client Credentials 获取令牌

Execute `curl -u my-trusted-client-with-secret:somesecret http://localhost:8080/oauth/token -d "grant_type=client_credentials"`

### 通过 authorization code 获取令牌

## 执行 resource server 请求
 使用 Rest GET 请求一个受保护的资源
1. 通过 authorization code or password 获取令牌
2. Execute curl -H "Content-Type: application/json" -H "Authorization: Bearer <your token>" http://localhost:8088/api/me

## 从聚合服务器中获取受保护的资源 aggregation server
采用 Rest GET 请求 aggregation server, 选择用户的 OAuth2 Token 产生出一个向资源服务器的请求
1. 通过 authorization code or password 获取令牌
2. Execute curl -H "Content-Type: application/json" -H "Authorization: Bearer <your token>" http://localhost:8888/api/me
    

## How to create own key store and corresponding public key file

generate keystore 
```
keytool -genkeypair -alias jwt-test -keyalg RSA \
-dname "CN=jwt,OU=jtw,O=jtw,L=zurich,S=zurich,C=CH" \
-keypass TODOchange \ 
-keystore jwt-test.jks \ 
-storepass TODOchange
```

or in one step: export public key
```
keytool -list -rfc --keystore jwt-test.jks | openssl x509 -inform pem -pubkey
```

copy paste public key part into public key file: public.cert
