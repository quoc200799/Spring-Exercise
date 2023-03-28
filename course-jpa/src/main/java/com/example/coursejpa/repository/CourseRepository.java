package com.example.coursejpa.repository;

import com.example.coursejpa.dto.CourseDto;
import com.example.coursejpa.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(nativeQuery = true, name = "findUserDemo")
    List<CourseDto> findCourseDemo(@Param("name") String name,
                                   @Param("type") String type,
                                   @Param("category") String category);

    @Query("select c " +
            "from Course c, Category ct " +
            "where (:name is null or c.name = :name) " +
            "and (:type is null or c.type = :type) " +
            "and (:category is null or ct.name = :category)")
    List<Course> findCourseDemo1(@Param("name") String name,
                                 @Param("type") String type,
                                 @Param("category") String category);

    // Sử dụng Native query
//    @Query(
//            value = "select * from Course s where upper(s.name) like upper(concat('%', ?1, '%'))",
//            countQuery = "select count(s.id) from Course s where upper(s.name) like upper(concat('%', ?1, '%'))",
//            nativeQuery = true
//    )
//    Page<Course> findByNameContainsIgnoreCase_NativeQuery(Pageable pageable);

}