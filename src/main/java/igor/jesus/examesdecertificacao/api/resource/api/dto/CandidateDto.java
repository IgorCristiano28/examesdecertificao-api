package igor.jesus.examesdecertificacao.api.resource.api.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CandidateDto {
	
	private Long id;
	private String email;
	private String nome;
	private String senha;

}
