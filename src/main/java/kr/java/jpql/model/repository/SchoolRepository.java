package kr.java.jpql.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import kr.java.jpql.model.entity.School;
import kr.java.jpql.model.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SchoolRepository {
    private final EntityManagerFactory emf;

    public SchoolRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // 생성
    public void save(School school) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(school); // 등록
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("추가 실패");
        } finally {
            em.close();
        }
    }

    // 전체 조회
    public List<School> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "select sc from School sc", School.class
            ).getResultList();
        } finally {
            em.close();
        }
    }
}
