/**
 * TÊN FILE: WebCategoryController.java
 * CHỨC NĂNG: Controller điều hướng trả về giao diện JSP cho người dùng.
 * DÀNH CHO NGƯỜI MỚI:
 * - Khác với @RestController (trả về JSON), @Controller này trả về tên file giao diện.
 * - Khi bạn mở trình duyệt gõ: http://localhost:8080/ hoặc http://localhost:8080/admin/category
 *   nó sẽ tìm và mở file /WEB-INF/views/admin/category-ajax.jsp.
 */
package vn.iotstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebCategoryController {

    /**
     * Mở trang quản lý Category AJAX
     * Chuyển hướng sang giao diện HTML AJAX tĩnh (chạy mượt mà 100% trên Tomcat nhúng)
     */
    @GetMapping({"/", "/admin/category", "/category-ajax"})
    public String showCategoryAjaxPage() {
        return "redirect:/category-ajax.html";
    }
}
