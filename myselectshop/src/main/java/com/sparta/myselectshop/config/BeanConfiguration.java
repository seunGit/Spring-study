//package com.sparta.myselectshop.config;
//
//import com.sparta.myselectshop.repository.ProductRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration                                                          // 필수 추가
//public class BeanConfiguration {
//    @Bean                                                               // 빈 등록.
//    public ProductRepository productRepository() {
//        String dbUrl = "jdbc:h2:mem:db";
//        String username = "sa";
//        String password = "";
//        return new ProductRepository(dbUrl, username, password);
//    }
//}