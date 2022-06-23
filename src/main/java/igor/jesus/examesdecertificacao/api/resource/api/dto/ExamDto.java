package igor.jesus.examesdecertificacao.api.resource.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExamDto {
	
	private Long id;
	private String descricao;
	private Long candidate;
	private Long availability;


}
