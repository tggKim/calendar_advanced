# 일정 관리 앱 calendar

# 개발 환경
<div>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white">
</div>

# API 명세서

![image](https://github.com/user-attachments/assets/efffe30c-6ffa-45c2-9cbf-69540dac692f)

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
- email -> 유저 이메일
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

```
{
    "message": "이미 로그인된 상태입니다."
}
```
- 409 Conflict
- 이미 로그인된 상태면 오류가 발생합니다.
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
    "message": "userId에 해당하는 유저가 없습니다."
}
```
- 404 Not Found
- 존재하지 않는 유에 대해 요청하면 에러가 발생합니다.

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
    "message": "userId에 해당하는 유저가 없습니다."
}
```
- 404 Not Found
- 존재하지 않는 유에 대해 요청하면 에러가 발생합니다.

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












## 일정 등록

**/api/schedules POST 요청**

<details>
<summary>Request</summary>
  
```
{
    "title" : "제목",
    "todo" : "할 일"
}
```
- title -> 제목
- todo -> 할 일

</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "scheduleId": 1,
    "userId": 1,
    "username": "tgghuhu",
    "title": "제목",
    "todo": "할 일",
    "commentCount": 0,
    "createdTime": "2025-02-11 19:37:46",
    "updatedTime": "2025-02-11 19:37:46"
}
```
- scheduleId -> 일정 식별자
- userId -> 유저 식별자
- username -> 유저 이름
- title -> 제목
- todo -> 할 일
- commentCount -> 댓글 갯수
- createDate -> 유저 생성일
- updatedDate -> 유저 수정일

실패
```
{
    "message": "로그인이 필요합니다."
}
```
- 401 Unauthorized
- 로그인하지 않으면 일정 등록을 할 수 없습니다.

```
{
    "title": "제목은 필수 입력 값 입니다."
}
```
- 400 Bad Request
- title은 필수로 입력 해야됩니다.

```
{
    "todo": "할 일은 필수 입력 값 입니다."
}
```
- 400 Bad Request
- todo는 필수로 입력 해야됩니다.
</details>










## 일정 목록 조회

**/api/schedules GET 요청**

<details>
<summary>Request</summary>

- api/schedules GET 요청하면 됩니다.
- size, page, sort 파라미터를 통해서 페이징처리를 할 수 있습니다.
    
</details>

<details>
<summary>Response</summary>
  
성공
```
[
    {
        "scheduleId": 1,
        "userId": 1,
        "username": "tgghuhu",
        "title": "제목",
        "todo": "할 일",
        "commentCount": 2,
        "createdTime": "2025-02-11 19:37:46",
        "updatedTime": "2025-02-11 19:37:46"
    },
    {
        "scheduleId": 2,
        "userId": 1,
        "username": "tgghuhu",
        "title": "제목",
        "todo": "할 일",
        "commentCount": 1,
        "createdTime": "2025-02-11 19:39:51",
        "updatedTime": "2025-02-11 19:39:51"
    },
    {
        "scheduleId": 3,
        "userId": 1,
        "username": "tgghuhu",
        "title": "제목",
        "todo": "할 일",
        "commentCount": 0,
        "createdTime": "2025-02-11 19:39:52",
        "updatedTime": "2025-02-11 19:39:52"
    }
]
```
- scheduleId -> 일정 식별자
- userId -> 유저 식별자
- username -> 유저 이름
- title -> 제목
- todo -> 할 일
- commentCount -> 댓글 갯수
- createDate -> 유저 생성일
- updatedDate -> 유저 수정일

일정이 없을 경우
```
[]
```
- 일정이 존재하지 않으면 빈 리스트를 반환합니다.
</details>













## 일정 단건 조회

**/api/schedules/{scheduleId} GET 요청**

<details>
<summary>Request</summary>

- /api/schedules/{scheduleId} GET 요청하면 됩니다.
    
</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "scheduleId": 1,
    "userId": 1,
    "username": "tgghuhu",
    "title": "제목",
    "todo": "할 일",
    "commentCount": 2,
    "createdTime": "2025-02-11 19:37:46",
    "updatedTime": "2025-02-11 19:37:46"
}
```
- scheduleId -> 일정 식별자
- userId -> 유저 식별자
- username -> 유저 이름
- title -> 제목
- todo -> 할 일
- commentCount -> 댓글 갯수
- createDate -> 유저 생성일
- updatedDate -> 유저 수정일

