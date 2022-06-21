package igor.jesus.examesdecertificacao.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igor.jesus.examesdecertificacao.api.resource.api.dto.AvailabilityDto;
import igor.jesus.examesdecertificacao.api.resource.api.dto.CandidateDto;
import igor.jesus.examesdecertificacao.exception.ErroAutenticacao;
import igor.jesus.examesdecertificacao.exception.RegraNegocioException;
import igor.jesus.examesdecertificacao.model.entity.Availability;
import igor.jesus.examesdecertificacao.model.entity.Candidate;
import igor.jesus.examesdecertificacao.service.AvailabilityService;
import igor.jesus.examesdecertificacao.service.CandidateService;



@RestController
@RequestMapping("/api/availabity")
public class AvailabilitResource {
	
	private AvailabilityService service;
	
	public AvailabilitResource(AvailabilityService service) {
		this.service = service;
		
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody AvailabilityDto dto) {
		
		Availability availability = Availability.builder()
				.descricao(dto.getDescricao())
				.status(dto.getStatus())
				.room(dto.getId_room()).build();
		
		try {
			Availability availabilitySalvo = service.createAvailability(availability);
			return new ResponseEntity(availabilitySalvo,HttpStatus.CREATED);
			
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
	

}
