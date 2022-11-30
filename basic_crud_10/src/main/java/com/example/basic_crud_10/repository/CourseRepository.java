package com.example.basic_crud_10.repository;

import com.example.basic_crud_10.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
