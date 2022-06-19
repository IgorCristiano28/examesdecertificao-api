package igor.jesus.examesdecertificacao.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import igor.jesus.examesdecertificacao.model.entity.Candidate;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CandidatoRepositoryTest {
	
	@Autowired
	CandidateRepository repository;
	
	@Autowired
	TestEntityManager entityManager;

	@Test @Order(1)
	public void deveVerificarAExistenciaDeUmEmail() {
		Candidate candidate = criarCandidate();
		entityManager.persist(candidate);
		
		
		//açao/execucao
		boolean result = repository.existsByEmail("candidate@email.com");
		
		
		//verificacao
		Assertions.assertThat(result).isTrue();
		
	}
	
	@Test @Order(2)
	public void deveRetornaFalsoQuandoNaoHouverCandidatoCadastradoComEmail() {
		//cenario
		//repository.deleteAll();
		
		//acao
		boolean result =repository.existsByEmail("candidate@email.com");
		
		//verificacao
		Assertions.assertThat(result).isFalse();
		
	}
	
	@Test @Order(3)
	public void devePersistirUmCandidatoNabaseDeDados() {
		//cenario
		Candidate candidate = criarCandidate();
				
		
		//acao
		Candidate usuarioSalvo = repository.save(candidate);
		
		//verificacao
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
			
	}
	
	
	@Test @Order(4)
	public void deveBuscarUmCandidatoPorEmail() {
		//cenario a classe não pode ter id, se não lanca excesao propriedade do entitymanager
		Candidate candidate = criarCandidate();
		entityManager.persist(candidate);
		
		//verificao
		Optional<Candidate> result = repository.findByEmail("candidate@email.com");
		
		Assertions.assertThat(result.isPresent()).isTrue();
		
	}
	
	@Test @Order(5)
	public void deveRetornaVazioAoBuscarCandidatoPorEmailQuandoNaoExisteNaBase() {
		//cenario
		
		
		//verificao
		Optional<Candidate> result = repository.findByEmail("candidate@email.com");
		
		Assertions.assertThat(result.isPresent()).isFalse();
		
	}
	
	public static Candidate criarCandidate() {
		return   Candidate 
				.builder()
				.email("candidate@email.com")
				.senha("senha")
				.build();
		
		
	}
	

}
