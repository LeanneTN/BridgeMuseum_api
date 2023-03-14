# BridgeMuseum_api

## API Document

### User
#### login
```
method: put

url: /user/user

requestParam:
String username,
String password
```

---
### register
```
method: post
url: /user/user
requestBody:
User{
    private Long id;
    private String name;
    private String password;
    private String phone;
    private Integer role;
    private String province;
    private String city;
    private String address;
}
```
---
### get users list
```
method: get
url: /user/users
requestBody:
User{
    private Long id;
    private String name;
    private String password;
    private String phone;
    private Integer role;
    private String province;
    private String city;
    private String address;
}
```
---
### get user detail information by user id
```
method: get
url: /user/id/{id}
```