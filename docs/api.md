# API 명세서 - 상세 버전

## 개요
스파르타 일정 관리 시스템 API 명세서입니다.

**Base URL:** `http://localhost:8080`

---

## 📋 일정(Schedule) API

### 1. 일정 생성
- **Method:** `POST`
- **URL:** `/schedules`
- **Description:** 새로운 일정을 생성합니다.

**Request:**
```json
{
    "title": "팀 회의",
    "content": "주간 팀 회의 진행"
}
```

**Response (201 OK):**
```json
{
    "id": 1,
    "title": "팀 회의",
    "content": "주간 팀 회의 진행",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
}
```

### 2. 일정 전체 조회
- **Method:** `GET`
- **URL:** `/schedules`
- **Description:** 모든 일정을 조회합니다.

**Response (200 OK):**
```json
[
    {
        "id": 1,
        "title": "팀 회의",
        "content": "주간 팀 회의 진행",
        "createdAt": "2024-01-15T10:30:00",
        "updatedAt": "2024-01-15T10:30:00"
    },
    {
        "id": 2,
        "title": "프로젝트 리뷰",
        "content": "분기별 프로젝트 검토",
        "createdAt": "2024-01-14T14:20:00",
        "updatedAt": "2024-01-14T14:20:00"
    }
]
```

### 3. 일정 단건 조회
- **Method:** `GET`
- **URL:** `/schedules/{id}`
- **Description:** 특정 ID의 일정을 조회합니다.

**Path Parameters:**
- `id`: 일정 ID

**Request Example:**
```
GET /schedules/1
```

**Response (200 OK):**
```json
{
    "id": 1,
    "title": "팀 회의",
    "content": "주간 팀 회의 진행",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00",
    "comments": [
        {
            "id": 1,
            "content": "참석하겠습니다",
            "author": "김철수",
            "createdAt": "2024-01-15T11:00:00"
        }
    ]
}
```

### 4. 일정 수정
- **Method:** `PATCH`
- **URL:** `/schedules/{id}`
- **Description:** 일정의 제목이나 내용을 수정합니다.

**Path Parameters:**
- `id`: 일정 ID

**Request:**
```json
{
    "title": "홍길동",
    "content": "수정된 팀 회의"
}
```
```json
{
    "title": "수정된 팀 회의"
}
```

**Response (200 OK):**
```json
{
    "id": 1,
    "name": "홍길동",
    "title": "수정된 팀 회의",
    "content": "주간 팀 회의 진행",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T15:45:00"
}
```

### 5. 일정 삭제
- **Method:** `DELETE`
- **URL:** `/schedules/{id}`
- **Description:** 특정 일정을 삭제합니다.

**Path Parameters:**
- `id`: 일정 ID

**Request:**
```json
{
    "password": "password123"
}
```

**Response (204 OK):**
```json
1
```

### 6. 일정 페이징 조회
- **Method:** `GET`
- **URL:** `/schedules/page`
- **Description:** 페이징 처리된 일정 목록을 조회합니다.

**Response (200 OK):**
```json
{
    "content": [
        {
            "id": 1,
            "commentCount": "1",
            "title": "팀 회의",
            "content": "주간 팀 회의 진행",
            "createdAt": "2024-01-15T10:30:00",
            "updatedAt": "2024-01-15T10:30:00",
            "name": "홍길동"
        }
    ],
    "totalElements": 20,
    "totalPages": 4,
    "size": 5,
    "number": 0,
    "first": true,
    "last": false
}
```

---

## 👤 사용자(User) API

### 1. 사용자 생성
- **Method:** `POST`
- **URL:** `/user`
- **Description:** 새로운 사용자를 생성합니다.
- 비밀번호는 영문한개 숫자 한개 이상 포함한 2글자 이상이어야 합니다.

**Request:**
```json
{
    "name": "홍길동",
    "email": "hong@example.com",
    "password": "password123"
}
```

**Response (201 Created):**
```json
{
    "id": 1,
    "name": "홍길동",
    "email": "hong@example.com",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
}
```

### 2. 사용자 전체 조회
- **Method:** `GET`
- **URL:** `/user`
- **Description:** 모든 사용자 목록을 조회합니다.

**Response (200 OK):**
```json
[
    {
        "id": 1,
        "name": "홍길동",
        "email": "hong@example.com",
        "createdAt": "2024-01-15T10:30:00",
        "updatedAt": "2024-01-15T10:30:00"
    },
    {
        "id": 2,
        "name": "김철수",
        "email": "kim@example.com",
        "createdAt": "2024-01-14T09:15:00",
        "updatedAt": "2024-01-14T09:15:00"
    }
]
```

### 3. 사용자 단건 조회
- **Method:** `GET`
- **URL:** `/user/{id}`
- **Description:** 특정 사용자 정보를 조회합니다.

**Path Parameters:**
- `id`: 사용자 ID

**Request Example:**
```
GET /users/1
```

**Response (200 OK):**
```json
{
    "id": 1,
    "name": "홍길동",
    "email": "hong@example.com",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
}
```

