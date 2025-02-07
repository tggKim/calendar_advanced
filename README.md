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
- email -> 유저 이메
- password -> 비밀번호

</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "userId": 1,
    "username": "tgg",
    "email": "scie429@gmail.com",
    "createdDate": "2025-28-07 04:28:19",
    "updatedDate": "2025-28-07 04:28:19"
}
```
- userId -> 유저 식별자
- username -> 유저 이름
- email -> 유저 이메일
- createDate -> 유저 생성일
- updatedDate -> 유 수정일

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
