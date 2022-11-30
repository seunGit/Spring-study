package com.example.basic_crud_10;

import com.example.basic_crud_10.dto.CourseRequestDto;
import com.example.basic_crud_10.entity.Course;
import com.example.basic_crud_10.repository.CourseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class BasicCrud10Application {

    public static void main(String[] args) {
        SpringApplication.run(BasicCrud10Application.class, args);
    }

    @Resource
    private CourseRepository courseRepository;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            File json = ResourceUtils.getFile("classpath:CourseData.json");

            List<CourseRequestDto> list = new ObjectMapper().readValue(json, new TypeReference<>(){});
            List<Course> courses = list.stream().map(Course::new).collect(Collectors.toCollection(ArrayList::new));

            courseRepository.saveAll(courses);
        };
    }

}
