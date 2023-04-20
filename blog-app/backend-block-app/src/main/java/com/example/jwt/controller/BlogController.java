package com.example.jwt.controller;

import com.example.jwt.entity.Blog;
import com.example.jwt.request.UpsertBlogRequest;
import com.example.jwt.security.IAuthenticationFacade;
import com.example.jwt.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/public")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private IAuthenticationFacade authenticationFacade;


    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("blogs")
    public ResponseEntity<Page<Blog>> getPageCourse(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize
//            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Page<Blog> list = blogService.getAllBlogPage(page, pageSize);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_USER"})
    @GetMapping("blogs/own-blogs")
    public ResponseEntity<Page<Blog>> getAllBlogPageOwnBlogs(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        Page<Blog> list = blogService.getAllBlogPageOwnBlogs(page, pageSize);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("search")
    public ResponseEntity<?> searchBlogs(@RequestParam String term) {
        List<Blog> blogList = blogService.searchBlog(term);
        return new ResponseEntity<>(blogList, new HttpHeaders(), HttpStatus.OK);

    }
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("blog/{id}/{slug}")
    public ResponseEntity<?> searchBlogIdAndSlug(@PathVariable Integer id,@PathVariable String slug) {
        Blog blog = blogService.findBlogByIdAndSlug(id,slug);
        return new ResponseEntity<>(blog, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @GetMapping("blog/{id}")
    public ResponseEntity<?> seachBlogById(@PathVariable Integer id) {
        Blog blog = blogService.findBlogById(id);
        return new ResponseEntity<>(blog, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @PutMapping("blog/{id}")
    public ResponseEntity<?> UpdateBlog(@PathVariable Integer id,@PathVariable UpsertBlogRequest request) {
        String blog = blogService.updateBlogById(id,request);
        return new ResponseEntity<>(blog, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @PostMapping("blog")
    public ResponseEntity<?> createNewBlog(@PathVariable UpsertBlogRequest request) {
        String blog = blogService.createNewBlog(request);
        return new ResponseEntity<>(blog, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("blog/{id}")
    public ResponseEntity<?> deleteBlogById(@PathVariable Integer id) {
        String deleteBlog = blogService.deleteBlog(id);
        return new ResponseEntity<>(deleteBlog, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("blogs/search")
    public ResponseEntity<?> searchBlogs(@RequestParam String keyword,
                                         @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "5") Integer pageSize) {
        Page<Blog> blogList = blogService.searchBlogWithKeyword(keyword,page,pageSize);
        return new ResponseEntity<>(blogList, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }
}
