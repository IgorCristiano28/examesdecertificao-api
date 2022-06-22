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
import igor.jesus.examesdecertificacao.api.resource.api.dto.RoomDto;
import igor.jesus.examesdecertificacao.exception.ErroAutenticacao;
import igor.jesus.examesdecertificacao.exception.RegraNegocioException;
import igor.jesus.examesdecertificacao.model.entity.Availability;
import igor.jesus.examesdecertificacao.model.entity.Candidate;
import igor.jesus.examesdecertificacao.model.entity.Room;
import igor.jesus.examesdecertificacao.service.AvailabilityService;
import igor.jesus.examesdecertificacao.service.CandidateService;
import igor.jesus.examesdecertificacao.service.RoomService;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomResource {
	
	private final RoomService service;
	
	//public RoomResource(RoomService service) {
	//	this.service = service;
		
	//}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody RoomDto dto) {
		
		Room room = Room.builder()
				.descricao(dto.getDescricao()).build();
		
		try {
			Room roomSalvo = service.createRoom(room);
			return new ResponseEntity(roomSalvo,HttpStatus.CREATED);
			
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
