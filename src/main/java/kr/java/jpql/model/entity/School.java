package kr.java.jpql.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long schoolId;

    @OneToMany(mappedBy = "school", fetch = FetchType.EAGER)
    private List<Student> students;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolId=" + schoolId +
                ", students=" + students +
                '}';
    }
}
