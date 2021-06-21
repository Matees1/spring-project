package net.matees.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepo repo) {
        return args -> {
            Student banan = new Student("Banan", GradeLevel.SEVENTH, (short) 11, "banantajam@gmail.com");
            Student maytham = new Student("Maytham", GradeLevel.NINTH, (short) 14, "maythamajam@gmail.com");

            repo.saveAll(List.of(maytham, banan));
        };
    }
}