실패
```
{
    "message": "scheduleId에 해당하는 일정이 없습니다."
}
```
- 404 Not Found
- 존재하지 않는 일정에 대해 요청하면 에러가 발생합니다.
</details>


























## 일정 수정

**/api/schedules/{scheduleId} PATCH 요청**

<details>
<summary>Request</summary>

```
{
    "title" : "제목 수정",
    "todo" : "할 일 수정",
    "password" : "1234"
}
```
- title -> 제목
- todo -> 할 일
- password -> 비밀번호호
    
</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "scheduleId": 1,
    "userId": 1,
    "username": "tgghuhu",
    "title": "제목 수정",
    "todo": "할 일 수정",
    "commentCount": 2,
    "createdTime": "2025-02-11 19:37:46",
    "updatedTime": "2025-02-11 19:41:12"
}
```
- scheduleId -> 일정 식별자
- userId -> 유저 식별자
- username -> 유저 이름
- title -> 제목
- todo -> 할 일
- commentCount -> 댓글 갯수
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
    "message": "일정에 대한 접근 권한이 없습니다."
}
```
- 403 Forbidden
- 로그인한 유저의 일정에 대해서만 수정 요청을 할 수 있습니다.

```
{
    "message": "scheduleId에 해당하는 일정이 없습니다."
}
```
- 404 Not Found
- 존재하지 않는 일정에 대해 요청하면 에러가 발생합니다.

```
{
    "title": "제목은 필수 입력 값 입니다."
}
```
- 400 Bad Request
- title은 필수로 입력 해야됩니다.

```
{
    "todo": "할 일은 필수 입력 값 입니다."
}
```
- 400 Bad Request
- todo는 필수로 입력 해야됩니다.

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

















## 일정 삭제

**/api/schedules/{scheduleId} POST 요청**

<details>
<summary>Request</summary>

```
{
    "password" : "1234"
}
```
- password -> 비밀번호
    
</details>

<details>
<summary>Response</summary>
  
성공
- 빈 body를 리턴합니다.

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
    "message": "일정에 대한 접근 권한이 없습니다."
}
```
- 403 Forbidden
- 로그인한 유저의 일정에 대해서만 삭 요청을 할 수 있습니다.

```
{
    "message": "scheduleId에 해당하는 일정이 없습니다."
}
```
- 404 Not Found
- 존재하지 않는 일정에 대해 요청하면 에러가 발생합니다.

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



























## 댓글 등록

**/api/comments POST 요청**

<details>
<summary>Request</summary>
  
```
{
    "content" : "댓글 내용",
    "scheduleId" : "1"
}
```
- content -> 댓글 내용
- scheduleId -> 일정 식별자

</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "commentId": 4,
    "scheduleId": 1,
    "userId": 1,
    "username": "tgghuhu",
    "content": "댓글 내용",
    "createdTime": "2025-02-10 16:49:10",
    "updatedTime": "2025-02-10 16:49:10"
}
```
- commentId -> 댓글 식별자
- scheduleId -> 일정 식별자
- userId -> 유저 식별자
- username -> 유저 이름
- content -> 댓글 내용
- createdDate -> 유저 생성일
- updatedDate -> 유저 수정일

실패
```
{
    "message": "로그인이 필요합니다."
}
```
- 401 Unauthorized
- 로그인하지 않으면 일정 등록을 할 수 없습니다.

```
{
    "message": "scheduleId에 해당하는 일정이 없습니다."
}
```
- 404 Not Found
- 존재하지 않는 일정에 대해 요청하면 에러가 발생합니다.

```
{
    "content": "댓글 내용은 필수 입력 값 입니다."
}
```
- 400 Bad Request
- content 필수로 입력 해야됩니다.

```
{
    "scheduleId": "scheduleId는 필수 입력 값 입니다."
}
```
- 400 Bad Request
- scheduleId는 필수로 입력 해야됩니다.
</details>










## 댓글 목록 조회

**/api/comments GET 요청**

<details>
<summary>Request</summary>

- api/comments GET 요청하면 됩니다.
    
</details>

<details>
<summary>Response</summary>
  
