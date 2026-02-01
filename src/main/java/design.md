API Design Document: Task Management System
1. Danh sách thực thể (Entities)
   User: Đại diện cho người dùng hệ thống.

Task: Đại diện cho công việc, mỗi task bắt buộc gắn với một userId.

2. Quy tắc thiết kế (Naming Conventions)
   Resource: Sử dụng danh từ số nhiều (/users, /tasks).

Path Variables: Sử dụng {id} để xác định tài nguyên cụ thể.

Query Parameters: Sử dụng để lọc (filtering) dữ liệu (?priority=high).

HTTP Methods:

GET: Truy vấn dữ liệu.

POST: Tạo mới tài nguyên.

PATCH: Cập nhật một phần (trạng thái, vai trò).

PUT: Cập nhật toàn bộ hoặc thiết lập mối quan hệ.

DELETE: Xóa tài nguyên.

Method,Endpoint,Mô tả,Body (JSON)
GET,/users,Lấy toàn bộ danh sách người dùng,Không
POST,/users,Tạo mới một người dùng,"{ ""name"": ""string"", ""email"": ""string"", ""role"": ""string"" }"
PATCH,/users/{id}/role,"Cập nhật vai trò (admin, staff,...)","{ ""role"": ""string"" }"
DELETE,/users/{id},Xóa người dùng khỏi hệ thống,Không

Method,Endpoint,Mô tả,Body (JSON)
GET,/tasks,Lấy toàn bộ danh sách công việc,Không
POST,/tasks,Tạo mới một công việc,"{ ""title"": ""string"", ""priority"": ""string"", ""userId"": long }"
PATCH,/tasks/{id}/status,Cập nhật trạng thái task,"{ ""status"": ""string"" }"
DELETE,/tasks/{id},Xóa một công việc,Không

Method,Endpoint,Mô tả
GET,/tasks?priority=high,Tìm các task có mức độ ưu tiên cao
GET,/tasks?priority=high&userId=1,"Tìm task ""high"" của User ID là 1"
GET,/users/{userId}/tasks,Liệt kê toàn bộ công việc của 1 người dùng cụ thể
PUT,/tasks/{taskId}/assign/{userId},Gắn (Assign) công việc cho người dùng

{
"title": "Viết tài liệu API",
"description": "Hoàn thiện file design.md cho dự án",
"priority": "high",
"status": "todo",
"userId": 1
}

{
"status": "in-progress"
}
5. Quy trình thực hiện (Workflow)
   Validation: Trước khi xử lý tại Controller, sử dụng @Valid để kiểm tra dữ liệu đầu vào (ví dụ: title không được để trống).
Mapping: Dùng @PostMapping cho các tác vụ tạo mới và @RequestBody để ánh xạ JSON vào đối tượng DTO (Data Transfer Object).
Foreign Key Logic: Khi tạo Task, hệ thống sẽ kiểm tra userId trong DB. Nếu tồn tại, Task sẽ được lưu với user_id tương ứng.