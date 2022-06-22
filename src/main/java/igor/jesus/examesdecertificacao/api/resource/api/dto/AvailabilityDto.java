package igor.jesus.examesdecertificacao.api.resource.api.dto;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import igor.jesus.examesdecertificacao.model.entity.Room;
import igor.jesus.examesdecertificacao.model.enums.StatusAvailability;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AvailabilityDto {
	
	
	
	private Long id;
	private String descricao;
	private String status;
	private Long room;
	
	

}
