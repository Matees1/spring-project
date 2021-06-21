package net.matees.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepo repo;

    @Autowired
    public StudentService(StudentRepo repo) {
        this.repo = repo;
    }
    public List<Student> getStudents() {
        return repo.findAll();
    }

    public void deleteStudent(Long id) {
        boolean exists = repo.existsById(id);
        if (!exists) {
            throw new IllegalArgumentException("student with id " + id + " does not exist!");
        }else{
            repo.deleteById(id);
        }
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = repo.findById(id).orElseThrow(() -> new IllegalStateException("Student with the id does not exist! ID: " + id));

        //.equals will return null if the two items are the same type/object
        //what the .equals will do is it will check if the old and the new names are the same,
        // and if they are not it will set the new name
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {

            //checks if the email is already taken, if not then set the new email to the existing one? that is a wonky sentence lmfao
            Optional<Student> emailOptional = repo.findStudentByEmail(email);
            if (emailOptional.isPresent()) {
                throw new IllegalArgumentException("The email is already taken!");
            }

            student.setEmail(email);
        }

    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = repo.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalArgumentException("The email already exists!");
        }

        repo.save(student);
        System.out.printf("The name of the student is %s \n", student.getName());
        System.out.printf("The age of the student is %s \n", student.getAge());
        System.out.printf("The email address of the student is %s \n", student.getEmail());
        System.out.printf("The ID of the student is %s \n", student.getId());
        if (student.getGradeLevel() != null) {
            System.out.printf("The grade level of the student is %s \n", student.getGradeLevel());
        }else{
            System.out.printf("The student is not in a grade level :(\n");
        }
    }
}
