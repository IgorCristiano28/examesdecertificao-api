package igor.jesus.examesdecertificacao.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import igor.jesus.examesdecertificacao.model.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	
	boolean existsByEmail(String email);

	Optional<Candidate> findByEmail (String email);

}
