package igor.jesus.examesdecertificacao.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import igor.jesus.examesdecertificacao.model.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>{

}
