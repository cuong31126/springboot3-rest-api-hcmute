/**
 * TÊN FILE: ImageController.java
 * CHỨC NĂNG: Controller phục vụ việc tải và hiển thị hình ảnh đã upload lên giao diện HTML / JSP.
 * DÀNH CHO NGƯỜI MỚI:
 * - Khi trình duyệt đọc thẻ <img src="/admin/categories/images/ten_anh.jpg">,
 *   nó sẽ gửi một request GET đến server.
 * - Controller này nhận tên file, gọi storageService.loadAsResource(filename)
 *   để đọc file từ thư mục "uploads" và bắn dữ liệu ảnh về cho trình duyệt hiển thị.
 */
package vn.iotstar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.iotstar.service.IStorageService;

@Controller
public class ImageController {

    @Autowired
    private IStorageService storageService;

    /**
     * Endpoint hiển thị ảnh cho Categories
     * URL: /admin/categories/images/{filename}
     */
    @GetMapping("/admin/categories/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveCategoryFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    /**
     * Endpoint hiển thị ảnh cho Products
     * URL: /admin/products/images/{filename}
     */
    @GetMapping("/admin/products/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveProductFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }
}
