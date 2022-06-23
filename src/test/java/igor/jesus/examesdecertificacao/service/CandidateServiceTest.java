package igor.jesus.examesdecertificacao.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import igor.jesus.examesdecertificacao.exception.RegraNegocioException;
import igor.jesus.examesdecertificacao.model.entity.Candidate;
import igor.jesus.examesdecertificacao.model.repository.CandidateRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Profile("test")
public class CandidateServiceTest {
	
	@Autowired
	CandidateService service;
	
	@Autowired
	CandidateRepository repository;
		
	
	@Test
	public void deveValidarEmailIntegracao() {
		assertDoesNotThrow(() -> {
		
		//cenario
		repository.deleteAll();
		 
		//acao
		service.validarEmail("email@email.com");
		});		
	
}
	
	
	
	@Test 
	public void deveLancarErroAoValidarEmailQuandoExistirEmailcadastradoIntegracao() {
		assertThrows(RegraNegocioException.class,() -> {
		
		//cenario
		Candidate candidate = Candidate.builder().nome("candidate").email("email@email.com").senha("teste").build();
		repository.save(candidate);
		  
		//acao
		service.validarEmail("email@email.com");
		});
	
	
	}
	
	
		

}
