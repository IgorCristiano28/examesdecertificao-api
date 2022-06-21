package igor.jesus.examesdecertificacao.api.resource.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDto {
	
	private Long id;
	private String descricao;

}
