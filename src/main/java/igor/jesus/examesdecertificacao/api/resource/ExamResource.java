package igor.jesus.examesdecertificacao.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import igor.jesus.examesdecertificacao.api.resource.api.dto.ExamDto;
import igor.jesus.examesdecertificacao.exception.RegraNegocioException;
import igor.jesus.examesdecertificacao.model.entity.Availability;
import igor.jesus.examesdecertificacao.model.entity.Candidate;
import igor.jesus.examesdecertificacao.model.entity.Exam;
import igor.jesus.examesdecertificacao.model.enums.StatusAvailability;
import igor.jesus.examesdecertificacao.service.AvailabilityService;
import igor.jesus.examesdecertificacao.service.CandidateService;
import igor.jesus.examesdecertificacao.service.ExamService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamResource {
	
	
	private final ExamService service;
	
	private final CandidateService candidateService;
	
	private final AvailabilityService availabilityService;
	
	@GetMapping
	public ResponseEntity buscar(
			
			@RequestParam(value = "descricao", required = false) String descricao,
			@RequestParam ( "candidate") Long idCandidate,
			@RequestParam ( "availability") Long idAvailability
			) {
		Exam examFiltro = new Exam();
		examFiltro.setDescricao(descricao);
		
		Optional<Candidate> candidate = candidateService.obterPorId(idCandidate);
		Optional<Availability> availability = availabilityService.obterPorId(idAvailability);
		
		if(!candidate.isPresent()) {
			return ResponseEntity.badRequest().body("N??o foi poss??vel realizar a consulta. Candidato n??o encontrado para a pesquisa realizada");
		}else {
			examFiltro.setCandidate(candidate.get());	
		}
		
		if(!availability.isPresent()) {
			return ResponseEntity.badRequest().body("N??o foi poss??vel realizar a consulta. Disponibilidade n??o encontrada para a pesquisa realizada");
		}else {
			examFiltro.setAvailability(availability.get());
		}
		
		List<Exam> exam = service.buscar(examFiltro);
		return ResponseEntity.ok(exam);
		
	}
	
	
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody ExamDto dto) {
		try {
		Exam exam = converter(dto);
		exam = service.salvar(exam);
		return new ResponseEntity(exam,HttpStatus.CREATED);
		}catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			}
	}
	
	
		
	@PutMapping("{id}")
	public ResponseEntity atualizar (@PathVariable ("id") Long id, @RequestBody ExamDto dto ) {
		return service.obterPorId(id).map(entity -> {
			try {
			Exam exam = converter(dto);
			exam.setId(entity.getId());
			service.atualizar(exam);
			return ResponseEntity.ok(exam);
			}catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet( () -> 
		    new ResponseEntity("Exam n??o encontrado na base de Dados.", HttpStatus.BAD_REQUEST));
		
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		return service.obterPorId(id).map(entidade -> {
			service.delete(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}).orElseGet(() -> 
	    new ResponseEntity("Lancamento n??o encontrado na base de Dados.",HttpStatus.BAD_REQUEST));
		
	}
	
	
	private Exam converter (ExamDto dto) {
		Exam exam = new Exam();
		exam.setId(dto.getId());
		exam.setDescricao(dto.getDescricao());
	
		Candidate candidate = candidateService.
		obterPorId(dto.getCandidate())
		.orElseThrow(() -> new RegraNegocioException("Candidato n??o encontrado para o Id Informado"));
		
		Availability availability = availabilityService.
				 obterPorId(dto.getAvailability())
				.orElseThrow(() -> new RegraNegocioException("Avalia????o n??o encontrada para o Id Informado"));
				
		
		exam.setCandidate(candidate);
		
		exam.setAvailability(availability);
			
		return exam;
		
	}
	
	

}
