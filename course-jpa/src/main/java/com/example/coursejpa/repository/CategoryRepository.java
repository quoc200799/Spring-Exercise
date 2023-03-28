package com.example.coursejpa.repository;

import com.example.coursejpa.entity.Category;
import com.example.coursejpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(nativeQuery = true, value = "select c.* from course c\n" +
            "            left join course_category cc \n" +
            "            on c.id = cc.course_id \n" +
            "            left join category ct\n" +
            "            on cc.category_id = ct.id\n" +
            "            where (:name is null or c.name = :name)\n"+
            "            and (:type is null or c.type = :type)\n" +
            "            and (:category is null or ct.name = :category)\n" +
            "            group by c.id")
    List<Course>  findCourse(@Param("name") String name,@Param("type")String type,@Param("category")String category);

    @Query("select c from Course c, Category ct where (:name is null or c.name = :name) and (:type is null or c.type = :type) and (:category is null or ct.name = :category)")
    List<Course> findCourseByNameAndTypeAndCat(@Param("name") String name,@Param("type")String type,@Param("category")String category);
}