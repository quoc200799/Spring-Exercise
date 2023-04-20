package com.example.jwt.controller;

import com.example.jwt.dto.CategoryDto;
import com.example.jwt.entity.Blog;
import com.example.jwt.entity.Category;
import com.example.jwt.request.UpsertCategoryRequest;
import com.example.jwt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/public")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("categories")
    public ResponseEntity<?> getListCategores() {
        List<CategoryDto> categories = categoryService.categoryDtos();
        return new ResponseEntity<>(categories, new HttpHeaders(), HttpStatus.OK);

    }
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("categories/top5")
    public ResponseEntity<?> getListCategoresTop5() {
        List<CategoryDto> categoriesTop5 = categoryService.categoryDtosTop5();
        return new ResponseEntity<>(categoriesTop5, new HttpHeaders(), HttpStatus.OK);

    }
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("categories/{name}")
    public ResponseEntity<?> searchCategoryByName(@PathVariable String name) {
        List<Blog> blogs = categoryService.findBlogsByCategoryName(name);
        return new ResponseEntity<>(blogs, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @GetMapping("categories/page")
    public ResponseEntity<Page<Category>> getPageCourse(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        Page<Category> list = categoryService.getAllCategoryPage(page, pageSize);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @PostMapping("categories")
    public ResponseEntity<?> createNewCategory(
           @PathVariable UpsertCategoryRequest request
    ) {
        String list = categoryService.createNewCategory(request);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @PutMapping("categories/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable Integer id,
            @PathVariable UpsertCategoryRequest request
    ) {
        String list = categoryService.updateCategory(id,request);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("categories/{id}")
    public ResponseEntity<?> deleteCategory(
            @PathVariable Integer id
    ) {
        String list = categoryService.deleteCategory(id);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
