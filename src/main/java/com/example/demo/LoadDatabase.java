package com.example.demo;

import com.example.demo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository) {

        return args -> {

            studentRepository.save(new Student("Patricia", "Costa", 19, true, Student.grades.GOOD));
            studentRepository.save(new Student("Denis", "Cionca", 20, false, Student.grades.MEDIUM));

            studentRepository.findAll().forEach(student -> log.info("Preloaded " + student));
        };
    }
}
