package com.example.coursejpa.controller;

import com.example.coursejpa.dto.CourseDto;
import com.example.coursejpa.entity.Course;
import com.example.coursejpa.request.CreateCourse;
import com.example.coursejpa.request.UpdateCourse;
import com.example.coursejpa.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public List<Course> getAllCourse(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String type,
                                     @RequestParam(required = false) String category) {
        return courseService.getAllCourse(name, type, category);
    }

    @GetMapping("example")
    public List<CourseDto> getAllCourse1(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String type,
                                         @RequestParam(required = false) String category) {
        return courseService.getAllCourse1(name, type, category);
    }

    @GetMapping("page")
    public ResponseEntity<Page<Course>> getPageCourse(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Course> list = courseService.getAllCountPage(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findCourseById(@PathVariable Integer id) {
        Course course = courseService.findCourseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    @DeleteMapping("{id}")
    public String deleteCourseById(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return "Xóa thành công!";
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateById(@PathVariable Integer id, @PathVariable UpdateCourse request) {
        Course course = courseService.updateById(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    @PostMapping("")
    public ResponseEntity<?> createCourse(@PathVariable CreateCourse request) {
        Course course = courseService.createCourse(request);
        return ResponseEntity.status(HttpStatus.OK).body(course);
    
    }
}
