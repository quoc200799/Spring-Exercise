package com.example.jwt.service;

import com.example.jwt.dto.CategoryDto;
import com.example.jwt.entity.Blog;
import com.example.jwt.entity.Category;
import com.example.jwt.repository.BlogRepository;
import com.example.jwt.repository.CategoryRepository;
import com.example.jwt.request.UpsertCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BlogRepository blogRepository;

    public List<CategoryDto> categoryDtos() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        categories.forEach(e -> {
            CategoryDto categoryDto = CategoryDto.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .used(blogRepository.countByCategories_Id(e.getId()))
                    .build();
            categoryDtos.add(categoryDto);
        });
        return categoryDtos;
    }

    public List<CategoryDto> categoryDtosTop5() {
        Comparator<CategoryDto> categoryDtoComparator = Comparator
                .comparing(CategoryDto::getUsed);
        List<CategoryDto> categoryDtos = categoryDtos().stream()
                .sorted(categoryDtoComparator).toList();
        List<CategoryDto> categoryDtosTop5 = new ArrayList<>();
        int sizeCate = categoryDtos.size();
        for (int i = 0; i < Math.min(sizeCate, 5); i++) {
            categoryDtosTop5.add(categoryDtos.get(i));
        }
        return categoryDtosTop5;
    }

    public List<Blog> findBlogsByCategoryName(String name) {
        List<Blog> blogList = blogRepository.findByCategories_NameIgnoreCase(name);
//        if(blogList.size() == 0){
////            throw  new RuntimeException("Blog not found");
//            return b
//        }
        return blogList;
    }
    public Page<Category> getAllCategoryPage(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Category> pagedResult = categoryRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null;
        }
    }

    public String createNewCategory(UpsertCategoryRequest request) {
        validation(request);
        Category category = Category.builder()
                .name(request.getName())
                .build();
        categoryRepository.save(category);
        return "Thêm mới thành công!";
    }

    public String updateCategory(Integer id,UpsertCategoryRequest request) {
        validation(request);
        Category category = categoryRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("not found id!");
        });
        category.setName(request.getName());
        categoryRepository.save(category);
        return "Thêm mới thành công!";
    }

    private void validation(UpsertCategoryRequest request) {
        if(request.getName().trim().equals("")){
            throw new RuntimeException("Name không được để trống");
        }
    }

    public String deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("not found id!");
        });
        categoryRepository.delete(category);
        return "Xóa thành công!";
    }
}
