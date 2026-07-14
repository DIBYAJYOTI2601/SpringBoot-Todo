# API Documentation

**Base URL**

```
http://localhost:8080
```

## Common Headers

For every request with a JSON body, include:

```http
Content-Type: application/json
```

For GET and DELETE requests with no body, no special headers are required.

> **Authentication:** No authentication headers are required. Security is currently open.

---

# User APIs

## 1. Create User

**Endpoint**

```http
POST /api/v1/usercreate
```

**Headers**

```http
Content-Type: application/json
```

**Request Body**

```json
{
  "userName": "admin_user",
  "email": "admin@enterprise.com",
  "passwordHashed": "dummy_hash_value"
}
```

**Example Response**

```json
{
  "id": "0aba90fd-1949-4959-b82b-1ec1b11542b2",
  "userName": "admin_user",
  "email": "admin@enterprise.com",
  "passwordHashed": "dummy_hash_value",
  "createdAt": 1710000000000,
  "updatedAt": 1710000000000
}
```

---

## 2. Get User

**Endpoint**

```http
GET /api/v1/getuser/{userid}
```

**Headers**

None

**Request Body**

None

**Example**

```http
GET /api/v1/getuser/0aba90fd-1949-4959-b82b-1ec1b11542b2
```

---

## 3. Get All Users

**Endpoint**

```http
GET /api/v1/getusers
```

**Headers**

None

**Request Body**

None

---

## 4. Update User

**Endpoint**

```http
PUT /api/v1/userupdate/{userid}
```

**Headers**

```http
Content-Type: application/json
```

**Request Body**

```json
{
  "userName": "admin_updated",
  "email": "admin.updated@enterprise.com",
  "passwordHashed": "new_hash_value"
}
```

**Example**

```http
PUT /api/v1/userupdate/0aba90fd-1949-4959-b82b-1ec1b11542b2
```

---

## 5. Delete User

**Endpoint**

```http
DELETE /api/v1/userdelete/{userid}
```

**Headers**

None

**Request Body**

None

**Example**

```http
DELETE /api/v1/userdelete/0aba90fd-1949-4959-b82b-1ec1b11542b2
```

---

# Task APIs

## 1. Create Task

**Endpoint**

```http
POST /api/v1/createtask/{userid}
```

**Headers**

```http
Content-Type: application/json
```

**Request Body (without tags)**

```json
{
  "title": "Design REST Controllers",
  "description": "Implement CRUD APIs",
  "status": "IN_PROGRESS",
  "priority": "HIGH"
}
```

**Request Body (with tags — optional)**

Tags must already exist. Send only the tag `id` values:

```json
{
  "title": "Design REST Controllers",
  "description": "Implement CRUD APIs",
  "status": "IN_PROGRESS",
  "priority": "HIGH",
  "tags": [
    { "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890" },
    { "id": "b2c3d4e5-f6a7-8901-bcde-f12345678901" }
  ]
}
```

**Example**

```http
POST /api/v1/createtask/0aba90fd-1949-4959-b82b-1ec1b11542b2
```

> **Note:** Do **not** send `user` or `id` in the request body. The `{userid}` path parameter assigns the task owner.
>
> **Tags:** Optional. Omit `tags` to create a task without tags, or include `tags` with existing tag IDs.

---

## 2. Get Task

**Endpoint**

```http
GET /api/v1/gettask/{taskid}
```

**Headers**

None

**Request Body**

None

**Example**

```http
GET /api/v1/gettask/2d618d15-59db-4f18-99ed-062676ab0a06
```

---

## 3. Get Tasks by User

**Endpoint**

```http
GET /api/v1/gettasks/{userid}
```

**Headers**

None

**Request Body**

None

**Example**

```http
GET /api/v1/gettasks/0aba90fd-1949-4959-b82b-1ec1b11542b2
```

---

## 4. Update Task

**Endpoint**

```http
PUT /api/v1/updatetask/{taskid}
```

**Headers**

```http
Content-Type: application/json
```

**Request Body (without changing tags)**

