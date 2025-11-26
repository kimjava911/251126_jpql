package kr.java.jpql.controller;

import kr.java.jpql.model.entity.School;
import kr.java.jpql.model.entity.Student;
import kr.java.jpql.model.repository.SchoolRepository;
import kr.java.jpql.model.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class StudentController {
    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    public StudentController(StudentRepository studentRepository, SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }

    @GetMapping
    public String index(Model model) {
        addStudents();
        model.addAttribute("students1", studentRepository.findAll());
        model.addAttribute("students2",
                List.of(
                        studentRepository.findById(3),
                        studentRepository.findById(2),
                        studentRepository.findById(1))
                );
        model.addAttribute("students3", studentRepository.findByWhere());
        model.addAttribute("schools1", schoolRepository.findAll());
        return "index";
    }

    public void addStudents() {
        School sc1 = new School();
        schoolRepository.save(sc1);
        School sc2 = new School();
        schoolRepository.save(sc2);

        Student s1 = new Student();
        s1.setName("김자바");
        s1.setAge(25);
        s1.setCity("대전");
        s1.setSchool(sc1);
        studentRepository.save(s1);
        Student s2 = new Student();
        s2.setName("박자바");
        s2.setAge(30);
        s2.setCity("부산");
        studentRepository.save(s2);
        Student s3 = new Student();
        s3.setName("김디비");
        s3.setAge(45);
        s3.setCity("서울");
        s3.setSchool(sc2);
        studentRepository.save(s3);
    }
}
