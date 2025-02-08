# 일정 관리 앱 calendar

# 개발 환경
<div>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white">
</div>

# API 명세서

![image](https://github.com/user-attachments/assets/2965cfcd-f637-407c-8e0f-c2d3a8ca45dc)

# ERD

![image](https://github.com/user-attachments/assets/024f32f6-ddd2-41c1-afec-48f3ce31596d)

# 기능








## 로그인

**/api/login POST 요청**

<details>
<summary>Request</summary>

```
{
    "email" : "scie429@gmail.com",
    "password" : "1234"
}
```
- email -> 유저 이메
- password -> 비밀번호
    
</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "userId": 1,
    "username": "xx",
    "email": "scie429@gmail.com",
    "createdDate": "2025-02-07 17:12:09",
    "updatedDate": "2025-02-07 17:40:51"
}
```
- userId -> 유저 식별자
- username -> 유저 이름
- email -> 유저 이메일
- createDate -> 유저 생성일
- updatedDate -> 유저 수정일

실패
```
{
    "email": "올바른 이메일 형식이 아닙니다."
}
```
- 400 Bad Request
- 올바른 이메일 형식을 입력해야 됩니다.

```
{
    "message": "해당 이메일로 등록된 유저가 없습니다."
}
```
- 404 Not Found
- 이메일로 등록된 유저가 없으면 에러가 발생합니다.

```
{
    "password": "비밀번호는 필수 입력 값입니다,"
}
```
- 400 Bad Request
- password는 필수로 입력 해야됩니다.

```
{
    "message": "비밀번호가 잘못되었습니다."
}
```
- 401 Unauthorized
- 비밀번호가 틀리면 오류가 발생합니다.
</details>
















## 로그아웃

**/api/logout POST 요청**

<details>
<summary>Request</summary>

- 빈 body로 요청하면 됩니다.
    
</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "message": "로그아웃 되었습니다."
}
```
- 로그아웃이 성공되었다는 메시지를 반환합니다.

실패
- 세션을 지우는 것이므로 실패하지 않습니다.
</details>













## 유저 등록

**/api/users POST 요청**

<details>
  <summary>Request</summary>
  
```
{
    "username" : "tgg",
    "email" : "scie429@gmail.com",
    "password" : "1234"
}
```
- username -> 유저 이름
- email -> 유저 이메일
- password -> 비밀번호

</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "userId": 1,
    "username": "tgg",
    "email": "scie430@gmail.com",
    "createdDate": "2025-02-07 17:04:41",
    "updatedDate": "2025-02-07 17:04:41"
}
```
- userId -> 유저 식별자
- username -> 유저 이름
- email -> 유저 이메일
- createDate -> 유저 생성일
- updatedDate -> 유저 수정일

실패
```
{
    "username": "이름은 필수 입력 값입니다."
}
```
- 400 Bad Request
- username 필수로 입력 해야됩니다.

```
{
    "email": "이메일은 필수 입력 값입니다."
}
```
- 400 Bad Request
- email은 필수로 입력 해야됩니다.

```
{
  "password": "비밀번호는 필수 입력 값입니다."
}
```
- 400 Bad Request
- password는 필수로 입력 해야됩니다.

```
{
    "email": "올바른 이메일 형식이 아닙니다."
}
```
- 400 Bad Request
- 올바른 이메일 형식을 입력 해야됩니다.

```
{
    "message": "이미 사용 중인 이메일입니다."
}
```
- 409 Conflict
- 중복된 이메일을 입력할 수 없습니다.
</details>







## 유저 목록 조회

**/api/users GET 요청**

<details>
<summary>Request</summary>

- api/users GET 요청하면 됩니다.
    
</details>

<details>
<summary>Response</summary>
  
성공
```
[
    {
        "userId": 1,
        "username": "tgg",
        "email": "scie430@gmail.com",
        "createdDate": "2025-02-07 17:04:41",
        "updatedDate": "2025-02-07 17:04:41"
    },
    {
        "userId": 2,
        "username": "tgg2",
        "email": "scie429@gmail.com",
        "createdDate": "2025-02-07 17:05:13",
        "updatedDate": "2025-02-07 17:05:13"
    }
]
```
- userId -> 유저 식별자
- username -> 유저 이름
- email -> 유저 이메일
- createDate -> 유저 생성일
- updatedDate -> 유저 수정일

유저가 없을 경우
```
[]
```
- 유저가 존재하지 않으면 빈 리스트를 반환합니다.
</details>








## 유저 단건 조회

**/api/users/{userId} GET 요청**

<details>
<summary>Request</summary>

- api/users/{userId} GET 요청하면 됩니다.
    
</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "userId": 1,
    "username": "ss",
    "email": "scie430@gmail.com",
    "createdDate": "2025-02-07 17:04:41",
    "updatedDate": "2025-02-07 17:05:57"
}
```
- userId -> 유저 식별자
- username -> 유저 이름
- email -> 유저 이메일
- createDate -> 유저 생성일
- updatedDa -> 유저 수정일

