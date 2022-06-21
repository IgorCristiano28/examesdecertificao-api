package igor.jesus.examesdecertificacao.api.resource.api.dto;

import igor.jesus.examesdecertificacao.model.entity.Availability;
import igor.jesus.examesdecertificacao.model.entity.Candidate;
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
