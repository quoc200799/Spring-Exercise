package com.example.jwt.service;

import com.example.jwt.entity.Blog;
import com.example.jwt.entity.Category;
import com.example.jwt.entity.User;
import com.example.jwt.repository.BlogRepository;
import com.example.jwt.repository.CategoryRepository;
import com.example.jwt.request.UpsertBlogRequest;
import com.example.jwt.security.IAuthenticationFacade;
import com.example.jwt.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    public Page<Blog> getAllBlogPage(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Blog> pagedResult = blogRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null;
        }
    }

    public List<Blog> searchBlog(String term) {
        return blogRepository.findByTitleIgnoreCase(term);

//        if (blogs != null) {
//            return blogs;
//        } else {
//            throw new RuntimeException("Not found Page");
//        }
    }

    public Blog findBlogByIdAndSlug(Integer id, String slug) {
        Optional<Blog> blog = blogRepository.findByIdAndSlug(id, slug);
        return blog.orElseThrow(() -> {
            throw new RuntimeException("Not found id or slug");
        });
    }

    public Blog findBlogById(Integer id) {
        return blogRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Not found blog id");
        });
    }

    public String createNewBlog(UpsertBlogRequest request) {
        valadation(request);
        List<Category> categoryList = findCategoriesById(request.getCategoryIds());
        Blog blog = Blog.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .content(request.getContent())
                .thumbnail(request.getThumbnail())
                .status(request.getStatus())
                .categories(categoryList)
                .build();
        blogRepository.save(blog);
        return "Thêm mới thành công";
    }

    public String updateBlogById(Integer id, UpsertBlogRequest request) {
        Blog blog = findBlogById(id);
        valadation(request);
        List<Category> categoryList = findCategoriesById(request.getCategoryIds());
        blog.setCategories(categoryList);
        blog.setStatus(request.getStatus());
        blog.setContent(request.getContent());
        blog.setDescription(request.getDescription());
        blog.setThumbnail(request.getThumbnail());
        blog.setTitle(request.getTitle());
        blogRepository.save(blog);
        return "Cập nhật thành công";
    }

    private void valadation(UpsertBlogRequest request) {
        if (request.getTitle().trim().equals("")) {
            throw new RuntimeException("Title không được để trống");

        }
        if (request.getDescription().trim().equals("")) {
            throw new RuntimeException("description không được để trống");

        }
        if (request.getThumbnail().trim().equals("")) {
            throw new RuntimeException("thumbnail không được để trống");

        }
        if (request.getCategoryIds().size() == 0) {
            throw new RuntimeException("category không được để trống");
        }
    }

    public List<Category> findCategoriesById(List<Integer> categoriesId) {
        List<Category> categoryList = new ArrayList<>();
        for (Integer integer : categoriesId) {
            Optional<Category> categoryOptional = categoryRepository.findById(integer);
            if (categoryOptional.isPresent()) {
                categoryList.add(categoryOptional.get());
            } else {
                throw new RuntimeException("Not found user id");
            }
        }
        return categoryList;
    }

    public String deleteBlog(Integer id) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            blogRepository.delete(blog);
            return "Xóa thành công";
        } else {
            throw new RuntimeException("Not found id");
        }
    }

    public Page<Blog> searchBlogWithKeyword(String keyword, Integer page, Integer pageSize) {
        Pageable paging = PageRequest.of(page, pageSize);

        Page<Blog> pagedResult = blogRepository.findByTitle(keyword, paging);

        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null;
        }
    }

    public Page<Blog> getAllBlogPageOwnBlogs(Integer page, Integer pageSize) {
        Pageable paging = PageRequest.of(page, pageSize);
        String user = authenticationFacade.getAuthentication().getName();
        Page<Blog> pagedResult = blogRepository.findByUser_Email(user, paging);

        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null;
        }
    }
}