실패
```
{
    "message": "userId에 해당하는 유저가 없습니다."
}
```
- 404 Not Found
- 존재하지 않는 유저에 대해 요청하면 에러가 발생합니다.
</details>








## 유저 수정

**/api/users/{userId} PATCH 요청**

<details>
<summary>Request</summary>

```
{
    "username" : "ss",
    "password" : "123"
}
```
- username -> 유저 이름
- password -> 비밀번호
    
</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "userId": 1,
    "username": "ss",
    "email": "scie430@gmail.com",
    "createdDate": "2025-02-07 17:04:41",
    "updatedDate": "2025-02-07 17:05:57"
}
```
- userId -> 유저 식별자
- username -> 유저 이름
- email -> 유저 이메일
- createDate -> 유저 생성일
- updatedDate -> 유저 수정일

실패
```
{
    "message": "로그인이 필요합니다."
}
```
- 401 Unauthorized
- 로그인하지 않으면 수정 요청을 할 수 없습니다.

```
{
    "message": "유저에 대한 접근 권한이 없습니다."
}
```
- 403 Forbidden
- 로그인한 유저가 아닌 유저에 대한 수정 요청을 할 수 없습니다.

```
{
    "username": "이름은 필수 입력 값입니다."
}
```
- 400 Bad Request
- username은 필수로 입력 해야됩니다.

```
{
    "password": "비밀번호는 필수 입력 값입니다,"
}
```
- 400 Bad Request
- password는 필수로 입력 해야됩니다.

```
{
    "message": "비밀번호가 잘못되었습니다."
}
```
- 401 Unauthorized
- 비밀번호가 틀리면 오류가 발생합니다.
</details>








## 유저 삭제

**/api/users/{userId} POST 요청**

<details>
<summary>Request</summary>

```
{
    "password" : "123"
}
```
- password -> 비밀번호
    
</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "message": "로그아웃 되었습니다."
}
```
- 유저가 삭제되었으므로 자동으로 로그아웃 됩니다.
- 로그아웃이 성공되었다는 메시지를 반환합니다.

실패
```
{
    "message": "로그인이 필요합니다."
}
```
- 401 Unauthorized
- 로그인하지 않으면 삭제 요청을 할 수 없습니다.

```
{
    "message": "유저에 대한 접근 권한이 없습니다."
}
```
- 403 Forbidden
- 로그인한 유저가 아닌 유저에 대한 삭제제 요청을 할 수 없습니다.

```
{
    "password": "비밀번호는 필수 입력 값입니다,"
}
```
- 400 Bad Request
- password는 필수로 입력 해야됩니다.

```
{
    "message": "비밀번호가 잘못되었습니다."
}
```
- 401 Unauthorized
- 비밀번호가 틀리면 오류가 발생합니다.
</details>

























