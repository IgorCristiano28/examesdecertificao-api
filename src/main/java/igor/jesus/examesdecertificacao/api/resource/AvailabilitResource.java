package igor.jesus.examesdecertificacao.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igor.jesus.examesdecertificacao.api.resource.api.dto.AtualizarStatusDto;
import igor.jesus.examesdecertificacao.api.resource.api.dto.AvailabilityDto;
import igor.jesus.examesdecertificacao.exception.RegraNegocioException;
import igor.jesus.examesdecertificacao.model.entity.Availability;
import igor.jesus.examesdecertificacao.model.entity.Room;
import igor.jesus.examesdecertificacao.model.enums.StatusAvailability;
import igor.jesus.examesdecertificacao.service.AvailabilityService;
import igor.jesus.examesdecertificacao.service.RoomService;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/availabity")
@RequiredArgsConstructor
public class AvailabilitResource {
	
	private final AvailabilityService service;
	
	private final RoomService roomService;
	
	//public AvailabilitResource(AvailabilityService service) {
	//	this.service = service;
		
	//}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody AvailabilityDto dto) {
		
		/*
		 * Availability availability = Availability.builder()
		 * .descricao(dto.getDescricao()) .status(dto.getStatus()).build();
		 */
		
		try {
			Availability availability = converter(dto);
			availability = service.salvar(availability);
			return new ResponseEntity(availability,HttpStatus.CREATED);	
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
	    new ResponseEntity("Disponibilidade não encontrado na base de Dados.",HttpStatus.BAD_REQUEST));
		
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody AvailabilityDto dto) {
		//a Entity é o retorno do meu optional
		return service.obterPorId(id).map(Entity -> {
			try {
			Availability lancamento = converter (dto);
			lancamento.setId(Entity.getId());
			service.atualizar(lancamento);
			return ResponseEntity.ok(lancamento);
			}catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> 
		    new ResponseEntity("Disponibilidade não encontrado na base de Dados.",HttpStatus.BAD_REQUEST));
		
	}
	
	
	
	
	@PutMapping("{id}/atualizar-status")
	public ResponseEntity atualizarStatus(@PathVariable  ("id")Long id, @RequestBody AtualizarStatusDto dto) {
		return service.obterPorId(id).map(Entity -> {
			StatusAvailability statusSelecionado = StatusAvailability.valueOf(dto.getStatus());
			if(statusSelecionado == null) {
				return ResponseEntity.badRequest().body("Não foi possível atualizar o status da disponibilidade, envie um status valido");	
			}
			
			try {
				Entity.setStatus(statusSelecionado);
				service.atualizar(Entity);
				return ResponseEntity.ok(Entity);		
				
			}catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
		}).orElseGet(() -> 
	    new ResponseEntity("Lancamento não encontrado na base de Dados.",HttpStatus.BAD_REQUEST));
		
		
	}
	
	
	
	
	private Availability converter (AvailabilityDto dto) {
		Availability availability = new Availability();
		availability.setId(dto.getId());
		availability.setDescricao(dto.getDescricao());
		
		Room room = roomService.
		obterPorId(dto.getRoom())
		.orElseThrow(() -> new RegraNegocioException("Sala não encontrada para o Id informado"));
		
		availability.setRoom(room);
		
		if(dto.getStatus() != null) {
			availability.setStatus(StatusAvailability.valueOf(dto.getStatus()));
			
		}
	
		return availability;
				
		
		
	}
	
	

}
