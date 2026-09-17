# BÀI TẬP CÁ NHÂN: RESTFUL API TRONG SPRING BOOT 3 & RENDER AJAX
> **Môn học**: Lập trình Web (WEBPR330479) - Trường ĐH Sư Phạm Kỹ Thuật TP.HCM (HCMUTE)  
> **Giảng viên**: ThS. Nguyễn Hữu Trung  
> **Công nghệ**: Spring Boot 3.3.4, Java 21, Spring Data JPA, Microsoft SQL Server, SpringDoc OpenAPI 3, jQuery 3.6.4, Bootstrap 5.

---

## 📌 Tóm tắt ngắn gọn những gì đã hoàn thành trong dự án

Dự án đã thực hiện đầy đủ 4 yêu cầu bài tập cá nhân từ giảng viên:

1. **Kiến trúc RESTful API chuẩn (Theo bài giảng [UTELMS 1452697](https://utexlms.hcmute.edu.vn/mod/page/view.php?id=1452697))**:
   * Định dạng chuẩn phản hồi `Response.java` gồm 3 trường: `{ status: Boolean, message: String, body: Object }`.
   * Sử dụng đầy đủ các phương thức HTTP: `GET`, `POST`, `PUT`, `DELETE` với mã phản hồi `200 OK`, `400 BAD_REQUEST`, `404 NOT_FOUND`.

2. **CRUD API Category kèm File Upload (Theo hướng dẫn [CRUD API Category](https://utexlms.hcmute.edu.vn/pluginfile.php/1977112/mod_resource/content/1/H%C6%AF%E1%BB%9ANG%20D%E1%BA%AAN%20CRUD%20API%20CATEGORY%20TR%C3%8AN%20SPRING%20BOOT%203.pdf))**:
   * Xây dựng Entity `Category` liên kết 1-N với `Product` qua `jakarta.persistence.*` và `@JsonIgnore`.
   * Tầng Service `FileSystemStorageServiceImpl` lưu trữ file ảnh vật lý vào thư mục `uploads/` có chống path-traversal và sinh mã UUID.
   * `CategoryAPIController`: Hỗ trợ upload ảnh icon mới hoặc giữ nguyên icon cũ khi sửa.

3. **Cấu hình Swagger 3 / OpenAPI (Theo hướng dẫn [Swagger 3](https://utexlms.hcmute.edu.vn/pluginfile.php/1977115/mod_resource/content/1/C%E1%BA%A4U%20H%C3%8CNH%20SWAGGER2%20V%C3%80%20SWAGGER%203%20TR%C3%8AN%20SPRING%20BOOT.pdf))**:
   * Tích hợp `springdoc-openapi-starter-webmvc-ui` (chuẩn OpenAPI 3 cho Spring Boot 3 & Jakarta EE thay cho Springfox đã lỗi thời).
   * Tạo class cấu hình `OpenApiConfig.java` cho phép test trực quan mọi API tại: `http://localhost:8080/swagger-ui/index.html`.

4. **Giao diện Client gọi AJAX CRUD trên cả Bảng Category và Product (Theo hướng dẫn [AJAX với RESTful API](https://utexlms.hcmute.edu.vn/pluginfile.php/1977118/mod_resource/content/1/H%C6%AF%E1%BB%9ANG%20D%E1%BA%AAN%20AJAX%20V%E1%BB%9AI%20RESTFUL%20API%20TRONG%20SPRING%20BOOT.pdf))**:
   * **Giao diện Category AJAX**: Hiển thị bảng, ảnh icon, Modal Bootstrap Thêm mới, Modal Sửa, nút Xóa kèm hiệu ứng mờ dần `fadeOut`.
   * **Giao diện Product AJAX**: Hiển thị bảng sản phẩm, giá tiền, giảm giá, tồn kho, chọn danh mục liên kết, Thêm/Xóa sản phẩm.
   * **Thanh điều hướng nhanh**: Hỗ trợ chuyển đổi qua lại mượt mà giữa Category và Product ngay trên thanh menu.

---

## 🚀 Hướng dẫn Clone về máy và Chạy dự án

### Yêu cầu hệ thống:
* **Java SDK**: Phiên bản 21 (hoặc 17+)
* **Maven**: 3.8+ (hoặc dùng Maven tích hợp sẵn trong IDE)
* **Hệ quản trị CSDL**: Microsoft SQL Server (đang bật cổng mặc định 1433)
* **IDE khuyên dùng**: Spring Tool Suite (STS) 4 / Eclipse / IntelliJ IDEA / VS Code

---

### Bước 1: Clone mã nguồn về máy
Mở Terminal / Command Prompt và chạy:
```bash
git clone <URL_REPO_CUA_BAN>
cd vietapibt1
```

---

### Bước 2: Tạo Cơ sở dữ liệu & Dữ liệu mẫu (SQL Server)
* Mở **SQL Server Management Studio (SSMS)** hoặc công cụ dòng lệnh `sqlcmd`.
* Mở file **`VietApiDb.sql`** (đã có sẵn ở thư mục gốc dự án) và bấm **Execute**.
* Script này sẽ tự động:
  1. Tạo Database `VietApiDb`.
  2. Tạo 2 bảng `Categories` và `Products` với khóa ngoại liên kết.
  3. Chèn sẵn 5 danh mục mẫu và 6 sản phẩm mẫu.

*(Nếu dùng dòng lệnh command line: `sqlcmd -S localhost -U sa -P caucuong -C -i VietApiDb.sql`)*

---

### Bước 3: Cấu hình kết nối CSDL (Nếu cần)
Mở file `src/main/resources/application.properties` và kiểm tra lại tài khoản SQL Server của máy bạn:
```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=VietApiDb;encrypt=true;trustServerCertificate=true;
spring.datasource.username=sa
spring.datasource.password=caucuong
```
*(Hãy đổi `password=caucuong` thành mật khẩu SQL Server của bạn nếu khác).*

---

### Bước 4: Khởi chạy ứng dụng

#### Cách 1: Chạy bằng dòng lệnh (Terminal):
```powershell
mvn spring-boot:run
```

#### Cách 2: Chạy trên Spring Tool Suite (STS) / Eclipse:
1. Chọn **File -> Open Projects from File System...** -> chọn thư mục `vietapibt1`.
2. Nhấp chuột phải vào dự án -> chọn **Run As -> Spring Boot App**.

---

## 🌐 Các đường dẫn kiểm thử dự án:

| Chức năng | Đường dẫn URL | Mô tả |
| :--- | :--- | :--- |
| **Trang chủ & Category AJAX** | [http://localhost:8080/](http://localhost:8080/) hoặc [/category-ajax.html](http://localhost:8080/category-ajax.html) | Quản lý Danh mục (CRUD không reload trang bằng AJAX) |
| **Trang Product AJAX** | [http://localhost:8080/product-ajax.html](http://localhost:8080/product-ajax.html) | Quản lý Sản phẩm (CRUD AJAX + Chọn Category) |
| **Tài liệu Swagger 3 UI** | [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) | Trang giao diện tương tác và test toàn bộ REST API |
| **API Category Endpoint** | [http://localhost:8080/api/category](http://localhost:8080/api/category) | Trả về JSON toàn bộ danh mục |
| **API Product Endpoint** | [http://localhost:8080/api/product](http://localhost:8080/api/product) | Trả về JSON toàn bộ sản phẩm |

---
*Tác giả bài tập: Sinh viên HCMUTE - Năm học 2026*
