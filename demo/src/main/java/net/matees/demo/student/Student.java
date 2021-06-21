package net.matees.demo.student;

import javax.persistence.*;

@Entity //marks the class as a bean
@Table // tell the program the attributes such as a name (program = database)
public class Student {

    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(generator = "student_sequence")
    private Long id;
    private String name;
    @Transient
    private GradeLevel gradeLevel;
    private Short age;
    private String email;

    public Student(String name, GradeLevel gradeLevel, Short age, String email) {
        this.name = name;
        this.gradeLevel = gradeLevel;
        this.age = age;
        this.email = email;
    }

    public Student(String name, Long id, GradeLevel gradeLevel, Short age, String email) {
        this.name = name;
        this.id = id;
        this.gradeLevel = gradeLevel;
        this.age = age;
        this.email = email;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gradeLevel=" + gradeLevel +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