```json
{
  "title": "Design REST Controllers (v2)",
  "description": "Updated description",
  "status": "DONE",
  "priority": "LOW"
}
```

**Request Body (replace tags)**

Send the full tag list you want on the task:

```json
{
  "title": "Design REST Controllers (v2)",
  "description": "Updated description",
  "status": "DONE",
  "priority": "LOW",
  "tags": [
    { "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890" }
  ]
}
```

**Request Body (remove all tags from task)**

```json
{
  "title": "Design REST Controllers (v2)",
  "description": "Updated description",
  "status": "DONE",
  "priority": "LOW",
  "tags": []
}
```

**Example**

```http
PUT /api/v1/updatetask/2d618d15-59db-4f18-99ed-062676ab0a06
```

> **Note:** If `tags` is omitted, existing tags stay unchanged. If `tags` is sent (including `[]`), it replaces the task's current tags.

---

## 5. Delete Task

**Endpoint**

```http
DELETE /api/v1/deletetask/{taskid}
```

**Headers**

None

**Request Body**

None

**Example**

```http
DELETE /api/v1/deletetask/2d618d15-59db-4f18-99ed-062676ab0a06
```

---

## 6. Delete All Tasks for a User

**Endpoint**

```http
DELETE /api/v1/deletetasks/{userid}
```

**Headers**

None

**Request Body**

None

**Example**

```http
DELETE /api/v1/deletetasks/0aba90fd-1949-4959-b82b-1ec1b11542b2
```

---

# Tag APIs

## 1. Create Tag

**Endpoint**

```http
POST /api/v1/createtag
```

**Headers**

```http
Content-Type: application/json
```

**Request Body**

```json
{
  "tagName": "Backend"
}
```

**Example Response**

```json
{
  "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
  "tagName": "Backend"
}
```

---

## 2. Get Tag

**Endpoint**

```http
GET /api/v1/gettag/{tagid}
```

**Headers**

None

**Request Body**

None

**Example**

```http
GET /api/v1/gettag/a1b2c3d4-e5f6-7890-abcd-ef1234567890
```

---

## 3. Get All Tags

**Endpoint**

```http
GET /api/v1/gettags
```

**Headers**

None

**Request Body**

None

---

## 4. Update Tag

**Endpoint**

```http
PUT /api/v1/updatetag/{tagid}
```

**Headers**

```http
Content-Type: application/json
```

**Request Body**

```json
{
  "tagName": "Backend-v2"
}
```

**Example**

```http
PUT /api/v1/updatetag/a1b2c3d4-e5f6-7890-abcd-ef1234567890
```

---

## 5. Delete Tag

**Endpoint**

```http
DELETE /api/v1/deletetag/{tagid}
```

**Headers**

None

**Request Body**

None

**Example**

```http
DELETE /api/v1/deletetag/a1b2c3d4-e5f6-7890-abcd-ef1234567890
```

---

# Allowed Enum Values

| Field      | Allowed Values                |
|------------|-------------------------------|
| `status`   | `TODO`, `IN_PROGRESS`, `DONE` |
| `priority` | `LOW`, `MEDIUM`, `HIGH`       |

---

# Recommended API Flow

1. **Create User** → copy `id` as `{userid}`
2. **Create Tag** → copy `id` as `{tagid}`
3. **Create Task** using `{userid}` with optional `"tags": [{ "id": "{tagid}" }]`
4. **Update Task** to change fields or replace/remove tags via the `tags` field
5. **Get Task** to verify the linked tags

---

# Notes

- Use the `id` returned from **Create User** as `{userid}` in User and Task API requests.
- Use the `id` returned from **Create Task** as `{taskid}` in Task API requests.
- Use the `id` returned from **Create Tag** as `{tagid}` in Tag and Task request bodies.
- Include `Content-Type: application/json` for all POST and PUT requests with a JSON body.
- GET and DELETE requests do not require a request body.
- Authentication is currently disabled; no authorization headers are required.
- Tags are optional on create. On update, omit `tags` to keep them, send `tags` to replace them, or send `"tags": []` to remove all.
