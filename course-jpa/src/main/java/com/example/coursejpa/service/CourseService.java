package com.example.coursejpa.service;

import com.example.coursejpa.dto.CourseDto;
import com.example.coursejpa.entity.Category;
import com.example.coursejpa.entity.Course;
import com.example.coursejpa.entity.User;
import com.example.coursejpa.exception.BadRequestException;
import com.example.coursejpa.exception.NotFoundException;
import com.example.coursejpa.repository.CategoryRepository;
import com.example.coursejpa.repository.CourseRepository;
import com.example.coursejpa.repository.UserRepository;
import com.example.coursejpa.request.CreateCourse;
import com.example.coursejpa.request.UpdateCount;
import com.example.coursejpa.request.UpdateCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Course> findCourse() {
        return courseRepository.findAll();
    }

    public Course findCourseById(Integer id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            return courseOptional.get();
        } else {
            throw new RuntimeException("Not found id");
        }
    }

    public List<Course> getAllCourse(String name, String type, String category) {
        return courseRepository.findCourseDemo1(name, type, category);
    }

    public List<CourseDto> getAllCourse1(String name, String type, String category) {
        return courseRepository.findCourseDemo(name, type, category);
    }

//    public Page<Course> findStudentByName_Native() {
//        Page<Course> page = courseRepository
//                .findAll(PageRequest.of(0,5));
//       return page;
//    }

    public Page<Course> getAllCountPage(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Course> pagedResult = courseRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            throw new BadRequestException("Not found Page");
        }
    }

    public void deleteCourse(Integer id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            courseRepository.delete(course);

        } else {
            throw new RuntimeException("Not found id");
        }
    }

    public Course updateById(Integer id, UpdateCourse request) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            Optional<User> userOptional = userRepository.findById(request.getUser_id());
            User user;
            List<Category> categoryList = new ArrayList<>();
            if (userOptional.isPresent()) {
                user = userOptional.get();
            } else {
                throw new RuntimeException("Not found user id");
            }
            request.getCategoryId().forEach(e -> {
                Category categoryRequest = category(e);
                categoryList.add(categoryRequest);
            });

            Course course = courseOptional.get();
            course.setName(request.getName());
            course.setDescription(request.getDescription());
            course.setPrice(request.getPrice());
            course.setThumbnail(request.getThumbnail());
            course.setType(request.getType());
            course.setUser(user);
            course.setCategories(categoryList);
            courseRepository.save(course);
            return course;
        } else {
            throw new RuntimeException("Not found id");
        }
    }

    public Category category(Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        } else {
            throw new RuntimeException("Not found category id");
        }
    }

    public User findUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("Not found user id");
        }
    }

    public Course createCourse(CreateCourse request) {
        List<Category> categoryList = new ArrayList<>();
        User user = findUserById(request.getUser_id());
        request.getCategoryId().forEach(e -> {
            Category categoryRequest = category(e);
            categoryList.add(categoryRequest);
        });
        Course course = Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .type(request.getType())
                .thumbnail(request.getThumbnail())
                .price(request.getPrice())
                .rating(request.getRating())
                .user(user)
                .categories(categoryList)
                .build();
        courseRepository.save(course);
        return course;
    }
}