### 4. 사용자 정보 수정
- **Method:** `PATCH`
- **URL:** `/user/me`
- **Description:** 사용자 정보를 수정합니다.
- 이메일이나 이름 중 수정할 정보 하나 이상은 작성해야 합니다.
- 현재 로그인 한 사람의 비밀번호를 입력해야 수정이 가능합니다.

**Request:**
```json
{
    "name": "김홍길동",
    "password": "password123"
}
```

**Response (200 OK):**
```json
{
    "id": 1,
    "name": "김홍길동",
    "email": "hong@example.com",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T16:20:00"
}
```

### 5. 사용자 삭제
- **Method:** `DELETE`
- **URL:** `/users/me`
- **Description:** 사용자를 삭제합니다.

**Request:**
```json
{
    "password": "password123"
}
```
**Response (204 OK):**
```json
1
```

---

## 🔐 인증 API

### 1. 로그인
- **Method:** `POST`
- **URL:** `/login`
- **Description:** 사용자 로그인을 처리합니다.

**Request:**
```json
{
    "email": "hong@example.com",
    "password": "password123"
}
```

**Response (200 OK):**
```json
{
        "id": 1,
        "name": "홍길동",
        "email": "hong@example.com"
}
```

### 2. 로그아웃
- **Method:** `POST`
- **URL:** `/logout`
- **Description:** 사용자 로그아웃을 처리합니다.

**Response (200 OK):**
```json
{
    "message": "로그아웃 성공"
}
```

---

## 💬 댓글(Comment) API

### 1. 댓글 생성
- **Method:** `POST`
- **URL:** `/schedules/{scheduleId}/comment`
- **Description:** 특정 일정에 댓글을 생성합니다.

**Path Parameters:**
- `scheduleId`: 일정 ID

**Request:**
```json
{
    "content": "좋은 일정이네요!"
}
```

**Response (201 Created):**
```json
{
    "id": 1,
    "scheduleId": 9,
    "userId": 14,
    "content": "좋은 일정이네요!",
    "createdAt": "2024-01-15T11:00:00",
    "updatedAt": "2024-01-15T11:00:00"
}
```

### 2. 일정별 댓글 조회
- **Method:** `GET`
- **URL:** `/schedules/{scheduleId}/comment`
- **Description:** 특정 일정의 모든 댓글을 조회합니다.

**Path Parameters:**
- `scheduleId`: 일정 ID

**Request Example:**
```
GET /schedules/7/comment
```

**Response (200 OK):**
```json
[
    {
        "commentId": 14,
        "scheduleId": 9,
        "userId": 14,
        "content": "좋은 일정이네요!",
        "createdAt": "2024-01-15T11:00:00",
        "updatedAt": "2024-01-15T11:00:00"
    },
    {
        "commentId": 15,
        "scheduleId": 9,
        "userId": 15,
        "content": "참석하겠습니다.",
        "createdAt": "2024-01-15T11:30:00",
        "updatedAt": "2024-01-15T11:30:00"
    }
]
```

### 3. 댓글 수정
- **Method:** `PATCH`
- **URL:** `/schedules/{scheduleId}/comment/{commentId}`
- **Description:** 특정 댓글을 수정합니다.

**Path Parameters:**
- `scheduleId`: 일정 ID
- `commentId`: 댓글 ID

**Request:**
```json
{
    "content": "수정된 댓글 내용입니다."
}
```

**Response (200 OK):**
```json
{
    "commentId": 15,
    "scheduleId": 9,
    "userId": 15, 
    "content": "수정된 댓글 내용입니다.",
    "createdAt": "2024-01-15T11:00:00",
    "updatedAt": "2024-01-15T15:30:00"
}
```

### 4. 댓글 삭제
- **Method:** `DELETE`
- **URL:** `/schedules/{scheduleId}/comment/{commentId}`
- **Description:** 특정 댓글을 삭제합니다.

**Path Parameters:**
- `scheduleId`: 일정 ID
- `commentId`: 댓글 ID

**Response (204 OK):**
```json
1
```

---
## 📝 성공 응답 예시

```json
{
    "status": 200,
    "code": "SUC-001",
    "message": "요청이 성공적으로 처리되었습니다.",
    "data": {
        "userId": 4,
        "name": "이름이름이름",
        "email": "메일@메일1",
        "createdAt": "2025-08-11T14:47:23.671032",
        "modifiedAt": "2025-08-11T14:47:23.671032"
    },
    "path": "/user/4",
    "timestamp": "2025-08-14T01:09:27.282102800"
}

```

## 📝 에러 응답 예시

### 400 Bad Request - 입력값 오류
```json
{
    "status": 400,
    "code": "VAL-001",
    "message": "입력값이 유효하지 않습니다",
    "data": null,
    "path": "/schedules",
    "timestamp": "2024-01-15T10:30:00"
}
```

---

## ⚠️ 주의사항

### 헤더 설정
```
Content-Type: application/json
Accept: application/json
```

### 세션 인증 필요 API
- 일정 생성, 조회, 수정, 삭제
- 사용자 정보 수정, 조회, 삭제
- 댓글 생성, 조회, 수정, 삭제

### 세션 관리
- 로그인 후 반환되는 `JSESSIONID` 쿠키를 모든 요청에 포함
- 세션 만료 시간: 30분 (비활성 상태 기준)
