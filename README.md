# 📅 Schedule API Project

Spring Boot 기반 **일정 관리 REST API** 프로젝트입니다.  
기본 CRUD(생성 / 조회 / 수정 / 삭제)를 제공하는 구조로 설계되었습니다.

---

## 🎯 프로젝트 목표

- REST API 기본 구조 이해
- 3-Layer Architecture (Controller → Service → Repository)
- DTO 분리 설계

---

## 📦 프로젝트 구조

```text
com.example.scheduleproject
 ├─ controller
 │   └─ ScheduleController
 ├─ dto
 │   ├─ ScheduleCreateRequest
 │   ├─ ScheduleCreateResponse
 │   ├─ ScheduleDeleteRequest
 │   └─ ScheduleDeleteResponse
 │   └─ ScheduleGetResponse
 │   └─ ScheduleUpdateRequest
 │   └─ ScheduleUpdateResponse
 ├─ entity
 │   └─ BaseEntity
 │   └─ Schedule
 ├─ repository
 │   └─ ScheduleRepository
 ├─ service
 │   └─ ScheduleService
 └─ ScheduleProjectApplication
```

---

## 🧱 ERD

<img width="385" height="213" alt="ERD" src="https://github.com/user-attachments/assets/b67b5b0d-a49f-449e-a064-793a44f58922" />

---

## 🧩 구현 기능

- ✅ 일정 생성 API
- ✅ 일정 전체/단건 조회 API
- ✅ 일정 수정 API
- ✅ 일정 삭제 API

---

## 📋 API 명세

### 1️⃣ 일정 생성

| 구분 | 내용 |
|----|----|
| Method | POST |
| URL | /schedules |
| Description | 새로운 일정을 생성한다. |
| Body | title, content, writer, password |  

### 2️⃣ 일정 전체 조회

| 구분 | 내용 |
|----|----|
| Method | GET |
| URL | /schedules |
| Description | 등록된 모든 일정을 최신 수정일 기준으로 조회한다. |  

### 3️⃣ 일정 단건 조회

| 구분 | 내용 |
|----|----|
| Method | GET |
| URL | /schedules/{id} |
| Description | 선택한 일정 하나를 조회한다. |

### 4️⃣ 일정 수정 (비밀번호 검증)

| 구분 | 내용 |
|----|----|
| Method | PUT |
| URL | /schedules/{id} |
| Description | 비밀번호 검증 후 제목과 내용을 수정한다. |
| Body | title, content, writer, password |

### 5️⃣ 일정 삭제 (비밀번호 검증)

| 구분 | 내용 |
|----|----|
| Method | DELETE |
| URL | /schedules/{id} |
| Description | 비밀번호 검증 후 선택한 일정을 삭제한다. |
| Body | password |
