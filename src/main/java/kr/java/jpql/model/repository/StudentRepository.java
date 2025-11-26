package kr.java.jpql.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import kr.java.jpql.model.entity.Student;
import kr.java.jpql.model.entity.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private final EntityManagerFactory emf;

    public StudentRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // 생성
    public void save(Student student) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(student); // 등록
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("추가 실패");
        } finally {
            em.close();
        }
    }

    // WHERE
    public List<Student> findByWhere() {
        EntityManager em = emf.createEntityManager();
        List<Student> result = new ArrayList<>();
        try {
            result.addAll(
                em.createQuery("SELECT s FROM Student s WHERE s.name = '김자바'", Student.class)
                        .getResultList()
            );
            result.addAll(
                    em.createQuery("SELECT s FROM Student s WHERE s.age BETWEEN 20 AND 40", Student.class)
                            .getResultList()
            );
            result.addAll(
                    em.createQuery("SELECT s FROM Student s WHERE s.city IN ('부산', '대전')", Student.class)
                            .getResultList()
            );
            result.addAll(
                    em.createQuery("SELECT s FROM Student s WHERE s.name LIKE '김%'", Student.class)
                            .getResultList()
            );
            return result;
        } finally {
            em.close();
        }
    }

    // 개별 조회
    public Student findById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
//            return em.find(Student.class, id);
            // :param -> setParameter
            // 1개 -> SingleResult 1개만.
            // 이름 바인딩 ***
            return em.createQuery("SELECT s FROM Student s WHERE s.id = :id", Student.class)
                    .setParameter("id", id)
                    .getSingleResult();
            // 위치 바인딩 (비권장)
//            return em.createQuery("SELECT s FROM Student s WHERE s.id = ?1", Student.class)
//                    .setParameter(1, id)
//                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    // 전체 조회
    public List<Student> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            // jpql -> em.createQuery
            // select s from Student s
            // -> select 's' from Student 's'
            // Student.class (@Entity)
            // .getResultList();
            return em.createQuery(
                    "select s from Student s", Student.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public List<StudentDTO> findByDTO() {
        EntityManager em = emf.createEntityManager();
        // record, class DTO -> service? -> JPQL.
        try {
            return em.createQuery(
                    "select new kr.java.jpql.model.entity.StudentDTO(s.name)" +
                            "from Student s", StudentDTO.class
            ).getResultList();
        } finally {
            em.close();
        }
    }
}
