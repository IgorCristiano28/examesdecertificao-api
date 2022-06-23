package igor.jesus.examesdecertificacao.api.resource.api.dto;

import lombok.Builder;
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
