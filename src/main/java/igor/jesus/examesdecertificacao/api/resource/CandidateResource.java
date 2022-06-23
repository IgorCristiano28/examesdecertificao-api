package igor.jesus.examesdecertificacao.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igor.jesus.examesdecertificacao.api.resource.api.dto.CandidateDto;
import igor.jesus.examesdecertificacao.exception.ErroAutenticacao;
import igor.jesus.examesdecertificacao.exception.RegraNegocioException;
import igor.jesus.examesdecertificacao.model.entity.Candidate;
import igor.jesus.examesdecertificacao.service.CandidateService;
import igor.jesus.examesdecertificacao.service.ExamService;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateResource {
	
	private final CandidateService service;
	private final ExamService examService;
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar (@RequestBody CandidateDto dto) {
		
		try {
		Candidate candidateAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
		    return ResponseEntity.ok(candidateAutenticado);
		}catch (ErroAutenticacao e) {
		return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody CandidateDto dto) {
		
		Candidate candidate = Candidate.builder()
				.nome(dto.getNome())
				.email(dto.getEmail())
				.senha(dto.getSenha()).build();
		
		try {
			Candidate candidateSalvo = service.salvar(candidate);
			return new ResponseEntity(candidateSalvo,HttpStatus.CREATED);
			
		}catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}
		
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		return service.obterPorId(id).map(entidade -> {
			service.delete(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}).orElseGet(() -> 
	    new ResponseEntity("Lancamento não encontrado na base de Dados.",HttpStatus.BAD_REQUEST));
		
	}
	
	@Autowired
	public CandidateResource(ExamService examService, CandidateService candidateService){
	     this.examService = examService;
	     this.service = candidateService;
	}
	

}