성공
```
[
    {
        "commentId": 1,
        "scheduleId": 1,
        "userId": 1,
        "username": "tgghuhu",
        "content": "댓글 내용",
        "createdTime": "2025-02-10 16:36:30",
        "updatedTime": "2025-02-10 16:36:30"
    },
    {
        "commentId": 2,
        "scheduleId": 1,
        "userId": 1,
        "username": "tgghuhu",
        "content": "댓글 내용",
        "createdTime": "2025-02-10 16:36:30",
        "updatedTime": "2025-02-10 16:36:30"
    },
    {
        "commentId": 3,
        "scheduleId": 1,
        "userId": 1,
        "username": "tgghuhu",
        "content": "댓글 내용",
        "createdTime": "2025-02-10 16:36:31",
        "updatedTime": "2025-02-10 16:36:31"
    },
    {
        "commentId": 4,
        "scheduleId": 1,
        "userId": 1,
        "username": "tgghuhu",
        "content": "댓글 내용",
        "createdTime": "2025-02-10 16:49:10",
        "updatedTime": "2025-02-10 16:49:10"
    }
]
```
- commentId -> 댓글 식별자
- scheduleId -> 일정 식별자
- userId -> 유저 식별자
- username -> 유저 이름
- content -> 댓글 내용
- createdDate -> 유저 생성일
- updatedDate -> 유저 수정일

댓글이 없을 경우
```
[]
```
- 댓글이이 존재하지 않으면 빈 리스트를 반환합니다.
</details>



























## 댓글 단건 조회

**/api/comments/{commentId} GET 요청**

<details>
<summary>Request</summary>

- /api/comments/{commentId} GET 요청하면 됩니다.
    
</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "commentId": 1,
    "scheduleId": 1,
    "userId": 1,
    "username": "tgghuhu",
    "content": "댓글 내용",
    "createdTime": "2025-02-10 17:38:36",
    "updatedTime": "2025-02-10 17:38:36"
}
```
- commentId -> 댓글 식별자
- scheduleId -> 일정 식별자
- userId -> 유저 식별자
- username -> 유저 이름
- content -> 댓글 내용
- createdDate -> 유저 생성일
- updatedDate -> 유저 수정일

실패
```
{
    "message": "commentId에 해당하는 유저가 없습니다."
}
```
- 404 Not Found
- 존재하지 않는 댓글에 대해 요청하면 에러가 발생합니다.
</details>



















## 댓글 수정

**/api/comments/{commentId} PATCH 요청**

<details>
<summary>Request</summary>

```
{
    "content" : "댓글 수정",
    "password" : "1234"
}
```
- content -> 댓글 내용
- password -> 비밀번호
    
</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "commentId": 2,
    "scheduleId": 1,
    "userId": 1,
    "username": "tgghuhu",
    "content": "댓글 수정",
    "createdTime": "2025-02-10 20:58:10",
    "updatedTime": "2025-02-10 20:58:12"
}
```
- commentId -> 댓글 식별자
- scheduleId -> 일정 식별자
- userId -> 유저 식별자
- username -> 유저 이름
- content -> 댓글 내용
- createdDate -> 유저 생성일
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
    "message": "댓글에 대한 접근 권한이 없습니다."
}
```
- 403 Forbidden
- 로그인한 유저의 댓글에 대해서만 수정 요청을 할 수 있습니다.

```
{
    "message": "commentId에 해당하는 유저가 없습니다."
}
```
- 404 Not Found
- 존재하지 않는 댓글에 대해 요청하면 에러가 발생합니다.

```
{
    "content": "댓글 내용은 필수 입력 값 입니다."
}
```
- 400 Bad Request
- content 필수로 입력 해야됩니다.

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






















## 댓글 삭제

**/api/comments/{commentId} POST 요청**

<details>
<summary>Request</summary>

```
{
    "password" : "1234"
}
```
- password -> 비밀번호
    
</details>

<details>
<summary>Response</summary>
  
성공
- 빈 body를 리턴합니다.

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
    "message": "일정에 대한 접근 권한이 없습니다."
}
```
- 403 Forbidden
- 로그인한 유저의 일정에 대해서만 삭 요청을 할 수 있습니다.

```
{
    "message": "commentId에 해당하는 유저가 없습니다."
}
```
- 404 Not Found
- 존재하지 않는 댓글에 대해 요청하면 에러가 발생합니다.

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


<hr>

# 트러블 슈팅

https://velog.io/@tgg/%EC%9D%BC%EC%A0%95-%EA%B4%80%EB%A6%AC-%EC%95%B1-JPA-%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85
